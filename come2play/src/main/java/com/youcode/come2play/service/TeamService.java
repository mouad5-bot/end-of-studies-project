package com.youcode.come2play.service;

import com.youcode.come2play.entities.Team;
import com.youcode.come2play.repository.TeamRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


public interface TeamService {
    Team save(Team team) throws Exception;
    Team edit(Long id) throws Exception;
    void delete(Long id) throws Exception;
    List<Team> findAll(Pageable pageable);
}
