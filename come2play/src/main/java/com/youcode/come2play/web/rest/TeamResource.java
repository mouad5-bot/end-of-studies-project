package com.youcode.come2play.web.rest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youcode.come2play.dtos.dto.response.MyTeamResponseDto;
import com.youcode.come2play.dtos.dto.response.TeamResponseDto;
import com.youcode.come2play.dtos.mapper.TeamMapper;
import com.youcode.come2play.entities.Team;
import com.youcode.come2play.entities.UserApp;
import com.youcode.come2play.service.TeamService;
import com.youcode.come2play.service.UserAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/team")
public class TeamResource {
    private final TeamService service;
    private final ObjectMapper objectMapper;
    private final TeamMapper teamMapper;
    private final UserAppService userAppService;

        @PostMapping("/add")
        public ResponseEntity<Team> add(
                @RequestParam("team") String teamStr,
                @RequestParam("file") MultipartFile image
        ) throws Exception {
            Team team = objectMapper.readValue(teamStr, Team.class);

            Team savedTeam = service.save(team, image);

            if (savedTeam == null) {
                return ResponseEntity.badRequest().body(team);
            } else {
                return ResponseEntity.ok(savedTeam);
            }
        }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Team> edit(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(service.edit(id));
    }

    @GetMapping("/getAll")
    public List<Team> getAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/myTeams")
    public ResponseEntity<List<MyTeamResponseDto>> getMyTeams() {
        Long loggedInUser = userAppService.getCurrentUser().getId();
        List<Team> teamList = service.findByCreatedBy(loggedInUser);
        return ResponseEntity.ok(
                teamList.stream()
                        .map(MyTeamResponseDto::toDto)
                        .toList()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        service.delete(id);
        return ResponseEntity.ok("The team is deleted successfully");
    }
}
