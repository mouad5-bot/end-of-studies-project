package com.youcode.come2play.web.rest;

import com.youcode.come2play.entities.Team;
import com.youcode.come2play.entities.UserApp;
import com.youcode.come2play.service.UserAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserResource {
    private final UserAppService userService;

    @PostMapping("/add")
    public ResponseEntity<UserApp> addUser(@RequestBody UserApp user) throws Exception {
        UserApp savedUser = userService.save(user);
        if (savedUser == null) {
            return ResponseEntity.badRequest().body(user);
        } else {
            return ResponseEntity.ok(savedUser);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<UserApp> editUser(@PathVariable Long id) throws Exception {
        UserApp editedUser = userService.edit(id);
        return ResponseEntity.ok(editedUser);
    }


    @GetMapping("/getAll")
    public List<UserApp> getAll(Pageable pageable) {
        return userService.findAll(pageable);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) throws Exception {
        userService.delete(id);
        return ResponseEntity.ok("The user is deleted successfully");
    }
}
