package board;

/**
 * Represents a location on the game board
 * <p/>
 * These objects are immutable
 */
final public class BoardLocation {
    final private int x;
    final private int y;

    public BoardLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public BoardLocation(char chessFirst, int chessSecond) {
        this(Character.toLowerCase(chessFirst) - 'a', chessSecond - 1);
    }

    public BoardLocation(BoardLocation old, int new_X, int new_Y) {
        this.x = old.x + new_X;
        this.y = old.y + new_Y;
    }

    public BoardLocation(BoardLocation other) {
        this.x = other.x;
        this.y = other.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) throw new NullPointerException();
        if (!(o instanceof BoardLocation)) return false;

        BoardLocation other = (BoardLocation) o;
        return (this.getX() == other.getX()) && (this.getY() == other.getY());
    }

    @Override
    public int hashCode() {
        return this.getX() * 1000 + this.getY();
    }


}