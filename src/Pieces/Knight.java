
package Pieces ;

import Game.ChessPieces ;

@SuppressWarnings( "javadoc" )
public class Knight extends ChessPieces
    {

    public Knight()
        {}


    public boolean legalMove( final int startRow,
                              final int startColumn,
                              final int desRow,
                              final int desColumn,
                              final int[][] playerMatrix )
        {

        this.finalDesRow = desRow ;
        this.finalDesColumn = desColumn ;
        this.strErrorMsg = "Horse can only move in a L shape" ;

        if ( ( desRow == ( startRow - 2 ) ) && ( desColumn == ( startColumn - 1 ) ) )
            { // 2N, 1E

            return true ;

            }
        else if ( ( desRow == ( startRow - 2 ) ) &&
                  ( desColumn == ( startColumn + 1 ) ) )
            { // 2N, 1W

            return true ;

            }
        else if ( ( desRow == ( startRow + 2 ) ) &&
                  ( desColumn == ( startColumn - 1 ) ) )
            { // 2S, 1E

            return true ;

            }
        else if ( ( desRow == ( startRow + 2 ) ) &&
                  ( desColumn == ( startColumn + 1 ) ) )
            { // 2S, 1W

            return true ;

            }
        else if ( ( desRow == ( startRow - 1 ) ) &&
                  ( desColumn == ( startColumn - 2 ) ) )
            { // 1N, 2E

            return true ;

            }
        else if ( ( desRow == ( startRow - 1 ) ) &&
                  ( desColumn == ( startColumn + 2 ) ) )
            { // 1N, 2W

            return true ;

            }
        else if ( ( desRow == ( startRow + 1 ) ) &&
                  ( desColumn == ( startColumn - 2 ) ) )
            { // 1S, 2E

            return true ;

            }
        else if ( ( desRow == ( startRow + 1 ) ) &&
                  ( desColumn == ( startColumn + 2 ) ) )
            {// 1S, 2W

            return true ;

            }

        return false ;

        }
    } // end class Knight