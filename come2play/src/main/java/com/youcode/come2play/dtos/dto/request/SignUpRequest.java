package com.youcode.come2play.dtos.dto.request;

import com.youcode.come2play.entities.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    //@Min(value = 6,message = "Password should be at least 6 characters")
    private String password;

    @NotNull(message = "Date of birthday cannot be null")
    private LocalDate bornOn;

    @NotNull(message = "Gender cannot be null")
    private Gender gender;

    //private List<String> authorities;
}
