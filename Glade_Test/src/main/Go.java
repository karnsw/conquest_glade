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
		OUTBOUNDS = false;
	
	
	public static void main(String[] args) {

		
		JFrame window = new JFrame("Glade");
		
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
		
		int ttlGameCols = boardColsCount + boardBufferColsCount;
		int ttlGameRows = boardRowsCount + boardBufferRowsCount;
				
				
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
		pieces[0][1] = new Piece(miniTest.Player1().getColor(), miniTest.Player1().getTeam(), RABBIT, false);
		pieces[0][2] = new Piece(miniTest.Player1().getColor(), miniTest.Player1().getTeam(), SNAKE, false);
		pieces[0][3] = new Piece(miniTest.Player1().getColor(), miniTest.Player1().getTeam(), BIRD, false);
		pieces[0][4] = new Piece(miniTest.Player1().getColor(), miniTest.Player1().getTeam(), GROUNDHOG, false);
		pieces[0][5] = new Piece(miniTest.Player1().getColor(), miniTest.Player1().getTeam(), TURTLE, false);
		pieces[columns-1][1] = new Piece(miniTest.Player2().getColor(), miniTest.Player1().getTeam(), RABBIT, false);
		pieces[columns-1][2] = new Piece(miniTest.Player2().getColor(), miniTest.Player1().getTeam(), SNAKE, false);
		pieces[columns-1][3] = new Piece(miniTest.Player2().getColor(), miniTest.Player1().getTeam(), BIRD, false);
		pieces[columns-1][4] = new Piece(miniTest.Player2().getColor(), miniTest.Player1().getTeam(), GROUNDHOG, false);
		pieces[columns-1][5] = new Piece(miniTest.Player2().getColor(), miniTest.Player1().getTeam(), TURTLE, false);
		
		
		
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
				if(row < (boardRowsCount/2 + boardRowStart)) {
					spaces[col][row].setTeamTerritory(miniTest.Player1().getTeam());
					spaces[col][row].setTeamTerritoryColor(miniTest.Player1().getColor());
				}
				 if((row >= boardRowsCount/2 + boardRowStart)) {
					spaces[col][row].setTeamTerritory(miniTest.Player2().getTeam());
					spaces[col][row].setTeamTerritoryColor(miniTest.Player2().getColor());
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
		
		//messagePanel.setPreferredSize(new Dimension(monitor_buffer.width, 100));

		/*
		String player = null;

		if(miniTest.getTurn() == 0) {
			 player = miniTest.Player1().getName();
				System.out.println("player1");
		}
		else if(miniTest.getTurn() == 1) {
			player = miniTest.Player2().getName();
			System.out.println("player2");
		}
		*/
		
		/*
		 * 		messagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		String player = miniTest.currentPlayer().getName();
		JLabel activePlayer = new JLabel(String.format("<html>Active Player:<br>%s</html>", player));
		activePlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
		statusPanel.add(activePlayer);
		statusPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		
		messagePanel.add(new Label("Help Messages + History"));		
		messagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		*/
		
		StatusLabel(statusPanel, miniTest.currentPlayer(), false);
		
		List<String> messages = new ArrayList<String>();	
		MessageLabel(messagePanel, messages, miniTest.currentPlayer().getName(), false);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
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
			private JPanel stack = new JPanel();
			
			private boolean engauged = false;
			private boolean engaugedPieceZone;
			
			private int X2 = -1;
			private int Y2 = -1;
			
			@Override
			public void mouseClicked(MouseEvent evt) {

				int mouseX = evt.getX();
				int mouseY = evt.getY();
				int X1 = mouseX/spaceSize;
				int Y1 = mouseY/spaceSize;

				
			if(pieces[X1][Y1] != null && pieces[X1][Y1].getSelected() == false) {	       	//there is a piece the piece was not selected already
					if(pieces[X1][Y1].getTeam() == miniTest.currentPlayer().getTeam()) {	//correct team piece has been selected
						if(spaces[X1][Y1].getZone() == OUTBOUNDS) {	 						//the piece is on current players rack
							pieces[X1][Y1].setSelected(true);
							engauged = true;
							engaugedPieceZone = OUTBOUNDS;					
							System.out.println("if");
							System.out.println("X1- " + X1 + "   X2- " + X2);
							System.out.println("Y1- " + Y1 + "   Y2- " + Y2);
							X2 = X1;
							Y2 = Y1;
							for(int row = boardRowStart; row < boardRowEnd; row++) {
								for(int col = boardColStart; col < boardColEnd; col++) {
									if(spaces[col][row].getTeamTerritory() == miniTest.currentPlayer().getTeam()) {
										spaces[col][row].setAddPiece(true);				//show spaces piece can be placed
									}
								}
							}
							board.revalidate();
							board.repaint();
							return;
						}
					}
					return; //incorrect teams piece was selected
				}
				else if(pieces[X1][Y1] == null) {			//new location selected is does not have a piece occupying it
					System.out.println("EPZ- " + engaugedPieceZone);
					if(engaugedPieceZone == INBOUNDS) {
						pieces[X1][Y1] = pieces[X2][Y2];	//move piece from starting location to new location
						pieces[X2][Y2] = null;				//remove piece from starting loaction
						X1 = -1;
						X2 = -1;
						Y1 = -1;
						Y2 = -1;
						engauged = false;
						board.revalidate();
						board.repaint();
						return;
					}
				
					else if(engaugedPieceZone == OUTBOUNDS){
						System.out.println("Y2- " + Y2);
						switch(Y2) {
							case RABBIT:
								if(miniTest.currentPlayer().getRabbitCount() > 0){
									miniTest.currentPlayer().removeRackPiece(RABBIT);
									JPanel playerPanel = passPlayerPanel(miniTest.currentPlayer(), p1panel, p2panel);
									RabbitLabel(miniTest.currentPlayer(), playerPanel, true);	
								}
								break;
							case SNAKE:
								if(miniTest.currentPlayer().getSnakeCount() > 0){
									miniTest.currentPlayer().removeRackPiece(SNAKE);
									JPanel playerPanel = passPlayerPanel(miniTest.currentPlayer(), p1panel, p2panel);
									SnakeLabel(miniTest.currentPlayer(), playerPanel, true);
									System.out.println("SNAKE: " + tempTeam);
								}
								break;
							case BIRD:
								if(miniTest.currentPlayer().getBirdCount() > 0){
									miniTest.currentPlayer().removeRackPiece(BIRD);
									JPanel playerPanel = passPlayerPanel(miniTest.currentPlayer(), p1panel, p2panel);
									BirdLabel(miniTest.currentPlayer(), playerPanel, true);
								}
								break;
							case GROUNDHOG:
								if(miniTest.currentPlayer().getGroundhogCount() > 0){
									miniTest.currentPlayer().removeRackPiece(GROUNDHOG);
									JPanel playerPanel = passPlayerPanel(miniTest.currentPlayer(), p1panel, p2panel);
									GroundhogLabel(miniTest.currentPlayer(), playerPanel, true);
								}
								break;
							case TURTLE:
								if(miniTest.currentPlayer().getTurtleCount() > 0) {
									miniTest.currentPlayer().removeRackPiece(TURTLE);
									JPanel playerPanel = passPlayerPanel(miniTest.currentPlayer(), p1panel, p2panel);
									TurtleLabel(miniTest.currentPlayer(), playerPanel, true);
								}
								break;
						}

						
						
						pieces[X2][Y2].toggleSelected();
						
						
						tempType = pieces[X2][Y2].getPieceType();
						tempTeam = pieces[X2][Y2].getTeam();
						System.out.println("p2,type: " + tempType);
						System.out.println("p2,team: " + tempTeam);
						
						//so piece can be highlighted
						Piece temp = pieces[X2][Y2].copyPiece();
						stack.add(temp);
						stack.add(spaces[X1][Y1]);
						board.remove(Y1*columns + X1);
						board.add(stack, Y1*columns + X1);
						

						
						
						
						X1 = -1;
						X2 = -1;
						Y1 = -1;
						Y2 = -1;
						engauged = false;
						board.revalidate();
						board.repaint();
						return;
					}
					
					
				}
				else if(pieces[X1][Y1].getSelected() == true) {
					System.out.println("else");
					System.out.println("X1- " + X1 + "   X2- " + X2);
					System.out.println("Y1- " + Y1 + "   Y2- " + Y2);
					
															//piece selected == true
					if(X1 == X2 && Y1 == Y2) {				//if selected piece has been clicked on again...
						pieces[X1][Y1].setSelected(false);	//deselect
						X1 = -1;
						X2 = -1;
						Y1 = -1;
						Y2 = -1;
						engauged = false;
						board.revalidate();
						board.repaint();
						return;
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

	
	
	
	public static JPanel passPlayerPanel(Player p, JPanel p1, JPanel p2) {
		JPanel playerPanel;
		if(p.getTeam() == 1) {
			playerPanel = p1;
		}
		else {
			playerPanel = p2;
		}
		return playerPanel;
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
	
	public static void StatusLabel(JPanel statusPanel, Player player, boolean remove){
		if(remove == true) {
			statusPanel.removeAll();
		}
		JLabel activePlayer = new JLabel(String.format("<html>Active Player:<br>%s</html>", player.getName()));
		activePlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
		statusPanel.add(activePlayer);
		statusPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
	}
	
	
	
	public static void MessageLabel(JPanel messagePanel, List<String> messages, String newMessage, boolean remove){
		if(remove == true) {
			messagePanel.removeAll();
		}
		if(messages.size() > 0) {
			messages.remove(messages.size()-1);
		}
		
		List<String> newMessages = new ArrayList<String>();
		newMessages.add(newMessage);
		messages.forEach(message -> newMessages.add(newMessage));
		
		StringBuilder labelString = (new StringBuilder()).append("<html>");
		newMessages.forEach(message -> labelString.append(message).append("<br>"));
		labelString.append("</html>");

		JLabel finalMessage = (new JLabel(String.format(labelString.toString())));
		messagePanel.add(finalMessage);		
	}
}



