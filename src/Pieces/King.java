
package Pieces ;

/**
 * @author Veerle
 */

import Game.ChessPieces ;

@SuppressWarnings( "javadoc" )
public class King extends ChessPieces
    {

    public King()
        {}


    public boolean legalMove( final int startRow,
                              final int startColumn,
                              final int desRow,
                              final int desColumn,
                              final int[][] playerMatrix )
        {

        this.finalDesRow = desRow ;
        this.finalDesColumn = desColumn ;

        // if moved south
        if ( ( desRow == ( startRow + 1 ) ) && ( desColumn == startColumn ) )
            {

            return true ;

            }
        // if moved southwest
        else if ( ( desRow == ( startRow + 1 ) ) &&
                  ( desColumn == ( startColumn - 1 ) ) )
            {

            return true ;

            }
        // if moved west
        else if ( ( desRow == startRow ) && ( desColumn == ( startColumn - 1 ) ) )
            {

            return true ;

            }
        // if moved northwest
        else if ( ( desRow == ( startRow - 1 ) ) &&
                  ( desColumn == ( startColumn - 1 ) ) )
            {

            return true ;

            }
        // if moved north
        else if ( ( desRow == ( startRow - 1 ) ) && ( desColumn == startColumn ) )
            {

            return true ;

            }
        // if moved northeast
        else if ( ( desRow == ( startRow - 1 ) ) &&
                  ( desColumn == ( startColumn + 1 ) ) )
            {

            return true ;

            }
        // if moved east
        else if ( ( desRow == startRow ) && ( desColumn == ( startColumn + 1 ) ) )
            {

            return true ;

            }
        // if moved southeast
        else if ( ( desRow == ( startRow + 1 ) ) &&
                  ( desColumn == ( startColumn + 1 ) ) )
            {

            return true ;

            }
        // if moved in any direction that is more than one space
        else
            {

            this.strErrorMsg = "King can only move one space in any direction" ;
            return false ;

            }
        }
    } // end class King