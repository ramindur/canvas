package com.blackcrowsys.canvas.command;

import com.blackcrowsys.canvas.Canvas;
import com.blackcrowsys.canvas.Coordinate;

/**
 * Created by ramindursingh on 20/08/2016.
 */
public class FillRegion implements  Command {

    private static final String ID = "FILL_REGION";

    private Coordinate location;

    private char drawCharacter;

    public FillRegion(Coordinate location, char drawCharacter) {
        this.location = location;
        this.drawCharacter = drawCharacter;
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
