package com.github.deividasp.hstracker.hs.entry;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Deividas Popelskis <deividas.popelskis@gmail.com>
 */
@Entity
@Table(name = "skill_entries")
public class SkillEntry extends HighScoresEntry {

	private int level;
	private long experience;

	private SkillEntry() { }

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