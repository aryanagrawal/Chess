package Tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import Model.Board;
import Model.Piece;

public class BoardTest {

	// @Test
	public void testBoard() {
		Board board = new Board();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i < 2 || i > 5) {
					assertTrue(board.hasPiece(new Point(i, j)));
				} else {
					assertFalse(board.hasPiece(new Point(i, j)));
				}
			}
		}

		assertEquals(Piece.ROOK, board.getPieceName(new Point(0, 0)));
		assertEquals(Piece.KNIGHT, board.getPieceName(new Point(0, 1)));
		assertEquals(Piece.BISHOP, board.getPieceName(new Point(0, 2)));
		assertEquals(Piece.QUEEN, board.getPieceName(new Point(0, 3)));
		assertEquals(Piece.KING, board.getPieceName(new Point(0, 4)));
		assertEquals(Piece.BISHOP, board.getPieceName(new Point(0, 5)));
		assertEquals(Piece.KNIGHT, board.getPieceName(new Point(0, 6)));
		assertEquals(Piece.ROOK, board.getPieceName(new Point(0, 7)));

		assertEquals(Piece.ROOK, board.getPieceName(new Point(7, 0)));
		assertEquals(Piece.KNIGHT, board.getPieceName(new Point(7, 1)));
		assertEquals(Piece.BISHOP, board.getPieceName(new Point(7, 2)));
		assertEquals(Piece.QUEEN, board.getPieceName(new Point(7, 3)));
		assertEquals(Piece.KING, board.getPieceName(new Point(7, 4)));
		assertEquals(Piece.BISHOP, board.getPieceName(new Point(7, 5)));
		assertEquals(Piece.KNIGHT, board.getPieceName(new Point(7, 6)));
		assertEquals(Piece.ROOK, board.getPieceName(new Point(7, 7)));

		for (int i = 0; i < 8; i++) {
			assertEquals(Piece.PAWN, board.getPieceName(new Point(1, i)));
			assertEquals(Piece.PAWN, board.getPieceName(new Point(6, i)));

			assertEquals(null, board.getPieceName(new Point(2, i)));
		}
	}

	
}
