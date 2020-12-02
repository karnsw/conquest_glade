import Space.java;

//Get classes and methods from Space class
import java.util.Random;
// setting up random number generator for special spaces
public class GladeBoard {
    //Creating board
    Space[][] space= new Space[8][8];
    public int j;
    public int o;
    public Random rand= new Random;
    public GladeBoard(){
            for(int i=0;i<space.length;i++){
                for(int o=0; o<space.length; o++){
                    this.space[i][o]=
                }
            }  
            SpecialSpace(); 
    }
    //Making method for Special spaces
    public Space SpecialSpace(){
        for(int e=0;e<rand.nextInt(8);e++){
        int xaxis= rand.nextInt(9);
        int yaxis= rand.nextInt(9);
        int space[xaxis][yaxis]=rand.nextInt(2);
        Spacetype(this.space[xaxis][yaxis]);
        }

    }
    //Getting current space for piece to determine if occupied and other features of space
    public Space getSpace(int x, int y) {
            return space[x][y];
    }
}
