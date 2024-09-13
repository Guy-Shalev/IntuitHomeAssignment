package com.guy.shalev.IntuitHomeAssignment.service.Impl;

import com.guy.shalev.IntuitHomeAssignment.dal.PlayerRepository;
import com.guy.shalev.IntuitHomeAssignment.model.dto.PlayerDTO;
import com.guy.shalev.IntuitHomeAssignment.model.entity.Player;
import com.guy.shalev.IntuitHomeAssignment.model.reponse.Players;
import com.guy.shalev.IntuitHomeAssignment.service.IPlayerService;
import com.guy.shalev.IntuitHomeAssignment.util.mapper.PlayerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService implements IPlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Players getAllPlayers() {
        List<Player> playersFromDB = playerRepository.findAll();

        List<PlayerDTO> playerDTOs = playersFromDB.stream()
                .map(PlayerMapper::convertToDTO)
                .collect(Collectors.toList());

        return new Players(playerDTOs);
    }

    @Override
    public PlayerDTO getPlayerById(String playerId) {
        Optional<Player> playerOptional = playerRepository.findById(playerId);
        return playerOptional.map(PlayerMapper::convertToDTO).orElse(null);
    }
}
