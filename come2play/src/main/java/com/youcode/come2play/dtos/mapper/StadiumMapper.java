package com.youcode.come2play.dtos.mapper;

import com.youcode.come2play.dtos.dto.response.StadiumResponseDto;
import com.youcode.come2play.entities.Stadium;
import com.youcode.come2play.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class StadiumMapper {
    private final FileUtils fileUtils;

    public StadiumResponseDto toDto(Stadium stadium) {

        byte[] stadiumImage;
        try {
            stadiumImage = fileUtils.fileToByteArray(stadium.getStadiumImage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return StadiumResponseDto.builder()
                .id(stadium.getId())
                .city(stadium.getCity())
                .price(stadium.getPrice())
                .stadeName(stadium.getStadeName())
                .teamType(stadium.getTeamType())
                .reservationList(stadium.getReservationList())
                .StadiumImage(stadiumImage)
                .build();
    }
}
