
/**
 * Write a description of class King here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class King extends ChessPiece
{
    public King(String newColor, Board theBoard){
        super("King",newColor,theBoard);
    }
    public King(String name, String newColor, Board theBoard){
        super(name,newColor,theBoard);
        setGraphics("KingBlack.png","KingWhite.png");
    }
   
}
