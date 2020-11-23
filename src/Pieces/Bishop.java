
package Pieces;

import Game.ChessPieces ;

/**
 * 
 * @author Veerle
 *
 */

public class Bishop extends ChessPieces {

    public void Bishop () {}

    public boolean legalMove (int startRow, int startColumn, int desRow, int desColumn, int[][] playerMatrix) {

        //If moved straight
        if (startRow == desRow || startColumn == desColumn) {

            strErrorMsg = "Bishop can only move along diagonal lines";
            return false;
        }

        return axisMove(startRow, startColumn, desRow, desColumn, playerMatrix, false);
    }

} // end class Bishop

