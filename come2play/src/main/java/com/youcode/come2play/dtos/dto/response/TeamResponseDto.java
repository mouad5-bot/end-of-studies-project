package com.youcode.come2play.dtos.dto.response;

import com.youcode.come2play.entities.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamResponseDto {
    private Team team;
    private MultipartFile image;
}
