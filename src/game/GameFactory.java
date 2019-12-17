package game;

import Main.GameView;
import Main.Player;
import board.Board;
import board.BoardLocation;
import pieces.*;


public class GameFactory {
    public static Game makeGame(Player p1, Player p2, GameView view, String type, String gameName) {

        if(type.equals("Normal"))
                return GameFactory.makeNormalChess(p1, p2, view, gameName);
        return GameFactory.makeCustemChess(p1, p2, view, gameName);
    }

    public static Game makeNormalChess(Player p1, Player p2, GameView gameView, String gameName) {
        Board board;
        if (gameView != null) {
            board = Board.normalChessBoard(gameView.getBoardView());
        } else {
            board = Board.normalChessBoard(null);
        }

        // add all the pawns
        int pawnsP1Y = 1;
        int pawnsP2Y = 6;
        for (int i = 0; i < 8; i++) {
            BoardLocation p1PawnLoc = new BoardLocation(i, pawnsP1Y);
            BoardLocation p2PawnLoc = new BoardLocation(i, pawnsP2Y);

            Pawn p1Pawn = new Pawn(p1);
            Pawn p2Pawn = new Pawn(p2);

            board.putPiece(p1Pawn, p1PawnLoc);
            board.putPiece(p2Pawn, p2PawnLoc);
        }

        // y locations for things
        int p1y = 0;
        int p2y = 7;

        // add the rooks
        for (int x : new int[]{0, 7}) {
            BoardLocation p1RookLoc = new BoardLocation(x, p1y);
            BoardLocation p2RookLoc = new BoardLocation(x, p2y);

            Rook p1Rook = new Rook(p1);
            Rook p2Rook = new Rook(p2);

            board.putPiece(p1Rook, p1RookLoc);
            board.putPiece(p2Rook, p2RookLoc);
        }

        // add the horsey
        for (int x : new int[]{1, 6}) {
            BoardLocation p1KnightLoc = new BoardLocation(x, p1y);
            BoardLocation p2KnightLoc = new BoardLocation(x, p2y);

            Knight p1Knight = new Knight(p1);
            Knight p2Knight = new Knight(p2);

            board.putPiece(p1Knight, p1KnightLoc);
            board.putPiece(p2Knight, p2KnightLoc);
        }

        // add the bishops
        for (int x : new int[]{2, 5}) {
            BoardLocation p1BishopLoc = new BoardLocation(x, p1y);
            BoardLocation p2BishopLoc = new BoardLocation(x, p2y);

            Bishop p1Bishop = new Bishop(p1);
            Bishop p2Bishop = new Bishop(p2);

            board.putPiece(p1Bishop, p1BishopLoc);
            board.putPiece(p2Bishop, p2BishopLoc);
        }

        // add king and queen
        King p1King = new King(p1);
        BoardLocation p1KingLoc = new BoardLocation(4, 0);
        board.putPiece(p1King, p1KingLoc);

        Queen p1Queen = new Queen(p1);
        BoardLocation p1QueenLoc = new BoardLocation(3, 0);
        board.putPiece(p1Queen, p1QueenLoc);

        King p2King = new King(p2);
        BoardLocation p2KingLoc = new BoardLocation(4, 7);
        board.putPiece(p2King, p2KingLoc);

        Queen p2Queen = new Queen(p2);
        BoardLocation p2QueenLoc = new BoardLocation(3, 7);
        board.putPiece(p2Queen, p2QueenLoc);

        return new Game(p1, p2, board, gameView, gameName);
    }
    public static Game makeCustemChess(Player p1, Player p2, GameView gameView, String gameName) {
        Board board;
        if (gameView != null) {
            board = Board.normalChessBoard(gameView.getBoardView());
        } else {
            board = Board.normalChessBoard(null);
        }

        // add all the pawns
        int pawnsP1Y = 1;
        int pawnsP2Y = 6;
        for (int i = 0; i < 8; i++) {
            BoardLocation p1KnightLoc = new BoardLocation(i, pawnsP1Y);
            BoardLocation p2KnightLoc = new BoardLocation(i, pawnsP2Y);

            Knight p1Knight = new Knight(p1);
            Knight p2Knight = new Knight(p2);

            board.putPiece(p1Knight, p1KnightLoc);
            board.putPiece(p2Knight, p2KnightLoc);
        }

        // y locations for things
        int p1y = 0;
        int p2y = 7;

        // add the rooks
        for (int x : new int[]{0, 7}) {
            Queen p1Queen = new Queen(p1);
            BoardLocation p1QueenLoc = new BoardLocation(x, p1y);
            board.putPiece(p1Queen, p1QueenLoc);

            Queen p2Queen = new Queen(p2);
            BoardLocation p2QueenLoc = new BoardLocation(x, p2y);
            board.putPiece(p2Queen, p2QueenLoc);
        }

        // add the horsey
        for (int x : new int[]{1, 6}) {
            Queen p1Queen = new Queen(p1);
            BoardLocation p1QueenLoc = new BoardLocation(x, p1y);
            board.putPiece(p1Queen, p1QueenLoc);

            Queen p2Queen = new Queen(p2);
            BoardLocation p2QueenLoc = new BoardLocation(x, p2y);
            board.putPiece(p2Queen, p2QueenLoc);
        }

        // add the bishops
        for (int x : new int[]{2, 5}) {
            Queen p1Queen = new Queen(p1);
            BoardLocation p1QueenLoc = new BoardLocation(x, p1y);
            board.putPiece(p1Queen, p1QueenLoc);

            Queen p2Queen = new Queen(p2);
            BoardLocation p2QueenLoc = new BoardLocation(x, p2y);
            board.putPiece(p2Queen, p2QueenLoc);
        }

        // add king and queen
        King p1King = new King(p1);
        BoardLocation p1KingLoc = new BoardLocation(4, 0);
        board.putPiece(p1King, p1KingLoc);

        Queen p1Queen = new Queen(p1);
        BoardLocation p1QueenLoc = new BoardLocation(3, 0);
        board.putPiece(p1Queen, p1QueenLoc);

        King p2King = new King(p2);
        BoardLocation p2KingLoc = new BoardLocation(4, 7);
        board.putPiece(p2King, p2KingLoc);

        Queen p2Queen = new Queen(p2);
        BoardLocation p2QueenLoc = new BoardLocation(3, 7);
        board.putPiece(p2Queen, p2QueenLoc);

        return new Game(p1, p2, board, gameView, gameName);
    }

    public enum GameType {NORMAL,CUSTEM}
}
