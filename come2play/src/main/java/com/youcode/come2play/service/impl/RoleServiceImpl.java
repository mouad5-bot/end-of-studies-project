package com.youcode.come2play.service.impl;

import com.youcode.come2play.entities.Role;
import com.youcode.come2play.repository.RoleRepository;
import com.youcode.come2play.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    @Override
    public Role save(Role role) throws Exception {
        return repository.save(role);
    }

    @Override
    public Role edit(Long id) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {

    }

    @Override
    public List<Role> findAll(Pageable pageable) {
        return null;
    }
}
