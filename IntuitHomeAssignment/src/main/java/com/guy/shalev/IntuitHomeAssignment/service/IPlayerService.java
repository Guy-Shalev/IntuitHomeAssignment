package com.guy.shalev.IntuitHomeAssignment.service;

import com.guy.shalev.IntuitHomeAssignment.model.dto.PlayerDTO;
import com.guy.shalev.IntuitHomeAssignment.model.reponse.Players;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface IPlayerService {

    Players getAllPlayers(Pageable pageable);

    PlayerDTO getPlayerById(String playerID);
}
