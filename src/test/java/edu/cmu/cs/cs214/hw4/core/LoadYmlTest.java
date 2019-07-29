package edu.cmu.cs.cs214.hw4.core;

import edu.cmu.cs.cs214.hw4.core.segmentpackage.CitySegmentImpl;
import edu.cmu.cs.cs214.hw4.core.segmentpackage.CloisterSegmentImpl;
import edu.cmu.cs.cs214.hw4.core.segmentpackage.FieldSegmentImpl;
import edu.cmu.cs.cs214.hw4.core.segmentpackage.RoadSegmentImpl;
import edu.cmu.cs.cs214.hw4.core.tileloaderpackage.LoadYml;
import edu.cmu.cs.cs214.hw4.core.tileloaderpackage.TileLoader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoadYmlTest {
    TileLoader lt1;
    TileLoader lt2;
    TileLoader lt3;
    @Before
    public void setUpATileLoader_LoddingSimpleTiles () {
        LoadYml loader = new LoadYml();
        lt1 = loader.parse("test_tiles/loadTileTest1.yml");
        lt2 = loader.parse("test_tiles/loadTileTest2.yml");
        lt3 = loader.parse("test_tiles/loadTileTest3.yml");
    }

    @Test
    public void checkTileLoader1(){
        assertEquals(lt1.getEastSegments(), lt1.getSouthSegments());
        assertEquals(lt1.getNorthSegments(), lt1.getWestSegments());
        assertEquals(((CitySegmentImpl)(lt1.getNorthSegments().get(0))).getPennant(), true);
        assertEquals(((CitySegmentImpl)(lt1.getWestSegments().get(0))).getPennant(), true);
    }

    @Test
    public void checkTileLoader2(){
        assertEquals((lt2.getEastSegments().get(0) instanceof CitySegmentImpl), true);
        assertEquals(lt2.getNorthSegments(), lt2.getSouthSegments());
        assertEquals((lt2.getNorthSegments().get(0) instanceof FieldSegmentImpl), true);
        assertEquals((lt2.getNorthSegments().get(1) instanceof FieldSegmentImpl), true);
        assertEquals((lt2.getNorthSegments().get(2) instanceof RoadSegmentImpl), true);
        assertEquals((lt2.getNorthSegments().get(0) == lt2.getNorthSegments().get(1)), false);
    }

    @Test
    public void checkTileLoader3(){
        assertEquals((lt3.getEastSegments().get(0) instanceof CitySegmentImpl), true);
        assertEquals(lt3.getNorthSegments(), lt3.getWestSegments());
        assertEquals(lt3.getEastSegments(), lt3.getWestSegments());
        assertEquals((lt3.getSouthSegments().get(0) instanceof FieldSegmentImpl), true);
        assertEquals((lt3.getSouthSegments().get(1) instanceof FieldSegmentImpl), true);
        assertEquals((lt3.getSouthSegments().get(2) instanceof RoadSegmentImpl), true);
        assertEquals((lt3.getSouthSegments().get(0) == lt3.getSouthSegments().get(1)), false);
    }

    @Test
    public void checkTileConverted1(){
        Tile t1 = lt1.convertToTile();
        assertEquals(t1.getNorthSegments(), t1.getWestSegments());
        assertEquals(t1.getSouthSegments(), t1.getEastSegments());
        assertEquals(t1.getNorthSegments().get(0) instanceof CitySegmentImpl, true);
        assertEquals(t1.getSouthSegments().get(0) instanceof FieldSegmentImpl, true);
        assertEquals(t1.getSouthSegments().get(0) == t1.getSouthSegments().get(1), false);
        assertEquals(t1.getSouthSegments().get(1) instanceof FieldSegmentImpl, true);
        assertEquals(t1.getSouthSegments().get(2) instanceof RoadSegmentImpl, true);
        assertEquals(t1.getCloisterSegment() instanceof CloisterSegmentImpl, true);
    }

    @Test
    public void checkTileConverted2(){
        Tile t2 = lt2.convertToTile();
        assertEquals(t2.getNorthSegments(), t2.getSouthSegments());
        assertEquals(t2.getNorthSegments().get(0) instanceof FieldSegmentImpl, true);
        assertEquals(t2.getNorthSegments().get(1) instanceof FieldSegmentImpl, true);
        assertEquals(t2.getNorthSegments().get(2) instanceof RoadSegmentImpl, true);
        assertEquals(t2.getWestSegments().get(0) instanceof FieldSegmentImpl, true);
        assertEquals(t2.getEastSegments().get(0) instanceof CitySegmentImpl, true);
    }

    @Test
    public void checkTileConverted3(){
        Tile t3 = lt3.convertToTile();
        assertEquals(t3.getNorthSegments(), t3.getEastSegments());
        assertEquals(t3.getWestSegments(), t3.getEastSegments());
        assertEquals(t3.getNorthSegments().get(0) instanceof CitySegmentImpl, true);
        assertEquals(t3.getSouthSegments().get(0) instanceof FieldSegmentImpl, true);
        assertEquals(t3.getSouthSegments().get(1) instanceof FieldSegmentImpl, true);
        assertEquals(t3.getSouthSegments().get(2) instanceof RoadSegmentImpl, true);
    }
}