# Canvas
A simple canvas for drawing basic objects, such as lines and rectangles.

This is for a code exercise.

## Requirements

* Supports basic commands to create canvas, draw line, draw rectangle, and fill a region
* Command line interface

## Running the program
Cloning this project will create a folder called "canvas." Change to this folder and run "mvn package" to create a JAR file called canvas-1.0-SNAPSHOT.jar in target folder.

If you are in canvas folder, type "java -jar target/canvas-1.0-SNAPSHOT.jar" to run the Main programme.

The commands supported are:
* C <width> <height> (i.e. C 20 10) - to create a canvas
* L x1 y1 x2 y2 (i.e. L 1 2 1 5) - to draw a line only vertical and horizontal are supported
* R x1 y1 x2 y2 (i.e. R 1 2 4 5) - to draw a rectangle with top left corner and bottom right corner
* B x y c (i.e. B 5 6 o to fill with 'o') - to fill a region with the given character
* Q to quit the interactive shell


## Project Details
Ths uses Command design pattern to interpret the user's command line input. Based on the input, it creates Command objects using Factory pattern.

* The Main class is used to run the interactive shell
* CanvasFactory is used to create concrete instance of Canvas, currently ConsoleCanvas
* CommandFactory is used to create Command objects, in command package, based on the user input
* CanvasProcessor is the main entry point for the Main class; it is the wrapper that takes user input, creates Command objects and executes it's execute methods
* ConsoleCanvas is the implementation of Canvas interface and does all the basic drawing, etc.

## Dependancies
There are only two dependancies: JUnit and Mockito - all for testing and mocking objects.




