package com.youcode.come2play.entities;

import com.youcode.come2play.entities.enums.RequestForTeam;
import com.youcode.come2play.entities.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "reserved_at")
    private LocalDateTime reservedAt;

    @Column(name = "date_of_match")
    private LocalDateTime dateOfMatch;

    @Column(name = "number_phone")
    private String numberPhone;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private RequestForTeam requestForTeam;

    @ManyToOne
    private Team team1;

    @ManyToOne
    private Team team2;

    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadiumId;
}
