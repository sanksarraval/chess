import java.util.ArrayList;

/**
 * Draw a chessboard.
 */
public class Board {
  
  // board size - square tiles
  public static final int BOARD_SIZE = 8;
  public static final double BOARD_PROPORTION = 1.0 / (double)BOARD_SIZE;
  private static final double TILE_RADIUS = 0.5/BOARD_SIZE;

  // Color references/tile states. 
  public static final String red = "RED";
  public static final String black = "BLACK";
  public static final String white = "WHITE";
  public static final String empty = "EMPTY";

  // The Subfolder to load the pieces from. 
  private String artFolder = "/Pieces/";

  // All pieces on the board. Null means no piece. 
  private ChessPiece[][] boardState;

  private final boolean DEBUG_MODE = false;

  // ----- Board Initialization -----
  // Set all values of array to Empty
  public Board(){
    // Inititialize and create the array. 
    clearBoard();
  }

  //Wipe & blank the board. 
  // This is sort of overkill here but I will be thorough for now. 
  public void clearBoard(){
    // The question becomes, when do we create the Tile objects? 
    // Advantages/ Disadvantages
    // - create once, reset each time - more optimized but have to carefully reset everything
    // - create many time - don't have to worry about old state, slight performance hit (maybe?)
    boardState = new ChessPiece[BOARD_SIZE][BOARD_SIZE];

    for( int row = 0; row < BOARD_SIZE; row++ )
      for( int col = 0; col < BOARD_SIZE; col++ )
        boardState[row][col] = null;
  }

  // ----- Board Utility Methods, Accessors & Mutators -----
  // Set the value t o a given value. 
  public void setTile(int row, int col, ChessPiece newPiece){
    setPiece(row,col, newPiece);
  }

  // Formerly isValidTile
  public boolean isDarkSquare(int row, int col){
    return ( 0 == ( row + col ) % 2 );
  }

  // Does the given position contain a piece. 
  public boolean hasPiece(int row, int col){
    return boardState[row][col] != null;
  }

  // Return the piece at a given position. 
  public ChessPiece getPiece(int row, int col){
    return boardState[row][col];
  }

  // Assign a give piece to a specific tile. 
  public void setPiece(int row, int col, ChessPiece piece){
    boardState[row][col] = piece;
  }

  // Return the color of the given piece or "NO PIECE FOUND" if it is null
  // Should not be called on an empty tile. Check it first with hasPiece. 
  public String getPieceColor(int row, int col){
    String color = "NO PIECE FOUND";
    if(hasPiece(row, col)){
      ChessPiece piece = getPiece(row, col);
      color = piece.getColor();
    }

    return color;
  }

  // Is this a tile on the board or not. 
  public boolean isValidTile(int row, int col){
    // Just if it is on the board. 
    if(row>= 0 && row <BOARD_SIZE && col >= 0 && col < BOARD_SIZE)
      return true;
    else 
      return false;
  }

  // ----- Drawing and Art related -----
  // Draw a blank board (no pieces)
  // note this will overwrite the pieces if they have been drawn first. 
  public void drawBoard(){
               
    // Iterate through the board
    for( int row = 0; row < BOARD_SIZE; row++ )
      for( int col = 0; col < BOARD_SIZE; col++ ){
        
        // alternate squares, choose black or white to draw
        if( isDarkSquare(row, col))
          StdDraw.setPenColor(StdDraw.DARK_GRAY);
        else
          StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        
        // Draw the rectangle
         // FilledRect accepts the center point of the width, then height, followed by the half width/height of the rect. 
        // corrdinates are in 0-1.0 range
        double xCenter = (2 * col + 1)/( 2.0 * BOARD_SIZE );
        double yCenter = (2 * row + 1)/( 2.0 * BOARD_SIZE );

        StdDraw.filledRectangle(xCenter, yCenter, TILE_RADIUS, TILE_RADIUS);
    }//both for loops
    
    // High End Graphics
    //It would look better with a border around it.
    StdDraw.line(0,0,1,0);
    StdDraw.line(1,0,1,1);
    StdDraw.line(1,1,0,1);
    StdDraw.line(0,1,0,0);  
  }//

