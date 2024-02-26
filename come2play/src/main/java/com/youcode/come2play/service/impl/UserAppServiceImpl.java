package com.youcode.come2play.service.impl;

import com.youcode.come2play.entities.UserApp;
import com.youcode.come2play.repository.UserAppRepository;
import com.youcode.come2play.service.UserAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserAppServiceImpl implements UserAppService {
    private final UserAppRepository repository;
    @Override
    public UserApp save(UserApp userApp) throws Exception {
        return repository.save(userApp);
    }

    @Override
    public UserApp edit(Long id) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {
         //repository.delete
    }

    @Override
    public List<UserApp> findAll(Pageable pageable) {
        return repository.findAll(pageable).toList();
    }
}
