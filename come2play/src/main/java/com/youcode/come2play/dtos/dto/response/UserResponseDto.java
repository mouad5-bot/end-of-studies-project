package com.youcode.come2play.dtos.dto.response;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link com.youcode.come2play.entities.UserApp}
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto implements Serializable {
   private Long id;
   private String firstName;
   private String lastName;
   private String email;
   private LocalDate bornOn;
   private String gender;
   private LocalDateTime createdAt;
   private LocalDateTime verifiedAt;
   private List<String> authorities;

}