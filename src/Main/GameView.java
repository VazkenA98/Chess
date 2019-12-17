package Main;

import game.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class GameView implements ActionListener {
    private BoardPanel boardView;
    private GameController controller;
    private JFrame mainWindow;
    private JPanel mainPanel;
    private JLabel currentPlayerLabel;
    private JButton forfeitButton;

    public GameView(GameController controller, String gameTitle) {
        this.controller = controller;
        controller.setView(this);

        mainWindow = new JFrame(gameTitle);
        mainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainWindow.setLocationByPlatform(true);

        mainPanel = new JPanel(new BorderLayout());

        boardView = new BoardPanel(controller);
        mainPanel.add(boardView, BorderLayout.CENTER);

        addToolbar();

        mainWindow.add(mainPanel);
        mainWindow.pack();
        mainWindow.setMinimumSize(mainWindow.getSize());
    }

    private void addToolbar() {
        JToolBar toolbar = new JToolBar();


        forfeitButton = new JButton("Surrender");
        forfeitButton.setActionCommand("Surrender");
        forfeitButton.addActionListener(this);
        toolbar.add(forfeitButton);


        toolbar.add(Box.createHorizontalGlue());

        currentPlayerLabel = new JLabel("current player");
        toolbar.add(currentPlayerLabel);

        mainPanel.add(toolbar, BorderLayout.NORTH);
    }

    public void setCurrentPlayer(String playerName) {
        currentPlayerLabel.setText("Current Player: " + playerName);
        forfeitButton.setText(playerName + " Surrender");
    }

    public void setVisible(boolean visible) {
        this.mainWindow.setVisible(visible);
    }

    public BoardPanel getBoardView() {
        return boardView;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getActionCommand().equals("Surrender")) {
            controller.surrender();
        }
    }


    public void alertInCheckMate(Player player) {
        JOptionPane.showMessageDialog(this.mainWindow,
                "Player " + player.toString() + " is in checkmate!",
                "Game over",
                JOptionPane.INFORMATION_MESSAGE);

        this.mainWindow.dispatchEvent(new WindowEvent(this.mainWindow, WindowEvent.WINDOW_CLOSING));
    }

    public void alertInCheck(Player player) {
        JOptionPane.showMessageDialog(this.mainWindow,
                "Player " + player.toString() + " is in check!",
                "Game over",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void alertForSurrender(Player who) {
        JOptionPane.showMessageDialog(this.mainWindow,
                "Game was Surrendered by: " + who.toString(),
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE);

        this.mainWindow.dispatchEvent(new WindowEvent(this.mainWindow, WindowEvent.WINDOW_CLOSING));
    }


}
