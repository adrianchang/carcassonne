package edu.cmu.cs.cs214.hw4.core.tileloaderpackage;

import edu.cmu.cs.cs214.hw4.core.Tile;
import edu.cmu.cs.cs214.hw4.core.segmentpackage.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Tile loader.
 * Using snakeyaml to load and then convert to Tile
 */
public class TileLoader {
    private ArrayList<String> northSegmentsName;
    private ArrayList<String> westSegmentsName;
    private ArrayList<String> eastSegmentsName;
    private ArrayList<String> southSegmentsName;
    private String cloisterSegmentName;

    private ArrayList<Segment> northSegments = new ArrayList<Segment>();
    private ArrayList<Segment> westSegments = new ArrayList<Segment>();
    private ArrayList<Segment> eastSegments = new ArrayList<Segment>();
    private ArrayList<Segment> southSegments = new ArrayList<Segment>();
    private Segment cloisterSegment;
    private Map<String, Segment> allSegments = new LinkedHashMap<String, Segment>();

    /**
     * convert TileLoader to Tile
     * @return converted tile
     */
    public Tile convertToTile () {
        Tile newT = new Tile();
        ArrayList<Segment> northSegmentsTile = new ArrayList<Segment>();
        ArrayList<Segment> southSegmentsTile = new ArrayList<Segment>();
        ArrayList<Segment> eastSegmentsTile = new ArrayList<Segment>();
        ArrayList<Segment> westSegmentsTile = new ArrayList<Segment>();
        for (Segment seg : northSegments) {
            northSegmentsTile.add(seg);
        }
        for (Segment seg : southSegments) {
            southSegmentsTile.add(seg);
        }
        for (Segment seg : eastSegments) {
            eastSegmentsTile.add(seg);
        }
        for (Segment seg : westSegments) {
            westSegmentsTile.add(seg);
        }
        newT.setFourDirectionSegments(northSegmentsTile, westSegmentsTile, southSegmentsTile, eastSegmentsTile);
        if (cloisterSegment != null) {
            newT.setCloisterSegment(cloisterSegment);
        }
        return newT;
    }

    private Segment newSegment(String s) {
        Segment temp;
        if (s.charAt(0) == 'c') {
            temp = new CitySegmentImpl();
        }else if (s.charAt(0) == 'r') {
            temp = new RoadSegmentImpl();
        }else if (s.charAt(0) == 'f') {
            temp = new FieldSegmentImpl();
        }else if (s.charAt(0) == 'p') {
            temp = new CitySegmentImpl();
            ((CitySegmentImpl) temp).setPennant();
        }else {
            throw new IllegalArgumentException("wrong config segment type(in loading tiles)");
        }
        return temp;
    }

    private Segment searchDuplicateName (String name) {
        for (String s : allSegments.keySet()){
            if (s.equals(name)){
                return allSegments.get(s);
            }
        }
        return null;
    }

    /**
     *
     * @param s name of the cloister. ever string is fine. if there's no string that it won't set a cloister segment
     */
    public void setCloisterSegmentName(String s){
        cloisterSegmentName = s;
        if (s.equals("")) {

        }else {
            cloisterSegment = new CloisterSegmentImpl();
        }
    }

    /**
     * getCloisterSegmentName
     * @return a string of cloisterSegmentName
     */
    public String getCloisterSegmentName() {
        return cloisterSegmentName;
    }

    /**
     * set northSegmentsName
     * @param names list of names ex: c1 c2 r1 r2 p1 p2 f1 f2
     */
    public void setNorthSegmentsName(ArrayList<String> names){
        northSegmentsName = names;
        for (String s: names) {
            Segment temp = newSegment(s);
            if (searchDuplicateName(s) != null){
                temp = searchDuplicateName(s);
            }
            northSegments.add(temp);
            allSegments.put(s, temp);
        }
    }

    /**
     * set westSegmentsName
     * @param names list of names ex: c1 c2 r1 r2 p1 p2 f1 f2
     */
    public void setWestSegmentsName(ArrayList<String> names){
        westSegmentsName = names;
        for (String s: names) {
            Segment temp = newSegment(s);
            if (searchDuplicateName(s) != null){
                temp = searchDuplicateName(s);
            }
            westSegments.add(temp);
            allSegments.put(s, temp);
        }
    }

    /**
     * set eastSegmentsName
     * @param names list of names ex: c1 c2 r1 r2 p1 p2 f1 f2
     */
    public void setEastSegmentsName(ArrayList<String> names){
        eastSegmentsName = names;
        for (String s: names) {
            Segment temp = newSegment(s);
            if (searchDuplicateName(s) != null){
                temp = searchDuplicateName(s);
            }
            eastSegments.add(temp);
            allSegments.put(s, temp);
        }
    }

    /**
     * set southSegmentsName
     * @param names list of names ex: c1 c2 r1 r2 p1 p2 f1 f2
     */
    public void setSouthSegmentsName(ArrayList<String> names){
        southSegmentsName = names;
        for (String s: names) {
            Segment temp = newSegment(s);
            if (searchDuplicateName(s) != null){
                temp = searchDuplicateName(s);
            }
            southSegments.add(temp);
            allSegments.put(s, temp);
        }
    }

    /**
     * get eastSegmentsName
     * @return arraylist of string of east segments name
     */
    public ArrayList<String> getEastSegmentsName() {
        return eastSegmentsName;
    }

    /**
     * get northSegmentsName
     * @return arraylist of string of north segments name
     */
    public ArrayList<String> getNorthSegmentsName() {
        return northSegmentsName;
    }

    /**
     * get southSegmentsName
     * @return arraylist of string of south segments name
     */
    public ArrayList<String> getSouthSegmentsName() {
        return southSegmentsName;
    }

    /**
     * get westSegmentsName
     * @return arraylist of string of west segments name
     */
    public ArrayList<String> getWestSegmentsName() {
        return westSegmentsName;
    }

    /**
     * get all segments
     * @return map<String, Segment></> of all segment
     */
    public Map<String, Segment> getAllSegments() {
        return allSegments;
    }

    /**
     * get east segments
     * @return map<String, Segment> of east segments
     */
    public ArrayList<Segment> getEastSegments() {
        return eastSegments;
    }

    /**
     * get north segments
     * @return map<String, Segment> of north segments
     */
    public ArrayList<Segment> getNorthSegments() {
        return northSegments;
    }

    /**
     * get south segments
     * @return map<String, Segment> of south segments
     */
    public ArrayList<Segment> getSouthSegments() {
        return southSegments;
    }

    /**
     * get west segments
     * @return map<String, Segment> of west segments
     */
    public ArrayList<Segment> getWestSegments() {
        return westSegments;
    }

    /**
     * get cloister segments
     * @return map<String, Segment> of cloister segments
     */
    public Segment getCloisterSegment() {
        return cloisterSegment;
    }
}
