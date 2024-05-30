package com.example.Cricbuzz.repository;


import com.example.Cricbuzz.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findTeamByName(String name);
}
