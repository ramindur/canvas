package com.blackcrowsys.canvas.command;

import com.blackcrowsys.canvas.Canvas;
import com.blackcrowsys.canvas.Coordinate;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TestFillRegion {

    private static final char FILL_CHAR = 'o';

    private Canvas canvas = mock(Canvas.class);

    @Test
    public void testFillingRegion(){
        Coordinate location = new Coordinate(10, 3);

        Command fillRegion = new FillRegion(location, FILL_CHAR);
        fillRegion.execute(canvas);

        verify(canvas, times(1)).fillRegion(location, FILL_CHAR);
    }
}
