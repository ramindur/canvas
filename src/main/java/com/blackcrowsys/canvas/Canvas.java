package com.blackcrowsys.canvas;

import com.blackcrowsys.canvas.exception.CanvasOperationException;

public interface Canvas {

    Canvas drawLine(Coordinate from, Coordinate to) throws CanvasOperationException;

    Canvas drawRectangle(Coordinate topLeftCorner, Coordinate bottomRightCorner) throws CanvasOperationException;

    Canvas fillRegion(Coordinate location, char fillCharacter) throws CanvasOperationException;

    void displayCanvas() throws CanvasOperationException;

    Object getCanvas();
}
