package board;

import Main.BoardPanel;
import Main.Piece;
import Main.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private HashMap<BoardLocation, Piece> data;
    private BoardLocation max;
    private BoardLocation min;
    private BoardPanel view;


    public Board(Board board) {
        this.min = board.min;
        this.max = board.max;
        this.view = null;

        this.data = new HashMap<BoardLocation, Piece>(board.data);
    }


/*     * Creates a new board with maximum size*/

    public Board(BoardLocation maximalLocation, BoardPanel view) {
        this.data = new HashMap<BoardLocation, Piece>();
        this.max = maximalLocation;
        this.min = new BoardLocation(0, 0);
        this.view = view;
    }


     /* Creates an 8x8 chess board*/

    public static Board normalChessBoard(BoardPanel view) {
        return new Board(new BoardLocation(8, 8), view);
    }

   /*get piece at board location*/
    public Piece getPieceAt(BoardLocation loc) {
        return data.get(loc);
    }


      /*removes the piece at loc from the board*/

    public void removePieceAt(BoardLocation loc) throws IllegalArgumentException {
        if (!this.locationOnBoard(loc)) throw new IllegalArgumentException("the location is not on the board");
        data.put(loc, null);

        if (view != null) {
            view.removePieceAt(loc);
        }
    }


     /* puts piece at loc on the board*/

    public void putPiece(Piece piece, BoardLocation loc) throws IllegalArgumentException {

        data.put(loc, piece);

        if (view != null) {
            view.putPiece(piece, loc);
        }
    }


     /*An array of all the pieces on the board*/

    public Piece[] allPiecesOnBoard() {
        ArrayList<Piece> ret = new ArrayList<Piece>();
        for (Map.Entry<BoardLocation, Piece> el : this.data.entrySet()) {
            if (el.getValue() != null) {
                ret.add(el.getValue());
            }
        }

        return ret.toArray(new Piece[ret.size()]);
    }

    /**
      player Player to find pieces for
     Array of all pieces owned by this player on the board
     */
    public Piece[] getPiecesFor(Player player) {
        ArrayList<Piece> ret = new ArrayList<Piece>();
        for (Piece p : this.allPiecesOnBoard()) {
            if (p.getOwner().equals(player)) {
                ret.add(p);
            }
        }
        return ret.toArray(new Piece[ret.size()]);

    }


     /* Finds the locations of all pieces of a certain kind owned by a certain player*/

    public BoardLocation[] findPieceLocations(Piece.Kind kind, Player who) {
        ArrayList<BoardLocation> ret = new ArrayList<BoardLocation>();
        for (Map.Entry<BoardLocation, Piece> el : this.data.entrySet()) {
            Piece p = el.getValue();
            if (p != null && p.getKind() == kind && p.getOwner().equals(who)) {
                ret.add(el.getKey());
            }
        }
        return ret.toArray(new BoardLocation[ret.size()]);
    }


     /*Gets the BoardLocation of a piece*/

    public BoardLocation getLocationFor(Piece piece) {
        for (Map.Entry<BoardLocation, Piece> el : this.data.entrySet()) {
            Piece p = el.getValue();
            if (p != null && p.equals(piece)) {
                return el.getKey();
            }
        }
        return null;
    }


     /* Checks if the given location is on the board*/

    public boolean locationOnBoard(BoardLocation loc) {

        boolean bigger = loc.getX() >= min.getX() && loc.getY() >= min.getY();
        boolean smaller = loc.getX() < max.getX() && loc.getY() < max.getY();
        return bigger && smaller;
    }
}
