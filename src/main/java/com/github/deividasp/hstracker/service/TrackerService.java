package com.github.deividasp.hstracker.service;

import com.github.deividasp.hstracker.hs.GameModes;

/**
 * @author Deividas Popelskis <deividas.popelskis@gmail.com>
 */
public interface TrackerService {

	boolean track(String username);
	boolean track(String username, GameModes gameMode);

}