package com.youcode.come2play.dtos.mapper;

import com.youcode.come2play.dtos.dto.request.ReservationDto;
import com.youcode.come2play.entities.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {
    public Reservation toEntity(ReservationDto responseDto) {

        return Reservation.builder()
                .dateOfMatch(responseDto.getDateOfMatch())
                .numberPhone(responseDto.getNumberPhone())
                .build();
    }
//    public ReservationRequestDto toDto(Reservation reservation) {
//
//        return ReservationRequestDto.builder()
//                .team2(reservation.getTeam2().getId())
//                .build();
//    }
}
