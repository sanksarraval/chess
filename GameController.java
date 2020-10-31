
// GameController for our basic Checkers game 
// Assignent 2 - Comp 1020. 
// c D. Fries 2020
// This class handles turn taking, input, selection of tiles and calling the Board to verify 
// and execute moves. Includes all main game logic. 
public class GameController{
	
  // Start 
  public static void main(String[] args) { 

  	// --- Initialization ---
  	// Create the gameboard
  	Board board = new Board();

  	// Comment out when loading from file. 
    board.resetBoard(); // reset pieces. 
    
  	// *** Uncomment to run with board from file. 
    //BoardLoader.loadBoardState(board, "StandardBoard.txt");

    board.drawBoard(); // background board
    board.drawPieces(); // Pieces only (formerly drawPieces but that was confusing. )

    // Starting player
    String playersTurn = Board.white; // white is 0, black is 1

    // --- Input variables ---
    boolean isSelected = false; // Is a tile currently selected
    // which tile is selected
    int selectedCol = 0; 
    int selectedRow = 0;

    // Mouse position when it is pressed
    double mouseX = 0;
    double mouseY = 0;

    // Used to convert the mouse press being held down to the mouse being pressed this frame. 
    boolean mouseDownLastFrame = false;
    boolean mousePressedThisFrame = false;

    // Print extra debugging messages
    boolean debug_mode = false;

	// Main Game Loop
	// Loop forever. 
    while(true){

		mousePressedThisFrame = false;// set to default

    	// To avoid the mouse just clicking true continiously
    	if(StdDraw.mousePressed()){
    		// ONLY check for input if the mouse was pressed this frame for the first time. 
    		if(!mouseDownLastFrame){
    			// IF the mouse was pressed on this FRAME
    			mousePressedThisFrame = true;
    		}

    		mouseDownLastFrame = true; // reset for the next frame
    	}else{
    		mousePressedThisFrame = false;
    		mouseDownLastFrame = false;
    	}

    	// Testing how far we have progressed
    	if( debug_mode) { System.out.print("C");}

    	// Notes: Filter by border
    	// Selection Notes: If no tile is selected, and the tile clicked is the color of the player whos turn it currently is
    	// then select that tile. If a tile is selected 1 of 3 things happen the next time a mouse press is detected
    	// 1. a different tile of the same color is selected - update the selection
    	// 2. a move is attempted (either 1 diagional forward or jumping over 1 diag forward and landing on 2 diag forward)
    	/// -- either fails and deselect OR
    	/// -- succeeds and the move is executed, then the turn ends. 
    	// 3. an invalid move is selected / something else is clicked - deselect any tile. 

    	if( mousePressedThisFrame ){

    		// Get mouse input. 
			mouseX = StdDraw.mouseX();
    		mouseY = StdDraw.mouseY();

    		// XX For testing, delete if you want
	    	if( debug_mode) { System.out.print("D");}
    
    		// If mouse in bounds
    		if( mouseX > 0 && mouseX < 1.0 && mouseY > 0 && mouseY < 1.0){
    			// convert to int (row/ col numbers)
	    		int xInt = (int)(mouseX / Board.BOARD_PROPORTION);
	    		int yInt = (int)(mouseY / Board.BOARD_PROPORTION);

	    		// This would be 8, out of bounds
	    		// (1.0 / Board.BOARD_PROPORTION)
	    		if( debug_mode) { System.out.println("int selected : " + xInt + " " + yInt);}

	    		// Select tile if not selected
	    		if(!isSelected){

					// check if tile belongs to current player
	    			String color = board.getPieceColor(yInt, xInt);
	    			// if its the white players turn
	    			if (playersTurn.equals(Board.white)){
	    				// and a white tile is selected
	    				if(color.equals(Board.white)){
	    					// then set that tile as selected
	    					selectedCol = xInt;
	    					selectedRow = yInt;
	    					isSelected = true;
	    		
	    				}else{
	    					System.out.println("Not a valid tile to select, must be White");
	    				}
	    			// it must be the black players turn
	    			}else{
	    				// so check if a black tile was selected
	    				if(color.equals(Board.black)){
	    					// if so, set that tile as selected
	    					selectedCol = xInt;
	    					selectedRow = yInt;
	    					isSelected = true;
	    					
	    				}else{
	    					System.out.println("Not a valid tile to select, must be Black");
	    				}
	    			}
	    		}else{
	    			// If a tile is already selected, we either Move, or deselect any tile. 

	    			// check if it is a valid move
	    			if( board.isValidMove(selectedRow, selectedCol, yInt, xInt, playersTurn)){
	    		
	    				// Apply Move
	    				board.applyMove(selectedRow, selectedCol, yInt, xInt);

	    				// END TURN
	    				// next player turn
	    				if(playersTurn.equals(Board.white)){
	    					playersTurn = Board.black;
	    				}else{
	    					playersTurn = Board.white;
	    				}

	    				isSelected = false;
	    			}

	    			// Are we trying to select another tile of the same color? 
	    			// If so, update the selection
	    			String color = board.getPieceColor(yInt, xInt);
	    			if (playersTurn.equals(Board.white)){
	    				if(color.equals(Board.white)){
	    					// update the selected tile, maintain selected status
	    					selectedCol = xInt;
	    					selectedRow = yInt;
	    				}else{
	    					isSelected = false; // deselect
	    				}
	    			}else if(playersTurn.equals(Board.black)){
	    				if(color.equals(Board.black)){
	    					// update the selected tile, maintain selected status
	    					selectedCol = xInt;
	    					selectedRow = yInt;
	    				}else{
	    					isSelected = false;
	    				}
	    			}else{
	    				System.out.println("Should never get here or the player color got unset. ");
	    			}


	    			// regardless, deselect the tile 
	    			//isSelected = false;
	    		}

	    		// Refresh Art
	    		board.drawBoard();
	    		board.drawPieces();

				// If a tile is selected, draw it in green instead. 
				if(isSelected){
					board.drawSelectedTile(selectedRow, selectedCol);
					board.drawSelectedForAllValidMoves(selectedRow, selectedCol);
				}

				// Check End Game
				// Count the total number of tiles remaining for each team. 
				// Not sure if we still need this 
				int whiteCount = board.countTiles(Board.white);
				int blackCount = board.countTiles(Board.black);

				boolean whiteKing = board.doesKingExist(Board.white);
				boolean blackKing = board.doesKingExist(Board.black);

				// If either team has 0, the game ends. 
				if(!whiteKing){
					System.out.println("Black Wins!");
				}
				if(!blackKing){
					System.out.println("White Wins!");
				}

	    	}
    	}
    	
    	StdDraw.show(50);
    }
  }
}