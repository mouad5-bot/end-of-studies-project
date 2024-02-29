package com.youcode.come2play.dtos.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
