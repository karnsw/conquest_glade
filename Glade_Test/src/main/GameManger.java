package main;

public class GameManger {

	private int _spaceCount;
	private int _rows = 8;
	private int _columns = 8;
	private int _minSpaceDim = 50;
	
	GameManger(){
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
	
}


