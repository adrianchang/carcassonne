package edu.cmu.cs.cs214.hw4.core.featurepackage;

import edu.cmu.cs.cs214.hw4.core.Follower;
import edu.cmu.cs.cs214.hw4.core.Player;
import edu.cmu.cs.cs214.hw4.core.Tile;
import edu.cmu.cs.cs214.hw4.core.featurepackage.Feature;
import edu.cmu.cs.cs214.hw4.core.segmentpackage.Segment;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * road feature extended from feature
 */
public class RoadFeatureExtend extends Feature {
    /**
     * constructor of road feature
     * @param t the first tile of this feature
     * @param s the first segment of this feature
     */
    public RoadFeatureExtend(Tile t, Segment s){
        super(t, s);
    }

    @Override
    public void setScore(){
        if (followerList.size() == 0){
            return;
        }else {
            ArrayList<Player> winners = getWinner();
            for (Player p : winners){
                HashMap<Tile, Tile> temp = new HashMap<Tile, Tile>();
                for (Tile t : featureTileChain){
                    temp.put(t, t);
                }
                p.addScore(temp.size());
            }
            for (Segment s: this.featureSegmentChain){
                s.removeFollower();
            }
            for(Follower f : followerList){
                f.getBoss().getFollowerBack(f);
            }
        }

    }

    @Override
    public void incompleteSetScore(){
        if (followerList.size() == 0){
            return;
        }else {
            ArrayList<Player> winners = getWinner();
            for (Player p : winners){
                HashMap<Tile, Tile> temp = new HashMap<Tile, Tile>();
                for (Tile t : featureTileChain){
                    temp.put(t, t);
                }
                p.addScore(temp.size());
            }
        }
    }
}
