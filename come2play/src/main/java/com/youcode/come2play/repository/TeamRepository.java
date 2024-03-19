package com.youcode.come2play.repository;

import com.youcode.come2play.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("SELECT t FROM Team t WHERE t.createdBy = ?1")
    List<Team> findByCreatedBy(Long id);
}
