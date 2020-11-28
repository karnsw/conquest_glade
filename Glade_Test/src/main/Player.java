package main;

import java.awt.Color;
import java.util.*;

public class Player {

	private static final int
	RABBIT = 0,
	SNAKE = 1,
	BIRD = 2,
	GROUNDHOG = 3,
	TURTLE = 4;
	
	private int ID;
	private int team;
	private String name;
	private Color color;
	private char territory[]  = new char[2];
	private int score;
	private List<Piece> rackPieces = new ArrayList<Piece>();
	private List<Space> defendSpaces = new ArrayList<Space>();
	private List<Piece> scoringPieces = new ArrayList<Piece>();
	

	Player(int ID){
		this.ID = ID;
	}
	Player(int id, int team, String name){
		this.ID = id;
		this.setTeam(team);
		this.setName(name);
	}
	Player(int ID, int teamColor, String name, int type){
		this.ID = ID;
		this.setTeam(teamColor);
		this.setName(name);
	
	}
	
	public int getID() {
		return ID;
	}

	
	public Color getTeamColorPropper() {
		if(team == 0) {
			return Color.BLUE;
		}
		else if(team == 1) {
			return Color.RED;
		}
		else {
			return Color.YELLOW;
		}
		
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return color;
	}
	
	public void setTeam(int team) {
		this.team = team;
	}
	public int getTeam() {
		return team;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
	
	
	public void addDefendSpace(Space defendSpace) {
		defendSpaces.add(defendSpace);
	}
	public Space getDefendSpace(int index) {
		return defendSpaces.get(index);
	}
	public void setDefendSpace(int index, Space defendSpace) {
		defendSpaces.set(index, defendSpace);
	}
	
	
	
	


	
	public int get_rack_count() {
		return this.rackPieces.size();
	}
	public void addRackPiece(Piece item) {
		this.rackPieces.add(item);
	}
	public void removeRackPiece(int type) {
		for(int i = 0; i < this.rackPieces.size(); i++) {
			if(this.rackPieces.get(i).getType() == type) {
				this.rackPieces.remove(i);
			}
		}
	}
	
	


	
	
	
	public int getRabbitCount(){
		int count = 0;
		for(int i = 0; i < this.rackPieces.size(); i++) {
			if(this.rackPieces.get(i).getType() == RABBIT) {
				count++;
			}
		}
		return count;
	}
	
	public int getSnakeCount(){
		int count = 0;
		for(int i = 0; i < this.rackPieces.size(); i++) {
			if(this.rackPieces.get(i).getType() == SNAKE) {
				count++;
			}
		}
		return count;
	}
	
	public int getBirdCount(){
		int count = 0;
		for(int i = 0; i < this.rackPieces.size(); i++) {
			if(this.rackPieces.get(i).getType() == BIRD) {
				count++;
			}
		}
		return count;
	}
	
	public int getGroundhogCount(){
		int count = 0;
		for(int i = 0; i < this.rackPieces.size(); i++) {
			if(this.rackPieces.get(i).getType() == GROUNDHOG) {
				count++;
			}
		}
		return count;
	}
	
	public int getTurtleCount(){
		int count = 0;
		for(int i = 0; i < this.rackPieces.size(); i++) {
			if(this.rackPieces.get(i).getType() == TURTLE) {
				count++;
			}
		}
		return count;
	}
	
	public int getDefendSpaceCount(){
		return defendSpaces.size();
	}
	
	
	
	public char[] getTerritory() {
		return territory;
	}
	public void setTerritory(char territory[]) {
		this.territory = territory;
	}
	
	
	
	
	public List<Piece> getScoringPieces() {
		return scoringPieces;
	}
	public void addScoringPiece(Piece piece) {
		this.scoringPieces.add(piece);
	}
	public void clearScoringPiece(Piece piece) {
		this.scoringPieces.clear();
	}
	
}
