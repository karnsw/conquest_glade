package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class GameManager {

	private List<Object> colors = new ArrayList<Object>() {{
		add("blue");
		add("red");
		add("yellow");
	}};

	private int _rows;
	private int _columns;
	private int _turn;
	private List<Player> players = new ArrayList<Player>();

	
	GameManager(){
		this._turn = 0;
	}	
	
	public void setUpClassicGlade() {
		_turn = 0;
		_rows = 8;
		_columns = 8;
		
		Player p1 = new Player(1);
		setPlayerName(p1);
		setTeamColor(p1);
		initializePiecesClassicGlade(p1);
		players.add(p1);
		
		Player p2 = new Player(2);
		setPlayerName(p2);
		setTeamColor(p2);
		initializePiecesClassicGlade(p2);
		players.add(p2);	
	}
	
	
	public Player currentPlayer() {
		int playerCount = this.players.size();
		int index = _turn % playerCount;
		Player p = players.get(index);
		return p;
	}
	
	
	
	public void addPlayer(Player p) {
		players.add(p);
	}

	public int getRows() {
		return _rows;
	}

	public void setRows(int rows) {
		this._rows = rows;
	}

	public int getColumns() {
		return _columns;
	}

	public void setColumns(int columns) {
		this._columns = columns;
	}
	
	
	
	
	
	
	
	public void incrementTurn() {
		this._turn++;
	}
	public int getTurn() {
		return this._turn;
	}
	
	private void setTeamColor(Player p) {
		String name = p.getName();
		Object[] colorChoices = new Object[colors.size()];
		System.out.println("length of colors- " + colors.size());
		for(int i = 0; i < colors.size(); i++) {
			colorChoices[i] = colors.get(i);
		}
		String choice = (String)JOptionPane.showInputDialog(null, name +  " select your team color.", "team color",
														JOptionPane.QUESTION_MESSAGE, null, colorChoices, colorChoices[0]);		
		if(choice == "blue") {
			p.setColor(Color.BLUE); 
		}
		else if(choice == "red") {
			p.setColor(Color.RED);
		}
		else if(choice == "yellow"){
			p.setColor(Color.YELLOW);
		}	
		colors.remove(choice);
	}
	
	
	private void setPlayerName(Player p) {
		String name = (String)JOptionPane.showInputDialog("Player " + p.getID() + ", please enter your name.");
		p.setName(name);
	}
	
	private void initializePiecesClassicGlade(Player p) {
		p.addRackPiece(new Piece(p.getColor(), 0, false));
		p.addRackPiece(new Piece(p.getColor(), 0, false));
		p.addRackPiece(new Piece(p.getColor(), 1, false));
		p.addRackPiece(new Piece(p.getColor(), 1, false));
		p.addRackPiece(new Piece(p.getColor(), 2, false));
		p.addRackPiece(new Piece(p.getColor(), 2, false));
		p.addRackPiece(new Piece(p.getColor(), 3, false));
		p.addRackPiece(new Piece(p.getColor(), 3, false));
		p.addRackPiece(new Piece(p.getColor(), 4, false));
	}
	
	public Player Player1() {
		return players.get(0);
	}
	
	public Player Player2() {
		return players.get(1);
	}
	
}


