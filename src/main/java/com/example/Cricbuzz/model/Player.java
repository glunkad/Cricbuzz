package com.example.Cricbuzz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "matches_played")
    private int matchesPlayed;

    @Column(name = "runs")
    private int runs;

    @Column(name = "average")
    private float average;

    @Column(name="strike_rate")
    private float strikeRate;

    @Column(name = "role")
    public String role;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
