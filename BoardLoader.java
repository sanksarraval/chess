import java.util.Scanner;
import java.io.*;
/**
 * This class is responsible for loading a file representing a Board into an actual Board. 
 * 
 *
 * @author (Sanskar Raval)
 * @version (2020-11-14)
 */
public class BoardLoader
{
    private static String fName; // Stores the name of the file.
    private static int row = 0; // Number of rows.
    private static int col = 0; // Number of cols.
    //Phase 2a
    /*
     * This method accepts both a reference of the board to be initialized as well as the file name to load from. 
     * It will handle the other methods.
     */
    public static void loadBoardState(Board theBoard, String fileName){
        fName = fileName;
        Board b = new Board();
        b = theBoard;
        loadPiecesFromString(b,parseFile(fileName));
    }
    //Phase 2b
    /*
     * This method load the disk from the file and returns a 2D array.
     */
    private static String[][] parseFile(String fileName){
        String[][] fName = {};// Empty 2D array.

        try{
            FileReader f = new FileReader(fileName);//Reads the name of the file.
            Scanner sc = new Scanner(f);// Accesses the file.
            while(sc.hasNextInt()){// Checks if it has an int.
                row = sc.nextInt();// Assigns the 1st int to row
                col = sc.nextInt();// Assigns the 2nd int to col.
            }
            fName = new String[row][col];// Initialization of the empty 2D array.
            while(sc.hasNext())
                for(int i = 0; i<row;i++){
                    for(int j = 0; j<col;j++){
                        fName[i][j] = sc.next();// Fills the empty 2D array.
                    }
                }
        }
        catch(Exception e){
            System.out.println("File Not Found");
            System.out.println(e.getMessage());
        }
        return fName;
    }
    //Phase 2c
    //Accept a 2D array of type String with all the board codes and load the pieces into the board from this method.
    //It will accept the ChessPiece from 2D and load it in the board using 2c.
    private static void loadPiecesFromString(Board targetBoard, String[][] boardCodes){
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                targetBoard.setPiece(i,j,loadChessPiece(targetBoard,boardCodes[i][j]));
            }        
        }
    }
    //Phase 2D
    //Creates and returns a single ChessPiece given a String code.
    private static ChessPiece loadChessPiece(Board targetBoard, String code){
        ChessPiece cp;// An object of ChessPiece.
        String color = ""; // Empty variable to store the string.
        boolean temp = true;// Temporary variable of type boolean.
        if(code.charAt(0)=='B'){// Checks the character at position 0 of the String.
            color = "BLACK";
        }
        else if(code.charAt(0)=='E'){// Checks the character at position 0 of the String.
            color = "EMPTY";
            temp = false;
        }
        else if(code.charAt(0)=='W'){// Checks the character at position 0 of the String.
            color = "WHITE";
        }
        cp = new Queen("",color,targetBoard);// Using polymorphism, here any chess piece could be used in place of Queen..
        if(temp == true){
            if(code.charAt(1)=='C'){// Checks the character at position 1 of the String.
                Castle c = new Castle("CASTLE",color,targetBoard);
                return c;
            }
            if(code.charAt(1)=='P'){// Checks the character at position 1 of the String.
                Pawn p = new Pawn("PAWN",color,targetBoard);
                return p;
            }
            if(code.charAt(1)=='K'){// Checks the character at position 1 of the String.
                King k = new King("KING",color,targetBoard);
                return k;
            }
            if(code.charAt(1)=='N'){// Checks the character at position 1 of the String.
                Knight n = new Knight("KNIGHT",color,targetBoard);
                return n;
            }
            if(code.charAt(1)=='Q'){// Checks the character at position 1 of the String.
                Queen q = new Queen("QUEEN",color,targetBoard);
                return q;
            }
            if(code.charAt(1)=='B'){// Checks the character at position 1 of the String.
                Bishop b = new Bishop("BISHOP",color,targetBoard);
                return b;
            }
        }
        return null;
    }
}
