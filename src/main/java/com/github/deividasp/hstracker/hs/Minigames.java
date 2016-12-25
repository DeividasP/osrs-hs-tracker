package com.github.deividasp.hstracker.hs;

import java.util.Optional;

/**
 * @author Deividas Popelskis <deividas.popelskis@gmail.com>
 */
public enum Minigames {

	BH_ROGUE("Bounty Hunter - Rogue"),
	BH_HUNTER("Bounty Hunter - Hunter"),
	LMS("LMS - Rank");

	private final String highScoresName;

	Minigames(String highScoresName) {
		this.highScoresName = highScoresName;
	}

	private String getHighScoresName() {
		return highScoresName;
	}

	public static Optional<Minigames> forName(String name) {
		for (Minigames minigame : Minigames.values()) {
			if (minigame.getHighScoresName().equalsIgnoreCase(name)) {
				return Optional.of(minigame);
			}
		}
		return Optional.empty();
	}

}