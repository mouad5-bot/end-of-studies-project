package com.youcode.come2play.dtos.dto.request;

import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Data
public class ReservationDto {

    @Future(message = "Date of match must be in the future")
    private LocalDateTime dateOfMatch;

    @NotNull(message = "Number Phone Must Be Not Null !")
    private String numberPhone;

    private Long team1;
    private Long team2;
}
