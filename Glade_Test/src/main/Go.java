package main;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Go {

	public static void main(String[] args) {

		
		JFrame window = new JFrame("Glade");

		
		
		Player ashton = new Player(0);
		ashton.add_rack_piece(new Piece(1));
		ashton.add_rack_piece(new Piece(1));
		ashton.add_rack_piece(new Piece(2));
		ashton.add_rack_piece(new Piece(2));
		ashton.add_rack_piece(new Piece(3));
		ashton.add_rack_piece(new Piece(3));
		ashton.add_rack_piece(new Piece(4));
		ashton.add_rack_piece(new Piece(4));
		
		Player brian = new Player(1);
		brian.add_rack_piece(new Piece(4));
		brian.add_rack_piece(new Piece(4));
		brian.add_rack_piece(new Piece(3));
		brian.add_rack_piece(new Piece(3));
		brian.add_rack_piece(new Piece(2));
		brian.add_rack_piece(new Piece(2));
		brian.add_rack_piece(new Piece(1));
		brian.add_rack_piece(new Piece(1));
		
		int board_x = 8;
		int board_y = 8;

		
		int mouseCoordinate[][] = new int[board_x][board_y];
		int pieceCoordinate[][] = new int[board_x][board_y];
		
		
		int playerOnePieces[] = new int[board_x];
		int playerTwoPieces[] = new int[board_x];
		


		
		
		
		
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
		
		
		int spaceSize = (monitor_buffer.width-200)/board_x;
		System.out.println("spaceSize- " + spaceSize);
		int minSpaceSize = 330/board_x;
		System.out.println("minSpaceSize- " + minSpaceSize);
		
		
		
		JPanel player1 = new JPanel();		
		player1.setPreferredSize(new Dimension(spaceSize+(spaceSize/5), spaceSize*board_y));
		player1.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
		player1.setLayout(new BoxLayout(player1, BoxLayout.Y_AXIS));
		
		JPanel player2 = new JPanel();
		player2.setPreferredSize(new Dimension(spaceSize+(spaceSize/5), spaceSize*board_y));
		player2.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		player2.setLayout(new BoxLayout(player2, BoxLayout.Y_AXIS));
		

		for(int i = 0; i < ashton.get_board_count(); i++) {
			player1.add(new GraySquare(3, spaceSize, 0, ashton.get_rack_piece(i).get_id()));
		}
		for(int i = 0; i < brian.get_board_count(); i++) {
			player2.add(new GraySquare(3, spaceSize, 0, brian.get_rack_piece(i).get_id()+4));
		}

		
		
		
		JPanel north = new JPanel();
		north.add(new Label("GLADE"));
		north.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		
		JPanel south = new JPanel();
		south.add(new Label("History"));		
		south.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		
		
		JPanel game = new JPanel();
		game.setLayout(new BorderLayout());
		
		game.add(north, BorderLayout.NORTH);
		game.add(south, BorderLayout.SOUTH);
		game.add(player1, BorderLayout.WEST);
		game.add(player2, BorderLayout.EAST);
		

		
		//window.setPreferredSize(new Dimension(((minSpaceSize)*(board_x)), (minSpaceSize*(board_y))));
		//window.setMinimumSize(new Dimension(((minSpaceSize)*(board_x)), (minSpaceSize*(board_y))));
		
		JPanel board = new JPanel();
		board.setPreferredSize(new Dimension((spaceSize*board_x), (spaceSize*board_y)));
		board.setMinimumSize(new Dimension((minSpaceSize*(board_x)), (minSpaceSize*(board_y))));
		
		board.setMaximumSize(monitor_buffer);
		
		
		System.out.println("minSize- " + board.getMinimumSize());
		System.out.println("Size- " + board.getSize());
		System.out.println();
		game.add(board, BorderLayout.CENTER);

		board.setLayout(new GridLayout(board_x, board_y));

		
		for(int col = 0; col < board_x; col++) {
			for(int row = 0; row < board_y; row++) {
				if(col%2 == row%2 || (col+1)%2 == (row+1)%2) {
					board.add(new GraySquare(0, spaceSize, mouseCoordinate[row][col], pieceCoordinate[row][col]));
				}
				else {
					board.add(new GraySquare(1, spaceSize, mouseCoordinate[row][col], pieceCoordinate[row][col]));
				}			
			}
		}
		

		
		board.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent evt) {
				
				int mouse_x = evt.getX();
				int mouse_y = evt.getY();
				System.out.println("clickX- " + mouse_x);
				System.out.println("clickY- " + mouse_y);

				if(pieceCoordinate[mouse_x / spaceSize][mouse_y / spaceSize] != 0) {
					pieceCoordinate[mouse_x / spaceSize][mouse_y / spaceSize] = 0;
				}
				else {
					pieceCoordinate[mouse_x / spaceSize][mouse_y / spaceSize] = 2;
				}
				
				
				for(int i = 0; i < board_x; i++) {
					for(int j = 0; j < board_y; j++) {

						if(pieceCoordinate[i][j] != 0) {
							board.remove(j*board_y + i);
							board.add(new GraySquare(0, spaceSize, mouseCoordinate[i][j], pieceCoordinate[i][j]), j*board_y + i);
						}
						else {
							if(i%2 == j%2 || (i+1%2 == (j+1)%2)) {
								board.remove(j*board_y + i);
								board.add(new GraySquare(0, spaceSize, mouseCoordinate[i][j], pieceCoordinate[i][j]), j*board_y + i);
							}
							else {
								board.remove(j*board_y + i);
								board.add(new GraySquare(1, spaceSize, mouseCoordinate[i][j], pieceCoordinate[i][j]), j*board_y + i);
							}
						}
				
						board.revalidate();
					}
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
			
			public void mouseDragged(MouseEvent evt) {
			}

			
			public void mouseMoved(MouseEvent evt) {
				
				int mouse_x = evt.getX();
				int mouse_y = evt.getY();
				System.out.println("X- " + mouse_x);
				System.out.println("Y- " + mouse_y);

				
				for(int i = 0; i < board_x; i++) {
					for(int j = 0; j < board_y; j++) {
	
						if(mouse_x / spaceSize != i || mouse_y / spaceSize != j) {
							if(board.getComponent(j*board_y + i).getColorModel().getColorSpace() == Color.RED.getColorSpace()) {
								//System.out.println("touching red & SHOULDNT BE :)");
								//System.out.println("sq,width " + board.getComponent(j*board_y + i).getWidth());
								//System.out.println("sq,height " + board.getComponent(j*board_y + i).getHeight());

								
								if(i%2 == j%2 || (i+1%2 == (j+1)%2)) {
									board.remove(j*board_y + i);
									//System.out.println("mosueData- " + mouseCoordinate[i][j]);
									mouseCoordinate[i][j] = 0;
									board.add(new GraySquare(0, spaceSize, mouseCoordinate[i][j], pieceCoordinate[i][j]), j*board_y + i);
								}
								else {
									board.remove(j*board_y + i);
									mouseCoordinate[i][j] = 0;
									board.add(new GraySquare(1, spaceSize, mouseCoordinate[i][j], pieceCoordinate[i][j]), j*board_y + i);
								}
							}
						}
						
						if(mouse_x / spaceSize == i && mouse_y / spaceSize == j && board.getComponent(j*board_y + i).getForeground() != Color.RED) {
							System.out.println("X- " + mouse_x/spaceSize);
							System.out.println("Y- " + mouse_y/spaceSize);
							System.out.println();
							mouseCoordinate[i][j] = 1;
							board.remove(j*board_y + i);
							board.add(new GraySquare(0, spaceSize, mouseCoordinate[i][j], pieceCoordinate[i][j]), j*board_y + i);

						}
				
						board.revalidate();
					}
				}
			}
		});
		
		
		
		
		window.setContentPane(game);
		//window.setContentPane(board);
		window.pack();
		window.setLocation(300, 50);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	
		
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
		
	}

	
}


