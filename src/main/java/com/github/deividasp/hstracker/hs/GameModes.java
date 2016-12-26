package com.github.deividasp.hstracker.hs;

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

}