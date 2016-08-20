package com.blackcrowsys.canvas.command;

import com.blackcrowsys.canvas.Canvas;
import com.blackcrowsys.canvas.Coordinate;
import com.sun.org.apache.regexp.internal.RE;
import org.w3c.dom.css.Rect;

/**
 * Created by ramindursingh on 20/08/2016.
 */
public class Rectangle implements Command {

    private static final String ID = "DRAW_RECTANGLE";

    private Coordinate topLeftCorner;

    private Coordinate bottomRightCorner;

    public Rectangle(Coordinate topLeftCorner, Coordinate bottomRightCorner){
        this.topLeftCorner = topLeftCorner;
        this.bottomRightCorner = bottomRightCorner;
    }

    @Override
    public Canvas execute(Canvas canvas) {
        return null;
    }

    @Override
    public String getCommandString() {
        return ID;
    }
}
