package glade;
//Created by Brian Greene on 11/20/2020
//This version has generic movement options.
public class move {
	public piece[][] movePiece(piece piece, piece[][] b, String moveType){
		
		String moveOption = moveType;
		if (moveOption.equals("up")){
			b[piece.getYPos() - 1][piece.getXPos()] = piece;
			b[piece.getYPos()][piece.getXPos()] = null;
		}
		else if (moveOption.equals("up left")) {
			b[piece.getYPos() - 1][piece.getXPos() - 1] = piece;
			b[piece.getYPos()][piece.getXPos()] = null;
		}
		else if (moveOption.equals("up right")) {
			b[piece.getYPos() - 1][piece.getXPos() + 1] = piece;
			b[piece.getYPos()][piece.getXPos()] = null;
		}
		else if (moveOption.equals("right")) {
			b[piece.getYPos()][piece.getXPos() + 1] = piece;
			b[piece.getYPos()][piece.getXPos()] = null;
		}
		else if (moveOption.equals("left")) {
			b[piece.getYPos()][piece.getXPos() - 1] = piece;
			b[piece.getYPos()][piece.getXPos()] = null;
		}
		else if (moveOption.equals("down")) {
			b[piece.getYPos() + 1][piece.getXPos()] = piece;
			b[piece.getYPos()][piece.getXPos()] = null;
		}
		else if (moveOption.equals("down right")) {
			b[piece.getYPos() + 1][piece.getXPos() + 1] = piece;
			b[piece.getYPos()][piece.getXPos()] = null;
		}
		else if (moveOption.equals("down left")) {
			b[piece.getYPos() + 1][piece.getXPos() - 1] = piece;
			b[piece.getYPos()][piece.getXPos()] = null;
		}
		return b;
	}
}
