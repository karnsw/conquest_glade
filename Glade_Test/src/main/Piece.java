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

private static final int
	RABBIT = 0,
	SNAKE = 1,
	BIRD = 2,
	GROUNDHOG = 3,
	TURTLE = 4;
	
	private int col;
	private int row;
	private int team;
	private int type;
	private boolean selected;
	private List<Move> moves = new ArrayList<Move>();
	private Color color;


	
	Piece(Color color, int type, boolean selected){
		this.color = color;
		this.type = type;
		this.selected = selected;
		this.setMoves();
	}
	
	Piece(Color color, int team, int type, boolean selected){
		this.color = color;
		this.type = type;
		this.selected = selected;
		this.team = team;
		this.setMoves();
	}

	Piece(int col, int row, Color color, int team, int type, boolean selected){
		this.color = color;
		this.type = type;
		this.selected = selected;
		this.team = team;
		this.setMoves();
	}
	
	
	
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	
	
	public int getPieceType() {
		return this.type;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public void toggleSelected() {
		if(this.selected == true) {
			this.selected = false;
		}
		else {
			this.selected = true;
		}
	}
	
	public boolean getSelected() {
		return selected;
	}
	
	public int getTeam() {
		return team;
	}
	
	
	private void setMoves() {
		Move move1 = new Move();
		Move move2 = new Move();
		
		if(this.type == RABBIT) {
			move1.F1();
			this.moves.add(move1);
		}
		else if(this.type == SNAKE) {
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
	
	
	public Piece copyPiece() {
		Piece pNew = new Piece(this.color, this.team, this.type, this.selected);
		return pNew;
	}

	
	
	public void paintComponent(Graphics g) {
		int size = this.getWidth();
		
		if(this.color == Color.BLUE) {
			Font glade = new Font(Font.SERIF, Font.BOLD, size);
			g.setColor(Color.BLACK);
			g.fillOval(size/10, size/10, size-(size/5), size-(size/5));

			if(this.selected == false) {
				g.setColor(Color.BLUE);
				Graphics2D g2;
				g2 = (Graphics2D)g; 
				g2.setStroke(new BasicStroke(5));
				g2.drawOval(size/10, size/10, size-(size/5), size-(size/5));
			}
			if(this.selected == true) {
				g.setColor(Color.CYAN);
				Graphics2D g2;
				g2 = (Graphics2D)g; 
				g2.setStroke(new BasicStroke(5));
				g2.drawOval(size/10, size/10, size-(size/5), size-(size/5));
			}
			
			switch(this.type) {
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
		if(this.color == Color.RED) {
			Font glade = new Font(Font.SERIF, Font.BOLD, size);
			g.setColor(Color.BLACK);
			g.fillOval(size/10, size/10, size-(size/5), size-(size/5));

			if(this.selected == false) {
				g.setColor(Color.RED);
				Graphics2D g2;
				g2 = (Graphics2D)g; 
				g2.setStroke(new BasicStroke(5));
				g2.drawOval(size/10, size/10, size-(size/5), size-(size/5));
			}
			if(this.selected == true) {
				g.setColor(Color.MAGENTA);
				Graphics2D g2;
				g2 = (Graphics2D)g; 
				g2.setStroke(new BasicStroke(5));
				g2.drawOval(size/10, size/10, size-(size/5), size-(size/5));
			}
			
			switch(this.type) {
			case RABBIT:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("R", (size/5), size-(size/5));
				break;
			case SNAKE:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("S", (size/5), size-(size/5));
				break;
			case BIRD:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("B", (size/5), size-(size/5));
				break;
			case GROUNDHOG:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("G", (size/5), size-(size/5));
				break;
			case TURTLE:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("T", (size/5), size-(size/5));
				break;
			}
		}
		
		
		if(this.color == Color.YELLOW) {
			Font glade = new Font(Font.SERIF, Font.BOLD, size);
			g.setColor(Color.BLACK);
			g.fillOval(size/10, size/10, size-(size/5), size-(size/5));

			if(this.selected == false) {
				g.setColor(Color.YELLOW);
				Graphics2D g2;
				g2 = (Graphics2D)g; 
				g2.setStroke(new BasicStroke(5));
				g2.drawOval(size/10, size/10, size-(size/5), size-(size/5));
			}
			if(this.selected == true) {
				g.setColor(Color.PINK);
				Graphics2D g2;
				g2 = (Graphics2D)g; 
				g2.setStroke(new BasicStroke(5));
				g2.drawOval(size/10, size/10, size-(size/5), size-(size/5));
			}
			
			switch(this.type) {
			case RABBIT:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("R", (size/5), size-(size/5));
				break;
			case SNAKE:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("S", (size/5), size-(size/5));
				break;
			case BIRD:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("B", (size/5), size-(size/5));
				break;
			case GROUNDHOG:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("G", (size/5), size-(size/5));
				break;
			case TURTLE:
				g.setColor(Color.WHITE);
				g.setFont(glade);
				g.drawString("T", (size/5), size-(size/5));
				break;
			}
		}
	}
}
