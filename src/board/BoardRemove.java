package board;


//import ErrorController.ActionCannotBeUndoneException;
import Main.Piece;

public class BoardRemove extends BoardAction {
    private final BoardLocation removalLocation;
    private Piece removedPiece;

    public BoardRemove(Board to, BoardLocation removalLocation) {
        super(to);
        this.removalLocation = removalLocation;
    }

    @Override
    public void apply() {
        removedPiece = to.getPieceAt(removalLocation);
        to.removePieceAt(removalLocation);
        applied = true;
    }

    @Override
    public void undo(){
        if (!applied)

        to.putPiece(removedPiece, removalLocation);
        applied = false;
    }
}

