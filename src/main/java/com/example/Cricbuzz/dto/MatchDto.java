package com.example.Cricbuzz.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MatchDto {

    private long id;

    private String venue;

    private LocalDate date;

    private String team1;

    private String team2;

}
