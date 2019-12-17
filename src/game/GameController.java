package game;


//import ErrorController.ActionCannotBeUndoneException;
import Main.GameView;
import Main.Tournament;
import board.BoardLocation;
import game.Game;
import game.GameMove;

import java.util.ArrayList;

public class GameController {
    private GameView view;
    private Game game;
    private ArrayList<GameMove> lastActions;

    public GameController(Tournament t) {
        lastActions = new ArrayList<GameMove>();
    }

    public void setModel(Game model) {
        this.game = model;
    }

    public void setView(GameView view) {
        this.view = view;
    }

    public void move(BoardLocation from, BoardLocation to) {
        boolean ret = game.canMove(
                game.getBoard(),
                game.getCurrentPlayer(),
                from, to);

        if (ret == true) {
            GameMove move = new GameMove(from, to, game);
            move.apply();

            lastActions.add(move);


            checkStatus();
        } else {

        }
    }

    public void highlightPossibleMoves(BoardLocation from) {
        game.highlightPossibleMoves(from);
    }

    private void checkStatus() {
        if (view != null) {
            if (game.inCheckMate(game.getCurrentPlayer())) {
                view.alertInCheckMate(game.getCurrentPlayer());
            }



            if (!game.inCheckMate(game.getCurrentPlayer()) && game.inCheck(game.getCurrentPlayer())) {
                view.alertInCheck(game.getCurrentPlayer());
            }
        }
    }

    public void surrender() {
        game.surrender(game.getCurrentPlayer());

    }


}
