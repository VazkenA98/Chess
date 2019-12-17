package Main;

import game.GameController;
import board.BoardLocation;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;


/* * A JPanel ancestor that represents a chess board*/

public class BoardPanel extends JPanel implements ActionListener {
    private JButton[][] squares = new JButton[8][8];
    private JButton alreadySelected = null;
    private GameController gameController;
    private ArrayList<JButton> highlighted;

    public BoardPanel(GameController controller) {
        super(new GridLayout(9, 9));
        this.setBorder(new LineBorder(Color.BLACK));

        this.gameController = controller;

        highlighted = new ArrayList<JButton>();

        makeSpaces();
        makeBoard();
    }

    private void makeBoard() {
        // starts in upper left corner
        char currentCol = 'a';
        int currentRow = 8;

        for (int y = this.squares[0].length; y >= 0; y--) {
            currentCol = 'a';
            for (int x = 0; x < this.squares.length + 1; x++) {
                // on left edge, add a label
                if (x == 0) {
                    JLabel lab = new JLabel("" + currentRow, SwingConstants.CENTER);
                    this.add(lab);
                    continue;
                }

                // on the bottom, add a label
                if (y == 0) {
                    JLabel lab = new JLabel("" + currentCol, SwingConstants.CENTER);
                    this.add(lab);
                    currentCol++;
                    continue;
                }

                JButton curr = squares[x - 1][y - 1];
                this.add(curr);
            }
            currentRow--;
        }
    }

    private void makeSpaces() {
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int ii = 0; ii < squares.length; ii++) {
            for (int jj = 0; jj < squares[ii].length; jj++) {

                JButton b = new JButton();
                b.setHorizontalAlignment(SwingConstants.CENTER);
                b.setFont(new Font("Arial", Font.PLAIN, 40));
                b.setActionCommand(String.format("%d,%d", ii, jj));
                b.setPreferredSize(new Dimension(64, 64));

                b.setMargin(buttonMargin);
                b.setOpaque(true);
                b.setBorder(new LineBorder(Color.GRAY));
                b.addActionListener(this);

                if ((jj % 2 == 1 && ii % 2 == 0) || (jj % 2 == 0 && ii % 2 == 1)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.GRAY);
                }

                squares[ii][jj] = b;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // extract coordinates
        Scanner scanf = new Scanner(e.getActionCommand());
        scanf.useDelimiter(",");
        int x = scanf.nextInt();
        int y = scanf.nextInt();

        JButton clicked = squares[x][y];

        if (alreadySelected == null) {
            alreadySelected = clicked;
            clicked.setBorder(new LineBorder(Color.RED, 5));

            gameController.highlightPossibleMoves(new BoardLocation(x, y));

        } else {
            clearSelection();

            scanf = new Scanner(alreadySelected.getActionCommand());
            scanf.useDelimiter(",");
            int ox = scanf.nextInt();
            int oy = scanf.nextInt();

            gameController.move(new BoardLocation(ox, oy), new BoardLocation(x, y));

            alreadySelected = null;
        }
    }

    private void clearSelection() {
        alreadySelected.setBorder(new LineBorder(Color.GRAY));

        for (JButton high : highlighted) {
            high.setBorder(new LineBorder(Color.GRAY));
        }

        highlighted = new ArrayList<JButton>();
        this.repaint();
    }

    public void removePieceAt(BoardLocation loc) {
        this.squares[loc.getX()][loc.getY()].setIcon(null);
        this.squares[loc.getX()][loc.getY()].repaint();
    }

    public void putPiece(Piece piece, BoardLocation loc) {
        this.squares[loc.getX()][loc.getY()].setIcon(piece.getPieceIcon());
        this.squares[loc.getX()][loc.getY()].repaint();
    }

    public void highlightSpace(BoardLocation loc) {
        squares[loc.getX()][loc.getY()].setBorder(new LineBorder(Color.RED, 5));
        highlighted.add(squares[loc.getX()][loc.getY()]);
        this.repaint();
    }
}
