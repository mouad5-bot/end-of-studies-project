package com.youcode.come2play.service;

import com.youcode.come2play.dtos.dto.RoleDto;
import com.youcode.come2play.entities.Team;
import com.youcode.come2play.entities.UserApp;
import com.youcode.come2play.web.exception.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

public interface UserAppService {
    UserApp save(UserApp userApp);

    UserApp edit(Long id);

    List<UserApp> findAll(Pageable pageable);

    Optional<UserApp> findById(Long id);

    Optional<UserApp> findByEmail(String email);

    void revokeRole(Long id, List<RoleDto> roles) throws ValidationException;

    UserApp assigneRole(Long id, List<RoleDto> roles) throws ValidationException, ResourceNotFoundException;

    List<String> getAuthorities();

    UserDetailsService userDetailsService();

    UserApp findByUsername(String username);

    List<String> getMyAuthorities();

    public UserApp getCurrentUser();

    void softDelete(Long id);

    void forceDelete(Long id);

    void delete(Long id);
}
