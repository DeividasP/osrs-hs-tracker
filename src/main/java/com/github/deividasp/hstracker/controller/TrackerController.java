package com.github.deividasp.hstracker.controller;

import com.github.deividasp.hstracker.hs.GameModes;
import com.github.deividasp.hstracker.hs.HighScores;
import com.github.deividasp.hstracker.service.TrackerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Deividas Popelskis <deividas.popelskis@gmail.com>
 */
@RestController
public class TrackerController {

	@Autowired
	private TrackerService service;

	@RequestMapping({ "/api/track/{username}", "/api/track/{gameMode}/{username}" })
	public boolean track(@PathVariable String username, @PathVariable Optional<String> gameMode) {
		if (gameMode.isPresent()) {
			Optional<GameModes> mode = GameModes.forName(gameMode.get());

			if (mode.isPresent()) {
				return service.track(username, mode.get());
			}
		}
		return service.track(username);
	}

	@RequestMapping({ "/api/update/{username}", "/api/update/{gameMode}/{username}" })
	public boolean update(@PathVariable String username, @PathVariable Optional<String> gameMode) {
		if (gameMode.isPresent()) {
			Optional<GameModes> mode = GameModes.forName(gameMode.get());

			if (mode.isPresent()) {
				return service.update(username, mode.get());
			}
		}
		return service.update(username);
	}

	@RequestMapping({ "/api/diff/{username}/{seconds}", "/api/get/{username}/{gameMode}/{seconds}" })
	public HighScores diff(@PathVariable String username, @PathVariable Optional<String> gameMode, @PathVariable int seconds) {
		if (gameMode.isPresent()) {
			Optional<GameModes> mode = GameModes.forName(gameMode.get());

			if (mode.isPresent()) {
				Optional<HighScores> highScoresOptional = service.getDifference(username, mode.get(), seconds);

				if (highScoresOptional.isPresent()) {
					return highScoresOptional.get();
				}

				return null;
			}
		}

		Optional<HighScores> highScoresOptional = service.getDifference(username, seconds);

		if (highScoresOptional.isPresent()) {
			return highScoresOptional.get();
		}

		return null;
	}

}