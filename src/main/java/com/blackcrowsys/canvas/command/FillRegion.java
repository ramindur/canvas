package com.blackcrowsys.canvas.command;

import com.blackcrowsys.canvas.Canvas;
import com.blackcrowsys.canvas.Coordinate;

public class FillRegion implements Command {

    private static final String ID = "FILL_REGION";

    private Coordinate location;

    private char fillCharacter;

    public FillRegion(Coordinate location, char fillCharacter) {
        this.location = location;
        this.fillCharacter = fillCharacter;
    }

    @Override
    public Canvas execute(Canvas canvas) {

        return canvas.fillRegion(location, fillCharacter);
    }

    @Override
    public String getCommandString() {
        return ID;
    }
}
