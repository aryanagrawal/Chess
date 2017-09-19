package Tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import Model.*;

public class RookTest {

	@Test
	public void test() {
		Board board = new Board();
		assertFalse(board.move(new Point(0, 0), new Point(3, 4)));
		assertFalse(board.move(new Point(0, 0), new Point(7, 0)));
		assertFalse(board.move(new Point(0, 0), new Point(0, 7)));
		assertTrue(board.move(new Point(1, 0), new Point(3, 0)));
		assertTrue(board.move(new Point(0, 0), new Point(1, 0)));
		assertTrue(board.move(new Point(1, 0), new Point(0, 0)));
		assertTrue(board.move(new Point(0, 0), new Point(2, 0)));
		assertTrue(board.move(new Point(2, 0), new Point(2, 4)));
		assertFalse(board.move(new Point(2, 4), new Point(1, 4)));
		assertTrue(board.move(new Point(2, 4), new Point(6, 4)));
		assertFalse(board.move(new Point(6, 4), new Point(7, 3)));
		assertTrue(board.move(new Point(6, 4), new Point(7, 4)));
		assertTrue(board.move(new Point(7, 4), new Point(7, 5)));
		assertTrue(board.move(new Point(7, 5), new Point(6, 5)));
		assertTrue(board.move(new Point(6, 5), new Point(3, 5)));
		// System.out.println(board.boardPiecesToString());
	}

	@Test
	public void movesTest() {
//		Board board = new Board();
//		System.out.println(board.getPiece(new Point(0, 0)).getPossibleMoves(board.getBoard()).toString());
//		assertTrue(board.move(new Point(1, 0), new Point(3, 0)));
//		System.out.println(board.getPiece(new Point(0, 0)).getPossibleMoves(board.getBoard()).toString());
//		assertTrue(board.move(new Point(0, 0), new Point(2, 0)));
//		System.out.println(board.getPiece(new Point(2, 0)).getPossibleMoves(board.getBoard()).toString());
//		System.out.println(board.boardPiecesToString());
	}
}
