package edu.cmu.cs.cs214.hw4.core.segmentpackage;

import edu.cmu.cs.cs214.hw4.core.Follower;
import edu.cmu.cs.cs214.hw4.core.Player;

/**
 * object that represent a field segment. can be add to the four direction segement list of tile
 * contain a follower
 */
public class FieldSegmentImpl implements Segment {
    private Follower follower;
    private String type = "field";
    @Override
    public Follower getFollower() {
        return follower;
    }

    @Override
    public boolean placeFollower(Player p) {
        Follower f = p.sendAFollower();
        if (f != null){
            follower = f;
            return true;
        }else {
            return false;
        }
    }

    /**
     * get the type of this segment
     * @return string of type
     */
    @Override
    public String getType() {
        return type;
    }

    @Override
    public void removeFollower(){
        follower = null;
    }
}
