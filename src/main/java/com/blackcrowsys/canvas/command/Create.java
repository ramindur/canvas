package com.blackcrowsys.canvas.command;


import com.blackcrowsys.canvas.Canvas;
import com.blackcrowsys.canvas.CanvasFactory;
import com.blackcrowsys.canvas.exception.CanvasException;

public class Create implements Command {

    private static final String ID = "CREATE";

    private int width;

    private int height;

    private CanvasFactory canvasFactory;

    public Create(int width, int height, CanvasFactory canvasFactory) throws CanvasException {
        if (width <= 0)
            throw new CanvasException("Width cannot be less than or equal to 0");
        this.width = width;

        if (height <= 0)
            throw new CanvasException("Height cannot be less than or equal to 0");
        this.height = height;

        if (canvasFactory == null) {
            throw new CanvasException("Canvas factory cannot be null");
        }
        this.canvasFactory = canvasFactory;
    }

    @Override
    public Canvas execute(Canvas canvas) {
        return canvasFactory.create(width, height);
    }

    @Override
    public String getCommandString() {
        return ID;
    }
}
