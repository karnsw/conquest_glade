package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BlueSquare extends JPanel{
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		int dim = 50;
		if(width > 50 || height > 50) {
			if(width > height) {
				dim = width;
			}
			else {
				dim = height;
			}
		}
		//System.out.println("width " + width);
		//System.out.println("height " + height + "\n");
		
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, dim, dim);
	}
}
