package edu.cmu.cs.cs214.hw4.core;

import edu.cmu.cs.cs214.hw4.core.segmentpackage.CitySegmentImpl;
import edu.cmu.cs.cs214.hw4.core.segmentpackage.CloisterSegmentImpl;
import edu.cmu.cs.cs214.hw4.core.segmentpackage.FieldSegmentImpl;
import edu.cmu.cs.cs214.hw4.core.segmentpackage.RoadSegmentImpl;
import edu.cmu.cs.cs214.hw4.core.tileloaderpackage.AllTileLoader;
import edu.cmu.cs.cs214.hw4.core.tileloaderpackage.LoadAllTile;
import edu.cmu.cs.cs214.hw4.core.tileloaderpackage.LoadYml;
import edu.cmu.cs.cs214.hw4.core.tileloaderpackage.TileLoader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoadAllTileTest {
    AllTileLoader atl1;
    AllTileLoader wholeAtl;
    TileLoader lt1;
    TileLoader lt2;
    TileLoader lt3;
    @Before
    public void setUpAllTileLoader_LoddingthreeTiles_InOneFile () {
        LoadAllTile loader = new LoadAllTile();
        atl1 = loader.parse("test_tiles/loadAllTileTest1.yml");
        lt1 = atl1.getTileLoaders().get(0);
        lt2 = atl1.getTileLoaders().get(1);
        lt3 = atl1.getTileLoaders().get(2);
        wholeAtl = loader.parse("game_tiles/loadAllTile.yml");

    }
    @Test
    public void checkTest1 () {
        assertEquals(atl1.getTileLoaders().size(), 3);
    }
    @Test
    public void checkTileLoader_ATile_Test(){
        assertEquals(lt1.getEastSegments(), lt1.getSouthSegments());
        assertEquals(lt1.getNorthSegments(), lt1.getWestSegments());
        assertEquals(((CitySegmentImpl)(lt1.getNorthSegments().get(0))).getPennant(), true);
        assertEquals(((CitySegmentImpl)(lt1.getWestSegments().get(0))).getPennant(), true);
    }

    @Test
    public void checkTileLoader_BTile_Test(){
        assertEquals((lt2.getEastSegments().get(0) instanceof CitySegmentImpl), true);
        assertEquals(lt2.getNorthSegments(), lt2.getSouthSegments());
        assertEquals((lt2.getNorthSegments().get(0) instanceof FieldSegmentImpl), true);
        assertEquals((lt2.getNorthSegments().get(1) instanceof FieldSegmentImpl), true);
        assertEquals((lt2.getNorthSegments().get(2) instanceof RoadSegmentImpl), true);
        assertEquals((lt2.getNorthSegments().get(0) == lt2.getNorthSegments().get(1)), false);
    }

    @Test
    public void checkTileLoader_CTile_Test(){
        assertEquals((lt3.getEastSegments().get(0) instanceof CitySegmentImpl), true);
        assertEquals(lt3.getNorthSegments(), lt3.getWestSegments());
        assertEquals(lt3.getEastSegments(), lt3.getWestSegments());
        assertEquals((lt3.getSouthSegments().get(0) instanceof FieldSegmentImpl), true);
        assertEquals((lt3.getSouthSegments().get(1) instanceof FieldSegmentImpl), true);
        assertEquals((lt3.getSouthSegments().get(2) instanceof RoadSegmentImpl), true);
        assertEquals((lt3.getSouthSegments().get(0) == lt3.getSouthSegments().get(1)), false);
    }

    @Test
    public void checkTileConverted_ATile_Test(){
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
    public void checkTileConverted_BTile_Test(){
        Tile t2 = lt2.convertToTile();
        assertEquals(t2.getNorthSegments(), t2.getSouthSegments());
        assertEquals(t2.getNorthSegments().get(0) instanceof FieldSegmentImpl, true);
        assertEquals(t2.getNorthSegments().get(1) instanceof FieldSegmentImpl, true);
        assertEquals(t2.getNorthSegments().get(2) instanceof RoadSegmentImpl, true);
        assertEquals(t2.getWestSegments().get(0) instanceof FieldSegmentImpl, true);
        assertEquals(t2.getEastSegments().get(0) instanceof CitySegmentImpl, true);
    }

    @Test
    public void checkTileConverted_CTile_Test(){
        Tile t3 = lt3.convertToTile();
        assertEquals(t3.getNorthSegments(), t3.getEastSegments());
        assertEquals(t3.getWestSegments(), t3.getEastSegments());
        assertEquals(t3.getNorthSegments().get(0) instanceof CitySegmentImpl, true);
        assertEquals(t3.getSouthSegments().get(0) instanceof FieldSegmentImpl, true);
        assertEquals(t3.getSouthSegments().get(1) instanceof FieldSegmentImpl, true);
        assertEquals(t3.getSouthSegments().get(2) instanceof RoadSegmentImpl, true);
    }

    @Test
    public void checkTileConverted_real(){
        LoadYml loader = new LoadYml();
        TileLoader tl4 = loader.parse("test_tiles/fourEdgeField_Cloister.yml");
        Tile t4 = tl4.convertToTile();
        assertEquals(t4.getSouthSegments().get(0), t4.getSouthSegments().get(2));
    }

    @Test
    public void testTileRotateFunction () {
        Tile t4 = lt3.convertToTile();
        t4.rotateTile("clockwise");
        t4.rotateTile("clockwise");
        t4.rotateTile("clockwise");
        t4.rotateTile("clockwise");
        assertEquals(t4.getNorthSegments(), lt3.convertToTile().getNorthSegments());
        assertEquals(t4.getWestSegments(), lt3.convertToTile().getWestSegments());
        assertEquals(t4.getEastSegments(), lt3.convertToTile().getEastSegments());
        assertEquals(t4.getSouthSegments(), lt3.convertToTile().getSouthSegments());
    }

    @Test
    public void checkWholeTileTest () {
        assertEquals(wholeAtl.getTileLoaders().size(), 24);
        TileLoader firstTL = wholeAtl.getTileLoaders().get(0);
        TileLoader lastTL = wholeAtl.getTileLoaders().get(23);
        assertEquals(lastTL.getNorthSegments().get(2), lastTL.getEastSegments().get(0));
        assertEquals(lastTL.getEastSegments().get(2), lastTL.getSouthSegments().get(0));
        assertEquals(lastTL.getSouthSegments().get(2), lastTL.getWestSegments().get(0));
        assertEquals(lastTL.getWestSegments().get(2), lastTL.getNorthSegments().get(0));
        Tile firstTile = firstTL.convertToTile();
        Tile lastTile = lastTL.convertToTile();
        assertEquals(lastTile.getEastSegments().get(0) instanceof FieldSegmentImpl, true);
        assertEquals(lastTile.getEastSegments().get(1) instanceof RoadSegmentImpl, true);
        assertEquals(lastTile.getEastSegments().get(2) instanceof FieldSegmentImpl, true);
        assertEquals(firstTile.getCloisterSegment() instanceof CloisterSegmentImpl, true);
    }
}