package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

public class Go {

	
	public static void main(String[] args) {

		
		JFrame window = new JFrame("Glade");


		
		int columns = (8 + 4);
		int rows = (8 + 2);

		
		int mouseCoordinate[][] = new int[columns][rows];
		int pieceCoordinate[][] = new int[columns][rows];
		
	
		
	////////////
		Piece pieces[][] = new Piece[columns][rows]; 
		Square spaces[][] = new Square[columns][rows]; 
	////////////	
		
		
		
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
		
		
		
		
		Player player1 = new Player(0);
		String s1 = (String)JOptionPane.showInputDialog("Player 1, please enter your name.","???");
		player1.set_name(s1);
		
		Object[] choices = {"blue", "red", "purple"};
		String c1 = (String)JOptionPane.showInputDialog(null, s1 +  " select your team color.", "team color", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
		if(c1 == "blue") {
			player1.set_teamColor(0); 
		}
		else if(c1 == "red") {
			player1.set_teamColor(1);
		}
		else {
			player1.set_teamColor(2);
		}

		System.out.println("p1 team color- " + player1.get_teamColor());
		
//		String s1 = (String)JOptionPane.showInputDialog(s1 + " select your team color.","???");
		player1.addRackPiece(new Piece(0, 0, false));
		player1.addRackPiece(new Piece(0, 0, false));
		player1.addRackPiece(new Piece(0, 1, false));
		player1.addRackPiece(new Piece(0, 1, false));
		player1.addRackPiece(new Piece(0, 2, false));
		player1.addRackPiece(new Piece(0, 2, false));
		player1.addRackPiece(new Piece(0, 3, false));
		player1.addRackPiece(new Piece(0, 3, false));
		player1.addRackPiece(new Piece(0, 4, false));
		/*
		player1.addRackPiece(new Piece(0, 0, false, spaceSize));
		player1.addRackPiece(new Piece(0, 0, false, spaceSize));
		player1.addRackPiece(new Piece(0, 1, false, spaceSize));
		player1.addRackPiece(new Piece(0, 1, false, spaceSize));
		player1.addRackPiece(new Piece(0, 2, false, spaceSize));
		player1.addRackPiece(new Piece(0, 2, false, spaceSize));
		player1.addRackPiece(new Piece(0, 3, false, spaceSize));
		player1.addRackPiece(new Piece(0, 3, false, spaceSize));
		player1.addRackPiece(new Piece(0, 4, false, spaceSize));
		*/
		
		
		Player player2 = new Player(1);
		String s2 = (String)JOptionPane.showInputDialog("Player 2, please enter your name.","???");
		player2.set_name(s2);
		player2.addRackPiece(new Piece(1, 0, false));
		player2.addRackPiece(new Piece(1, 0, false));
		player2.addRackPiece(new Piece(1, 1, false));
		player2.addRackPiece(new Piece(1, 1, false));
		player2.addRackPiece(new Piece(1, 2, false));
		player2.addRackPiece(new Piece(1, 2, false));
		player2.addRackPiece(new Piece(1, 3, false));
		player2.addRackPiece(new Piece(1, 3, false));
		player2.addRackPiece(new Piece(1, 4, false));
		
		/*
		player2.addRackPiece(new Piece(1, 0, false, spaceSize));
		player2.addRackPiece(new Piece(1, 0, false, spaceSize));
		player2.addRackPiece(new Piece(1, 1, false, spaceSize));
		player2.addRackPiece(new Piece(1, 1, false, spaceSize));
		player2.addRackPiece(new Piece(1, 2, false, spaceSize));
		player2.addRackPiece(new Piece(1, 2, false, spaceSize));
		player2.addRackPiece(new Piece(1, 3, false, spaceSize));
		player2.addRackPiece(new Piece(1, 3, false, spaceSize));
		player2.addRackPiece(new Piece(1, 4, false, spaceSize));
		 */
		
		
		pieces[0][1] = new Piece(0,0,false, spaceSize);
		pieces[0][2] = new Piece(0,1,false, spaceSize);
		pieces[0][3] = new Piece(0,2,false, spaceSize);
		pieces[0][4] = new Piece(0,3,false, spaceSize);
		pieces[0][5] = new Piece(0,4,false, spaceSize);
		pieces[columns-1][1] = new Piece(1,0,false, spaceSize);
		pieces[columns-1][2] = new Piece(1,1,false, spaceSize);
		pieces[columns-1][3] = new Piece(1,2,false, spaceSize);
		pieces[columns-1][4] = new Piece(1,3,false, spaceSize);
		pieces[columns-1][5] = new Piece(1,4,false, spaceSize);
		
		
		
		
		
		
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < columns; col++) {
				if(col == 0) {
					spaces[col][row] = (new Square(1, mouseCoordinate[col][row], pieceCoordinate[col][row]));
					spaces[col][row].setType(3);
				}
				else if(col == columns-1) {
					spaces[col][row] = (new Square(1, mouseCoordinate[col][row], pieceCoordinate[col][row]));
					spaces[col][row].setType(4);
				}
				else if(row == 0 || row == rows-1 || col == 1 || col == columns-2){
					spaces[col][row] = (new Square(2, mouseCoordinate[col][row], pieceCoordinate[col][row]));
					spaces[col][row].setType(2);
				}

				
				else if(col%2 == row%2 || (col+1)%2 == (row+1)%2) {
					spaces[col][row] = (new Square(0, mouseCoordinate[col][row], pieceCoordinate[col][row]));
					spaces[col][row].setType(0);
				}
				else {
					spaces[col][row] = (new Square(1, mouseCoordinate[col][row], pieceCoordinate[col][row]));
					spaces[col][row].setType(0);
				}			
			}
		}
		
		
		
		
		
		
		
		
		
		
		


		

		
		//initalize player 1 panel
		JPanel player1panel = new JPanel();		
		player1panel.setPreferredSize(new Dimension(102, spaceSize*rows)); ///102 needs to be flexible
		player1panel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
		player1panel.setLayout(new GridLayout(rows, 1));
		player1panel.setLayout(new BoxLayout(player1panel, BoxLayout.Y_AXIS));
		
		
		player1panel.add(new Label(player1.get_name()));

