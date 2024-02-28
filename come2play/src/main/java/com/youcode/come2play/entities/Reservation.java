package com.youcode.come2play.entities;

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

    @ManyToOne
    private Team team1;

    @ManyToOne
    private Team team2;

    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadiumId;
}
