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
	private int _id = 0;
	private List<Moves> moves = new ArrayList<Moves>();
	//private List<Piece> rackPieces = new ArrayList<Piece>();
	
	
	Piece(){
		this._id = -1;
		this._team = -1;
		this._type = -1;
		this._selected = false;
		this._size = -1;		
	}
	
	Piece(int team, int type, boolean selected, int size){
		this._id = _id++;
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
		Moves move1 = new Moves();
		Moves move2 = new Moves();
		
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
	
	public List<Moves> possibleSpaces() {
		return this.moves;
	}
	
	
	public void paintComponent(Graphics g) {
		if(this._team == 0) {
			Font glade = new Font(Font.SERIF, Font.BOLD, this._size);
			g.setColor(Color.BLACK);
			g.fillOval(this._size/10, this._size/10, this._size-(this._size/5), this._size-(this._size/5));

			if(this._selected == false) {
				g.setColor(Color.BLUE);
				Graphics2D g2;
				g2 = (Graphics2D)g; 
				g2.setStroke(new BasicStroke(5));
				g2.drawOval(this._size/10, this._size/10, this._size-(this._size/5), this._size-(this._size/5));
			}
			if(this._selected == true) {
				g.setColor(Color.CYAN);
				Graphics2D g2;
				g2 = (Graphics2D)g; 
				g2.setStroke(new BasicStroke(5));
				g2.drawOval(this._size/10, this._size/10, this._size-(this._size/5), this._size-(this._size/5));
			}
			
			switch(this._type) {

			case 0:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("R", (this._size/5), this._size-(this._size/5));
				break;
			case 1:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("S", (this._size/5), this._size-(this._size/5));
				break;
			case 2:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("B", (this._size/5), this._size-(this._size/5));
				break;
			case 3:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("G", (this._size/5), this._size-(this._size/5));
				break;
			case 4:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("T", (this._size/5), this._size-(this._size/5));
				break;

		}
		}
		if(this._team == 1) {
			Font glade = new Font(Font.SERIF, Font.BOLD, this._size);
			g.setColor(Color.RED);
			g.fillOval(this._size/10, this._size/10, this._size-(this._size/5), this._size-(this._size/5));
			
			if(this._selected == true) {
				g.setColor(Color.MAGENTA);
				g.drawOval(this._size/10, this._size/10, this._size-(this._size/5), this._size-(this._size/5));
			}
			
			switch(this._type) {
			case 0:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("R", (this._size/5), this._size-(this._size/5));
				break;
			case 1:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("S", (this._size/5), this._size-(this._size/5));
				break;
			case 2:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("B", (this._size/5), this._size-(this._size/5));
				break;
			case 3:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("G", (this._size/5), this._size-(this._size/5));
				break;
			case 4:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("T", (this._size/5), this._size-(this._size/5));
				break;

			}
		}
		
	}
}
