package com.blackcrowsys.canvas;

import com.blackcrowsys.canvas.exception.CanvasOperationException;

public class ConsoleCanvas implements Canvas {

    private static final char DEFAULT_CHAR = 'x';

    private char[][] canvas;

    private int width;

    private int height;

    /**
     * Constructor for ConsoleCanvas. It creates an 2D char array and surrounds it with '-' & '|'.
     *
     * @param width  the width of the canvas
     * @param height the height of the canvas
     */
    public ConsoleCanvas(int width, int height) {
        this.width = width;
        this.height = height;
        canvas = new char[height + 2][width + 2];
        drawHorizontalLine(new Coordinate(0, 0), new Coordinate(width + 1, 0), '-');
        drawHorizontalLine(new Coordinate(0, height + 1), new Coordinate(width + 1, height + 1), '-');
        drawVerticalLine(new Coordinate(0, 1), new Coordinate(0, height), '|');
        drawVerticalLine(new Coordinate(width + 1, 1), new Coordinate(width + 1, height), '|');
    }

    /**
     * Draws a rectangle with the given corner coordinates.
     *
     * @param topLeftCorner     the top left corner
     * @param bottomRightCorner the bottom right corner
     * @return this
     */
    @Override
    public Canvas drawRectangle(Coordinate topLeftCorner, Coordinate bottomRightCorner) throws CanvasOperationException {
        drawLine(topLeftCorner, new Coordinate(bottomRightCorner.getX(), topLeftCorner.getY()));
        drawLine(topLeftCorner, new Coordinate(topLeftCorner.getX(), bottomRightCorner.getY()));

        drawLine(new Coordinate(topLeftCorner.getX(), bottomRightCorner.getY()), bottomRightCorner);
        drawLine(new Coordinate(bottomRightCorner.getX(), topLeftCorner.getY()), bottomRightCorner);
        return this;
    }

    /**
     * Draws a vertical or horizontal line between two locations.
     *
     * @param from the from location
     * @param to   the to location
     * @return this
     */
    @Override
    public Canvas drawLine(Coordinate from, Coordinate to) throws CanvasOperationException {
        if (!checkCoordinates(from) || !checkCoordinates(to))
            throw new CanvasOperationException("Coordinates outside of region");

        if (from.getX() == to.getX()) {
            if (from.getY() > to.getY()) {
                int temp = from.getY();
                from.setY(to.getY());
                to.setY(temp);
            }
            drawVerticalLine(from, to, DEFAULT_CHAR);
            return this;
        }

        if (from.getY() == to.getY()) {
            if (from.getX() > to.getX()) {
                int temp = from.getX();
                from.setX(to.getX());
                to.setX(temp);
            }
            drawHorizontalLine(from, to, DEFAULT_CHAR);
            return this;
        }

        throw new CanvasOperationException("Request line is not a vertical or horizontal line");
    }

    /**
     * Fills a region with the fillCharacter.
     *
     * @param location      the location around which should be filled
     * @param fillCharacter the charcter to use
     * @return this
     */
    @Override
    public Canvas fillRegion(Coordinate location, char fillCharacter) throws CanvasOperationException {
        if (!checkCoordinates(location)) {
            throw new CanvasOperationException("Location is outside of canvas");
        }
        if (canvas[location.getY()][location.getX()] == 0) {
            fillRegionAroundPoint(location, fillCharacter);
            return this;
        }
        throw new CanvasOperationException("Location is already occupied");
    }

    public int getWidth() {
        return canvas[0].length - 2;
    }

    public int getHeight() {
        return canvas.length - 2;
    }

    public char[][] getCanvas() {
        return canvas;
    }

    /**
     * Draws a horizontal line.
     *
     * @param from          the from location
     * @param to            the to location
     * @param drawCharacter the character to use
     */
    private void drawHorizontalLine(Coordinate from, Coordinate to, char drawCharacter) {
        for (int x = from.getX(); x <= to.getX(); x++) canvas[from.getY()][x] = drawCharacter;
    }

