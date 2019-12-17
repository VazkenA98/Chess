package board;

//import ErrorController.ActionCannotBeUndoneException;

import java.util.ArrayList;
import java.util.Collections;


/*  Abstract class representing a change made to the board*/

public abstract class BoardAction {
    protected Board to;
    protected boolean applied;

    public BoardAction(Board to) {
        this.to = to;
    }

     /** Applies all the actions in the given array list, in the order of the list*/

    public static void applyActionsInOrder(ArrayList<BoardAction> actions) {
        for (BoardAction act : actions) {
            act.apply();
        }
    }

    public abstract void apply();

    public abstract void undo(); //throws ActionCannotBeUndoneException;
}
