package com.example.Cricbuzz.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchDto {

    private long id;

    private String venue;

    private LocalDate date;

    private String team1;

    private String team2;


}
