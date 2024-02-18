package com.youcode.come2play.service.impl;

import com.youcode.come2play.entities.Team;
import com.youcode.come2play.repository.TeamRepository;
import com.youcode.come2play.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository repository;

    @Override
    public Team save(Team team) throws Exception {
        return repository.save(team);
    }

    @Override
    public Team edit(Long id) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {

    }

    @Override
    public List<Team> findAll(Pageable pageable) {
        return repository.findAll(pageable).toList();
    }
}