    /**
     * Draws a vertical line.
     *
     * @param from          the from location
     * @param to            the to location
     * @param fillCharacter the character to use
     */
    private void drawVerticalLine(Coordinate from, Coordinate to, char fillCharacter) {
        for (int y = from.getY(); y <= to.getY(); y++) canvas[y][from.getX()] = fillCharacter;
    }

    /**
     * Fills the region at the point and around it.
     * It does this by traversing along the x and y axis in one direction and then the other direction
     * until it hits a location which already is set.
     *
     * @param location      the location
     * @param fillCharacter the character to use
     */
    private void fillRegionAroundPoint(Coordinate location, char fillCharacter) {
        canvas[location.getY()][location.getX()] = fillCharacter;

        for (int y = location.getY(); y <= height; y++) {
            fillAlongXAxisGoingRight(location.getX(), y, fillCharacter);
            fillAlongXAxisGoingLeft(location.getX(), y, fillCharacter);
        }

        for (int y = location.getY(); y > 0; y--) {
            fillAlongXAxisGoingRight(location.getX(), y, fillCharacter);
            fillAlongXAxisGoingLeft(location.getX(), y, fillCharacter);
        }
    }

    /*
     * The following are methods to support the fillRegion methods.
     * In order to speed things up, it uses the X/Y coordinate values rather than
     * a Coordinate object.
     */
    private void fillAlongXAxisGoingRight(int x, int y, char fillCharacter) {
        for (int xIndex = x; xIndex <= width; xIndex++) {
            if (checkIfCanFillAtLocation(xIndex, y, fillCharacter)) {
                canvas[y][xIndex] = fillCharacter;
            }
            fillYAxisGoingDown(xIndex, y, fillCharacter);
            fillYAxisGoingUp(xIndex, y, fillCharacter);
        }
    }

    private void fillAlongXAxisGoingLeft(int x, int y, char fillCharacter) {
        for (int xIndex = x; xIndex > 0; xIndex--) {
            if (checkIfCanFillAtLocation(xIndex, y, fillCharacter)) {
                canvas[y][xIndex] = fillCharacter;
            }
            fillYAxisGoingDown(xIndex, y, fillCharacter);
            fillYAxisGoingUp(xIndex, y, fillCharacter);
        }
    }

    private void fillYAxisGoingUp(int x, int y, char fillCharacter) {
        for (int yIndex = y; yIndex < height; yIndex++) {
            if (checkIfCanFillAtLocation(x, yIndex, fillCharacter)) {
                canvas[yIndex][x] = fillCharacter;
            }
        }
    }

    private void fillYAxisGoingDown(int x, int y, char fillCharacter) {
        for (int yIndex = y; yIndex > 0; yIndex--) {
            if (checkIfCanFillAtLocation(x, yIndex, fillCharacter)) {
                canvas[yIndex][x] = fillCharacter;
            }
        }
    }

    /**
     * It checks if the location can be filled. It does this by checking that it is not already set and there
     * is a fill character adjacent to the location.
     *
     * @param x             the X axis point
     * @param y             the Y axis point
     * @param fillCharacter the fill character
     * @return true if location can be filled
     */
    private boolean checkIfCanFillAtLocation(int x, int y, char fillCharacter) {
        if (canvas[y][x] == 0) {
            if (canvas[y - 1][x] == fillCharacter
                    || canvas[y + 1][x] == fillCharacter
                    || canvas[y][x - 1] == fillCharacter
                    || canvas[y][x + 1] == fillCharacter)
                return true;
        }
        return false;
    }

    /**
     * Checks if the location is within the canvas boundry.
     *
     * @param at the location
     * @return false if outside of the canvas, otherwise true
     */
    private boolean checkCoordinates(Coordinate at) {
        if (at.getX() < 1 || at.getX() > width) return false;
        if (at.getY() < 1 || at.getY() > height) return false;
        return true;
    }
}
