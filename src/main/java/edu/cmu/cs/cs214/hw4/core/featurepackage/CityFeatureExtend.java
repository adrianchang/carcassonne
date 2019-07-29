package edu.cmu.cs.cs214.hw4.core.featurepackage;

import edu.cmu.cs.cs214.hw4.core.Follower;
import edu.cmu.cs.cs214.hw4.core.Player;
import edu.cmu.cs.cs214.hw4.core.Tile;
import edu.cmu.cs.cs214.hw4.core.segmentpackage.CitySegmentImpl;
import edu.cmu.cs.cs214.hw4.core.segmentpackage.Segment;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * city feature extended from feature
 */
public class CityFeatureExtend extends Feature {
    /**
     * constructor of city feature
     * @param t the first tile of this feature
     * @param s the first segment of this feature
     */
    public CityFeatureExtend(Tile t, Segment s){
        super(t, s);
    }

    @Override
    public void setScore(){
        if (followerList.size() == 0) {
            return;
        }else {
            ArrayList<Player> winners = getWinner();
            for (Player p : winners){
                HashMap<Tile, Tile> temp = new HashMap<Tile, Tile>();
                for (Tile t : featureTileChain){
                    temp.put(t, t);
                }
                int pennantCount = 0;
                for (Segment s :featureSegmentChain){
                    if (((CitySegmentImpl)s).getPennant()){
                        pennantCount++;
                    }
                }
                p.addScore((pennantCount + temp.size())*2);
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
    public void incompleteSetScore () {
        if (followerList.size() == 0){
            return;
        }else {
            ArrayList<Player> winners = getWinner();
            for (Player p : winners){
                HashMap<Tile, Tile> temp = new HashMap<Tile, Tile>();
                for (Tile t : featureTileChain){
                    temp.put(t, t);
                }
                int pennantCount = 0;
                for (Segment s :featureSegmentChain){
                    if (((CitySegmentImpl)s).getPennant()){
                        pennantCount++;
                    }
                }
                p.addScore(pennantCount + temp.size());
            }
        }
    }
}
