package com.example.Cricbuzz.dto;

import lombok.Data;

@Data
public class PlayerDto {
    private long id;

    private String name;

    private int matchesPlayed;

    private int runs;

    private float average;

    private float strikeRate;
}
