package com.blackcrowsys.canvas;

/**
 * Created by ramindursingh on 20/08/2016.
 */
public interface Canvas {

    Canvas drawLine(Coordinate from, Coordinate to);

    Canvas drawRectangle(Coordinate topLeftCorner, Coordinate bottomRightCorner);

    Canvas fillRegion(Coordinate location, char fillCharacter);
}
