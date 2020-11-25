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
import java.util.ArrayList;
import java.util.List;

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
		
		GameManager miniTest = new GameManager();
		miniTest.setUpClassicGlade();

		int columns = (miniTest.getColumns() + 4);
		int rows = (miniTest.getRows() + 2);
		
		Piece pieces[][] = new Piece[columns][rows]; 
		Space spaces[][] = new Space[columns][rows]; 
		
		
		
		
		int mouseCoordinate[][] = new int[columns][rows];
		int pieceCoordinate[][] = new int[columns][rows];
		
	
	

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
		
		
		
		
		
		
		//initalize pieces array
		pieces[0][1] = new Piece(miniTest.Player1().getColor(),0,false);
		pieces[0][2] = new Piece(miniTest.Player1().getColor(),1,false);
		pieces[0][3] = new Piece(miniTest.Player1().getColor(),2,false);
		pieces[0][4] = new Piece(miniTest.Player1().getColor(),3,false);
		pieces[0][5] = new Piece(miniTest.Player1().getColor(),4,false);
		miniTest.incrementTurn();
		pieces[columns-1][1] = new Piece(miniTest.Player2().getColor(),0,false);
		pieces[columns-1][2] = new Piece(miniTest.Player2().getColor(),1,false);
		pieces[columns-1][3] = new Piece(miniTest.Player2().getColor(),2,false);
		pieces[columns-1][4] = new Piece(miniTest.Player2().getColor(),3,false);
		pieces[columns-1][5] = new Piece(miniTest.Player2().getColor(),4,false);
		
		
		
		//initalize spaces array
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < columns; col++) {
				if(col == 0) {
					spaces[col][row] = (new Space(1, 0, 0));
					spaces[col][row].setType(3);
				}
				else if(col == columns-1) {
					spaces[col][row] = (new Space(1, 0, 0));
					spaces[col][row].setType(4);
				}
				else if(row == 0 || row == rows-1 || col == 1 || col == columns-2){
					spaces[col][row] = (new Space(2, mouseCoordinate[col][row], pieceCoordinate[col][row]));
					spaces[col][row].setType(2);
				}

				
				else if(col%2 == row%2 || (col+1)%2 == (row+1)%2) {
					spaces[col][row] = (new Space(0, mouseCoordinate[col][row], pieceCoordinate[col][row]));
					spaces[col][row].setType(0);
				}
				else {
					spaces[col][row] = (new Space(1, mouseCoordinate[col][row], pieceCoordinate[col][row]));
					spaces[col][row].setType(0);
				}			
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		//initalize player 1 panel
		JPanel p1panel = new JPanel();		
		p1panel.setPreferredSize(new Dimension(102, spaceSize*rows)); ///102 needs to be flexible to work on all monitors ()
		p1panel.setBorder(BorderFactory.createLineBorder(miniTest.Player1().getColor(), 3));
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
		p2panel.setPreferredSize(new Dimension(102, spaceSize*rows)); ///102 needs to be flexible
		p2panel.setBorder(BorderFactory.createLineBorder(miniTest.Player2().getColor(), 3));
		p2panel.setLayout(new GridLayout(rows, 1));
		p2panel.setLayout(new BoxLayout(p2panel, BoxLayout.Y_AXIS));
		
		p2panel.add(new Label(miniTest.Player1().getName()));
		RabbitLabel(miniTest.Player1(), p2panel, false);
		SnakeLabel(miniTest.Player1(), p2panel, false);
		BirdLabel(miniTest.Player1(), p2panel, false);
		GroundhogLabel(miniTest.Player1(), p2panel, false);
		TurtleLabel(miniTest.Player1(), p2panel, false);
		p2panel.add(new Label(""));
		ScoreLabel(miniTest.Player1(), p2panel, false);
		p2panel.add(new Label(""));
		
		
		
		
		 
		 
		JPanel status = new JPanel();
		String player = null;

		if(miniTest.getTurn() == 0) {
			 player = miniTest.Player1().getName();
				System.out.println("player1");
		}
		else if(miniTest.getTurn() == 1) {
			player = miniTest.Player2().getName();
			System.out.println("player2");
		}
		JLabel activePlayer = new JLabel(String.format("<html>Active Player:<br>%s</html>", player));
		activePlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
		status.add(activePlayer);
		status.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		
		JPanel messages = new JPanel();
		messages.add(new Label("Help Messages + History"));		
		messages.setPreferredSize(new Dimension(monitor_buffer.width, 100));
		messages.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		
		
		
		JPanel game = new JPanel();
		game.setLayout(new BorderLayout());
		game.add(status, BorderLayout.NORTH);
		game.add(messages, BorderLayout.SOUTH);
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
		
		
		//initalize board image
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
			private boolean validSelection = false;
			
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
							if(miniTest.getTurn() % 2 == miniTest.Player1().getID()) {
								validSelection = true;
							}
							
							if(validSelection == true) {
								//test weather player1 has pieces available in th selection
								if(mouseYcoordinate == 1 && miniTest.Player1().getRabbitCount() > 0  ||
								   mouseYcoordinate == 2 && miniTest.Player1().getSnakeCount() > 0  ||
								   mouseYcoordinate == 3 && miniTest.Player1().getBirdCount() > 0  ||
								   mouseYcoordinate == 4 && miniTest.Player1().getGroundhogCount() > 0 || 
								   mouseYcoordinate == 5 && miniTest.Player1().getTurtleCount() > 0){
														
	
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
							
							miniTest.incrementTurn();
							
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
										
					miniTest.currentPlayer().removeRackPiece(tempType);
			
					
					System.out.println("here!!!");
					JPanel panel;
					if(miniTest.currentPlayer() == miniTest.Player1()) {
						panel = p1panel;
					}
					else {
						panel = p2panel;
					}
						
					switch (selectionY) {
					case 1:
						RabbitLabel(miniTest.currentPlayer(), panel, true);
						break;
					case 2:
						System.out.println("snake!!!");
						SnakeLabel(miniTest.currentPlayer(), panel, true);
						break;
					case 3:
						BirdLabel(miniTest.currentPlayer(), panel, true);
						break;
					case 4:
						GroundhogLabel(miniTest.currentPlayer(), panel, true);
						break;
					case 5:
						TurtleLabel(miniTest.currentPlayer(), panel, true);
						break;
					}
					
					addPiece = false;
					tempType = -1;
					tempTeam = -1;
					selectionX = -1;
					selectionY = -1;		
				}		
				
				
				JPanel status = new JPanel();
				String player = null;
				if(miniTest.getTurn() == 0) {
					 player = miniTest.Player1().getName();
				}
				else if(miniTest.getTurn() == 1) {
					player = miniTest.Player2().getName();
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

	
	
	
	
	
	
	
	
	//helper methods for maintaining player info panels
	public static void RabbitLabel(Player player, JPanel playerPanel, boolean remove) {
		if(remove == true) {
			playerPanel.remove(1);
		}
		JLabel Rabbits = new JLabel(String.format("<html>Rabbits:<br>%d</html>", player.getRabbitCount()));
		Rabbits.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerPanel.add(Rabbits, 1);	
	}
	
	
	public static void SnakeLabel(Player player, JPanel playerPanel, boolean remove) {
		if(remove == true) {
			playerPanel.remove(2);
		}
		JLabel Snakes = new JLabel(String.format("<html>Snakes:<br>%d</html>", player.getSnakeCount()));
		Snakes.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerPanel.add(Snakes, 2);
	}

	public static void BirdLabel(Player player, JPanel playerPanel, boolean remove) {
		if(remove == true) {
			playerPanel.remove(3);
		}
		JLabel Birds = new JLabel(String.format("<html>Birds:<br>%d</html>", player.getBirdCount()));
		Birds.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerPanel.add(Birds, 3);
	}
	
	public static void GroundhogLabel(Player player, JPanel playerPanel, boolean remove) {
		if(remove == true) {
			playerPanel.remove(4);
		}
		JLabel Groundhogs = new JLabel(String.format("<html>Groundhogs:<br>%d</html>", player.getGroundhogCount()));
		Groundhogs.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerPanel.add(Groundhogs, 4);
	}
	
	public static void TurtleLabel(Player player, JPanel playerPanel, boolean remove) {
		if(remove == true) {
			playerPanel.remove(5);
		}
		JLabel Turtles = new JLabel(String.format("<html>Turtles:<br>%d</html>", player.getTurtleCount()));
		Turtles.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerPanel.add(Turtles, 5);
	}
	
	public static void ScoreLabel(Player player, JPanel playerPanel, boolean remove) {
		if(remove == true) {
			playerPanel.remove(7);
		}
		JLabel Score = new JLabel(String.format("<html>Score:<br>%d</html>", player.getScore()));
		Score.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerPanel.add(Score, 7);
	}
}



