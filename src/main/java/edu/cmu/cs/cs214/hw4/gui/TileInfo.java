package edu.cmu.cs.cs214.hw4.gui;

import edu.cmu.cs.cs214.hw4.core.Tile;

import java.awt.image.BufferedImage;

public class TileInfo {
    private Tile t;
    private BufferedImage tImage;

    public TileInfo(Tile t, BufferedImage tImage){
        this.t = t;
        this.tImage = tImage;
    }

    public BufferedImage getTImage() {
        return tImage;
    }

    public Tile getT() {
        return t;
    }
}
