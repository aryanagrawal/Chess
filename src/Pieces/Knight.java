package Pieces;

import java.awt.Point;
import java.util.ArrayList;

import Model.*;

public class Knight extends Pieces {

	public Knight(Color color, Piece piece, Point currPos) {
		super(color, piece, currPos);
	}

	@Override
	public boolean validate(Tile from, Tile to, Tile[][] myBoard) {
		/*
		 * Invariant that holds true here
		 * 
		 * 1. Not moving on same place 
		 * 2. Piece movement is in range 
		 * 3. Its a valid piece
		 * 4. Not placing on itself
		 * 
		 */
		if ((Math.abs(from.getPosition().x - to.getPosition().x) == 2
				&& Math.abs(from.getPosition().y - to.getPosition().y) == 1)
				|| Math.abs(from.getPosition().x - to.getPosition().x) == 1
						&& Math.abs(from.getPosition().y - to.getPosition().y) == 2)
			return true;

		return false;
	}

	@Override
	public ArrayList<Point> getPossibleMoves(Tile[][] board) {
		possibleMoves = new ArrayList<Point>();

		possibleMoves.add(new Point(currentPosition.x + 2, currentPosition.y + 1));
		possibleMoves.add(new Point(currentPosition.x + 2, currentPosition.y - 1));
		possibleMoves.add(new Point(currentPosition.x + 1, currentPosition.y + 2));
		possibleMoves.add(new Point(currentPosition.x + 1, currentPosition.y - 2));
		possibleMoves.add(new Point(currentPosition.x - 1, currentPosition.y + 2));
		possibleMoves.add(new Point(currentPosition.x - 1, currentPosition.y - 2));
		possibleMoves.add(new Point(currentPosition.x - 2, currentPosition.y + 1));
		possibleMoves.add(new Point(currentPosition.x - 2, currentPosition.y - 1));

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
