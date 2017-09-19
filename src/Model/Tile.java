package Model;

import java.awt.Point;

import Pieces.Pieces;

public class Tile {

	public Pieces piece;
	Color color;
	boolean hasPiece;
	Point position;

	public Tile(Color color, Point point) {
		piece = null;
		this.color = color;
		hasPiece = false;
		this.position = point;
	}

	public Piece getPiece() {
		if (piece != null)
			return piece.getPiece();
		else
			return null;
	}
	
	public Color getPieceColor(){
		return piece.getColor();
	}
	
	public boolean hasPiece(){
		return hasPiece;
	}
	
	public Point getPosition(){
		return position;
	}
}
