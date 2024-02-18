package com.youcode.come2play.web.rest;

import com.youcode.come2play.entities.Reservation;
import com.youcode.come2play.entities.Stadium;
import com.youcode.come2play.entities.Team;
import com.youcode.come2play.service.StadiumService;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/stadium")
public class StadiumResource {
    private final StadiumService service;
    @PostMapping
    public ResponseEntity<Stadium> add(@RequestBody Stadium stadium) throws Exception {
        return ResponseEntity.ok( service.save(stadium));
    }
    @PostMapping
    public ResponseEntity<Stadium> edit(@RequestBody Long id) throws Exception {
        return ResponseEntity.ok( service.edit(id));
    }
    @GetMapping
    public List<Stadium> getAll(@ParameterObject Pageable pageable){
        return service.findAll((org.springframework.data.domain.Pageable) pageable);
    }
    @PostMapping
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        service.delete(id);
        return ResponseEntity.ok("The stadium is deleted successfully");
    }

}
