package com.youcode.come2play.entities;

import jakarta.persistence.*;
import lombok.*;
import org.w3c.dom.stylesheets.LinkStyle;

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

    @OneToMany(mappedBy = "stadiumId")
    private List<Reservation> reservationList;
}
