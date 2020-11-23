
package Pieces ;

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

        // S
        if ( ( desRow == ( startRow + 1 ) ) && ( desColumn == startColumn ) )
            {

            return true ;

            }
        else if ( ( desRow == ( startRow + 1 ) ) &&
                  ( desColumn == ( startColumn - 1 ) ) )
            { // SW

            return true ;

            }
        else if ( ( desRow == startRow ) && ( desColumn == ( startColumn - 1 ) ) )
            { // W

            return true ;

            }
        else if ( ( desRow == ( startRow - 1 ) ) &&
                  ( desColumn == ( startColumn - 1 ) ) )
            { // NW

            return true ;

            }
        else if ( ( desRow == ( startRow - 1 ) ) && ( desColumn == startColumn ) )
            { // N

            return true ;

            }
        else if ( ( desRow == ( startRow - 1 ) ) &&
                  ( desColumn == ( startColumn + 1 ) ) )
            { // NE

            return true ;

            }
        else if ( ( desRow == startRow ) && ( desColumn == ( startColumn + 1 ) ) )
            { // E

            return true ;

            }
        else if ( ( desRow == ( startRow + 1 ) ) &&
                  ( desColumn == ( startColumn + 1 ) ) )
            { // SE

            return true ;

            }
        else
            {

            this.strErrorMsg = "King can only move one space in any direction" ;
            return false ;

            }
        }
    } // end class King