package com.blackcrowsys.canvas;


import com.blackcrowsys.canvas.command.*;
import com.blackcrowsys.canvas.exception.InvalidCommandException;

public class CommandFactory {

    private static final String CREATE = "C";
    private static final String LINE = "L";
    private static final String RECTANGLE = "R";
    private static final String FILL = "B";
    private static final String QUIT = "Q";


    /**
     * Creates a command based on the arguments.
     *
     * @param arguments
     * @return
     * @throws InvalidCommandException
     */
    public Command getCommandFor(String arguments) throws InvalidCommandException {
        String[] args = arguments.split("\\s+");
        switch (args[0]) {
            case CREATE:
                if (args.length != 3)
                    throw new InvalidCommandException("Invalid number of arguments for create command");
                try {
                    int width = Integer.parseInt(args[1]);
                    int height = Integer.parseInt(args[2]);
                    return new Create(width, height);
                } catch (NumberFormatException e) {
                    throw new InvalidCommandException(e.getMessage());
                }
            case LINE:
                if (args.length != 5)
                    throw new InvalidCommandException("Invalid number of arguments for draw line command");
                try {
                    Coordinate from = new Coordinate(args[1], args[2]);
                    Coordinate to = new Coordinate(args[3], args[4]);
                    return new Line(from, to);
                } catch (NumberFormatException e) {
                    throw new InvalidCommandException(e.getMessage());
                }
            case RECTANGLE:
                if (args.length != 5)
                    throw new InvalidCommandException("Invalid number of arguments for rectangle");
                try {
                    Coordinate topLeftCorner = new Coordinate(args[1], args[2]);
                    Coordinate bottomRightCorner = new Coordinate(args[3], args[4]);
                    return new Rectangle(topLeftCorner, bottomRightCorner);
                } catch (NumberFormatException e) {
                    throw new InvalidCommandException(e.getMessage());
                }
            case FILL:
                if (args.length != 4)
                    throw new InvalidCommandException("Invalid number of arguments for fill region");
                try {
                    Coordinate location = new Coordinate(args[1], args[2]);
                    return new FillRegion(location, args[3].charAt(0));
                } catch (NumberFormatException e) {
                    throw new InvalidCommandException(e.getMessage());
                }
            case QUIT:
                return new Quit();
            default:
                throw new InvalidCommandException("Command not recognize");
        }
    }
}
