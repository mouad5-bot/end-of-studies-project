package com.youcode.come2play.web.rest;

import com.youcode.come2play.entities.Reservation;
import com.youcode.come2play.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/reservation")
public class ReservationResource {
    private final ReservationService service;

    @PostMapping("/add")
    public ResponseEntity<Reservation> add(@RequestBody Reservation reservation) throws Exception {
        Reservation savedReservation = service.save(reservation);
        if (savedReservation == null) {
            return ResponseEntity.badRequest().body(reservation);
        } else {
            return ResponseEntity.ok(savedReservation);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Reservation> edit(@PathVariable Long id) throws Exception {
        Reservation editedReservation = service.edit(id);
        return ResponseEntity.ok(editedReservation);
    }

    @GetMapping("/getAll")
    public List<Reservation> getAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        service.delete(id);
        return ResponseEntity.ok("The reservation is deleted successfully");
    }
}
