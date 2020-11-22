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
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

public class Go {

	public static void main(String[] args) {

		
		
		
		JFrame window = new JFrame("Glade");

		
		

		
		int columns = (8 + 4);
		int rows = (8 + 2);

		
		int mouseCoordinate[][] = new int[columns][rows];
		int pieceCoordinate[][] = new int[columns][rows];
		
		
		int playerOnePieces[] = new int[columns];
		int playerTwoPieces[] = new int[columns];
		
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
		
		
		
		
		Player p1 = new Player(0);
		p1.set_name("Ashton");
		//int p1id = p1.get_id();
		p1.addRackPiece(new Piece(0, 0, false, spaceSize));
		p1.addRackPiece(new Piece(0, 0, false, spaceSize));
		p1.addRackPiece(new Piece(0, 1, false, spaceSize));
		p1.addRackPiece(new Piece(0, 1, false, spaceSize));
		p1.addRackPiece(new Piece(0, 2, false, spaceSize));
		p1.addRackPiece(new Piece(0, 2, false, spaceSize));
		p1.addRackPiece(new Piece(0, 3, false, spaceSize));
		p1.addRackPiece(new Piece(0, 3, false, spaceSize));
		
		Player p2 = new Player(1);
		p2.set_name("Brian");
		p2.addRackPiece(new Piece(1, 0, false, spaceSize));
		p2.addRackPiece(new Piece(1, 0, false, spaceSize));
		p2.addRackPiece(new Piece(1, 1, false, spaceSize));
		p2.addRackPiece(new Piece(1, 1, false, spaceSize));
		p2.addRackPiece(new Piece(1, 2, false, spaceSize));
		p2.addRackPiece(new Piece(1, 2, false, spaceSize));
		p2.addRackPiece(new Piece(1, 3, false, spaceSize));
		p2.addRackPiece(new Piece(1, 3, false, spaceSize));
		
		
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
				if(col == 0 || col == columns-1) {
					spaces[col][row] = (new Square(1, spaceSize, mouseCoordinate[col][row], pieceCoordinate[col][row]));
				}
				else if(row == 0 || row == rows-1 || col == 1 || col == columns-2){
					spaces[col][row] = (new Square(2, spaceSize, mouseCoordinate[col][row], pieceCoordinate[col][row]));
				}

				
				else if(col%2 == row%2 || (col+1)%2 == (row+1)%2) {
					spaces[col][row] = (new Square(0, spaceSize, mouseCoordinate[col][row], pieceCoordinate[col][row]));
				}
				else {
					spaces[col][row] = (new Square(1, spaceSize, mouseCoordinate[col][row], pieceCoordinate[col][row]));
				}			
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		JPanel player1 = new JPanel();		
		player1.setPreferredSize(new Dimension(102, spaceSize*rows));
		player1.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
		player1.setLayout(new GridLayout(rows, 1));
		player1.setLayout(new BoxLayout(player1, BoxLayout.Y_AXIS));
		
		JPanel player2 = new JPanel();
		player2.setPreferredSize(new Dimension(102, spaceSize*rows));
		player2.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		player2.setLayout(new GridLayout(rows, 1));
		player2.setLayout(new BoxLayout(player2, BoxLayout.Y_AXIS));
		

		player1.add(new Label(p1.get_name()));

		JLabel Rabbits = new JLabel(String.format("<html>Rabbits:<br>%d</html>", p1.getRabbitCount()));
		Rabbits.setAlignmentX(Component.CENTER_ALIGNMENT);
		player1.add(Rabbits);

		JLabel Snakes = new JLabel(String.format("<html>Snakes:<br>%d</html>", p1.getSnakeCount()));
		Snakes.setAlignmentX(Component.CENTER_ALIGNMENT);
		player1.add(Snakes);
		
		JLabel Birds = new JLabel(String.format("<html>Birds:<br>%d</html>", p1.getBirdCount()));
		Birds.setAlignmentX(Component.CENTER_ALIGNMENT);
		player1.add(Birds);
		
		JLabel Groundhogs = new JLabel(String.format("<html>Groundhogs:<br>%d</html>", p1.getGroundhogCount()));
		Groundhogs.setAlignmentX(Component.CENTER_ALIGNMENT);
		player1.add(Groundhogs);
		
		JLabel Turtles = new JLabel(String.format("<html>Turtles:<br>%d</html>", p1.getTurtleCount()));
		Turtles.setAlignmentX(Component.CENTER_ALIGNMENT);
		player1.add(Turtles);
		
		
		player1.add(new Label(""));
		player1.add(new Label(""));
		player1.add(new Label(""));
		
		for(int i = 0; i < rows; i++) {
			//player2.add(new Square(2, spaceSize));
			//player2.add(p2.get_rack_piece(i));
			player2.add(new Number(3));
		}

		
		
		
		JPanel north = new JPanel();
		north.add(new Label("GLADE"));
		north.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		
		JPanel south = new JPanel();
		south.add(new Label("History"));		
		south.setPreferredSize(new Dimension(monitor_buffer.width, 100));
		south.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		
		
		JPanel game = new JPanel();
		game.setLayout(new BorderLayout());
		
		game.add(north, BorderLayout.NORTH);
		game.add(south, BorderLayout.SOUTH);
		game.add(player1, BorderLayout.WEST);
		game.add(player2, BorderLayout.EAST);
		

		
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
					//System.out.println("Piece@-  col:" + col + " row:" + row);
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
		
		
		/*
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < columns; col++) {
				
				//top and bottom corners
				if(row == 0 && col == 0 || row == 0 && col == columns-1 || row == rows-1 && col == 0 || row == rows-1 && col == columns-1 ) {
					board.add(new Square(3, spaceSize, mouseCoordinate[col][row], pieceCoordinate[col][row]));
				}
				//top row
				else if(row == 0 || row == rows-1) {
					board.add(new Square(2, spaceSize, mouseCoordinate[col][row], pieceCoordinate[col][row]));
				}				
				//bottom row
				else if(col == 1 || col == columns-2) {
					board.add(new Square(2, spaceSize, mouseCoordinate[col][row], pieceCoordinate[col][row]));					
				}
				//player1 pieces
				else if(col == 0) {	
					JPanel stack = new JPanel();
					stack.setLayout(new OverlayLayout(stack));
					if(pieces[col][row] != null) {
						stack.add(pieces[col][row]);
						stack.add(new Square(1,spaceSize, 0,0));
						board.add(stack, row*columns + col);
					}
					
					else {
						board.add(new Square(2, spaceSize, mouseCoordinate[col][row], pieceCoordinate[col][row]), row*columns + col);
					}
				}
				else if(col == columns-1) {
					board.add(p2.get_rack_piece(row-1));
				}
				
				
				else if(col%2 == row%2 || (col+1)%2 == (row+1)%2) {
					board.add(new Square(0, spaceSize, mouseCoordinate[col][row], pieceCoordinate[col][row]));
				}
				else {
					board.add(new Square(1, spaceSize, mouseCoordinate[col][row], pieceCoordinate[col][row]));
				}			
			}
		}
		
		 */
		
		
		
	
		
		
		board.addMouseListener(new MouseListener() {

			private int tempType = -1;
			private int tempTeam = -1;
			private int selectionX = -1;
			private int selectionY = -1;
			private boolean selectionMade = false;
			
			@Override
			public void mouseClicked(MouseEvent evt) {
				
				int mouse_x = evt.getX();
				int mouse_y = evt.getY();
				System.out.println("clickX- " + mouse_x);
				System.out.println("clickY- " + mouse_y);
				int mouseXcoordinate = mouse_x / spaceSize;
				int mouseYcoordinate = mouse_y / spaceSize;
					
				JPanel stack = new JPanel();
				stack.setLayout(new OverlayLayout(stack));
				
				
				if(selectionMade == false) {

					if(mouseXcoordinate == 0) {		
						switch(mouseYcoordinate) {
						case 1:
							pieces[mouseXcoordinate][mouseYcoordinate].toggleSelection();
							tempType = pieces[mouseXcoordinate][mouseYcoordinate].getPieceType();
							tempTeam = pieces[mouseXcoordinate][mouseYcoordinate].getTeam();
							
				
							stack.add(pieces[mouseXcoordinate][mouseYcoordinate]);
							stack.add(spaces[mouseXcoordinate][mouseYcoordinate]);
							board.remove(mouseYcoordinate*columns + mouseXcoordinate);
							board.add(stack, mouseYcoordinate*columns + mouseXcoordinate);
							
							
							selectionX = mouseXcoordinate;
							selectionY = mouseYcoordinate;
							selectionMade = true;
							break;
							
						
						case 2:
							pieces[mouseXcoordinate][mouseYcoordinate].toggleSelection();
							tempType = pieces[mouseXcoordinate][mouseYcoordinate].getPieceType();
							tempTeam = pieces[mouseXcoordinate][mouseYcoordinate].getTeam();
							
							stack.add(pieces[mouseXcoordinate][mouseYcoordinate]);
							stack.add(spaces[mouseXcoordinate][mouseYcoordinate]);
							board.remove(mouseYcoordinate*columns + mouseXcoordinate);
							board.add(stack, mouseYcoordinate*columns + mouseXcoordinate);
							
							selectionX = mouseXcoordinate;
							selectionY = mouseYcoordinate;
							selectionMade = true;
							break;
							
						}
					}
					
					if(mouseXcoordinate > 1 && mouseXcoordinate < columns-2 && mouseYcoordinate > 1 && mouseYcoordinate < rows-2) {
						if(pieces[mouseXcoordinate][mouseYcoordinate] != null) {
							pieces[mouseXcoordinate][mouseYcoordinate].toggleSelection();
							Piece here = pieces[mouseXcoordinate][mouseYcoordinate];
							int size = here.getListLength();
							for(int i = 0; i < here.getListLength(); i++) {
								int f = pieces[mouseXcoordinate][mouseYcoordinate].possibleSpaces().get(i)._Forward;
								int r = pieces[mouseXcoordinate][mouseYcoordinate].possibleSpaces().get(i)._Right;
								int l = pieces[mouseXcoordinate][mouseYcoordinate].possibleSpaces().get(i)._Left;
								if(f == 1 && r == 1) {
									//if(it is player 1's turn)
									spaces[mouseXcoordinate + 1][mouseYcoordinate + 1].togglePieceHere();
											//if it is player 2's turn than - instead of +
								}
								else if(f == 1 && l == 1) {
									System.out.println("move right");
									spaces[mouseXcoordinate - 1][mouseYcoordinate + 1].togglePieceHere();
								}
								else if(f == 1) {
									spaces[mouseXcoordinate][mouseYcoordinate + 1].togglePieceHere();
								}
							}
						}
					}
					
					
					board.revalidate();
					board.repaint();
				}
				
				
				if(pieces[mouseXcoordinate][mouseYcoordinate] == null) {
					stack.removeAll();
					pieces[mouseXcoordinate][mouseYcoordinate] = new Piece(tempTeam, tempType, false, spaceSize);
					stack.add(pieces[mouseXcoordinate][mouseYcoordinate]);
					stack.add(spaces[mouseXcoordinate][mouseYcoordinate]);
					//board.remove(mouseYcoordinate*columns + mouseXcoordinate);
					board.add(stack, mouseYcoordinate*columns + mouseXcoordinate);

					pieces[selectionX][selectionY].toggleSelection();

					board.revalidate();
					board.repaint();
			
					
					
					
					p1.removeRackPiece(tempType);
			
					JLabel Rabbits = new JLabel(String.format("<html>Rabbits:<br>%d</html>", p1.getRabbitCount()));
					Rabbits.setAlignmentX(Component.CENTER_ALIGNMENT);
					player1.remove(1);
					player1.add(Rabbits, 1);
					
					
					selectionMade = false;
					tempType = -1;
					tempTeam = -1;
					selectionX = -1;
					selectionY = -1;
					
				}
						
						
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
			
			
			public void mouseDragged(MouseEvent evt) {
			}

			
			public void mouseMoved(MouseEvent evt) {
				
				int mouse_x = evt.getX();
				int mouse_y = evt.getY();
				System.out.println("X- " + mouse_x + "  Column: " + mouse_x/spaceSize);
				System.out.println("Y- " + mouse_y + "  Row: " + mouse_y/spaceSize);
				System.out.println("prevX- " + prevX + "  PrevY- " + prevY);
				
				
				
				int mouseXcoordinate = mouse_x / spaceSize;
				int mouseYcoordinate = mouse_y / spaceSize;
				
				
				if(initalize == true) {
					spaces[prevX][prevY].toggleMouseHere();
					initalize = false;
					System.out.println("initalize = " + initalize);
				}
				
				
				if(prevX != mouseXcoordinate || prevY != mouseYcoordinate) {
					
					
					
				for(int col = 0; col < columns; col++) {	
					for(int row = 0; row < rows; row++) {

						if(mouseXcoordinate == col && mouseYcoordinate == row) {
								
							spaces[col][row].toggleMouseHere();
	//						mouseCoordinate[col][row] = 1;
							spaces[prevX][prevY].toggleMouseHere();

						}
						
						
						
						/*
						else {
							mouseCoordinate[col][row] = 0;
							board.remove(row*columns + col);
						}
							//
							
							//top and bottom corners
							if(row == 0 && col == 0 || row == 0 && col == columns-1 || row == rows-1 && col == 0 || row == rows-1 && col == columns-1 ) {
								board.add(new Square(3, spaceSize, mouseCoordinate[col][row], pieceCoordinate[col][row]), row*columns + col);
							}
							//top row
							else if(row == 0 || row == rows-1) {
								board.add(new Square(2, spaceSize, mouseCoordinate[col][row], pieceCoordinate[col][row]), row*columns + col);
							}				
							//bottom row
							else if(col == 1 || col == columns-2) {
								board.add(new Square(2, spaceSize, mouseCoordinate[col][row], pieceCoordinate[col][row]), row*columns + col);					
							}
							//player1 pieces
							else if(col == 0) {		
								JPanel stack = new JPanel();
								stack.setLayout(new OverlayLayout(stack));

								switch(row) {
								case 1:
									stack.add(pieces[col][row]);
									stack.add(new Square(1,spaceSize, mouseCoordinate[col][row],pieceCoordinate[col][row]));
									board.add(stack, row*columns + col);
									break;
								case 2:
									board.add(new Piece(0,1,false,spaceSize), row*columns + col);
									break;
								case 3:
									board.add(new Piece(0,2,false,spaceSize), row*columns + col);
									break;
								case 4:
									board.add(new Piece(0,3,false,spaceSize), row*columns + col);
									break;
								default:
									board.add(new Square(2, spaceSize, mouseCoordinate[col][row], pieceCoordinate[col][row]), row*columns + col);
								}
							}
							else if(col == columns-1) {
								board.add(p2.get_rack_piece(row-1), row*columns + col);
							}
							
							
							else if(col%2 == row%2 || (col+1)%2 == (row+1)%2) {
								board.add(new Square(0, spaceSize, mouseCoordinate[col][row], pieceCoordinate[col][row]), row*columns + col);
							}
							else {
								board.add(new Square(1, spaceSize, mouseCoordinate[col][row], pieceCoordinate[col][row]), row*columns + col);
							}	
						//}
							*/

						board.revalidate();
						board.repaint();
					}
				}
				prevX = mouseXcoordinate;
				prevY = mouseYcoordinate;
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				/*
				for(int i = 0; i < columns; i++) {
					for(int j = 0; j < rows; j++) {
	
						if(mouse_x / spaceSize != i || mouse_y / spaceSize != j) {
							if(board.getComponent(j*rows + i).getColorModel().getColorSpace() == Color.RED.getColorSpace()) {
								//System.out.println("touching red & SHOULDNT BE :)");
								//System.out.println("sq,width " + board.getComponent(j*rows + i).getWidth());
								//System.out.println("sq,height " + board.getComponent(j*rows + i).getHeight());

								
								if(i%2 == j%2 || (i+1%2 == (j+1)%2)) {
									board.remove(j*rows + i);
									//System.out.println("mosueData- " + mouseCoordinate[i][j]);
									mouseCoordinate[i][j] = 0;
									board.add(new Square(0, spaceSize, mouseCoordinate[i][j], pieceCoordinate[i][j]), j*rows + i);
								}
								else {
									board.remove(j*rows + i);
									mouseCoordinate[i][j] = 0;
									board.add(new Square(1, spaceSize, mouseCoordinate[i][j], pieceCoordinate[i][j]), j*rows + i);
								}
							}
						}
						
						
						
						if(mouse_x / spaceSize == i && mouse_y / spaceSize == j && board.getComponent(j*rows + i).getForeground() != Color.RED) {
							System.out.println("X- " + mouse_x/spaceSize);
							System.out.println("Y- " + mouse_y/spaceSize);
							System.out.println();
							mouseCoordinate[i][j] = 1;
							board.remove(j*rows + i);
							board.add(new Square(0, spaceSize, mouseCoordinate[i][j], pieceCoordinate[i][j]), j*rows + i);

						}
				
						board.revalidate();
						
					}
				}
				*/
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


