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

public class King extends Piece {
    public King(Player owner) {
        super(owner);
    }

    public Kind getKind() {
        return Kind.KING;
    }

    public Icon getPieceIcon() {
        return this.getOwner().getPlayerColor() == PlayerColor.WHITE ? new ImageIcon("icons/white/king.png") : new ImageIcon("icons/black/king.png");
    }

    public ArrayList<BoardLocation> allPotentialMoves(BoardLocation from, Board board) {
        ArrayList<BoardLocation> ret = new ArrayList();
        int[] var4 = new int[]{-1, 0, 1};
        int var5 = var4.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            int x_off = var4[var6];
            int[] var8 = new int[]{-1, 0, 1};
            int var9 = var8.length;

            for(int var10 = 0; var10 < var9; ++var10) {
                int y_off = var8[var10];
                if (x_off != 0 || y_off != 0) {
                    BoardLocation dest = new BoardLocation(from, x_off, y_off);
                    if (board.locationOnBoard(dest)) {
                        Piece atDest = board.getPieceAt(dest);
                        if (atDest == null) {
                            ret.add(dest);
                        } else if (!atDest.getOwner().equals(this.getOwner())) {
                            ret.add(dest);
                        }
                    }
                }
            }
        }

        return ret;
    }
}
