package com.example.Cricbuzz.repository;

import com.example.Cricbuzz.dto.TeamDto;
import com.example.Cricbuzz.model.Player;
import com.example.Cricbuzz.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player findByName(String name);

    List<Team> findByTeamId(long teamId);
}
