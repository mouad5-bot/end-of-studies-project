package com.youcode.come2play.service;

import com.youcode.come2play.entities.Team;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


public interface TeamService {
    Team save(Team team, MultipartFile file) throws Exception;
    Team edit(Long id) throws Exception;
    void delete(Long id) throws Exception;
    List<Team> findAll(Pageable pageable);
    List<Team> findByCreatedBy(Long id);
    Optional<Team> findById(Long id);

}
