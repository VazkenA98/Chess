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

public class Alfil extends Piece {
    public Alfil(Player owner) {
        super(owner);
    }

    public Kind getKind() {
        return Kind.ALFIL;
    }

    public Icon getPieceIcon() {
        return this.getOwner().getPlayerColor() == PlayerColor.WHITE ? new ImageIcon("icons/white/alfil.png") : new ImageIcon("icons/black/alfil.png");
    }

    public ArrayList<BoardLocation> allPotentialMoves(BoardLocation from, Board board) {
        ArrayList<BoardLocation> ret = new ArrayList();
        int[] var4 = new int[]{-2, 2};
        int var5 = var4.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            int x = var4[var6];
            int[] var8 = new int[]{-2, 2};
            int var9 = var8.length;

            for(int var10 = 0; var10 < var9; ++var10) {
                int y = var8[var10];
                BoardLocation dest = new BoardLocation(from, x, y);
                if (board.locationOnBoard(dest)) {
                    Piece atLoc = board.getPieceAt(dest);
                    if (atLoc == null) {
                        ret.add(dest);
                    } else if (!atLoc.getOwner().equals(this.getOwner())) {
                        ret.add(dest);
                    }
                }
            }
        }

        return ret;
    }
}
