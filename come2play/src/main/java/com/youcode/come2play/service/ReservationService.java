package com.youcode.come2play.service;

import com.youcode.come2play.dtos.dto.request.ReservationDto;
import com.youcode.come2play.dtos.dto.response.ReservationResponseDto;
import com.youcode.come2play.entities.Reservation;
import com.youcode.come2play.web.exception.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    ReservationDto save(ReservationDto reservation) throws Exception;
    Reservation edit(Long id) throws Exception;
    void delete(Long id) throws Exception;
    List<ReservationResponseDto> findAll(Pageable pageable);
    Optional<Reservation> findById(Long id);

    void approve(Long id) throws ResourceNotFoundException;
}
