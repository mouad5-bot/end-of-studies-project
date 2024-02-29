package com.youcode.come2play.repository;

import com.youcode.come2play.entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserAppRepository extends JpaRepository<UserApp, Long> {
    Optional<UserApp> findByEmail(String email);

    @Modifying
    @Query("UPDATE UserApp u SET u.enabled = :enable WHERE u.id = :id")
    void updateEnableById(boolean enable, Long id);

    @Query("SELECT u FROM UserApp u WHERE u.id = ?1 AND u.deletedAt IS NULL")
    void softDelete(Long id);

    @Query("SELECT u FROM UserApp u WHERE u.id = ?1 AND u.deletedAt IS NOT NULL")
    void forceDelete(Long id);
}
