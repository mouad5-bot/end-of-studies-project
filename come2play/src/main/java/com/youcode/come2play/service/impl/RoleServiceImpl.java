package com.youcode.come2play.service.impl;

import com.youcode.come2play.entities.Role;
import com.youcode.come2play.repository.RoleRepository;
import com.youcode.come2play.service.RoleService;
import com.youcode.come2play.utils.CustomError;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;


    @Override
    public Role save(Role role) throws ValidationException {
        Optional<Role> optionalRole = roleRepository.findByName(role.getName());
        if (optionalRole.isPresent())
            throw new ValidationException(String.valueOf(new CustomError("name", "Role with this name already exists")));
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<Role> findAll(Pageable pageable) {
        return roleRepository.findAll(pageable).toList();
    }

    @Override
    public List<Role> getALlRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if(role.isPresent())
            roleRepository.delete(role.get());
        else
            throw new NoSuchElementException("Role not found with id: " + id);

    }

    @Override
    public Role findById(Long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if(roleOptional.isEmpty())
            throw new IllegalArgumentException("role doesn't exist with this id: " + id);
        return roleOptional.get();
    }
}
