package com.github.deividasp.hstracker.repository;

import com.github.deividasp.hstracker.hs.HighScores;
import com.github.deividasp.hstracker.hs.HighScoresPK;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Deividas Popelskis <deividas.popelskis@gmail.com>
 */
public interface HighScoresRepository extends CrudRepository<HighScores, HighScoresPK> {

}