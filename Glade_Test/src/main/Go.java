package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Go {

	public static void main(String[] args) {

		GameManger karns = new GameManger();
		
		Dimension monitor = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
		int monitor_x = monitor.width;
		int monitor_y = monitor.height;
		Dimension monitor_buffer = new Dimension(monitor_x - 300, monitor_y - 300); 
		
		
		int board_x = 8;
		int board_y = 8;
		int space_size = 50;
		
		//if(board_x * space_size > )
		
		JPanel game = new JPanel();
		game.setMinimumSize(new Dimension((50*board_x), (50*board_y)));
		game.setLayout(new BorderLayout(10,10));

		
		JPanel board = new JPanel();
		board.setMinimumSize(new Dimension((50*board_x), (50*board_y)));
		board.setLayout(new GridLayout(board_x,board_y));
		
		for(int i = 0; i < board_x; i++) {
			for(int j = 0; j < board_y; j++) {
				if(i%2 == j%2 || (i+1)%2 == (j+1)%2) {
					board.add(new GraySquare());
					//board.add(new SquareSpace(50, 1, karns).draw());
				}
				else {
					board.add(new BlueSquare());
				}
			}
		}

		game.add(board, BorderLayout.CENTER);
		
		JFrame window = new JFrame("Glade");
		window.setMaximumSize(monitor_buffer);
		window.setContentPane(game);
		window.setLocation(100, 100);
		window.setVisible(true);
		
		window.addMouseMotionListener(new MouseMotionListener(){
			public void mouseDragged(MouseEvent evt) {
			}

			public void mouseMoved(MouseEvent evt) {
				int mouse_x = evt.getX();
				int mouse_y = evt.getY();
				System.out.println("Spaces- " + karns.get_spaceCount());
				System.out.println("X- " + mouse_x);
				System.out.println("Y- " + mouse_y);
				System.out.println();
			}
		});
		
		window.addComponentListener(new ComponentAdapter(){ 
			public void componentResized(ComponentEvent evt) {
				
				Dimension d = window.getSize();
				if(d.height != d.width) {
					if(d.height > d.width) {
						d.width = d.height;
					}
					else {
						d.height = d.width;
					}
				}
				System.out.println("Size- " + board.getSize());
				
				
					
				
				window.setSize(d);
				
				
				/*
				Dimension d = window.getSize();
				System.out.println("Size- " + d);
				Dimension minD = window.getMinimumSize();
				System.out.println("minSize- " + minD + "\n");
				
				if(d.width < minD.width) {
					d.width = minD.width;
				}
				if(d.height < minD.height) {
					d.height = minD.height;
				}
				window.setSize(d);
				*/
			}
		});
	}
}
