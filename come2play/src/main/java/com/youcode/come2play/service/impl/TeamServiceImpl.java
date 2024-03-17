package com.youcode.come2play.service.impl;

import com.youcode.come2play.entities.Team;
import com.youcode.come2play.entities.UserApp;
import com.youcode.come2play.repository.TeamRepository;
import com.youcode.come2play.security.SecurityUtils;
import com.youcode.come2play.service.TeamService;
import com.youcode.come2play.service.UserAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository repository;
    private final UserAppService userAppService;

    @Override
    public Team save(Team team) throws Exception {
        team.setCreatedBy(getCurrentUserId());
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

    @Override
    public List<Team> findByCreatedBy(Long id) {
        return repository.findByCreatedBy(id);
    }

    public Long getCurrentUserId() {
        UserApp user = userAppService.getCurrentUser();
        return user.getId();
    }
}
