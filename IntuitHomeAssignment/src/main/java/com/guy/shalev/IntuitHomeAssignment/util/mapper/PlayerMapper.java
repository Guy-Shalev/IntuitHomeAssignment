package com.guy.shalev.IntuitHomeAssignment.util.mapper;

import com.guy.shalev.IntuitHomeAssignment.model.dto.PlayerDTO;
import com.guy.shalev.IntuitHomeAssignment.model.entity.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class PlayerMapper {
    private static final Logger logger = LogManager.getLogger(PlayerMapper.class);


    private static final List<DateTimeFormatter> DATE_FORMATTERS = List.of(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy")
    );

    public static PlayerDTO convertToDTO(Player player) {
        PlayerDTO dto = new PlayerDTO();

        dto.setPlayerID(player.getPlayerID());
        dto.setBirthYear(player.getBirthYear());
        dto.setBirthMonth(player.getBirthMonth());
        dto.setBirthDay(player.getBirthDay());
        dto.setBirthCountry(player.getBirthCountry());
        dto.setBirthState(player.getBirthState());
        dto.setBirthCity(player.getBirthCity());
        dto.setDeathYear(player.getDeathYear());
        dto.setDeathMonth(player.getDeathMonth());
        dto.setDeathDay(player.getDeathDay());
        dto.setDeathCountry(player.getDeathCountry());
        dto.setDeathState(player.getDeathState());
        dto.setDeathCity(player.getDeathCity());
        dto.setNameFirst(player.getNameFirst());
        dto.setNameLast(player.getNameLast());
        dto.setNameGiven(player.getNameGiven());
        dto.setWeight(player.getWeight());
        dto.setHeight(player.getHeight());
        dto.setBats(player.getBats());
        dto.setThrowingHand(player.getThrowingHand());
        dto.setRetroID(player.getRetroID());
        dto.setBbrefID(player.getBbrefID());
        dto.setDebut(convertStringToLocalDate(player.getPlayerID(), "debut", player.getDebut()));
        dto.setFinalGame(convertStringToLocalDate(player.getPlayerID(), "finalGame", player.getFinalGame()));

        return dto;
    }

    /**
     * Converts a string date to a LocalDate object.
     *
     * @param playerId   The ID of the player (for logging purposes)
     * @param fieldName  The name of the field being converted (for logging purposes)
     * @param dateString The date string to convert
     * @return The converted LocalDate, or null if conversion fails
     */
    private static LocalDate convertStringToLocalDate(String playerId, String fieldName, String dateString) {
        if (isBlank(dateString)) {
            return null;
        }

        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                // Continue to the next formatter
            }
        }
        // If we've exhausted all formatters without success, log a warning with all attempt details
        logger.warn("PlayerID: {}, Failed to parse {} date '{}'.",
                playerId, fieldName, dateString);
        return null;
    }
}
