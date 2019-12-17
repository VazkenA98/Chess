package pieces;

import Main.Piece;
import Main.Player;

import Main.Player.PlayerColor;
import board.Board;
import board.BoardLocation;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class BerolinaPawn extends Piece {
    private int offsetMultiplier;

    public BerolinaPawn(Player owner) {
        super(owner);
        if (this.getOwner().getPlayerColor() == PlayerColor.WHITE) {
            this.offsetMultiplier = 1;
        } else if (this.getOwner().getPlayerColor() == PlayerColor.BLACK) {
            this.offsetMultiplier = -1;
        }

    }

    public Kind getKind() {
        return Kind.BEROLINA;
    }

    public Icon getPieceIcon() {
        return this.getOwner().getPlayerColor() == PlayerColor.WHITE ? new ImageIcon("icons/white/berolina.png") : new ImageIcon("icons/black/berolina.png");
    }

    public ArrayList<BoardLocation> allPotentialMoves(BoardLocation from, Board board) {
        ArrayList<BoardLocation> ret = new ArrayList();
        this.tryDiagonal(1, from, board, ret);
        this.tryDiagonal(-1, from, board, ret);
        this.attack(new BoardLocation(from, 0, this.offsetMultiplier), board, ret);
        return ret;
    }

    private void tryDiagonal(int direction, BoardLocation from, Board board, ArrayList<BoardLocation> ret) {
        BoardLocation loc1 = new BoardLocation(from, direction * this.offsetMultiplier, this.offsetMultiplier);
        if (board.locationOnBoard(loc1) && board.getPieceAt(loc1) == null) {
            ret.add(loc1);
        }

        BoardLocation loc2 = new BoardLocation(from, direction * 2 * this.offsetMultiplier, 2 * this.offsetMultiplier);
        if (board.locationOnBoard(loc2) && board.getPieceAt(loc1) == null && board.getPieceAt(loc2) == null && this.moveCounter == 0) {
            ret.add(loc2);
        }

    }

    private void attack(BoardLocation boardLocation, Board board, ArrayList<BoardLocation> ret) {
        if (board.locationOnBoard(boardLocation)) {
            Piece otherPiece = board.getPieceAt(boardLocation);
            if (otherPiece != null && !otherPiece.getOwner().equals(this.getOwner())) {
                ret.add(boardLocation);
            }
        }

    }
}
