package com.blackcrowsys.canvas;


import com.blackcrowsys.canvas.command.*;
import com.blackcrowsys.canvas.exception.CanvasOperationException;
import com.blackcrowsys.canvas.exception.InvalidCommandException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class TestCommandFactory {
    private static final String CREATE_CANVAS = "C 20 4";
    private static final String DRAW_LINE = "L 1 2 6 2";
    private static final String DRAW_RECTANGLE = "R 16 1 20 3";
    private static final String FILL_REGION = "B 10 3 o";
    private static final String QUIT = "Q";

    private CommandFactory commandFactory;

    private CanvasFactory canvasFactory = mock(CanvasFactory.class);


    @Before
    public void setUp() {

        commandFactory = new CommandFactory(canvasFactory);
    }

    @Test
    public void testGeneratingCommandForCreatingCanvas() throws InvalidCommandException, CanvasOperationException {
        Command createCanvasCommand = new CreateCanvas(20, 4, canvasFactory);
        Command createCanvas = commandFactory.getCommandFor(CREATE_CANVAS);
        assertNotNull(createCanvas);
        assertTrue(createCanvasCommand.getCommandString().equals(createCanvas.getCommandString()));
    }

    @Test(expected = InvalidCommandException.class)
    public void testGeneratingCreateCommandWithTooManyArguments() throws InvalidCommandException, CanvasOperationException {
        commandFactory.getCommandFor("C 20 4 6");
    }

    @Test(expected = InvalidCommandException.class)
    public void testGeneratingCreateCommandWithTooFewArguments() throws InvalidCommandException, CanvasOperationException {
        commandFactory.getCommandFor("C 20");
    }

    @Test(expected = InvalidCommandException.class)
    public void testGeneratingCreateCommandWithInvalidArguments() throws InvalidCommandException, CanvasOperationException {
        commandFactory.getCommandFor("C 20 a");
    }

    @Test
    public void testGeneratingCommandForDrawingLine() throws InvalidCommandException, CanvasOperationException {
        Command drawLineCommand = new DrawLine(new Coordinate(), new Coordinate());
        Command drawLine = commandFactory.getCommandFor(DRAW_LINE);
        assertNotNull(drawLine);
        assertTrue(drawLineCommand.getCommandString().equals(drawLine.getCommandString()));
    }

    @Test(expected = InvalidCommandException.class)
    public void testGeneratingForDrawingLineWithTooManyArguments() throws InvalidCommandException, CanvasOperationException {
        commandFactory.getCommandFor("L 1 2 5 1 2");
    }

    @Test(expected = InvalidCommandException.class)
    public void testGeneratingForDrawingLineWithTooFewArguments() throws InvalidCommandException, CanvasOperationException {
        commandFactory.getCommandFor("L 1 2 5");
    }

    @Test(expected = InvalidCommandException.class)
    public void testGeneratingForDrawingLineWithInvalidArguments() throws InvalidCommandException, CanvasOperationException {
        commandFactory.getCommandFor("L 1 2 6 a");
    }

    @Test
    public void testGeneratingCommandForDrawingRectangle() throws InvalidCommandException, CanvasOperationException {
        Command drawRectangleCommand = new DrawRectangle(new Coordinate(), new Coordinate());
        Command drawRectangle = commandFactory.getCommandFor(DRAW_RECTANGLE);
        assertNotNull(drawRectangle);
        assertTrue(drawRectangleCommand.getCommandString().equals(drawRectangle.getCommandString()));
    }

    @Test(expected = InvalidCommandException.class)
    public void testGeneratingForRectangleWithTooManyArguments() throws InvalidCommandException, CanvasOperationException {
        commandFactory.getCommandFor("R 1 2 6 9 8");
    }

    @Test(expected = InvalidCommandException.class)
    public void testGeneratingForRectangleWithTooFewArguments() throws InvalidCommandException, CanvasOperationException {
        commandFactory.getCommandFor("R 1 2 6");
    }

    @Test(expected = InvalidCommandException.class)
    public void testGeneratingForRectangleWithInvalidArguments() throws InvalidCommandException, CanvasOperationException {
        commandFactory.getCommandFor("R 1 2 6 a");
    }

    @Test
    public void testGeneratingCommandForFillRegion() throws InvalidCommandException, CanvasOperationException {
        Command fillRegionCommand = new FillRegion(new Coordinate(), 'x');
        Command fillRegion = commandFactory.getCommandFor(FILL_REGION);
        assertNotNull(fillRegion);
        assertTrue(fillRegionCommand.getCommandString().equals(fillRegion.getCommandString()));
    }

    @Test(expected = InvalidCommandException.class)
    public void testGeneratingForFillRegionWithTooManyArguments() throws InvalidCommandException, CanvasOperationException {
        commandFactory.getCommandFor("B 1 2 6 o");
    }

    @Test(expected = InvalidCommandException.class)
    public void testGeneratingForFillRegionWithTooFewArguments() throws InvalidCommandException, CanvasOperationException {
        commandFactory.getCommandFor("B 1 o");
    }

    @Test(expected = InvalidCommandException.class)
    public void testGeneratingForFillRegionWithInvalidArguments() throws InvalidCommandException, CanvasOperationException {
        commandFactory.getCommandFor("B 1 a o");
    }

    @Test
    public void testGeneratingCommandForQuitting() throws InvalidCommandException, CanvasOperationException {
        Command quitCommand = new Quit();
        Command quit = commandFactory.getCommandFor(QUIT);
        assertNotNull(quit);
        assertTrue(quitCommand.getCommandString().equals(quit.getCommandString()));
    }

    @Test(expected = InvalidCommandException.class)
    public void testThrowsExceptionForInvalidCommand() throws InvalidCommandException, CanvasOperationException {
        commandFactory.getCommandFor("S 1 3 5");
    }
}
