package Pieces;

import java.awt.Point;
import java.util.ArrayList;

import Model.*;

public class Rook extends Pieces {

	public Rook(Color color, Piece piece, Point currPos) {
		super(color, piece, currPos);
	}

	@Override
	public boolean validate(Tile from, Tile to, Tile[][] board) {
		/*
		 * Invariant true here: 1. not moving at same position 2. points are in
		 * range 3. Rook actually exists 4. Not killing own piece
		 */
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

	@Override
	public ArrayList<Point> getPossibleMoves(Tile[][] board) {
		possibleMoves = new ArrayList<Point>();
		for (int i = 0; i < 8; i++) {
			possibleMoves.add(new Point(i, currentPosition.y));
			possibleMoves.add(new Point(currentPosition.x, i));
		}

		ArrayList<Point> list = new ArrayList<Point>();
		for (Point point : possibleMoves)
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

		possibleMoves = list;
		return possibleMoves;
	}
}
