
/**
 * Write a description of class Pawn here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Pawn extends ChessPiece
{
  public Pawn(String newColor, Board theBoard){
        super("Pawn",newColor,theBoard);
    }
   public Pawn(String name, String newColor, Board theBoard){
        super(name,newColor,theBoard);
        setGraphics("PawnBlack.png","PawnWhite.png");
    }
}
