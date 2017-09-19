package Web;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Model.Chess;
import Model.Color;
import View.GraphicView;

@SuppressWarnings("serial")
public class Client extends JFrame {

	int playerCode;
	Point[] currentMove;
	Chess chess;
	
	MyListener actionList;
	GraphicView view;

	boolean gameStarted;

	private static final String ADDRESS = "localhost";
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	private static final String POINT = "point";
	private static final String STARTER = "starter";

	public Client(String username) throws UnknownHostException, IOException {
		setSize(690, 710);
		setLocation(50, 30);
		setTitle("Chess- " + username);
		
		gameStarted = false;

		currentMove = new Point[2];
		chess = new Chess();
		view = new GraphicView(chess);

		actionList = new MyListener();
		view.addMouseListener(actionList);
		view.addMouseMotionListener(actionList);

		this.add(view);
		view.repaint();
		socket = new Socket(ADDRESS, Server.SERVER_PORT);
		oos = new ObjectOutputStream(socket.getOutputStream());
		ois = new ObjectInputStream(socket.getInputStream());
		ServerListener listener = new ServerListener();
		listener.start();
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		String username = JOptionPane.showInputDialog("Username?");
		(new Client(username)).setVisible(true);
	}

	private class ServerListener extends Thread {

		
		private int playersConnected=0;
		
		@Override
		public void run() {
	
			try {
				while (true) {
					Message message = (Message) ois.readObject();
					if(gameStarted && message.getMessageType().equals(POINT)){
						Point[] move = message.getPoints();
						currentMove[0] = move[0];
						currentMove[1] = move[1];
						try {
							chess.move(move[0], move[1]);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						view.repaint();
					}
					
					else if (!gameStarted && message.getMessageType().equals(STARTER)){
						playerCode = message.getPlayercode();
						chess.playerCode = playerCode;
						playersConnected = message.playersConnected;
						if(playersConnected == 2)
							gameStarted = true;
						view.repaint();
					}
				}
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			view.repaint();
		}
	}

	private class MyListener implements MouseListener, MouseMotionListener {

		Point from, to;

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {

			if (playerCode == 0) {
				from = new Point(7 - ((e.getPoint().y - 20) / 80), (e.getPoint().x - 20) / 80);

			} else if (playerCode == 1) {
				from = new Point(7 - (7 - ((e.getPoint().y - 20) / 80)), 7 - ((e.getPoint().x - 20) / 80));
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {

			if(!gameStarted)
				return;
			
			if(chess.hasPiece(from.x, from.y)){
				Color color = (Color) chess.getPiece(from.x, from.y)[1];
				if((playerCode==0 && color.equals(Color.BLACK)) || (playerCode==1 && color.equals(Color.WHITE)))
					return;
			}
			
			if (playerCode == 0) {
				to = new Point(7 - ((e.getPoint().y - 20) / 80), (e.getPoint().x - 20) / 80);
				try {
					chess.move(from, to);
				} catch (Exception cantMove) {
					cantMove.printStackTrace();
				}
				Point[] move = new Point[3];
				move[0] = from;
				move[1] = to;
				move[2] = new Point(chess.playerCode, chess.playerCode);
				try {
					Message message = new Message("point", from, to);
					oos.writeObject(message);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				view.repaint();
			} else if (playerCode == 1) {
				to = new Point(((e.getPoint().y - 20) / 80), 7 - ((e.getPoint().x - 20) / 80));
				try {
					chess.move(from, to);
				} catch (Exception cantMove) {
					cantMove.printStackTrace();
				}
				Point[] move = new Point[3];
				move[0] = from;
				move[1] = to;
				move[2] = new Point(chess.playerCode, chess.playerCode);
				try {
					Message message = new Message(POINT, from, to);
					oos.writeObject(message);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				view.repaint();
			}
			
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