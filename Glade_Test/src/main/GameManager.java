package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class GameManager {

	private static final int
	RABBIT = 0,
	SNAKE = 1,
	BIRD = 2,
	GROUNDHOG = 3,
	TURTLE = 4;

	private List<Object> colors = new ArrayList<Object>() {{
		add("blue");
		add("red");
		add("yellow");
	}};
	
	private Color boardAColor;
	private Color boardBColor;
	private Color boarderColor;
	private Color rackColor;
	private Color panelColor;
		
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
		setBoardAColor(Color.GRAY);
		setBoardBColor(Color.LIGHT_GRAY);
		setBoarderColor(Color.BLACK);
		setRackColor(Color.GRAY);
		setPanelColor(Color.LIGHT_GRAY);
		
		Player p1 = new Player(1);
		setPlayerNameMenu(p1);
		setTeamColorMenu(p1);
		p1.setTeam(1);
		p1.setTerritory(new char[]{'T','4'});
		initializePiecesClassicGlade(p1);
		initializeSpacesToDefendClassicGlade(p1);
		players.add(p1);
		
		Player p2 = new Player(2);
		setPlayerNameMenu(p2);
		setTeamColorMenu(p2);
		p2.setTeam(2);
		p2.setTerritory(new char[]{'B','4'});
		initializePiecesClassicGlade(p2);
		initializeSpacesToDefendClassicGlade(p2);
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
	public int getTotalTurns() {
		return this._turn;
	}
	
	private void setTeamColorMenu(Player p) {
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
	
	
	private void setPlayerNameMenu(Player p) {
		String name = (String)JOptionPane.showInputDialog("Player " + p.getID() + ", please enter your name.");
		if(name.length() > 15) {
			char[] arr = new char[15];
			for(int i = 0; i < 15; i++) {
				arr[i] = name.charAt(i);
			}
			String shortName = String.valueOf(arr);
			name = shortName;
		}
		p.setName(name);
	}
	
	private void initializePiecesClassicGlade(Player p) {
		p.addRackPiece(new Piece(p.getColor(), p.getID(), p.getTerritory()[0], p.getTeam(), RABBIT, false));
		p.addRackPiece(new Piece(p.getColor(), p.getID(), p.getTerritory()[0], p.getTeam(), RABBIT, false));
		p.addRackPiece(new Piece(p.getColor(), p.getID(), p.getTerritory()[0], p.getTeam(), SNAKE, false));
		p.addRackPiece(new Piece(p.getColor(), p.getID(), p.getTerritory()[0], p.getTeam(), SNAKE, false));
		p.addRackPiece(new Piece(p.getColor(), p.getID(), p.getTerritory()[0], p.getTeam(), BIRD, false));
		p.addRackPiece(new Piece(p.getColor(), p.getID(), p.getTerritory()[0], p.getTeam(), BIRD, false));
		p.addRackPiece(new Piece(p.getColor(), p.getID(), p.getTerritory()[0], p.getTeam(), GROUNDHOG, false));
		p.addRackPiece(new Piece(p.getColor(), p.getID(), p.getTerritory()[0], p.getTeam(), GROUNDHOG, false));
		p.addRackPiece(new Piece(p.getColor(), p.getID(), p.getTerritory()[0], p.getTeam(), TURTLE, false));
	}
	
	private void initializeSpacesToDefendClassicGlade(Player p) {
		int row;
		for(int col = 0; col < _columns; col++) {
			if(p.getTeam() == 1) {
				row = 0;
				p.addDefendSpace(new Space(col, row));	//top row
			}
			else if(p.getTeam() == 2) {
				row = _rows-1;
				p.addDefendSpace(new Space(col, row));	//bottom row
			}
		}
	}
	
	
	public Player Player1() {
		return players.get(0);
	}
	
	public Player Player2() {
		return players.get(1);
	}

	public List<Player> getPlayers(){
		return players;
	}
	
	
	
	
	
	
	public Color getBoardAColor() {
		return boardAColor;
	}

	public void setBoardAColor(Color boardAColor) {
		this.boardAColor = boardAColor;
	}

	public Color getBoardBColor() {
		return boardBColor;
	}

	public void setBoardBColor(Color boardBColor) {
		this.boardBColor = boardBColor;
	}

	public Color getBoarderColor() {
		return boarderColor;
	}

	public void setBoarderColor(Color boarderColor) {
		this.boarderColor = boarderColor;
	}

	public Color getRackColor() {
		return rackColor;
	}

	public void setRackColor(Color rackColor) {
		this.rackColor = rackColor;
	}

	public Color getPanelColor() {
		return panelColor;
	}

	public void setPanelColor(Color panelColor) {
		this.panelColor = panelColor;
	}
	
	
}


