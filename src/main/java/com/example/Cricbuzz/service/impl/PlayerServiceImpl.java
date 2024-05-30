package com.example.Cricbuzz.service.impl;

import com.example.Cricbuzz.dto.PlayerDto;
import com.example.Cricbuzz.dto.PlayerDto;
import com.example.Cricbuzz.exception.PlayerNotFoundException;
import com.example.Cricbuzz.model.Player;
import com.example.Cricbuzz.repository.PlayerRepository;
import com.example.Cricbuzz.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    private  PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<PlayerDto> getAllPlayers() {
        List<Player> playerList = playerRepository.findAll();
        return playerList.stream().map(player -> mapToDto(player)).collect(Collectors.toList());
    }

    @Override
    public PlayerDto addPlayer(PlayerDto playerDto) {
        Player player = mapToEntity(playerDto);
        playerRepository.save(player);
        PlayerDto newPlayer = mapToDto(player);
        return playerDto;
    }

    @Override
    public PlayerDto getPlayerById(long id) {
        Player player = playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException("Player not found !"));
        return mapToDto(player);
    }

    private PlayerDto mapToDto(Player Player) {
        PlayerDto PlayerDto  = new PlayerDto();
        PlayerDto.setId(Player.getId());
        PlayerDto.setName(Player.getName());
        PlayerDto.setMatchesPlayed(Player.getMatchesPlayed());
        PlayerDto.setRuns(Player.getRuns());
        PlayerDto.setAverage(Player.getAverage());
        PlayerDto.setStrikeRate(Player.getStrikeRate());
        return PlayerDto;
    }

    private Player mapToEntity(PlayerDto PlayerDto) {
        Player Player  = new Player();
        Player.setId(PlayerDto.getId());
        Player.setName(PlayerDto.getName());
        Player.setMatchesPlayed(PlayerDto.getMatchesPlayed());
        Player.setRuns(PlayerDto.getRuns());
        Player.setAverage(PlayerDto.getAverage());
        Player.setStrikeRate(PlayerDto.getStrikeRate());
        return Player;
    }

}
