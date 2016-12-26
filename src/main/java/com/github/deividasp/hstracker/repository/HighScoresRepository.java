package com.github.deividasp.hstracker.repository;

import com.github.deividasp.hstracker.hs.GameModes;
import com.github.deividasp.hstracker.hs.HighScores;
import com.github.deividasp.hstracker.hs.HighScoresPK;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Deividas Popelskis <deividas.popelskis@gmail.com>
 */
public interface HighScoresRepository extends CrudRepository<HighScores, HighScoresPK> {

	@Query("SELECT CASE WHEN COUNT(h) > 0 THEN true ELSE false END FROM HighScores h WHERE h.username = :username AND h.gameMode = :gameMode")
	boolean existsByUsernameAndGameMode(@Param("username") String username, @Param("gameMode") GameModes gameMode);

}