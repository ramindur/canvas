package com.blackcrowsys.canvas.command;

import com.blackcrowsys.canvas.Canvas;
import com.blackcrowsys.canvas.Coordinate;
import com.blackcrowsys.canvas.exception.CanvasOperationException;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class TestDrawRectangle {
    private Canvas canvas = mock(Canvas.class);

    @Test
    public void testDrawingRectangle() throws CanvasOperationException {
        Coordinate topLeftCorner = new Coordinate(16, 1);
        Coordinate bottomRightCorner = new Coordinate(20, 3);

        Command drawRectangle = new DrawRectangle(topLeftCorner, bottomRightCorner);
        drawRectangle.execute(canvas);
        verify(canvas, times(1)).drawRectangle(topLeftCorner, bottomRightCorner);
    }
}
