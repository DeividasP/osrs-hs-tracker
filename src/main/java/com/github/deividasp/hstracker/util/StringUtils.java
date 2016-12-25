package com.github.deividasp.hstracker.util;

/**
 * @author Deividas Popelskis <deividas.popelskis@gmail.com>
 */
public class StringUtils {

	public static String strip(String string, String... stripStrings) {
		for (String stripString : stripStrings) {
			string = string.replace(stripString, "");
		}
		return string;
	}

}