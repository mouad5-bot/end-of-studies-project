package com.youcode.come2play.dtos.dto.response;

import com.youcode.come2play.entities.Reservation;
import com.youcode.come2play.entities.enums.TeamType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StadiumResponseDto {

    private Long id;

    private String stadeName;

    private String city;

    private byte[] StadiumImage;

    private TeamType teamType;

    private double price;

    private List<Reservation> reservationList;

}
