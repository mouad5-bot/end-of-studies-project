package com.youcode.come2play.dtos.dto.response;

import com.youcode.come2play.entities.Reservation;
import com.youcode.come2play.entities.enums.TeamType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StadiumResponseDto {
    private Long id;

    @NotBlank(message = "the stadium name must be not blank")
    private String stadeName;

    @NotBlank(message = "the stadium name must be not blank")
    private String city;

    private byte[] StadiumImage;

    @NotNull(message = "The team type must not be null")
    private TeamType teamType;

    @NotNull(message = "price must be not null")
    private double price;
}
