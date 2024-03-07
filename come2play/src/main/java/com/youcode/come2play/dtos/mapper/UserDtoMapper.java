package com.youcode.come2play.dtos.mapper;

import com.youcode.come2play.dtos.dto.request.UserRequestDto;
import com.youcode.come2play.dtos.dto.response.UserResponseDto;
import com.youcode.come2play.entities.Role;
import com.youcode.come2play.entities.UserApp;

import java.util.ArrayList;
import java.util.List;

public class UserDtoMapper {

    private UserDtoMapper() {
    }

    public static UserApp toEntity(UserRequestDto userDto) {
        List<Role> roles = new ArrayList<>();
        if(userDto.getAuthorities() != null){
            for (String role : userDto.getAuthorities()) {
                roles.add(Role.builder().name(role).build());
            }
        }
        return UserApp.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .roleList(roles)
                .build();
    }

    public static UserResponseDto toDto(UserApp user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .verifiedAt(user.getVerifiedAt())
                .authorities(user.getRoleList().stream().map(Role::getName).toList())
                .build();
    }
}
