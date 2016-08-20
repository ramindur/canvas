package com.blackcrowsys.canvas;

import com.blackcrowsys.canvas.command.Command;
import com.blackcrowsys.canvas.exception.InvalidCommandException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;


public class TestCanvasProcessor {

    private static final String CREATE_CANVAS = "C 20 4";

    private CanvasProcessor processor;

    private CommandFactory commandFactory = mock(CommandFactory.class);

    private Command command = mock(Command.class);

    @Before
    public void setUp() throws InvalidCommandException {
        processor = new CanvasProcessor(commandFactory);
        when(commandFactory.getCommandFor(CREATE_CANVAS)).thenReturn(command);
    }

    @Test
    public void testProcessorCreatesCommandAndExecutes() throws InvalidCommandException {
        Command aCommand = processor.forCommand(CREATE_CANVAS);
        assertNotNull(aCommand);
        verify(aCommand, times(1)).execute();
    }
}
