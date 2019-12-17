package pieces;



import Main.Piece;
import Main.Player;
import Main.Piece.Kind;
import Main.Player.PlayerColor;
import board.Board;
import board.BoardLocation;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Pawn extends Piece {
    private int offsetMultiplier;

    public Pawn(Player owner) {
        super(owner);
        if (this.getOwner().getPlayerColor() == PlayerColor.WHITE) {
            this.offsetMultiplier = 1;
        } else if (this.getOwner().getPlayerColor() == PlayerColor.BLACK) {
            this.offsetMultiplier = -1;
        }

    }

    public Kind getKind() {
        return Kind.PAWN;
    }

    public Icon getPieceIcon() {
        return this.getOwner().getPlayerColor() == PlayerColor.WHITE ? new ImageIcon("icons/white/pawn.png") : new ImageIcon("icons/black/pawn.png");
    }

    public ArrayList<BoardLocation> allPotentialMoves(BoardLocation from, Board board) {
        ArrayList<BoardLocation> ret = new ArrayList();
        BoardLocation loc1 = new BoardLocation(from, 0, this.offsetMultiplier);
        if (board.locationOnBoard(loc1) && board.getPieceAt(loc1) == null) {
            ret.add(loc1);
        }

        BoardLocation loc2 = new BoardLocation(from, 0, 2 * this.offsetMultiplier);
        if (board.locationOnBoard(loc2) && board.getPieceAt(loc1) == null && board.getPieceAt(loc2) == null && this.moveCounter == 0) {
            ret.add(loc2);
        }

        this.attack(new BoardLocation(from, -1, this.offsetMultiplier), board, ret);
        this.attack(new BoardLocation(from, 1, this.offsetMultiplier), board, ret);
        return ret;
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
