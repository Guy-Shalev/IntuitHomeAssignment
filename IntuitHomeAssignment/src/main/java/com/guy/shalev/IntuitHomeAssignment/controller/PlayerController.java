package com.guy.shalev.IntuitHomeAssignment.controller;

import com.guy.shalev.IntuitHomeAssignment.exception.PlayerNotFoundException;
import com.guy.shalev.IntuitHomeAssignment.model.dto.PlayerDTO;
import com.guy.shalev.IntuitHomeAssignment.model.reponse.Players;
import com.guy.shalev.IntuitHomeAssignment.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final IPlayerService playerService;

    @Autowired
    public PlayerController(IPlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping(value = {"", "/"})
    public ResponseEntity<Players> getAllPlayers(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(playerService.getAllPlayers(pageable));
    }

    @GetMapping("/{playerID}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable String playerID) {
        PlayerDTO player = playerService.getPlayerById(playerID);
        return ResponseEntity.ok(player);
    }
}
