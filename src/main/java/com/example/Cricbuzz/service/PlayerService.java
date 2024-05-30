package com.example.Cricbuzz.service;

import com.example.Cricbuzz.dto.PlayerDto;

import java.util.List;

public interface PlayerService {
    List<PlayerDto> getAllPlayers();

    PlayerDto addPlayer(PlayerDto playerDto);

    PlayerDto getPlayerById(long id);
}
