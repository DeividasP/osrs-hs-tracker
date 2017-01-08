package com.github.deividasp.hstracker.service;

import com.github.deividasp.hstracker.hs.GameModes;
import com.github.deividasp.hstracker.hs.HighScores;
import com.github.deividasp.hstracker.hs.HighScoresParser;
import com.github.deividasp.hstracker.repository.HighScoresRepository;

import com.github.deividasp.hstracker.util.TimeUtils;

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

	private Optional<HighScores> getCurrentHighScores(String username, GameModes gameMode) {
		HighScoresParser parser = new HighScoresParser(username, gameMode);

		try {
			parser.connect();
		} catch (Exception exception) {
			exception.printStackTrace();
			return Optional.empty();
		}

		return parser.parse();
	}

	private boolean persistHighScores(String username, GameModes gameMode) {
		Optional<HighScores> highScoresOptional = getCurrentHighScores(username, gameMode);

		highScoresOptional.ifPresent(highScoresRepo::save);

		return highScoresOptional.isPresent();
	}

	@Override
	public boolean track(String username) {
		return track(username, DEFAULT_TRACKING_GAME_MODE);
	}

	@Override
	public boolean track(String username, GameModes gameMode) {
		return highScoresRepo.exists(username, gameMode)
				|| persistHighScores(username, gameMode);
	}

	@Override
	public boolean update(String username) {
		return update(username, DEFAULT_TRACKING_GAME_MODE);
	}

	@Override
	public boolean update(String username, GameModes gameMode) {
		return highScoresRepo.exists(username, gameMode)
				&& persistHighScores(username, gameMode);
	}

	@Override
	public Optional<HighScores> getDifference(String username, int seconds) {
		return getDifference(username, DEFAULT_TRACKING_GAME_MODE, seconds);
	}

	@Override
	public Optional<HighScores> getDifference(String username, GameModes gameMode, int seconds) {
		Optional<HighScores> highScoresOptional = getCurrentHighScores(username, gameMode);

		if (!highScoresOptional.isPresent()) {
			return Optional.empty();
		}

		return highScoresOptional.get().getDifference(highScoresRepo.find(username, gameMode.toString(),
				TimeUtils.getMillisBefore(seconds)));
	}

}