package com.blackcrowsys.canvas.command;


import com.blackcrowsys.canvas.Coordinate;
import com.blackcrowsys.canvas.command.Command;

public class Line implements Command {

    private static final String ID = "DRAW_LINE";

    private Coordinate from;

    private Coordinate to;

    public Line(Coordinate from, Coordinate to) {
        this.from = from;
        this.to = to;
    }

    public void execute() {

    }

    @Override
    public String getCommandString() {
        return ID;
    }
}
