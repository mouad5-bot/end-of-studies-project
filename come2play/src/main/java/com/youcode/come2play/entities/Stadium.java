package com.youcode.come2play.entities;

import lombok.*;
import com.youcode.come2play.entities.enums.TeamType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stadium")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stadium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "stade_name")
    private String stadeName;

    @Column(name = "city")
    private String city;

    @Column(name = "image")
    private String image;

    @Enumerated(EnumType.STRING)
    private TeamType teamType;

    @Column(name = "price")
    private double price;

    @OneToMany(mappedBy = "stadiumId")
    private List<Reservation> reservationList;
}
