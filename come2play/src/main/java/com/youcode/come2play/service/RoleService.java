package com.youcode.come2play.service;

import com.youcode.come2play.entities.Role;
import com.youcode.come2play.entities.Team;
import org.springframework.data.domain.Pageable;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role save(Role role) throws ValidationException;
    Optional<Role> findByName(String name) ;
    List<Role> findAll(Pageable pageable);
    void delete(Long id);

    Role findById(Long id);
}
