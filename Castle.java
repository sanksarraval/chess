
/**
 * Write a description of class Castle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Castle extends ChessPiece
{
    String name;// Private string name.
    Board b;// Pravate reference to the board b
    public Castle(String newColor, Board theBoard){
        super("Castle",newColor,theBoard);
    }

    public Castle(String name, String newColor, Board theBoard){
        super(name,newColor,theBoard);// Calling the constructor form the parent class.
        this.name = name;
        color = newColor;
        b = theBoard;
        setGraphics("CastleBlack.png","CastleWhite.png");
    }

    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol){
        boolean retVal = false;
        String startColor = b.getPieceColor(startRow,startCol);
        String endColor = "EMPTY";
        // Assigns the color of the Piece to endColor.
        if(b.hasPiece(endRow,endCol)){
            endColor = b.getPieceColor(endRow,endCol);
        }
        // Checks if the color is same or not.
        // If the endColor is not same, then it captures it.
        // If the endColor is empty, it will move to the assigned tile.
         if(endColor != startColor || endColor == "EMPTY"){
            for(int i = 1;i<b.BOARD_SIZE;i++){ // for loop to iterate through 1 to 7
                if((startRow-i==endRow)&&(startCol==endCol)||(startRow+i==endRow)&&(startCol==endCol)){ // Downward Movement and Upward Movement
                    if(b.hasPiece(endRow,endCol)&& startColor == endColor){ // Checks for the color equality. If equal then it won't capture.
                        retVal = false;
                        break; // Breaks from the for loop.(Hopefully)
                    }
                    else{
                        retVal = true;
                    }
                }
                if((startRow==endRow)&&(startCol-i==endCol)||(startRow==endRow)&&(startCol+i==endCol)){ // Right and Left Movement.
                    if(b.hasPiece(endRow,endCol)&& startColor == endColor){ //Checks for the color equality. If equal then it won't capture.
                        retVal = false;
                        break; // Breaks from the for loop.(Hopefully)
                    }
                    else{
                        retVal = true;
                    }
                }
            }
        }
        return retVal;
    }
}
