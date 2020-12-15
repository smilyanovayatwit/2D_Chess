
package Pieces ;

/**
 * @author Veerle
 */
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

        // if moved 2 north, 1 east
        if ( ( desRow == ( startRow - 2 ) ) && ( desColumn == ( startColumn - 1 ) ) )
            {

            return true ;

            }
        // if moved 2 north, 1 west
        else if ( ( desRow == ( startRow - 2 ) ) &&
                  ( desColumn == ( startColumn + 1 ) ) )
            {

            return true ;

            }
        // if moved 2 south, 1 east
        else if ( ( desRow == ( startRow + 2 ) ) &&
                  ( desColumn == ( startColumn - 1 ) ) )
            {

            return true ;

            }
        // if moved 2 south, 1 west
        else if ( ( desRow == ( startRow + 2 ) ) &&
                  ( desColumn == ( startColumn + 1 ) ) )
            {

            return true ;

            }
        // if moved 1 north, 2 east
        else if ( ( desRow == ( startRow - 1 ) ) &&
                  ( desColumn == ( startColumn - 2 ) ) )
            {

            return true ;

            }
        // if moved 1 north, 2 west
        else if ( ( desRow == ( startRow - 1 ) ) &&
                  ( desColumn == ( startColumn + 2 ) ) )
            {

            return true ;

            }
        // if moved 1 south, 2 east
        else if ( ( desRow == ( startRow + 1 ) ) &&
                  ( desColumn == ( startColumn - 2 ) ) )
            {

            return true ;

            }
        // if moved 1 south, 2 west
        else if ( ( desRow == ( startRow + 1 ) ) &&
                  ( desColumn == ( startColumn + 2 ) ) )
            {

            return true ;

            }

        // if moved in any non-L shaped way
        return false ;

        }
    } // end class Knight