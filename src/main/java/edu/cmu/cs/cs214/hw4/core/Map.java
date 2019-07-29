package edu.cmu.cs.cs214.hw4.core;

import edu.cmu.cs.cs214.hw4.core.featurepackage.Feature;
import edu.cmu.cs.cs214.hw4.core.featurepackage.FieldFeatureExtend;
import edu.cmu.cs.cs214.hw4.core.segmentpackage.Segment;
import edu.cmu.cs.cs214.hw4.core.tileloaderpackage.XYCoordinate;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * the current map of the game
 */
public class Map {
    private HashMap<XYCoordinate, Tile> currentMap;
    private ArrayList<XYCoordinate> territory;
    private AllFeatures allFeature;

    /**
     * constructor of map
     * initialize currentMap, border
     */
    Map () {
        currentMap = new HashMap<XYCoordinate, Tile>();
        territory = new ArrayList<XYCoordinate>(); // contain border and some currentmap
        XYCoordinate initPosition = new XYCoordinate(0, 0);
        territory.add(initPosition);
        allFeature = new AllFeatures();
    }

    /**
     * get all feature
     * @return AllFeatures allFeature
     */
    public AllFeatures getAllFeature() {
        return allFeature;
    }

    /**
     * get current map
     * @return a hashmap<XYCoordinate, tile> current map
     */
    public HashMap<XYCoordinate, Tile> getCurrentMap() {
        return currentMap;
    }

    /**
     * check if the tile is on territory(include border and current map)
     * @param x x coordinate
     * @param y y coordinate
     * @return statement is true or not
     */
    private boolean onTerritory(int x, int y){
        XYCoordinate xy = new XYCoordinate(x, y);
        return territory.contains(xy);
    }


    /**
     * check if the tile is on current map
     * @param x x coordinate
     * @param y y coordinate
     * @return statement is true or not
     */
    private boolean onMap (int x, int y){
        XYCoordinate xy = new XYCoordinate(x, y);
        return currentMap.containsKey(xy);
    }

    /**
     * update map to new map with one new added tile
     * @param x x coordinate
     * @param y y coordinate
     * @param t the tile to place on map
     */
    private void updateMap(int x, int y, Tile t){
        XYCoordinate xy = new XYCoordinate(x, y);
        currentMap.put(xy, t);
    }

    /**
     * expand territory with the new tile and it's position x y
     * @param x x coordinate
     * @param y y coordinate
     */
    private void updateTerritory(int x, int y){
        XYCoordinate xy = new XYCoordinate(x, y);
        XYCoordinate xy1 = new XYCoordinate(x + 1, y);
        XYCoordinate xy2 = new XYCoordinate(x, y + 1);
        XYCoordinate xy3 = new XYCoordinate(x - 1, y);
        XYCoordinate xy4 = new XYCoordinate(x, y - 1);
        territory.remove(xy);
        territory.add(xy1);
        territory.add(xy2);
        territory.add(xy3);
        territory.add(xy4);
    }

    /**
     * get the neighbors of the new tile that we want to place
     * @param x the new tile's x coordinate
     * @param y the new tile's y coordinate
     * @return HashMap<Directions, Tile> of neighbors
     */
    private HashMap<Directions, Tile>  getNewTileNeighbors (int x, int y) {
        HashMap<Directions, Tile> neighbors = new HashMap<Directions, Tile>();
        Tile neighborT;
        //East neighbor
        XYCoordinate xy = new XYCoordinate(x + 1, y);
        neighborT = currentMap.get(xy);
        if(neighborT != null ){
            neighbors.put(Directions.east, neighborT);
        }
        //West neighbor
        xy = new XYCoordinate(x - 1, y);
        neighborT = currentMap.get(xy);
        if(neighborT != null ){
            neighbors.put(Directions.west, neighborT);
        }
        //North neighbor
        xy = new XYCoordinate(x, y + 1);
        neighborT = currentMap.get(xy);
        if(neighborT != null ){
            neighbors.put(Directions.north, neighborT);
        }
        //South neighbor
        xy = new XYCoordinate(x, y - 1);
        neighborT = currentMap.get(xy);
        if(neighborT != null ){
            neighbors.put(Directions.south, neighborT);
        }
        //North East neighbor
        xy = new XYCoordinate(x + 1, y + 1);
        neighborT = currentMap.get(xy);
        if(neighborT != null ){
            neighbors.put(Directions.northeast, neighborT);
        }
        //North West neighbor
        xy = new XYCoordinate(x - 1, y + 1);
        neighborT = currentMap.get(xy);
        if(neighborT != null ){
            neighbors.put(Directions.northwest, neighborT);
        }
        //South East neighbor
        xy = new XYCoordinate(x + 1, y - 1);
        neighborT = currentMap.get(xy);
        if(neighborT != null ){
            neighbors.put(Directions.southeast, neighborT);
        }
        //South West neighbor
        xy = new XYCoordinate(x - 1, y - 1);
        neighborT = currentMap.get(xy);
        if(neighborT != null ){
            neighbors.put(Directions.southwest, neighborT);
        }
        return neighbors;
    }


