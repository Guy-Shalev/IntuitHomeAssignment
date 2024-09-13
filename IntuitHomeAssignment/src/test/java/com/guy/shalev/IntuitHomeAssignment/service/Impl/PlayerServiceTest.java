package com.guy.shalev.IntuitHomeAssignment.service.Impl;

import com.guy.shalev.IntuitHomeAssignment.dal.PlayerRepository;
import com.guy.shalev.IntuitHomeAssignment.exception.PlayerNotFoundException;
import com.guy.shalev.IntuitHomeAssignment.model.dto.PlayerDTO;
import com.guy.shalev.IntuitHomeAssignment.model.entity.Player;
import com.guy.shalev.IntuitHomeAssignment.model.reponse.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    private PlayerService playerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        playerService = new PlayerService(playerRepository);
    }

    @Test
    void getAllPlayers_shouldReturnAllPlayers() {
        Player player1 = new Player("player1", "1990", "01", "01", "USA", "CA", "Los Angeles", null, null, null, null, null, null, "John", "Doe", "John Doe", "200", "72", "R", "R", "2010-04-01", "2020-09-30", "retr1", "bbre1");
        Player player2 = new Player("player2", "1995", "05", "15", "USA", "NY", "New York", null, null, null, null, null, null, "Jane", "Smith", "Jane Smith", "180", "68", "L", "L", "2015-04-01", null, "retr2", "bbre2");

        Page<Player> page = new PageImpl<>(List.of(player1, player2));
        Pageable pageable = PageRequest.of(0, 2);

        when(playerRepository.findAll(pageable)).thenReturn(page);

        Players result = playerService.getAllPlayers(pageable);

        assertNotNull(result);
        assertEquals(2, result.getPlayers().size());
        assertEquals("John", result.getPlayers().get(0).getNameFirst());
        assertEquals("Jane", result.getPlayers().get(1).getNameFirst());
        assertEquals(1, result.getTotalPages());
        assertEquals(2, result.getTotalElements());
        assertEquals(0, result.getCurrentPage());
        assertEquals(2, result.getPageSize());
    }

    @Test
    void getPlayerById_existingPlayer_shouldReturnPlayer() {
        String playerId = "player1";
        Player player = new Player(playerId, "1990", "01", "01", "USA", "CA", "Los Angeles", null, null, null, null, null, null, "John", "Doe", "John Doe", "200", "72", "R", "R", "2010-04-01", "2020-09-30", "retr1", "bbre1");
        when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));

        PlayerDTO result = playerService.getPlayerById(playerId);

        assertNotNull(result);
        assertEquals("John", result.getNameFirst());
        assertEquals("Doe", result.getNameLast());
    }

    @Test
    void getPlayerById_nonExistingPlayer_shouldThrowPlayerNotFoundException() {
        String playerId = "nonexistent";
        when(playerRepository.findById(playerId)).thenReturn(Optional.empty());

        assertThrows(PlayerNotFoundException.class, () -> playerService.getPlayerById(playerId));
    }
}