  // Draw all the pieces on the board currently. 
  public void drawPieces(){
    for( int row = 0; row < BOARD_SIZE; row++ )
      for( int col = 0; col < BOARD_SIZE; col++ )
        if( hasPiece(row,col))
          drawPiece(row, col);
  }

  // Drawing a single piece at a given location. 
  // Formerly drawTile
  public void drawPiece(int row, int col){

    String graphic = "NOT FOUND";

    // Verifying this exists. 
    if(hasPiece(row,col)){
      // Gets the peice from the board here. 
      ChessPiece p = getPiece(row, col);
      if(p != null){
        graphic = p.getGraphic();

        double xCenter = (2 * col + 1)/( 2.0 * BOARD_SIZE );
        double yCenter = (2 * row + 1)/( 2.0 * BOARD_SIZE );
        //StdDraw.filledCircle(xCenter, yCenter, TILE_RADIUS);
        //StdDraw.picture(double x, double y, String s, double w, double h)
        // Load from a Sub-directory not the current directory. 
        graphic =  artFolder + graphic;

        StdDraw.picture(xCenter, yCenter, graphic, TILE_RADIUS*2, TILE_RADIUS*2);
      }else{
        System.out.println(row + " " + col);
        System.out.println("Warning, no graphic found, piece was null");
      }

    }else{
      System.out.println("Warning, drawPiece called but no piece found");
    }
  }

  // ----- Drawing Selection boxes for available moves ----- 
  // Essentially for testing, will show all the Valid moves on the board for a given tile. 
  // Not very efficient but will brute force test all possible moves. 
  // Useful for testing, not needed for GC right now. 
  public void drawSelectedForAllValidMoves(int row, int col){

    if( hasPiece(row,col)){
      // get the piece frmo the tile. 
      ChessPiece cp = getPiece(row, col);

      // Simply brute for test
      for(int i = 0 ; i < BOARD_SIZE; i ++){
        for(int j =0; j < BOARD_SIZE; j++){
          // isValidMove(int startRow, int startCol, int endRow, int endCol, String color){
          if( isValidMove(row, col, i,j, cp.getColor())){
            drawSelectedTile(i,j);
          }
        } 
      }
    } 
  }

  // Test if a move is valid and highlight the tile if it is. 
  public void drawSelectedForSingleMove(int startRow, int startCol, int endRow, int endCol){
    if( hasPiece(startRow,startCol)){
      // get the piece from the tile. 
      ChessPiece cp = getPiece(startRow, startCol);

      // isValidMove(int startRow, int startCol, int endRow, int endCol, String color){
      if( isValidMove(startRow, startCol, endRow,endCol, cp.getColor())){
        drawSelectedTile(endRow,endCol);
      }
    }else{
      System.out.println("No Piece found at given tile");
    }
  }

  // Draw a green tile on the selected row
  public void drawSelectedTile(int row, int col){
    StdDraw.setPenColor(StdDraw.GREEN);
    double xCenter = (2 * col + 1)/( 2.0 * BOARD_SIZE );
    double yCenter = (2 * row + 1)/( 2.0 * BOARD_SIZE );
    
    // Updated from A2
    // Now we are drawing a green background with the piece on top 
    StdDraw.filledRectangle(xCenter, yCenter, TILE_RADIUS, TILE_RADIUS);
    //StdDraw.filledCircle(xCenter, yCenter, TILE_RADIUS);

    // Also draw the piece. (on top because it was drawn after). 
    if(hasPiece(row, col)){
      drawPiece(row, col);
    }
  }

  // Counts all pieces of a given color
  public int countTiles(String color){

    int count = 0;

    // Iterate through the board
    for( int row = 0; row < BOARD_SIZE; row++ ){
      for( int col = 0; col < BOARD_SIZE; col++ ){
        if( hasPiece(row, col)){
          if(getPieceColor(row,col).equals(color)){
            count++;
          }
        }
      }
    }

    return count;
  }

