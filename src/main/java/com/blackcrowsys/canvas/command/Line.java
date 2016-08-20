package com.blackcrowsys.canvas.command;


import com.blackcrowsys.canvas.command.Command;

public class Line implements Command {

    private static final String ID = "DRAW_LINE";

    public void execute() {

    }

    @Override
    public String getCommandString() {
        return ID;
    }
}
