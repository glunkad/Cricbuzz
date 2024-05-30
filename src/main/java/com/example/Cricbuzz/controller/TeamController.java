package com.example.Cricbuzz.controller;

import com.example.Cricbuzz.dto.PlayerDto;
import com.example.Cricbuzz.dto.TeamPlayerDto;
import com.example.Cricbuzz.model.Player;
import com.example.Cricbuzz.repository.TeamRepository;
import com.example.Cricbuzz.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private TeamService teamService;


    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/{team_id}/squad")
    public ResponseEntity<String> addPlayerToTeam(
            @PathVariable long team_id,
            @RequestBody TeamPlayerDto teamPlayerDto
    ) {
        return new ResponseEntity<>(teamService.addPlayerToTeam(team_id,teamPlayerDto), HttpStatus.OK);
    }

    @GetMapping("/{team_id}/squad")
    public  ResponseEntity<List<TeamPlayerDto>> getAllPlayers(
            @PathVariable long team_id
    ) {
        return new ResponseEntity<>(teamService.getPlayers(team_id), HttpStatus.OK);
    }

}
