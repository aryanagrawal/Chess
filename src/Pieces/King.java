package Pieces;

import java.awt.Point;
import java.util.ArrayList;

import Model.*;

public class King extends Pieces {

	public King(Color color, Piece piece, Point currPos) {
		super(color, piece, currPos);
	}

	@Override
	public boolean validate(Tile from, Tile to, Tile[][] myBoard) {
		/*
		 * Invariant true here: 1. not moving at same position 2. points are in
		 * range 3. King actually exists 4. Not killing own piece
		 */

		if (Math.abs(from.getPosition().x - to.getPosition().x) < 2
				&& Math.abs(from.getPosition().y - to.getPosition().y) < 2)
			return true;
		return false;
	}

	@Override
	public ArrayList<Point> getPossibleMoves(Tile[][] board) {
		possibleMoves = new ArrayList<Point>();

		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				possibleMoves.add(new Point(currentPosition.x + i, currentPosition.y + j));
			}
		}
		ArrayList<Point> list = new ArrayList<Point>();
		for (Point point : possibleMoves) {
			if (simpleInvariantTest(currentPosition, point, board))
				if (validate(board[currentPosition.x][currentPosition.y], board[point.x][point.y], board))
					list.add(point);
		}
		possibleMoves = list;
		return possibleMoves;
	}

}
