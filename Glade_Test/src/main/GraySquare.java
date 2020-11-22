package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class GraySquare extends JPanel{
	
		private int _color;
		private int _minDim;
		private int _mouseHere;
		private int _pieceHere;
		
		GraySquare(int color, int minDim, int mouseHere, int pieceHere){
			this._color = color;
			this._minDim = minDim;
			this._mouseHere = mouseHere;
			this._pieceHere = pieceHere;
		}
		
		GraySquare(int color, int minDim){
			this._color = color;
			this._minDim = minDim;
		}
	
		
		public int get_color() {
			return this._color;
		}
		public int get_minDim() {
			return this._minDim;
		}
		public int get_mouseHere() {
			return this._mouseHere;
		}
		public int get_pieceHere() {
			return this._pieceHere;
		}
		
	
		public void paintComponent(Graphics g) {
				
				super.paintComponent(g);
				int width = getWidth();
				int height = getHeight();
				int dim = _minDim;
				if(width > _minDim || height > _minDim) {
					if(width > height) {
						dim = width;
					}
					else {
						dim = height;
					}
				}
				
				System.out.println("width- " + width);
				System.out.println("height- " + height);
				
				if(this._mouseHere == 0) {
					switch(this._color) {
					case 0:
						g.setColor(Color.LIGHT_GRAY);
						break;
					case 1:
						g.setColor(Color.GRAY);
						break;
					case 2:
						g.setColor(Color.BLACK);
						break;
					}
				}
				else {
					g.setColor(Color.YELLOW);
				}
	
				g.fillRect(0, 0, dim, dim);
				
				
				if(this._pieceHere != 0) {
					Font glade = new Font(Font.SERIF, Font.BOLD, dim);
					
					switch(this._pieceHere) {
					case 1:
						g.setColor(Color.BLUE);
						g.fillOval(dim/10, dim/10, dim-(dim/5), dim-(dim/5));
						
						g.setColor(Color.WHITE);
						g.setFont(glade);
						g.drawString("G", (dim/5), dim-(dim/5));
						break;
					case 2:
						g.setColor(Color.BLUE);
						g.fillOval(dim/10, dim/10, dim-(dim/5), dim-(dim/5));
						
						g.setColor(Color.WHITE);
						g.setFont(glade);
						g.drawString("B", (dim/5), dim-(dim/5));
						break;
					case 3:
						g.setColor(Color.BLUE);
						g.fillOval(dim/10, dim/10, dim-(dim/5), dim-(dim/5));
						
						g.setColor(Color.WHITE);
						g.setFont(glade);
						g.drawString("S", (dim/5), dim-(dim/5));
						break;
					case 4:
						g.setColor(Color.BLUE);
						g.fillOval(dim/10, dim/10, dim-(dim/5), dim-(dim/5));
						
						g.setColor(Color.WHITE);
						g.setFont(glade);
						g.drawString("R", (dim/5), dim-(dim/5));
						break;
					
					
					case 5:
						g.setColor(Color.RED);
						g.fillOval(dim/10, dim/10, dim-(dim/5), dim-(dim/5));
						
						g.setColor(Color.WHITE);
						g.setFont(glade);
						g.drawString("G", (dim/5), dim-(dim/5));
						break;
					case 6:
						g.setColor(Color.RED);
						g.fillOval(dim/10, dim/10, dim-(dim/5), dim-(dim/5));
						
						g.setColor(Color.WHITE);
						g.setFont(glade);
						g.drawString("B", (dim/5), dim-(dim/5));
						break;
					case 7:
						g.setColor(Color.RED);
						g.fillOval(dim/10, dim/10, dim-(dim/5), dim-(dim/5));
						
						g.setColor(Color.WHITE);
						g.setFont(glade);
						g.drawString("S", (dim/5), dim-(dim/5));
						break;
					case 8:
						g.setColor(Color.RED);
						g.fillOval(dim/10, dim/10, dim-(dim/5), dim-(dim/5));
						
						g.setColor(Color.WHITE);
						g.setFont(glade);
						g.drawString("R", (dim/5), dim-(dim/5));
						break;
					
				}
	
				}
			}
		}
	

