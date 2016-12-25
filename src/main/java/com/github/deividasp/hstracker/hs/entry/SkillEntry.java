package com.github.deividasp.hstracker.hs.entry;

/**
 * @author Deividas Popelskis <deividas.popelskis@gmail.com>
 */
public class SkillEntry extends HighScoresEntry {

	private final int level;
	private final long experience;

	public SkillEntry(String name, int rank, int level, long experience) {
		super(name, rank);

		this.level = level;
		this.experience = experience;
	}

	public int getLevel() {
		return level;
	}

	public long getExperience() {
		return experience;
	}

}