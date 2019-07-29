package edu.cmu.cs.cs214.hw4.core.segmentpackage;

import edu.cmu.cs.cs214.hw4.core.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class SegmentTest {
    @Test
    public void placeFollower_Test() {
        Segment s = new CitySegmentImpl();
        Segment s2 = new CitySegmentImpl();
        Segment s3 = new CitySegmentImpl();
        Segment s4 = new CitySegmentImpl();
        Segment s5 = new CitySegmentImpl();
        Segment s6 = new CitySegmentImpl();
        Segment s7 = new CitySegmentImpl();
        Segment s8 = new CitySegmentImpl();

        Player p1 = new Player("Adrian");

        assertEquals(s.placeFollower(p1), true);
        assertEquals(s2.placeFollower(p1), true);
        assertEquals(s3.placeFollower(p1), true);
        assertEquals(s4.placeFollower(p1), true);
        assertEquals(s5.placeFollower(p1), true);
        assertEquals(s6.placeFollower(p1), true);
        assertEquals(s7.placeFollower(p1), true);
        assertEquals(s8.placeFollower(p1), false);
    }

    @Test
    public void getFollower_Test(){
        Segment s = new CitySegmentImpl();

        Player p1 = new Player("Adrian");

        assertEquals(s.placeFollower(p1), true);

        assertEquals(s.getFollower().getBoss(), p1);
    }

    @Test
    public void getType_Test(){
        Segment s = new CitySegmentImpl();
        Segment s2 = new CloisterSegmentImpl();
        Segment s3 = new RoadSegmentImpl();
        Segment s4 = new FieldSegmentImpl();
        assertEquals(s.getType(), "city");
        assertEquals(s2.getType(), "cloister");
        assertEquals(s3.getType(), "road");
        assertEquals(s4.getType(), "field");

    }
}