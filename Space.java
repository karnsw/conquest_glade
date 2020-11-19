package GladeBoard:
//Link with game board
import java.util.Random;

public class Space {

    int x;
    int y;
    //current space
    public Space( int x, int y) {
        this.x = x;
        this.y=y;
    
    }
    //Special space functionality
    public Spacetype(int x){
        int freeze=0;
        int fire=1;
        int wind=2;
        //May make these offensive abilities instead of trap spaces
        if(x==freeze)
        click(piece){
            for(round=2;round>rand.nextInt(2);round--)
            piece = Space;
        }
        //Freezes a piece for 1 or 2 turns
        else if(x==fire)
        {
            if(OppPiece=piece[x-1][y-1]||OppPiece=piece[x-1][y]||OppPiece=piece[x-1][y+1]|OppPiece=piece[x][y-1]||OppPiece=piece[x][y+1]||OppPiece=piece[x+1][y-1]||OppPiece=piece[x+1][y]||OppPiece=piece[x-1][y+1])
            {
            click(OppPiece);
            clearSpace(OppPiece);
            }
        }
        //Allows a piece to take a piece in any surrounding space

        else
        click(OppPiece){
            if(space=OppPiece[x+1][y]||space=OppPiece[x-1][y]||space=OppPiece[x-1][y+1]|space=OppPiece[x][y-1])
            click(space){
               OppPiece=space; 
            }
        }
        //Shifts a piece in a direction the player chooses.

    }
    public void SpaceOccupy(Piece piece){
        if(this.piece != null);
            this.piece.MakeAvailable(false);

            this.piece=OppPiece;
    }
    // Function to determine if space is occupied.
    public boolean Occupied(){
        if(piece != null)
            return true;
        else
            return false;
    }
    // Used to clear space when piece is killed
    public piece clearSpace(){
        piece killPiece= this.piece;
        this.piece=null;
        return killPiece;
    }


}