//package com.youcode.come2play.config;
//
//import com.youcode.come2play.entities.Role;
//import com.youcode.come2play.entities.UserApp;
//import com.youcode.come2play.repository.RoleRepository;
//import com.youcode.come2play.repository.UserAppRepository;
//import com.youcode.come2play.service.UserAppService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import com.youcode.come2play.security.AuthoritiesConstants;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class OnStartUp implements CommandLineRunner {
//
//    private final UserAppRepository userRepository;
//    private final UserAppService userService;
//    private final PasswordEncoder passwordEncoder;
//    private final RoleRepository roleRepository;
//    private static final String PASSWORD = "password";
//    @Override
//    public void run(String... args) {
//        List<Role> roles = createRoles();
//        createUsers(roles);
//        if (userRepository.count() > 0) {
//            try {
//                userService.save(UserApp.builder()
//                        .email("admin@gmail.com")
//                        .password(passwordEncoder.encode("password"))
//                        .firstName("mouad")
//                        .lastName("fifel")
//                        .verifiedAt(LocalDateTime.now())
//                        .enabled(true)
//                        .credentialsNonExpired(true)
//                        .accountNonLocked(true)
//                        .accountNonExpired(true)
//                        .build());
//            }catch (Exception e) {
//                log.error(e.getMessage());
//            }
//        }
//    }
//
//
//    private void createUsers(List<Role> roles) {
//        if(userRepository.count() > 0)
//            return;
//
//        UserApp admin = UserApp.builder()
//                .email("admin@gmail.com")
//                .password(passwordEncoder.encode(PASSWORD))
//                .firstName("Admin")
//                .lastName("User")
//                .roleList(roles)
//                .verifiedAt(LocalDateTime.now())
//                .build();
//        userRepository.save(admin);
//
//        roles.forEach(role -> {
//
//            if (role.getName().equals(AuthoritiesConstants.ROLE_STADIUM_MANAGER))
//                userRepository.save(UserApp.builder()
//                        .email("manager@gmail.com")
//                        .password(passwordEncoder.encode(PASSWORD))
//                        .firstName("Manager")
//                        .lastName("User")
//                        .verifiedAt(LocalDateTime.now())
//                        .roleList(List.of(role))
//                        .build());
//            if (role.getName().equals(AuthoritiesConstants.ROLE_PLAYER)) {
//                userRepository.save(UserApp.builder()
//                        .email("player@gmail.com")
//                        .password(passwordEncoder.encode(PASSWORD))
//                        .firstName("Player")
//                        .lastName("User")
//                        .verifiedAt(LocalDateTime.now())
//                        .roleList(List.of(role))
//                        .build());
//            }
//        });
//    }
//
//    private List<Role> createRoles() {
//        if(roleRepository.count() > 0)
//            return roleRepository.findAll();
//
//        return roleRepository.saveAll(List.of(
//                Role.builder().name(AuthoritiesConstants.ROLE_STADIUM_MANAGER).build(),
//                Role.builder().name(AuthoritiesConstants.ROLE_ADMIN).build(),
//                Role.builder().name(AuthoritiesConstants.ROLE_PLAYER).build()
//        ));
//    }
//
//}
//
