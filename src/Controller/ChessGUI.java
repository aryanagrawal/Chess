package Controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import Model.Chess;
import View.GraphicView;

public class ChessGUI extends JFrame {

	private Chess chess;
	private MyListener actionList;
	private GraphicView chessView;

	public ChessGUI() {
		chess = new Chess();
		chessView = new GraphicView(chess);
		actionList = new MyListener();
		chessView.addMouseListener(actionList);
		setSize(690 + 500, 710);
		setLocation(50, 30);
		setTitle("Chess");
		add(chessView);
	}

	public static void main(String[] args) {
		ChessGUI chessGUI = new ChessGUI();
		chessGUI.setVisible(true);
	}

	private class MyListener implements MouseListener, MouseMotionListener {

		Point from, to;
		boolean piecePicked = false;

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			from = new Point(7 - ((e.getPoint().y - 20) / 80), (e.getPoint().x - 20) / 80);
			piecePicked = true;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			to = new Point(7 - ((e.getPoint().y - 20) / 80), (e.getPoint().x - 20) / 80);
			piecePicked = false;
			chess.move(from, to);
			chessView.repaint();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			if (piecePicked) {
				Point point = from;

			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
