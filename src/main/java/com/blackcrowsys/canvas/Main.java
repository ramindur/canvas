package com.blackcrowsys.canvas;

import com.blackcrowsys.canvas.command.Command;
import com.blackcrowsys.canvas.command.DisplayCanvas;
import com.blackcrowsys.canvas.exception.CanvasOperationException;
import com.blackcrowsys.canvas.exception.InvalidCommandException;

public class Main {

    private CanvasProcessor processor;

    private Canvas canvas;

    private boolean continueShell;

    public Main() {
        processor = new CanvasProcessor();
        continueShell = true;
    }

    public static void main(String[] args) {
        Main interactiveShell = new Main();
        interactiveShell.run();
    }

    private void run() {
        Command displayConsole = new DisplayCanvas();
        while (continueShell) {
            System.out.print("\nenter command: ");
            String userInput = System.console().readLine();
            if(userInput.isEmpty()){
                System.out.print("\nplease enter a valid command");
            }else{
                try {
                    canvas = processor.runCommandOnCanvas(userInput, canvas);
                    if (canvas == null) {
                        continueShell = false;
                    } else {
                        displayConsole.execute(canvas);
                    }
                } catch (CanvasOperationException | InvalidCommandException e) {
                    System.out.print("\n" + e.getMessage());
                }
            }
        }
    }
}
