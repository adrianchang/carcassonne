package edu.cmu.cs.cs214.hw4.core.tileloaderpackage;

import edu.cmu.cs.cs214.hw4.core.tileloaderpackage.TileLoader;

import java.util.ArrayList;

/**
 * the parent loader of Tile loader
 * contain only an arraylist of TileLoader
 * let snake yaml load this class and then the snake yaml will load TileLoader
 */
public class AllTileLoader {
    private ArrayList<TileLoader> tileLoaders = new ArrayList<TileLoader>();

    /**
     * get tile loaders
     * @return array list of tile loaders
     */
    public ArrayList<TileLoader> getTileLoaders() {
        return tileLoaders;
    }

    /**
     * set tileLoaders arraylist
     * @param tileLoaders the tileLoaders to set to this class's tileLoaders
     */
    public void setTileLoaders(ArrayList<TileLoader> tileLoaders) {
        this.tileLoaders = tileLoaders;
    }
}
