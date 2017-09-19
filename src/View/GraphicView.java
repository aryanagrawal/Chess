package View;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Model.Chess;
import Model.Color;
import Model.Piece;

public class GraphicView extends JPanel implements Observer {

	Chess chess;
	private Graphics2D g2;
	Image black, white, blackRook, blackKnight, blackBishop, blackKing, blackQueen, blackPawn, whiteRook, whiteKnight,
			whiteBishop, whiteKing, whiteQueen, whitePawn;
	public GraphicView(Chess chess) {

		try {
			black = ImageIO.read(new File("images/brown.png"));
			white = ImageIO.read(new File("images/marble.png"));

			blackRook = ImageIO.read(new File("images/blackRook.png"));
			blackKnight = ImageIO.read(new File("images/blackKnight.png"));
			blackBishop = ImageIO.read(new File("images/blackBishop.png"));
			blackKing = ImageIO.read(new File("images/blackKing.png"));
			blackQueen = ImageIO.read(new File("images/blackQueen.png"));
			blackPawn = ImageIO.read(new File("images/blackPawn.png"));
			whiteRook = ImageIO.read(new File("images/whiteRook.png"));
			whiteKnight = ImageIO.read(new File("images/whiteKnight.png"));
			whiteBishop = ImageIO.read(new File("images/whiteBishop.png"));
			whiteKing = ImageIO.read(new File("images/whiteKing.png"));
			whiteQueen = ImageIO.read(new File("images/whiteQueen.png"));
			whitePawn = ImageIO.read(new File("images/whitePawn.png"));
//			chess.playerCode = 1;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.chess = chess;
		setSize(440, 440);
		repaint();
	}

	@Override
	public void update(Observable o, Object arg) {
	}

	@Override
	public void paintComponent(Graphics g) {
		int k = chess.playerCode;
		int iNew, jNew;
		System.out.println(chess.playerCode);
		g2 = (Graphics2D) g;
		g2.drawLine(20, 20, 660, 20);
		g2.drawLine(20, 20, 20, 660);
		g2.drawLine(660, 660, 20, 660);
		g2.drawLine(660, 660, 660, 20);
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				iNew = 7 * k + (int) Math.pow(-1, k) * i;
				jNew = 7 * k + (int) Math.pow(-1, k) * j;

				
				
				if (chess.getTileColor(i, j).equals(Color.BLACK))
					g2.drawImage(black, jNew * 80 + 20, 580 - iNew * 80, 80, 80, null);
				else
					g2.drawImage(white, jNew * 80 + 20, 580 - iNew * 80, 80, 80, null);

				if (chess.hasPiece(i, j)) {
					Object[] piece = chess.getPiece(i, j);
					if (piece[0].equals(Piece.ROOK) && piece[1].equals(Color.BLACK))
						g2.drawImage(blackRook, 10 + jNew * 80 + 20, 580 - iNew * 80, 60, 80, null);
					else if (piece[0].equals(Piece.ROOK) && piece[1].equals(Color.WHITE))
						g2.drawImage(whiteRook, 10 + jNew * 80 + 20, 580 - iNew * 80, 60, 80, null);
					else if (piece[0].equals(Piece.KNIGHT) && piece[1].equals(Color.BLACK))
						g2.drawImage(blackKnight, 10 + jNew * 80 + 20, 580 - iNew * 80, 60, 80, null);
					else if (piece[0].equals(Piece.KNIGHT) && piece[1].equals(Color.WHITE))
						g2.drawImage(whiteKnight, 10 + jNew * 80 + 20, 580 - iNew * 80, 60, 80, null);
					else if (piece[0].equals(Piece.BISHOP) && piece[1].equals(Color.BLACK))
						g2.drawImage(blackBishop, 10 + jNew * 80 + 20, 580 - iNew * 80, 60, 80, null);
					else if (piece[0].equals(Piece.BISHOP) && piece[1].equals(Color.WHITE))
						g2.drawImage(whiteBishop, 10 + jNew * 80 + 20, 580 - iNew * 80, 60, 80, null);
					else if (piece[0].equals(Piece.KING) && piece[1].equals(Color.BLACK))
						g2.drawImage(blackKing, 10 + jNew * 80 + 20, 580 - iNew * 80, 60, 80, null);
					else if (piece[0].equals(Piece.KING) && piece[1].equals(Color.WHITE))
						g2.drawImage(whiteKing, 10 + jNew * 80 + 20, 580 - iNew * 80, 60, 80, null);
					else if (piece[0].equals(Piece.QUEEN) && piece[1].equals(Color.BLACK))
						g2.drawImage(blackQueen, 10 + jNew * 80 + 20, 580 - iNew * 80, 60, 80, null);
					else if (piece[0].equals(Piece.QUEEN) && piece[1].equals(Color.WHITE))
						g2.drawImage(whiteQueen, 10 + jNew * 80 + 20, 580 - iNew * 80, 60, 80, null);
					else if (piece[0].equals(Piece.PAWN) && piece[1].equals(Color.BLACK))
						g2.drawImage(blackPawn, 10 + jNew * 80 + 20, 580 - iNew * 80, 60, 80, null);
					else if (piece[0].equals(Piece.PAWN) && piece[1].equals(Color.WHITE))
						g2.drawImage(whitePawn, 10 + jNew * 80 + 20, 580 - iNew * 80, 60, 80, null);
				}
			}
		}

	}
}
