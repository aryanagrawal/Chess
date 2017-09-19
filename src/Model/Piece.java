package Model;

public enum Piece {
	PAWN("Pawn"), ROOK("Rook"), KNIGHT("Knight"), BISHOP("Bishop"), KING("King"), QUEEN("Queen");
	
	public final String name;
	
	Piece(String name){
		this.name=name;
	}
	
	public String toString(){
		return name;
	}
	
}
