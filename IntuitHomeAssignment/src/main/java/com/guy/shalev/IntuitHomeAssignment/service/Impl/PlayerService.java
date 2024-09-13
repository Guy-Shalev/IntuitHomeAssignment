package com.guy.shalev.IntuitHomeAssignment.service.Impl;

import com.guy.shalev.IntuitHomeAssignment.dal.PlayerRepository;
import com.guy.shalev.IntuitHomeAssignment.exception.PlayerNotFoundException;
import com.guy.shalev.IntuitHomeAssignment.model.dto.PlayerDTO;
import com.guy.shalev.IntuitHomeAssignment.model.entity.Player;
import com.guy.shalev.IntuitHomeAssignment.model.reponse.Players;
import com.guy.shalev.IntuitHomeAssignment.service.IPlayerService;
import com.guy.shalev.IntuitHomeAssignment.util.mapper.PlayerMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService implements IPlayerService {
    private static final Logger logger = LogManager.getLogger(PlayerService.class);

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Players getAllPlayers(Pageable pageable) {
        Page<Player> playersPage = playerRepository.findAll(pageable);
        List<PlayerDTO> playerDTOs = playersPage.getContent().stream()
                .map(PlayerMapper::convertToDTO)
                .collect(Collectors.toList());

        Players players = new Players(playerDTOs);
        players.setTotalPages(playersPage.getTotalPages());
        players.setTotalElements(playersPage.getTotalElements());
        players.setCurrentPage(playersPage.getNumber());
        players.setPageSize(playersPage.getSize());

        return players;
    }

    @Override
    public PlayerDTO getPlayerById(String playerId) {
        return playerRepository.findById(playerId)
                .map(PlayerMapper::convertToDTO)
                .orElseThrow(() -> {
                    logger.warn("Player not found with ID: {}", playerId);
                    return new PlayerNotFoundException("Player not found with ID: " + playerId);
                });
    }
}
