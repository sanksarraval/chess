
/**
 * Write a description of class Pawn here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Pawn extends ChessPiece
{
    private String name;// Private string name.
    private Board b;// Pravate reference to the board b
    public Pawn(String newColor, Board theBoard){
        super("Pawn",newColor,theBoard);
    }

    public Pawn(String name, String newColor, Board theBoard){
        super(name,newColor,theBoard);// Calling the constructor form the parent class.
        this.name = name;
        color = newColor;
        b = theBoard;
        setGraphics("PawnBlack.png","PawnWhite.png");
    }

    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol){
        boolean retVal = false;
        String startColor = b.getPieceColor(startRow,startCol);
        String endColor = "EMPTY";
        // Only moves forward if there is no piece in the way.(BLACK PIECE)
        if(b.hasPiece(endRow,endCol) == false && startColor == "BLACK"){
            if((startRow+1==endRow)&&(startCol==endCol)){
                retVal=true;
            }
        }
        //Only moves forward if there is no piece in the way.(WHITE PIECE)
        if(b.hasPiece(endRow,endCol) == false && startColor == "WHITE"){
            if((startRow-1==endRow)&&(startCol==endCol)){
                retVal=true;
            }
        }
        //Capture move
        if(b.hasPiece(endRow,endCol)){ // checks for a piece in the way.
            endColor = b.getPieceColor(endRow,endCol); // Assign it to the endColor
            if(startColor == "BLACK"){ //  Capture Move for the Black Piece
                if(startColor != endColor){
                    if((startRow+1==endRow)&&(startCol+1==endCol)||(startRow+1==endRow)&&(startCol-1==endCol)){
                        retVal = true;
                    }
                }
            }
            if(startColor == "WHITE"){ // Capture move for the White Piece
                if(startColor != endColor){
                    if((startRow-1==endRow)&&(startCol+1==endCol)||(startRow-1==endRow)&&(startCol-1==endCol)){
                        retVal = true;
                    }
                }
            }
        }
        return retVal;
    }
}
