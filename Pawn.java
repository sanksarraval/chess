
/**
 * Write a description of class Pawn here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Pawn extends ChessPiece
{
    String name;
    Board b;
    public Pawn(String newColor, Board theBoard){
        super("Pawn",newColor,theBoard);
    }

    public Pawn(String name, String newColor, Board theBoard){
        super(name,newColor,theBoard);
        this.name = name;
        color = newColor;
        b = theBoard;
        setGraphics("PawnBlack.png","PawnWhite.png");
    }
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol){
        boolean retVal = false;
        String startColor = b.getPieceColor(startRow,startCol);
        String endColor = "EMPTY";
        // Only moves forward if there is no piece in the way.
        if(b.hasPiece(endRow,endCol) == false){
            if((startRow+1==endRow)&&(startCol==endCol)){
            retVal=true;
            }
        }
        //Capture move
        if(b.hasPiece(endRow,endCol)){
            endColor = b.getPieceColor(endRow,endCol);
            if(startColor != endColor){
                if((startRow+1==endRow)&&(startCol+1==endCol)||(startRow+1==endRow)&&(startCol-1==endCol)){
                    retVal = true;
                }
            }
            else{
                System.out.println("Invalid Move");
            }
        }
        return retVal;
    }
}
