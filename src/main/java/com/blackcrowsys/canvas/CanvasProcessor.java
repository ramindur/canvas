package com.blackcrowsys.canvas;


import com.blackcrowsys.canvas.command.Command;
import com.blackcrowsys.canvas.exception.CanvasException;
import com.blackcrowsys.canvas.exception.InvalidCommandException;

public class CanvasProcessor {

    private static final String CREATE = "CREATE";

    private static final String QUIT = "QUIT";

    private CommandFactory commandFactory;

    public CanvasProcessor() {
        commandFactory = new CommandFactory(new CanvasFactory());
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
    public Canvas runCommandOnCanvas(String arguments, Canvas canvas) throws InvalidCommandException, CanvasException {
        Command command = commandFactory.getCommandFor(arguments);
        if (canvas == null) {
            if (command.getCommandString().equals(CREATE) || command.getCommandString().equals(QUIT)) {
                return command.execute(canvas);
            } else {
                throw new InvalidCommandException("Canvas does not exist");
            }
        } else {
            return command.execute(canvas);
        }
    }
}
