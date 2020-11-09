package glade;

public class move {
	public void movePiece(piece piece, piece[][] b, String moveType){
		String moveOption = moveType;
		if (moveOption.equals("move")) {
			if (piece.getMove().equals("diagonal left")) {
				if (piece.getOwner().equals("Player1")) {
					b[piece.getYPos() - 1][piece.getXPos() - 1] = piece;
				}
				else {
					b[piece.getYPos() + 1][piece.getXPos() - 1] = piece;
				}
				b[piece.getXPos()][piece.getYPos()] = null;
			}
			else if (piece.getMove().equals("left")){
				b[piece.getYPos()][piece.getXPos() - 1] = piece;
				b[piece.getXPos()][piece.getYPos()] = null;
			}
			else if (piece.getMove().equals("forward")) {
				if (piece.getOwner().equals("Player1")) {
					b[piece.getYPos() - 1][piece.getXPos()] = piece;
				}
				else {
					b[piece.getYPos() + 1][piece.getXPos()] = piece;
				}
				b[piece.getXPos()][piece.getYPos()] = null;
			}
			else if (piece.getMove().equals("backward")) {
				if (piece.getOwner().equals("Player1")) {
					b[piece.getYPos() + 1][piece.getXPos()] = piece;
				}
				else {
					b[piece.getYPos() + 1][piece.getXPos()] = piece;
				}
				b[piece.getXPos()][piece.getYPos()] = null;
			}
		}
		else {
			if (piece.getMove().equals("diagonal right")) {
				if (piece.getOwner().equals("Player1")) {
					b[piece.getYPos() - 1][piece.getXPos() + 1] = piece;
				}
				else{
					b[piece.getYPos() + 1][piece.getXPos() + 1] = piece;
				}
				b[piece.getXPos()][piece.getYPos()] = null;
			}
			else if (piece.getMove().equals("right")){
				b[piece.getYPos()][piece.getXPos() + 1] = piece;
				b[piece.getXPos()][piece.getYPos()] = null;
			}
		}
	}
}
