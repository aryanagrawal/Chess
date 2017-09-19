package Model;

import java.awt.Point;

public class Chess {

	public int playerCode;
	Board map;
	Tile[][] gameBoard;

	Color lastMoved; // 1 for white -1 for black

	public Chess() {
		map = new Board();
		gameBoard = map.getBoard();
		lastMoved = Color.BLACK;
	}

	public Chess(int code) {
		map = new Board();
		gameBoard = map.getBoard();
		lastMoved = Color.BLACK;
		this.playerCode = code;
	}
	
	public int getCode(){
		return playerCode;
	}
	
	public void move(Point from, Point to) {

		
		if(from.x < 0 || from.x >7 || from.y < 0 || from.y >7 || to.x < 0 || to.x >7 ||to.y < 0 || to.y >7)
			return;

		if(!hasPiece(from.x, from.y))
			return;

		if (lastMoved.equals(Color.BLACK) && gameBoard[from.x][from.y].getPieceColor().equals(Color.WHITE)) {
			if (map.move(from, to))
				lastMoved = Color.WHITE;
		}

		else if (lastMoved.equals(Color.WHITE) && gameBoard[from.x][from.y].getPieceColor().equals(Color.BLACK)) {
			if (map.move(from, to))
				lastMoved = Color.BLACK;
		}

	}

	public Object[] getPiece(int i, int j) {
		Object object[] = new Object[2];
		object[0] = gameBoard[i][j].getPiece();
		object[1] = gameBoard[i][j].getPieceColor();
		return object;
	}

	public boolean hasPiece(int i, int j) {
		return gameBoard[i][j].hasPiece;
	}

	public Color getTileColor(int i, int j) {
		return gameBoard[i][j].color;
	}
}
