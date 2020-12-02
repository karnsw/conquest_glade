package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Number extends JPanel{
	

	private int _num;

	 Number(int num){
			this._num = num;
	}
		
		
		
		
	public void paintComponent(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, this.getWidth(), this.getHeight());
		g.drawRect(1, 1, this.getWidth()-1, this.getHeight()-1);
		
		Font glade = new Font(Font.SERIF, Font.BOLD, this.getHeight());

				
				
				switch(this._num) {
				case 0:
					g.setColor(Color.WHITE);
					g.setFont(glade);
					g.drawString("0", this.getWidth(), this.getHeight());
					break;
				case 1:
					g.setColor(Color.WHITE);
					g.setFont(glade);
					g.drawString("1", this.getWidth(), this.getHeight());
					break;
				case 2:
					g.setColor(Color.WHITE);
					g.setFont(glade);
					g.drawString("2", this.getWidth(), this.getHeight());
					break;
				case 3:
					g.setColor(Color.WHITE);
					g.setFont(glade);
					g.drawString("3", this.getWidth()/3, (this.getHeight()*5)/6);
					break;
				case 4:
					g.setColor(Color.WHITE);
					g.setFont(glade);
					g.drawString("4", this.getWidth(), this.getHeight());
					break;
				case 5:
					g.setColor(Color.WHITE);
					g.setFont(glade);
					g.drawString("5", this.getWidth(), this.getHeight());
					break;
				}
			}
}
