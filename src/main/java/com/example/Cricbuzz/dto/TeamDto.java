package com.example.Cricbuzz.dto;

import com.example.Cricbuzz.model.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamDto {

    private String name;

//    private List<PlayerDto> playerList;
    private List<TeamPlayerDto> playerList;
}
