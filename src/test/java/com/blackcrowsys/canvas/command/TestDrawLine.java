package com.blackcrowsys.canvas.command;


import com.blackcrowsys.canvas.Canvas;
import com.blackcrowsys.canvas.Coordinate;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class TestDrawLine {

    private Canvas canvas = mock(Canvas.class);

    @Test
    public void testDrawingLine() {
        Coordinate from = new Coordinate(1, 2);
        Coordinate to = new Coordinate(6, 2);

        DrawLine drawLine = new DrawLine(from, to);
        drawLine.execute(canvas);
        verify(canvas, times(1)).drawLine(from, to);
    }
}
