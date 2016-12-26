package com.github.deividasp.hstracker.hs;

import com.github.deividasp.hstracker.util.FileUtils;

import org.junit.Test;

import java.io.File;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * @author Deividas Popelskis <deividas.popelskis@gmail.com>
 */
public class HighScoresParserTest {

	@Test
	public void test1() {
		HighScoresParser parser = new HighScoresParser("Valid Username");

		try {
			Optional<File> fileOptional = FileUtils.getResourceFile("hs_valid_username.html");

			if (fileOptional.isPresent()) {
				parser.read(fileOptional.get());
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		Optional<HighScores> highScoresOptional = parser.parse();

		highScoresOptional.ifPresent(h -> {
			h.getSkillEntry(Skills.OVERALL).ifPresent(e -> assertEquals(1, e.getRank()));
			h.getSkillEntry(Skills.STRENGTH).ifPresent(e -> assertEquals(4, e.getRank()));
			h.getSkillEntry(Skills.ATTACK).ifPresent(e -> assertEquals(99, e.getLevel()));
			h.getSkillEntry(Skills.DEFENCE).ifPresent(e -> assertEquals(200000000, e.getExperience()));
			h.getSkillEntry(Skills.RANGED).ifPresent(e -> assertEquals(5, e.getRank()));

			assertEquals(false, h.getMinigameEntry(Minigames.LMS).isPresent());
		});
	}

	@Test
	public void test2() {
		HighScoresParser parser = new HighScoresParser("Invalid Username");

		try {
			Optional<File> fileOptional = FileUtils.getResourceFile("hs_invalid_username.html");

			if (fileOptional.isPresent()) {
				parser.read(fileOptional.get());
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		assertEquals(false, parser.parse().isPresent());
	}

	@Test
	public void test3() {
		HighScoresParser parser = new HighScoresParser("Lelalt", GameModes.IRONMAN);

		try {
			parser.connect();
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		Optional<HighScores> highScoresOptional = parser.parse();

		highScoresOptional.ifPresent(h -> {
			h.getSkillEntry(Skills.FIREMAKING).ifPresent(e -> assertEquals(1, e.getRank()));
			h.getSkillEntry(Skills.STRENGTH).ifPresent(e -> assertEquals(99, e.getLevel()));
			h.getSkillEntry(Skills.FIREMAKING).ifPresent(e -> assertEquals(200000000, e.getExperience()));
		});

		parser = new HighScoresParser("Lelalt");

		try {
			parser.connect();
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		highScoresOptional = parser.parse();

		highScoresOptional.ifPresent(h -> {
			h.getSkillEntry(Skills.FIREMAKING).ifPresent(e -> assertEquals(19, e.getRank()));
			h.getSkillEntry(Skills.STRENGTH).ifPresent(e -> assertEquals(99, e.getLevel()));
		});
	}

	@Test
	public void test4() {
		HighScoresParser parser = new HighScoresParser("Navus", GameModes.ULTIMATE_IRONMAN);

		try {
			parser.connect();
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		Optional<HighScores> highScoresOptional = parser.parse();

		highScoresOptional.ifPresent(h -> {
			h.getSkillEntry(Skills.COOKING).ifPresent(e -> assertEquals(1, e.getRank()));
			h.getSkillEntry(Skills.COOKING).ifPresent(e -> assertEquals(99, e.getLevel()));
			h.getSkillEntry(Skills.COOKING).ifPresent(e -> assertEquals(200000000, e.getExperience()));
		});
	}

}