
package Pieces;

import Game.ChessPieces ;

/**
 * 
 * @author Veerle
 *
 */

public class Queen extends ChessPieces {

        public void Queen () {}

        public boolean legalMove (int startRow, int startColumn, int desRow, int desColumn, int[][] playerMatrix) {

            boolean axis = true;

            //XOR If ONE of these conditions match (if both true or false then false is returned) 
            if (startRow == desRow ^ startColumn == desColumn) { 
            
                axis = true; //Moving straight along axis
                
            } else if (startRow != desRow && startColumn != desColumn) {
            
                axis = false; //Moving diagonal
                
            } else {

                strErrorMsg = "Queen can move in a straight line in any direction";
                return false;

            }

            return axisMove(startRow, startColumn, desRow, desColumn, playerMatrix, axis);
        }
}// end class Queen