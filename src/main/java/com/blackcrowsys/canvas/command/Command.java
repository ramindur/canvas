package com.blackcrowsys.canvas.command;


import com.blackcrowsys.canvas.Canvas;

public interface Command {

    Canvas execute(Canvas canvas);

    String getCommandString();

}
