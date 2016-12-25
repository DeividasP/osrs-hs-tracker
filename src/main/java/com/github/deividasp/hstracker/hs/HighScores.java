package com.github.deividasp.hstracker.hs;

import com.github.deividasp.hstracker.hs.entry.MinigameEntry;
import com.github.deividasp.hstracker.hs.entry.SkillEntry;

import java.util.Map;
import java.util.Optional;

/**
 * @author Deividas Popelskis <deividas.popelskis@gmail.com>
 */
public class HighScores {

	private final String username;
	private final Map<Skills, SkillEntry> skillEntries;
	private final Map<Minigames, MinigameEntry> minigameEntries;

	public HighScores(String username, Map<Skills, SkillEntry> skillEntries, Map<Minigames, MinigameEntry> minigameEntries) {
		this.username = username;
		this.skillEntries = skillEntries;
		this.minigameEntries = minigameEntries;
	}

	public String getUsername() {
		return username;
	}

	public Optional<SkillEntry> getSkillEntry(Skills skill) {
		return Optional.ofNullable(skillEntries.get(skill));
	}

	public Optional<MinigameEntry> getMinigameEntry(Minigames minigame) {
		return Optional.ofNullable(minigameEntries.get(minigame));
	}

}