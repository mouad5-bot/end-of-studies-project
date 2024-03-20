package com.youcode.come2play.service;

import com.youcode.come2play.dtos.dto.request.ReservationDto;
import com.youcode.come2play.dtos.dto.request.ReservationRequestDto;
import com.youcode.come2play.entities.Reservation;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReservationService {
    ReservationDto save(ReservationDto reservation) throws Exception;
    Reservation edit(Long id) throws Exception;
    void delete(Long id) throws Exception;
    List<Reservation> findAll(Pageable pageable);
}
