package com.youcode.come2play.security.auth;

import com.youcode.come2play.dtos.dto.request.SignInRequest;
import com.youcode.come2play.dtos.dto.request.SignUpRequest;
import com.youcode.come2play.entities.Role;
import com.youcode.come2play.entities.UserApp;
import com.youcode.come2play.repository.RoleRepository;
import com.youcode.come2play.repository.UserAppRepository;
import com.youcode.come2play.security.jwt.JwtService;
import com.youcode.come2play.security.jwt.TokenType;
import com.youcode.come2play.service.UserAppService;
import com.youcode.come2play.web.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserAppService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserAppRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    public void signup(SignUpRequest request) throws ValidationException {
        Role role = roleRepository.findByName("ROLE_MEMBER")
                .orElseGet(() -> roleRepository.save(Role.builder().name("ROLE_MEMBER").build()));
        UserApp user = UserApp.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .roleList(List.of(role))
                .accountNonLocked(false)
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
        userService.save(user);
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var accessToken = jwtService.generateToken(user, TokenType.ACCESS_TOKEN);
        var refreshToken = jwtService.generateToken(user, TokenType.REFRESH_TOKEN);
        return  JwtAuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();    }

    @Override
    public JwtAuthenticationResponse refreshToken(String refreshToken) throws ValidationException {
        if(jwtService.isTokenValid(refreshToken, TokenType.REFRESH_TOKEN)) {
            String username = jwtService.extractUserName(refreshToken);
            var user = userRepository.findByEmail(username).orElseThrow(() -> new ValidationException("User not found"));
            var accessToken = jwtService.generateToken(user, TokenType.ACCESS_TOKEN);
            return JwtAuthenticationResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        }
        throw new UnauthorizedException("Refresh token is invalid");    }

    @Override
    public UserApp me() {
        return userService.getCurrentUser();
    }
}