    /**
     * check whether the tile's segment match their neighbors segments type and add the segments to right feaeture
     * @param t the new tile
     * @param neighbors the tile's neighbors
     * @return
     */
    private boolean tileMatch(Tile t,  HashMap<Directions, Tile> neighbors) {
        //north
        ArrayList<Segment> northAS = t.getNorthSegments();
        Tile northNeighbor = neighbors.get(Directions.north);
        if (northNeighbor != null) {
            int tempSize = northNeighbor.getSouthSegments().size();
            if (northAS.size() != tempSize){
                return false;
            }
            for (int i = 0; i < northAS.size(); i++){
                if (!(northAS.get(i).getType().equals(northNeighbor.getSouthSegments().get(tempSize - i - 1).getType()))){
                    return false;
                }
            }
        }
        //east
        ArrayList<Segment> eastAS = t.getEastSegments();
        Tile eastNeighbor = neighbors.get(Directions.east);
        if (eastNeighbor != null){
            int tempSize = eastNeighbor.getWestSegments().size();
            if (eastAS.size() != tempSize){
                return false;
            }
            for (int i = 0; i < eastAS.size(); i++){
                if (!(eastAS.get(i).getType().equals(eastNeighbor.getWestSegments().get(tempSize - i - 1).getType()))){
                    return false;
                }
            }
        }
        //west
        ArrayList<Segment> westAS = t.getWestSegments();
        Tile westNeighbor = neighbors.get(Directions.west);
        if (westNeighbor != null){
            int tempSize = westNeighbor.getEastSegments().size();
            if (westAS.size() != tempSize){
                return false;
            }
            for (int i = 0; i < westAS.size(); i++){
                if (!(westAS.get(i).getType().equals(westNeighbor.getEastSegments().get(tempSize - i - 1).getType()))){
                    return false;
                }
            }
        }
        //south
        ArrayList<Segment> southAS = t.getSouthSegments();
        Tile southNeighbor = neighbors.get(Directions.south);
        if (southNeighbor != null){
            int tempSize = southNeighbor.getNorthSegments().size();
            if (southAS.size() != tempSize){
                return false;
            }
            for (int i = 0; i < southAS.size(); i++){
                if (!(southAS.get(i).getType().equals(southNeighbor.getNorthSegments().get(tempSize - i - 1).getType()))){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * add the new tile's neighbors and update neighbors
     * @param x x coordinate
     * @param y y coordinate
     * @param t new tile
     * @param neighbors the list of the neighors of the new tile
     * @param firstTime first time setting neighbors
     */
    private void addNeighbors(int x, int y, Tile t, HashMap<Directions, Tile> neighbors, boolean firstTime){
        for (java.util.Map.Entry<Directions, Tile> entry : neighbors.entrySet()) {
            Directions d = entry.getKey();
            Tile neighborT = entry.getValue();
            t.addNeighbors(d, neighborT);
            if (firstTime){
                HashMap<Directions, Tile> neighborNeighbor = new HashMap<Directions, Tile>();
                switch (d){
                    case east:
                        neighborNeighbor.put(Directions.west, t);
                        addNeighbors(x - 1, y, neighborT, neighborNeighbor, false);
                        break;
                    case west:
                        neighborNeighbor.put(Directions.east, t);
                        addNeighbors(x + 1, y, neighborT, neighborNeighbor, false);
                        break;
                    case north:
                        neighborNeighbor.put(Directions.south, t);
                        addNeighbors(x, y + 1, neighborT, neighborNeighbor, false);
                        break;
                    case south:
                        neighborNeighbor.put(Directions.north, t);
                        addNeighbors(x, y - 1, neighborT, neighborNeighbor, false);
                        break;
                    case northeast:
                        neighborNeighbor.put(Directions.southwest, t);
                        addNeighbors(x + 1, y + 1, neighborT, neighborNeighbor, false);
                        break;
                    case northwest:
                        neighborNeighbor.put(Directions.southeast, t);
                        addNeighbors(x - 1, y + 1, neighborT, neighborNeighbor, false);
                        break;
                    case southeast:
                        neighborNeighbor.put(Directions.northwest, t);
                        addNeighbors(x + 1, y - 1, neighborT, neighborNeighbor, false);
                        break;
                    case southwest:
                        neighborNeighbor.put(Directions.northeast, t);
                        addNeighbors(x - 1, y - 1, neighborT, neighborNeighbor, false);
                        break;
                    default:
                        throw new IllegalArgumentException("there's an error in addNeighbors");
                }
            }
        }
    }

    private boolean hasEnemyFollowerSingleEdge (ArrayList<Segment> sl, ArrayList<Segment> nsl){
        int slSize = sl.size();
        for (int i = 0; i < slSize; i++){
            Follower tileFollower = sl.get(i).getFollower();
            if (tileFollower != null){
                Segment neighborSegment = nsl.get(slSize - i - 1);
                ArrayList<Follower> neighborFeatureFollowerList = allFeature.featureHasSegment(neighborSegment)
                        .getFollowerList();
                if (neighborFeatureFollowerList.size() != 0){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasEnemyFollower (Tile t, HashMap<Directions, Tile> tileNeighbors){
        if (tileNeighbors.get(Directions.north) != null
                && hasEnemyFollowerSingleEdge(t.getNorthSegments(), tileNeighbors.get(Directions.north).getSouthSegments()) ){
            return true;
        }
        if (tileNeighbors.get(Directions.south) != null
                && hasEnemyFollowerSingleEdge(t.getSouthSegments(), tileNeighbors.get(Directions.south).getNorthSegments()) ){
            return true;
        }
        if (tileNeighbors.get(Directions.east) != null
                && hasEnemyFollowerSingleEdge(t.getEastSegments(), tileNeighbors.get(Directions.east).getWestSegments()) ){
            return true;
        }
        if (tileNeighbors.get(Directions.west) != null
                && hasEnemyFollowerSingleEdge(t.getWestSegments(), tileNeighbors.get(Directions.west).getEastSegments()) ){
            return true;
        }
        return false;
    }

    private ArrayList<Feature> checkComplete (Tile t){
        ArrayList<Feature> completeList = new ArrayList<Feature>();
        //city feature
        for(Feature f : allFeature.getCitydFeature()){
            if (f.getBreaches() == 0){
                completeList.add(f);
            }
        }
        //road feature
        for(Feature f : allFeature.getRoadFeature()){
            if (f.getBreaches() == 0){
                completeList.add(f);
            }
        }
        //field feature
        for(Feature f : allFeature.getFieldFeature()){
            if (f.getBreaches() == 0){
                completeList.add(f);
            }
        }
        //cloister feature
        for(Feature f : allFeature.getCloisterFeature()){
            if (f.getBreaches() == 0){
                completeList.add(f);
            }
        }
        return completeList;
    }

    private void updateCloisterFeature (HashMap<Directions, Tile> tileNeighbors){
        for (Tile neighborT : tileNeighbors.values()){
            Segment neighborCloister = neighborT.getCloisterSegment();
            if(neighborCloister != null){
                allFeature.featureHasSegment(neighborCloister).subBreachOne();
            }
        }
    }

    private void setScore (ArrayList<Feature> completeList){
        for (Feature f : completeList){
            if (!(f instanceof FieldFeatureExtend) ){
                f.setScore();
                allFeature.removeCompleteFeature(f);
            }
        }
    }

    /**
     * place a tile on the current map
     * @param x the x coordinate to place the tile on
     * @param y the y coordinate to place the tile on
     * @param  t the tile to place on current map
     * @return if it's valid return true, else false.
     */
    boolean placeTileOnMap (int x, int y, Tile t) {
        HashMap<Directions, Tile> tileNeighbors = getNewTileNeighbors(x, y);
        if (!onTerritory(x, y) || onMap(x, y) || !tileMatch(t, tileNeighbors) || hasEnemyFollower(t, tileNeighbors)){
            return false;
        }
        updateMap(x, y, t);
        updateTerritory(x, y);
        addNeighbors(x, y, t, tileNeighbors, true);
        allFeature.addTileIntoFeature(t, tileNeighbors);
        updateCloisterFeature(tileNeighbors);
        ArrayList<Feature> completeList = checkComplete(t);
        setScore(completeList);
        return true;
    }

    /**
     * count the score for all remaining incomplete features
     */
    void finalAccounting (){
        allFeature.calculateIncompleteFeature();
    }


}
