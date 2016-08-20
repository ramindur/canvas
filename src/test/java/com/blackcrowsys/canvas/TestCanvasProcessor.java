package com.blackcrowsys.canvas;

import com.blackcrowsys.canvas.command.Command;
import com.blackcrowsys.canvas.command.FillRegion;
import com.blackcrowsys.canvas.exception.CanvasOperationException;
import com.blackcrowsys.canvas.exception.InvalidCommandException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;


public class TestCanvasProcessor {

    private static final String CREATE_CANVAS = "C 20 4";

    private static final String FILL_REGION = "B 10 6 o";

    private CanvasProcessor processor;

    private CommandFactory commandFactory = mock(CommandFactory.class);

    private Command command = mock(Command.class);

    private Canvas canvas = mock(Canvas.class);

    @Before
    public void setUp() throws InvalidCommandException, CanvasOperationException {
        processor = new CanvasProcessor(commandFactory);
        when(commandFactory.getCommandFor(CREATE_CANVAS)).thenReturn(command);
        when(command.execute(canvas)).thenReturn(canvas);
    }

    @Test
    public void testProcessorCreatesCommandAndExecutes() throws InvalidCommandException, CanvasOperationException {
        Canvas returnedCanvas = processor.runCommandOnCanvas(CREATE_CANVAS, canvas);
        assertNotNull(returnedCanvas);
        verify(command, times(1)).execute(canvas);
    }

    @Test(expected = InvalidCommandException.class)
    public void testRunningDrawCommandBeforeCanvasCreated() throws InvalidCommandException, CanvasOperationException {
        when(commandFactory.getCommandFor(FILL_REGION)).thenReturn(new FillRegion(new Coordinate(), 'o'));
        processor.runCommandOnCanvas(FILL_REGION, null);
    }
}
