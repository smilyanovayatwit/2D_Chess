
package Pieces ;

import Game.ChessPieces ;

@SuppressWarnings( "javadoc" )
public class Bishop extends ChessPieces
    {

    public Bishop()
        {}


    public boolean legalMove( final int startRow,
                              final int startColumn,
                              final int desRow,
                              final int desColumn,
                              final int[][] playerMatrix )
        {

        // If moved straight
        if ( ( startRow == desRow ) || ( startColumn == desColumn ) )
            {

            this.strErrorMsg = "Bishop can only move along diagonal lines" ;
            return false ;
            }

        return axisMove( startRow,
                         startColumn,
                         desRow,
                         desColumn,
                         playerMatrix,
                         false ) ;
        }

    } // end class Bishop
