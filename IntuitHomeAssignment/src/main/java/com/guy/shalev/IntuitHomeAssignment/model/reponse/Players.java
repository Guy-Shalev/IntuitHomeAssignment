package com.guy.shalev.IntuitHomeAssignment.model.reponse;

import com.guy.shalev.IntuitHomeAssignment.model.dto.PlayerDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Players {

    List<PlayerDTO> players;
    private int totalPages;
    private long totalElements;
    private int currentPage;
    private int pageSize;

    public Players(List<PlayerDTO> players) {
        this.players = players;
    }

}
