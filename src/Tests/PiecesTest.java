package Tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import Model.Color;
import Model.Piece;
import Pieces.*;

public class PiecesTest {

	@Test
	public void test() {
		Pieces Rook = new Rook(Color.WHITE, Piece.ROOK, new Point(0, 0));
		assertTrue(Piece.ROOK.equals(Rook.getPiece()));
		assertTrue(Rook.getColor().equals(Color.WHITE));
		assertTrue(Rook.getCurrentPosition().equals(new Point(0, 0)));
	}

}
