import *.java;
java.lang.Math.abs();
public class Player{
    //Array for piece rack not sure how many pieces we're going to have.
    Rack[] rack= new Rack[];
    //Giving Player values for each piece.
    int Rabbit= 4;
    int turtle= 3;
    int bird = 2;
    int Hound= 1;
    //Givng opponent pieces values for the board.
    int OppRabbit=-4;
    int Oppturtle=-3;
    int Oppbird=-2;
    int OppHound=-1;
    public boolean ValidMove()
    {
        //create move validator
    }
    public turn()
    {
        //make all player pieces equal opponent pieces utilizing pseudo board
        for(i=0;i<9;i++)
        {
            for(j=0;j<9;j++)
            {
                if(space[i][j]!=0)
                    {
                    if(space[i][j]<0)
                        {
                            //Flip opponents to Player piece
                            int pseudo=space[i][j];
                            //utilize absolute value to make piece positive very of piece value
                            space[i][j]=Math.abs(space[i][j]);
                        }
                    else
                        {
                            //Flip Player piece to opponent's
                            int pseudo=space[i][j];
                            //utilize value - value*2 to reach negative version of piece
                            space[i][j]=space[i][j]-(space[i][j]*2);
                        }

                    }
            }
        }
    }
    
}