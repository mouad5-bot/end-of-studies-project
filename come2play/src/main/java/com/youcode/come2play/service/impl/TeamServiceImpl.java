package com.youcode.come2play.service.impl;

import com.youcode.come2play.entities.Team;
import com.youcode.come2play.entities.UserApp;
import com.youcode.come2play.repository.TeamRepository;
import com.youcode.come2play.service.TeamService;
import com.youcode.come2play.service.UserAppService;
import com.youcode.come2play.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository repository;
    private final UserAppService userAppService;
    private final FileUtils fileUtils;

    @Override
    public Team save(Team team, MultipartFile file) throws Exception {
        String storedFile = fileUtils.storeFile(file);
        team.setTeamImage(storedFile);
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

    public List<Team> findByCreatedBy(Long id) {
        return repository.findByCreatedBy(id);
    }

    @Override
    public Optional<Team> findById(Long id) {
        return repository.findById(id);
    }

    public Long getCurrentUserId() {
        UserApp user = userAppService.getCurrentUser();
        return user.getId();
    }
}
