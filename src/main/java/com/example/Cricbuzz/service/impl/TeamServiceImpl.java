package com.example.Cricbuzz.service.impl;

import com.example.Cricbuzz.dto.TeamDto;
import com.example.Cricbuzz.dto.TeamPlayerDto;
import com.example.Cricbuzz.exception.PlayerNotFoundException;
import com.example.Cricbuzz.exception.TeamNotFoundException;
import com.example.Cricbuzz.model.Player;
import com.example.Cricbuzz.model.Team;
import com.example.Cricbuzz.repository.PlayerRepository;
import com.example.Cricbuzz.repository.TeamRepository;
import com.example.Cricbuzz.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public String addPlayerToTeam(long teamId, TeamPlayerDto teamPlayerDto) {
        Player player = playerRepository.findByName(teamPlayerDto.getName());
        if (player == null) {
            throw new PlayerNotFoundException("Player not found");
        }
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new TeamNotFoundException("Team not found exception"));
        team.getPlayerList().add(player);
        player.setTeam(team);
        player.setRole(teamPlayerDto.getRole());
        playerRepository.save(player);
        teamRepository.save(team);
        return "Player added to team successfully";
    }

    @Override
    public List<TeamPlayerDto> getPlayers(long teamId) {
        List<Player> playerList = playerRepository.findByTeamId(teamId);
        if (playerList.isEmpty()) {
            throw new TeamNotFoundException("No players found for team ID: " + teamId);
        }
        return playerList.stream()
                .map(player -> new TeamPlayerDto(player.getName(), player.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public TeamDto addTeam(TeamDto teamDto) {
        Team team = mapToEntity(teamDto);
        teamRepository.save(team);
        return mapToDto(team);
    }

    public TeamDto mapToDto(Team team){
        TeamDto teamDto = new TeamDto();
        teamDto.setName(team.getName());
//        teamDto.setPlayerList(team.getPlayerList());
        return teamDto;
    }

    public Team mapToEntity(TeamDto teamDto){
        Team team = new Team();
        team.setName(teamDto.getName());
//        team.setPlayerList(teamDto.getPlayerList());
        return team;
    }
}
