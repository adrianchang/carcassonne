package edu.cmu.cs.cs214.hw4.gui;

import edu.cmu.cs.cs214.hw4.core.Game;
import edu.cmu.cs.cs214.hw4.core.GameActionListener;
import edu.cmu.cs.cs214.hw4.core.Map;
import edu.cmu.cs.cs214.hw4.core.Player;

import javax.swing.*;
import java.util.ArrayList;

/**
 * setup frame
 * responsible for setting up players
 */
public class SetUpFrame extends JFrame{
    private Game g;
    private ArrayList<Player> playerList;
    //GUI components
    private JTextArea responseArea;

    /**
     * setup frame constructor
     * @param g the game library
     */
    public SetUpFrame (Game g){
        this.g = g;
        setTitle("Set Players");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        InputPanel inputPanel = new  InputPanel("1");
        InputPanel inputPanel2 = new InputPanel("2");
        InputPanel inputPanel3 = new InputPanel("3");
        InputPanel inputPanel4 = new InputPanel("4");
        InputPanel inputPanel5 = new InputPanel("5");
        responseArea = new JTextArea();
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        this.add(inputPanel);
        this.add(inputPanel2);
        this.add(inputPanel3);
        this.add(inputPanel4);
        this.add(inputPanel5);
        JButton startGameButton = new JButton("start the game");
        startGameButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                createAndShowGameBoard();
            });
        });
        this.add(startGameButton);
    }

    private void createAndShowGameBoard(){
        JFrame gameFrame = new GameFrame(g);
        gameFrame.pack();
        gameFrame.setResizable(false);
        gameFrame.setVisible(true);
        this.setVisible(false);
    }

    private class InputPanel extends JPanel {
        private static final String SOLVE_BUTTON_TEXT = "Add";
        private static final String PROMPT = "set name for player";
        private static final int INPUT_FIELD_WIDTH = 40;
        private JButton addButton;
        private JTextField input;

        InputPanel(String playerNum) {
            addButton = new JButton(SOLVE_BUTTON_TEXT);
            input = new JTextField(PROMPT + playerNum, INPUT_FIELD_WIDTH);
            addButton.addActionListener(e -> {
                String newPlayerName = input.getText();
                g.addPlayers(newPlayerName);
                addButton.setText("Added");
                addButton.setEnabled(false);
                pack();
            });
            this.add(input);
            this.add(addButton);
        }
    }

}
