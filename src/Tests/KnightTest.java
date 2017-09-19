package Tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import Model.*;

public class KnightTest {

	@Test
	public void test() {
		Board board = new Board();

		assertFalse(board.move(new Point(0, 1), new Point(0, 1)));
		assertFalse(board.move(new Point(0, 1), new Point(0, 0)));
		assertFalse(board.move(new Point(0, 1), new Point(1, 0)));
		assertFalse(board.move(new Point(0, 1), new Point(2, 1)));
		assertFalse(board.move(new Point(0, 1), new Point(7, 7)));
		assertFalse(board.move(new Point(0, 1), new Point(2, 3)));

		assertTrue(board.move(new Point(0, 1), new Point(2, 2)));
		assertFalse(board.move(new Point(2, 2), new Point(2, 0)));
		assertTrue(board.move(new Point(2, 2), new Point(0, 1)));
		assertTrue(board.move(new Point(0, 1), new Point(2, 0)));

		assertTrue(board.move(new Point(2, 0), new Point(0, 1)));
		assertTrue(board.move(new Point(0, 1), new Point(2, 2)));
		assertTrue(board.move(new Point(2, 2), new Point(4, 3)));
		assertTrue(board.move(new Point(4, 3), new Point(6, 4)));

		assertTrue(board.move(new Point(6, 4), new Point(7, 2)));
		assertTrue(board.move(new Point(7, 2), new Point(5, 3)));
		assertTrue(board.move(new Point(5, 3), new Point(7, 4)));
		assertFalse(board.move(new Point(7, 4), new Point(8, 6)));
		//

		// System.out.println(board.boardPiecesToString());
	}
	
	@Test
	public void testPossibleMoves(){
		Board board = new Board();
//		System.out.println(board.getPiece(new Point(0, 1)).getPossibleMoves(board.getBoard()).toString());
		assertTrue(board.move(new Point(0, 1), new Point(2, 2)));
//		System.out.println(board.getPiece(new Point(2, 2)).getPossibleMoves(board.getBoard()).toString());
//		System.out.println(board.boardPiecesToString());
	}

}
