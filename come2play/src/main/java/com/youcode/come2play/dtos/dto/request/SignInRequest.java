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
        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Email should be valid")
        private String email;

        @NotBlank(message = "Password cannot be blank")
        private String password;
    }
