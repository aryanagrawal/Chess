package Tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import Model.*;

public class QueenTest {

	@Test
	public void test() {
		Board board = new Board();

		assertFalse(board.move(new Point(0, 3), new Point(7, 2)));
//		assertFalse(board.move(new Point(0, 3), new Point(1, 3)));
//		assertFalse(board.move(new Point(0, 3), new Point(2, 3)));
//
//		assertTrue(board.move(new Point(1, 4), new Point(2, 4)));
//
//		assertTrue(board.move(new Point(0, 3), new Point(2, 5)));
//		System.out.println(board.getPiece(new Point(2, 5)).getPossibleMoves(board.getBoard()));
//		System.out.println(board.boardPiecesToString());
	}

}
