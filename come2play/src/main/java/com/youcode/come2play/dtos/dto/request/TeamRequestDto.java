package com.youcode.come2play.dtos.dto.request;

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
public class TeamRequestDto {
    private Long id;

    @NotBlank(message = "The team name must not be blank")
    private String teamName;

    @NotNull(message = "The team type must not be null")
    private TeamType teamType;

    private byte[] teamImage;

    private Long createdBy;

}