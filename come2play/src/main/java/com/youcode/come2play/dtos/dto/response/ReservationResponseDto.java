package com.youcode.come2play.dtos.dto.response;

import com.youcode.come2play.entities.Stadium;
import com.youcode.come2play.entities.enums.RequestForTeam;
import com.youcode.come2play.entities.enums.Status;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Data
@Builder
public class ReservationResponseDto {
    private Long id;

    private LocalDateTime reservedAt;

    private LocalDateTime dateOfMatch;

    private String numberPhone;

    private Status status;

    private RequestForTeam requestForTeam;

    private String team1;

    private String team2;

    private String stadiumName;
}
