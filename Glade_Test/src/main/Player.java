package main;

import java.util.*;

public class Player {

	private int _id;
	private int _teamColor;
	private String _name;
	private int _type;
	private int _score;
	private List<Piece> rackPieces = new ArrayList<Piece>();
	private List<Piece> boardPieces = new ArrayList<Piece>();
	

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
	
	
	
	
	
	public int get_board_count() {
		return this.boardPieces.size();
	}
	public void addBoardPiece(Piece item) {
		this.boardPieces.add(item);
	}
	public void removeBoardPiece(int type) {
		for(int i = 0; i < this.boardPieces.size(); i++) {
			if(this.boardPieces.get(i).getPieceType() == type) {
				this.boardPieces.remove(i);
			}
		}
	}

	
	public int get_rack_count() {
		return this.rackPieces.size();
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