		JLabel p1Rabbits = new JLabel(String.format("<html>Rabbits:<br>%d</html>", player1.getRabbitCount()));
		p1Rabbits.setAlignmentX(Component.CENTER_ALIGNMENT);
		player1panel.add(p1Rabbits);

		JLabel p1Snakes = new JLabel(String.format("<html>Snakes:<br>%d</html>", player1.getSnakeCount()));
		p1Snakes.setAlignmentX(Component.CENTER_ALIGNMENT);
		player1panel.add(p1Snakes);
		
		JLabel p1Birds = new JLabel(String.format("<html>Birds:<br>%d</html>", player1.getBirdCount()));
		p1Birds.setAlignmentX(Component.CENTER_ALIGNMENT);
		player1panel.add(p1Birds);
		
		JLabel p1Groundhogs = new JLabel(String.format("<html>Groundhogs:<br>%d</html>", player1.getGroundhogCount()));
		p1Groundhogs.setAlignmentX(Component.CENTER_ALIGNMENT);
		player1panel.add(p1Groundhogs);
		
		JLabel p1Turtles = new JLabel(String.format("<html>Turtles:<br>%d</html>", player1.getTurtleCount()));
		p1Turtles.setAlignmentX(Component.CENTER_ALIGNMENT);
		player1panel.add(p1Turtles);
		
		player1panel.add(new Label(""));

		JLabel p1Score= new JLabel(String.format("<html>Score:<br>%d</html>", player1.get_score()));
		p1Score.setAlignmentX(Component.CENTER_ALIGNMENT);
		player1panel.add(p1Score);
		
