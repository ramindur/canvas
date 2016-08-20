package com.blackcrowsys.canvas;


import com.blackcrowsys.canvas.command.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestCommandFactory {
    @Test
    public void execute() throws Exception {

    }


    private static final String CREATE_CANVAS = "C 20 4";

    private static final String DRAW_LINE = "L 1 2 6 2";

    private static final String DRAW_RECTANGLE = "R 16 1 20 3";

    private static final String FILL_REGION = "B 10 3 o";

    private CommandFactory factory;

    @Before
    public void setUp(){
        factory = new CommandFactory();
    }

    @Test
    public void testGeneratingCommandForCreatingCanvas(){
        Command createCanvasCommand = new Create();
        Command createCanvas = factory.getCommandFor(CREATE_CANVAS);
        assertNotNull(createCanvas);
        assertTrue(createCanvasCommand.getCommandString().equals(createCanvas.getCommandString()));
    }


    @Test
    public void testGeneratingCommandForDrawingLine(){
        Command drawLineCommand = new Line();
        Command drawLine = factory.getCommandFor(DRAW_LINE);
        assertNotNull(drawLine);
        assertTrue(drawLineCommand.getCommandString().equals(drawLine.getCommandString()));
    }

    @Test
    public void testGeneratingCommandForDrawingRectangle(){
        Command drawRectangleCommand = new Rectangle();
        Command drawRectangle = factory.getCommandFor(DRAW_RECTANGLE);
        assertNotNull(drawRectangle);
        assertTrue(drawRectangleCommand.getCommandString().equals(drawRectangle.getCommandString()));
    }

    @Test
    public void testGeneratingCommandForFillRegtion(){
        Command fillRegionCommand = new FillRegion();
        Command fillRegion = factory.getCommandFor(FILL_REGION);
        assertNotNull(fillRegion);
        assertTrue(fillRegionCommand.getCommandString().equals(fillRegion.getCommandString()));
    }
}
