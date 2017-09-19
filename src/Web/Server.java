package Web;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {

	public static final int SERVER_PORT = 9001;
	private static ServerSocket sock;
	private static List<ObjectOutputStream> clients = Collections.synchronizedList(new ArrayList<>());

	public static void main(String[] args) throws IOException {
		sock = new ServerSocket(SERVER_PORT);
		System.out.println("Server started on port " + SERVER_PORT);

		int players = 0;
		while (players != 2) {
			Socket socket = null;
			socket = sock.accept();
			ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
			clients.add(os);
			ClientHandler handler = new ClientHandler(is, clients);
			handler.players++;
			handler.start();
			System.out.println("Accepted a new connection from " + socket.getInetAddress());
			players++;
		}
	}
}

class ClientHandler extends Thread {
	private ObjectInputStream input;
	private List<ObjectOutputStream> clients;
	public int players;
	private boolean gameStart;

	private static final String STARTER = "starter";
	private static final String POINT = "point";

	public ClientHandler(ObjectInputStream input, List<ObjectOutputStream> clients) {
		this.input = input;
		this.clients = clients;
		this.players = 0;
		this.gameStart = false;
	}

	@Override
	public void run() {
		while (true) {
			if (gameStart) {
				try {
					Message message = (Message) input.readObject();
					if (message.getMessageType().equals(POINT)) {
						writeMovesToClients(message);
					}

				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (!gameStart) {
				gameStart = true;

				try {
					for (int i = 0; i < clients.size(); i++) {
						Message starter = new Message(STARTER, i, clients.size());
						clients.get(i).writeObject(starter);
						clients.get(i).reset();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void writeMovesToClients(Message move) {
		synchronized (clients) {
			for (int i = 0; i < clients.size(); i++) {
				try {
					clients.get(i).writeObject(move);
					clients.get(i).reset();
				} catch (IOException e) {
					clients.remove(clients.get(i));
					e.printStackTrace();
				}
			}
		}
	}
}