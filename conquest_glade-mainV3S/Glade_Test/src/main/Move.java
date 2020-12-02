package main;

public class Move {

	int Up;
	int Down;
	int Left;
	int Right;
	
	Move() {
		this.Up = 0;
		this.Down = 0;
		this.Left = 0;
		this.Right = 0;
	}

	
	
	public void F1(char playerTerritory) {
		switch(playerTerritory) {
			case 'T':
				this.Up = 0;
				this.Down = 1;
				this.Left = 0;
				this.Right = 0;
				break;
			case 'B':
				this.Up = 1;
				this.Down = 0;
				this.Left = 0;
				this.Right = 0;
				break;
			case 'L':
				this.Up = 0;
				this.Down = 0;
				this.Left = 0;
				this.Right = 1;
				break;
			case 'R':
				this.Up = 0;
				this.Down = 0;
				this.Left = 1;
				this.Right = 0;
				break;				
		}
	}
	public void B1(char playerTerritory) {
		switch(playerTerritory) {
			case 'T':
				this.Up = 1;
				this.Down = 0;
				this.Left = 0;
				this.Right = 0;
				break;
			case 'B':
				this.Up = 0;
				this.Down = 1;
				this.Left = 0;
				this.Right = 0;
				break;
			case 'L':
				this.Up = 0;
				this.Down = 0;
				this.Left = 1;
				this.Right = 0;
				break;
			case 'R':
				this.Up = 0;
				this.Down = 0;
				this.Left = 0;
				this.Right = 1;
				break;				
		}
	}
	public void R1(char playerTerritory) {
		switch(playerTerritory) {
			case 'T':
				this.Up = 0;
				this.Down = 0;
				this.Left = 1;
				this.Right = 0;
				break;
			case 'B':
				this.Up = 0;
				this.Down = 0;
				this.Left = 0;
				this.Right = 1;
				break;
			case 'L':
				this.Up = 0;
				this.Down = 1;
				this.Left = 0;
				this.Right = 0;
				break;
			case 'R':
				this.Up = 1;
				this.Down = 0;
				this.Left = 0;
				this.Right = 0;
				break;				
		}
	}
	public void L1(char playerTerritory) {
		switch(playerTerritory) {
			case 'T':
				this.Up = 0;
				this.Down = 0;
				this.Left = 0;
				this.Right = 1;
				break;
			case 'B':
				this.Up = 0;
				this.Down = 0;
				this.Left = 1;
				this.Right = 0;
				break;
			case 'L':
				this.Up = 1;
				this.Down = 0;
				this.Left = 0;
				this.Right = 0;
				break;
			case 'R':
				this.Up = 0;
				this.Down = 1;
				this.Left = 0;
				this.Right = 0;
				break;				
		}
	}
	public void FL1(char playerTerritory) {
		switch(playerTerritory) {
			case 'T':
				setUp(0);
				setDown(1);
				setLeft(0);
				setRight(1);
				break;
			case 'B':
				setUp(1);
				setDown(0);
				setLeft(1);
				setRight(0);
				break;
			case 'L':
				this.Up = 1;
				this.Down = 0;
				this.Left = 0;
				this.Right = 1;
				break;
			case 'R':
				this.Up = 0;
				this.Down = 1;
				this.Left = 1;
				this.Right = 0;
				break;				
		}
	}	
	public void FR1(char playerTerritory) {
		switch(playerTerritory) {
			case 'T':
				setUp(0);
				setDown(1);
				setLeft(1);
				setRight(0);
				break;
			case 'B':
				setUp(1);
				setDown(0);
				setLeft(0);
				setRight(1);
				break;
			case 'L':
				this.Up = 0;
				this.Down = 1;
				this.Left = 0;
				this.Right = 1;
				break;
			case 'R':
				this.Up = 1;
				this.Down = 0;
				this.Left = 1;
				this.Right = 0;
				break;				
		}
	}
	public void F2(char playerTerritory) {
		switch(playerTerritory) {
			case 'T':
				this.Up = 0;
				this.Down = 2;
				this.Left = 0;
				this.Right = 0;
				break;
			case 'B':
				this.Up = 2;
				this.Down = 0;
				this.Left = 0;
				this.Right = 0;
				break;
			case 'L':
				this.Up = 0;
				this.Down = 0;
				this.Left = 0;
				this.Right = 2;
				break;
			case 'R':
				this.Up = 0;
				this.Down = 0;
				this.Left = 2;
				this.Right = 0;
				break;				
		}
	}
	
	public int getUp(){
		return this.Up;
	}
	public int getDown() {
		return this.Down;
	}
	public int getLeft() {
		return this.Left;
	}
	public int getRight() {
		return this.Right;
	}
	public void setUp(int up){
		this.Up = up;
	}
	public void setDown(int down) {
		this.Down = down;
	}
	public void setLeft(int left) {
		this.Left = left;
	}
	public void setRight(int right) {
		this.Right = right;
	}
}
