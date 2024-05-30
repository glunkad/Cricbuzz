package com.example.Cricbuzz.dto;


import com.example.Cricbuzz.model.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


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

//    private List<Team> squads;
//    private List<TeamDto> squads;
}
