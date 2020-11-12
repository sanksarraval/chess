
/**
 * Write a description of class Castle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Castle extends ChessPiece
{
    String name;
    Board b;
    public Castle(String newColor, Board theBoard){
        super("Castle",newColor,theBoard);
    }

    public Castle(String name, String newColor, Board theBoard){
        super(name,newColor,theBoard);
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
        // Checks is the color is same of not.
        // If the endColor is not same, then it captures it.
        // If the endColor is empty, it will move to the assigned tile.
        if(endColor != startColor || endColor == "EMPTY"){
            for(int i = 1;i<b.BOARD_SIZE;i++){
                if((startRow-i==endRow)&&(startCol==endCol)||(startRow+i==endRow)&&(startCol==endCol)){
                    if(b.hasPiece(endRow,endCol)){
                        retVal = false;
                        break;
                    }
                    else{
                        retVal = true;
                    }
                }
                if((startRow==endRow)&&(startCol-i==endCol)||(startRow==endRow)&&(startCol+i==endCol)){
                    if(b.hasPiece(endRow,endCol)){
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
