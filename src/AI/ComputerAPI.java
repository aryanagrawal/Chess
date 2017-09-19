package AI;

import java.awt.Point;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class ComputerAPI {

	public String fen, move, target, starterBoard;
	public String bestMove;
	
	public static final String FORMAT = "json";
	
	public ComputerAPI(){
		fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
		starterBoard = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
		move = "";

		callAPI();
		
	}
	
	@SuppressWarnings("deprecation")
	public String setTarget(String fen, String move){
		String fenURI = URLEncoder.encode(fen);
		String moveURI = URLEncoder.encode(move);
		
		target = "http://api.underwaterchess.com/game?fen="
				+ fenURI
				+ "&move="
				+ moveURI
				+ "&format="
				+ FORMAT;
			
		return target;
	}
	
	public String getStarterBoard(){
		return starterBoard;
	}
	
	public void callAPI(){
		String myTarget = setTarget(starterBoard, "");
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(myTarget);
		String output = target.request(MediaType.TEXT_XML).get(String.class);
		table = generateDictionary(output.substring(1, output.length()-1));
	}
	
	Hashtable<String, Object> table;
	@SuppressWarnings("unchecked")
	public void move(Point from, Point to){
		String line = "abcdefgh";
		String move = "" + line.charAt(from.y) + (from.x+1) + line.charAt(to.y) + (to.x+1);
		System.out.println(move);
		
		String myTarget = setTarget(fen, move);
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(myTarget);
		String output = target.request(MediaType.TEXT_XML).get(String.class);
		table = generateDictionary(output.substring(1, output.length()-1));
		
		this.move = move;
		this.fen = (String) table.get("fen");
		
		Hashtable<String, Object> turns = (Hashtable<String, Object>) table.get("turn"); 
		this.bestMove = (String) turns.get("bestMove");
	}

	
	@SuppressWarnings("unchecked")
	public Point[] getRandomMove(){
		Hashtable<String, Object> turns = (Hashtable<String, Object>) table.get("turn");
		ArrayList<String> possibleMoves = ((ArrayList<String>)(turns).get("legalMoves"));
		String random = possibleMoves.get(new Random().nextInt(possibleMoves.size()));
		return getMoveAsPoints(random);
	}
	
	public String getMoveAsString(Point from, Point to){
		String line = "abcdefgh";
		return "" + line.charAt(from.y) + (from.x+1) + line.charAt(to.y) + (to.x+1);
	}
	
	public Point[] getMoveAsPoints(String move){
		String line = "abcdefgh";
		Point from = new Point();
		Point to = new Point();
		from.x = Integer.parseInt(""+move.charAt(1)) -1;
		from.y = line.indexOf(move.charAt(0));
		
		to.x = Integer.parseInt(""+move.charAt(3)) -1;
		to.y = line.indexOf(move.charAt(2));
		
		return new Point[] {from, to};
	}
	
	public String getBestMove(){
		return this.bestMove;
	}
	
	private static Hashtable<String, Object> generateDictionary(String output){
		Hashtable<String, Object> table = new Hashtable<String, Object>();
		String[] data = output.split(",(?!([^\\{]*\\})|([^\\[]*\\]))");

		String[] line;
		Object o;
		for (String d : data) {
			line = d.split(":(?![^{}]*\\})\\s*");

			try{
				o = Integer.parseInt(line[1]);	
			} catch(Exception e){
				o = null;
			}

			if(line[1].charAt(0) == '"' && line[1].charAt(line[1].length()-1) == '"')
				line[1] = line[1].substring(1, line[1].length()-1);
			if(line[1].equals("true"))
				o = true;
			else if(line[1].equals("false"))
				o = false;
			else if(line[1].startsWith("{")){
				o = generateDictionary(line[1].substring(1, line[1].length()-1));
			}
			
			else if(line[1].startsWith("[")){
				ArrayList<String> list = new ArrayList<String>();
				line[1] = line[1].substring(1, line[1].length()-1);
				String[] moves = line[1].split(",");
				
				for(String move : moves){
					move = move.trim();
					list.add(move.substring(1, move.length()-1));
				}
				o = list;
			}

			else{
				o = line[1];
			}

			line[0] = line[0].replaceAll("[^A-Za-z]", "");
			table.put(line[0], o);
		}
		return table;
	}
}