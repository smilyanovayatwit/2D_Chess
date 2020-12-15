
package Pieces ;

/**
 * @author Veerle
 */

import Game.ChessPieces ;

@SuppressWarnings( "javadoc" )
public class Rook extends ChessPieces
    {

    public Rook()
        {}


    public boolean legalMove( final int startRow,
                              final int startColumn,
                              final int desRow,
                              final int desColumn,
                              final int[][] playerMatrix )
        {
        // if moved in a non-straight way/diagonally
        if ( ( startRow != desRow ) && ( startColumn != desColumn ) )
            {
            this.strErrorMsg = "Rook can only move Horiztonal or Vertical" ;
            return false ;
            }

        // since the queen shares the same movement as a bishop or rook, the path
        // checking code is shared for all 3 of them

        // boolean at the end is whether the piece is moving straight or diagonally
        return axisMove( startRow,
                         startColumn,
                         desRow,
                         desColumn,
                         playerMatrix,
                         true ) ;
        }
    } // end class Rook