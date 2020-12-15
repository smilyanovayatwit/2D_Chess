
package Pieces ;

/**
 * @author Veerle
 */
import Game.ChessPieces ;

@SuppressWarnings( "javadoc" )
public class Queen extends ChessPieces
    {

    public Queen()
        {}


    public boolean legalMove( final int startRow,
                              final int startColumn,
                              final int desRow,
                              final int desColumn,
                              final int[][] playerMatrix )
        {

        boolean axis = true ;

        // if moving straight along axis
        if ( ( startRow == desRow ) ^ ( startColumn == desColumn ) )
            {

            axis = true ; // Moving straight along axis

            }
        // if moving diagonally
        else if ( ( startRow != desRow ) && ( startColumn != desColumn ) )
            {

            axis = false ; // Moving diagonal

            }
        // XOR: if only ONE of the above conditions match
        // otherwise if both true or false then false is returned
        else
            {

            this.strErrorMsg = "Queen can move in a straight line in any direction" ;
            return false ;

            }

        return axisMove( startRow,
                         startColumn,
                         desRow,
                         desColumn,
                         playerMatrix,
                         axis ) ;
        }
    } // end class Queen