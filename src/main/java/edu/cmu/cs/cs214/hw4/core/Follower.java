package edu.cmu.cs.cs214.hw4.core;

/**
 * the follower object. Follower belongs to a player and follower can be place on a tile
 * contain the boss of the follower
 */
public class Follower {
    private final Player boss;

    /**
     * constructor of Follower object
     * @param p the boss of the follower
     */
    public Follower (Player p) {
        boss = p;
    }

    /**
     * get the boss of this follower
     * @return Player boss
     */
    public Player getBoss() {
        return boss;
    }
}
