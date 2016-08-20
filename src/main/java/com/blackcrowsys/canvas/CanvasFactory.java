package com.blackcrowsys.canvas;


public class CanvasFactory {

    /**
     * Creates a new Canvas object. Only console canvas supported.
     *
     * @param width  the width of the canvas
     * @param height the height of the canvas
     * @return the instance of canvas
     */
    public Canvas create(int width, int height) {
        return new ConsoleCanvas(width, height);
    }
}
