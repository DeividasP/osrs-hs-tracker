package com.github.deividasp.hstracker.hs;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Deividas Popelskis <deividas.popelskis@gmail.com>
 */
public class HighScoresPK implements Serializable {

	private String username;
	private GameModes gameMode;

	private HighScoresPK() { }

	public HighScoresPK(String username, GameModes gameMode) {
		this.username = username;
		this.gameMode = gameMode;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (object == null || getClass() != object.getClass()) {
			return false;
		}

		HighScoresPK primaryKey = (HighScoresPK) object;

		return Objects.equals(username, primaryKey.username) && gameMode == primaryKey.gameMode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(username, gameMode);
	}

}