package com.blackcrowsys.canvas.command;


import com.blackcrowsys.canvas.Canvas;
import com.blackcrowsys.canvas.CanvasFactory;
import com.blackcrowsys.canvas.exception.CanvasOperationException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class TestCreateCanvas {

    private static final int WIDTH = 20;
    private static final int HEIGHT = 4;
    private Canvas canvas = mock(Canvas.class);


    CanvasFactory factory = new CanvasFactory() {
        @Override
        public Canvas create(int width, int height) {
            return canvas;
        }
    };

    @Test
    public void testCreateCommand() throws CanvasOperationException {
        Command create = new CreateCanvas(WIDTH, HEIGHT, factory);
        Canvas canvas = create.execute(null);
        assertNotNull(canvas);
        assertEquals(canvas, canvas);
    }

    @Test(expected = CanvasOperationException.class)
    public void testCreatingCanvasWithNullFactory() throws CanvasOperationException {
        new CreateCanvas(WIDTH, HEIGHT, null);
    }

    @Test(expected = CanvasOperationException.class)
    public void testCreatingCanvasWithZeroWidth() throws CanvasOperationException {
        new CreateCanvas(0, HEIGHT, factory);
    }

    @Test(expected = CanvasOperationException.class)
    public void testCreatingCanvasWithZeroHeight() throws CanvasOperationException {
        new CreateCanvas(WIDTH, 0, factory);
    }

    @Test(expected = CanvasOperationException.class)
    public void testCreatingCanvasWithNegativeWidth() throws CanvasOperationException {
        new CreateCanvas(-1, HEIGHT, factory);
    }

    @Test(expected = CanvasOperationException.class)
    public void testCreatingCanvasWithNegativeHeight() throws CanvasOperationException {
        new CreateCanvas(WIDTH, -1, factory);
    }
}
