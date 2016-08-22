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
        if (!checkCoordinatesForRectangle(topLeftCorner, bottomRightCorner))
            throw new CanvasOperationException("Given coordinates are not top left and bottom right corner respectively");

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

        throw new CanvasOperationException("Line is not a vertical or horizontal line");
    }

    /**
     * Fills a region with the fillCharacter. It does this by repeatedly calling scan and fill function until
     * the function returns false (i.e. scan operation did not fill any location).
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
            canvas[location.getY()][location.getX()] = fillCharacter;
            while (scanCanvasForFilling(fillCharacter)) ;
            return this;
        }
        throw new CanvasOperationException("Location is already occupied");
    }

    @Override
    public void displayCanvas() {
        for (char[] row : canvas) {
            for (char pixel : row) {
                if (pixel == 0) pixel = ' ';
                System.out.print(pixel);
            }
            System.out.println("");
        }
    }

    public int getWidth() {
        return canvas[0].length - 2;
    }

    public int getHeight() {
        return canvas.length - 2;
    }

    public Object getCanvas() {
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
     * It scans along the X/Y axis of the canvas/
     *
     * @param fillCharacter the fill character
     * @return true if it did find a location which it filled with the fill character, otherwise false
     */
    private boolean scanCanvasForFilling(char fillCharacter) {
        boolean filledSomeLocation = false;
        for (int yLocation = 1; yLocation <= height; yLocation++) {
            for (int xLocation = 1; xLocation <= width; xLocation++) {
                if (canvas[yLocation][xLocation] == 0) if (checkAndFillAtLocation(xLocation, yLocation, fillCharacter))
                    filledSomeLocation = true;
            }
        }
        return filledSomeLocation;
    }

    /**
     * Checks if the location can be filled with the fill character
     *
     * @param x             the X axis point
     * @param y             the Y axis point
     * @param fillCharacter the fill character
     * @return true if it did fill, otherwise false
     */
    private boolean checkAndFillAtLocation(int x, int y, char fillCharacter) {
        if (checkIfCanFillAtLocation(x, y, fillCharacter)) {
            canvas[y][x] = fillCharacter;
            return true;
        }
        return false;
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
            if (canvas[y - 1][x] == fillCharacter) return true;
            if (canvas[y + 1][x] == fillCharacter) return true;
            if (canvas[y][x - 1] == fillCharacter) return true;
            if (canvas[y][x + 1] == fillCharacter) return true;
            if (canvas[y - 1][x - 1] == fillCharacter) return true;
            if (canvas[y - 1][x + 1] == fillCharacter) return true;
            if (canvas[y + 1][x - 1] == fillCharacter) return true;
            if (canvas[y + 1][x + 1] == fillCharacter) return true;
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

    /**
     * Checks if the coordinates are top left corner and bottom right corner
     *
     * @param topLeftCorner
     * @param bottomRightCorner
     * @return true if the coordinates make sense, otherwise false
     */
    private boolean checkCoordinatesForRectangle(Coordinate topLeftCorner, Coordinate bottomRightCorner) {
        if (topLeftCorner.getX() <= bottomRightCorner.getX()) {
            if (topLeftCorner.getY() <= bottomRightCorner.getY()) return true;
        }
        return false;
    }
}
