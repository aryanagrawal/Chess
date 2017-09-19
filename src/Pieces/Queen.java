package Pieces;

import java.awt.Point;
import java.util.ArrayList;

import Model.*;

public class Queen extends Pieces {

	public Queen(Color color, Piece piece, Point currPos) {
		super(color, piece, currPos);
	}

	@Override
	public boolean validate(Tile from, Tile to, Tile[][] board) {
		return validateAsRook(from, to, board) || validateAsBishop(from, to, board);
	}

	private boolean validateAsRook(Tile from, Tile to, Tile[][] board) {
		int max, min;

		if (from.getPosition().x == to.getPosition().x) {
			max = Math.max(from.getPosition().y, to.getPosition().y);
			min = Math.min(from.getPosition().y, to.getPosition().y);

			for (int i = min + 1; i < max; i++) {
				if (board[from.getPosition().x][i].hasPiece())
					return false;
			}
			return true;
		}

		if (from.getPosition().y == to.getPosition().y) {
			max = Math.max(from.getPosition().x, to.getPosition().x);
			min = Math.min(from.getPosition().x, to.getPosition().x);

			for (int i = min + 1; i < max; i++) {
				if (board[i][from.getPosition().y].hasPiece())
					return false;
			}
			return true;
		}
		return false;
	}

	private boolean validateAsBishop(Tile from, Tile to, Tile[][] board) {
		/*
		 * Invariant true here: 1. not moving at same position 2. points are in
		 * range 3. Bishop actually exists 4. Not killing own piece
		 */
		int maxX, minX, minY, maxY;
		// diagonal test
		if (Math.abs(from.getPosition().x - to.getPosition().x) != Math.abs(from.getPosition().y - to.getPosition().y))
			return false;

		// this means we are moving in a diagonal fashion
		else {
			maxX = Math.max(from.getPosition().x, to.getPosition().x);
			minX = Math.min(from.getPosition().x, to.getPosition().x);
			minY = Math.min(from.getPosition().y, to.getPosition().y);
			maxY = Math.max(from.getPosition().y, to.getPosition().y);

			if (((from.getPosition().x > to.getPosition().x) && (from.getPosition().y > to.getPosition().y))
					|| ((from.getPosition().x < to.getPosition().x) && (from.getPosition().y < to.getPosition().y))) {
				for (int i = minX + 1; i < maxX; i++) {
					if (minY + 1 < 0)
						continue;
					if (board[i][minY + 1].hasPiece()) {
						return false;
					}
					minY++;
				}
				return true;
			}
			if (((from.getPosition().x > to.getPosition().x) && (from.getPosition().y < to.getPosition().y))
					|| ((from.getPosition().x < to.getPosition().x) && (from.getPosition().y > to.getPosition().y))) {
				for (int i = minX + 1; i < maxX; i++) {
					if (minY + 1 < 0)
						continue;
					if (board[i][maxY - 1].hasPiece()) {
						return false;
					}
					maxY--;
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public ArrayList<Point> getPossibleMoves(Tile[][] board) {
		possibleMoves = new ArrayList<Point>();
		possibleMoves.addAll(getpossiblePositionsAsRook(board));
		possibleMoves.addAll(getpossiblePositionsAsBishop(board));
		return possibleMoves;
	}

	private ArrayList<Point> getpossiblePositionsAsBishop(Tile[][] board) {
		ArrayList<Point> possibleMoves = new ArrayList<Point>();

		for (int i = 1; i < 8; i++) {
			possibleMoves.add(new Point(currentPosition.x + i, currentPosition.y + i));
			possibleMoves.add(new Point(currentPosition.x + i, currentPosition.y - i));
			possibleMoves.add(new Point(currentPosition.x - i, currentPosition.y + i));
			possibleMoves.add(new Point(currentPosition.x - i, currentPosition.y - i));
		}

		ArrayList<Point> list = new ArrayList<Point>();

		for (Point point : possibleMoves) {
			if (simpleInvariantTest(currentPosition, point, board)) {
				if (validateAsBishop(board[currentPosition.x][currentPosition.y], board[point.x][point.y], board)) {
					if (board[point.x][point.y].hasPiece() && board[point.x][point.y].getPiece().equals(Piece.KING)
							&& !board[point.x][point.y].getPieceColor().equals(color)) {
						if (color.equals(Color.BLACK))
							whiteKingUnderCheck = true;
						else
							blackKingUnderCheck = true;
					}
					list.add(point);
				}
			}
		}
		possibleMoves = list;
		return possibleMoves;
	}

	private ArrayList<Point> getpossiblePositionsAsRook(Tile[][] board) {
		ArrayList<Point> possibleMove = new ArrayList<Point>();

		for (int i = 0; i < 8; i++) {
			possibleMove.add(new Point(i, currentPosition.y));
			possibleMove.add(new Point(currentPosition.x, i));
		}

		ArrayList<Point> list = new ArrayList<Point>();
		for (Point point : possibleMove)
			if (simpleInvariantTest(currentPosition, point, board))
				if (validateAsRook(board[currentPosition.x][currentPosition.y], board[point.x][point.y], board)) {
					if (board[point.x][point.y].hasPiece() && board[point.x][point.y].getPiece().equals(Piece.KING)
							&& !board[point.x][point.y].getPieceColor().equals(color)) {
						if (color.equals(Color.BLACK))
							whiteKingUnderCheck = true;
						else
							blackKingUnderCheck = true;
					}
					list.add(point);
				}

		possibleMove = list;
		return possibleMove;
	}
}
