package edu.cmu.cs.cs214.hw4.core;

import edu.cmu.cs.cs214.hw4.core.segmentpackage.Segment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * the Tile class
 * describe how a tile is presented
 * northSegment: the segment list that contain all the segments that's on the north side of the tile
 * westSegment: the segment list that contain all the segments that's on the west side of the tile
 * southSegment: the segment list that contain all the segments that's on the south side of the tile
 * eastSegment: the segment list that contain all the segments that's on the east side of the tile
 * cloisterSegment: the segment for cloister segment
 * neighbors: the segment list that contains all the neighbors from all eight direction of the tile
 */
public class Tile {
    private String name;
    private ArrayList<Segment> northSegments;
    private ArrayList<Segment> westSegments;
    private ArrayList<Segment> southSegments;
    private ArrayList<Segment> eastSegments;
    private Segment cloisterSegment;
    private HashMap<Directions, Tile> neighbors;
    private int tileID;

    /**
     * Tile constructor initialize all private segments and neighbors
     */
    public Tile (){
        northSegments = new ArrayList<Segment>();
        westSegments = new ArrayList<Segment>();
        southSegments = new ArrayList<Segment>();
        eastSegments = new ArrayList<Segment>();
        cloisterSegment = null;
        neighbors = new HashMap<Directions, Tile>();
    }

    /**
     * set tile id
     * @param tileID the id to set to the tile
     */
    public void setTileID(int tileID) {
        this.tileID = tileID;
    }

    /**
     * get the id of the tile
     * @return tile id of int
     */
    public int getTileID() {
        return tileID;
    }

    /**
     * set up to eight neighbor tiles into the tile
     * @param neighbors the neighbors of the tile to set for the tile
     */
    public void setNeighbors(HashMap<Directions, Tile> neighbors) {
        this.neighbors = neighbors;
    }

    /**
     * add a tile to this tile's neighbor
     * @param direction the direction this neighbor is at
     * @param t the neighbor tile
     */
    public void addNeighbors(Directions direction, Tile t){
        if (neighbors.get(direction) != null) {
            System.out.println("warning!! add duplicate neighbor");
        }
        neighbors.put(direction, t);
    }

    /**
     * set the four direction segment list of a tile
     * @param northSegments north segment list
     * @param westSegments west segment list
     * @param southSegments south segment list
     * @param eastSegments east segment list
     */
    public void setFourDirectionSegments(ArrayList<Segment> northSegments, ArrayList<Segment> westSegments,
                                         ArrayList<Segment> southSegments,ArrayList<Segment> eastSegments){
        this.northSegments = northSegments;
        this.westSegments = westSegments;
        this.southSegments = southSegments;
        this.eastSegments = eastSegments;
    }

    /**
     * set name of the tile
     * @param name the name
     */
    public  void setName(String name){
        this.name = name;
    }

    /**
     * get name of the tile
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * set array of segments of north direction
     * @param northSegments the segments to set
     */
    public void setNorthSegments(ArrayList<Segment> northSegments){
        this.northSegments = northSegments;
    }
    /**
     * set array of segments of west direction
     * @param westSegments the segments to set
     */
    public void setWestSegments(ArrayList<Segment> westSegments) {
        this.westSegments = westSegments;
    }

    /**
     * set array of segments of south direction
     * @param southSegments the segments to set
     */
    public void setSouthSegments(ArrayList<Segment> southSegments){
        this.southSegments = southSegments;
    }

    /**
     * set array of segments of east direction
     * @param eastSegments the segments to set
     */
    public void setEastSegments(ArrayList<Segment> eastSegments){
        this.eastSegments = eastSegments;
    }

    /**
     * get north segments
     * @return northSegments
     */
    public ArrayList<Segment> getNorthSegments(){
        return northSegments;
    }

    /**
     * get east segments
     * @return eastSegments
     */
    public ArrayList<Segment> getEastSegments() {
        return eastSegments;
    }

    /**
     * get south segments
     * @return southSegments
     */
    public ArrayList<Segment> getSouthSegments() {
        return southSegments;
    }

    /**
     * get west segments
     * @return westSegments
     */
    public ArrayList<Segment> getWestSegments() {
        return westSegments;
    }

    /**
     * set the cloister segment of this tile
     * @param cloisterSegment cloisterSegment
     */
    public void setCloisterSegment(Segment cloisterSegment) {
        this.cloisterSegment = cloisterSegment;
    }

    /**
     * rotate the tile clockwise
     * @param rotateDirction the rotateDirction, "clockwise" or "counterClockwise"
     */
    public void rotateTile (String rotateDirction) {
        ArrayList<Segment> temp;
        if (rotateDirction.equals("clockwise")) {
            temp = (ArrayList<Segment>) northSegments.clone();
            northSegments = (ArrayList<Segment>) westSegments.clone();
            westSegments = (ArrayList<Segment>) southSegments.clone();
            southSegments = (ArrayList<Segment>) eastSegments.clone();
            eastSegments = temp;
            HashMap<Directions, Tile> newNeighbors = new HashMap<Directions, Tile>();
            for (Map.Entry<Directions, Tile> entry : neighbors.entrySet()) {
                Directions key = entry.getKey();
                Tile value = entry.getValue();
                if (key.equals(Directions.north)) {
                    newNeighbors.put(Directions.east, value);

                } else if (key.equals(Directions.west)) {
                    newNeighbors.put(Directions.north, value);

                } else if (key.equals(Directions.south)) {
                    newNeighbors.put(Directions.west, value);

                } else if (key.equals(Directions.east)) {
                    newNeighbors.put(Directions.south, value);

                } else if (key.equals(Directions.northwest)) {
                    newNeighbors.put(Directions.northeast, value);

                } else if (key.equals(Directions.northeast)) {
                    newNeighbors.put(Directions.southeast, value);

                } else if (key.equals(Directions.southwest)) {
                    newNeighbors.put(Directions.northwest, value);

                } else if (key.equals(Directions.southeast)) {
                    newNeighbors.put(Directions.southwest, value);

                } else {
                    throw new IllegalArgumentException("rotate clockwise error. key is " + key);
                }
                neighbors = newNeighbors;
            }
        }else if (rotateDirction.equals("counterClockwise")) {
            throw new IllegalArgumentException("no supported rotateTile counterClockwise ");
        }else {
            throw new IllegalArgumentException("rotateTile method's argument should be clockwise or counterClockwise");
        }
    }

    /**
     * get the segment list of one of the four direction
     * @param direction "north" or "west" or "south" or "east"
     * @return arraylist of the segments in the direction of the tile
     */
    public ArrayList<Segment> getFourDirectionSegments (String direction) {
        if (direction.equals("north")) {
            return northSegments;
        }else if (direction.equals("west")) {
            return westSegments;
        }else if (direction.equals("south")) {
            return southSegments;
        }else if (direction.equals("east")) {
            return eastSegments;
        }else {
            throw new IllegalArgumentException("getFourDirectionSegments wrong argument");
        }
    }

    /**
     * getCloisterSegment. If there's no cloisterSegment return null
     * @return the getCloisterSegment in this tile, if there's no cloisterSegment return null
     */
    public Segment getCloisterSegment () {
        return cloisterSegment;
    }

    /**
     * getNeighbors
     * @return HashMap<String, Tile> neighbors
     */
    public HashMap<Directions, Tile> getNeighbors() {
        return neighbors;
    }
}
