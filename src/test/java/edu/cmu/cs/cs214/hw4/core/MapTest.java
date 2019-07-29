package edu.cmu.cs.cs214.hw4.core;

import edu.cmu.cs.cs214.hw4.core.tileloaderpackage.LoadYml;
import edu.cmu.cs.cs214.hw4.core.tileloaderpackage.TileLoader;
import edu.cmu.cs.cs214.hw4.core.tileloaderpackage.XYCoordinate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapTest {
    Map m;
    @Before
    public void setMapM () {
        m = new Map();
    }

    @Test
    public void mapTerritory_rightPlacement_Test() {
        Tile t = new Tile();
        Tile t2 = new Tile();
        Tile t3 = new Tile();
        Tile t4 = new Tile();
        Tile t5 = new Tile();
        Tile t6 = new Tile();
        Tile t7 = new Tile();
        Tile t8 = new Tile();
        Tile t9 = new Tile();
        Tile t10 = new Tile();
        Tile t11 = new Tile();
        Tile t12 = new Tile();
        Tile t13 = new Tile();
        Tile t14 = new Tile();
        Tile t15 = new Tile();
        Tile t16 = new Tile();
        Tile t17 = new Tile();
        Tile t18 = new Tile();
        Tile t19 = new Tile();
        Tile t20 = new Tile();
        //normal test
        assertEquals(m.placeTileOnMap(0, 0, t), true);
        assertEquals(m.getCurrentMap().size(), 1);
        assertEquals(m.placeTileOnMap(1, 0, t2), true);
        assertEquals(m.getCurrentMap().size(), 2);
        assertEquals(m.placeTileOnMap(2, 0, t3), true);
        assertEquals(m.getCurrentMap().size(), 3);
        assertEquals(m.placeTileOnMap(2, 1, t4), true);
        assertEquals(m.getCurrentMap().size(), 4);
        assertEquals(m.placeTileOnMap(1, 1, t5), true);
        assertEquals(m.getCurrentMap().size(), 5);
        assertEquals(m.placeTileOnMap(-1, 0, t6), true);
        assertEquals(m.getCurrentMap().size(), 6);
        assertEquals(m.placeTileOnMap(0, -1, t7), true);
        assertEquals(m.getCurrentMap().size(), 7);

        //invalid duplicate position test
        assertEquals(m.placeTileOnMap(0, 0, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);
        assertEquals(m.placeTileOnMap(1, 0, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);
        assertEquals(m.placeTileOnMap(2, 1, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);
        assertEquals(m.placeTileOnMap(2, 1, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);
        assertEquals(m.placeTileOnMap(1, 1, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);
        assertEquals(m.placeTileOnMap(-1, 0, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);
        assertEquals(m.placeTileOnMap(0, -1, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);

        //invalid territory position test
        assertEquals(m.placeTileOnMap(0, -3, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);
        assertEquals(m.placeTileOnMap(-3, 0, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);
        assertEquals(m.placeTileOnMap(-2, 1, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);
        assertEquals(m.placeTileOnMap(-2, -1, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);
        assertEquals(m.placeTileOnMap(-1, 2, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);
        assertEquals(m.placeTileOnMap(-1, -2, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);
        assertEquals(m.placeTileOnMap(0, 2, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);
        assertEquals(m.placeTileOnMap(1, 3, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);
        assertEquals(m.placeTileOnMap(1, -2, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);
        assertEquals(m.placeTileOnMap(2, 4, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);
        assertEquals(m.placeTileOnMap(2, 3, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);
        assertEquals(m.placeTileOnMap(3, 3, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);
        assertEquals(m.placeTileOnMap(2, -2, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);
        assertEquals(m.placeTileOnMap(3, 4, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);
        assertEquals(m.placeTileOnMap(3, 2, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);
        assertEquals(m.placeTileOnMap(3, -1, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);
        assertEquals(m.placeTileOnMap(4, 3, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);
        assertEquals(m.placeTileOnMap(4, 0, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);
        assertEquals(m.placeTileOnMap(4, 2, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);
        assertEquals(m.placeTileOnMap(4, 1, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);
        assertEquals(m.placeTileOnMap(5, 2, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);
        assertEquals(m.placeTileOnMap(5, 1, t2), false);
        assertEquals(m.getCurrentMap().size(), 7);

        assertEquals(m.placeTileOnMap(-2, 0, t8), true);
        assertEquals(m.placeTileOnMap(-1, 1, t9), true);
        assertEquals(m.placeTileOnMap(-1, -1, t10), true);
        assertEquals(m.placeTileOnMap(0, 1, t11), true);
        assertEquals(m.placeTileOnMap(0, -2, t12), true);
        assertEquals(m.placeTileOnMap(1, 2, t13), true);
        assertEquals(m.placeTileOnMap(1, -1, t14), true);
        assertEquals(m.placeTileOnMap(2, 2, t15), true);
        assertEquals(m.placeTileOnMap(2, -1, t16), true);
        assertEquals(m.placeTileOnMap(3, 1, t17), true);
        assertEquals(m.placeTileOnMap(3, 0, t18), true);
        assertEquals(m.placeTileOnMap(4, 2, t19), false);
        assertEquals(m.placeTileOnMap(4, 1, t20), true);
    }

    @Test
    public void tileUpdateNeighborTest(){
        Tile t = new Tile();
        Tile t2 = new Tile();
        Tile t3 = new Tile();
        Tile t4 = new Tile();
        //t1
        m.placeTileOnMap(0, 0, t);
        XYCoordinate xy = new XYCoordinate(0, 0);
        assertEquals(m.getCurrentMap().get(xy), t);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.east), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.west), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.south), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.north), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.northwest), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.northeast), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.southeast), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.southwest), null);

        //t2
        m.placeTileOnMap(-1, 0, t2);
        xy = new XYCoordinate(-1, 0);
        assertEquals(m.getCurrentMap().get(xy), t2);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.east), t);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.west), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.south), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.north), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.northwest), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.northeast), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.southeast), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.southwest), null);

        xy = new XYCoordinate(0, 0);
        assertEquals(m.getCurrentMap().get(xy), t);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.east), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.west), t2);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.south), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.north), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.northwest), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.northeast), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.southeast), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.southwest), null);

        //t3
        m.placeTileOnMap(0, 1, t3);
        xy = new XYCoordinate(0, 1);
        assertEquals(m.getCurrentMap().get(xy), t3);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.east), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.west), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.south), t);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.north), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.northwest), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.northeast), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.southeast), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.southwest), t2);

        xy = new XYCoordinate(0, 0);
        assertEquals(m.getCurrentMap().get(xy), t);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.east), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.west), t2);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.south), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.north), t3);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.northwest), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.northeast), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.southeast), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.southwest), null);

        xy = new XYCoordinate(-1, 0);
        assertEquals(m.getCurrentMap().get(xy), t2);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.east), t);
