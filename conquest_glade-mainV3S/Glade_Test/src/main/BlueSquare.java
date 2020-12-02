package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BlueSquare extends JPanel{
	
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

		g.setColor(Color.BLUE);
		g.fillRect(0, 0, dim, dim);
		

	}
}
