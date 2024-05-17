package com.example.Cricbuzz.controller;

import com.example.Cricbuzz.dto.PlayerDto;
import com.example.Cricbuzz.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/")
    public ResponseEntity<List<PlayerDto>> getPlayers() {
        return new ResponseEntity<>(playerService.getAllPlayers(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<PlayerDto> addPlayer(
            @RequestBody PlayerDto playerDto
    ) {
        return new ResponseEntity<>(playerService.addPlayer(playerDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/stats")
    public ResponseEntity<PlayerDto> getPlayerById(
            @PathVariable long id
    ) {
        return new ResponseEntity<>(playerService.getPlayerById(id), HttpStatus.OK);
    }

}
