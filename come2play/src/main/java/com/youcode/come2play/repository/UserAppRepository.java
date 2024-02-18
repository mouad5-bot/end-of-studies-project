package com.youcode.come2play.repository;

import com.youcode.come2play.entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAppRepository extends JpaRepository<UserApp, Long> {
}
