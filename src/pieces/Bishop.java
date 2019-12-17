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

public class Bishop extends Piece {
    public Bishop(Player owner) {
        super(owner);
    }

    public Kind getKind() {
        return Kind.BISHOP;
    }

    public Icon getPieceIcon() {
        return this.getOwner().getPlayerColor() == PlayerColor.WHITE ? new ImageIcon("icons/white/bishop.png") : new ImageIcon("icons/black/bishop.png");
    }

    public ArrayList<BoardLocation> allPotentialMoves(BoardLocation from, Board board) {
        ArrayList<BoardLocation> ret = new ArrayList();
        this.addUntilBlocked(Direction.RIGHTUP, from, board, ret);
        this.addUntilBlocked(Direction.RIGHTDOWN, from, board, ret);
        this.addUntilBlocked(Direction.LEFTUP, from, board, ret);
        this.addUntilBlocked(Direction.LEFTDOWN, from, board, ret);
        return ret;
    }
}
