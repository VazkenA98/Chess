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

public class Knight extends Piece {
    public Knight(Player owner) {
        super(owner);
    }

    public Kind getKind() {
        return Kind.KNIGHT;
    }

    public Icon getPieceIcon() {
        return this.getOwner().getPlayerColor() == PlayerColor.WHITE ? new ImageIcon("icons/white/knight.png") : new ImageIcon("icons/black/knight.png");
    }

    public ArrayList<BoardLocation> allPotentialMoves(BoardLocation from, Board board) {
        ArrayList<BoardLocation> ret = new ArrayList();
        int[] var4 = new int[]{-2, -1, 1, 2};
        int var5 = var4.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            int x_off = var4[var6];
            int[] var8 = new int[]{-2, -1, 1, 2};
            int var9 = var8.length;

            for(int var10 = 0; var10 < var9; ++var10) {
                int y_off = var8[var10];
                if (Math.abs(x_off) != Math.abs(y_off)) {
                    BoardLocation newLoc = new BoardLocation(from, x_off, y_off);
                    if (board.locationOnBoard(newLoc)) {
                        Piece atLoc = board.getPieceAt(newLoc);
                        if (atLoc == null || !atLoc.getOwner().equals(this.getOwner())) {
                            ret.add(newLoc);
                        }
                    }
                }
            }
        }

        return ret;
    }
}
