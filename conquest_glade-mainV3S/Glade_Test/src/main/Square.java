package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class Square extends JPanel{
	
	
		private int _color;
		private int _minDim;
		private int _mouseHere;
		private int _pieceHere;
		private int _type;
		
		Square(int color, int minDim, int mouseHere, int pieceHere){
			this._color = color;
			this._minDim = minDim;
			this._mouseHere = mouseHere;
			this._pieceHere = pieceHere;
		}

		Square(int color, int mouseHere, int pieceHere){
			this._color = color;
			//this._minDim = minDim;
			this._mouseHere = mouseHere;
			this._pieceHere = pieceHere;
		}
		
		Square(int color, int minDim){
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
		
		public void toggleMouseHere() {
			if(this._mouseHere == 0) {
				this._mouseHere = 1;
			}
			else {
				this._mouseHere = 0;
			}
		}

		public void togglePieceHere() {
			if(this._pieceHere == 0) {
				this._pieceHere = 1;
			}
			else {
				this._pieceHere = 0;
			}
		}
		
		
		// 0 = active game square
		// 1 = edge
		// 2 = Out of Bounds
		// 3 = player 1 rack
		// 4 = player 2 rack
		public void setType(int type) {
			this._type = type;
		}
		public int getType() {
			return this._type;
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
				
				//System.out.println("SpaceWidth- " + width);
			//	System.out.println("SpaceHeight- " + height);
				
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
					case 3:
						g.setColor(Color.CYAN);
						break;
					}
				}
				else {
					g.setColor(Color.YELLOW);
				}
				
				if(this._pieceHere == 1) {
					g.setColor(Color.ORANGE);
				}
	
				g.fillRect(0, 0, dim, dim);
				

	
				}
			}
		
	

