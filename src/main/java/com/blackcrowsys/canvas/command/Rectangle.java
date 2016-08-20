package com.blackcrowsys.canvas.command;

/**
 * Created by ramindursingh on 20/08/2016.
 */
public class Rectangle implements Command {

    private static final String ID = "DRAW_RECTANGLE";
    @Override
    public void execute() {

    }

    @Override
    public String getCommandString() {
        return ID;
    }
}
