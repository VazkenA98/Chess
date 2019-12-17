package Main;

import board.Board;
import board.BoardLocation;

import javax.swing.*;
import java.util.ArrayList;

public abstract class Piece implements Cloneable {

    protected int moveCounter;
    private Player owner;

    public Piece(Player owner) {
        this.moveCounter = 0;
        this.owner = owner;
    }

    public abstract Kind getKind();

    public abstract Icon getPieceIcon();

    public abstract ArrayList<BoardLocation> allPotentialMoves(BoardLocation from, Board board);

    //helps to check if piece can do this move
    public boolean canMove(BoardLocation from, BoardLocation to, Board on) {
        ArrayList<BoardLocation> allPossible = this.allPotentialMoves(from, on);
        return allPossible != null && this.allPotentialMoves(from, on).contains(to);
    }


     //returns ArrayList of all possible locations a piece can get to by going direction D

    protected void addUntilBlocked(Direction d, BoardLocation from, Board board, ArrayList<BoardLocation> lst) {
        int xoffset = 0;
        int yoffest = 0;
        switch (d) {
            case LEFT:
                xoffset = -1;
                yoffest = 0;
                break;
            case RIGHT:
                xoffset = 1;
                yoffest = 0;
                break;
            case UP:
                xoffset = 0;
                yoffest = 1;
                break;
            case DOWN:
                xoffset = 0;
                yoffest = -1;
                break;
            case LEFTUP:
                xoffset = -1;
                yoffest = 1;
                break;
            case RIGHTUP:
                xoffset = 1;
                yoffest = 1;
                break;
            case LEFTDOWN:
                xoffset = -1;
                yoffest = -1;
                break;
            case RIGHTDOWN:
                xoffset = 1;
                yoffest = -1;
                break;
        }

        while (true) {
            BoardLocation move = new BoardLocation(from, xoffset, yoffest);

            // break if we went off the board
            if (!board.locationOnBoard(move)) break;

            // check for a piece here
            Piece atMoveLoc = board.getPieceAt(move);
            if (atMoveLoc != null) {
                // we are done moving
                if (!atMoveLoc.getOwner().equals(this.getOwner())) {
                    lst.add(move);
                }
                break;
            }

            lst.add(move);

            switch (d) {
                case LEFT:
                    xoffset--;
                    break;
                case RIGHT:
                    xoffset++;
                    break;
                case UP:
                    yoffest++;
                    break;
                case DOWN:
                    yoffest--;
                    break;
                case LEFTUP:
                    xoffset--;
                    yoffest++;
                    break;
                case RIGHTUP:
                    xoffset++;
                    yoffest++;
                    break;
                case LEFTDOWN:
                    xoffset--;
                    yoffest--;
                    break;
                case RIGHTDOWN:
                    xoffset++;
                    yoffest--;
                    break;
            }
        }
    }

    public Player getOwner() {
        return owner;
    }

    public void youMoved() {
        this.moveCounter++;
    }

    public Piece clone() throws CloneNotSupportedException {
        Piece clone = (Piece) super.clone();
        clone.owner = new Player(this.owner);
        clone.moveCounter = this.moveCounter;

        return clone;
    }

    @Override
    public String toString() {
        return "{" + this.getClass() + ": " + this.getOwner() + "}";
    }


    public static enum Kind {KING, QUEEN, ROOK, BISHOP, KNIGHT, ALFIL, KNIGHTRIDER, BEROLINA, PAWN}

    protected enum Direction {LEFT, RIGHT, UP, DOWN, LEFTUP, LEFTDOWN, RIGHTUP, RIGHTDOWN}
}

