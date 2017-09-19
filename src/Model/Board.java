package Model;

import java.awt.Point;

import Pieces.*;

public class Board {

	public static final int SIDE = 8;
	private Tile[][] board;
	boolean isWhiteKingUnderCheck, isBlackKingUnderCheck;

	public Board() {
		board = new Tile[SIDE][SIDE];
		for (int i = 0; i < SIDE; i++) {
			for (int j = 0; j < SIDE; j++) {
				if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1))
					board[i][j] = new Tile(Color.BLACK, new Point(i, j));
				else
					board[i][j] = new Tile(Color.WHITE, new Point(i, j));
			}
		}

		setPieces();
	}

	public void setPieces() {
		for (int j = 0; j < SIDE; j++) {
			board[1][j].hasPiece = true;
			board[0][j].hasPiece = true;
			board[1][j].piece = new Pawn(Color.WHITE, Piece.PAWN, new Point(1, j));

			board[6][j].hasPiece = true;
			board[7][j].hasPiece = true;
			board[6][j].piece = new Pawn(Color.BLACK, Piece.PAWN, new Point(6, j));
		}

		board[0][0].piece = new Rook(Color.WHITE, Piece.ROOK, new Point(0, 0));
		board[0][1].piece = new Knight(Color.WHITE, Piece.KNIGHT, new Point(0, 1));
		board[0][2].piece = new Bishop(Color.WHITE, Piece.BISHOP, new Point(0, 2));
		board[0][3].piece = new Queen(Color.WHITE, Piece.QUEEN, new Point(0, 3));
		board[0][4].piece = new King(Color.WHITE, Piece.KING, new Point(0, 4));
		board[0][5].piece = new Bishop(Color.WHITE, Piece.BISHOP, new Point(0, 5));
		board[0][6].piece = new Knight(Color.WHITE, Piece.KNIGHT, new Point(0, 6));
		board[0][7].piece = new Rook(Color.WHITE, Piece.ROOK, new Point(0, 7));

		board[7][0].piece = new Rook(Color.BLACK, Piece.ROOK, new Point(7, 0));
		board[7][1].piece = new Knight(Color.BLACK, Piece.KNIGHT, new Point(7, 1));
		board[7][2].piece = new Bishop(Color.BLACK, Piece.BISHOP, new Point(7, 2));
		board[7][3].piece = new Queen(Color.BLACK, Piece.QUEEN, new Point(7, 3));
		board[7][4].piece = new King(Color.BLACK, Piece.KING, new Point(7, 4));
		board[7][5].piece = new Bishop(Color.BLACK, Piece.BISHOP, new Point(7, 5));
		board[7][6].piece = new Knight(Color.BLACK, Piece.KNIGHT, new Point(7, 6));
		board[7][7].piece = new Rook(Color.BLACK, Piece.ROOK, new Point(7, 7));
	}

	public boolean hasPiece(Point point) {
		return board[point.x][point.y].hasPiece;
	}

	public Piece getPieceName(Point point) {
		return board[point.x][point.y].getPiece();
	}

	public Pieces getPiece(Point point){
		return board[point.x][point.y].piece;
	}

	public Tile[][] getBoard(){
		return board;
	}
	
	public boolean move(Point from, Point to) {

		if(from.equals(to))
			return false;
		
		if (from.x >= SIDE || from.x < 0 || from.y >= SIDE || from.y < 0 || to.x >= SIDE || to.x < 0 || to.y >= SIDE
				|| to.y < 0) {
			return false;
		}
		// Invariant true here: points are in board range
		
		if (board[from.x][from.y].piece == null) {
			return false;
		}

		// Invariant true here: not trying to move a null piece

		if (board[to.x][to.y].piece != null) {
			if (board[from.x][from.y].piece.getColor().equals(board[to.x][to.y].piece.getColor())) {
		
				return false;
			}
		}

		// invariant true here: not killing self piece
		if (board[from.x][from.y].piece.validate(board[from.x][from.y], board[to.x][to.y], board)) {
			board[to.x][to.y].piece = board[from.x][from.y].piece;
			board[to.x][to.y].hasPiece = true;
			board[to.x][to.y].piece.setCurrentPosition(to);

			board[from.x][from.y].piece = null;
			board[from.x][from.y].hasPiece = false;
			return true;
		}
		return false;
	}

	// print the positions of the board
	public String boardPositionsToString() {
		StringBuffer b = new StringBuffer();
		for (int i = SIDE - 1; i >= 0; i--) {
			for (int j = 0; j < SIDE; j++) {
				b.append(board[i][j].position);
				if (j != SIDE - 1)
					b.append("  ");
			}
			if (i != 0)
				b.append("\n");
		}
		return b.toString();
	}

	public String boardPiecesToString() {
		StringBuffer b = new StringBuffer();
		for (int i = SIDE - 1; i >= 0; i--) {
			for (int j = 0; j < SIDE; j++) {
				if (board[i][j].piece != null)
					b.append(board[i][j].piece.getPiece());
				else
					b.append(board[i][j].piece);
				if (j != SIDE - 1)
					b.append("\t");
			}
			if (i != 0)
				b.append("\n\n\n");
		}
		return b.toString();
	}
}
