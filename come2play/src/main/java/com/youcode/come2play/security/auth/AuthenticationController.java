package com.youcode.come2play.security.auth;

import com.youcode.come2play.dtos.dto.request.SignInRequest;
import com.youcode.come2play.dtos.dto.request.SignUpRequest;
import com.youcode.come2play.dtos.dto.response.UserResponseDto;
import com.youcode.come2play.dtos.mapper.UserDtoMapper;
import com.youcode.come2play.entities.UserApp;
import com.youcode.come2play.utils.ResponseApi;
import com.youcode.come2play.web.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody @Valid SignInRequest credential) {
        JwtAuthenticationResponse result = authenticationService.signin(credential);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseApi<String>> signup(@RequestBody @Valid SignUpRequest register) throws ValidationException {
        authenticationService.signup(register);
        return ResponseEntity.ok(ResponseApi.<String>builder().message("Thank you for your registration !").build());
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> me() {
        UserApp result = authenticationService.me();
        return ResponseEntity.ok(UserDtoMapper.toDto(result));
    }

    @GetMapping("/token/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refreshToken(HttpServletRequest request) throws ValidationException {
        String authorization = request.getHeader("Authorization");
        if(authorization == null || !authorization.startsWith("Bearer ")) {
            throw new UnauthorizedException("Refresh token is missing");
        }
        String token = authorization.substring(7);
        JwtAuthenticationResponse result = authenticationService.refreshToken(token);
        return ResponseEntity.ok(result);
    }
}
