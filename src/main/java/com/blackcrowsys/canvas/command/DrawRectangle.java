package com.blackcrowsys.canvas.command;

import com.blackcrowsys.canvas.Canvas;
import com.blackcrowsys.canvas.Coordinate;
import com.blackcrowsys.canvas.exception.CanvasOperationException;

public class DrawRectangle implements Command {

    private static final String ID = "DRAW_RECTANGLE";

    private Coordinate topLeftCorner;

    private Coordinate bottomRightCorner;

    public DrawRectangle(Coordinate topLeftCorner, Coordinate bottomRightCorner) {
        this.topLeftCorner = topLeftCorner;
        this.bottomRightCorner = bottomRightCorner;
    }

    @Override
    public Canvas execute(Canvas canvas) throws CanvasOperationException {
        return canvas.drawRectangle(topLeftCorner, bottomRightCorner);
    }

    @Override
    public String getCommandString() {
        return ID;
    }
}
