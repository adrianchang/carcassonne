package edu.cmu.cs.cs214.hw4.core.featurepackage;

import edu.cmu.cs.cs214.hw4.core.Player;
import edu.cmu.cs.cs214.hw4.core.Tile;
import edu.cmu.cs.cs214.hw4.core.segmentpackage.CitySegmentImpl;
import edu.cmu.cs.cs214.hw4.core.segmentpackage.Segment;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * field feature object. Extended from feature class
 */
public class FieldFeatureExtend extends Feature {
    /**
     * constructor of field feature
     * @param t the first tile of this feature
     * @param s the first segment of this feature
     */
    public FieldFeatureExtend(Tile t, Segment s){
        super(t, s);
    }

    @Override
    public void setScore(){

    }

    /**
     * set score for incomplete field
     * @param completeFeatures complete features
     */
    public void incompleteSetScore(ArrayList<Feature> completeFeatures){
        if (followerList.size() == 0){
            return;
        }else {
            ArrayList<Player> winners = getWinner();
            for (Player p : winners){
                HashMap<Tile, Tile> temp = new HashMap<Tile, Tile>();
                for (Tile t : featureTileChain){
                    temp.put(t, t);
                }
                HashMap<Segment, Segment> citySegment = new HashMap<Segment, Segment>();
                for (Tile t :temp.keySet()){
                    for(Segment s : t.getNorthSegments()){
                        if (s instanceof CitySegmentImpl){
                            citySegment.put(s, s);
                        }
                    }
                    for(Segment s : t.getSouthSegments()){
                        if (s instanceof CitySegmentImpl){
                            citySegment.put(s, s);
                        }
                    }
                    for(Segment s : t.getWestSegments()){
                        if (s instanceof CitySegmentImpl){
                            citySegment.put(s, s);
                        }
                    }
                    for(Segment s : t.getEastSegments()){
                        if (s instanceof CitySegmentImpl){
                            citySegment.put(s, s);
                        }
                    }
                }
                int completeCityNum = countComplete(citySegment, completeFeatures);
                p.addScore(completeCityNum*3);
            }
        }
    }

    private boolean hasSegmentInFeature(Segment s, Feature f){
        for (Segment temps : f.getFeatureSegmentChain()){
            if (temps == s){
                return true;
            }
        }
        return false;
    }

    private int countComplete(HashMap<Segment, Segment> citySegments, ArrayList<Feature> completeFeatures){
        int counter = 0;
        for(Feature f : completeFeatures){
            if (f instanceof CityFeatureExtend){
                for (Segment s : citySegments.keySet()){
                    if (hasSegmentInFeature(s, f)){
                        counter++;
                        break;
                    }
                }
            }
        }
        return counter;
    }
}
