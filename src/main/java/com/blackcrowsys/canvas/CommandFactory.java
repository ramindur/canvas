package com.blackcrowsys.canvas;


import com.blackcrowsys.canvas.command.*;
import com.blackcrowsys.canvas.exception.CanvasOperationException;
import com.blackcrowsys.canvas.exception.InvalidCommandException;

public class CommandFactory {

    private static final String CREATE = "C";
    private static final String LINE = "L";
    private static final String RECTANGLE = "R";
    private static final String FILL = "B";
    private static final String QUIT = "Q";

    private CanvasFactory canvasFactory;

    public CommandFactory(CanvasFactory canvasFactory){
        this.canvasFactory = canvasFactory;
    }


    /**
     * Creates a command based on the arguments.
     *
     * @param arguments
     * @return
     * @throws InvalidCommandException
     */
    public Command getCommandFor(String arguments) throws InvalidCommandException, CanvasOperationException {
        String[] args = arguments.split("\\s+");
        switch (args[0]) {
            case CREATE:
                if (args.length != 3)
                    throw new InvalidCommandException("Invalid number of arguments to create canvas");
                try {
                    int width = Integer.parseInt(args[1]);
                    int height = Integer.parseInt(args[2]);
                    return new CreateCanvas(width, height, canvasFactory);
                } catch (NumberFormatException e) {
                    throw new InvalidCommandException(e.getMessage() + " must be a number");
                }
            case LINE:
                if (args.length != 5)
                    throw new InvalidCommandException("Invalid number of arguments to draw line");
                try {
                    Coordinate from = new Coordinate(args[1], args[2]);
                    Coordinate to = new Coordinate(args[3], args[4]);
                    return new DrawLine(from, to);
                } catch (NumberFormatException e) {
                    throw new InvalidCommandException(e.getMessage() + " must be a number");
                }
            case RECTANGLE:
                if (args.length != 5)
                    throw new InvalidCommandException("Invalid number of arguments draw rectangle");
                try {
                    Coordinate topLeftCorner = new Coordinate(args[1], args[2]);
                    Coordinate bottomRightCorner = new Coordinate(args[3], args[4]);
                    return new DrawRectangle(topLeftCorner, bottomRightCorner);
                } catch (NumberFormatException e) {
                    throw new InvalidCommandException(e.getMessage() + " must be a number");
                }
            case FILL:
                if (args.length != 4)
                    throw new InvalidCommandException("Invalid number of arguments to fill a region");
                try {
                    Coordinate location = new Coordinate(args[1], args[2]);
                    return new FillRegion(location, args[3].charAt(0));
                } catch (NumberFormatException e) {
                    throw new InvalidCommandException(e.getMessage() + " must be a number");
                }
            case QUIT:
                return new Quit();
            default:
                throw new InvalidCommandException("Command not recognized");
        }
    }
}
