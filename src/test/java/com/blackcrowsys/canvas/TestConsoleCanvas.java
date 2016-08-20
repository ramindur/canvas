package com.blackcrowsys.canvas;


import com.blackcrowsys.canvas.exception.CanvasOperationException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestConsoleCanvas {

    private static final int WIDTH = 4;

    private static final int HEIGHT = 2;
    ConsoleCanvas consoleCanvas;
    private char[][] canvas = new char[HEIGHT + 2][WIDTH + 2];

    @Before
    public void setUp() {
        consoleCanvas = new ConsoleCanvas(WIDTH, HEIGHT);
    }

    @Test
    public void testCreatingCanvas() {
        setBlankCanvas();
        assertNotNull(consoleCanvas);
        assertEquals(WIDTH, consoleCanvas.getWidth());
        assertEquals(HEIGHT, consoleCanvas.getHeight());
        assertArrayEquals(canvas, consoleCanvas.getCanvas());
    }

    @Test
    public void testDrawingHorizontalLine() throws CanvasOperationException {
        setBlankCanvas();
        setHorizontalLine();
        consoleCanvas.drawLine(new Coordinate(1, 1), new Coordinate(4, 1));
        assertArrayEquals(canvas, consoleCanvas.getCanvas());
    }

    @Test(expected = CanvasOperationException.class)
    public void testDrawingHorizontalLineOutsideOfRange() throws CanvasOperationException {
        consoleCanvas.drawLine(new Coordinate(0, 2), new Coordinate(3, 2));
        consoleCanvas.drawLine(new Coordinate(1, 2), new Coordinate(5, 2));
    }

    @Test
    public void testDrawingVerticalLine() throws CanvasOperationException {
        setVerticalLine();
        consoleCanvas.drawLine(new Coordinate(2, 1), new Coordinate(2, 2));
        assertArrayEquals(canvas, consoleCanvas.getCanvas());
    }

    @Test(expected = CanvasOperationException.class)
    public void testDrawingVerticalLineOutsideOfRange() throws CanvasOperationException {
        consoleCanvas.drawLine(new Coordinate(1, 0), new Coordinate(1, 2));
        consoleCanvas.drawLine(new Coordinate(2, 1), new Coordinate(2, 3));
    }

    @Test(expected = CanvasOperationException.class)
    public void testDrawingLineThatIsNotAHorizontalOrVerticalLine() throws CanvasOperationException {
        consoleCanvas.drawLine(new Coordinate(1, 1), new Coordinate(3, 2));
    }

    @Test
    public void testDrawRectangle() throws CanvasOperationException {
        setRectangule();
        consoleCanvas.drawRectangle(new Coordinate(2, 1), new Coordinate(4, 2));
        assertArrayEquals(canvas, consoleCanvas.getCanvas());
    }

    @Test
    public void testFillRegion() throws CanvasOperationException {
        fillRegion();
        consoleCanvas.drawRectangle(new Coordinate(2, 1), new Coordinate(4, 2));
        consoleCanvas.fillRegion(new Coordinate(1, 1), 'o');
        assertArrayEquals(canvas, consoleCanvas.getCanvas());
    }

    @Test(expected = CanvasOperationException.class)
    public void testFillingRegionOutsideRange() throws CanvasOperationException {
        consoleCanvas.fillRegion(new Coordinate(0, 1), 'o');
        consoleCanvas.fillRegion(new Coordinate(1, 3), 'o');
    }

    private void setBlankCanvas() {
        canvas[0] = new char[]{'-', '-', '-', '-', '-', '-'};
        canvas[1] = new char[]{'|', 0, 0, 0, 0, '|'};
        canvas[2] = new char[]{'|', 0, 0, 0, 0, '|'};
        canvas[3] = new char[]{'-', '-', '-', '-', '-', '-'};
    }

    private void setHorizontalLine() {
        canvas[1] = new char[]{'|', 'x', 'x', 'x', 'x', '|'};
    }

    private void setVerticalLine() {
        canvas[0] = new char[]{'-', '-', '-', '-', '-', '-'};
        canvas[1] = new char[]{'|', 0, 'x', 0, 0, '|'};
        canvas[2] = new char[]{'|', 0, 'x', 0, 0, '|'};
        canvas[3] = new char[]{'-', '-', '-', '-', '-', '-'};
    }

    private void setRectangule() {
        canvas[0] = new char[]{'-', '-', '-', '-', '-', '-'};
        canvas[1] = new char[]{'|', 0, 'x', 'x', 'x', '|'};
        canvas[2] = new char[]{'|', 0, 'x', 'x', 'x', '|'};
        canvas[3] = new char[]{'-', '-', '-', '-', '-', '-'};
    }

    private void fillRegion() {
        canvas[0] = new char[]{'-', '-', '-', '-', '-', '-'};
        canvas[1] = new char[]{'|', 'o', 'x', 'x', 'x', '|'};
        canvas[2] = new char[]{'|', 'o', 'x', 'x', 'x', '|'};
        canvas[3] = new char[]{'-', '-', '-', '-', '-', '-'};
    }
}
