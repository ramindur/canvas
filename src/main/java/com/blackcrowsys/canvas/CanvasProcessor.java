package com.blackcrowsys.canvas;


import com.blackcrowsys.canvas.command.Command;
import com.blackcrowsys.canvas.exception.InvalidCommandException;

public class CanvasProcessor {

    private CommandFactory commandFactory;

    public CanvasProcessor() {
        commandFactory = new CommandFactory();
    }

    public CanvasProcessor(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    /**
     * Generates and executes command on the canvas based on the arguments.
     *
     * @param arguments the command and arguments
     * @param canvas    the canvas
     * @return the updated canvas
     * @throws InvalidCommandException if the command, arguments are invalid
     */
    public Canvas runCommandOnCanvas(String arguments, Canvas canvas) throws InvalidCommandException {
        Command command = commandFactory.getCommandFor(arguments);
        return command.execute(canvas);
    }
}
