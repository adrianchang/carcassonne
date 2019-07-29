package edu.cmu.cs.cs214.hw4.core.tileloaderpackage;

import java.util.Objects;

/**
 * x y coordinate object
 * contain x and y coordinate
 */
public class XYCoordinate {
    private int x;
    private int y;

    /**
     * x y coordinate constructor
     * @param x the x coordinate to set
     * @param y the y coordinate to set
     */
    public XYCoordinate (int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * get x coordinate
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * get y coordinate
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * set x coordinate
     * @param x the x coordinate to set to x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * set y coordinate
     * @param y the y coordinate to set to y
     */
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof XYCoordinate)) {
            return false;
        }
        XYCoordinate newObj = (XYCoordinate) obj;
        if (x == newObj.getX() && y == newObj.getY()) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
