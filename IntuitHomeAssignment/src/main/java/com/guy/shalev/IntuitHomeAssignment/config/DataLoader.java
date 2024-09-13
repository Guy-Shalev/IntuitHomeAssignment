package com.guy.shalev.IntuitHomeAssignment.config;

import com.guy.shalev.IntuitHomeAssignment.dal.PlayerRepository;
import com.guy.shalev.IntuitHomeAssignment.model.entity.Player;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStreamReader;
import java.io.Reader;


@Configuration
public class DataLoader {
    private static final Logger logger = LogManager.getLogger(DataLoader.class);

    public static final String PLAYER_FILE_NAME = "player.csv";
    private final PlayerRepository playerRepository;

    @Autowired
    public DataLoader(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            try (Reader in = new InputStreamReader(new ClassPathResource(PLAYER_FILE_NAME).getInputStream())) {
                logger.info("Started loading players data");
                Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);

                for (CSVRecord record : records) {
                    Player player = new Player(
                            record.get("playerID"),
                            record.get("birthYear"),
                            record.get("birthMonth"),
                            record.get("birthDay"),
                            record.get("birthCountry"),
                            record.get("birthState"),
                            record.get("birthCity"),
                            record.get("deathYear"),
                            record.get("deathMonth"),
                            record.get("deathDay"),
                            record.get("deathCountry"),
                            record.get("deathState"),
                            record.get("deathCity"),
                            record.get("nameFirst"),
                            record.get("nameLast"),
                            record.get("nameGiven"),
                            record.get("weight"),
                            record.get("height"),
                            record.get("bats"),
                            record.get("throws"),
                            record.get("debut"),
                            record.get("finalGame"),
                            record.get("retroID"),
                            record.get("bbrefID")
                    );

                    playerRepository.save(player);
                }
                logger.info("Done loading players data");
            }
        };
    }
}
