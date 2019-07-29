package edu.cmu.cs.cs214.hw4.core;

import edu.cmu.cs.cs214.hw4.core.featurepackage.CityFeatureExtend;
import edu.cmu.cs.cs214.hw4.core.featurepackage.Feature;
import edu.cmu.cs.cs214.hw4.core.segmentpackage.CitySegmentImpl;
import edu.cmu.cs.cs214.hw4.core.tileloaderpackage.LoadYml;
import edu.cmu.cs.cs214.hw4.core.tileloaderpackage.TileLoader;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FeatureTest {
    @Test
    public void getWinnerTest(){
        Player p = new Player("Adrian");
        Player p2 = new Player("Brian");
        LoadYml loader = new LoadYml();
        TileLoader lt1 = loader.parse("test_tiles/checkSegmentTest1.yml");
        Tile t = lt1.convertToTile();
        assertEquals(t.getNorthSegments().get(0) instanceof CitySegmentImpl, true);
        assertEquals(t.getNorthSegments().get(0).placeFollower(p), true);
        Feature f = new CityFeatureExtend(t, t.getNorthSegments().get(0));

        TileLoader lt2 = loader.parse("test_tiles/checkSegmentTest1.yml");
        Tile t2 = lt2.convertToTile();
        assertEquals(t2.getNorthSegments().get(0) instanceof CitySegmentImpl, true);
        assertEquals(t2.getNorthSegments().get(0).placeFollower(p), true);
        Feature f2 = new CityFeatureExtend(t2, t2.getNorthSegments().get(0));

        TileLoader lt3 = loader.parse("test_tiles/checkSegmentTest1.yml");
        Tile t3 = lt3.convertToTile();
        assertEquals(t3.getNorthSegments().get(0) instanceof CitySegmentImpl, true);
        assertEquals(t3.getNorthSegments().get(0).placeFollower(p2), true);
        Feature f3 = new CityFeatureExtend(t3, t3.getNorthSegments().get(0));

        TileLoader lt4 = loader.parse("test_tiles/checkSegmentTest1.yml");
        Tile t4 = lt4.convertToTile();
        assertEquals(t4.getNorthSegments().get(0) instanceof CitySegmentImpl, true);
        assertEquals(t4.getNorthSegments().get(0).placeFollower(p2), true);
        Feature f4 = new CityFeatureExtend(t4, t4.getNorthSegments().get(0));

        f.appendFeature(f2);
        f.appendFeature(f3);
        f.appendFeature(f4);
        assertEquals(f.getWinner().size(), 2);
        ArrayList<Player> tempP= new ArrayList<Player>();
        tempP.add(p2);
        tempP.add(p);
        assertEquals(f.getWinner().size(), 2);
        if (f.getWinner().get(0) == p){
            assertEquals(f.getWinner().get(0), p);
            assertEquals(f.getWinner().get(1), p2);
        }else {
            assertEquals(f.getWinner().get(0), p2);
            assertEquals(f.getWinner().get(1), p);
        }
        TileLoader lt5 = loader.parse("test_tiles/checkSegmentTest1.yml");
        Tile t5 = lt5.convertToTile();
        assertEquals(t5.getNorthSegments().get(0) instanceof CitySegmentImpl, true);
        assertEquals(t5.getNorthSegments().get(0).placeFollower(p2), true);
        Feature f5 = new CityFeatureExtend(t5, t5.getNorthSegments().get(0));

        f.appendFeature(f5);
        assertEquals(f.getWinner().size(), 1);
        assertEquals(f.getWinner().get(0), p2);
    }

}