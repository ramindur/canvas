package com.blackcrowsys.canvas.command;

import com.blackcrowsys.canvas.Canvas;
import com.blackcrowsys.canvas.exception.CanvasOperationException;

public class DisplayCanvas implements Command{

    private static final String ID = "DISPLAY_CANVAS";

    @Override
    public Canvas execute(Canvas canvas) throws CanvasOperationException {
        canvas.displayCanvas();
        return canvas;
    }

    @Override
    public String getCommandString() {
        return ID;
    }
}
