package com.blackcrowsys.canvas.command;


import com.blackcrowsys.canvas.command.Command;

public class Create implements Command {

    private static final String ID = "CREATE";

    public void execute() {

    }

    @Override
    public String getCommandString() {
        return ID;
    }


}
