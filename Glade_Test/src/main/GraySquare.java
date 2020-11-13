package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GraySquare extends JPanel{
	
	
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
			
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, dim, dim);
		}
	}

