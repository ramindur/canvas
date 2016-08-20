package com.blackcrowsys.canvas.command;

import com.blackcrowsys.canvas.Canvas;
import com.blackcrowsys.canvas.exception.CanvasOperationException;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

public class TestQuit {

    private Canvas canvas = mock(Canvas.class);

    @Test
    public void testQuitExecuteReturnsNull() throws CanvasOperationException {
        Command quit = new Quit();
        assertNull(quit.execute(canvas));
    }
}
