package edu.cmu.cs.cs214.hw4.core;

import edu.cmu.cs.cs214.hw4.core.segmentpackage.Segment;
import edu.cmu.cs.cs214.hw4.core.tileloaderpackage.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * game object
 * communicate with the player
 * and control highest level action
 */
public class Game {
    private ArrayList<Tile> allTiles;
    private ArrayList<Tile> availableTiles;
    private ArrayList<Player> playerList;
    private Map m;
    private int currentPlayerIndex;
    private GameActionListener ga;
    private int fakeCounter;

    /**
     * getPlayerList
     * @return arraylist of plaer
     */
    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    /**
     * get current map
     */
    public HashMap<XYCoordinate, Tile> getCurrentMap(){
        return m.getCurrentMap();
    }

    private void loadTiles (String tileFileName){
        LoadAllTile loader = new LoadAllTile();
        AllTileLoader alt = loader.parse(tileFileName);
        ArrayList<TileLoader> al = alt.getTileLoaders();
        int counter = 0;
        for (TileLoader t : al) {
            Tile temp = t.convertToTile();
            temp.setTileID(counter);
            allTiles.add(temp);
            availableTiles.add(temp);
            counter++;
        }
    }

    /**
     * restart the game
     * in case there's no valid tile to place on the map
     */
    public void restartGame(){
        m = new Map();
        playerList = new ArrayList<Player>();
        allTiles = new ArrayList<Tile>();
        availableTiles = new ArrayList<Tile>();
        final String tileFileName = "game_tiles/loadAllTile.yml";
        loadTiles(tileFileName);
        loadTiles(tileFileName);
        loadTiles(tileFileName);
        fakeCounter = 1;
        currentPlayerIndex = 0;
    }

    /**
     * subscribe gameaction listener to this game, only allow one gameaction listener
     * @param ga gamelistener
     */
    public void subscribeGameActionListener(GameActionListener ga){
        this.ga = ga;
    }

    /**
     * place a follower of the current player on a segment
     * @param s the segment to place a follower on
     */
    public boolean placeFollower(Segment s){
        if (s.placeFollower(playerList.get(currentPlayerIndex))){
            return true;
        }else {
            return false;
        }
    }

    /**
     * the constructor of game
     * load all tiles
     */
    public Game(){
        m = new Map();
        playerList = new ArrayList<Player>();
        allTiles = new ArrayList<Tile>();
        availableTiles = new ArrayList<Tile>();
        final String tileFileName = "game_tiles/loadAllTile.yml";
        loadTiles(tileFileName);
        loadTiles(tileFileName);
        loadTiles(tileFileName);
        fakeCounter = 1;
        currentPlayerIndex = 0;
    }

    /**
     * draw a random tile from allTiles and remove the tile from available tiles
     * if there's no more  tile, call settlethemap count the final score
     * @return the random tile
     */
    public Tile drawATile(){
        if (availableTiles.size() == 0){
            settleTheMap();
            return null;
        }
        Random rand = new Random();
        int  n = rand.nextInt(availableTiles.size());
        Tile returnTile = availableTiles.get(n);
        availableTiles.remove(returnTile);
        return returnTile;
    }

    /**
     * get arraylist of available tiles(undraw tiles)
     * @return availableTiles
     */
    public ArrayList<Tile> getAvailableTiles() {
        return availableTiles;
    }

    /**
     * get array list of tile
     * @return allTiles
     */
    public ArrayList<Tile> getAllTiles() {
        return allTiles;
    }

    /**
     * add a player with playername to the player list
     * @param playerName the name of the new player
     */
    public void addPlayers(String playerName){
        Player p = new Player(playerName);
        playerList.add(p);
    }

    /**
     * turn to next player
     */
    private void nextPlayer(){
        currentPlayerIndex = (currentPlayerIndex + 1)%(playerList.size());
        ga.playerChanged(playerList.get(currentPlayerIndex));
    }

    /**
     * place new tile on xy coordinate
     * call gameactionlistener mapchanged and nextplayer method
     * switch to next player
     * @param x x coordinate
     * @param y y coordinate
     * @param newTile the new tile
     * @return whether the placement is valid or not
     */
    public boolean placeNewTile (int x, int y, Tile newTile){
        if (m.placeTileOnMap(x, y, newTile)) {
            HashMap<Player, Integer> playerScoreList = new HashMap<Player, Integer>();
            for(Player p : playerList){
                playerScoreList.put(p, p.getScore());
            }
            ga.scoreChanged(playerScoreList);
            nextPlayer();
            return true;
        }else {
            return false;
        }
    }



    /**
     * show the current map
     * @return hashmap of map
     */
    public java.util.Map<XYCoordinate, Tile> showMap(){
        return m.getCurrentMap();
    }


    private ArrayList<Player> getWinnersOfGame (HashMap<Player, Integer> finalScores){
        int max = 0;
        for (Player p : finalScores.keySet()){
            int finalScore = finalScores.get(p);
            if (finalScore > max){
                max = finalScore;
            }
        }
        ArrayList<Player> winners = new ArrayList<Player>();
        for (Player p : finalScores.keySet()){
            int finalScore = finalScores.get(p);
            if (finalScore == max){
                winners.add(p);
            }
        }
        return winners;
    }

    /**
     * settle the map, update incomplete features
     * final scoring
     */
    private void settleTheMap(){
        m.finalAccounting();
        HashMap<Player, Integer> finalScore = new HashMap<Player, Integer>();
        for (Player p : playerList){
            finalScore.put(p, p.getScore());
        }
        ga.finalScore(getWinnersOfGame(finalScore), finalScore);
    }

    Tile fakeDrawTile (){
        LoadYml loader = new LoadYml();
        TileLoader lt = new TileLoader();
        switch (fakeCounter) {
            case 1:
                lt = loader.parse("test_tiles/j.yml");
                break;
            case 2:
                lt = loader.parse("test_tiles/m.yml");
                break;
            case 3:
                lt = loader.parse("test_tiles/m.yml");
                break;
            case 4:
                lt = loader.parse("test_tiles/fourIdenticalSquare.yml");
                break;
            case 5:
                lt = loader.parse("test_tiles/d.yml");
                break;
            case 6:
                lt = loader.parse("test_tiles/fourEdgeField_Cloister.yml");
                break;
            case 7:
                settleTheMap();
                return null;
            default:
                throw new IllegalArgumentException("no cases in fake draw tile");
        }
        fakeCounter++;
        return lt.convertToTile();


    }

    /**
     * start the game. call game action listener
     */
    public void gameStart(){
        ga.playerChanged(playerList.get(0));
    }

}
