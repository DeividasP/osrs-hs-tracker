package com.github.deividasp.hstracker.hs;

import com.github.deividasp.hstracker.hs.entry.minigame.MinigameEntry;
import com.github.deividasp.hstracker.hs.entry.minigame.Minigames;
import com.github.deividasp.hstracker.hs.entry.skill.SkillEntry;
import com.github.deividasp.hstracker.hs.entry.skill.Skills;
import com.github.deividasp.hstracker.util.ParseUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Deividas Popelskis <deividas.popelskis@gmail.com>
 */
public class HighScoresParser {

	private static final String HIGH_SCORES_URL = "http://services.runescape.com/m=hiscore_oldschool%s/hiscorepersonal.ws?user1=%s";

	private static final String ENTRIES_QUERY = "tr:gt(2)";
	private static final String ENTRY_COLUMNS_QUERY = "td";
	private static final String TITLE_QUERY = "#contentHiscores b";

	private static final String NUMBER_SEPARATOR = ",";
	private static final String EXISTING_USER_TITLE_TEXT = "Personal scores for %s";

	private static final int SKILL_ENTRY_COLUMN_COUNT = 5;
	private static final int MINIGAME_ENTRY_COLUMN_COUNT = 4;

	private static final int NAME_COLUMN_INDEX = 1;
	private static final int RANK_COLUMN_INDEX = 2;
	private static final int LEVEL_COLUMN_INDEX = 3;
	private static final int SCORE_COLUMN_INDEX = 3;
	private static final int EXPERIENCE_COLUMN_INDEX = 4;

	private static final GameModes DEFAULT_GAME_MODE = GameModes.NORMAL;

	private final String username;
	private final GameModes gameMode;

	private Document document;

	public HighScoresParser(String username) {
		this(username, DEFAULT_GAME_MODE);
	}

	public HighScoresParser(String username, GameModes gameMode) {
		this.username = username;
		this.gameMode = gameMode;
	}

	public void read(File file) throws IOException {
		document = Jsoup.parse(file, "UTF-8");
	}

	public void connect() throws Exception {
		String url = String.format(HIGH_SCORES_URL, gameMode.getUrlParameterSuffix(), URLEncoder.encode(username, "UTF-8"));

		document = Jsoup.connect(url).get();
	}

	public Optional<HighScores> parse() {
		if (!userExists() || document == null) {
			return Optional.empty();
		}

		Map<Skills, SkillEntry> skillEntries = new HashMap<>();
		Map<Minigames, MinigameEntry> minigameEntries = new HashMap<>();

		document.select(ENTRIES_QUERY).stream().filter(e -> e.select(ENTRY_COLUMNS_QUERY).size() == SKILL_ENTRY_COLUMN_COUNT)
				.forEach(e -> parseSkillEntry(e, skillEntries));
		document.select(ENTRIES_QUERY).stream().filter(e -> e.select(ENTRY_COLUMNS_QUERY).size() == MINIGAME_ENTRY_COLUMN_COUNT)
				.forEach(e -> parseMinigameEntry(e, minigameEntries));

		return Optional.of(new HighScores(username, gameMode, System.currentTimeMillis(), skillEntries, minigameEntries));
	}

	private boolean userExists() {
		Element titleElement = document.select(TITLE_QUERY).first();

		return titleElement != null && titleElement.toString().contains(String.format(EXISTING_USER_TITLE_TEXT, username));
	}

	private void parseSkillEntry(Element element, Map<Skills, SkillEntry> skillEntries) {
		String name = element.child(NAME_COLUMN_INDEX).text();
		int rank = ParseUtils.parseInt(element.child(RANK_COLUMN_INDEX).text(), NUMBER_SEPARATOR);
		int level = ParseUtils.parseInt(element.child(LEVEL_COLUMN_INDEX).text(), NUMBER_SEPARATOR);
		long experience = ParseUtils.parseLong(element.child(EXPERIENCE_COLUMN_INDEX).text(), NUMBER_SEPARATOR);

		skillEntries.put(Skills.valueOf(name.toUpperCase()), new SkillEntry(name, rank, level, experience));
	}

	private void parseMinigameEntry(Element element, Map<Minigames, MinigameEntry> minigameEntries) {
		String name = element.child(NAME_COLUMN_INDEX).text();
		int rank = ParseUtils.parseInt(element.child(RANK_COLUMN_INDEX).text(), NUMBER_SEPARATOR);
		int score = ParseUtils.parseInt(element.child(SCORE_COLUMN_INDEX).text(), NUMBER_SEPARATOR);

		Minigames.forName(name).ifPresent(m -> minigameEntries.put(m, new MinigameEntry(name, rank, score)));
	}

}