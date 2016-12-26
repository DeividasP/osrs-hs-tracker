package com.github.deividasp.hstracker.hs;

import java.util.Optional;

/**
 * @author Deividas Popelskis <deividas.popelskis@gmail.com>
 */
public enum GameModes {

	NORMAL(""),
	IRONMAN("_ironman"),
	ULTIMATE_IRONMAN("_ultimate"),
	HARDCORE_IRONMAN("_hardcore_ironman"),
	DEADMAN("_deadman"),
	DEADMAN_SEASONAL("_seasonal");

	private final String urlParameterSuffix;

	GameModes(String urlParameterSuffix) {
		this.urlParameterSuffix = urlParameterSuffix;
	}

	public String getUrlParameterSuffix() {
		return urlParameterSuffix;
	}

	public static Optional<GameModes> forName(String name) {
		for (GameModes gameMode : GameModes.values()) {
			if (gameMode.toString().equalsIgnoreCase(name)) {
				return Optional.of(gameMode);
			}
		}
		return Optional.empty();
	}

}