package com.github.deividasp.hstracker.util;

import java.util.concurrent.TimeUnit;

/**
 * @author Deividas Popelskis <deividas.popelskis@gmail.com>
 */
public class TimeUtils {
	
	public static long getMillisBefore(int minutes) {
		return System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(minutes);
	}

}