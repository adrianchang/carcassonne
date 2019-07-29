package edu.cmu.cs.cs214.hw4.core;

import edu.cmu.cs.cs214.hw4.core.featurepackage.*;
import edu.cmu.cs.cs214.hw4.core.segmentpackage.Segment;
import edu.cmu.cs.cs214.hw4.core.segmentpackage.CitySegmentImpl;
import edu.cmu.cs.cs214.hw4.core.segmentpackage.CloisterSegmentImpl;
import edu.cmu.cs.cs214.hw4.core.segmentpackage.FieldSegmentImpl;
import edu.cmu.cs.cs214.hw4.core.segmentpackage.RoadSegmentImpl;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * contain all the features on the map
 * there is four list of features in this class. Thus I do use instance for routing. I don't think I could use
 * polymorphism for this
 */
public class AllFeatures {
    private ArrayList<Feature> fieldFeatures = new ArrayList<Feature>();
    private ArrayList<Feature> cityFeatures = new ArrayList<Feature>();
    private ArrayList<Feature> roadFeatures = new ArrayList<Feature>();
    private ArrayList<Feature> cloisterFeatures = new ArrayList<Feature>(); // will not add any tile to it
    private ArrayList<Feature> completeFeatures = new ArrayList<Feature>();

    /**
     * if the segment is not in all features return null, else return the feature it's in
     * @param s segment s
     * @return the feature segment s is in
     */
    Feature featureHasSegment (Segment s){
        for (Feature f : completeFeatures){
            if (f.containSegment(s)){
                return f;
            }
        }
        ArrayList<Feature> targetFeatureList;
        targetFeatureList = getFeatureListWithSegment(s);
        for (Feature f : targetFeatureList){
            if (f.containSegment(s)) {
                return f;
            }
        }
        return null;
    }

    /**
     * remove complete feature from their feature list to complete feature list
     * @param f the feature to remove
     */
     void removeCompleteFeature (Feature f){
         ArrayList<Feature> targetFeatureList;
         targetFeatureList = getFeatureListWithSegment(f.getFeatureSegmentChain().get(0));
         targetFeatureList.remove(f);
         completeFeatures.add(f);
    }

    /**
     * get the city feature
     * @return arraylist of feature of the city feature
     */
    ArrayList<Feature> getCitydFeature() {
        return cityFeatures;
    }

    /**
     * get the cloister feature
     * @return arraylist of feature of the cloister feature
     */
    ArrayList<Feature> getCloisterFeature() {
        return cloisterFeatures;
    }

    /**
     * get the field feature
     * @return arraylist of feature of the field feature
     */
    ArrayList<Feature> getFieldFeature() {
        return fieldFeatures;
    }

    /**
     * get the road feature
     * @return arraylist of feature of the road feature
     */
    ArrayList<Feature> getRoadFeature() {
        return roadFeatures;
    }

    private ArrayList<Feature> getFeatureListWithSegment(Segment s){
        //routing to the right feature list
        if (s instanceof FieldSegmentImpl){
            return fieldFeatures;
        }else if (s instanceof CitySegmentImpl){
            return cityFeatures;
        }else if (s instanceof RoadSegmentImpl){
            return roadFeatures;
        }else if (s instanceof CloisterSegmentImpl){
            return cloisterFeatures;
        }
        throw new IllegalArgumentException("there's something wrong in getFeatureWithString(), s is " + s);
    }

    private void createNewFeature(Tile t, Segment s){
        //routing to the right feature list
        if (s instanceof FieldSegmentImpl){
            fieldFeatures.add(new FieldFeatureExtend(t, s));
        }else if (s instanceof CitySegmentImpl){
            cityFeatures.add(new CityFeatureExtend(t, s));
        }else if (s instanceof RoadSegmentImpl){
            roadFeatures.add(new RoadFeatureExtend(t, s));
        }else{
            throw new IllegalArgumentException("there's something wrong in createNewFeature(), s is " + s);
        }
    }

