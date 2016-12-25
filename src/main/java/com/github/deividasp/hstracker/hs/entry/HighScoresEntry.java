package com.github.deividasp.hstracker.hs.entry;

/**
 * @author Deividas Popelskis <deividas.popelskis@gmail.com>
 */
public abstract class HighScoresEntry {

	private final String name;
	private final int rank;

	HighScoresEntry(String name, int rank) {
		this.name = name;
		this.rank = rank;
	}

	public String getName() {
		return name;
	}

	public int getRank() {
		return rank;
	}

}