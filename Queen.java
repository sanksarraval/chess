
/**
 * Write a description of class Queen here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Queen extends ChessPiece
{
    String name;// Private string name.
    Board b;// Pravate reference to the board b
    public Queen(String newColor, Board theBoard){
        super("Queen",newColor,theBoard);
    }

    public Queen(String name, String newColor, Board theBoard){
        super(name,newColor,theBoard);// Calling the constructor form the parent class.
        this.name = name;
        color = newColor;
        b = theBoard;
        setGraphics("QueenBlack.png","QueenWhite.png");
    }

    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol){
        boolean retVal = false;
        String startColor = b.getPieceColor(startRow,startCol);
        String endColor = "EMPTY";
        // Assigns the color of the Piece to endColor.
        if(b.hasPiece(endRow,endCol)){
            endColor = b.getPieceColor(endRow,endCol);
        }
        // Checks is the color is same of not.
        // If the endColor is not same, then it captures it.
        // If the endColor is empty, it will move to the assigned tile.
        // Queen is the combination of Bishop and Castle.
        if(endColor != startColor || endColor == "EMPTY"){
            for(int i = 1;i<b.BOARD_SIZE;i++){// for loop to iterate through 1 to 7
                if((startRow+i==endRow)&&(startCol+i==endCol)||(startRow+i==endRow)&&(startCol-i==endCol)){//Upward Diagonal movement
                    if(b.hasPiece(endRow,endCol)&& startColor == endColor){
                        retVal = false;
                        break;
                    }
                    else{
                        retVal = true;
                    }
                }
                if((startRow-i==endRow)&&(startCol-i==endCol)||(startRow-i==endRow)&&(startCol+i==endCol)){// downward Diagonal movement
                    if(b.hasPiece(endRow,endCol)&& startColor == endColor){
                        retVal = false;
                        break;
                    }
                    else{
                        retVal = true;
                    }
                }
                if((startRow-i==endRow)&&(startCol==endCol)||(startRow+i==endRow)&&(startCol==endCol)){// upward straight movement 
                    if(b.hasPiece(endRow,endCol)&& startColor == endColor){
                        retVal = false;
                        break;
                    }
                    else{
                        retVal = true;
                    }
                }
                if((startRow==endRow)&&(startCol-i==endCol)||(startRow==endRow)&&(startCol+i==endCol)){// Downward straight Movement.
                    if(b.hasPiece(endRow,endCol)&& startColor == endColor){
                        retVal = false;
                        break;
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