    private void addSingleEdge(Tile t, ArrayList<Segment> tileCorrespondEdge, ArrayList<Segment> neighborCorrespondEdge) {
        if (neighborCorrespondEdge != null){
            //has neighbor
            for (int i = 0; i < tileCorrespondEdge.size(); i++){
                Segment tempS = tileCorrespondEdge.get(i);
                Feature featureOfTemps = featureHasSegment(tempS);
                Feature featureOfNeighbor = featureHasSegment(neighborCorrespondEdge.get(tileCorrespondEdge.size() - i - 1));
                if (featureOfTemps == null) {
                    featureOfNeighbor.addToChain(t, tempS);
                }else {
                    //this segment has a brother in this tile and there's a neighbor need to append to another feature
                    if (featureOfTemps != featureOfNeighbor) {
                        featureOfTemps.appendFeature(featureOfNeighbor);
                        getFeatureListWithSegment(tempS).remove(featureOfNeighbor);
                    }else {
                        //featureOfNeighbor.addToChain(t, tempS);
                        featureOfTemps.setBreaches(featureOfTemps.getBreaches() - 1);
                    }
                }
            }
        }else {
            for (int i = 0; i < tileCorrespondEdge.size(); i++){
                Segment tempS = tileCorrespondEdge.get(i);
                Feature featureOfTemps = featureHasSegment(tempS);
                if (featureOfTemps == null) {
                    //this segment is not in all feature list, it should create a new feature
                    createNewFeature(t, tempS);
                }else {
                    //this segment already belongs to a feature. and there's no need to append a feature to another one
                    featureOfTemps.setBreaches(featureOfTemps.getBreaches() + 1);
                }
            }
        }
    }

    /**
     * calculate all teh incomplete feature for clear accounting
     * and then set the score
     * it won't return followers
     */
    void calculateIncompleteFeature() {
        for (Feature f : cityFeatures){
            f.incompleteSetScore();
        }
        for (Feature f : roadFeatures){
            f.incompleteSetScore();
        }
        for (Feature f : cloisterFeatures){
            f.incompleteSetScore();
        }
        for (Feature f : fieldFeatures){
            ((FieldFeatureExtend)f).incompleteSetScore(completeFeatures);
        }
    }


    /**
     * add this tile into all the appropriate features
     * @param t the tile to add to all the features
     * @param neighbors the neighor of this new tile
     */
    void addTileIntoFeature(Tile t, HashMap<Directions, Tile> neighbors){
        //north
        Tile neighbor = neighbors.get(Directions.north);
        ArrayList<Segment> tileCorrespondEdge = t.getNorthSegments();
        ArrayList<Segment> neighborCorrespondEdge;
        if (neighbor != null){
            neighborCorrespondEdge = neighbor.getSouthSegments();
        }else {
            neighborCorrespondEdge = null;
        }
        addSingleEdge(t, tileCorrespondEdge, neighborCorrespondEdge);
        //south
        neighbor = neighbors.get(Directions.south);
        tileCorrespondEdge = t.getSouthSegments();
        if (neighbor != null){
            neighborCorrespondEdge = neighbor.getNorthSegments();
        }else {
            neighborCorrespondEdge = null;
        }
        addSingleEdge(t, tileCorrespondEdge, neighborCorrespondEdge);
        //east
        neighbor = neighbors.get(Directions.east);
        tileCorrespondEdge = t.getEastSegments();
        if (neighbor != null){
            neighborCorrespondEdge = neighbor.getWestSegments();
        }else {
            neighborCorrespondEdge = null;
        }
        addSingleEdge(t, tileCorrespondEdge, neighborCorrespondEdge);
        //west
        neighbor = neighbors.get(Directions.west);
        tileCorrespondEdge = t.getWestSegments();
        if (neighbor != null){
            neighborCorrespondEdge = neighbor.getEastSegments();
        }else {
            neighborCorrespondEdge = null;
        }
        addSingleEdge(t, tileCorrespondEdge, neighborCorrespondEdge);
        //cloister
        Segment clo = t.getCloisterSegment();
        if(clo != null){
            CloisterFeatureExtend cloisterF = new CloisterFeatureExtend(t, clo);
            cloisterFeatures.add(cloisterF);
        }
    }

}
