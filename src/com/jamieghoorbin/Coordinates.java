package com.jamieghoorbin;

/**
 * A point class that represents the location (x, y)
 *
 * @author (Jamie Ghoorbin)
 * @version (12 / 10 / 2018)
 */

public class Coordinates
{
    private int x;
    private int y;

    /**
     * The constructor for the Coordinates class
     * @param x The x coordinate
     * @param y Te y coordinate
     */
    public Coordinates(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the coordinate x
     * @return x The coordinate x
     */
    public int getX()
    {
        return x;
    }

    /**
     * Returns the coordinate y
     * @return y The coordinate y
     */
    public int getY()
    {
        return y;
    }

    /**
     * Set the coordinate for x
     * @param x The coordinate x
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * Set the coordinate for y
     * @param y The coordinate y
     */
    public void setY(int y)
    {
        this.y = y;
    }
}