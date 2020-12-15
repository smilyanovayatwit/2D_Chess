
package Pieces ;

/**
 * @author Veerle
 */

import Game.ChessPieces ;

@SuppressWarnings( "javadoc" )
public class Pawn extends ChessPieces
    {

    public Pawn()
        {}


    public boolean legalMove( final int startRow,
                              final int startColumn,
                              final int desRow,
                              final int desColumn,
                              final int[][] playerMatrix,
                              final int currentPlayer )
        {

        boolean legalMove = true ;
        final int[] playerPawnStart = { 6, 1 } ;

        // if moving in the wrong direction
        if ( ( ( currentPlayer == 1 ) && ( desRow >= startRow ) ) ||
             ( ( currentPlayer == 2 ) && ( desRow <= startRow ) ) )
            {

            this.strErrorMsg = "Can not move in that direction" ;
            legalMove = false ;

            }
        // if moving sideways/diagonally
        else if ( desColumn != startColumn )
            {

            // if only moving one place sideways/diagonally
            if ( ( ( desColumn > startColumn ) &&
                   ( desColumn == ( startColumn + 1 ) ) ) ||
                 ( ( desColumn < startColumn ) &&
                   ( desColumn == ( startColumn - 1 ) ) ) )
                {

                // if cell is empty
                if ( ( ( desRow == ( startRow + 1 ) ) && ( currentPlayer == 2 ) ) ||
                     ( ( desRow == ( startRow - 1 ) ) && ( currentPlayer == 1 ) ) )
                    {

                    if ( playerMatrix[ desRow ][ desColumn ] == 0 )
                        {

                        this.strErrorMsg = "Can only move diagonal when taking an enemy piece" ;
                        legalMove = false ;

                        }
                    }
                else
                    {

                    this.strErrorMsg = "Can not move that far" ;
                    legalMove = false ;

                    }
                }
            else
                {

                this.strErrorMsg = "Can not move that far" ;
                legalMove = false ;

                }
            }
        // if moved two or more places
        else if ( ( ( currentPlayer == 1 ) && ( desRow < ( startRow - 1 ) ) ) ||
                  ( ( currentPlayer == 2 ) && ( desRow > ( startRow + 1 ) ) ) )
            {

            // if moved two places
            if ( ( ( currentPlayer == 1 ) && ( desRow == ( startRow - 2 ) ) ) ||
                 ( ( currentPlayer == 2 ) && ( desRow == ( startRow + 2 ) ) ) )
                {
                // if not at pawn starting place
                if ( playerPawnStart[ currentPlayer - 1 ] != startRow )
                    {

                    this.strErrorMsg = "Can not move that far" ;
                    legalMove = false ;

                    }
                }
            // pawn can only move 1 space
            else
                {

                this.strErrorMsg = "Can not move that far" ;
                legalMove = false ;

                }
            }

        if ( legalMove )
            {
            this.finalDesRow = desRow ;
            this.finalDesColumn = desColumn ;
            }
        return legalMove ;
        }
    } // end class Pawn