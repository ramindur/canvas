package com.blackcrowsys.canvas;

public interface Canvas {

    Canvas drawLine(Coordinate from, Coordinate to);

    Canvas drawRectangle(Coordinate topLeftCorner, Coordinate bottomRightCorner);

    Canvas fillRegion(Coordinate location, char fillCharacter);
}