		player1panel.add(new Label(""));
		
		
		
		
		//intialize player 2 panel
		JPanel player2panel = new JPanel();
		player2panel.setPreferredSize(new Dimension(102, spaceSize*rows)); ///102 needs to be flexible
		player2panel.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		player2panel.setLayout(new GridLayout(rows, 1));
		player2panel.setLayout(new BoxLayout(player2panel, BoxLayout.Y_AXIS));
		
		player2panel.add(new Label(player2.get_name()));

		JLabel p2Rabbits = new JLabel(String.format("<html>Rabbits:<br>%d</html>", player2.getRabbitCount()));
		p2Rabbits.setAlignmentX(Component.CENTER_ALIGNMENT);
		player2panel.add(p2Rabbits);

		JLabel p2Snakes = new JLabel(String.format("<html>Snakes:<br>%d</html>", player2.getSnakeCount()));
		p2Snakes.setAlignmentX(Component.CENTER_ALIGNMENT);
		player2panel.add(p2Snakes);
		
		JLabel p2Birds = new JLabel(String.format("<html>Birds:<br>%d</html>", player2.getBirdCount()));
		p2Birds.setAlignmentX(Component.CENTER_ALIGNMENT);
		player2panel.add(p2Birds);
		
		JLabel p2Groundhogs = new JLabel(String.format("<html>Groundhogs:<br>%d</html>", player2.getGroundhogCount()));
		p2Groundhogs.setAlignmentX(Component.CENTER_ALIGNMENT);
		player2panel.add(p2Groundhogs);
		
		JLabel p2Turtles = new JLabel(String.format("<html>Turtles:<br>%d</html>", player2.getTurtleCount()));
		p2Turtles.setAlignmentX(Component.CENTER_ALIGNMENT);
		player2panel.add(p2Turtles);
		
		player2panel.add(new Label(""));
		
		JLabel p2Score= new JLabel(String.format("<html>Score:<br>%d</html>", player2.get_score()));
		p2Score.setAlignmentX(Component.CENTER_ALIGNMENT);
		player2panel.add(p2Score);
		
		player2panel.add(new Label(""));
		
		
		
		
		GameManager Boss = new GameManager();
		
		
		
		
		JPanel status = new JPanel();
		String player = null;
		if(Boss.getTurn() == 0) {
			 player = player1.get_name();
		}
		else if(Boss.getTurn() == 1) {
			player = player2.get_name();
		}
		JLabel activePlayer = new JLabel(String.format("<html>Active Player:<br>%s</html>", player));
		activePlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
		status.add(activePlayer);
		status.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		
		
		
		JPanel south = new JPanel();
		south.add(new Label("History"));		
		south.setPreferredSize(new Dimension(monitor_buffer.width, 100));
		south.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		
		
		JPanel game = new JPanel();
		game.setLayout(new BorderLayout());
		
