package com.youcode.come2play.dtos.mapper;

import com.youcode.come2play.dtos.dto.request.ReservationDto;
import com.youcode.come2play.entities.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {
    public Reservation toEntity(ReservationDto requestDto) {

        return Reservation.builder()
                .dateOfMatch(requestDto.getDateOfMatch())
                .numberPhone(requestDto.getNumberPhone())
                .build();
    }
//    public ReservationRequestDto toDto(Reservation reservation) {
//
//        return ReservationRequestDto.builder()
//                .team2(reservation.getTeam2().getId())
//                .build();
//    }
}