//        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.west), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.south), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.north), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.northwest), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.northeast), t3);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.southeast), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.southwest), null);

        //t4
        m.placeTileOnMap(-1, 1, t4);
        xy = new XYCoordinate(-1, 1);
        assertEquals(m.getCurrentMap().get(xy), t4);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.east), t3);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.west), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.south), t2);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.north), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.northwest), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.northeast), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.southeast), t);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.southwest), null);

        xy = new XYCoordinate(0, 1);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.east), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.west), t4);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.south), t);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.north), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.northwest), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.northeast), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.southeast), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.southwest), t2);

        xy = new XYCoordinate(0, 0);
        assertEquals(m.getCurrentMap().get(xy), t);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.east), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.west), t2);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.south), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.north), t3);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.northwest), t4);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.northeast), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.southeast), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.southwest), null);

        xy = new XYCoordinate(-1, 0);
        assertEquals(m.getCurrentMap().get(xy), t2);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.east), t);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.west), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.south), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.north), t4);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.northwest), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.northeast), t3);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.southeast), null);
        assertEquals(m.getCurrentMap().get(xy).getNeighbors().get(Directions.southwest), null);
    }

    @Test
    public void checkSegmentsMatch_FieldAndCity_Test () {
        LoadYml loader = new LoadYml();
        TileLoader lt1 = loader.parse("test_tiles/checkSegmentTest1.yml");
        TileLoader lt2 = loader.parse("test_tiles/checkSegmentTest1.yml");
        TileLoader lt3 = loader.parse("test_tiles/checkSegmentTest1.yml");
        TileLoader lt4 = loader.parse("test_tiles/checkSegmentTest1.yml");
        TileLoader lt5 = loader.parse("test_tiles/checkSegmentTest1.yml");
        Tile t1 = lt1.convertToTile();
        Tile t2 = lt2.convertToTile();
        Tile t3 = lt3.convertToTile();
        Tile t4 = lt4.convertToTile();
        Tile t5 = lt5.convertToTile();
        assertEquals(m.placeTileOnMap(0, 0, t1), true);
        assertEquals(m.placeTileOnMap(1, 0, t2), true);
        assertEquals(m.placeTileOnMap(-1, 0, t3), true);
        t4.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(0, 1, t4), true);
        t5.rotateTile("clockwise");
        t5.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(0, -1, t5), true);

    }

    @Test
    public void checkSegmentsMatch_FieldAndRoad_Test () {
        TileLoader lt1;
        LoadYml loader = new LoadYml();
        lt1 = loader.parse("test_tiles/fourIdenticalSquare.yml");
        TileLoader lt2 = loader.parse("test_tiles/fourIdenticalSquare.yml");
        TileLoader lt3 = loader.parse("test_tiles/fourIdenticalSquare.yml");
        TileLoader lt4 = loader.parse("test_tiles/fourIdenticalSquare.yml");
        TileLoader lt5 = loader.parse("test_tiles/fourIdenticalSquare.yml");
        TileLoader lt6 = loader.parse("test_tiles/fourIdenticalSquare.yml");
        TileLoader lt7 = loader.parse("test_tiles/fourIdenticalSquare.yml");
        Tile t = lt1.convertToTile();
        Tile t2 = lt2.convertToTile();
        Tile t3 = lt3.convertToTile();
        Tile t4 = lt4.convertToTile();
        Tile t5 = lt5.convertToTile();
        Tile t6 = lt6.convertToTile();
        Tile t7 = lt7.convertToTile();
        //normal test
        assertEquals(m.placeTileOnMap(0, 0, t), true);
        assertEquals(m.getCurrentMap().size(), 1);
        assertEquals(m.placeTileOnMap(1, 0, t2), true);
        assertEquals(m.getCurrentMap().size(), 2);
        assertEquals(m.placeTileOnMap(2, 0, t3), true);
        assertEquals(m.getCurrentMap().size(), 3);
        assertEquals(m.placeTileOnMap(2, 1, t4), true);
        assertEquals(m.getCurrentMap().size(), 4);
        assertEquals(m.placeTileOnMap(1, 1, t5), true);
        assertEquals(m.getCurrentMap().size(), 5);
        assertEquals(m.placeTileOnMap(-1, 0, t6), true);
        assertEquals(m.getCurrentMap().size(), 6);
        assertEquals(m.placeTileOnMap(0, -1, t7), true);
        assertEquals(m.getCurrentMap().size(), 7);
    }

    @Test
    public void checkSegmentsMatch_FieldAndCloister_MakeSureItCheckAllEdges_Test () {
        LoadYml loader = new LoadYml();
        TileLoader lt1 = loader.parse("test_tiles/fourEdgeField_Cloister.yml");
        TileLoader lt2 = loader.parse("test_tiles/fourEdgeField_Cloister.yml");
        TileLoader lt3 = loader.parse("test_tiles/fourEdgeField_Cloister.yml");
        TileLoader lt4 = loader.parse("test_tiles/fourEdgeField_Cloister.yml");
        Tile t = lt1.convertToTile();
        Tile t2 = lt2.convertToTile();
        Tile t3 = lt3.convertToTile();
        Tile t4 = lt4.convertToTile();
        assertEquals(m.placeTileOnMap(0, 0, t), true);
        assertEquals(m.getCurrentMap().size(), 1);
        assertEquals(m.placeTileOnMap(1, 0, t2), true);
        assertEquals(m.getCurrentMap().size(), 2);
        assertEquals(m.placeTileOnMap(0, 1, t3), false);
        assertEquals(m.getCurrentMap().size(), 2);
        assertEquals(m.placeTileOnMap(0, -1, t3), false);
        assertEquals(m.getCurrentMap().size(), 2);
        t3.rotateTile("clockwise");
        t3.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(0, -1, t3), true);
        assertEquals(m.getCurrentMap().size(), 3);
        t4.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(2, 0, t4), false);
        assertEquals(m.getCurrentMap().size(), 3);
        t4.rotateTile("clockwise");
        t4.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(-1, 0, t4), false);
        assertEquals(m.getCurrentMap().size(), 3);
        t4.rotateTile("clockwise");
        t4.rotateTile("clockwise");
        t4.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(1, -1, t4), true);
        assertEquals(m.getCurrentMap().size(), 4);
    }

    @Test
    public void addToAllFeature_Breaches_Test(){
        TileLoader lt1;
        LoadYml loader = new LoadYml();
        lt1 = loader.parse("test_tiles/fourIdenticalSquare.yml");
        TileLoader lt2 = loader.parse("test_tiles/fourIdenticalSquare.yml");
        TileLoader lt3 = loader.parse("test_tiles/fourIdenticalSquare.yml");
        TileLoader lt4 = loader.parse("test_tiles/fourIdenticalSquare.yml");
        TileLoader lt5 = loader.parse("test_tiles/fourIdenticalSquare.yml");
        TileLoader lt6 = loader.parse("test_tiles/fourIdenticalSquare.yml");
        TileLoader lt7 = loader.parse("test_tiles/fourIdenticalSquare.yml");
        Tile t = lt1.convertToTile();
        Tile t2 = lt2.convertToTile();
        Tile t3 = lt3.convertToTile();
        Tile t4 = lt4.convertToTile();
        Tile t5 = lt5.convertToTile();
        Tile t6 = lt6.convertToTile();
        Tile t7 = lt7.convertToTile();
        //normal test
        assertEquals(m.placeTileOnMap(0, 0, t), true);
        assertEquals(m.getCurrentMap().size(), 1);
        assertEquals(m.placeTileOnMap(1, 0, t2), true);
        assertEquals(m.getCurrentMap().size(), 2);
        assertEquals(m.placeTileOnMap(2, 0, t3), true);
        assertEquals(m.getCurrentMap().size(), 3);
        assertEquals(m.placeTileOnMap(2, 1, t4), true);
        assertEquals(m.getCurrentMap().size(), 4);
        assertEquals(m.placeTileOnMap(1, 1, t5), true);
        assertEquals(m.getCurrentMap().size(), 5);
        assertEquals(m.placeTileOnMap(-1, 0, t6), true);
        assertEquals(m.getCurrentMap().size(), 6);
        assertEquals(m.placeTileOnMap(0, -1, t7), true);
        assertEquals(m.getCurrentMap().size(), 7);

        assertEquals(m.getAllFeature().featureHasSegment(t5.getEastSegments().get(2)).getFeatureSegmentChain().size(), 4);
        assertEquals(m.getAllFeature().featureHasSegment(t5.getSouthSegments().get(2)).getFeatureSegmentChain().size(), 3);
        assertEquals(m.getAllFeature().featureHasSegment(t5.getWestSegments().get(2)).getFeatureSegmentChain().size(), 1);
        assertEquals(m.getAllFeature().featureHasSegment(t5.getNorthSegments().get(2)).getFeatureSegmentChain().size(), 2);
        //bottom right
        assertEquals(m.getAllFeature().featureHasSegment(t5.getEastSegments().get(2)).getBreaches(), 0);
        //bttom left
        assertEquals(m.getAllFeature().featureHasSegment(t5.getSouthSegments().get(2)).getBreaches(), 2);
        //top left
        assertEquals(m.getAllFeature().featureHasSegment(t5.getWestSegments().get(2)).getBreaches(), 2);
        //top right
        assertEquals(m.getAllFeature().featureHasSegment(t5.getNorthSegments().get(2)).getBreaches(), 2);

        assertEquals(m.getAllFeature().featureHasSegment(t3.getWestSegments().get(1)).getBreaches(), 0);
        assertEquals(m.getAllFeature().featureHasSegment(t3.getEastSegments().get(0)).getBreaches(), 2);
        assertEquals(m.getAllFeature().featureHasSegment(t3.getEastSegments().get(1)).getBreaches(), 1);
        assertEquals(m.getAllFeature().featureHasSegment(t3.getEastSegments().get(2)).getBreaches(), 2);

        assertEquals(m.getAllFeature().featureHasSegment(t.getSouthSegments().get(2)).getBreaches(), 2);
        assertEquals(m.getAllFeature().featureHasSegment(t.getNorthSegments().get(1)).getBreaches(), 1);

        assertEquals(m.getAllFeature().featureHasSegment(t6.getWestSegments().get(0)).getBreaches(), 2);
        assertEquals(m.getAllFeature().featureHasSegment(t6.getWestSegments().get(1)).getBreaches(), 1);
        assertEquals(m.getAllFeature().featureHasSegment(t6.getWestSegments().get(2)).getBreaches(), 2);
    }

    @Test
    public void addToAllFeature_Breaches_Hole_Test2(){
        TileLoader lt1;
        LoadYml loader = new LoadYml();
        lt1 = loader.parse("test_tiles/m.yml");
        TileLoader lt2 = loader.parse("test_tiles/t.yml");
        TileLoader lt3 = loader.parse("test_tiles/m.yml");
        TileLoader lt4 = loader.parse("test_tiles/t.yml");
        TileLoader lt5 = loader.parse("test_tiles/m.yml");
        TileLoader lt6 = loader.parse("test_tiles/t.yml");
        TileLoader lt7 = loader.parse("test_tiles/m.yml");
        TileLoader lt8 = loader.parse("test_tiles/t.yml");
        Tile t = lt1.convertToTile();
        Tile t2 = lt2.convertToTile();
        Tile t3 = lt3.convertToTile();
        Tile t4 = lt4.convertToTile();
        Tile t5 = lt5.convertToTile();
        Tile t6 = lt6.convertToTile();
        Tile t7 = lt7.convertToTile();
        Tile t8 = lt8.convertToTile();
        assertEquals(m.placeTileOnMap(0, 0, t), true);
        assertEquals(m.placeTileOnMap(-1, 0, t2), true);
        t3.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(-2, 0, t3), true);
        t4.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(-2, 1, t4), true);
        t5.rotateTile("clockwise");
        t5.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(-2, 2, t5), true);
        t6.rotateTile("clockwise");
        t6.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(-1, 2, t6), true);
        t7.rotateTile("clockwise");
        t7.rotateTile("clockwise");
        t7.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(0, 2, t7), true);
        t8.rotateTile("clockwise");
        t8.rotateTile("clockwise");
        t8.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(0, 1, t8), true);
        assertEquals(m.getAllFeature().featureHasSegment(t.getNorthSegments().get(0)).getBreaches(), 4);
    }

    @Test
    public void addToAllFeature_Cloister_Test (){
        LoadYml loader = new LoadYml();
        TileLoader lt1 = loader.parse("test_tiles/fourEdgeField_Cloister.yml");
        TileLoader lt2 = loader.parse("test_tiles/fourEdgeField_Cloister.yml");
        TileLoader lt3 = loader.parse("test_tiles/fourEdgeField_Cloister.yml");
        TileLoader lt4 = loader.parse("test_tiles/fourEdgeField_Cloister.yml");
        Tile t = lt1.convertToTile();
        Tile t2 = lt2.convertToTile();
        Tile t3 = lt3.convertToTile();
        Tile t4 = lt4.convertToTile();
        assertEquals(m.placeTileOnMap(0, 0, t), true);
        assertEquals(m.getCurrentMap().size(), 1);
        assertEquals(m.placeTileOnMap(1, 0, t2), true);
        assertEquals(m.getCurrentMap().size(), 2);
        assertEquals(m.placeTileOnMap(0, 1, t3), false);
        assertEquals(m.getCurrentMap().size(), 2);
        assertEquals(m.placeTileOnMap(0, -1, t3), false);
        assertEquals(m.getCurrentMap().size(), 2);
        t3.rotateTile("clockwise");
        t3.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(0, -1, t3), true);
        assertEquals(m.getCurrentMap().size(), 3);
        t4.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(2, 0, t4), false);
        assertEquals(m.getCurrentMap().size(), 3);
        t4.rotateTile("clockwise");
        t4.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(-1, 0, t4), false);
        assertEquals(m.getCurrentMap().size(), 3);
        t4.rotateTile("clockwise");
        t4.rotateTile("clockwise");
        t4.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(1, -1, t4), true);
        assertEquals(m.getCurrentMap().size(), 4);
        assertEquals(m.getAllFeature().featureHasSegment(t.getNorthSegments().get(0)).getBreaches(), 8);
        assertEquals(m.getAllFeature().featureHasSegment(t.getNorthSegments().get(0)), m.getAllFeature().featureHasSegment(t.getWestSegments().get(0)));
        assertEquals(m.getAllFeature().featureHasSegment(t3.getNorthSegments().get(0)), m.getAllFeature().featureHasSegment(t3.getNorthSegments().get(2)));
        assertEquals(m.getAllFeature().featureHasSegment(t.getNorthSegments().get(0)), m.getAllFeature().featureHasSegment(t3.getNorthSegments().get(0)));
        assertEquals(m.getAllFeature().getFieldFeature().size(), 1);
        assertEquals(m.getAllFeature().getRoadFeature().size(), 0);
        assertEquals(m.getAllFeature().getCitydFeature().size(), 0);
        assertEquals(m.getAllFeature().getCloisterFeature().size(), 4);
    }

    @Test
    public void addToAllFeature_Road_Test(){
        LoadYml loader = new LoadYml();
        TileLoader lt1 = loader.parse("test_tiles/twoCityTwoFieldRoad.yml");
        TileLoader lt2 = loader.parse("test_tiles/twoCityTwoFieldRoad.yml");
        TileLoader lt3 = loader.parse("test_tiles/twoCityTwoFieldRoad.yml");
        TileLoader lt4 = loader.parse("test_tiles/twoCityTwoFieldRoad.yml");
        Tile t = lt1.convertToTile();
        Tile t2 = lt2.convertToTile();
        Tile t3 = lt3.convertToTile();
        Tile t4 = lt4.convertToTile();
        assertEquals(m.placeTileOnMap(0, 0, t), true);
        assertEquals(m.getCurrentMap().size(), 1);
        t2.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(1, 0, t2), true);
        assertEquals(m.getCurrentMap().size(), 2);
        t3.rotateTile("clockwise");
        t3.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(1, -1, t3), true);
        assertEquals(m.getCurrentMap().size(), 3);
        t4.rotateTile("clockwise");
        t4.rotateTile("clockwise");
        t4.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(0, -1, t4), true);
        assertEquals(m.getCurrentMap().size(), 4);
        assertEquals(m.getAllFeature().featureHasSegment(t.getEastSegments().get(1)).getBreaches(), 0);
    }

    @Test
    public void addToAllFeature_placeFollower_twoFollowOnSameFeature_Test(){
        LoadYml loader = new LoadYml();
        TileLoader lt1 = loader.parse("test_tiles/fourEdgeField_Cloister.yml");
        TileLoader lt2 = loader.parse("test_tiles/fourEdgeField_Cloister.yml");
        TileLoader lt3 = loader.parse("test_tiles/fourEdgeField_Cloister.yml");
        TileLoader lt4 = loader.parse("test_tiles/fourEdgeField_Cloister.yml");
        Tile t = lt1.convertToTile();
        Tile t2 = lt2.convertToTile();
        Tile t3 = lt3.convertToTile();
        Tile t4 = lt4.convertToTile();
        Player p1 = new Player("Adrian");
        Follower f1 = new Follower(p1);
        Follower f2 = new Follower(p1);
        Follower f3 = new Follower(p1);
        t.getNorthSegments().get(0).placeFollower(p1);
        t2.getNorthSegments().get(0).placeFollower(p1);
        t3.getCloisterSegment().placeFollower(p1);
        assertEquals(m.placeTileOnMap(0, 0, t), true);
        assertEquals(m.getCurrentMap().size(), 1);
        assertEquals(m.placeTileOnMap(1, 0, t2), false);
        assertEquals(m.getCurrentMap().size(), 1);
    }

    @Test
    public void addToAllFeature_hasEnemyFollower_NormalCase_Test(){
        LoadYml loader = new LoadYml();
        TileLoader lt1 = loader.parse("test_tiles/fourEdgeField_Cloister.yml");
        TileLoader lt2 = loader.parse("test_tiles/fourEdgeField_Cloister.yml");
        TileLoader lt3 = loader.parse("test_tiles/fourEdgeField_Cloister.yml");
        TileLoader lt4 = loader.parse("test_tiles/fourEdgeField_Cloister.yml");
        Tile t = lt1.convertToTile();
        Tile t2 = lt2.convertToTile();
        Tile t3 = lt3.convertToTile();
        Tile t4 = lt4.convertToTile();
        Player p1 = new Player("Adrian");
        Player p2 = new Player("Brian");
        Follower f1 = new Follower(p1);
        Follower f2 = new Follower(p2);
        Follower f3 = new Follower(p1);
        Follower f4 = new Follower(p2);
        t.getNorthSegments().get(0).placeFollower(p1);
        t2.getNorthSegments().get(0).placeFollower(p2);
        t3.getCloisterSegment().placeFollower(p1);
        t4.getSouthSegments().get(1).placeFollower(p2);
        assertEquals(m.placeTileOnMap(0, 0, t), true);
        assertEquals(m.getCurrentMap().size(), 1);
        assertEquals(m.placeTileOnMap(1, 0, t2), false);
        assertEquals(m.getCurrentMap().size(), 1);
        assertEquals(m.placeTileOnMap(1, 0, t3), true);
        assertEquals(m.getCurrentMap().size(), 2);
        assertEquals(m.placeTileOnMap(0, -1, t2), false);
        assertEquals(m.getCurrentMap().size(), 2);
        assertEquals(m.placeTileOnMap(-1, 0, t2), false);
        assertEquals(m.getCurrentMap().size(), 2);
        assertEquals(m.placeTileOnMap(0, 1, t2), false);
        assertEquals(m.getCurrentMap().size(), 2);
    }

    @Test
    public void addToAllFeature_hasEnemyFollower_TwoFollowerOnONeFeature_Test(){
        LoadYml loader = new LoadYml();
        TileLoader lt1 = loader.parse("test_tiles/fourEdgeField_Cloister.yml");
        TileLoader lt2 = loader.parse("test_tiles/e.yml");
        TileLoader lt3 = loader.parse("test_tiles/e.yml");
        TileLoader lt4 = loader.parse("test_tiles/m.yml");
        Tile t = lt1.convertToTile();
        Tile t2 = lt2.convertToTile();
        Tile t3 = lt3.convertToTile();
        Tile t4 = lt4.convertToTile();
        Player p1 = new Player("Adrian");
        Player p2 = new Player("Brian");
        Follower f1 = new Follower(p1);
        Follower f2 = new Follower(p2);
        t2.getNorthSegments().get(0).placeFollower(p1);
        t3.getNorthSegments().get(0).placeFollower(p2);
        t.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(0, 0, t), true);
        assertEquals(m.getCurrentMap().size(), 1);
        t2.rotateTile("clockwise");
        t2.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(1, 0, t2), true);
        assertEquals(m.getCurrentMap().size(), 2);
        t3.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(0, -1, t3), true);
        assertEquals(m.getCurrentMap().size(), 3);
        assertEquals(m.placeTileOnMap(1, -1, t4), true);
        assertEquals(m.getCurrentMap().size(), 4);
        assertEquals(m.getAllFeature().featureHasSegment(t4.getNorthSegments().get(0)).getFollowerList().size(), 2);
        //test City score
        assertEquals(p1.getScore(), 8);
        assertEquals(p2.getScore(), 8);
    }

    @Test
    public void checkScore_City_ShareScore_Test(){
        LoadYml loader = new LoadYml();
        TileLoader lt1 = loader.parse("test_tiles/e.yml");
        TileLoader lt2 = loader.parse("test_tiles/e.yml");
        TileLoader lt3 = loader.parse("test_tiles/e.yml");
        TileLoader lt4 = loader.parse("test_tiles/e.yml");
        Tile t = lt1.convertToTile();
        Tile t2 = lt2.convertToTile();
        Tile t3 = lt3.convertToTile();
        Tile t4 = lt4.convertToTile();
        Player p1 = new Player("Adrian");
        Player p2 = new Player("Brian");
        Follower f1 = new Follower(p1);
        Follower f2 = new Follower(p2);
        Follower f3 = new Follower(p1);
        Follower f4 = new Follower(p2);
        t.getNorthSegments().get(0).placeFollower(p1);
        assertEquals(m.placeTileOnMap(0,0,t), true);
        t2.rotateTile("clockwise");
        t2.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(0, 1, t2), true);
        assertEquals(p1.getScore(), 4);
    }

    @Test
    public void checkScore_Road_CountTile_Test(){
        LoadYml loader = new LoadYml();
        TileLoader lt1 = loader.parse("test_tiles/v.yml");
        TileLoader lt2 = loader.parse("test_tiles/v.yml");
        TileLoader lt3 = loader.parse("test_tiles/v.yml");
        TileLoader lt4 = loader.parse("test_tiles/fourIdenticalSquare.yml");
        Tile t = lt1.convertToTile();
        Tile t2 = lt2.convertToTile();
        Tile t3 = lt3.convertToTile();
        Tile t4 = lt4.convertToTile();
        Player p1 = new Player("Adrian");
        Follower f1 = new Follower(p1);
        t.getWestSegments().get(1).placeFollower(p1);
        t.rotateTile("clockwise");
        t.rotateTile("clockwise");
        t.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(0, 0, t), true);
        assertEquals(m.placeTileOnMap(1, 0, t2), true);
        t3.rotateTile("clockwise");
        t3.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(0, -1, t3), true);
        assertEquals(m.placeTileOnMap(1,-1, t4), true);
        assertEquals(p1.getScore(), 4);
    }

    @Test
    public void checkScore_Cloister_Noraml_Test(){
        LoadYml loader = new LoadYml();
        TileLoader lt1 = loader.parse("test_tiles/b.yml");
        TileLoader lt2 = loader.parse("test_tiles/b.yml");
        TileLoader lt3 = loader.parse("test_tiles/b.yml");
        TileLoader lt4 = loader.parse("test_tiles/b.yml");
        TileLoader lt5 = loader.parse("test_tiles/b.yml");
        TileLoader lt6 = loader.parse("test_tiles/b.yml");
        TileLoader lt7 = loader.parse("test_tiles/b.yml");
        TileLoader lt8 = loader.parse("test_tiles/b.yml");
        TileLoader lt9 = loader.parse("test_tiles/b.yml");
        Tile t = lt1.convertToTile();
        Tile t2 = lt2.convertToTile();
        Tile t3 = lt3.convertToTile();
        Tile t4 = lt4.convertToTile();
        Tile t5 = lt5.convertToTile();
        Tile t6 = lt6.convertToTile();
        Tile t7 = lt7.convertToTile();
        Tile t8 = lt8.convertToTile();
        Tile t9 = lt9.convertToTile();
        Player p1 = new Player("Adrian");
        Follower f1 = new Follower(p1);
        t.getCloisterSegment().placeFollower(p1);
        assertEquals(m.placeTileOnMap(0, 0, t), true);
        assertEquals(m.placeTileOnMap(1, 0, t2), true);
        assertEquals(m.placeTileOnMap(1, -1, t3), true);
        assertEquals(m.placeTileOnMap(0, -1, t4), true);
        assertEquals(m.placeTileOnMap(-1, -1, t5), true);
        assertEquals(m.placeTileOnMap(-1, 0, t6), true);
        assertEquals(m.placeTileOnMap(-1, 1, t7), true);
        assertEquals(m.placeTileOnMap(0, 1, t8), true);
        assertEquals(p1.getScore(), 0);
        assertEquals(m.placeTileOnMap(1, 1, t9), true);
        assertEquals(p1.getScore(), 9);
    }

    @Test
    public void finalAccounting_City_Test(){
        LoadYml loader = new LoadYml();
        TileLoader lt1 = loader.parse("test_tiles/e.yml");
        TileLoader lt2 = loader.parse("test_tiles/e.yml");
        TileLoader lt3 = loader.parse("test_tiles/e.yml");
        TileLoader lt4 = loader.parse("test_tiles/checkSegmentTest1.yml");
        Tile t = lt1.convertToTile();
        Tile t2 = lt2.convertToTile();
        Tile t3 = lt3.convertToTile();
        Tile t4 = lt4.convertToTile();
        Player p1 = new Player("Adrian");
        Player p2 = new Player("Brian");
        Follower f1 = new Follower(p1);
        Follower f2 = new Follower(p2);
        t.getNorthSegments().get(0).placeFollower(p1);
        t2.getNorthSegments().get(0).placeFollower(p2);
        t.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(0,0,t), true);
        assertEquals(m.placeTileOnMap(0,1,t3), true);
        t2.rotateTile("clockwise");
        t2.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(1, 1, t2), true);
        assertEquals(m.placeTileOnMap(1, 0, t4), true);
        m.finalAccounting();
        assertEquals(p1.getScore(), 4);
        assertEquals(p2.getScore(), 4);
    }
    @Test
    public void finalAccounting_City＿TwoPlayersShare_Test(){
        LoadYml loader = new LoadYml();
        TileLoader lt1 = loader.parse("test_tiles/e.yml");
        TileLoader lt2 = loader.parse("test_tiles/e.yml");
        TileLoader lt3 = loader.parse("test_tiles/e.yml");
        TileLoader lt4 = loader.parse("test_tiles/checkSegmentTest1.yml");
        Tile t = lt1.convertToTile();
        Tile t2 = lt2.convertToTile();
        Tile t3 = lt3.convertToTile();
        Tile t4 = lt4.convertToTile();
        Player p1 = new Player("Adrian");
        Follower f1 = new Follower(p1);
        Follower f2 = new Follower(p1);
        t.getNorthSegments().get(0).placeFollower(p1);
        t2.getNorthSegments().get(0).placeFollower(p1);
        t.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(0,0,t), true);
        assertEquals(m.placeTileOnMap(0,1,t3), true);
        t2.rotateTile("clockwise");
        t2.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(1, 1, t2), true);
        assertEquals(m.placeTileOnMap(1, 0, t4), true);
        m.finalAccounting();
        assertEquals(p1.getScore(), 4);
    }
    @Test
    public void finalAccounting_Road_Test(){
        LoadYml loader = new LoadYml();
        TileLoader lt1 = loader.parse("test_tiles/v.yml");
        TileLoader lt2 = loader.parse("test_tiles/v.yml");
        TileLoader lt3 = loader.parse("test_tiles/v.yml");
        TileLoader lt4 = loader.parse("test_tiles/fourIdenticalSquare.yml");
        Tile t = lt1.convertToTile();
        Tile t2 = lt2.convertToTile();
        Tile t3 = lt3.convertToTile();
        Tile t4 = lt4.convertToTile();
        Player p1 = new Player("Adrian");
        Follower f1 = new Follower(p1);
        t.getWestSegments().get(1).placeFollower(p1);
        t.rotateTile("clockwise");
        t.rotateTile("clockwise");
        t.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(0, 0, t), true);
        assertEquals(m.placeTileOnMap(1, 0, t2), true);
        t3.rotateTile("clockwise");
        t3.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(0, -1, t3), true);
        m.finalAccounting();
        assertEquals(p1.getScore(), 3);
    }

    @Test
    public void finalAccounting_Cloister_Test(){
        LoadYml loader = new LoadYml();
        TileLoader lt1 = loader.parse("test_tiles/b.yml");
        TileLoader lt2 = loader.parse("test_tiles/b.yml");
        TileLoader lt3 = loader.parse("test_tiles/b.yml");
        TileLoader lt4 = loader.parse("test_tiles/b.yml");
        TileLoader lt5 = loader.parse("test_tiles/b.yml");
        TileLoader lt6 = loader.parse("test_tiles/b.yml");
        TileLoader lt7 = loader.parse("test_tiles/b.yml");
        TileLoader lt8 = loader.parse("test_tiles/b.yml");
        TileLoader lt9 = loader.parse("test_tiles/b.yml");
        Tile t = lt1.convertToTile();
        Tile t2 = lt2.convertToTile();
        Tile t3 = lt3.convertToTile();
        Tile t4 = lt4.convertToTile();
        Tile t5 = lt5.convertToTile();
        Tile t6 = lt6.convertToTile();
        Tile t7 = lt7.convertToTile();
        Tile t8 = lt8.convertToTile();
        Tile t9 = lt9.convertToTile();
        Player p1 = new Player("Adrian");
        Follower f1 = new Follower(p1);
        t.getCloisterSegment().placeFollower(p1);
        assertEquals(m.placeTileOnMap(0, 0, t), true);
        assertEquals(m.placeTileOnMap(1, 0, t2), true);
        assertEquals(m.placeTileOnMap(1, -1, t3), true);
        assertEquals(m.placeTileOnMap(0, -1, t4), true);
        assertEquals(m.placeTileOnMap(-1, -1, t5), true);
        m.finalAccounting();
        assertEquals(p1.getScore(), 5);
        assertEquals(m.placeTileOnMap(-1, 0, t6), true);
        assertEquals(m.placeTileOnMap(-1, 1, t7), true);
        assertEquals(m.placeTileOnMap(0, 1, t8), true);
        m.finalAccounting();
        assertEquals(p1.getScore(), 5 + 8);
        assertEquals(m.placeTileOnMap(1, 1, t9), true);

    }

    @Test
    public void finalAccounting_Field_Test(){
        LoadYml loader = new LoadYml();
        TileLoader lt1 = loader.parse("test_tiles/m.yml");
        TileLoader lt2 = loader.parse("test_tiles/m.yml");
        TileLoader lt3 = loader.parse("test_tiles/h.yml");
        TileLoader lt4 = loader.parse("test_tiles/h.yml");
        TileLoader lt5 = loader.parse("test_tiles/e.yml");
        Tile t = lt1.convertToTile();
        Tile t2 = lt2.convertToTile();
        Tile t3 = lt3.convertToTile();
        Tile t4 = lt4.convertToTile();
        Tile t5 = lt5.convertToTile();
        Player p1 = new Player("Adrian");
        Follower f1 = new Follower(p1);
        Player p2 = new Player("Brian");
        Follower f2 = new Follower(p2);
        t3.getNorthSegments().get(0).placeFollower(p1);
        t.getNorthSegments().get(0).placeFollower(p2);
        t.rotateTile("clockwise");
        t.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(0, 0, t), true);
        t2.rotateTile("clockwise");
        t2.rotateTile("clockwise");
        t2.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(1, 0, t2), true);
        t3.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(0, -1, t3), true);
        t4.rotateTile("clockwise");
        assertEquals(m.placeTileOnMap(1, -1, t4), true);
        assertEquals(m.placeTileOnMap(0, -2, t5), true);
        m.finalAccounting();
        assertEquals(p1.getScore(), 6);
        assertEquals(p2.getScore(), 12);
    }

    @Test
    public void throughOut_GuideExample_Test (){
        LoadYml loader = new LoadYml();
        TileLoader lt1 = loader.parse("test_tiles/j.yml");
        TileLoader lt2 = loader.parse("test_tiles/m.yml");
        TileLoader lt3 = loader.parse("test_tiles/m.yml");
        TileLoader lt4 = loader.parse("test_tiles/fourIdenticalSquare.yml");
        TileLoader lt5 = loader.parse("test_tiles/d.yml");
        TileLoader lt6 = loader.parse("test_tiles/fourEdgeField_Cloister.yml");
        Tile t = lt1.convertToTile();
        Tile t2 = lt2.convertToTile();
        Tile t3 = lt3.convertToTile();
        Tile t4 = lt4.convertToTile();
        Tile t5 = lt5.convertToTile();
        Tile t6 = lt6.convertToTile();
        t.rotateTile("clockwise");
        t2.rotateTile("clockwise");
        t2.rotateTile("clockwise");
        t2.rotateTile("clockwise");
        t3.rotateTile("clockwise");
        t5.rotateTile("clockwise");
        t5.rotateTile("clockwise");
        t5.rotateTile("clockwise");
        t6.rotateTile("clockwise");
        Player p1 = new Player("Adrian");
        Follower f1 = new Follower(p1);
        Player p2 = new Player("Brian");
        Follower f2 = new Follower(p2);
        Player p3 = new Player("Corbin");
        Follower f3 = new Follower(p3);
        t.getNorthSegments().get(0).placeFollower(p1);
        t2.getNorthSegments().get(0).placeFollower(p2);
        t5.getSouthSegments().get(0).placeFollower(p3);
        assertEquals(m.placeTileOnMap(0, 0, t), true);
        assertEquals(m.placeTileOnMap(1, 0, t2), true);
        assertEquals(m.placeTileOnMap(2, 0, t3), true);
        assertEquals(m.placeTileOnMap(0, -1, t4), true);
        assertEquals(m.placeTileOnMap(1, -1, t5), true);
        assertEquals(m.placeTileOnMap(2, -1, t6), true);
        m.finalAccounting();
        assertEquals(p1.getScore(), 3);
        assertEquals(p2.getScore(), 3);
        assertEquals(p3.getScore(), 3);
    }

    @Test
    public void throughOut_GuideExample_ModifiedSamePlayer_Test (){
        // the game listener will print out some actions
        LoadYml loader = new LoadYml();
        TileLoader lt1 = loader.parse("test_tiles/j.yml");
        TileLoader lt2 = loader.parse("test_tiles/m.yml");
        TileLoader lt3 = loader.parse("test_tiles/m.yml");
        TileLoader lt4 = loader.parse("test_tiles/fourIdenticalSquare.yml");
        TileLoader lt5 = loader.parse("test_tiles/d.yml");
        TileLoader lt6 = loader.parse("test_tiles/fourEdgeField_Cloister.yml");
        Tile t = lt1.convertToTile();
        Tile t2 = lt2.convertToTile();
        Tile t3 = lt3.convertToTile();
        Tile t4 = lt4.convertToTile();
        Tile t5 = lt5.convertToTile();
        Tile t6 = lt6.convertToTile();
        t.rotateTile("clockwise");
        t2.rotateTile("clockwise");
        t2.rotateTile("clockwise");
        t2.rotateTile("clockwise");
        t3.rotateTile("clockwise");
        t5.rotateTile("clockwise");
        t5.rotateTile("clockwise");
        t5.rotateTile("clockwise");
        t6.rotateTile("clockwise");
        Player p1 = new Player("Adrian");
        Follower f1 = new Follower(p1);
        Follower f2 = new Follower(p1);
        Follower f3 = new Follower(p1);
        t.getNorthSegments().get(0).placeFollower(p1);
        t2.getNorthSegments().get(0).placeFollower(p1);
        t5.getSouthSegments().get(0).placeFollower(p1);
        t6.getCloisterSegment().placeFollower(p1);
        assertEquals(m.placeTileOnMap(0, 0, t), true);
        assertEquals(m.placeTileOnMap(1, 0, t2), true);
        assertEquals(m.placeTileOnMap(2, 0, t3), true);
        assertEquals(m.placeTileOnMap(0, -1, t4), true);
        assertEquals(m.placeTileOnMap(1, -1, t5), true);
        assertEquals(m.placeTileOnMap(2, -1, t6), true);
        m.finalAccounting();
        assertEquals(p1.getScore(), 7);
    }

    @Test
    public void cloister_incompleteTest (){
        // the game listener will print out some actions
        LoadYml loader = new LoadYml();
        TileLoader lt1 = loader.parse("test_tiles/b.yml");
        TileLoader lt2 = loader.parse("test_tiles/b.yml");
        TileLoader lt3 = loader.parse("test_tiles/b.yml");
        TileLoader lt4 = loader.parse("test_tiles/b.yml");
        TileLoader lt5 = loader.parse("test_tiles/b.yml");
        TileLoader lt6 = loader.parse("test_tiles/b.yml");
        Tile t = lt1.convertToTile();
        Tile t2 = lt2.convertToTile();
        Tile t3 = lt3.convertToTile();
        Tile t4 = lt4.convertToTile();
        Tile t5 = lt5.convertToTile();
        Tile t6 = lt6.convertToTile();
        Player p1 = new Player("Adrian");
        Follower f1 = new Follower(p1);
        Follower f2 = new Follower(p1);
        Follower f3 = new Follower(p1);
        t.getCloisterSegment().placeFollower(p1);
        t2.getCloisterSegment().placeFollower(p1);
        t3.getCloisterSegment().placeFollower(p1);
        t4.getCloisterSegment().placeFollower(p1);
        t5.getCloisterSegment().placeFollower(p1);
        t6.getCloisterSegment().placeFollower(p1);
        assertEquals(m.placeTileOnMap(0, 0, t), true);
        assertEquals(m.placeTileOnMap(1, 0, t2), true);
        assertEquals(m.placeTileOnMap(2, 0, t3), true);
        assertEquals(m.placeTileOnMap(0, -1, t4), true);
        assertEquals(m.placeTileOnMap(1, -1, t5), true);
        assertEquals(m.placeTileOnMap(2, -1, t6), true);
        m.finalAccounting();
        assertEquals(p1.getScore(), 28);
    }


}