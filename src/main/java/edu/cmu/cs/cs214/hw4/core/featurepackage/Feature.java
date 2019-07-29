package edu.cmu.cs.cs214.hw4.core.featurepackage;

import edu.cmu.cs.cs214.hw4.core.Follower;
import edu.cmu.cs.cs214.hw4.core.Player;
import edu.cmu.cs.cs214.hw4.core.Tile;
import edu.cmu.cs.cs214.hw4.core.segmentpackage.Segment;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * a feature is a list of chained segments
 */
public abstract class Feature {
    protected ArrayList<Tile> featureTileChain;
    protected ArrayList<Segment> featureSegmentChain;
    protected ArrayList<Follower> followerList;
    protected int breaches;

    /**
     * Feature constructor
     * @param t the tile to add to this feature
     * @param s the segment in this tile to add to this feature
     */
    public Feature(Tile t, Segment s) {
        featureTileChain = new ArrayList<Tile>();
        featureSegmentChain = new ArrayList<>();
        followerList = new ArrayList<>();
        featureTileChain.add(t);
        featureSegmentChain.add(s);
        Follower newFollower = s.getFollower();
        if (newFollower != null){
            followerList.add(newFollower);
        }
        breaches = 1;
    }

    /**
     * get the winner of this feature(the players with most follower in this feature)
     * @return the list of player of the winners of this feature
     */
    public ArrayList<Player> getWinner() {
        HashMap<Player, Integer> playerMap = new HashMap<Player, Integer>();
        for (Follower f : followerList){
            Integer temp = playerMap.get(f.getBoss());
            if (temp != null){
                playerMap.put(f.getBoss(), temp + 1);
            }else {
                playerMap.put(f.getBoss(), 1);
            }
        }
        int max = 0;
        for (Player p : playerMap.keySet()){
            if (playerMap.get(p) > max){
                max = playerMap.get(p);
            }
        }
        ArrayList<Player> winners = new ArrayList<Player>();
        for(Player p : playerMap.keySet()){
            if (playerMap.get(p) == max){
                winners.add(p);
            }
        }
        return winners;
    }

    /**
     * get followerlist
     * @return arraylisit of follower
     */
    public ArrayList<Follower> getFollowerList(){
        return followerList;
    };

    /**
     * get the number of breaches of this feature
     * @return integer of the number of breaches
     */
    public int getBreaches(){
        return breaches;
    }

    /**
     * set the score to all the players involved in this feature
     */
    public void setScore(){

    }

    /**
     * set score for uncomplete features
     */
    public void incompleteSetScore(){

    }

    /**
     * set the breaches of this feature
     * @param newBreaches the new number of breaches to set
     */
    public void setBreaches(int newBreaches){
        breaches = newBreaches;
    }

    /**
     * sub number of breach by one
     */
    public void subBreachOne(){
        breaches = breaches - 1;
    }

    /**
     * add tile to the chain in the feature
     * @param t the tile to insert to the feature
     * @param s the segment to insert to the segemnt chain
     */
    public void addToChain(Tile t, Segment s){
        featureTileChain.add(t);
        featureSegmentChain.add(s);
        Follower newFollower = s.getFollower();
        if (newFollower != null){
            followerList.add(newFollower);
        }
        breaches = breaches - 1;
    }

    /**
     * get all the segment in this feature
     * @return array list of segment of all the segment in this feature
     */
    public ArrayList<Segment> getFeatureSegmentChain() {
        return featureSegmentChain;
    }

    /**
     * get all the tiles in this feature
     * @return array list of tiles of all the tiles in this feature
     */
    public ArrayList<Tile> getFeatureTileChain() {
        return featureTileChain;
    }

    /**
     * check whether this feature contain this segment
     * @param s the segment to search for
     * @return it does contain the segment or not
     */
    public boolean containSegment(Segment s) {
        for (Segment tempS : featureSegmentChain){
            if (s == tempS){
                return true;
            }
        }
        return false;
    }

    /**
     * append one feature to another
     * @param f the feature to append to this feature
     */
    public void appendFeature(Feature f) {
        featureTileChain.addAll(f.getFeatureTileChain());
        featureSegmentChain.addAll(f.getFeatureSegmentChain());
        followerList.addAll(f.getFollowerList());
        // no sure this part
        // feature + feature + tile - 2 = feature + feature - 1
        breaches = breaches + f.getBreaches() - 1;

    }
}
