import java.util.ArrayList;

// This is the parent class for all ChessPieces. 
public abstract class ChessPiece{

	// KING, QUEEN, BISHOP, ROOK, KNIGHT, PAWN
	private String type = "Generic Chess Piece";

	// "BLACK" or "WHITE"
	protected String color = "UNKNOWN";

	// File graphic references. 
	private String graphicBlack = "chess-board-and-pieces.jpg";
	private String graphicWhite = "chess-board-and-pieces.jpg";

	// a reference to the current board (needed for many methods)
	private Board boardRef;

	// Will enable additional debugging output. 
	protected final boolean DEBUG_MODE = false;

	// Base constructor. 
	// type is the piece type 
	// color is "BLACK" or "WHITE"
	// theBoard is the current board in use. 
	public ChessPiece(String type, String color, Board theBoard){
		this.type = type;
		this.color = color;
		boardRef = theBoard;
	}

	// --- Accessors ---
	// Over ride to return PAWN
	public String toString(){
		return color + " " + getType();
	}

	// Return a reference to the current board. 
	public Board getBoard(){
		return boardRef;
	}

	// Retrieve the type. 
	public String getType(){
		return type;
	}
	// The color of this piece. 
	public String getColor(){
		return color;
	}

	// Retrieve the graphic. Used for the Board class when drawing. 
	public String getGraphic(){
		if(color.equals(Board.black)){
			return graphicBlack;
		}else{
			return graphicWhite;
		}
	}

	// --- Mutators ---
	public void setGraphics(String blackGraphic, String whiteGraphic ){
		graphicBlack = blackGraphic;
		graphicWhite = whiteGraphic;

	}

	// Is the move from a given tile to a given tile a valid move (for this peice type?)
	// Port this to just use row and col anyways. 
    public boolean isValidMove( int currentRow, int currentCol, int futureRow, int futureCol ){
    	System.out.println("Should not get called");
		return false;
	}

}