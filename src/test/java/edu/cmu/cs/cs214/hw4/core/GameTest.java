package edu.cmu.cs.cs214.hw4.core;

import edu.cmu.cs.cs214.hw4.core.segmentpackage.CloisterSegmentImpl;
import edu.cmu.cs.cs214.hw4.core.segmentpackage.FieldSegmentImpl;
import edu.cmu.cs.cs214.hw4.core.tileloaderpackage.LoadYml;
import edu.cmu.cs.cs214.hw4.core.tileloaderpackage.TileLoader;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class GameTest {
    Game g;
    @Before
    public void createGame () {
        g = new Game();

    }

    @Test
    public void loadAllTiles_Test () {
        ArrayList<Tile> allTiles = g.getAllTiles();
        assertEquals(allTiles.size(), 72);
        assertEquals(allTiles.get(0) == allTiles.get(24), false);
        assertEquals(allTiles.get(48) == allTiles.get(24), false);
        assertEquals(allTiles.get(0).getCloisterSegment() instanceof CloisterSegmentImpl, true);
        assertEquals(allTiles.get(24).getCloisterSegment() instanceof CloisterSegmentImpl, true);
        assertEquals(allTiles.get(0).getNorthSegments().get(0) instanceof FieldSegmentImpl, true);
        assertEquals(allTiles.get(24).getNorthSegments().get(0) instanceof FieldSegmentImpl, true);

        assertEquals(allTiles.get(0).getCloisterSegment() == allTiles.get(24).getCloisterSegment(), false);
        assertEquals(allTiles.get(0).getCloisterSegment() == allTiles.get(24).getCloisterSegment(), false);
        assertEquals(allTiles.get(24).getCloisterSegment() == allTiles.get(48).getCloisterSegment(), false);
        assertEquals(allTiles.get(24) == allTiles.get(48), false);
        assertEquals(allTiles.get(0).getNorthSegments().get(0) == allTiles.get(1).getNorthSegments().get(0), false);
    }
     @Test
    public void drawTile_Test (){
        assertEquals(g.getAvailableTiles().size(), 72);
        HashMap<Tile, Tile> checkMap = new HashMap<Tile, Tile>();
        for (int i = 0; i < 72; i ++) {
            Tile tempT = g.drawATile();
            assertEquals(tempT instanceof Tile, true);
            checkMap.put(tempT, tempT);
        }
        assertEquals(g.getAvailableTiles().size(), 0);
        assertEquals(checkMap.size(), 72);
    }
    @Test
    public void normalGameTest(){
        g.showMap();
        g.addPlayers("Adrian");
        g.addPlayers("Brian");
        GameActionListener ga = new GameActionListenerImplTest();
        g.subscribeGameActionListener(ga);
        Tile temp;
        //player 1 turn t1
        temp = g.fakeDrawTile();
        temp.rotateTile("clockwise");
        g.placeFollower(temp.getNorthSegments().get(0));
        assertEquals(g.placeNewTile(0, 0, temp), true);
        //player 2 turn t2
        temp = g.fakeDrawTile();
        temp.rotateTile("clockwise");
        temp.rotateTile("clockwise");
        temp.rotateTile("clockwise");
        g.placeFollower(temp.getSouthSegments().get(0));
        assertEquals(g.placeNewTile(1, 0, temp), true);
        //player 1 turn t3
        temp = g.fakeDrawTile();
        temp.rotateTile("clockwise");
        assertEquals(g.placeNewTile(2, 0, temp), true);
        //player 2 turn t4
        temp = g.fakeDrawTile();
        assertEquals(g.placeNewTile(0, -1, temp), true);
        //player 1 turn t5
        temp = g.fakeDrawTile();
        temp.rotateTile("clockwise");
        temp.rotateTile("clockwise");
        temp.rotateTile("clockwise");
        g.placeFollower(temp.getSouthSegments().get(0));
        assertEquals(g.placeNewTile(1, -1, temp), true);
        //player 2 turn t6
        temp = g.fakeDrawTile();
        temp.rotateTile("clockwise");
        assertEquals(g.placeNewTile(2, -1, temp), true);
        temp = g.fakeDrawTile();
        assertEquals(temp, null);


        //restart the game
        g.restartGame();
        g.showMap();
        g.addPlayers("Adrian");
        g.addPlayers("Brian");
        ga = new GameActionListenerImplTest();
        g.subscribeGameActionListener(ga);
        //player 1 turn t1
        temp = g.fakeDrawTile();
        temp.rotateTile("clockwise");
        g.placeFollower(temp.getNorthSegments().get(0));
        assertEquals(g.placeNewTile(0, 0, temp), true);
        //player 2 turn t2
        temp = g.fakeDrawTile();
        temp.rotateTile("clockwise");
        temp.rotateTile("clockwise");
        temp.rotateTile("clockwise");
        g.placeFollower(temp.getSouthSegments().get(0));
        assertEquals(g.placeNewTile(1, 0, temp), true);
        //player 1 turn t3
        temp = g.fakeDrawTile();
        temp.rotateTile("clockwise");
        assertEquals(g.placeNewTile(2, 0, temp), true);
        //player 2 turn t4
        temp = g.fakeDrawTile();
        assertEquals(g.placeNewTile(0, -1, temp), true);
        //player 1 turn t5
        temp = g.fakeDrawTile();
        temp.rotateTile("clockwise");
        temp.rotateTile("clockwise");
        temp.rotateTile("clockwise");
        g.placeFollower(temp.getSouthSegments().get(0));
        assertEquals(g.placeNewTile(1, -1, temp), true);
        //player 2 turn t6
        temp = g.fakeDrawTile();
        temp.rotateTile("clockwise");
        assertEquals(g.placeNewTile(2, -1, temp), true);
        temp = g.fakeDrawTile();
        assertEquals(temp, null);
    }
}