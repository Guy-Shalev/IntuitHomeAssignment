package com.guy.shalev.IntuitHomeAssignment.service.Impl;

import com.guy.shalev.IntuitHomeAssignment.dal.PlayerRepository;
import com.guy.shalev.IntuitHomeAssignment.exception.PlayerNotFoundException;
import com.guy.shalev.IntuitHomeAssignment.model.dto.PlayerDTO;
import com.guy.shalev.IntuitHomeAssignment.model.entity.Player;
import com.guy.shalev.IntuitHomeAssignment.model.reponse.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class PlayerServiceIntegrationTest {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerRepository playerRepository;

    @BeforeEach
    void setUp() {
        playerRepository.deleteAll();
    }

    @Test
    void testGetAllPlayers_EmptyDatabase() {
        Players result = playerService.getAllPlayers(getPageable());
        assertTrue(result.getPlayers().isEmpty());
    }

    private static Pageable getPageable(int pageSize) {
        return PageRequest.of(0, pageSize);
    }

    private static Pageable getPageable() {
        return PageRequest.of(0, 20);
    }

    @Test
    void testGetAllPlayers_MultiplePlayersExist() {
        Player player1 = createPlayer("player1", "John", "Doe");
        Player player2 = createPlayer("player2", "Jane", "Smith");
        playerRepository.saveAll(List.of(player1, player2));

        Players result = playerService.getAllPlayers(getPageable());

        assertEquals(2, result.getPlayers().size());
        assertTrue(result.getPlayers().stream().anyMatch(p -> p.getNameFirst().equals("John")));
        assertTrue(result.getPlayers().stream().anyMatch(p -> p.getNameFirst().equals("Jane")));
    }

    @Test
    void testGetPlayerById_ExistingPlayer() {
        Player player = createPlayer("player1", "John", "Doe");
        playerRepository.save(player);

        PlayerDTO result = playerService.getPlayerById("player1");

        assertNotNull(result);
        assertEquals("John", result.getNameFirst());
        assertEquals("Doe", result.getNameLast());
    }

    @Test
    void testGetPlayerById_NonExistingPlayer() {
        assertThrows(PlayerNotFoundException.class, () -> playerService.getPlayerById("nonexistent"));
    }

    @Test
    void testPlayerFieldMapping() {
        Player player = new Player(
                "player1", "1990", "01", "15", "USA", "CA", "Los Angeles",
                "2050", "12", "31", "USA", "NY", "New York",
                "John", "Doe", "John Michael Doe",
                "200", "72", "R", "R", "2010-04-01", "2020-09-30", "retr1", "bbre1"
        );
        playerRepository.save(player);

        PlayerDTO result = playerService.getPlayerById("player1");

        assertNotNull(result);
        assertEquals("1990", result.getBirthYear());
        assertEquals("01", result.getBirthMonth());
        assertEquals("15", result.getBirthDay());
        assertEquals("USA", result.getBirthCountry());
        assertEquals("CA", result.getBirthState());
        assertEquals("Los Angeles", result.getBirthCity());
        assertEquals("2050", result.getDeathYear());
        assertEquals("12", result.getDeathMonth());
        assertEquals("31", result.getDeathDay());
        assertEquals("USA", result.getDeathCountry());
        assertEquals("NY", result.getDeathState());
        assertEquals("New York", result.getDeathCity());
        assertEquals("John", result.getNameFirst());
        assertEquals("Doe", result.getNameLast());
        assertEquals("John Michael Doe", result.getNameGiven());
        assertEquals("200", result.getWeight());
        assertEquals("72", result.getHeight());
        assertEquals("R", result.getBats());
        assertEquals("R", result.getThrowingHand());
        assertEquals(LocalDate.of(2010, 4, 1), result.getDebut());
        assertEquals(LocalDate.of(2020, 9, 30), result.getFinalGame());
        assertEquals("retr1", result.getRetroID());
        assertEquals("bbre1", result.getBbrefID());
    }

    @Test
    void testGetAllPlayers_LargeDataset() {
        List<Player> players = generateLargePlayerDataset(1000);
        playerRepository.saveAll(players);

        Players result = playerService.getAllPlayers(getPageable(1000));

        assertEquals(1000, result.getPlayers().size());
    }

    @Test
    void testGetPlayerById_PlayerWithNullFields() {
        Player player = new Player(
                "player1", null, null, null, null, null, null,
                null, null, null, null, null, null,
                "John", "Doe", null,
                null, null, null, null, null, null, null, null
        );
        playerRepository.save(player);

        PlayerDTO result = playerService.getPlayerById("player1");

        assertNotNull(result);
        assertEquals("John", result.getNameFirst());
        assertEquals("Doe", result.getNameLast());
        assertNull(result.getBirthYear());
        assertNull(result.getDebut());
        assertNull(result.getFinalGame());
    }

    private Player createPlayer(String id, String firstName, String lastName) {
        return new Player(id, "1990", "01", "01", "USA", "CA", "Los Angeles",
                null, null, null, null, null, null,
                firstName, lastName, firstName + " " + lastName,
                "200", "72", "R", "R", "2010-04-01", "2020-09-30", "retr" + id, "bbre" + id);
    }

    private List<Player> generateLargePlayerDataset(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> createPlayer("player" + i, "FirstName" + i, "LastName" + i))
                .collect(Collectors.toList());
    }
}

