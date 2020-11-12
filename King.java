
/**
 * Write a description of class King here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class King extends ChessPiece
{
    String name;
    Board b;
    public King(String newColor, Board theBoard){
        super("King",newColor,theBoard);
    }

    public King(String name, String newColor, Board theBoard){
        super(name,newColor,theBoard);
        this.name = name;
        color = newColor;
        b = theBoard;
        setGraphics("KingBlack.png","KingWhite.png");
    }

    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol){
        boolean retVal = false;
        String startColor = b.getPieceColor(startRow,startCol);
        String endColor = "EMPTY";
        if(b.hasPiece(endRow,endCol)){
            endColor = b.getPieceColor(endRow,endCol);
        }
        if(endColor != startColor || endColor == "EMPTY"){
            if((startRow+1==endRow)&&(startCol+1==endCol)||(startRow-1==endRow)&&(startCol-1==endCol)//diagonal east.
            ||(startRow+1==endRow)&&(startCol-1==endCol)||(startRow-1==endRow)&&(startCol+1==endCol)
            ||(startRow+1==endRow)&&(startCol == endCol)||(startRow-1==endRow)&&(startCol == endCol)
            ||(startCol+1==endCol)&&(startRow == endRow)||(startCol-1==endCol)&&(startRow == endRow))
            {
                retVal=true;
            }
        }
        return retVal;
    }
}
