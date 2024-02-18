package com.youcode.come2play.service;

import com.youcode.come2play.entities.Team;
import com.youcode.come2play.entities.UserApp;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserAppService {
    UserApp save(UserApp userApp) throws Exception;
    UserApp edit(Long id) throws Exception;
    void delete(Long id) throws Exception;
    List<UserApp> findAll(Pageable pageable);
}
