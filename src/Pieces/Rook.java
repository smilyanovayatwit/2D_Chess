
package Pieces ;

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
        // If moved diagonal
        if ( ( startRow != desRow ) && ( startColumn != desColumn ) )
            {
            this.strErrorMsg = "Rock can only move Horiztonal or Vertical" ;
            return false ;
            }

        // Since Queen shares the same movement as a bishop or rock, the path
        // checking code is shared for all 3 of them
        // The boolean at the end is weather the piece is moving straight, or
        // diagonally
        return axisMove( startRow,
                         startColumn,
                         desRow,
                         desColumn,
                         playerMatrix,
                         true ) ;
        }
    }// end class Rook