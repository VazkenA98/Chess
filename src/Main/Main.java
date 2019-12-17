package Main;

import game.GameController;

import game.Game;
import game.GameFactory;

import javax.swing.*;

public class Main {
    public static void main(String args[]) {


        String player1Name = JOptionPane.showInputDialog(null,
                "White Player Name?",
                "Input Dialog Box", JOptionPane.INFORMATION_MESSAGE);
        String player2Name = JOptionPane.showInputDialog(null,
                "Black Player Name?",
                "Input Dialog Box", JOptionPane.INFORMATION_MESSAGE);
        String[] options = {"Normal","Custem"};
        String n = (String)JOptionPane.showInputDialog(null, "Do you like turtles??",
                "Chess mode", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);


        Tournament model = new Tournament(
                new Player(Player.PlayerColor.WHITE, player1Name),
                new Player(Player.PlayerColor.BLACK, player2Name));

        GameController gameController = new GameController(model);
        GameView gameView = new GameView(gameController, "Chess view");
        Game chessModel = GameFactory.makeGame(model.getPlayer1(), model.getPlayer2(), gameView, n, "Chess");
        gameController.setModel(chessModel);
        gameView.setVisible(true);

    }
}
