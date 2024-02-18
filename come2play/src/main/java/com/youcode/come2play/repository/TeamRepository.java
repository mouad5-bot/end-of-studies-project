package com.youcode.come2play.repository;

import com.youcode.come2play.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
