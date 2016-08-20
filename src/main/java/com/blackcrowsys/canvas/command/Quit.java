package com.blackcrowsys.canvas.command;

/**
 * Created by ramindursingh on 20/08/2016.
 */
public class Quit implements Command {

    private static final String ID = "QUIT";

    @Override
    public void execute() {

    }

    @Override
    public String getCommandString() {
        return ID;
    }
}
