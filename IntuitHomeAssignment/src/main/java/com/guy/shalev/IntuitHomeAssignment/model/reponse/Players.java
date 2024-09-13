package com.guy.shalev.IntuitHomeAssignment.model.reponse;

import com.guy.shalev.IntuitHomeAssignment.model.dto.PlayerDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Players {

    List<PlayerDTO> players;

}
