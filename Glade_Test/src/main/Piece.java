package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;



public class Piece extends JPanel {


	private int _team;
	private int _type;
	private boolean _selected;
	private int _size;
	private List<Move> moves = new ArrayList<Move>();
	//private List<Piece> rackPieces = new ArrayList<Piece>();
	
	
	Piece(){
		this._team = -1;
		this._type = -1;
		this._selected = false;
		this._size = -1;		
	}

	Piece(int team, int type, boolean selected){
		this._team = team;
		this._type = type;
		this._selected = selected;
		//this._size = size;
		this.setMoves();
	}
	
	
	Piece(int team, int type, boolean selected, int size){
		this._team = team;
		this._type = type;
		this._selected = selected;
		this._size = size;
		this.setMoves();
	}
	
	public int getPieceType() {
		return this._type;
	}
	
	public void toggleSelection() {
		if(this._selected == true) {
			this._selected = false;
		}
		else {
			this._selected = true;
		}
	}
	
	public boolean getSelection() {
		return this._selected;
	}
	
	public int getTeam() {
		return this._team;
	}
	
	
	private void setMoves() {
		Move move1 = new Move();
		Move move2 = new Move();
		
		if(this._type == 0) {
			move1.F1();
			this.moves.add(move1);
		}
		else if(this._type == 1) {
			move1.FR1();
			this.moves.add(move1);

			move2.FL1();
			this.moves.add(move2);
		}
	}
	
	public int getListLength() {
		return this.moves.size();
	}
	
	public List<Move> possibleSpaces() {
		return this.moves;
	}
	
	
	public void paintComponent(Graphics g) {
		int size = this.getWidth();
		
		if(this._team == 0) {
			Font glade = new Font(Font.SERIF, Font.BOLD, size);
			g.setColor(Color.BLACK);
			g.fillOval(size/10, size/10, size-(size/5), size-(size/5));

			if(this._selected == false) {
				g.setColor(Color.BLUE);
				Graphics2D g2;
				g2 = (Graphics2D)g; 
				g2.setStroke(new BasicStroke(5));
				g2.drawOval(size/10, size/10, size-(size/5), size-(size/5));
			}
			if(this._selected == true) {
				g.setColor(Color.CYAN);
				Graphics2D g2;
				g2 = (Graphics2D)g; 
				g2.setStroke(new BasicStroke(5));
				g2.drawOval(size/10, size/10, size-(size/5), size-(size/5));
			}
			
			switch(this._type) {
			case 0:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("R", (size/5), size-(size/5));
				break;
			case 1:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("S", (size/5), size-(size/5));
				break;
			case 2:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("B", (size/5), size-(size/5));
				break;
			case 3:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("G", (size/5), size-(size/5));
				break;
			case 4:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("T", (size/5), size-(size/5));
				break;
			}
		}
		if(this._team == 1) {
			Font glade = new Font(Font.SERIF, Font.BOLD, size);
			g.setColor(Color.BLACK);
			g.fillOval(size/10, size/10, size-(size/5), size-(size/5));

			if(this._selected == false) {
				g.setColor(Color.RED);
				Graphics2D g2;
				g2 = (Graphics2D)g; 
				g2.setStroke(new BasicStroke(5));
				g2.drawOval(size/10, size/10, size-(size/5), size-(size/5));
			}
			if(this._selected == true) {
				g.setColor(Color.MAGENTA);
				Graphics2D g2;
				g2 = (Graphics2D)g; 
				g2.setStroke(new BasicStroke(5));
				g2.drawOval(size/10, size/10, size-(size/5), size-(size/5));
			}
			
			switch(this._type) {
			case 0:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("R", (size/5), size-(size/5));
				break;
			case 1:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("S", (size/5), size-(size/5));
				break;
			case 2:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("B", (size/5), size-(size/5));
				break;
			case 3:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("G", (size/5), size-(size/5));
				break;
			case 4:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("T", (size/5), size-(size/5));
				break;
			}
		}
		
	}
}
