package Web;

import java.awt.Point;
import java.io.Serializable;

public class Message implements Serializable{

	String messageType;
	
	Point from, to;
	
	int playerCode;
	int playersConnected;
	
	// Message for connectivity
	// Use this for transmitting connectivity details,
	// messageType = STARTER
	// code = playerCode {0, 1}
	// playersConnected = number of players connected
	public Message(String messageType, int code, int playersConnected){
		this.messageType = messageType;
		this.playerCode = code;
		this.playersConnected = playersConnected;
	}
	
	// Message for piece movement
	// use this for transmitting piece movement details
	// messageType = POINT
	// from = starting position
	// to = ending position
	public Message (String messageType, Point from, Point to){
		this.messageType = messageType;
		this.from = from;
		this.to = to;
	}
	
	// Functions used by all ports
	public Message getMessage(){
		return this;
	}

	// get the type of message
	public String getMessageType(){
		return this.messageType;
	}
	
	// used by STARTER
	public int getPlayercode(){
		return this.playerCode;
	}
	
	// used by POINT
	public Point[] getPoints(){
		Point[] points = new Point[2];
		points[0] = from;
		points[1] = to;
		return points;
	}
}
