package com.example.Cricbuzz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerDto {
    private long id;

    private String name;

    private int matchesPlayed;

    private int runs;

    private float average;

    private float strikeRate;
}
