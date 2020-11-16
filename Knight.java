
/**
 * Write a description of class Knight here.
 *
 * @author (Sanskar Raval)
 * @version (2020-11-15)
 */
public class Knight extends ChessPiece
{
    private String name;// Private string name.
    private Board b;// Pravate reference to the board b
    public Knight(String newColor, Board theBoard){
        super("Knight",newColor,theBoard);
    }

    public Knight(String name, String newColor, Board theBoard){
        super(name,newColor,theBoard);// Calling the constructor form the parent class.
        this.name = name;
        color = newColor;
        b = theBoard;
        setGraphics("KnightBlack.png","KnightWhite.png");
    }

    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol){
        boolean retVal = false;// Temporary variable
        String startColor = b.getPieceColor(startRow,startCol);
        String endColor = "EMPTY";
        // Checks if the color is same or not.
        // If the endColor is not same, then it captures it.
        // If the endColor is empty, it will move to the assigned tile.
        if(b.hasPiece(endRow,endCol)){
            endColor = b.getPieceColor(endRow,endCol);
        }
        if(endColor != startColor || endColor == "EMPTY"){
            if((startRow+2==endRow)&&(startCol-1==endCol)||(startRow+2==endRow)&&(startCol+1==endCol)){// 2 vertical and 1 horizontal space upward
                if(b.hasPiece(endRow,endCol)&& endColor == startColor){
                        retVal = false;                      
                    }
                    else{
                        retVal = true;
                    }
            }
            if((startRow+1==endRow)&&(startCol-2==endCol)||(startRow+1==endRow)&&(startCol+2==endCol)){// 1 vertical and 2 horizontal space upward
                if(b.hasPiece(endRow,endCol)&& endColor == startColor){
                        retVal = false;                      
                    }
                    else{
                        retVal = true;
                    }
            }
            if((startRow-1==endRow)&&(startCol-2==endCol)||(startRow-1==endRow)&&(startCol+2==endCol)){// 1 vertical and 2 horizontal space downward
                if(b.hasPiece(endRow,endCol)&& endColor == startColor){
                        retVal = false;                      
                    }
                    else{
                        retVal = true;
                    }
            }
            if((startRow-2==endRow)&&(startCol-1==endCol)||(startRow-2==endRow)&&(startCol+1==endCol)){//2 vertical and 1 horizontal space downward.
                if(b.hasPiece(endRow,endCol)&& endColor == startColor){
                        retVal = false;                      
                    }
                    else{
                        retVal = true;
                    }
            }
        }
        return retVal;
    }
}
