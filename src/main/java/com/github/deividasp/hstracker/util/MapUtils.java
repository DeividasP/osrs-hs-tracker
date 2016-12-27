package com.github.deividasp.hstracker.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Deividas Popelskis <deividas.popelskis@gmail.com>
 */
public class MapUtils {

	public static <K, V> Map<K, V> copy(Map<K, V> sourceMap) {
		Map<K, V> mapCopy = new HashMap<>();
		mapCopy.putAll(sourceMap);
		return mapCopy;
	}

}