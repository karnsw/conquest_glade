package main;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class GameManager {

	private int _spaceCount;
	private int _rows;
	private int _columns;
	private int _minSpaceDim;
	private int _turn;
	private List<Player> players = new ArrayList<Player>();

	
	GameManager(){
		this._spaceCount = 0;
		this._turn = 0;
	}
	
	GameManager(int mode){
		if(mode == 0) {
			setUpGlade();
		}
	}
	
	
	public void addPlayer(Player human) {
		players.add(human);
	}
	
	
	private void setUpGlade() {
		
		_turn = 0;
		_rows = 8;
		_columns = 8;
		
		
		Player p0 = new Player(1);
		
		String s0 = (String)JOptionPane.showInputDialog("Player 0, please enter your name.","???");
		p0.set_name(s0);
		
		
		
		players.add(p0);
		p0.addRackPiece(new Piece(p0.get_teamColor(), 0, false));
		
		
		players.add(new Player(1));
		System.out.println("inital player count " + players.size());
	}
	
	
	public Player currentPlayer() {

		int playerCount = this.players.size();
		System.out.println("player count " + playerCount);
		System.out.println("turn " + _turn);
		int index = _turn % playerCount;
		System.out.println("index " + index);
		Player p = players.get(index);
		System.out.println("player p " + p);
		return p;
	}
	
	
	public int get_spaceCount(){
			return this._spaceCount;
		}
	public void inc_spaceCount() {
			this._spaceCount = _spaceCount++;
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
	
	public int get_minSpaceDim() {
		return this._minSpaceDim;
	}
	
	public void set_minSpaceDim(int dimension) {
		this._minSpaceDim = dimension;
	}
	
	public void incrementTurn() {
		this._turn++;
	}
	public int getTurn() {
		return this._turn;
	}
}


