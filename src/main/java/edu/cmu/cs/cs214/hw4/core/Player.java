package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;

/**
 * the class that represent a player in this board game
 */
public class Player {
    private String name;
    private int score;
    private ArrayList<Follower> followers;

    /**
     * player construcotr
     * automatically generate seven followers for this player object
     * set player's name
     * set initial score = 0
     * @param name the name of the player
     */
    public Player (String name) {
        ArrayList<Follower> fList = new ArrayList<Follower>();
        for (int i = 0; i < 7; i++) {
            Follower f = new Follower(this);
            fList.add(f);
        }
        followers = fList;
        this.name = name;
        score = 0;
    }

    /**
     * get the name of the player
     * @return String player's name
     */
    public String getName () {
        return name;
    }

    /**
     * get the score of the player
     * @return int score of the player
     */
    public int getScore () {
        return score;
    }

    /**
     * send a follower to a segment(get a follower but have consequences)
     * the function will reduce a follower in ArrayList<Follower> follower
     * @return if there are still followers to set, it will the follower that's sent. Otherwise, return null
     */
    public Follower sendAFollower() {
        if (followers.size() == 0) {
            return null;
        }else {
            return followers.remove(0);
        }
    }

    /**
     * return the follower back to he's player
     * @param f the follower
     */
    public void getFollowerBack(Follower f){
        followers.add(f);
    }

    /**
     * add score to the player
     * @param addScore the score to add
     */
    public void addScore(int addScore){
        score += addScore;
    }

}
