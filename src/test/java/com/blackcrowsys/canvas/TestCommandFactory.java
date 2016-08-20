package com.blackcrowsys.canvas;


import com.blackcrowsys.canvas.command.*;
import com.blackcrowsys.canvas.exception.InvalidCommandException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestCommandFactory {
    private static final String CREATE_CANVAS = "C 20 4";
    private static final String DRAW_LINE = "L 1 2 6 2";
    private static final String DRAW_RECTANGLE = "R 16 1 20 3";
    private static final String FILL_REGION = "B 10 3 o";
    private static final String QUIT = "Q";
    private CommandFactory factory;

    @Test
    public void execute() throws Exception {

    }

    @Before
    public void setUp() {
        factory = new CommandFactory();
    }

    @Test
    public void testGeneratingCommandForCreatingCanvas() throws InvalidCommandException {
        Command createCanvasCommand = new Create(20, 4);
        Command createCanvas = factory.getCommandFor(CREATE_CANVAS);
        assertNotNull(createCanvas);
        assertTrue(createCanvasCommand.getCommandString().equals(createCanvas.getCommandString()));
    }

    @Test(expected = InvalidCommandException.class)
    public void testGeneratingCreateCommandWithTooManyArguments() throws InvalidCommandException {
        factory.getCommandFor("C 20 4 6");
    }

    @Test(expected = InvalidCommandException.class)
    public void testGeneratingCreateCommandWithTooFewArguments() throws InvalidCommandException {
        factory.getCommandFor("C 20");
    }

    @Test(expected = InvalidCommandException.class)
    public void testGeneratingCreateCommandWithInvalidArguments() throws InvalidCommandException {
        factory.getCommandFor("C 20 a");
    }

    @Test
    public void testGeneratingCommandForDrawingLine() throws InvalidCommandException {
        Command drawLineCommand = new Line(new Coordinate(), new Coordinate());
        Command drawLine = factory.getCommandFor(DRAW_LINE);
        assertNotNull(drawLine);
        assertTrue(drawLineCommand.getCommandString().equals(drawLine.getCommandString()));
    }

    @Test(expected = InvalidCommandException.class)
    public void testGeneratingForDrawingLineWithTooManyArguments() throws InvalidCommandException {
        factory.getCommandFor("L 1 2 5 1 2");
    }

    @Test(expected = InvalidCommandException.class)
    public void testGeneratingForDrawingLineWithTooFewArguments() throws InvalidCommandException {
        factory.getCommandFor("L 1 2 5");
    }

    @Test(expected = InvalidCommandException.class)
    public void testGeneratingForDrawingLineWithInvalidArguments() throws InvalidCommandException {
        factory.getCommandFor("L 1 2 6 a");
    }

    @Test
    public void testGeneratingCommandForDrawingRectangle() throws InvalidCommandException {
        Command drawRectangleCommand = new Rectangle(new Coordinate(), new Coordinate());
        Command drawRectangle = factory.getCommandFor(DRAW_RECTANGLE);
        assertNotNull(drawRectangle);
        assertTrue(drawRectangleCommand.getCommandString().equals(drawRectangle.getCommandString()));
    }

    @Test(expected = InvalidCommandException.class)
    public void testGeneratingForRectangleWithTooManyArguments() throws InvalidCommandException {
        factory.getCommandFor("R 1 2 6 9 8");
    }

    @Test(expected = InvalidCommandException.class)
    public void testGeneratingForRectangleWithTooFewArguments() throws InvalidCommandException {
        factory.getCommandFor("R 1 2 6");
    }

    @Test(expected = InvalidCommandException.class)
    public void testGeneratingForRectangleWithInvalidArguments() throws InvalidCommandException {
        factory.getCommandFor("R 1 2 6 a");
    }

    @Test
    public void testGeneratingCommandForFillRegion() throws InvalidCommandException {
        Command fillRegionCommand = new FillRegion(new Coordinate(), 'x');
        Command fillRegion = factory.getCommandFor(FILL_REGION);
        assertNotNull(fillRegion);
        assertTrue(fillRegionCommand.getCommandString().equals(fillRegion.getCommandString()));
    }

    @Test(expected = InvalidCommandException.class)
    public void testGeneratingForFillRegionWithTooManyArguments() throws InvalidCommandException {
        factory.getCommandFor("B 1 2 6 o");
    }

    @Test(expected = InvalidCommandException.class)
    public void testGeneratingForFillRegionWithTooFewArguments() throws InvalidCommandException {
        factory.getCommandFor("B 1 o");
    }

    @Test(expected = InvalidCommandException.class)
    public void testGeneratingForFillRegionWithInvalidArguments() throws InvalidCommandException {
        factory.getCommandFor("B 1 a o");
    }

    @Test
    public void testGeneratingCommandForQuitting() throws InvalidCommandException {
        Command quitCommand = new Quit();
        Command quit = factory.getCommandFor(QUIT);
        assertNotNull(quit);
        assertTrue(quitCommand.getCommandString().equals(quit.getCommandString()));
    }

    @Test(expected = InvalidCommandException.class)
    public void testThrowsExceptionForInvalidCommand() throws InvalidCommandException {
        factory.getCommandFor("S 1 3 5");
    }
}