  // Counts all pieces of a given color
  public boolean doesKingExist(String givenColor){

    boolean returnVal = false;

    // Iterate through the board
    for( int row = 0; row < BOARD_SIZE; row++ ){
      for( int col = 0; col < BOARD_SIZE; col++ ){
        if( hasPiece(row, col) && getPieceColor(row,col).equals(givenColor)){
          if(getPiece(row,col) instanceof King){
            returnVal = true;
          }
        }
      }
    }

    return returnVal;
  }

  // Just clear the board for now. 
  public void endTurn(){

    StdDraw.clear();
  }

  // ------------------------ CHECK MOVES --------------
  // Will call the individual pieces to see if the move is valid. 
  public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, String color){
    
    boolean isValid = false;

    // Bounds checking, both need to be on the board. 
    if( !isValidTile(startRow, startCol) || !isValidTile(endRow, endCol)){
      System.out.println("ERROR: Should not be calling this, invalid tile for movement.");
      return false;
    }

    // Is this a valid piece to move?
    ChessPiece toMove = getPiece(startRow, startCol);
    if(toMove == null){
      System.out.println("ERROR: No piece found on Tile at:" + startRow + " " + startCol);
      return false;
    }

    // Check that it is the correct turn. 
    if(toMove.getColor() != color ){
      System.out.println("ERROR: Wrong Teams Turn:" + color + "'s turn but the piece is ' " + toMove.getColor());
      return false;
    }

    // Actually doing the hard work. 
    // Ask if the move is valid and let the piece sort it out. 
    // Oops this is calling itself, welcome to recursion. 
//  isValid = isValidMove(startRow,startCol, endRow, endCol, color);
    // We want the version of the method on the ChessPiece
    isValid = toMove.isValidMove(startRow,startCol, endRow, endCol);

    // Error Checking. 
    if ( DEBUG_MODE) {  System.out.println("Valid move: [" + startRow +"," + startCol + "]->[" + endRow +","+ endCol +"]" + isValid);  }

    return isValid;
  }

  // doesn't do any error checking so make sure you verify it first. 
  // Just move the piece at (startRow, startCol) to (endRow, endCol)
  public void applyMove(int startRow, int startCol, int endRow, int endCol){

    ChessPiece toMove = getPiece(startRow, startCol);
    ChessPiece captured = getPiece(endRow, endCol);
    setPiece(endRow, endCol, toMove);
    setPiece(startRow, startCol, null);

    if( captured != null ){
      System.out.println(captured.toString() + " captured by " + toMove.getColor());
    }
  }

  // Assumes the Board and arrays are already created
  // sets the pieces 
  // Hard coded to set the pieces. 
  public void resetBoard(){
    // Base Board
    // Iterate through the board

    // Reuse methods when you can. 
    clearBoard();

    // ************* Redundant, they will already be null ***************

    // Reset Pieces 
    // White
    int row = 1;
    for( int col = 0; col < BOARD_SIZE; col++ ){
      
      // alternate squares, choose black or red to draw
      if( isValidTile(row, col))
        boardState[row][col] = new Pawn(Board.white, this);
    }

    // Black pieces
    row = 6;
    for( int col = 0; col < BOARD_SIZE; col++ ){
      
      // alternate squares, choose black or white to draw
      if( isValidTile(row, col))
        boardState[row][col] = new Pawn(Board.black, this);
    }
    
    // Create all the White pieces
    boardState[0][0] = new Castle(white, this);
    boardState[0][7] = new Castle(white, this);
    boardState[0][1] = new Knight(white, this);
    boardState[0][6] = new Knight(white, this);
    boardState[0][2] = new Bishop(white, this);
    boardState[0][5] = new Bishop(white, this);
    boardState[0][3] = new Queen(white, this);
    boardState[0][4] = new King(white, this);

    // Create all the black pieces. 
    boardState[7][0] = new Castle(black, this);
    boardState[7][7] = new Castle(black, this);
    boardState[7][1] = new Knight(black, this);
    boardState[7][6] = new Knight(black, this);
    boardState[7][2] = new Bishop(black, this);
    boardState[7][5] = new Bishop(black, this);
    boardState[7][4] = new Queen(black, this);
    boardState[7][3] = new King(black, this);
  }
  

}//Chessboard
