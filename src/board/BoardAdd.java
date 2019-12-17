package board;

//import ErrorController.ActionCannotBeUndoneException;
import Main.Piece;

public class BoardAdd extends BoardAction {
    private Piece toAdd;
    private BoardLocation location;

    public BoardAdd(Board to, Piece toAdd, BoardLocation loc) {
        super(to);
        this.toAdd = toAdd;
        this.location = loc;
    }

    @Override
    public void apply() {
        to.putPiece(toAdd, location);
        applied = true;
    }

    @Override
    public void undo(){
        if (!applied)

        to.removePieceAt(location);
        applied = false;
    }
}
