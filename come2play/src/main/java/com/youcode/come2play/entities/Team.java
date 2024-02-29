package com.youcode.come2play.entities;

import com.youcode.come2play.entities.enums.TeamType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "team")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "team_name")
    private String teamtName;

    @Enumerated(EnumType.STRING)
    private TeamType teamType;


    @Column(name = "image")
    private String  image;

    @ManyToMany
    private List<UserApp> userList;

    @OneToMany(mappedBy = "team1")
    private List<Reservation> reservationsTeam1;

    @OneToMany(mappedBy = "team2")
    private List<Reservation> reservationsTeam2;
}