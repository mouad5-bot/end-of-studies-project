package com.youcode.come2play.web.rest;

import com.youcode.come2play.entities.Stadium;
import com.youcode.come2play.entities.Team;
import com.youcode.come2play.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springdoc.api.annotations.ParameterObject;


import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/team")
public class TeamResource {
    private final TeamService service;
    @PostMapping
    public ResponseEntity<Team> add(@RequestBody Team team) throws Exception {
        Team team1 = service.save(team);
        if(team1 == null) {
            return ResponseEntity.badRequest().body(team);
        }else {
            return ResponseEntity.ok(team1);
        }
    }
    @PostMapping
    public ResponseEntity<Team> edit(@RequestBody Long id) throws Exception {
        return ResponseEntity.ok( service.edit(id));
    }
    @GetMapping
    public List<Team> getAll(@ParameterObject Pageable pageable){
        return service.findAll((org.springframework.data.domain.Pageable) pageable);
    }

    @PostMapping
    public ResponseEntity delete(@PathVariable Long id) throws Exception {
        service.delete(id);
        return ResponseEntity.ok("The team is deleted successfully");
    }
}
