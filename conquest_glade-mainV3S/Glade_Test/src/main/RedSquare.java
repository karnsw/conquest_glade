package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class RedSquare extends JPanel{
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		int dim = 100;
		if(width > dim || height > dim) {
			if(width > height) {
				dim = width;
			}
			else {
				dim = height;
			}
		}
		//System.out.println("width " + width);
		//System.out.println("height " + height + "\n");
		
		g.setColor(Color.RED);
		g.fillRect(0, 0, dim, dim);
	}
}
