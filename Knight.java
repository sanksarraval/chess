
/**
 * Write a description of class Knight here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Knight extends ChessPiece
{
    String name;
    Board b;
    public Knight(String newColor, Board theBoard){
        super("Knight",newColor,theBoard);
    }

    public Knight(String name, String newColor, Board theBoard){
        super(name,newColor,theBoard);
        this.name = name;
        color = newColor;
        b = theBoard;
        setGraphics("KnightBlack.png","KnightWhite.png");
    }

    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol){
        boolean retVal = false;
        String startColor = b.getPieceColor(startRow,startCol);
        String endColor = "EMPTY";
        if(b.hasPiece(endRow,endCol)){
            endColor = b.getPieceColor(endRow,endCol);
        }
        if(endColor != startColor || endColor == "EMPTY"){
            if((startRow+2==endRow)&&(startCol-1==endCol)||(startRow+2==endRow)&&(startCol+1==endCol)){
                if(b.hasPiece(endRow,endCol)&& endColor == startColor){
                        retVal = false;                      
                    }
                    else{
                        retVal = true;
                    }
            }
            if((startRow+1==endRow)&&(startCol-2==endCol)||(startRow+1==endRow)&&(startCol+2==endCol)){
                if(b.hasPiece(endRow,endCol)&& endColor == startColor){
                        retVal = false;                      
                    }
                    else{
                        retVal = true;
                    }
            }
            if((startRow-1==endRow)&&(startCol-2==endCol)||(startRow-1==endRow)&&(startCol+2==endCol)){
                if(b.hasPiece(endRow,endCol)&& endColor == startColor){
                        retVal = false;                      
                    }
                    else{
                        retVal = true;
                    }
            }
            if((startRow-2==endRow)&&(startCol-1==endCol)||(startRow-2==endRow)&&(startCol+1==endCol)){
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
