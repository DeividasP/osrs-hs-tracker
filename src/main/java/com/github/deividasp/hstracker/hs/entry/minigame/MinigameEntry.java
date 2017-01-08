package com.github.deividasp.hstracker.hs.entry.minigame;

import com.github.deividasp.hstracker.hs.entry.HighScoresEntry;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.Optional;

/**
 * @author Deividas Popelskis <deividas.popelskis@gmail.com>
 */
@Entity
@Table(name = "minigame_entries")
public class MinigameEntry extends HighScoresEntry {

	private int score;

	private MinigameEntry() { }

	public MinigameEntry(String name, int rank, int score) {
		super(name, rank);

		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public MinigameEntry getDifference(Optional<MinigameEntry> otherEntryOptional) {
		if (!otherEntryOptional.isPresent()) {
			return new MinigameEntry(name, rank, score);
		}

		MinigameEntry otherEntry = otherEntryOptional.get();

		return new MinigameEntry(name, otherEntry.getRank() - rank, score - otherEntry.score);
	}

}