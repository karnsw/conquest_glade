package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JPanel;

public class SquareSpace{

	private int _dim;
	private int _color;
	private int _id;

	SquareSpace(int sideLength, int color, GameManger manage){
		_id = manage.get_spaceCount();
		manage.inc_spaceCount();
		this.set_dim(sideLength);
		this.set_color(color);
	}
	
	public int get_id(){
		return _id;
	}
	public int get_dim() {
		return _dim;
	}
	public void set_dim(int sideLength) {
		this._dim = sideLength;
	}
	public int get_color() {
		return _color;
	}
	public void set_color(int color) {
		this._color = color;
	}
	
	
	public void draw() {
		switch(get_color()) {
		case 0:
			new BlueSquare();
		case 1:
			new GraySquare();

		}
	}
}