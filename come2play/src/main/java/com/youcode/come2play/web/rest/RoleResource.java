package com.youcode.come2play.web.rest;

import com.youcode.come2play.entities.Role;
import com.youcode.come2play.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/role")
public class RoleResource {
    private final RoleService service;

    @PostMapping("/add")
    public ResponseEntity<Role> add(@RequestBody Role role) throws Exception {
        return ResponseEntity.ok(service.save(role));
    }

    @GetMapping("/getAll")
    public List<Role> getAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        service.delete(id);
        return ResponseEntity.ok("The role is deleted successfully");
    }
}
