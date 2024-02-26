package com.youcode.come2play.web.rest;
import com.youcode.come2play.entities.Stadium;
import com.youcode.come2play.service.StadiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/stadium")
public class StadiumResource {
    private final StadiumService service;

    @PostMapping("/add")
    public ResponseEntity<Stadium> add(@RequestBody Stadium stadium) throws Exception {
        return ResponseEntity.ok(service.save(stadium));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Stadium> edit(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(service.edit(id));
    }

    @GetMapping("/getAll")
    public List<Stadium> getAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        service.delete(id);
        return ResponseEntity.ok("The stadium is deleted successfully");
    }
}
