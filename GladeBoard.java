package GameCode;
import Interface;
//Get classes and methods from Space class
import java.util.Random;
// setting up random number generator for special spaces
public class Board {
    //Creating board
    private Space[][] space= new Space[8][8];

    public Board(){
            for(int i=0;i<space.length;i++){}=
                for(int o=0; o<space.length; o++){
                    this.space[i][j]=new Space(i,o);
                }
            }   
    }
    //Making method for Special spaces
    public SpecialSpace(Board){
        for(int e=0;e<rand.nextInt(8);e++){
        int xaxis= rand.nextInt(9);
        int yaxis= rand.nextInt(9);
        this.space[xaxis][yaxis]=rand.nextInt(2);
        Spacetype(this.space[xaxis][yaxis]);
        }

    }
    //Getting current space for piece to determine if occupied and other features of space
    public Space getSpace(int x, int y) {
            return space[x][y];
    }
}
