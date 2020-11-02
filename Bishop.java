
/**
 * Write a description of class Bishop here.
 *
 * @author (your name)
 * @version (a version number or a date)
 
 */
public class Bishop extends ChessPiece
{
    public Bishop(String newColor, Board theBoard){
        super("Bishop",newColor,theBoard);
    }
    public Bishop(String name, String newColor, Board theBoard){
        super(name,newColor,theBoard);
        setGraphics("BishopBlack.png","BishopWhite.png");
    }
    
}
