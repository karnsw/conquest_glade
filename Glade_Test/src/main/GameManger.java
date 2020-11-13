package main;

public class GameManger {

	private int spaceCount;
	
	GameManger(){
		this.spaceCount = 0;
	}
	
	public int get_spaceCount(){
			return this.spaceCount;
		}
	public void inc_spaceCount() {
			this.spaceCount = spaceCount++;
		}
	}

