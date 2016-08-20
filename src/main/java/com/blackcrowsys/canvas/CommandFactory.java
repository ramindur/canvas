package com.blackcrowsys.canvas;


import com.blackcrowsys.canvas.command.*;

public class CommandFactory {
    public Command getCommandFor(String arguments) {
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
        }
        return null;
    }
}
