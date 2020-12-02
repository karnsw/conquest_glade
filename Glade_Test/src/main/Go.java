package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

public class Go {

	private static final int
		RABBIT = 0,
		SNAKE = 1,
		BIRD = 2,
		GROUNDHOG = 3,
		TURTLE = 4;
	
	private static final boolean
		YES = true,
		NO = false,
		INBOUNDS = true,
		OUTBOUNDS = false,
		BOARD = true,
		RACK = false;
	
	
	public static void main(String[] args) {

		
		JFrame window = new JFrame("Glade");
		
		
		JMenuBar menuBar = new JMenuBar();
		JMenu rules = new JMenu("Rules");
		JMenu restart = new JMenu("Restart");
		//menu.getAccessibleContext().setAccessibleDescription("snakes are great");
		
		
		menuBar.add(rules);
		menuBar.add(restart);
		
		window.setJMenuBar(menuBar);
		
		GameManager miniTest = new GameManager();
		miniTest.setUpClassicGlade();

		int columns = (miniTest.getColumns() + 4);
		int rows = (miniTest.getRows() + 2);
		
		int boardBufferColsCount = 4;
		int boardBufferRowsCount = 2;
		
		int boardColsCount = miniTest.getColumns();
		int boardRowsCount = miniTest.getRows();
		
		int boardColStart = boardBufferColsCount/2;
		int boardRowStart = boardBufferRowsCount/2;
		
		int boardColEnd = boardBufferColsCount/2 + boardColsCount;
		int boardRowEnd = boardBufferRowsCount/2 + boardRowsCount;
		
	
	

	////////////	NEED WORK //// set up game dimensions	
		
		
		Dimension monitor = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
		int monitor_x = monitor.width;
		int monitor_y = monitor.height;
		int maxDim;
		if(monitor_x < monitor_y) {
			maxDim = monitor_x;
		}
		else {
			maxDim = monitor_y;
		}
		System.out.println("monitor Max- " + maxDim);
		Dimension monitor_buffer = new Dimension(maxDim-100, maxDim-100);
		Dimension monitor_buffer_with_menuBar = new Dimension(maxDim-100, maxDim-68);
		
		window.setMaximumSize(monitor_buffer_with_menuBar);
		
		int spaceSize = (monitor_buffer.width-100)/columns; //bc columns is larger than rows
		System.out.println("spaceSize- " + spaceSize);
		int minSpaceSize = 330/columns;
		System.out.println("minSpaceSize- " + minSpaceSize);
		
		
		Piece pieces[][] = new Piece[columns][rows]; 
		Space spaces[][] = new Space[columns][rows]; 
		
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < columns; col++) {
				pieces[col][row] = null;
			}
		}
		
		
		char player1Territory = miniTest.Player1().getTerritory()[0];
		System.out.println("teritory char p1- " + player1Territory);
		char player2Territory = miniTest.Player2().getTerritory()[0];
		System.out.println("teritory char p2- " + player2Territory);
				
		//initalize pieces array
		pieces[0][1] = new Piece(miniTest.Player1().getColor(), miniTest.Player1().getID(), miniTest.Player1().getTerritory()[0], miniTest.Player1().getTeam(), RABBIT, false);
		pieces[0][2] = new Piece(miniTest.Player1().getColor(), miniTest.Player1().getID(), miniTest.Player1().getTerritory()[0], miniTest.Player1().getTeam(), SNAKE, false);
		pieces[0][3] = new Piece(miniTest.Player1().getColor(), miniTest.Player1().getID(), miniTest.Player1().getTerritory()[0], miniTest.Player1().getTeam(), BIRD, false);
		pieces[0][4] = new Piece(miniTest.Player1().getColor(), miniTest.Player1().getID(), miniTest.Player1().getTerritory()[0], miniTest.Player1().getTeam(), GROUNDHOG, false);
		pieces[0][5] = new Piece(miniTest.Player1().getColor(), miniTest.Player1().getID(), miniTest.Player1().getTerritory()[0], miniTest.Player1().getTeam(), TURTLE, false);
		pieces[columns-1][1] = new Piece(miniTest.Player2().getColor(), miniTest.Player2().getID(), miniTest.Player2().getTerritory()[0], miniTest.Player2().getTeam(), RABBIT, false);
		pieces[columns-1][2] = new Piece(miniTest.Player2().getColor(), miniTest.Player2().getID(), miniTest.Player2().getTerritory()[0], miniTest.Player2().getTeam(), SNAKE, false);
		pieces[columns-1][3] = new Piece(miniTest.Player2().getColor(), miniTest.Player2().getID(), miniTest.Player2().getTerritory()[0], miniTest.Player2().getTeam(), BIRD, false);
		pieces[columns-1][4] = new Piece(miniTest.Player2().getColor(), miniTest.Player2().getID(), miniTest.Player2().getTerritory()[0], miniTest.Player2().getTeam(), GROUNDHOG, false);
		pieces[columns-1][5] = new Piece(miniTest.Player2().getColor(), miniTest.Player2().getID(), miniTest.Player2().getTerritory()[0], miniTest.Player2().getTeam(), TURTLE, false);
		
		
		
		//Initialize spaces array (color=Y,zone=Y,defend=Y,teamTerritory=N,teamTerritoryColor=N,
		//							special=N,mouseHere=Y,availableMove=Y)
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < columns; col++) {
				if(col == 0) {	//player1 rack
					spaces[col][row] = (new Space(miniTest.getRackColor(), OUTBOUNDS, NO, 0, null , 0, NO, NO, NO));
				}
				else if(col == columns-1) {  //player2 rack
					spaces[col][row] = (new Space(miniTest.getRackColor(), OUTBOUNDS, NO, 0, null, 0, NO, NO, NO));
				}
				else if(row == 0 || row == rows-1 || col == 1 || col == columns-2){
					spaces[col][row] = (new Space(miniTest.getBoarderColor(), OUTBOUNDS, NO, 0, null, 0, NO, NO, NO));
				}
				else if(col%2 == row%2) { // || (col+1)%2 == (row+1)%2) {
					spaces[col][row] = (new Space(miniTest.getBoardAColor(), INBOUNDS, NO, 0, null, 0, NO, NO, NO));
				}
				else {
					spaces[col][row] = (new Space(miniTest.getBoardBColor(), INBOUNDS, NO, 0, null, 0, NO, NO, NO));
				}			
			}
		}
		//assign team territory and color
		for(int row = boardRowStart; row < boardRowEnd; row++) {
			for(int col = boardColStart; col < boardColEnd; col++) {
				for(int i = 0; i < miniTest.getPlayers().size(); i++) {
					//if territory is not currently neutral			
					

						int tCount = (miniTest.getPlayers().get(i).getTerritory()[1]) - '0';

						if(miniTest.getPlayers().get(i).getTerritory()[0] == 'T' && (row-boardRowStart) < tCount) {
							if(spaces[col][row].getTeamTerritory() != 0) {
								spaces[col][row].setTeamTerritory(-1);					//if territory == -1, no one can drop piece there
								spaces[col][row].setTeamTerritoryColor(Color.BLACK);
							}
							else {
								spaces[col][row].setTeamTerritory(miniTest.getPlayers().get(i).getTeam());
								spaces[col][row].setTeamTerritoryColor(miniTest.getPlayers().get(i).getColor());
							}
						}
						else if(miniTest.getPlayers().get(i).getTerritory()[0] == 'B' && row >= (boardRowEnd-tCount)) {
							if(spaces[col][row].getTeamTerritory() != 0) {
								System.out.println("lets go black");
								spaces[col][row].setTeamTerritory(-1);					//if territory == -1, no one can drop piece there
								spaces[col][row].setTeamTerritoryColor(Color.BLACK);
							}
							else{
								spaces[col][row].setTeamTerritory(miniTest.getPlayers().get(i).getTeam());
								spaces[col][row].setTeamTerritoryColor(miniTest.getPlayers().get(i).getColor());
							}
						}
						else if(miniTest.getPlayers().get(i).getTerritory()[0] == 'L' && (col-boardColStart) < tCount) {
							if(spaces[col][row].getTeamTerritory() != 0) {
								spaces[col][row].setTeamTerritory(-1);					//if territory == -1, no one can drop piece there
								spaces[col][row].setTeamTerritoryColor(Color.BLACK);
							}
							else{
								spaces[col][row].setTeamTerritory(miniTest.getPlayers().get(i).getTeam());
								spaces[col][row].setTeamTerritoryColor(miniTest.getPlayers().get(i).getColor());
							}
						}
						else if(miniTest.getPlayers().get(i).getTerritory()[0] == 'R' && col >= (boardColEnd-tCount)) {
							if(spaces[col][row].getTeamTerritory() != 0) {
								spaces[col][row].setTeamTerritory(-1);					//if territory == -1, no one can drop piece there
								spaces[col][row].setTeamTerritoryColor(Color.BLACK);
							}
							else{
								spaces[col][row].setTeamTerritory(miniTest.getPlayers().get(i).getTeam());
								spaces[col][row].setTeamTerritoryColor(miniTest.getPlayers().get(i).getColor());
							}
						}
						
						
				}
			}
		}

		
		
		///NEED TO FIX/// -- initalizing spaces to defend
		
		for(int index = 0; index < miniTest.Player1().getDefendSpaceCount(); index++){
			int col = miniTest.Player1().getDefendSpace(index).getCol() + boardColStart;
			int row = miniTest.Player1().getDefendSpace(index).getRow() + boardRowStart;
			spaces[col][row].setDefended(true);
			miniTest.Player1().setDefendSpace(index, spaces[col][row]);			//unnecessary as far as i can tell
		}
		for(int index = 0; index < miniTest.Player2().getDefendSpaceCount(); index++){
			int col = miniTest.Player2().getDefendSpace(index).getCol() + boardColStart;
			int row = miniTest.Player2().getDefendSpace(index).getRow() + boardRowStart;
			spaces[col][row].setDefended(true);
			miniTest.Player2().setDefendSpace(index, spaces[col][row]);			//unnecessary as far as i can tell
		}
		
		
		
		
		
		//initalize player 1 panel
		JPanel p1panel = new JPanel();
		p1panel.setBackground(miniTest.getPanelColor());
		p1panel.setPreferredSize(new Dimension(102, spaceSize*rows)); 						//102 needs to be flexible to work on all monitors ()
		p1panel.setBorder(BorderFactory.createLineBorder(miniTest.Player1().getColor(), 4));//4 needs to be flexible
		p1panel.setLayout(new GridLayout(rows, 1));
		p1panel.setLayout(new BoxLayout(p1panel, BoxLayout.Y_AXIS));
				
		p1panel.add(new Label(miniTest.Player1().getName()));		
		RabbitLabel(miniTest.Player1(), p1panel, false);
		SnakeLabel(miniTest.Player1(), p1panel, false);
		BirdLabel(miniTest.Player1(), p1panel, false);
		GroundhogLabel(miniTest.Player1(), p1panel, false);
		TurtleLabel(miniTest.Player1(), p1panel, false);
		p1panel.add(new Label(""));
		ScoreLabel(miniTest.Player1(), p1panel, false);
		p1panel.add(new Label(""));
		
		
		//intialize player 2 panel
		JPanel p2panel = new JPanel();
		p2panel.setBackground(miniTest.getPanelColor());
		p2panel.setPreferredSize(new Dimension(102, spaceSize*rows)); 						//102 needs to be flexible
		p2panel.setBorder(BorderFactory.createLineBorder(miniTest.Player2().getColor(), 4));//4 needs to be flexible
		p2panel.setLayout(new GridLayout(rows, 1));
		p2panel.setLayout(new BoxLayout(p2panel, BoxLayout.Y_AXIS));
		
		p2panel.add(new Label(miniTest.Player2().getName()));
		RabbitLabel(miniTest.Player1(), p2panel, false);
		SnakeLabel(miniTest.Player1(), p2panel, false);
		BirdLabel(miniTest.Player1(), p2panel, false);
		GroundhogLabel(miniTest.Player1(), p2panel, false);
		TurtleLabel(miniTest.Player1(), p2panel, false);
		p2panel.add(new Label(""));
		ScoreLabel(miniTest.Player1(), p2panel, false);
		p2panel.add(new Label(""));
		
		
		
		
		 
		 
		JPanel statusPanel = new JPanel();
		JPanel messagePanel = new JPanel();
		
		messagePanel.setPreferredSize(new Dimension(monitor_buffer.width, 100));
		
		StatusLabel(statusPanel, miniTest.getTotalTurns(), miniTest.currentPlayer());
		
		List<String> messages = new ArrayList<String>();	
		MessageLabel(messagePanel, messages, "Let's Play Glade!", null);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		JPanel game = new JPanel();
		game.setLayout(new BorderLayout());
		game.add(statusPanel, BorderLayout.NORTH);
		game.add(messagePanel, BorderLayout.SOUTH);
		game.add(p1panel, BorderLayout.WEST);
		game.add(p2panel, BorderLayout.EAST);
		

		
		//window.setPreferredSize(new Dimension(((minSpaceSize)*(columns)), (minSpaceSize*(rows))));
		//window.setMinimumSize(new Dimension(((minSpaceSize)*(columns)), (minSpaceSize*(rows))));
		
		JPanel board = new JPanel();
		board.setPreferredSize(new Dimension((spaceSize*columns), (spaceSize*rows)));
		//board.setPreferredSize(new Dimension((spaceSize*rows), (spaceSize*columns)));
		board.setMinimumSize(new Dimension((minSpaceSize*(columns)), (minSpaceSize*(rows))));
		
		board.setMaximumSize(monitor_buffer);
		
		
		System.out.println("minSize- " + board.getMinimumSize());
		System.out.println("Size- " + board.getSize());
		System.out.println();
		game.add(board, BorderLayout.CENTER);
		board.setLayout(new GridLayout(rows, columns));
		
		
		//initalize game image
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < columns; col++) {
				JPanel stack = new JPanel();
				stack.setLayout(new OverlayLayout(stack));
				if(pieces[col][row] != null) {
					stack.add(pieces[col][row]);
					stack.add(spaces[col][row]);
					board.add(stack, row*columns + col);
				}
				else {
					stack.add(spaces[col][row]);
					board.add(stack, row*columns + col);			
				}
			}
		}
		
			
		
	
		
		

		
		
		board.addMouseListener(new MouseListener() {
			
			private boolean selected = false;
			private boolean selectedPieceZone;
			boolean moveFound = false;
			
			private int X2 = -1;
			private int Y2 = -1;
			
			private int PX1;
			private int PY1;
			private int RX1;
			private int RY1;

			
			@Override
			public void mouseClicked(MouseEvent evt) {		
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int mouseX = arg0.getX();
				int mouseY = arg0.getY();
				PX1 = mouseX/spaceSize;
				PY1 = mouseY/spaceSize;
				System.out.println("PX1- " + PX1 + "       PY1- " + PY1);
				
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int mouseX = arg0.getX();
				int mouseY = arg0.getY();
				RX1 = mouseX/spaceSize;
				RY1 = mouseY/spaceSize;
				System.out.println("RX1- " + RX1 + "       RY1- " + RY1);
				
				if(PX1 != RX1 || PY1 != RY1) {
					String newMessage = "To select a piece, your cursor must remain in the same space "
							+ "when you press and release. Try again";
					MessageLabel(messagePanel, messages, newMessage, miniTest.currentPlayer());
					return;
				}
				
				else {
					int X1 = mouseX/spaceSize;
					int Y1 = mouseY/spaceSize;

					
					if(selected == true) {
						if(X1 == X2 && Y1 == Y2) {										//the player want to deselect the current piece
							if(moveFound == true) {										//the current piece has available moves to remove
								int newX, newY;
								for(int index = 0; index < pieces[X1][Y1].getMovesListLength(); index++) {
									newX = X1 - pieces[X1][Y1].getMove(index).getLeft() + pieces[X1][Y1].getMove(index).getRight();
									newY = Y1 - pieces[X1][Y1].getMove(index).getUp() + pieces[X1][Y1].getMove(index).getDown();
									if(spaces[newX][newY].getZone() == INBOUNDS && pieces[newX][newY] == null ||
										//or possible space is inbounds, occupied and of the other team 	
										spaces[newX][newY].getZone() == INBOUNDS && pieces[newX][newY] != null && pieces[newX][newY].getTeam() != miniTest.currentPlayer().getTeam()) {
										
										moveFound = false; 
										spaces[newX][newY].setAvailableMove(false);									
									}
								}
							}
							//player was planning to add piece. the spaces that showed valid placement need to be reverted to neutral
							else {						
								for(int row = boardRowStart; row < boardRowEnd; row++) {
									for(int col = boardColStart; col < boardColEnd; col++) {
										if(spaces[col][row].getTeamTerritory() == miniTest.currentPlayer().getTeam()) {
											spaces[col][row].setAddPiece(false);				//show spaces piece can be placed
										}
									}
								}
							}	
							pieces[X1][Y1].setSelected(false);
							selected = false;
							X2 = -1;
							Y2 = -1;
							board.revalidate();
							board.repaint();
							String newMessage = "Your prior selection has been deselcted. Choose a new piece.";
							MessageLabel(messagePanel, messages, newMessage, miniTest.currentPlayer());
							System.out.println("done");
							return;
						}
						else if(selectedPieceZone == BOARD) {
							if(moveFound == true) {	
								if(spaces[X1][Y1].getAvailableMove() == false) {
									String newMessage = "You must select one of the valid moves (highlighted) "
											+ "or deselect your current piece to start over. Try again.";
									MessageLabel(messagePanel, messages, newMessage, miniTest.currentPlayer());
									return;
								}
								else if(spaces[X1][Y1].getAvailableMove() == true) {
									if(pieces[X1][Y1] != null){ 						//space clicked has opponents piece
										
										int scoreTemp = miniTest.currentPlayer().getScore();
										scoreTemp = scoreTemp + 10;
										miniTest.currentPlayer().setScore(scoreTemp);		//fix for different piece type
										
										String pieceName = "";
										switch(pieces[X2][Y2].getType()) {
										case RABBIT:
											pieceName = "Rabbit";
											break;
										case SNAKE:
											pieceName = "Snake";
											break;
										case BIRD:
											pieceName = "Bird";
											break;
										case GROUNDHOG:
											pieceName = "Groundhog";
											break;
										};
										String opponentPieceName = "";
										switch(pieces[X1][Y1].getType()) {
										case RABBIT:
											opponentPieceName = "Rabbit";
											break;
										case SNAKE:
											opponentPieceName = "Snake";
											break;
										case BIRD:
											opponentPieceName = "Bird";
											break;
										case GROUNDHOG:
											opponentPieceName = "Groundhog";
											break;
										};
										
										System.out.println("name- " + miniTest.currentPlayer().getName());
										System.out.println("score " + miniTest.currentPlayer().getScore());
										
										ScoreLabel(miniTest.currentPlayer(), passPlayerPanel(miniTest.currentPlayer(), p1panel, p2panel), true);
										StatusLabel(statusPanel, miniTest.getTotalTurns(), miniTest.currentPlayer());
										
										StringBuilder newMessage = new StringBuilder("Your ").append(pieceName).append(" has overtaken your opponents ")
																					.append(opponentPieceName).append(". Score +10"); ///fix for all scores
										
										MessageLabel(messagePanel, messages, newMessage.toString(), miniTest.currentPlayer());
					
										
										pieces[X2][Y2].setSelected(false);
										pieces[X1][Y1] = pieces[X2][Y2];
										pieces[X2][Y2] = null;
										moveFound = false;
										selected = false;

										for(int row = boardRowStart; row < boardRowEnd; row++) {
											for(int col = boardColStart; col < boardColEnd; col++) {
												if(spaces[col][row].getAvailableMove() == true) {
													spaces[col][row].setAvailableMove(false);
												}
											}
										}
										consecutiveTurns(pieces, spaces, miniTest.currentPlayer(), boardRowStart, boardRowEnd, 
												boardColStart, boardColEnd, board, columns, rows);
										//////////////////////////////////
										
										JPanel stack = new JPanel();
										stack.setLayout(new OverlayLayout(stack));
										//stack.removeAll();
										stack.add(pieces[X1][Y1]);
										stack.add(spaces[X1][Y1]);
										
										board.remove(Y1*columns + X1);
										board.add(stack, Y1*columns + X1);
										
										board.revalidate();
										board.repaint();
										
										miniTest.incrementTurn();
										MessageLabel(messagePanel, messages, miniTest.currentPlayer().getName() + " your turn!", null);
										
										
										return;
									}
									//space clicked is not occupied by a piece (empty)
									else if(pieces[X1][Y1] == null) {									
										String pieceName = "";
										switch(pieces[X2][Y2].getType()) {
										case RABBIT:
											pieceName = "Rabbit";
											break;
										case SNAKE:
											pieceName = "Snake";
											break;
										case BIRD:
											pieceName = "Bird";
											break;
										case GROUNDHOG:
											pieceName = "Groundhog";
											break;
										};
										StringBuilder newMessage = new StringBuilder("You have moved a ").append(pieceName).append(".");
										MessageLabel(messagePanel, messages, newMessage.toString(), miniTest.currentPlayer());
										
										pieces[X2][Y2].setSelected(false);
										pieces[X1][Y1] = pieces[X2][Y2];
										pieces[X2][Y2] = null;
										moveFound = false;
										selected = false;
										
										for(int row = boardRowStart; row < boardRowEnd; row++) {
											for(int col = boardColStart; col < boardColEnd; col++) {
												if(spaces[col][row].getAvailableMove() == true) {
													spaces[col][row].setAvailableMove(false);
												}
											}
										}
										
										
										consecutiveTurns(pieces, spaces, miniTest.currentPlayer(), boardRowStart, boardRowEnd, 
												boardColStart, boardColEnd, board, columns, rows);
										////////////////////////////////////////
										
										
										JPanel stack = new JPanel();
										stack.setLayout(new OverlayLayout(stack));
										//stack.removeAll();
										stack.add(pieces[X1][Y1]);
										stack.add(spaces[X1][Y1]);

										board.remove(Y1*columns + X1);
										board.add(stack, Y1*columns + X1);
										
										board.revalidate();
										board.repaint();
										
										miniTest.incrementTurn();
										MessageLabel(messagePanel, messages, miniTest.currentPlayer().getName() + " your turn!", null);
										StatusLabel(statusPanel, miniTest.getTotalTurns(), miniTest.currentPlayer());
										return;
									}	
								}
							}	
						}
						//piece selected is from player rack
						else if(selectedPieceZone == RACK) {		
							//location selected is already occupied piece
							if(pieces[X1][Y1] != null) {					
								String newMessage = "Your selection is already occupied by a piece. You must select one of the valid moves (outlined) "
										+ "or deselect your current piece to start over. Try again.";
								MessageLabel(messagePanel, messages, newMessage, miniTest.currentPlayer());
								System.out.println("done");
								return;
							}
							//location selected is not valid territory for current piece
							else if(spaces[X1][Y1].getTeamTerritory() != miniTest.currentPlayer().getTeam()) { 	
								String newMessage = "Your selection is outside of your teritory. You must select one of the valid moves (outlined) "
										+ "or deselect your current piece to start over. Try again.";
								MessageLabel(messagePanel, messages, newMessage, miniTest.currentPlayer());
								System.out.println("done");
								return;
							}
							//copy piece type from rack to board, remove one from rack
							else {
								pieces[X2][Y2].setSelected(false);
								pieces[X1][Y1] = new Piece(pieces[X2][Y2]);
								selected = false;
																	
								String pieceName = "";
								miniTest.currentPlayer().removeRackPiece(pieces[X1][Y1].getType());
								switch(pieces[X1][Y1].getType()) {
								case RABBIT:
									pieceName = "Rabbit";
									RabbitLabel(miniTest.currentPlayer(), passPlayerPanel(miniTest.currentPlayer(), p1panel, p2panel), true);
									break;
								case SNAKE:
									pieceName = "Snake";
									SnakeLabel(miniTest.currentPlayer(), passPlayerPanel(miniTest.currentPlayer(), p1panel, p2panel), true);
									break;
								case BIRD:
									pieceName = "Bird";
									BirdLabel(miniTest.currentPlayer(), passPlayerPanel(miniTest.currentPlayer(), p1panel, p2panel), true);
									break;
								case GROUNDHOG:
									pieceName = "Groundhog";
									GroundhogLabel(miniTest.currentPlayer(), passPlayerPanel(miniTest.currentPlayer(), p1panel, p2panel), true);
									break;
								case TURTLE:
									pieceName = "Turtle";
									TurtleLabel(miniTest.currentPlayer(), passPlayerPanel(miniTest.currentPlayer(), p1panel, p2panel), true);
									break;
								};
								
								for(int row = boardRowStart; row < boardRowEnd; row++) {
									for(int col = boardColStart; col < boardColEnd; col++) {
										if(spaces[col][row].getTeamTerritory() == miniTest.currentPlayer().getTeam()) {
											spaces[col][row].setAddPiece(false);				//hide spaces piece could be placed (b/c it is now) 
										}
									}
								}
								
								consecutiveTurns(pieces, spaces, miniTest.currentPlayer(), boardRowStart, boardRowEnd, 
										boardColStart, boardColEnd, board, columns, rows);
								//////////////////////////////
								
								StringBuilder newMessage = new StringBuilder("You have added a ").append(pieceName).append(" to the board.");	
								MessageLabel(messagePanel, messages, newMessage.toString(), miniTest.currentPlayer());
										
								JPanel stack = new JPanel();
								stack.setLayout(new OverlayLayout(stack));
								//stack.removeAll();
								stack.add(pieces[X1][Y1]);
								stack.add(spaces[X1][Y1]);
								
								board.remove(Y1*columns + X1);
								board.add(stack, Y1*columns + X1);
								
								board.revalidate();
								board.repaint();
															
								miniTest.incrementTurn();
								
								MessageLabel(messagePanel, messages, miniTest.currentPlayer().getName() + " your turn!", null);
								StatusLabel(statusPanel, miniTest.getTotalTurns(), miniTest.currentPlayer());
								
								
								return;		
							}
						}
					}
					
					else if(selected == false) {															//no piece is currently selected
						if(pieces[X1][Y1] == null) {												//location clicked does not have a piece
							String newMessage = "You must select a piece. Try again.";
							MessageLabel(messagePanel, messages, newMessage, miniTest.currentPlayer());
							return;
						}
						else if(pieces[X1][Y1] != null){																		//location clicked does have a piece
							if(pieces[X1][Y1].getPlayerID() != miniTest.currentPlayer().getID()) {	//piece does not belong to player
								String newMessage = "You must select your own piece. Try again.";
								MessageLabel(messagePanel, messages, newMessage, miniTest.currentPlayer());
								return;
							}
							else {																	//piece does belong to current player
								if(spaces[X1][Y1].getZone() == OUTBOUNDS) {							//piece is on current players rack
									selectedPieceZone = RACK;
									if(pieces[X1][Y1].getType() == RABBIT) {						
										if(miniTest.currentPlayer().getRabbitCount() == 0){			//player does not have any rabbits in reserve
											String newMessage = "You do not have any Rabbits in reserve. Try again.";
											MessageLabel(messagePanel, messages, newMessage, miniTest.currentPlayer());
											return;
										}
										else {
											pieces[X1][Y1].setSelected(true);						//player does have rabbits in reserve
											selected = true;
											X2 = X1;
											Y2 = Y1;
											for(int row = boardRowStart; row < boardRowEnd; row++) {
												for(int col = boardColStart; col < boardColEnd; col++) {
													if(pieces[col][row] == null) {					//location must be empty 
														if(spaces[col][row].getTeamTerritory() == miniTest.currentPlayer().getTeam()) {
															spaces[col][row].setAddPiece(true);				//show spaces piece can be placed
														}
													}
												}
											}
											board.revalidate();
											board.repaint();
											return;
										}
									}
									else if(pieces[X1][Y1].getType() == SNAKE) {						
										if(miniTest.currentPlayer().getSnakeCount() <= 0){			//player does not have any snakes in reserve
											String newMessage = "You do not have any Snakes in reserve. Try again.";
											MessageLabel(messagePanel, messages, newMessage, miniTest.currentPlayer());
											return;
										}
										else {
											pieces[X1][Y1].setSelected(true);						//player does have snakes in reserve
											selected = true;
											X2 = X1;
											Y2 = Y1;
											for(int row = boardRowStart; row < boardRowEnd; row++) {
												for(int col = boardColStart; col < boardColEnd; col++) {
													if(pieces[col][row] == null) {					//location must be empty 
														if(spaces[col][row].getTeamTerritory() == miniTest.currentPlayer().getTeam()) {
															spaces[col][row].setAddPiece(true);				//show spaces piece can be placed
														}
													}
												}
											}
											board.revalidate();
											board.repaint();
											return;
										}
									}
									else if(pieces[X1][Y1].getType() == BIRD) {						
										if(miniTest.currentPlayer().getBirdCount() <= 0){			//player does not have any birds in reserve
											String newMessage = "You do not have any Birds in reserve. Try again.";
											MessageLabel(messagePanel, messages, newMessage, miniTest.currentPlayer());
											return;
										}
										else {
											pieces[X1][Y1].setSelected(true);						//player does have birds in reserve
											selected = true;
											X2 = X1;
											Y2 = Y1;
											for(int row = boardRowStart; row < boardRowEnd; row++) {
												for(int col = boardColStart; col < boardColEnd; col++) {
													if(pieces[col][row] == null) {					//location must be empty 
														if(spaces[col][row].getTeamTerritory() == miniTest.currentPlayer().getTeam()) {
															spaces[col][row].setAddPiece(true);				//show spaces piece can be placed
														}
													}
												}
											}
											board.revalidate();
											board.repaint();
											return;
										}
									}
									else if(pieces[X1][Y1].getType() == GROUNDHOG) {						
										if(miniTest.currentPlayer().getGroundhogCount() <= 0){		//player does not have any groundhogs in reserve
											String newMessage = "You do not have any Groundhogs in reserve. Try again.";
											MessageLabel(messagePanel, messages, newMessage, miniTest.currentPlayer());
											return;
										}
										else {
											pieces[X1][Y1].setSelected(true);						//player does have groundhogs in reserve
											selected = true;
											X2 = X1;
											Y2 = Y1;
											for(int row = boardRowStart; row < boardRowEnd; row++) {
												for(int col = boardColStart; col < boardColEnd; col++) {
													if(pieces[col][row] == null) {					//location must be empty 
														if(spaces[col][row].getTeamTerritory() == miniTest.currentPlayer().getTeam()) {
															spaces[col][row].setAddPiece(true);				//show spaces piece can be placed
														}
													}
												}
											}
											board.revalidate();
											board.repaint();
											return;
										}
									}
									else if(pieces[X1][Y1].getType() == TURTLE) {						
										if(miniTest.currentPlayer().getTurtleCount() <= 0){			//player does not have any turtles in reserve
											String newMessage = "You do not have any Turtles in reserve. Try again.";
											MessageLabel(messagePanel, messages, newMessage, miniTest.currentPlayer());
											System.out.println("done");
											return;
										}
										else {
											pieces[X1][Y1].setSelected(true);						//player does have turtles in reserve
											selected = true;
											X2 = X1;
											Y2 = Y1;
											for(int row = boardRowStart; row < boardRowEnd; row++) {
												for(int col = boardColStart; col < boardColEnd; col++) {
													if(pieces[col][row] == null) {					//location must be empty 
														if(spaces[col][row].getTeamTerritory() == miniTest.currentPlayer().getTeam()) {
															spaces[col][row].setAddPiece(true);				//show spaces piece can be placed
														}
													}
												}
											}
											board.revalidate();
											board.repaint();
											return;
										}
									}
								}
								else if(spaces[X1][Y1].getZone() == INBOUNDS) {						//selected location is inbounds		
									selectedPieceZone = BOARD;
									
									//FIND AVAILABLE MOVES
									int newX, newY;
									for(int index = 0; index < pieces[X1][Y1].getMovesListLength(); index++) {
										newX = X1 - pieces[X1][Y1].getMove(index).getLeft() + pieces[X1][Y1].getMove(index).getRight();
										newY = Y1 - pieces[X1][Y1].getMove(index).getUp() + pieces[X1][Y1].getMove(index).getDown();
										//possible space is inbounds and empty
										if(spaces[newX][newY].getZone() == INBOUNDS && pieces[newX][newY] == null ||
											//or possible space is inbounds, occupied and of the other team 	
											spaces[newX][newY].getZone() == INBOUNDS && pieces[newX][newY] != null && 
											pieces[newX][newY].getTeam() != miniTest.currentPlayer().getTeam() &&
											pieces[newX][newY].getType() != TURTLE){
												
												moveFound = true; 
												spaces[newX][newY].setAvailableMove(true);
										}
									}
									if(moveFound == true) {
										pieces[X1][Y1].setSelected(true);
										selected = true;
										X2 = X1;
										Y2 = Y1;
										board.revalidate();
										board.repaint();
										System.out.println("done - SELECTED + MOVE");
										return;
									}
									else if(pieces[X1][Y1].getType() == TURTLE) {
										String newMessage = "Turtles can not be moved. Try again.";
										MessageLabel(messagePanel, messages, newMessage, miniTest.currentPlayer());
										System.out.println("done - SELECTED + NO MOVE");
										return;
									}
									else {
										String newMessage = "You have chosen a piece with no available moves. Try again.";
										MessageLabel(messagePanel, messages, newMessage, miniTest.currentPlayer());
										System.out.println("done - SELECTED + NO MOVE");
										return;
									}
								}
							}
						}
					}	
				}
			}
		});
		
		
		
		
		board.addMouseMotionListener(new MouseMotionListener(){
			
			private int prevX;
			private int prevY;
			private boolean initalize = true;

			public void mouseMoved(MouseEvent evt) {
				int mouse_x = evt.getX();
				int mouse_y = evt.getY();
				int mouseXcoordinate = mouse_x / spaceSize;
				int mouseYcoordinate = mouse_y / spaceSize;
				if(initalize == true) {
					spaces[prevX][prevY].toggleMouseHere();
					initalize = false;
				}
				if(prevX != mouseXcoordinate || prevY != mouseYcoordinate) {
					for(int col = 0; col < columns; col++) {	
						for(int row = 0; row < rows; row++) {
							if(mouseXcoordinate == col && mouseYcoordinate == row) {
								spaces[col][row].toggleMouseHere();
								spaces[prevX][prevY].toggleMouseHere();
							}
							board.revalidate();
							board.repaint();
						}
					}
					prevX = mouseXcoordinate;
					prevY = mouseYcoordinate;
				}
			}
			@Override
			public void mouseDragged(MouseEvent evt) {
				// TODO Auto-generated method stub
			}
		});
		
		
		
		
		window.setContentPane(game);
		//window.setContentPane(board);
		window.pack();
		window.setLocation(300, 50);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	/*
		
		//only works to expand the board currently  +++++ ADD UPDATE TO SPACE SIZE SO MOUSE EVENTS WILL CORELEATE
		window.addComponentListener(new ComponentAdapter(){ 
			public void componentResized(ComponentEvent evt) {
				
				int windowDim;
				//int menuBarHeight = window.getHeight() - window.getWidth();

				if(board.getWidth() != board.getHeight()) {
					if(board.getWidth() > board.getHeight()) {
						if(board.getWidth() < monitor_buffer.width) {
							windowDim = board.getWidth();
						}
						else {
							windowDim = monitor_buffer.width;
						}
						board.setPreferredSize(new Dimension(windowDim, windowDim));
						board.setMinimumSize(new Dimension(windowDim, windowDim));
						board.revalidate();
						window.revalidate();
						window.pack();
					}
					else {
						if(board.getHeight() < monitor_buffer.height) {
							windowDim = board.getHeight();
						}
						else {
							windowDim = monitor_buffer.height;
						}
						board.setPreferredSize(new Dimension(windowDim, windowDim));
						board.setMinimumSize(new Dimension(windowDim, windowDim));
						board.revalidate();
						window.revalidate();
						window.pack();
					}

				}
			}
		});
		
	*/
	
	}

	public static void consecutiveTurns(Piece[][] pieces, Space[][] spaces, Player p,
										int boardRowStart, int boardRowEnd, int boardColStart, int boardColEnd,
										JPanel board, int columns, int rows){
		
		for(int row = boardRowStart; row < boardRowEnd; row++) {
			for(int col = boardColStart; col < boardColEnd; col++) {
				if(pieces[col][row] != null && pieces[col][row].getTeam() != p.getTeam()) {  //piece belongs to other team 
					if(pieces[col][row].getTeam() != spaces[col][row].getTeamTerritory() && spaces[col][row].getDefended() == true) {	//piece is in enemy territory
						pieces[col][row].incConsecutiveTurnsInEnemyTerritory();				//increments count piece has been in enemy territory
					}
					if(pieces[col][row].getConsecutiveTurnsInEnemyTerritory() >= 3) {
						
						/*
						for(int row2 = boardRowStart; row2 < boardRowEnd; row2++) {
							for(int col2 = boardColStart; col2 < boardColEnd; col2++) {
								spaces[col2][row2] = null;
								spaces[col2][row2] = new Space(Color.WHITE);
								if(pieces[col2][row2] != null && pieces[col2][row2].getTeam() == p.getTeam()) {
									pieces[col2][row2] = null;
								}
								
								board.remove(row2*columns + col2);
								JPanel stack = new JPanel();
								stack.setLayout(new OverlayLayout(stack));
								if(pieces[col2][row2] != null) {
									stack.add(pieces[col2][row2]);
									stack.add(spaces[col2][row2]);
									board.add(stack, row2*columns + col2);
								}
								else {
									stack.add(spaces[col2][row2]);
									board.add(stack, row2*columns + col2);			
								}
							}
						}
						*/
						board.removeAll();
						int x = board.getX();
						int y = board.getY();
						int width = board.getWidth();
						int height = board.getHeight();

						
						//board.setLayout();
						board.revalidate();
						
						System.out.println("height- " + board.getWidth());
						System.out.println("width- " + board.getWidth());
						System.out.println("X- " + board.getX());
						System.out.println("Y- " + board.getY());
						board.setBounds(x, y, width, height);
						JLabel winner = new JLabel(String.format("<html>Player: %s<br>YOU LOSE!</html>", p.getName()), JLabel.CENTER);
						winner.setForeground(p.getColor());
						winner.setBounds(x, y, width, height);
						Font glade = new Font(Font.SERIF, Font.BOLD, 20);
						winner.setFont(glade);
						board.add(winner);
						board.revalidate();
						board.repaint();
						
						return;
					}
				}
			}
		}
	}
	
	
	
	

	//only good for 2 players
	public static JPanel passPlayerPanel(Player p, JPanel p1, JPanel p2) {
		if(p.getTeam() == 1) {
			return p1;
		}
		else {
			return p2;
		}
	}
	
	
	
	//helper methods for maintaining player info panels
	public static void RabbitLabel(Player player, JPanel playerPanel, boolean remove) {
		if(remove == true) {
			playerPanel.remove(1);
		}
		JLabel Rabbits = new JLabel(String.format("<html>Rabbits:<br>%d</html>", player.getRabbitCount()));
		Rabbits.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerPanel.add(Rabbits, 1);
		playerPanel.revalidate();
		playerPanel.repaint();
	}
	
	
	public static void SnakeLabel(Player player, JPanel playerPanel, boolean remove) {
		if(remove == true) {
			playerPanel.remove(2);
		}
		JLabel Snakes = new JLabel(String.format("<html>Snakes:<br>%d</html>", player.getSnakeCount()));
		Snakes.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerPanel.add(Snakes, 2);
		playerPanel.revalidate();
		playerPanel.repaint();
	}

	public static void BirdLabel(Player player, JPanel playerPanel, boolean remove) {
		if(remove == true) {
			playerPanel.remove(3);
		}
		JLabel Birds = new JLabel(String.format("<html>Birds:<br>%d</html>", player.getBirdCount()));
		Birds.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerPanel.add(Birds, 3);
		playerPanel.revalidate();
		playerPanel.repaint();
	}
	
	public static void GroundhogLabel(Player player, JPanel playerPanel, boolean remove) {
		if(remove == true) {
			playerPanel.remove(4);
		}
		JLabel Groundhogs = new JLabel(String.format("<html>Groundhogs:<br>%d</html>", player.getGroundhogCount()));
		Groundhogs.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerPanel.add(Groundhogs, 4);
		playerPanel.revalidate();
		playerPanel.repaint();
	}
	
	public static void TurtleLabel(Player player, JPanel playerPanel, boolean remove) {
		if(remove == true) {
			playerPanel.remove(5);
		}
		JLabel Turtles = new JLabel(String.format("<html>Turtles:<br>%d</html>", player.getTurtleCount()));
		Turtles.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerPanel.add(Turtles, 5);
		playerPanel.revalidate();
		playerPanel.repaint();
	}
	
	public static void ScoreLabel(Player player, JPanel playerPanel, boolean remove) {
		if(remove == true) {
			playerPanel.remove(7);
		}
		JLabel Score = new JLabel(String.format("<html>Score:<br>%d</html>", player.getScore()));
		Score.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerPanel.add(Score, 7);
		playerPanel.revalidate();
		playerPanel.repaint();
	}
	
	
	
	
	public static void StatusLabel(JPanel statusPanel, int ttlTurns, Player player){
		statusPanel.removeAll();
		JLabel activePlayer = new JLabel(String.format("<html>Total turns:  %d<br>Active Player:  %s</html>", ttlTurns, player.getName()));
		activePlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
		statusPanel.add(activePlayer);
		statusPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		statusPanel.revalidate();
		statusPanel.repaint();
	}
	
	public static void MessageLabel(JPanel messagePanel, List<String> messages, String newMessage, Player player){
		messagePanel.removeAll();
		
		if(player != null) {
			StringBuilder newMsgBldr = new StringBuilder();
			newMsgBldr.append(player.getName() + ":  ");
			newMsgBldr.append(newMessage);
			newMessage = newMsgBldr.toString();
		}
		
		if(messages.size() == 5) {
			messages.remove(messages.size()-1);
		}
		
		List<String> newMessages = new ArrayList<String>();
		newMessages.add(newMessage);
		messages.forEach(message -> newMessages.add(message));
		messages.clear();
		newMessages.forEach(message -> messages.add(message));
		
		StringBuilder labelString = (new StringBuilder()).append("<html>");
		messages.forEach(message -> labelString.append(message).append("<br>"));
		labelString.append("</html>");
		
		JLabel finalMessage = (new JLabel(String.format(labelString.toString())));
		messagePanel.add(finalMessage);	
		messagePanel.revalidate();
		messagePanel.repaint();
	}
}



