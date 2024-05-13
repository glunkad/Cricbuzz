package com.example.Cricbuzz.repository;

import com.example.Cricbuzz.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
