package com.example.Cricbuzz.service;

import com.example.Cricbuzz.dto.TeamDto;
import com.example.Cricbuzz.dto.TeamPlayerDto;

import java.util.List;

public interface TeamService {
    String addPlayerToTeam(long teamId, TeamPlayerDto teamPlayerDto);

    List<TeamPlayerDto> getPlayers(long teamId);

    TeamDto addTeam(TeamDto teamDto);
}
