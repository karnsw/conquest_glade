package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import org.w3c.dom.events.MouseEvent;

public class Board extends JPanel implements MouseListener, MouseMotionListener{

	
	int board_x = 3;
	int board_y = 3;
	int SpaceSize = 100;
	int mouseData[][] = new int[board_x][board_y]; 
	
	
	
	
	Board() {
        setBackground(Color.BLACK);
        addMouseListener(this);
	}
	
	
	public void mouseMoved(MouseEvent evt) {
	}
	
	public void paintComponent(Graphics g){
	}
	
	
	
	
	
	
	
	
	@Override
	public void mouseDragged(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
