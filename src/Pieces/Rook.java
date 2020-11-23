
package Pieces;

import Game.ChessPieces ;

/**
 * 
 * @author Veerle
 *
 */

public class Rook extends ChessPieces {

    public void Rook () {}

    public boolean legalMove (int startRow, int startColumn, int desRow, int desColumn, int[][] playerMatrix)
    {
        //If moved diagonal
        if (startRow != desRow && startColumn != desColumn) {
            strErrorMsg = "Rock can only move Horiztonal or Vertical";
            return false;
        }
        
        //Since Queen shares the same movement as a bishop or rock, the path checking code is shared for all 3 of them
        //The boolean at the end is weather the piece is moving straight, or diagonally
        return axisMove(startRow, startColumn, desRow, desColumn, playerMatrix, true);
    }
}// end class Rook