package com.youcode.come2play.security.auth;

import com.youcode.come2play.dtos.dto.request.SignInRequest;
import com.youcode.come2play.dtos.dto.request.SignUpRequest;
import com.youcode.come2play.entities.UserApp;
import com.youcode.come2play.security.auth.JwtAuthenticationResponse;

import javax.validation.ValidationException;

public interface AuthenticationService {
    void signup(SignUpRequest request) throws ValidationException;

    JwtAuthenticationResponse signin(SignInRequest request);

    JwtAuthenticationResponse refreshToken(String refreshToken) throws ValidationException;

    UserApp me();
}
