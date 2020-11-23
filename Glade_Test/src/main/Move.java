package main;

public class Move {

	int _Forward;
	int _Backward;
	int _Left;
	int _Right;
	
	Move() {
		this._Forward = 0;
		this._Backward = 0;
		this._Left = 0;
		this._Right = 0;
	}
	
	public void R1() {
		this._Forward = 0;
		this._Backward = 0;
		this._Left = 0;
		this._Right = 1;
	}
	
	public void L1() {
		this._Forward = 0;
		this._Backward = 0;
		this._Left = 1;
		this._Right = 0;
	}
	
	public void F1() {
		this._Forward = 1;
		this._Backward = 0;
		this._Left = 0;
		this._Right = 0;
	}

	public void B1() {
		this._Forward = 0;
		this._Backward = 1;
		this._Left = 0;
		this._Right = 0;
	}
	public void FL1() {
		this._Forward = 1;
		this._Backward = 0;
		this._Left = 1;
		this._Right = 0;
	}
	public void FR1() {
		this._Forward = 1;
		this._Backward = 0;
		this._Left = 0;
		this._Right = 1;
	}
}
