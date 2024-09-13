package com.guy.shalev.IntuitHomeAssignment.dal;

import com.guy.shalev.IntuitHomeAssignment.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
}
