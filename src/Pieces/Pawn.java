package Pieces;

import java.awt.Point;
import java.util.ArrayList;

import Model.*;

public class Pawn extends Pieces {

	public Pawn(Color color, Piece piece, Point currPos) {
		super(color, piece, currPos);
	}

	public Point getCurrentPosition() {
		return currentPosition;
	}
	
	

	@Override
	public boolean validate(Tile from, Tile to, Tile[][] board) {

		// if it is a black pawn
		if (color == Color.BLACK) {
			// trying to go forward
			if (from.getPosition().y == to.getPosition().y) {
				if (to.hasPiece())
					return false;
				if (from.getPosition().x == 6) {
					if (to.getPosition().x == 5) {
						return true;
					} else if (to.getPosition().x == 4
							&& !board[from.getPosition().x - 1][from.getPosition().y].hasPiece()) {
						if (!board[from.getPosition().x - 1][from.getPosition().y].hasPiece())
							return true;
					}
				} else if (from.getPosition().x - to.getPosition().x == 1) {
					if (!to.hasPiece())
						return true;
				}
			} else if ((to.getPosition().x - from.getPosition().x == -1
					&& Math.abs(to.getPosition().y - from.getPosition().y) == 1) && to.hasPiece()) {
				return true;
			}
		}

		if (color == Color.WHITE) {

			// trying to go forward
			if (from.getPosition().y == to.getPosition().y) {
				if (to.hasPiece())
					return false;
				if (from.getPosition().x == 1) {
					if (to.getPosition().x == 2) {
						return true;
					} else if (to.getPosition().x == 3) {

						if (!board[from.getPosition().x + 1][from.getPosition().y].hasPiece())
							return true;
					}
				} else if (from.getPosition().x - to.getPosition().x == -1) {
					if (!to.hasPiece())
						return true;
				}
			} else if ((to.getPosition().x - from.getPosition().x == 1
					&& Math.abs(to.getPosition().y - from.getPosition().y) == 1) && to.hasPiece()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public ArrayList<Point> getPossibleMoves(Tile[][] board) {
		possibleMoves = new ArrayList<Point>();
		int colorFactor;
		if (color.equals(Color.BLACK))
			colorFactor = 1;
		else
			colorFactor = -1;

		// if at base
		if (currentPosition.x == (int) (3.5 + 2.5 * colorFactor)) {
			if (!board[currentPosition.x - colorFactor][currentPosition.y].hasPiece())
				possibleMoves.add(new Point(currentPosition.x - colorFactor, currentPosition.y));
			if (!board[currentPosition.x - 2 * colorFactor][currentPosition.y].hasPiece())
				possibleMoves.add(new Point(currentPosition.x - 2 * colorFactor, currentPosition.y));
		} else if (!board[currentPosition.x - colorFactor][currentPosition.y].hasPiece())
			possibleMoves.add(new Point(currentPosition.x - colorFactor, currentPosition.y));

		if (board[currentPosition.x - colorFactor][currentPosition.y - colorFactor].hasPiece()) {

			int oppositeColor;
			if (board[currentPosition.x - colorFactor][currentPosition.y - colorFactor].getPieceColor()
					.equals(Color.BLACK))
				oppositeColor = 1;
			else
				oppositeColor = -1;
			if (oppositeColor != colorFactor)
				possibleMoves.add(new Point(currentPosition.x - colorFactor, currentPosition.y - colorFactor));
		}

		if (board[currentPosition.x - colorFactor][currentPosition.y + colorFactor].hasPiece()) {
			int oppositeColor;
			if (board[currentPosition.x - colorFactor][currentPosition.y + colorFactor].getPieceColor()
					.equals(Color.BLACK))
				oppositeColor = 1;
			else
				oppositeColor = -1;
			if (oppositeColor != colorFactor)
				possibleMoves.add(new Point(currentPosition.x - colorFactor, currentPosition.y + colorFactor));
		}

		// final tests
		ArrayList<Point> list = new ArrayList<Point>();
		for (Point point : possibleMoves) {
			if (simpleInvariantTest(currentPosition, point, board))
				if (validate(board[currentPosition.x][currentPosition.y], board[point.x][point.y], board)){
					if(board[point.x][point.y].hasPiece() && board[point.x][point.y].getPiece().equals(Piece.KING) && !board[point.x][point.y].getPieceColor().equals(color)){
						if(color.equals(Color.BLACK))
							whiteKingUnderCheck=true;
						else
							blackKingUnderCheck=true;
					}
					list.add(point);
				}
		}
		possibleMoves = list;
		return possibleMoves;
	}
}