
package Game;


/**
 * 
 * @author Veerle
 *
 */

public class CellMatrix {


    //Stores which player is in a cell. 0 empty, 1 player one, 2 player two
    private int[][] playerMatrix = new int[8][8]; 
    
    /*Stores which piece is in a cell. 0 pawn, 1 rock, 2 knight, 3 bishop,
      4 queen, 5 king, 6 empty*/
    private int[][] pieceMatrix = new int[8][8]; 

    public void CellMatrix () {}

    public void resetMatrix () {

        for (int row = 0; row < 8; row++) {

            for (int column = 0; column < 8; column++) {
                //first two rows
                if (row <= 1) {
                
                    playerMatrix[row][column] = 2;

                    if (row == 1) {                   
                        pieceMatrix[row][column] = 0;
                    }
                } else if (row >= 2 && row <= 5){

                    playerMatrix[row][column] = 0;
                    pieceMatrix[row][column] = 6;

                } else {
                    playerMatrix[row][column] = 1;

                    if (row == 6) {
                        pieceMatrix[row][column] = 0;
                    }
                }

                if (row == 0 || row == 7) {
                    if (column < 5) {
                        pieceMatrix[row][column] = (column + 1);
                    } else {
                        pieceMatrix[row][column] = (8 - column);
                    }
                }
            }
        }
    }

    public int getPlayerCell (int row, int column) {
        return playerMatrix[row][column];
    }

    public int getPieceCell (int row, int column) {
        return pieceMatrix[row][column];
    }

    public void setPlayerCell (int row, int column, int player) {
        playerMatrix[row][column] = player;
    }

    public void setPieceCell (int row, int column, int piece) {
        pieceMatrix[row][column] = piece;
    }

    public int[][] getPlayerMatrix () {
        return playerMatrix;
    }

    public int[][] getPieceMatrix () {
        return pieceMatrix;
    }

    public boolean checkWinner (int currentPlayer) {

        int checkPlayer = 0;

        if (currentPlayer == 1) {
            checkPlayer = 2;
        } else {
            checkPlayer = 1;
        }

        for (int row = 0; row < 8; row++){
            for (int column = 0; column < 8; column++) {
                //If the enemy's king still remains
                if (playerMatrix[row][column] == checkPlayer && pieceMatrix[row][column] == 5) {
                    return false;
                }
            }
        }
        return true;
    }
} // end class CellMatrix
