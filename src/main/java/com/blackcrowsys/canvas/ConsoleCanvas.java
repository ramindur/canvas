package com.blackcrowsys.canvas;

public class ConsoleCanvas implements Canvas {

    public ConsoleCanvas(int width, int height) {
    }

    @Override
    public Canvas drawLine(Coordinate from, Coordinate to) {
        return this;
    }

    @Override
    public Canvas drawRectangle(Coordinate topLeftCorner, Coordinate bottomRightCorner) {
        return this;
    }

    @Override
    public Canvas fillRegion(Coordinate location, char fillCharacter) {
        return this;
    }
}
