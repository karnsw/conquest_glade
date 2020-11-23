package main;

public class GameManager {

	private int _spaceCount;
	private int _rows;
	private int _columns;
	private int _minSpaceDim;
	private int _turn = 0;
	
	GameManager(){
		this._spaceCount = 0;
	}
	
	public int get_spaceCount(){
			return this._spaceCount;
		}
	public void inc_spaceCount() {
			this._spaceCount = _spaceCount++;
		}

	public int get_rows() {
		return _rows;
	}

	public void set_rows(int rows) {
		this._rows = rows;
	}

	public int get_columns() {
		return _columns;
	}

	public void set_columns(int columns) {
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


