package com.blackcrowsys.canvas.command;

/**
 * Created by ramindursingh on 20/08/2016.
 */
public class FillRegion implements  Command {

    private static final String ID = "FILL_REGION";

    @Override
    public void execute() {

    }

    @Override
    public String getCommandString() {
        return ID;
    }
}
