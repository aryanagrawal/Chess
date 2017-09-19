package AI;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Model.Chess;
import View.GraphicView;

public class ChessGUI extends JFrame {

	private Chess chess;
	private MyListener actionList;
	private GraphicView chessView;

	private int playerCode;
	private int aiCode;
	private String playertoMove;

	ComputerAPI api;

	public ChessGUI() {
		chess = new Chess();
		chessView = new GraphicView(chess);
		actionList = new MyListener();
		chessView.addMouseListener(actionList);
		setSize(690 + 500, 710);
		setLocation(50, 30);
		setTitle("Chess");
		add(chessView);
		api = new ComputerAPI();

		String[] options = new String[] { "White", "Black" };
		playerCode = JOptionPane.showOptionDialog(null, "Chose your Piece Color", "Chess", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		if (playerCode == 0)
			playertoMove = "human";
		else
			playertoMove = "ai";

		 chess.playerCode = playerCode;
		// aiCode = 1 - playerCode;
		// System.out.println("aiCode = " + aiCode);

		if (playertoMove.equals("ai")) {
			Point[] move = api.getRandomMove();
			api.move(move[0], move[1]);
			chess.move(move[0], move[1]);
			chessView.repaint();
			playertoMove = "human";
		}
	}

	public static void main(String[] args) {
		ChessGUI chessGUI = new ChessGUI();
		chessGUI.setVisible(true);
	}

	private class MyListener implements MouseListener, MouseMotionListener {

		Point from, to;

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (playertoMove.equals("ai"))
				return;
			else {
				if (playerCode == 0)
					from = new Point(7 - ((e.getPoint().y - 20) / 80), (e.getPoint().x - 20) / 80);
				else if (playerCode == 1)
					from = new Point(7 - (7 - ((e.getPoint().y - 20) / 80)), 7 - ((e.getPoint().x - 20) / 80));
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
			if (playertoMove.equals("human")){
				if (playerCode == 0) {
					to = new Point(7 - ((e.getPoint().y - 20) / 80), (e.getPoint().x - 20) / 80);
//					chessView.repaint();
					chess.move(from, to);
					
					System.out.println("running");
//					api.move(from, to);
					
				} else if (playerCode == 1) {
					to = new Point(((e.getPoint().y - 20) / 80), 7 - ((e.getPoint().x - 20) / 80));
					
					chess.move(from, to);
					
					System.out.println("running");
//					api.move(from, to);
					
				}
				playertoMove = "ai";
				chessView.repaint();
				api.move(from, to);
				if(playerCode == 0){
					String bestMove = api.bestMove;
					Point[] move = api.getMoveAsPoints(bestMove);
					chess.move(move[0], move[1]);
					chessView.repaint();
					api.move(move[0], move[1]);
					
					
				} else if(playerCode == 1){
					String bestMove = api.bestMove;
					System.out.println("bestMove = " + api.bestMove);
					Point[] move = api.getMoveAsPoints(bestMove);

					chess.move(move[0], move[1]);
					chessView.repaint();
					api.move(move[0], move[1]);

				}
				playertoMove = "human";
			}
			chessView.repaint();

		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseDragged(MouseEvent e) {
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		}
	}
}