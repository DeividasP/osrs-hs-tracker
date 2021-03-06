package com.github.deividasp.hstracker.hs;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.github.deividasp.hstracker.hs.entry.minigame.MinigameEntry;
import com.github.deividasp.hstracker.hs.entry.minigame.Minigames;
import com.github.deividasp.hstracker.hs.entry.skill.SkillEntry;
import com.github.deividasp.hstracker.hs.entry.skill.Skills;
import com.github.deividasp.hstracker.serialization.MapSerializer;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.HashMap;
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
	@JsonSerialize(using = MapSerializer.class)
	private Map<Skills, SkillEntry> skillEntries;

	@OneToMany(cascade = CascadeType.ALL)
	@JsonSerialize(using = MapSerializer.class)
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

	public Optional<HighScores> getDifference(Optional<HighScores> otherHighScoresOptional) {
		if (!otherHighScoresOptional.isPresent()) {
			return Optional.empty();
		}

		HighScores otherHighScores = otherHighScoresOptional.get();

		Map<Skills, SkillEntry> skillEntries = new HashMap<>();

		for (Skills skill : Skills.values()) {
			getSkillEntry(skill).ifPresent(e ->
				skillEntries.put(skill, e.getDifference(otherHighScores.getSkillEntry(skill)))
			);
		}

		Map<Minigames, MinigameEntry> minigameEntries = new HashMap<>();

		for (Minigames minigame : Minigames.values()) {
			getMinigameEntry(minigame).ifPresent(m ->
				minigameEntries.put(minigame, m.getDifference(otherHighScores.getMinigameEntry(minigame)))
			);
		}

		return Optional.of(new HighScores(username, gameMode, System.currentTimeMillis(),
				skillEntries, minigameEntries));
	}

}