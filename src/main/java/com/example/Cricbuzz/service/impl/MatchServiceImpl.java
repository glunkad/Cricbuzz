package com.example.Cricbuzz.service.impl;

import com.example.Cricbuzz.dto.*;
import com.example.Cricbuzz.exception.MatchNotFoundException;
import com.example.Cricbuzz.model.Match;
import com.example.Cricbuzz.model.Player;
import com.example.Cricbuzz.model.Team;
import com.example.Cricbuzz.repository.MatchRepository;
import com.example.Cricbuzz.repository.TeamRepository;
import com.example.Cricbuzz.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {

    private MatchRepository matchRepository;

    private TeamRepository teamRepository;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository, TeamRepository teamRepository) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public MatchDto addMatch(MatchDto matchDto) {
        Match match = mapToEntity(matchDto);
        Match newMatch = matchRepository.save(match);
        return mapToDto(newMatch);

    }

    @Override
    public List<MatchDto> getAllMatches() {
        List<Match> match = matchRepository.findAll();
        return match.stream().map(m -> mapToDto(m)).collect(Collectors.toList());
    }

    @Override
    public MatchDetailsDto getMatchById(long id) {
        Match match = matchRepository.findById(id).orElseThrow(() -> new MatchNotFoundException("Match could not be found"));
        return mapToMatchDetailsDto(match);
    }

    private MatchDto mapToDto(Match match) {
        MatchDto matchDto  = new MatchDto();
        matchDto.setId(match.getId());
        matchDto.setDate(match.getDate());
        matchDto.setVenue(match.getVenue());
        matchDto.setTeam1(match.getTeam1().getName());
        matchDto.setTeam2(match.getTeam2().getName());
        return matchDto;
    }

    private MatchDetailsDto mapToMatchDetailsDto(Match match){
        MatchDetailsDto matchDetailsDto = new MatchDetailsDto();
        matchDetailsDto.setId(match.getId());
        matchDetailsDto.setVenue(match.getVenue());
        matchDetailsDto.setTeam1(match.getTeam1().getName());
        matchDetailsDto.setTeam2(match.getTeam2().getName());
        matchDetailsDto.setDate(match.getDate());

        List<TeamDto> squads = new ArrayList<>();
        TeamDto team1Dto = mapTeamToDto(match.getTeam1());
        squads.add(team1Dto);
        TeamDto team2Dto = mapTeamToDto(match.getTeam2());
        squads.add(team2Dto);

        matchDetailsDto.setSquads(squads);
        return  matchDetailsDto;
    }

    private TeamDto mapTeamToDto(Team team) {
        TeamDto teamDto = new TeamDto();
        teamDto.setName(team.getName());
        List<TeamPlayerDto> teamPlayerDtoList = team.getPlayerList().stream().map((player) -> mapTeamPlayerToDto(player)).collect(Collectors.toList());
        teamDto.setPlayerList(teamPlayerDtoList);
        return teamDto;
    }

    private TeamPlayerDto mapTeamPlayerToDto(Player player) {
        TeamPlayerDto teamPlayerDto = new TeamPlayerDto();
        teamPlayerDto.setName(player.getName());
        teamPlayerDto.setRole(player.getRole());
        return teamPlayerDto;
    }


    private Match mapToEntity(MatchDto matchDto) {
        Match match  = new Match();
        match.setId(matchDto.getId());
        match.setDate(matchDto.getDate());
        match.setVenue(matchDto.getVenue());
        match.setTeam1(teamRepository.findTeamByName(matchDto.getTeam1()));
        match.setTeam2(teamRepository.findTeamByName(matchDto.getTeam2()));
        return match;
    }
}