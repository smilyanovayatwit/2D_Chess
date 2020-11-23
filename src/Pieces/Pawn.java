
package Pieces ;

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

        if ( ( ( currentPlayer == 1 ) && ( desRow >= startRow ) ) ||
             ( ( currentPlayer == 2 ) && ( desRow <= startRow ) ) )
            { // If moving in wrong direction

            this.strErrorMsg = "Can not move in that direction" ;
            legalMove = false ;

            }
        else if ( desColumn != startColumn )
            { // If moving sideways

            if ( ( ( desColumn > startColumn ) &&
                   ( desColumn == ( startColumn + 1 ) ) ) ||
                 ( ( desColumn < startColumn ) &&
                   ( desColumn == ( startColumn - 1 ) ) ) )
                { // If only moving one place side ways

                if ( ( ( desRow == ( startRow + 1 ) ) && ( currentPlayer == 2 ) ) ||
                     ( ( desRow == ( startRow - 1 ) ) && ( currentPlayer == 1 ) ) )
                    {

                    if ( playerMatrix[ desRow ][ desColumn ] == 0 )
                        { // If cell is empty

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
        else if ( ( ( currentPlayer == 1 ) && ( desRow < ( startRow - 1 ) ) ) ||
                  ( ( currentPlayer == 2 ) && ( desRow > ( startRow + 1 ) ) ) )
            { // If moved two or more places

            if ( ( ( currentPlayer == 1 ) && ( desRow == ( startRow - 2 ) ) ) ||
                 ( ( currentPlayer == 2 ) && ( desRow == ( startRow + 2 ) ) ) )
                { // If moved two places

                if ( playerPawnStart[ currentPlayer - 1 ] != startRow )
                    { // If not at pawn starting place

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

        if ( legalMove )
            {
            this.finalDesRow = desRow ;
            this.finalDesColumn = desColumn ;
            }
        return legalMove ;
        }
    } // end class Pawn