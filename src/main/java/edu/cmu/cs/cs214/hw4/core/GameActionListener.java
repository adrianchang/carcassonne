package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * listen to game action
 */
public interface GameActionListener {

    /**
     * called when one player's score changes
     * might not really changed!!
     * @param playerScoreList list of all player's scores
     */
    void scoreChanged(HashMap<Player, Integer> playerScoreList);

    /**
     * play has changed
     * @param p the next player
     */
    void playerChanged(Player p);

    /**
     * final score came out
     * @param winners winners of the game
     * @param finalScores the finalScores of all players
     */
    void finalScore(ArrayList<Player> winners, HashMap<Player, Integer> finalScores);

}
