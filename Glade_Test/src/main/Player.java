package main;

import java.util.*;

public class Player {

	private int _id;
	private int _teamColor;
	private String _name;
	private int _type;
	private int _score;
	private Piece[] _board_pieces = new Piece[8];
	private Piece[] _rack_pieces = new Piece[8];
	private List<Piece> rackPieces = new ArrayList<Piece>();
	

	Player(int id){
		this._id = id;
	}
	Player(int id, int teamColor, String name){
		this._id = id;
		this.set_teamColor(teamColor);
		this.set_name(name);
	}
	Player(int id, int teamColor, String name, int type){
		this._id = id;
		this.set_teamColor(teamColor);
		this.set_name(name);
		this.set_type(type);	
	}
	
	public int get_id() {
		return _id;
	}
	public int get_teamColor() {
		return _teamColor;
	}
	public void set_teamColor(int _teamColor) {
		this._teamColor = _teamColor;
	}
	public String get_name() {
		return _name;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
	public int get_type() {
		return _type;
	}
	public void set_type(int _type) {
		this._type = _type;
	}
	public int get_score() {
		return _score;
	}
	public void set_score(int _score) {
		this._score = _score;
	}
	
	public void add_board_piece(Piece item, int pos) {
		this._board_pieces[pos] = item;
	}
	public void remove_board_piece(Piece item, int pos) {
		this._board_pieces[pos] = null;
	}

	public void add_rack_piece(Piece item, int pos) {
		this._rack_pieces[pos] = item;
	}
	
	
	
	public void remove_rack_piece(int item) {
		for(int i = 0; i < this._rack_pieces.length; i++) {
			if(this._rack_pieces[i].getPieceType() == item) {
				this._rack_pieces[i] = this._rack_pieces[i+1];
			}
		}
	}
	
	
	public void addRackPiece(Piece item) {
		this.rackPieces.add(item);
	}
	
	public void removeRackPiece(int type) {
		for(int i = 0; i < this.rackPieces.size(); i++) {
			if(this.rackPieces.get(i).getPieceType() == type) {
				this.rackPieces.remove(i);
			}
		}
	}
	
	
	public int get_board_count() {
		return this._rack_pieces.length;
	}
	public int get_rack_count() {
		return this._rack_pieces.length;
	}
	public Piece get_rack_piece(int pos) {
		return this._rack_pieces[pos];
	}
	public Piece get_board_piece(int pos) {
		return this._rack_pieces[pos];
	}
	
	
	
	public int getRabbitCount(){
		int count = 0;
		for(int i = 0; i < this.rackPieces.size(); i++) {
			if(this.rackPieces.get(i).getPieceType() == 0) {
				count++;
			}
		}
		return count;
	}
	
	public int getSnakeCount(){
		int count = 0;
		for(int i = 0; i < this.rackPieces.size(); i++) {
			if(this.rackPieces.get(i).getPieceType() == 1) {
				count++;
			}
		}
		return count;
	}
	
	public int getBirdCount(){
		int count = 0;
		for(int i = 0; i < this.rackPieces.size(); i++) {
			if(this.rackPieces.get(i).getPieceType() == 2) {
				count++;
			}
		}
		return count;
	}
	
	public int getGroundhogCount(){
		int count = 0;
		for(int i = 0; i < this.rackPieces.size(); i++) {
			if(this.rackPieces.get(i).getPieceType() == 3) {
				count++;
			}
		}
		return count;
	}
	
	public int getTurtleCount(){
		int count = 0;
		for(int i = 0; i < this.rackPieces.size(); i++) {
			if(this.rackPieces.get(i).getPieceType() == 4) {
				count++;
			}
		}
		return count;
	}
}
