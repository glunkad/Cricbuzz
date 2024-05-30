package com.example.Cricbuzz.controller;


import com.example.Cricbuzz.dto.MatchDetailsDto;
import com.example.Cricbuzz.dto.MatchDto;
import com.example.Cricbuzz.model.Match;
import com.example.Cricbuzz.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {


    private MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/")
    public ResponseEntity<List<MatchDto>> getMatches() {
        return new ResponseEntity<>(matchService.getAllMatches(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchDetailsDto> matchDetail(
            @PathVariable long id
    ) {
        return  new ResponseEntity<>(matchService.getMatchById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<MatchDto> addMatch(
            @RequestBody MatchDto matchDto
    ) {
        return new ResponseEntity<>(matchService.addMatch(matchDto), HttpStatus.CREATED);
    }

}
