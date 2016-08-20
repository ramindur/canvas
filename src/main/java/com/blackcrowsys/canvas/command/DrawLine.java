package com.blackcrowsys.canvas.command;


import com.blackcrowsys.canvas.Canvas;
import com.blackcrowsys.canvas.Coordinate;
import com.blackcrowsys.canvas.exception.CanvasOperationException;

public class DrawLine implements Command {

    private static final String ID = "DRAW_LINE";

    private Coordinate from;

    private Coordinate to;

    public DrawLine(Coordinate from, Coordinate to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public Canvas execute(Canvas canvas) throws CanvasOperationException {
        return canvas.drawLine(from, to);
    }

    @Override
    public String getCommandString() {
        return ID;
    }
}
