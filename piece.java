package glade;

public class piece {
	private String owner;
	private String type;
	private String move;
	private String move2;
	private int id;
	private int xPos;
	private int yPos;
	
	public piece(String pieceOwner, String pieceType, int pieceId, int startx, int starty)
	{
		owner = pieceOwner;
		type = pieceType;
		move = getMove();
		move2 = getMove2();
		id = pieceId;
		xPos = startx;
		yPos = starty;
	}
	
	public String getMove() {
		String pieceName = type.toLowerCase();
		if (pieceName.equals("rabbit")) {
			pieceName = "forward";
		}
		else if (pieceName.equals("snake")) {
			pieceName = "diagonal left";
		}
		else if (pieceName.equals("bird")) {
			pieceName = "sideways left";
		}
		else if (pieceName.equals("groundhog")) {
			pieceName = "backward";
		}
		else {
			pieceName = "none";
		}
		return pieceName;
	}
	
	public String getMove2() {
		String pieceName = type.toLowerCase();
		if (pieceName.contentEquals("snake")) {
			pieceName = "diagonal right";
		}
		else if (pieceName.contentEquals("bird")) {
			pieceName = "sideways right";
		}
		else{
			pieceName = "none";
		}
		return pieceName;
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
