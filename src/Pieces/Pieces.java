package Pieces;

import java.awt.Point;
import java.util.ArrayList;

import Model.*;

public abstract class Pieces {
	Piece piece;
	Color color;
	ArrayList<Point> possibleMoves;
	Point currentPosition;

	boolean blackKingUnderCheck, whiteKingUnderCheck;

	public Pieces(Color color, Piece piece, Point currPos) {
		this.color = color;
		this.piece = piece;
		this.currentPosition = currPos;
		possibleMoves = new ArrayList<Point>();
		blackKingUnderCheck = false;
		whiteKingUnderCheck = false;
	}

	public Piece getPiece() {
		return this.piece;
	}

	public Color getColor() {
		return this.color;
	}

	public Point getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Point point) {
		this.currentPosition = point;
	}

	

	public abstract boolean validate(Tile from, Tile to, Tile[][] myBoard);

	public abstract ArrayList<Point> getPossibleMoves(Tile[][] myBoard);

	public boolean simpleInvariantTest(Point from, Point to, Tile[][] board) {
		int SIDE = 8;

		// same locations test
		if (from.equals(to))
			return false;

		// out of bounds test
		if (from.x >= SIDE || from.x < 0 || from.y >= SIDE || from.y < 0 || to.x >= SIDE || to.x < 0 || to.y >= SIDE
				|| to.y < 0) {
			return false;
		}

		// from point is null test
		if (board[from.x][from.y].piece == null) {
			return false;
		}

		// from and to are same color
		if (board[to.x][to.y].piece != null) {
			if (board[from.x][from.y].piece.getColor().equals(board[to.x][to.y].piece.getColor())) {
				return false;
			}
		}
		return true;
	}

	public boolean isOpponentKingUnderCheck(Tile[][] myBoard) {
		for (Point point : possibleMoves) {
			if (myBoard[point.x][point.y].hasPiece() && myBoard[point.x][point.y].getPiece() == Piece.KING) {
				if (!myBoard[point.x][point.y].piece.color.equals(color))
					return true;
			}
		}
		return false;
	}
	
	
}
