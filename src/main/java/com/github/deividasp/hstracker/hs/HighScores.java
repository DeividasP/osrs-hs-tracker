package com.github.deividasp.hstracker.hs;

import com.github.deividasp.hstracker.hs.entry.minigame.MinigameEntry;
import com.github.deividasp.hstracker.hs.entry.minigame.Minigames;
import com.github.deividasp.hstracker.hs.entry.skill.SkillEntry;
import com.github.deividasp.hstracker.hs.entry.skill.Skills;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.Map;
import java.util.Optional;

/**
 * @author Deividas Popelskis <deividas.popelskis@gmail.com>
 */
@Entity
@Table(name = "high_scores")
@IdClass(HighScoresPK.class)
public class HighScores {

	@Id
	private String username;

	@Id
	@Enumerated(EnumType.STRING)
	private GameModes gameMode;

	@Id
	private long timestamp;

	@OneToMany(cascade = CascadeType.ALL)
	private Map<Skills, SkillEntry> skillEntries;

	@OneToMany(cascade = CascadeType.ALL)
	private Map<Minigames, MinigameEntry> minigameEntries;

	private HighScores() { }

	public HighScores(String username, GameModes gameMode, long timestamp, Map<Skills, SkillEntry> skillEntries, Map<Minigames, MinigameEntry> minigameEntries) {
		this.username = username;
		this.gameMode = gameMode;
		this.timestamp = timestamp;
		this.skillEntries = skillEntries;
		this.minigameEntries = minigameEntries;
	}

	public String getUsername() {
		return username;
	}

	public GameModes getGameMode() {
		return gameMode;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public Optional<SkillEntry> getSkillEntry(Skills skill) {
		return Optional.ofNullable(skillEntries.get(skill));
	}

	public Optional<MinigameEntry> getMinigameEntry(Minigames minigame) {
		return Optional.ofNullable(minigameEntries.get(minigame));
	}

}