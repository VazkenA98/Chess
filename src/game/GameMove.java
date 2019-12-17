package game;

//import ErrorController.ActionCannotBeUndoneException;
import Main.Piece;
import Main.Player;
import board.BoardAction;
import board.BoardAdd;
import board.BoardLocation;
import board.BoardRemove;

import java.util.ArrayList;


public class GameMove {
    protected boolean applied = false;
    private Player activeBeforeMove;
    private Game g;
    private ArrayList<BoardAction> boardActions;
    private ArrayList<Piece> addedToPlayersGraveyard;
    private Piece p;

    public GameMove(BoardLocation from, BoardLocation to, Game g) {
        this.g = g;

        boardActions = new ArrayList<BoardAction>();
        addedToPlayersGraveyard = new ArrayList<Piece>();

        if (g.getBoard().getPieceAt(to) != null) {
            addedToPlayersGraveyard.add(g.getBoard().getPieceAt(to));
            boardActions.add(new BoardRemove(g.getBoard(), to));
        }

        p = g.getBoard().getPieceAt(from);

        boardActions.add(new BoardRemove(g.getBoard(), from));
        boardActions.add(new BoardAdd(g.getBoard(), p, to));

        activeBeforeMove = g.getCurrentPlayer();
    }

    public void apply() {
        applied = true;
        BoardAction.applyActionsInOrder(boardActions);
        for (Piece p : addedToPlayersGraveyard) {
            g.getGraveyards().get(activeBeforeMove).add(p);
        }
        p.youMoved();
        g.moveToNextPlayer();
    }


}
