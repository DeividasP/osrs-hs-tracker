package com.github.deividasp.hstracker.util;

/**
 * @author Deividas Popelskis <deividas.popelskis@gmail.com>
 */
public class ParseUtils {

	public static int parseInt(String value, String... separators) {
		return Integer.parseInt(StringUtils.strip(value, separators));
	}

	public static long parseLong(String value, String... separators) {
		return Long.parseLong(StringUtils.strip(value, separators));
	}

}