		game.add(status, BorderLayout.NORTH);
		game.add(south, BorderLayout.SOUTH);
		game.add(player1panel, BorderLayout.WEST);
		game.add(player2panel, BorderLayout.EAST);
		

		
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

		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < columns; col++) {
				if(pieces[col][row] != null) {
					JPanel stack = new JPanel();
					stack.setLayout(new OverlayLayout(stack));
					stack.add(pieces[col][row]);
					stack.add(spaces[col][row]);
					board.add(stack, row*columns + col);
				}
				else {
					board.add(spaces[col][row], row*columns + col);					
				}
			}
		}
		
			
	
		
		
		board.addMouseListener(new MouseListener() {

			private int tempType = -1;
			private int tempTeam = -1;
			private int selectionX = -1;
			private int selectionY = -1;
			private boolean addPiece = false;
			private boolean movePiece = false;
			
			@Override
			public void mouseClicked(MouseEvent evt) {
				
				int mouse_x = evt.getX();
				int mouse_y = evt.getY();
				int mouseXcoordinate = mouse_x / spaceSize;
				int mouseYcoordinate = mouse_y / spaceSize;
				
				System.out.println("outer X- " + mouseXcoordinate);
				System.out.println("outer Y- " + mouseYcoordinate);
				
				JPanel stack = new JPanel();
				stack.setLayout(new OverlayLayout(stack));
				
				
				if(movePiece == false) {

					//if piece from player1 rack is selected 
					if(mouseXcoordinate == 0) {		
						if(mouseYcoordinate < 6 && mouseYcoordinate > 0) {
							if(mouseYcoordinate == 1 && player1.getRabbitCount() > 0  ||
							   mouseYcoordinate == 2 && player1.getSnakeCount() > 0  ||
							   mouseYcoordinate == 3 && player1.getBirdCount() > 0  ||
							   mouseYcoordinate == 4 && player1.getGroundhogCount() > 0 || 
							   mouseYcoordinate == 5 && player1.getTurtleCount() > 0){
													

								pieces[mouseXcoordinate][mouseYcoordinate].toggleSelection();
								tempType = pieces[mouseXcoordinate][mouseYcoordinate].getPieceType();
								tempTeam = pieces[mouseXcoordinate][mouseYcoordinate].getTeam();
								
								stack.add(pieces[mouseXcoordinate][mouseYcoordinate]);
								stack.add(spaces[mouseXcoordinate][mouseYcoordinate]);
								
								board.remove(mouseYcoordinate*columns + mouseXcoordinate);
								board.add(stack, mouseYcoordinate*columns + mouseXcoordinate);
								
								selectionX = mouseXcoordinate;
								selectionY = mouseYcoordinate;
								addPiece = true;
							}
						}
					}
					
					//if piece from player2 rack is selected 
					else if(mouseXcoordinate == columns-1) {		
						if(mouseYcoordinate < 6 && mouseYcoordinate > 0) {
							pieces[mouseXcoordinate][mouseYcoordinate].toggleSelection();
							tempType = pieces[mouseXcoordinate][mouseYcoordinate].getPieceType();
							tempTeam = pieces[mouseXcoordinate][mouseYcoordinate].getTeam();
							
							//so piece can be highlighted
							stack.add(pieces[mouseXcoordinate][mouseYcoordinate]);
							stack.add(spaces[mouseXcoordinate][mouseYcoordinate]);
							board.remove(mouseYcoordinate*columns + mouseXcoordinate);
							board.add(stack, mouseYcoordinate*columns + mouseXcoordinate);
							
							selectionX = mouseXcoordinate;
							selectionY = mouseYcoordinate;
							addPiece = true;
						}
					}
					
				}
					
				if(movePiece == true && selectionX == mouseXcoordinate && selectionY == mouseYcoordinate) {
					pieces[mouseXcoordinate][mouseYcoordinate].toggleSelection();
					movePiece = false;
					
					for(int col = 0; col < columns; col++) {
						for(int row = 0; row < rows; row++) {
							if(spaces[col][row].get_pieceHere() == 1) {
								spaces[col][row].togglePieceHere();
							}
						}
					}
					board.revalidate();
					board.repaint();
					
					tempType = -1;
					tempTeam = -1;
					selectionX = -1;
					selectionY = -1;
				}
					
				
				
				else if(addPiece == false) {	
					if(mouseXcoordinate > 1 && mouseXcoordinate < columns-2 && mouseYcoordinate > 0 && mouseYcoordinate < rows-1) {
						
						//if the selection on the board has a piece
						if(pieces[mouseXcoordinate][mouseYcoordinate] != null && movePiece != true) {
							movePiece = true;
							pieces[mouseXcoordinate][mouseYcoordinate].toggleSelection();
							tempType = pieces[mouseXcoordinate][mouseYcoordinate].getPieceType();
							tempTeam = pieces[mouseXcoordinate][mouseYcoordinate].getTeam();
							selectionX = mouseXcoordinate;
							selectionY = mouseYcoordinate;
							
							for(int i = 0; i < pieces[mouseXcoordinate][mouseYcoordinate].getListLength(); i++) {
								int f = pieces[mouseXcoordinate][mouseYcoordinate].possibleSpaces().get(i)._Forward;
								int b = pieces[mouseXcoordinate][mouseYcoordinate].possibleSpaces().get(i)._Backward;
								int r = pieces[mouseXcoordinate][mouseYcoordinate].possibleSpaces().get(i)._Right;
								int l = pieces[mouseXcoordinate][mouseYcoordinate].possibleSpaces().get(i)._Left;
								if(f == 1 && r == 1) {
									//if(it is player 1's turn)
									if(pieces[mouseXcoordinate][mouseYcoordinate].getTeam() == 1) {
										int newX = mouseXcoordinate + 1; 
										int newY = mouseYcoordinate + 1;
										
										if(spaces[newX][newY].getType() == 0) {
											spaces[newX][newY].togglePieceHere();
											
											if(pieces[newX][newY] != null && pieces[newX][newY].getTeam() == 1) {
												spaces[newX][newY].togglePieceHere();
											}
										}
									}
									//if(it is player 2's turn)
									if(pieces[mouseXcoordinate][mouseYcoordinate].getTeam() == 0) {
										int newX = mouseXcoordinate - 1; 
										int newY = mouseYcoordinate - 1;
										
										if(spaces[newX][newY].getType() == 0) {
											spaces[newX][newY].togglePieceHere();
											
											if(pieces[newX][newY] != null && pieces[newX][newY].getTeam() == 0) {
												spaces[newX][newY].togglePieceHere();
											}
										}
									}
									
									
									//if the space available for a move is an active piece then toggle its color

											//if it is player 2's turn than - instead of +
								}
								else if(f == 1 && l == 1) {
									if(pieces[mouseXcoordinate][mouseYcoordinate].getTeam() == 1) {
										int newX = mouseXcoordinate - 1; 
										int newY = mouseYcoordinate + 1;
										
										if(spaces[newX][newY].getType() == 0) {
											spaces[newX][newY].togglePieceHere();
											if(pieces[newX][newY] != null && pieces[newX][newY].getTeam() == 1) {
												spaces[newX][newY].togglePieceHere();
											}
										}
									}

									if(pieces[mouseXcoordinate][mouseYcoordinate].getTeam() == 0) {
										int newX = mouseXcoordinate + 1; 
										int newY = mouseYcoordinate - 1;
										
										if(spaces[newX][newY].getType() == 0) {
											spaces[newX][newY].togglePieceHere();
											if(pieces[newX][newY] != null && pieces[newX][newY].getTeam() == 0) {
												spaces[newX][newY].togglePieceHere();
											}
										}
									}
								}
							}
						}	
					}
					board.revalidate();
					board.repaint();
				}
				
					

				if(movePiece == true) {
					
					if(spaces[mouseXcoordinate][mouseYcoordinate].get_pieceHere() == 1) {	

							stack.removeAll();
							System.out.println("its true!");
							System.out.println("type- " + tempType);
							
							if(pieces[mouseXcoordinate][mouseYcoordinate] != null) {
								if(pieces[mouseXcoordinate][mouseYcoordinate].getTeam() != pieces[selectionX][selectionY].getTeam()) {
									pieces[mouseXcoordinate][mouseYcoordinate] = new Piece(tempTeam, tempType, false);
									stack.add(pieces[mouseXcoordinate][mouseYcoordinate]);
									stack.add(spaces[mouseXcoordinate][mouseYcoordinate]);			
									board.remove(mouseYcoordinate*columns + mouseXcoordinate);
									board.add(stack, mouseYcoordinate*columns + mouseXcoordinate);
								}
							}
							
							else {
								pieces[mouseXcoordinate][mouseYcoordinate] = new Piece(tempTeam, tempType, false);
								stack.add(pieces[mouseXcoordinate][mouseYcoordinate]);
								stack.add(spaces[mouseXcoordinate][mouseYcoordinate]);						
								board.add(stack, mouseYcoordinate*columns + mouseXcoordinate);
							}
							
							pieces[selectionX][selectionY] = null;
							board.remove(selectionY*columns + selectionX);
							board.add(spaces[selectionX][selectionY], selectionY*columns + selectionX);
						
							
							for(int col = 0; col < columns; col++) {
								for(int row = 0; row < rows; row++) {
									if(spaces[col][row].get_pieceHere() == 1) {
										spaces[col][row].togglePieceHere();
									}
								}
							}
	
							board.revalidate();
							board.repaint();
							
							Boss.incrementTurn();
							
							addPiece = false;
							movePiece = false;
							tempType = -1;
							tempTeam = -1;
							selectionX = -1;
							selectionY = -1;	
					}
					
				
				}
				
				
				if(pieces[mouseXcoordinate][mouseYcoordinate] == null && addPiece == true) {
					stack.removeAll();
					pieces[mouseXcoordinate][mouseYcoordinate] = new Piece(tempTeam, tempType, false);
					stack.add(pieces[mouseXcoordinate][mouseYcoordinate]);
					stack.add(spaces[mouseXcoordinate][mouseYcoordinate]);
					//board.remove(mouseYcoordinate*columns + mouseXcoordinate);
					board.add(stack, mouseYcoordinate*columns + mouseXcoordinate);

					pieces[selectionX][selectionY].toggleSelection();

					board.revalidate();
					board.repaint();
										
					player1.removeRackPiece(tempType);
			
					
					if(selectionX == 0 && selectionY == 1) {
						JLabel Rabbits = new JLabel(String.format("<html>Rabbits:<br>%d</html>", player1.getRabbitCount()));
						Rabbits.setAlignmentX(Component.CENTER_ALIGNMENT);
						player1panel.remove(1);
						player1panel.add(Rabbits, 1);
					}
					else if(selectionX == 0 && selectionY == 2) {
						JLabel Snakes = new JLabel(String.format("<html>Snakes:<br>%d</html>", player1.getSnakeCount()));
						Snakes.setAlignmentX(Component.CENTER_ALIGNMENT);
						player1panel.remove(2);
						player1panel.add(Snakes, 2);
					}					
					else if(selectionX == 0 && selectionY == 3) {
						JLabel p1Birds = new JLabel(String.format("<html>Birds:<br>%d</html>", player1.getBirdCount()));
						p1Birds.setAlignmentX(Component.CENTER_ALIGNMENT);
						player1panel.remove(3);
						player1panel.add(p1Birds, 3);
					}
					else if(selectionX == 0 && selectionY == 4) {	
						JLabel p1Groundhogs = new JLabel(String.format("<html>Groundhogs:<br>%d</html>", player1.getGroundhogCount()));
						p1Groundhogs.setAlignmentX(Component.CENTER_ALIGNMENT);
						player1panel.remove(4);
						player1panel.add(p1Groundhogs, 4);
					}
					else if(selectionX == 0 && selectionY == 5) {
						JLabel p1Turtles = new JLabel(String.format("<html>Turtles:<br>%d</html>", player1.getTurtleCount()));
						p1Turtles.setAlignmentX(Component.CENTER_ALIGNMENT);
						player1panel.remove(5);
						player1panel.add(p1Turtles, 5);
					}
					
					
					addPiece = false;
					tempType = -1;
					tempTeam = -1;
					selectionX = -1;
					selectionY = -1;		
				}		
				
				
				JPanel status = new JPanel();
				String player = null;
				if(Boss.getTurn() == 0) {
					 player = player1.get_name();
				}
				else if(Boss.getTurn() == 1) {
					player = player2.get_name();
				}
				JLabel activePlayer = new JLabel(String.format("<html>Active Player:<br>%s</html>", player));
				activePlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
				status.add(activePlayer);
				status.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
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
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
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
		
		//only works to expand the board currently
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

	

	
	
}



