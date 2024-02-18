package com.youcode.come2play.web.rest;

import com.youcode.come2play.entities.Reservation;
import com.youcode.come2play.entities.Team;
import com.youcode.come2play.service.ReservationService;
import com.youcode.come2play.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/reservation")
public class ReservationResource {
    private final ReservationService service;
    @PostMapping
    public ResponseEntity<Reservation> add(@RequestBody Reservation reservation) throws Exception {
        Reservation reservation1 = service.save(reservation);
        if(reservation1 == null) {
            return ResponseEntity.badRequest().body(reservation);
        }else {
            return ResponseEntity.ok(reservation1);
        }
    }
    @PostMapping
    public ResponseEntity<Reservation> edit(@RequestBody Long id) throws Exception {
        return ResponseEntity.ok( service.edit(id));
    }
    @GetMapping
    public List<Reservation> getAll(@ParameterObject Pageable pageable){
        return service.findAll((org.springframework.data.domain.Pageable) pageable);
    }

    @PostMapping
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        service.delete(id);
        return ResponseEntity.ok("The reservation is deleted successfully");
    }
}
