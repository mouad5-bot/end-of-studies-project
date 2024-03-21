package com.youcode.come2play.dtos.dto.response;

import com.youcode.come2play.entities.Team;
import com.youcode.come2play.entities.enums.TeamType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyTeamResponseDto {
    private Long id;
    private String teamName;
    private TeamType teamType;
    private String image;
    private Long createdBy;

    public static MyTeamResponseDto toDto(Team team){
        return MyTeamResponseDto.builder()
                .id(team.getId())
                .image(team.getTeamImage())
                .createdBy(team.getCreatedBy())
                .teamName(team.getTeamName())
                .teamType(team.getTeamType())
                .build();
    }
}
