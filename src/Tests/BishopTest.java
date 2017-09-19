package Tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import Model.*;

public class BishopTest {

	@Test
	public void test() {
//		Board board = new Board();
//		assertFalse(board.move(new Point(0,2), new Point(4,5)));
//		assertFalse(board.move(new Point(1,3), new Point(4,5)));
//		assertFalse(board.move(new Point(0,2), new Point(1,1)));
//		assertTrue(board.move(new Point(1,1), new Point(2,1)));
//		assertTrue(board.move(new Point(0,2), new Point(2,0)));
//		assertTrue(board.move(new Point(2,0), new Point(1,1)));
//		
//		assertFalse(board.move(new Point(1,1), new Point(7,7)));
//		assertTrue(board.move(new Point(1,1), new Point(6,6)));
//		
//		assertTrue(board.move(new Point(6,6), new Point(7,5)));
//		assertTrue(board.move(new Point(7,5), new Point(6,4)));
		
		
		
//		System.out.println(board.boardPiecesToString());
	}
	
	@Test
	public void moveTest(){
		Board board = new Board();
//		System.out.println(board.getPiece(new Point(0, 2)).getPossibleMoves(board.getBoard()));
		assertTrue(board.move(new Point(1,4), new Point(3,4)));
//		System.out.println(board.getPiece(new Point(0, 2)).getPossibleMoves(board.getBoard()));
//		assertTrue(board.move(new Point(0,2), new Point(1,1)));
//		System.out.println(board.getPiece(new Point(1, 1)).getPossibleMoves(board.getBoard()));
		assertTrue(board.move(new Point(0,5), new Point(3,2)));
//		
//		System.out.println(board.getPiece(new Point(1, 3)).getPossibleMoves(board.getBoard()));
//		System.out.println(board.boardPiecesToString());
		
	}

}
