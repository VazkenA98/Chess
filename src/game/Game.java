package game;

//import ErrorController.GameNotFinishedException;
import ErrorController.GameNotFinishedException;
import Main.GameView;
//import Main.MoveResult;
import Main.Piece;
import Main.Player;
import board.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Allows interaction with the game via a couple of public methods
 */
public class Game {
    private Board board;
    private HashMap<Player, ArrayList<Piece>> graveyards;
    private Player[] players;
    private int currentPlayerIndex;
    private GameView view;
    private Player surrenderedPlayer = null;
    private String name;


    public Game(Player p1, Player p2, Board startingBoard, GameView view, String gameName) {
        board = startingBoard;
        this.name = gameName;

        graveyards = new HashMap<Player, ArrayList<Piece>>();
        graveyards.put(p1, new ArrayList<Piece>());
        graveyards.put(p2, new ArrayList<Piece>());

        players = new Player[2];
        players[0] = p1;
        players[1] = p2;

        currentPlayerIndex = 0;

        this.view = view;

        if (view != null) {
            view.setCurrentPlayer(this.getCurrentPlayer().toString());
        }
    }


 /*    * Moves to the next player's turn. Changes the game's state*/

    public Player moveToNextPlayer() {
        this.currentPlayerIndex = this.nextPlayerIndex();

        if (this.view != null) {
            this.view.setCurrentPlayer(this.getCurrentPlayer().toString());
        }

        return this.players[this.currentPlayerIndex];
    }

    public Player getCurrentPlayer() {
        return this.players[this.currentPlayerIndex];
    }

    private int nextPlayerIndex() {
        return (this.currentPlayerIndex + 1) % 2;
    }

    public HashMap<Player, ArrayList<Piece>> getGraveyards() {
        return graveyards;
    }

    public boolean inCheck(Player who) {

        return this.inCheck(this.board, who);
    }

    public boolean inCheckMate(Player who) {
        if (!inCheck(who))
            return false;

        for (Piece p : board.getPiecesFor(who)) {
            BoardLocation pLoc = board.getLocationFor(p);
            for (BoardLocation possible : p.allPotentialMoves(pLoc, board)) {
                if (canMove(this.board, who, pLoc, possible) == true) {
                    return false;
                }
            }
        }

        return true;
    }



    private boolean inCheck(Board board, Player who) {

        BoardLocation[] kingLocs = board.findPieceLocations(Piece.Kind.KING, who);
        if (kingLocs.length == 0) return false;

        BoardLocation kingLoc = kingLocs[0];

        for (Player other : this.players) {
            if (other.equals(who)) {
                continue;
            }
            for (Piece enemyPiece : board.getPiecesFor(other)) {
                if (enemyPiece.canMove(board.getLocationFor(enemyPiece), kingLoc, board)) {
                    return true;
                }
            }
        }

        return false;
    }

    public Boolean canMove(Board on, Player who, BoardLocation from, BoardLocation to) {
        /* Checks if the given location is on the board*/
        if (!on.locationOnBoard(from)) return false;

        Piece sourcePiece = on.getPieceAt(from);
        if (sourcePiece == null) {
            return false;
        }

        if (!sourcePiece.getOwner().equals(who)) {
            return false;
        }

        if (!sourcePiece.canMove(from, to, on)) {
            return false;
        }

        Board newBoard = new Board(on);

        ArrayList<BoardAction> actions = new ArrayList<BoardAction>();

        Piece destinationPiece = on.getPieceAt(to);
        if (destinationPiece != null) {
            actions.add(new BoardRemove(newBoard, to));
        }

        // move piece into new location on new board
        actions.add(new BoardRemove(newBoard, from));
        actions.add(new BoardAdd(newBoard, sourcePiece, to));

        BoardAction.applyActionsInOrder(actions);

        if (this.inCheck(newBoard, who)) {
            if (this.inCheck(on, who)) {
                return false;
            } else {
                return false;
            }
        }

        return true;
    }

    public Board getBoard() {
        return board;
    }

    public void highlightPossibleMoves(BoardLocation from) {
        if (view != null) {
            Piece source = board.getPieceAt(from);
            if (source != null) {
                for (BoardLocation possible : source.allPotentialMoves(from, board)) {
                    if (canMove(board, getCurrentPlayer(), from, possible) == true) {
                        view.getBoardView().highlightSpace(possible);
                    }
                }
            }
        }
    }




    public void surrender(Player who) {
        surrenderedPlayer = who;

        if (view != null) {
            view.alertForSurrender(who);
        }
    }


}