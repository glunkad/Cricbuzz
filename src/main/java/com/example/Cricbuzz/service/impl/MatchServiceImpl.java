package com.example.Cricbuzz.service.impl;

import com.example.Cricbuzz.dto.MatchDto;
import com.example.Cricbuzz.exception.MatchNotFoundException;
import com.example.Cricbuzz.model.Match;
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
        Match match = new Match();
        match.setDate(matchDto.getDate());
        match.setVenue(matchDto.getVenue());
        match.setTeam1(matchDto.getTeam1());
        match.setTeam2(matchDto.getTeam2());

        Match newMatch = matchRepository.save(match);

        MatchDto matchResponse = new MatchDto();
        matchResponse.setId(newMatch.getId());
        matchResponse.setDate(newMatch.getDate());
        matchResponse.setVenue(newMatch.getVenue());
        matchResponse.setTeam1(newMatch.getTeam1());
        matchResponse.setTeam2(newMatch.getTeam2());
        return matchResponse;

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
        return matchDto;
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
