package com.blackcrowsys.canvas.command;


import com.blackcrowsys.canvas.Canvas;
import com.blackcrowsys.canvas.CanvasFactory;
import com.blackcrowsys.canvas.exception.CanvasException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class TestCreate {

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
    public void testCreateCommand() throws CanvasException {
        Create create = new Create(WIDTH, HEIGHT, factory);
        Canvas canvas = create.execute(null);
        assertNotNull(canvas);
        assertEquals(canvas, canvas);
    }

    @Test(expected = CanvasException.class)
    public void testCreatingCanvasWithNullFactory() throws CanvasException {
        new Create(WIDTH, HEIGHT, null);
    }

    @Test(expected = CanvasException.class)
    public void testCreatingCanvasWithZeroWidth() throws CanvasException {
        new Create(0, HEIGHT, factory);
    }

    @Test(expected = CanvasException.class)
    public void testCreatingCanvasWithZeroHeight() throws CanvasException {
        new Create(WIDTH, 0, factory);
    }

    @Test(expected = CanvasException.class)
    public void testCreatingCanvasWithNegativeWidth() throws CanvasException {
        new Create(-1, HEIGHT, factory);
    }

    @Test(expected = CanvasException.class)
    public void testCreatingCanvasWithNegativeHeight() throws CanvasException {
        new Create(WIDTH, -1, factory);
    }
}
