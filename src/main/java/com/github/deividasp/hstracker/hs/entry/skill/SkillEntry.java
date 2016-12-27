package com.github.deividasp.hstracker.hs.entry.skill;

import com.github.deividasp.hstracker.hs.entry.HighScoresEntry;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.Optional;

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

	public SkillEntry getDifference(Optional<SkillEntry> otherEntryOptional) {
		if (!otherEntryOptional.isPresent()) {
			return new SkillEntry(name, rank, level, experience);
		}

		SkillEntry otherEntry = otherEntryOptional.get();

		return new SkillEntry(name, rank - otherEntry.getRank(), level - otherEntry.level, experience - otherEntry.experience);
	}

}