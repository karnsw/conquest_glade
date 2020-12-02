package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class Space extends JPanel{
	
	private static final boolean
		YES = true,
		NO = false,
		INBOUNDS = true,
		OUTBOUNDS = false;
	
	private int col;
	private int row;
	private Color color;				//what color is the piece (generally)
	private boolean zone;				//INBOUNDS or OUTBOUNDS
	private boolean defended;			//is this piece being defended by a team
	private int teamTerritory;			//where teams can place pieces
	private Color teamTerritoryColor;	//color for team that controls territory
	private int special;				//special ability (fire, ice, wind, none)
	private boolean mouseHere;			//is the mouse hovering over current location
	private boolean availableMove;		//what locations engaged piece can move to
	private boolean addPiece;			//is a piece being added to the board?
	
	
	Space(int col, int row){
		setCol(col);
		setRow(row);
	}
	
	Space(Color color){
		this.color = color;
	}
	
	
	Space(Color color, boolean zone, boolean defended, int teamTerritory, Color teamTerritoryColor,
			  int special, boolean mouseHere, boolean availableMove, boolean addPiece){
			
			this.color = color;
			setZone(zone);
			setDefended(defended);
			this.setTeamTerritory(teamTerritory);
			this.setTeamTerritoryColor(teamTerritoryColor);
			this.setSpecial(special);
			this.mouseHere = mouseHere;
			this.availableMove = availableMove;
			this.addPiece = addPiece;
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
	
	public boolean getZone() {
		return zone;
	}
	public void setZone(boolean zone) {
		this.zone = zone;
	}
	
	public boolean getDefended() {
		return defended;
	}
	public void setDefended(boolean defended) {
		this.defended = defended;
	}
	
	public int getTeamTerritory() {
		return teamTerritory;
	}
	public void setTeamTerritory(int teamTerritory) {
		this.teamTerritory = teamTerritory;
	}

	public Color getTeamTerritoryColor() {
		return teamTerritoryColor;
	}
	public void setTeamTerritoryColor(Color teamTerritoryColor) {
		this.teamTerritoryColor = teamTerritoryColor;
	}
	
	public int getSpecial() {
		return special;
	}
	public void setSpecial(int special) {
		this.special = special;
	}

	public boolean getMouseHere() {
		return mouseHere;
	}
	public void setMouseHere(boolean mouseHere) {
		this.mouseHere = mouseHere;
	}
	public void toggleMouseHere() {
		if(this.mouseHere == NO) {
			this.mouseHere = YES;
		}
		else {
			this.mouseHere = NO;
		}
	}

	public boolean getAvailableMove() {
		return availableMove;
	}
	public void setAvailableMove(boolean availableMove) {
		this.availableMove = availableMove;
	}
	public void toggleAvailableMove() {
		if(this.availableMove == NO) {
			this.availableMove = YES;
		}
		else {
			this.availableMove = NO;
		}
	}

	public boolean AddPiece() {
		return addPiece;
	}
	public void setAddPiece(boolean addPiece) {
		this.addPiece = addPiece;
	}
	public void toggleAddPiece() {
		if(this.addPiece == NO) {
			this.addPiece = YES;
		}
		else {
			this.addPiece = NO;
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2;
		int dim = getWidth();
		if(getHeight() < dim) {
			dim = getHeight();
		}

		
		if(this.availableMove == YES) {
			g.setColor(Color.ORANGE);

		}
		else if(this.mouseHere == YES) {
			g.setColor(Color.GREEN);
		}
		else {
			g.setColor(this.color);
		}
		g.fillRect(0, 0, dim, dim);
		
		if(defended == true) {
			g2 = (Graphics2D)g; 
			g2.setStroke(new BasicStroke(3));
			g2.setColor(teamTerritoryColor);
			g2.drawRoundRect(2, 2, dim-4, dim-4, 5, 5);
		}
		if(this.availableMove == YES) {
			g2 = (Graphics2D)g; 
			g2.setStroke(new BasicStroke(2));
			g2.setColor(Color.BLACK);
			g2.drawRect(0, 0, dim, dim);
		}
		else if(addPiece == YES) {
			g2 = (Graphics2D)g;
			g2.setStroke(new BasicStroke(2));
			g2.setColor(teamTerritoryColor);
			g2.drawRect(2, 2, dim-4, dim-4);
		}
	}

}
