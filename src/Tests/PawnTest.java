package Tests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Test;

import Model.Board;

public class PawnTest {

	@Test
	public void testPawn() {

		Board board = new Board();

		assertTrue(board.move(new Point(1, 1), new Point(2, 1)));
		assertTrue(board.move(new Point(1, 5), new Point(3, 5)));
		assertFalse(board.move(new Point(2, 1), new Point(4, 1)));

		assertTrue(board.move(new Point(6, 1), new Point(5, 1)));
		assertTrue(board.move(new Point(6, 5), new Point(4, 5)));
		assertFalse(board.move(new Point(5, 1), new Point(3, 1)));

		assertFalse(board.move(new Point(3, 5), new Point(4, 5)));
		assertFalse(board.move(new Point(3, 5), new Point(5, 5)));

		assertTrue(board.move(new Point(6, 0), new Point(4, 0)));
		assertTrue(board.move(new Point(4, 0), new Point(3, 0)));
		assertTrue(board.move(new Point(3, 0), new Point(2, 0)));
		assertFalse(board.move(new Point(1, 0), new Point(2, 0)));
		assertFalse(board.move(new Point(1, 0), new Point(3, 0)));

		assertTrue(board.move(new Point(1, 4), new Point(3, 4)));
		assertTrue(board.move(new Point(3, 4), new Point(4, 5)));
		
		assertFalse(board.move(new Point(1, 2), new Point(2, 3)));
		assertFalse(board.move(new Point(1, 3), new Point(2, 2)));

		assertFalse(board.move(new Point(6, 3), new Point(5, 2)));
		assertFalse(board.move(new Point(6, 3), new Point(5, 4)));
		
		assertFalse(board.move(new Point(4, 5), new Point(3, 5)));
		assertFalse(board.move(new Point(2, 0), new Point(1, 0)));
		
		assertTrue(board.move(new Point(6, 3), new Point(5, 3)));
		
		
		assertTrue(board.move(new Point(1, 7), new Point(3, 7)));
		assertTrue(board.move(new Point(3, 7), new Point(4, 7)));
		
		assertFalse(board.move(new Point(6, 7), new Point(4, 7)));
		assertTrue(board.move(new Point(4, 7), new Point(5, 7)));
		assertFalse(board.move(new Point(1, 7), new Point(3, 7)));
		assertFalse(board.move(new Point(1, 7), new Point(3, 7)));
		
		
//		System.out.println(board.boardPiecesToString());

	}
	
	@Test
	public void testPossibleMoves(){
		Board board = new Board();
		
		System.out.println(board.getPiece(new Point(1, 1)).getPossibleMoves(board.getBoard()).toString());
		assertTrue(board.move(new Point(1, 1), new Point(2, 1)));
		System.out.println(board.getPiece(new Point(2, 1)).getPossibleMoves(board.getBoard()).toString());

		System.out.println(board.getPiece(new Point(6, 1)).getPossibleMoves(board.getBoard()).toString());
		assertTrue(board.move(new Point(6, 1), new Point(5, 1)));
		System.out.println(board.getPiece(new Point(5, 1)).getPossibleMoves(board.getBoard()).toString());
		
		assertTrue(board.move(new Point(1, 5), new Point(3, 5)));
		assertTrue(board.move(new Point(6, 5), new Point(4, 5)));
		assertTrue(board.move(new Point(6, 4), new Point(4, 4)));
		
		System.out.println(board.getPiece(new Point(3, 5)).getPossibleMoves(board.getBoard()).toString());
		System.out.println(board.getPiece(new Point(4, 5)).getPossibleMoves(board.getBoard()).toString());
		System.out.println(board.getPiece(new Point(4, 4)).getPossibleMoves(board.getBoard()).toString());
		
		System.out.println(board.boardPiecesToString());
	}

	
	@Test
	public void testMoves(){
		Board board = new Board();
		assertTrue(board.move(new Point(1, 5), new Point(3, 5)));
		assertTrue(board.move(new Point(1, 3), new Point(3, 3)));
		assertTrue(board.move(new Point(6, 4), new Point(4, 4)));
		System.out.println(board.boardPiecesToString());
		
		System.out.println(board.getPiece(new Point(3, 5)).getPossibleMoves(board.getBoard()).toString());
		System.out.println(board.getPiece(new Point(3, 3)).getPossibleMoves(board.getBoard()).toString());
		System.out.println(board.getPiece(new Point(4, 4)).getPossibleMoves(board.getBoard()).toString());
	}

}
