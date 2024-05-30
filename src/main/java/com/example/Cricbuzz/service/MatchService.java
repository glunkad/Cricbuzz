package com.example.Cricbuzz.service;

import com.example.Cricbuzz.dto.MatchDetailsDto;
import com.example.Cricbuzz.dto.MatchDto;

import java.util.List;

public interface MatchService {
    MatchDto addMatch(MatchDto matchDto);
    List<MatchDto> getAllMatches();

    MatchDetailsDto getMatchById(long id);
}
