# Canvas
A simple canvas for drawing basic objects, such as lines and rectangles.
It was developed using IntelliJ and can be imported into IntelliJ as a Maven project.

## Requirements

* Supports basic commands to create canvas, draw line, draw rectangle, and fill a region
* Command line interface
* Interactive shell

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
There are only two dependencies: JUnit and Mockito - all for testing and mocking objects.
These are included in the POM.

## Design Rationale
I decided to use Command design pattern as this fits very well the problem and gives greater flexibility to extend it, either by creating additional commands or implementing a different Canvas.

By separating the commands from the canvas drawing, each can be developed and implemented independently of each other.

The ConsoleCanvas, called this simply because it works against a shell console but could have been easily called CharCanvas, uses a 2D char array as this is the simplest solution for the given requirement.

The Command interface represents the instructions that should be executed by the Canvas object. This object only knows what methods of the Canvas class to call to carry out the user's request. Additional commands can be easily supported by implementing additional Command classes. However, in some cases, further work may be required in Canvas implementations, i.e. supporting diagonal lines.

Using the factory to either create Command instances or Canvas instance decouples the main classes and allows for testing using mocks, as well as for Dependency Injection if used in DI frameworks.
