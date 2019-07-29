package edu.cmu.cs.cs214.hw4.gui;

import edu.cmu.cs.cs214.hw4.core.Game;

import javax.swing.*;

/**
 * Entry point of your program
 * control the flow
 */
public class Main {
    private static Game g;

    /**
     * main executing method
     * @param args the args to take from terminal
     */
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }catch(Exception e){
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            createAndShowSetUpBoard();
        });
    }

    private static void createAndShowSetUpBoard() {
        //add frame and set its closing operation
        Game g = new Game();
        JFrame setUpframe = new SetUpFrame(g);

        //display the JFrame
        setUpframe.pack();
        setUpframe.setResizable(false);
        setUpframe.setVisible(true);
        // TODO: Start implementing your GUI here.

        // Create a game core and do any necessary setup.

        // Create the game UI and setup. You will need to write
        // your own GUI classes under the "gui" package.

    }
}
