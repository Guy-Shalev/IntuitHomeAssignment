package com.guy.shalev.IntuitHomeAssignment.controller;

import com.guy.shalev.IntuitHomeAssignment.exception.GlobalExceptionHandler;
import com.guy.shalev.IntuitHomeAssignment.exception.PlayerNotFoundException;
import com.guy.shalev.IntuitHomeAssignment.model.dto.PlayerDTO;
import com.guy.shalev.IntuitHomeAssignment.model.reponse.Players;
import com.guy.shalev.IntuitHomeAssignment.service.IPlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PlayerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IPlayerService playerService;

    private PlayerController playerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        playerController = new PlayerController(playerService);
        mockMvc = MockMvcBuilders.standaloneSetup(playerController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void getAllPlayers_shouldReturnAllPlayers() {
        Pageable pageable = PageRequest.of(0, 20);
        Players players = getPlayers();

        when(playerService.getAllPlayers(pageable)).thenReturn(players);

        ResponseEntity<Players> response = playerController.getAllPlayers(pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().getPlayers().size());
        assertEquals(1, response.getBody().getTotalPages());
        assertEquals(2, response.getBody().getTotalElements());
        assertEquals(0, response.getBody().getCurrentPage());
        assertEquals(20, response.getBody().getPageSize());
    }

    private static Players getPlayers() {
        Players players = new Players(Arrays.asList(
                new PlayerDTO("player1", "1990", "01", "01", "USA", "CA", "Los Angeles", null, null, null, null, null, null, "John", "Doe", "John Doe", "200", "72", "R", "R", null, null, "retr1", "bbre1"),
                new PlayerDTO("player2", "1995", "05", "15", "USA", "NY", "New York", null, null, null, null, null, null, "Jane", "Smith", "Jane Smith", "180", "68", "L", "L", null, null, "retr2", "bbre2")
        ));
        players.setTotalPages(1);
        players.setTotalElements(2);
        players.setCurrentPage(0);
        players.setPageSize(20);
        return players;
    }

    @Test
    void getPlayerById_existingPlayer_shouldReturnPlayer() {
        String playerId = "player1";
        PlayerDTO playerDTO = new PlayerDTO(playerId, "1990", "01", "01", "USA", "CA", "Los Angeles", null, null, null, null, null, null, "John", "Doe", "John Doe", "200", "72", "R", "R", null, null, "retr1", "bbre1");
        when(playerService.getPlayerById(playerId)).thenReturn(playerDTO);

        ResponseEntity<PlayerDTO> response = playerController.getPlayerById(playerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("John", response.getBody().getNameFirst());
        assertEquals("Doe", response.getBody().getNameLast());
    }

    @Test
    void getPlayerById_nonExistingPlayer_shouldReturnNotFound() throws Exception {
        String nonExistentPlayerId = "nonexistent";
        when(playerService.getPlayerById(nonExistentPlayerId))
                .thenThrow(new PlayerNotFoundException("Player not found with ID: " + nonExistentPlayerId));

        mockMvc.perform(get("/api/players/{id}", nonExistentPlayerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Player not found with ID: " + nonExistentPlayerId));
    }
}