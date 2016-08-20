package com.blackcrowsys.canvas.command;

import com.blackcrowsys.canvas.Canvas;

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
