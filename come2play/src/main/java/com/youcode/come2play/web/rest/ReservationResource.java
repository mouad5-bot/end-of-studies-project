package com.youcode.come2play.web.rest;

import com.youcode.come2play.dtos.dto.request.ReservationDto;
import com.youcode.come2play.dtos.dto.request.ReservationRequestDto;
import com.youcode.come2play.entities.Reservation;
import com.youcode.come2play.entities.Team;
import com.youcode.come2play.entities.enums.RequestForTeam;
import com.youcode.come2play.entities.enums.Status;
import com.youcode.come2play.service.ReservationService;
import com.youcode.come2play.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/reservation")
public class ReservationResource {
    private final ReservationService service;
    private final TeamService teamService;

    @PostMapping("/add")
    public ResponseEntity<ReservationDto> add(@RequestBody ReservationDto reservation) throws Exception {

        ReservationDto savedReservation = service.save(reservation);
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
