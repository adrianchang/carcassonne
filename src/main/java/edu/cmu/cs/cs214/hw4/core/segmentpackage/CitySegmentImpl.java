package edu.cmu.cs.cs214.hw4.core.segmentpackage;

import edu.cmu.cs.cs214.hw4.core.Follower;
import edu.cmu.cs.cs214.hw4.core.Player;

/**
 * object that represent a city segment. can be add to the four direction segement list of tile
 * contain a follower
 */
public class CitySegmentImpl implements Segment {
    private Follower follower;
    private Boolean pennant = false;
    private String type = "city";
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
     * set a pennant
     * set pennant to true
     */
    public void setPennant (){
        pennant = true;
    }

    /**
     * get whether there's a pennant
     * @return if there's a pennat return true, else false
     */
    public Boolean getPennant () {
        return pennant;
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
