package com.blackcrowsys.canvas;


import com.blackcrowsys.canvas.command.Command;
import com.blackcrowsys.canvas.exception.InvalidCommandException;

public class CanvasProcessor {

    private CommandFactory commandFactory;

    public CanvasProcessor(){
        commandFactory = new CommandFactory();
    }

    public CanvasProcessor(CommandFactory commandFactory){
        this.commandFactory = commandFactory;
    }

    /**
     * Creates a Command object using command factory that represents the arguments, and executes it's
     * execute method.
     * @param arguments the command line argument
     * @return the command object
     */
    public Command forCommand(String arguments) throws InvalidCommandException {
        Command command = commandFactory.getCommandFor(arguments);
        command.execute();
        return command;
    }
}
