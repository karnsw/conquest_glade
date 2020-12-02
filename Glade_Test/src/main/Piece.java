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
	private int playerID;
	private int team;
	private char playerTerritory;
	private int type;
	private boolean selected;
	private List<Move> moves = new ArrayList<Move>();
	private Color primaryColor;
	private Color selectedColor;
	
	private int consecutiveTurnsInEnemyTerritory;

	
	Piece(Color primaryColor, int type, boolean selected){
		this.primaryColor = primaryColor;
		this.type = type;
		this.selected = selected;
		setMoves();
	}
	
	Piece(Color primaryColor, int team, int type, boolean selected){
		this.primaryColor = primaryColor;
		this.type = type;
		this.selected = selected;
		this.team = team;
		setMoves();
	}

	Piece(Color primaryColor, int playerID, char playerTerritory, int team, int type, boolean selected){
		this.primaryColor = primaryColor;
		setType(type);
		setPlayerID(playerID);
		this.selected = selected;
		this.team = team;
		setPlayerTerritory(playerTerritory);
		setMoves();
		setSelectedColor();
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
	
	
	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public char getPlayerTerritory() {
		return playerTerritory;
	}

	public void setPlayerTerritory(char playerTerritory) {
		this.playerTerritory = playerTerritory;
	}

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
		
	public int getTeam(){
		return team;
	}
	public void setTeam(int team){
		this.team = team;
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
	
	
	
	
	
	public Color getPrimaryColor() {
		return primaryColor;
	}
	public void setPrimaryColor(Color color) {
		this.primaryColor = color;
	};
	
	
	
	
	
	
	private void setMoves() {
		Move move1 = new Move();
		Move move2 = new Move();
		
		if(this.type == RABBIT) {
			move1.F1(this.playerTerritory);
			this.moves.add(move1);
		}
		else if(this.type == SNAKE) {
			move1.FR1(this.playerTerritory);
			this.moves.add(move1);
			move2.FL1(this.playerTerritory);
			this.moves.add(move2);
		}
		else if(this.type == BIRD) {
			move1.L1(this.playerTerritory);
			this.moves.add(move1);
			move2.R1(this.playerTerritory);
			this.moves.add(move2);
		}
		else if(this.type == GROUNDHOG) {
			move1.B1(this.playerTerritory);
			this.moves.add(move1);
		}
		else if(this.type == TURTLE) {
		}
	}
	
	
	public void setSelectedColor() {
		if(this.primaryColor == Color.BLUE) {
			this.selectedColor = Color.CYAN;
		}
		else if(this.primaryColor == Color.RED) {
			this.selectedColor = Color.MAGENTA;
		}
		else if(this.primaryColor == Color.YELLOW) {
			this.selectedColor = Color.PINK;
		}
	}
	
	
	
	public int getMovesListLength() {
		return this.moves.size();
	}
	
	public List<Move> getMovesList() {
		return this.moves;
	}
	public Move getMove(int index) {
		return this.moves.get(index);
	}	
	
	Piece(Piece p) {
		setPrimaryColor(p.primaryColor);
		setPlayerID(p.playerID);
		setPlayerTerritory(p.playerTerritory);
		setTeam(p.team);
		setType(p.type);
		setSelected(p.selected);
		setMoves();
	}
	
	
	
	
	public void paintComponent(Graphics g) {
		int size = this.getWidth();
		Font glade = new Font(Font.SERIF, Font.BOLD, size);
		g.setColor(Color.BLACK);
		g.fillOval(size/10, size/10, size-(size/5), size-(size/5));

		if(this.selected == false) {
			g.setColor(this.primaryColor);
		}
		else if(this.selected == true) {
			g.setColor(this.selectedColor);
		}
		Graphics2D g2;
		g2 = (Graphics2D)g; 
		g2.setStroke(new BasicStroke(5));
		g2.drawOval(size/10, size/10, size-(size/5), size-(size/5));
		
		if(this.getConsecutiveTurnsInEnemyTerritory() >= 1) {
			g2.setStroke(new BasicStroke(3));
			g2.setColor(this.primaryColor);
			g2.drawRoundRect(2, 2, size-4, size-4, 5, 5);
		}
		if(this.getConsecutiveTurnsInEnemyTerritory() >= 2) {
			g2.setStroke(new BasicStroke(3));
			g2.setColor(this.primaryColor);
			g2.drawRoundRect(6, 6, size-12, size-12, 3, 3);
		}
		if(this.getConsecutiveTurnsInEnemyTerritory() == 3) {
			g2.setStroke(new BasicStroke(3));
			g2.setColor(this.primaryColor);
			g2.drawRoundRect(10, 10, size-20, size-20, 2, 2);
		}
		
		
		g.setColor(Color.WHITE);
		g.setFont(glade);
		switch(this.type) {
		case RABBIT:
			g.drawString("R", (size/5), size-(size/5));
			break;
		case SNAKE:
			g.drawString("S", (size/5), size-(size/5));
			break;
		case BIRD:
			g.drawString("B", (size/5), size-(size/5));
			break;
		case GROUNDHOG:
			g.drawString("G", (size/5), size-(size/5));
			break;
		case TURTLE:
			g.drawString("T", (size/5), size-(size/5));
			break;
		}
	}

	public int getConsecutiveTurnsInEnemyTerritory() {
		return consecutiveTurnsInEnemyTerritory;
	}

	public void incConsecutiveTurnsInEnemyTerritory() {
		this.consecutiveTurnsInEnemyTerritory++;
	}
}
	

