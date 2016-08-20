package com.blackcrowsys.canvas;


import com.blackcrowsys.canvas.command.*;
import com.blackcrowsys.canvas.exception.InvalidCommandException;

public class CommandFactory {
    public Command getCommandFor(String arguments) throws InvalidCommandException {
        String[] args = arguments.split("\\s+");
        switch (args[0]){
            case "C":
                return new Create();
            case "L":
                return new Line();
            case "R":
                return new Rectangle();
            case "B":
                return new FillRegion();
            case "Q":
                return new Quit();
            default:
                throw new InvalidCommandException("Command not recognize");
        }
    }
}
