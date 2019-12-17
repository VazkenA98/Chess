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

public class Rook extends Piece {
    public Rook(Player owner) {
        super(owner);
    }

    public Kind getKind() {
        return Kind.ROOK;
    }

    public Icon getPieceIcon() {
        return this.getOwner().getPlayerColor() == PlayerColor.WHITE ? new ImageIcon("icons/white/rook.png") : new ImageIcon("icons/black/rook.png");
    }

    public ArrayList<BoardLocation> allPotentialMoves(BoardLocation from, Board board) {
        ArrayList<BoardLocation> ret = new ArrayList();
        this.addUntilBlocked(Direction.LEFT, from, board, ret);
        this.addUntilBlocked(Direction.RIGHT, from, board, ret);
        this.addUntilBlocked(Direction.UP, from, board, ret);
        this.addUntilBlocked(Direction.DOWN, from, board, ret);
        return ret;
    }
}
