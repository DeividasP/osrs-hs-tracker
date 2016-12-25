package com.github.deividasp.hstracker.hs.entry;

/**
 * @author Deividas Popelskis <deividas.popelskis@gmail.com>
 */
public class MinigameEntry extends HighScoresEntry {

	private final int score;

	public MinigameEntry(String name, int rank, int score) {
		super(name, rank);

		this.score = score;
	}

	public int getScore() {
		return score;
	}

}