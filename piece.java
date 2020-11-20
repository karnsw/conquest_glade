package glade;
//Created by Brian Greene on 11/20/2020
//This version enables the creation of generic pieces. 
//Note that this version will probably require code changes 
//for piece creation to set certain parameters (move, move2, xPos, yPos)
//as a consequence of making the pieces generic.
public class piece {
	private String owner;
	private String type;
	private String move;
	private String move2;
	private int id;
	private int xPos;
	private int yPos;
	
	public piece(String pieceOwner, String pieceType, int pieceId)
	{
		//owner, type and id will not change for a particular piece.
		owner = pieceOwner;
		type = pieceType;
		id = pieceId;
		//xPos, yPos, move and move2 must be changed with set methods.
		move = "none";
		move2 = "none";
		xPos = -1;
		yPos = -1;
	}
	
	public String getMove() {
		return move;
	}
	public void setMove(String s) {
		move = s;
	}
	
	public String getMove2() {
		return move2;
	}
	
	public void setMove2(String s) {
		move2 = s;
	}
	
	public String getOwner(){
		return owner;
	}
	
	public String getType() {
		return type;
	}
	
	public int getID() {
		return id;
	}
	
	public int getXPos() {
		return xPos;
	}
	
	public void setXPos(int x) {
		xPos = x;
	}
	
	public int getYPos() {
		return yPos;
	}
	
	public void setYPos(int y) {
		yPos = y;
	}
}
