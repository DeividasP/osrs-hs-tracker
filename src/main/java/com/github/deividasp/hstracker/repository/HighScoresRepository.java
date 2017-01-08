package com.github.deividasp.hstracker.repository;

import com.github.deividasp.hstracker.hs.GameModes;
import com.github.deividasp.hstracker.hs.HighScores;
import com.github.deividasp.hstracker.hs.HighScoresPK;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author Deividas Popelskis <deividas.popelskis@gmail.com>
 */
public interface HighScoresRepository extends CrudRepository<HighScores, HighScoresPK> {

	@Query("SELECT CASE WHEN COUNT(h) > 0 THEN true ELSE false END FROM HighScores h WHERE h.username = :username AND h.gameMode = :gameMode")
	boolean exists(@Param("username") String username, @Param("gameMode") GameModes gameMode);

	@Query(value = "SELECT * FROM high_scores h WHERE h.username = ?1 AND h.game_mode = ?2 AND h.timestamp >= ?3 ORDER BY h.timestamp ASC LIMIT 1", nativeQuery = true)
	Optional<HighScores> find(String username, String gameMode, long timestamp);

}