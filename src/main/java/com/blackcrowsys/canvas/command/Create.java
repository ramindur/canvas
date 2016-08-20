package com.blackcrowsys.canvas.command;


import com.blackcrowsys.canvas.command.Command;
import com.blackcrowsys.canvas.exception.InvalidCommandException;

public class Create implements Command {

    private static final String ID = "CREATE";

    private int width;

    private int height;

    public Create(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void execute() {

    }

    @Override
    public String getCommandString() {
        return ID;
    }


}
