package com.youcode.come2play.dtos.dto.request;

import com.youcode.come2play.entities.Stadium;
import com.youcode.come2play.entities.Team;
import com.youcode.come2play.entities.enums.RequestForTeam;
import com.youcode.come2play.entities.enums.Status;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequestDto {

    private Long id;

    private LocalDateTime reservedAt;

    @Future(message = "Date of match must be in the future")
    private LocalDateTime dateOfMatch;

    @NotNull(message = "Number Phone Must Be Not Null !")
    private String numberPhone;

    private Status status;

    private RequestForTeam requestForTeam;

    private Long team1;

    private Long team2;

    private Stadium stadiumId;


}
