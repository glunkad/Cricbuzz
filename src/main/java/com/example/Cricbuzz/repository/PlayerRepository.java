package com.example.Cricbuzz.repository;

import com.example.Cricbuzz.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlayerRepository extends JpaRepository<Player, Long> {

}
