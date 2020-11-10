package main;

import java.util.*;

public class Player {

	private int _id;
	private int _teamColor;
	private String _name;
	private int _type;
	private int _score;
	private List<Piece> _board_pieces = new ArrayList<Piece>();
	private List<Piece> _rack_pieces = new ArrayList<Piece>();

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
	
	public void add_board_piece(Piece item) {
		this._board_pieces.add(item);
	}
	public void remove_board_piece(Piece item) {
		for(Piece i: this._board_pieces) {
			if(i.get_id() == item.get_id()) {
				this._board_pieces.remove(i);
			}
		}
	}
	public void add_rack_piece(Piece item) {
		this._rack_pieces.add(item);
	}
	public void remove_rack_piece(Piece item) {
		for(Piece i: this._rack_pieces) {
			if(i.get_id() == item.get_id()) {
				this._rack_pieces.remove(i);
			}
		}
	}
}
