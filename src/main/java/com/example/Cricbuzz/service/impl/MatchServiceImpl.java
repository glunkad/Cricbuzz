package com.example.Cricbuzz.service.impl;

import com.example.Cricbuzz.dto.MatchDto;
import com.example.Cricbuzz.dto.PlayerDto;
import com.example.Cricbuzz.dto.TeamDto;
import com.example.Cricbuzz.dto.TeamPlayerDto;
import com.example.Cricbuzz.exception.MatchNotFoundException;
import com.example.Cricbuzz.model.Match;
import com.example.Cricbuzz.model.Player;
import com.example.Cricbuzz.model.Team;
import com.example.Cricbuzz.repository.MatchRepository;
import com.example.Cricbuzz.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {

    private MatchRepository matchRepository;


    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
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
    public MatchDto getMatchById(long id) {
        Match match = matchRepository.findById(id).orElseThrow(() -> new MatchNotFoundException("Match could not be found"));
        return mapToDto(match);
    }

    private MatchDto mapToDto(Match match) {
        MatchDto matchDto  = new MatchDto();
        matchDto.setId(match.getId());
        matchDto.setDate(match.getDate());
        matchDto.setVenue(match.getVenue());
        matchDto.setTeam1(match.getTeam1());
        matchDto.setTeam2(match.getTeam2());
        if(match.getSquad() == null){
            match.setSquad(null);
        } else{
            List<TeamDto> teamDtoList = match.getSquad().stream().map((team) -> mapTeamToDto(team)).collect(Collectors.toList());
            matchDto.setSquads(teamDtoList);
        }
        return matchDto;
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
        match.setTeam1(matchDto.getTeam1());
        match.setTeam2(matchDto.getTeam2());
        return match;
    }
}