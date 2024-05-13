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

    private String name;

    @Column(name = "matches_played")
    private int matchesPlayed;

    private int runs;

    private float average;

    @Column(name="strike_rate")
    private float strikeRate;

}
