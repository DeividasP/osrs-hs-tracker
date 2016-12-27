package com.github.deividasp.hstracker.hs.entry;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Deividas Popelskis <deividas.popelskis@gmail.com>
 */
@MappedSuperclass
public abstract class HighScoresEntry {

	@Id
	@GeneratedValue
	private long id;

	protected String name;
	protected int rank;

	protected HighScoresEntry() { }

	protected HighScoresEntry(String name, int rank) {
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