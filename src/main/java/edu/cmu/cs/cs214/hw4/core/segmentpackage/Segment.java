package edu.cmu.cs.cs214.hw4.core.segmentpackage;

import edu.cmu.cs.cs214.hw4.core.Follower;
import edu.cmu.cs.cs214.hw4.core.Player;

/**
 * the interface that define the method of a basic segment
 */
public interface Segment {
    /**
     * get the follower in the segment
     * @return the Follower follower
     */
    Follower getFollower();

    /**
     * place a follower of p on the segment
     * @param p the player who owns followers
     * @return whether placement succeed or not
     */
    boolean placeFollower(Player p);

    /**
     * get the type of this segment
     * there's a get type function here because there is a need to compare the segments' types in map class
     * if I use instance of then there will be a big chuck of code
     * and i don't think override equals method is suitable here because it's just the equal of types not
     * the whole object
     * @return string of type
     */
    String getType();

    /**
     * remove the follower from this segment
     */
    void removeFollower();
}
