package com.youcode.come2play.web.rest;
import com.youcode.come2play.entities.Team;
import com.youcode.come2play.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/team")
public class TeamResource {
    private final TeamService service;

    @PostMapping("/add")
    public ResponseEntity<Team> add(@RequestBody Team team) throws Exception {
        Team savedTeam = service.save(team);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        service.delete(id);
        return ResponseEntity.ok("The team is deleted successfully");
    }
}
