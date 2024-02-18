package com.youcode.come2play.service;

import com.youcode.come2play.entities.Role;
import com.youcode.come2play.entities.Team;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {
    Role save(Role role) throws Exception;
    Role edit(Long id) throws Exception;
    void delete(Long id) throws Exception;
    List<Role> findAll(Pageable pageable);
}
