package com.youcode.come2play.dtos.mapper;

import com.youcode.come2play.dtos.dto.response.ReservationResponseDto;
import com.youcode.come2play.entities.Reservation;
import com.youcode.come2play.entities.Stadium;


public class ReservationResponseMapper {
    public static Reservation toEntity(ReservationResponseDto requestDto) {

        return Reservation.builder()
                .id(requestDto.getId())
                .dateOfMatch(requestDto.getDateOfMatch())
                .numberPhone(requestDto.getNumberPhone())
                .reservedAt(requestDto.getReservedAt())
                .status(requestDto.getStatus())
                .requestForTeam(requestDto.getRequestForTeam())
                .build();
    }
    public static ReservationResponseDto toDto(Reservation reservation) {
        String stadiumName = reservation.getStadiumId().getStadeName();

        return ReservationResponseDto.builder()
                .id(reservation.getId())
                .dateOfMatch(reservation.getDateOfMatch())
                .numberPhone(reservation.getNumberPhone())
                .reservedAt(reservation.getReservedAt())
                .status(reservation.getStatus())
                .requestForTeam(reservation.getRequestForTeam())
                .stadiumName(stadiumName)
                .build();
    }
}
