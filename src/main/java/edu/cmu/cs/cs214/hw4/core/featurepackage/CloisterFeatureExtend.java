package edu.cmu.cs.cs214.hw4.core.featurepackage;

import edu.cmu.cs.cs214.hw4.core.Follower;
import edu.cmu.cs.cs214.hw4.core.Tile;
import edu.cmu.cs.cs214.hw4.core.segmentpackage.Segment;
/**
 * cloister feature extended from feature
 */
public class CloisterFeatureExtend extends Feature {
    /**
     * constructor of cloister feature
     * @param t the first tile of this feature
     * @param s the first segment of this feature
     */
    public CloisterFeatureExtend(Tile t, Segment s){
        super(t, s);
        int neighborNum = t.getNeighbors().size();
        super.setBreaches(8-neighborNum);
    }
    @Override
    public void setScore(){
        if (followerList.size() == 0) {
            return;
        }else {
            Follower f = followerList.get(0);
            f.getBoss().addScore(9);
            f.getBoss().getFollowerBack(f);
            for (Segment s: this.featureSegmentChain){
                s.removeFollower();
            }
        }

    }
    @Override
    public void incompleteSetScore() {
        if (followerList.size() == 0){
            return;
        }else {
            Follower f = followerList.get(0);
            f.getBoss().addScore(1 + 8 - breaches);
        }
    }

}
