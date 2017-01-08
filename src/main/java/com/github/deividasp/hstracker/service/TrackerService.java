package com.github.deividasp.hstracker.service;

import com.github.deividasp.hstracker.hs.GameModes;
import com.github.deividasp.hstracker.hs.HighScores;

import java.util.Optional;

/**
 * @author Deividas Popelskis <deividas.popelskis@gmail.com>
 */
public interface TrackerService {

	boolean track(String username);
	boolean track(String username, GameModes gameMode);

	boolean update(String username);
	boolean update(String username, GameModes gameMode);

	Optional<HighScores> getDifference(String username, int seconds);
	Optional<HighScores> getDifference(String username, GameModes gameMode, int seconds);

}