package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * test gameaction listener
 * there will be some output because I need to test this action listener function for gui usage
 */
public class GameActionListenerImplTest implements GameActionListener {

    @Override
    public void scoreChanged(HashMap<Player, Integer>  playerScoreList) {
        System.out.println("scoreChanged ");
    }

    @Override
    public void playerChanged(Player p) {
        System.out.println("playerChanged " + p);
    }

    @Override
    public void finalScore(ArrayList<Player> winners, HashMap<Player, Integer> finalScores){
        System.out.println("finalScore ");
    }
}
