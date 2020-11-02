
/**
 * Write a description of class Queen here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Queen extends ChessPiece
{
    public Queen(String newColor, Board theBoard){
        super("Queen",newColor,theBoard);
    }
    public Queen(String name, String newColor, Board theBoard){
        super(name,newColor,theBoard);
        setGraphics("QueenBlack.png","QueenWhite.png");
    }
}
