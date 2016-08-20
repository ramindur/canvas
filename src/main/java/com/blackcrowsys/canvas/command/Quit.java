package com.blackcrowsys.canvas.command;

import com.blackcrowsys.canvas.Canvas;

/**
 * Created by ramindursingh on 20/08/2016.
 */
public class Quit implements Command {

    private static final String ID = "QUIT";

    @Override
    public Canvas execute(Canvas canvas) {
        return null;
    }

    @Override
    public String getCommandString() {
        return ID;
    }
}
