package com.github.deividasp.hstracker.service;

import com.github.deividasp.hstracker.hs.GameModes;
import com.github.deividasp.hstracker.hs.HighScores;
import com.github.deividasp.hstracker.hs.HighScoresPK;
import com.github.deividasp.hstracker.hs.HighScoresParser;
import com.github.deividasp.hstracker.repository.HighScoresRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Deividas Popelskis <deividas.popelskis@gmail.com>
 */
@Service
public class DefaultTrackerService implements TrackerService {

	private static final GameModes DEFAULT_TRACKING_GAME_MODE = GameModes.NORMAL;

	@Autowired
	private HighScoresRepository highScoresRepo;

	@Override
	public boolean track(String username) {
		return track(username, DEFAULT_TRACKING_GAME_MODE);
	}

	@Override
	public boolean track(String username, GameModes gameMode) {
		if (!highScoresRepo.exists(new HighScoresPK(username, gameMode))) {
			HighScoresParser parser = new HighScoresParser(username, gameMode);

			try {
				parser.connect();
			} catch (Exception exception) {
				exception.printStackTrace();
				return false;
			}

			Optional<HighScores> highScores = parser.parse();

			highScores.ifPresent(highScoresRepo::save);
			return highScores.isPresent();
		}
		return true;
	}

}