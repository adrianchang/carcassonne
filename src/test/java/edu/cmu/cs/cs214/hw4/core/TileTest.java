package edu.cmu.cs.cs214.hw4.core;

import edu.cmu.cs.cs214.hw4.core.segmentpackage.Segment;
import edu.cmu.cs.cs214.hw4.core.segmentpackage.CitySegmentImpl;
import edu.cmu.cs.cs214.hw4.core.segmentpackage.CloisterSegmentImpl;
import edu.cmu.cs.cs214.hw4.core.segmentpackage.FieldSegmentImpl;
import edu.cmu.cs.cs214.hw4.core.segmentpackage.RoadSegmentImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class TileTest {
    Tile t;
    Segment city = new CitySegmentImpl();
    Segment road = new RoadSegmentImpl();
    Segment field = new FieldSegmentImpl();
    Segment field2 = new FieldSegmentImpl();
    Segment cloister = new CloisterSegmentImpl();
    ArrayList<Segment> north = new ArrayList<Segment>();
    ArrayList<Segment> south = new ArrayList<Segment>();
    ArrayList<Segment> east = new ArrayList<Segment>();
    ArrayList<Segment> west = new ArrayList<Segment>();
    HashMap<Directions, Tile> newNeighbors = new HashMap<Directions, Tile>();
    @Before
    public void setUpATileAndNeighbors (){
        t = new Tile();
        Tile t1 = new Tile();
        Tile t2 = new Tile();
        Tile t3 = new Tile();
        Tile t4 = new Tile();
        Tile t5 = new Tile();
        Tile t6 = new Tile();
        Tile t7 = new Tile();
        Tile t8 = new Tile();
        north.add(city);
        west.add(city);
        south.add(field);
        south.add(field2);
        south.add(road);
        east.add(field);
        east.add(field2);
        east.add(road);
        t.setFourDirectionSegments(north, west, south, east);
        HashMap<Directions, Tile> neighbor = new HashMap<Directions, Tile>();
        neighbor.put(Directions.north, t1);
        neighbor.put(Directions.northwest, t2);
        neighbor.put(Directions.west, t3);
        neighbor.put(Directions.southwest, t4);
        neighbor.put(Directions.south, t5);
        neighbor.put(Directions.southeast, t6);
        neighbor.put(Directions.east, t7);
        neighbor.put(Directions.northeast, t8);
        t.setNeighbors(neighbor);
        t.setCloisterSegment(cloister);
        newNeighbors.put(Directions.east, t1);
        newNeighbors.put(Directions.north, t3);
        newNeighbors.put(Directions.west, t5);
        newNeighbors.put(Directions.south, t7);
        newNeighbors.put(Directions.northwest, t4);
        newNeighbors.put(Directions.southwest, t6);
        newNeighbors.put(Directions.southeast, t8);
        newNeighbors.put(Directions.northeast, t2);
    }

    @Test
    public void rotateTest(){
        t.rotateTile("clockwise");
        //check all direction
        Segment newEastCity = t.getFourDirectionSegments("east").get(0);
        assertEquals(newEastCity, city);
        Segment southField = t.getFourDirectionSegments("south").get(0);
        assertEquals(southField, field);
        Segment southField2 = t.getFourDirectionSegments("south").get(1);
        assertEquals(southField2, field2);
        Segment southRoad = t.getFourDirectionSegments("south").get(2);
        assertEquals(southRoad, road);
        Segment westField = t.getFourDirectionSegments("west").get(0);
        assertEquals(westField, field);
        Segment westField2 = t.getFourDirectionSegments("west").get(1);
        assertEquals(westField2, field2);
        Segment westRoad = t.getFourDirectionSegments("west").get(2);
        assertEquals(westRoad, road);
        Segment newNorthCity = t.getFourDirectionSegments("north").get(0);
        assertEquals(newNorthCity, city);
        //check all neighbors
        HashMap rotatedNeighbors = t.getNeighbors();
        assertEquals(rotatedNeighbors, newNeighbors);
        Segment newCloister = t.getCloisterSegment();
        assertEquals(newCloister, cloister);
    }

    @Test
    public void rotateTest_fourTimes() {
        t.rotateTile("clockwise");
        t.rotateTile("clockwise");
        t.rotateTile("clockwise");
        t.rotateTile("clockwise");
        assertEquals(t.getNorthSegments(), north);
        assertEquals(t.getWestSegments(), west);
        assertEquals(t.getEastSegments(), east);
        assertEquals(t.getSouthSegments(), south);
    }

    @Test
    public void neighborAddTest() {
        Tile t2 = new Tile();
        Tile t2Neighbor = new Tile();
        t2.addNeighbors(Directions.north, t2Neighbor);
        assertEquals(t2.getNeighbors().get(Directions.north), t2Neighbor);
        t2.addNeighbors(Directions.north, t2Neighbor);
    }
}