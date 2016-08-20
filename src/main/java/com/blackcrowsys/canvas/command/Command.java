package com.blackcrowsys.canvas.command;


import com.blackcrowsys.canvas.Canvas;
import com.blackcrowsys.canvas.exception.CanvasOperationException;

public interface Command {

    Canvas execute(Canvas canvas) throws CanvasOperationException;

    String getCommandString();

}
