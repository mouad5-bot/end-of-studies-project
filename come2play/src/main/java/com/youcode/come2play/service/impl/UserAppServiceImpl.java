package com.youcode.come2play.service.impl;

import com.youcode.come2play.dtos.dto.RoleDto;
import com.youcode.come2play.entities.Role;
import com.youcode.come2play.entities.UserApp;
import com.youcode.come2play.repository.RoleRepository;
import com.youcode.come2play.repository.UserAppRepository;
import com.youcode.come2play.service.RoleService;
import com.youcode.come2play.service.UserAppService;
import com.youcode.come2play.utils.CustomError;
import com.youcode.come2play.web.exception.EmailAlreadyExistException;
import com.youcode.come2play.web.exception.ResourceNotFoundException;
import com.youcode.come2play.security.SecurityUtils;
import com.youcode.come2play.security.AuthoritiesConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static com.youcode.come2play.utils.AppConstants.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserAppServiceImpl implements UserAppService {
    private final UserAppRepository userRepository;
    private final RoleService roleService;
    private final RoleRepository roleRepository;


    @Override
    public UserApp save(UserApp user) {
        findByEmail(user.getEmail()).ifPresent(u -> {
            throw new EmailAlreadyExistException();
        });
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        return userRepository.save(user);
    }

    @Override
    public UserApp edit(Long id) {
        return null;
    }

    @Override
    public List<UserApp> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).toList();
    }

    @Override
    public Optional<UserApp> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserApp> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void revokeRole(Long id, List<RoleDto> roles) throws ValidationException {
        Optional<UserApp> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            UserApp user = userOptional.get();
            List<Role> roleList = new ArrayList<>();
            roles.forEach(roleDto -> roleService.
                    findByName(roleDto.getName()).ifPresent(roleList::add));

            if (new HashSet<>(user.getRoleList()).containsAll(roleList)) {
                user.getRoleList().removeAll(roleList);
                userRepository.save(user);
            } else {
                throw new ValidationException(String.valueOf(CustomError.builder()
                        .field("roles")
                        .message("User does not have all specified roles.")
                        .build()));
            }
        }
        else {
            throw new ValidationException(String.valueOf(CustomError.builder()
                    .field("user id")
                    .message("User does not exist")
                    .build()));
        }
    }

    @Override
    public UserApp assigneRole(Long id, List<RoleDto> roles) throws ValidationException, ResourceNotFoundException {
        Optional<UserApp> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            UserApp user = userOptional.get();
            List<Role> roleList = new ArrayList<>();
            final int[] someExist = {0};
            roles.forEach(roleDto ->
                    roleService.findByName(roleDto.getName())
                            .ifPresentOrElse(
                                    role -> {
                                        if (user.getRoleList().contains(role))
                                            someExist[0] = 1;
                                        roleList.add(role);
                                    },
                                    () -> roleList.add(Role.builder().name(roleDto.getName()).build())));
            if(someExist[0] == 1)
                throw new ValidationException(String.valueOf(CustomError.builder().field("roles").message("User already has some of specified roles.").build()));
            roleRepository.saveAll(roleList);
            user.getRoleList().addAll(roleList);
            return userRepository.save(user);
        }
        throw new ResourceNotFoundException(USER_NOT_FOUND);
    }

    @Override
    public List<String> getAuthorities() {
        return roleService.getALlRoles().stream().map(Role::getName).toList();
    }

    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
    }

    @Override
    public UserApp findByUsername(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
    }

    @Override
    public List<String> getMyAuthorities() {
        return getCurrentUser()
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
    }

    @Override
    public UserApp getCurrentUser() {
        String currentUserLogin = SecurityUtils.getCurrentUserEmail();
        if(currentUserLogin == null)
            throw new BadCredentialsException(USER_NOT_FOUND);
        return this.findByUsername(currentUserLogin);
    }

    @Override
    public void softDelete(Long id) {
        userRepository.softDelete(id);
    }

    @Override
    public void forceDelete(Long id) {
        userRepository.forceDelete(id);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
