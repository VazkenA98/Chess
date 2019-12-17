package pieces;

import Main.Piece;
import Main.Player;

import Main.Player.PlayerColor;
import board.Board;
import board.BoardLocation;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Queen extends Piece {
    public Queen(Player owner) {
        super(owner);
    }

    public Kind getKind() {
        return Kind.QUEEN;
    }

    public Icon getPieceIcon() {
        return this.getOwner().getPlayerColor() == PlayerColor.WHITE ? new ImageIcon("icons/white/queen.png") : new ImageIcon("icons/black/queen.png");
    }

    public ArrayList<BoardLocation> allPotentialMoves(BoardLocation from, Board board) {
        ArrayList<BoardLocation> ret = new ArrayList();
        this.addUntilBlocked(Direction.UP, from, board, ret);
        this.addUntilBlocked(Direction.DOWN, from, board, ret);
        this.addUntilBlocked(Direction.LEFT, from, board, ret);
        this.addUntilBlocked(Direction.RIGHT, from, board, ret);
        this.addUntilBlocked(Direction.LEFTUP, from, board, ret);
        this.addUntilBlocked(Direction.LEFTDOWN, from, board, ret);
        this.addUntilBlocked(Direction.RIGHTUP, from, board, ret);
        this.addUntilBlocked(Direction.RIGHTDOWN, from, board, ret);
        return ret;
    }
}
