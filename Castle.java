
/**
 * Write a description of class Castle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Castle extends ChessPiece
{
   public Castle(String newColor, Board theBoard){
        super("Castle",newColor,theBoard);
    }
   public Castle(String name, String newColor, Board theBoard){
        super(name,newColor,theBoard);
        setGraphics("CastleBlack.png","CastleWhite.png");
    }
}
