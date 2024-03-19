package com.youcode.come2play.dtos.mapper;

import com.youcode.come2play.dtos.dto.request.TeamRequestDto;
import com.youcode.come2play.dtos.dto.response.StadiumResponseDto;
import com.youcode.come2play.entities.Stadium;
import com.youcode.come2play.entities.Team;
import com.youcode.come2play.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class TeamMapper {
    private final FileUtils fileUtils;

    public TeamRequestDto toDto(Team team) {

        byte[] teamImage;
        try {
            teamImage = fileUtils.fileToByteArray(team.getTeamImage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return TeamRequestDto.builder()
                .teamName(team.getTeamName())
                .teamType(team.getTeamType())
                .createdBy(team.getCreatedBy())
                .teamImage(teamImage)
                .build();
    }
}
