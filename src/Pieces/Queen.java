
package Pieces ;

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

        // XOR If ONE of these conditions match (if both true or false then false is
        // returned)
        if ( ( startRow == desRow ) ^ ( startColumn == desColumn ) )
            {

            axis = true ; // Moving straight along axis

            }
        else if ( ( startRow != desRow ) && ( startColumn != desColumn ) )
            {

            axis = false ; // Moving diagonal

            }
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
    }// end class Queen