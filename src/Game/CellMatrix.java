
package Game ;

@SuppressWarnings( "javadoc" )
public class CellMatrix
    {

    // Stores which player is in a cell. 0 empty, 1 player one, 2 player two
    private final int[][] playerMatrix = new int[ 8 ][ 8 ] ;

    /*
     * Stores which piece is in a cell. 0 pawn, 1 rock, 2 knight, 3 bishop, 4 queen,
     * 5 king, 6 empty
     */
    private final int[][] pieceMatrix = new int[ 8 ][ 8 ] ;

    public CellMatrix()
        {}


    public void resetMatrix()
        {

        for ( int row = 0 ; row < 8 ; row++ )
            {

            for ( int column = 0 ; column < 8 ; column++ )
                {
                // first two rows
                if ( row <= 1 )
                    {

                    this.playerMatrix[ row ][ column ] = 2 ;

                    if ( row == 1 )
                        {
                        this.pieceMatrix[ row ][ column ] = 0 ;
                        }
                    }
                else if ( ( row >= 2 ) && ( row <= 5 ) )
                    {

                    this.playerMatrix[ row ][ column ] = 0 ;
                    this.pieceMatrix[ row ][ column ] = 6 ;

                    }
                else
                    {
                    this.playerMatrix[ row ][ column ] = 1 ;

                    if ( row == 6 )
                        {
                        this.pieceMatrix[ row ][ column ] = 0 ;
                        }
                    }

                if ( ( row == 0 ) || ( row == 7 ) )
                    {
                    if ( column < 5 )
                        {
                        this.pieceMatrix[ row ][ column ] = ( column + 1 ) ;
                        }
                    else
                        {
                        this.pieceMatrix[ row ][ column ] = ( 8 - column ) ;
                        }
                    }
                }
            }
        }


    public int getPlayerCell( final int row,
                              final int column )
        {
        return this.playerMatrix[ row ][ column ] ;
        }


    public int getPieceCell( final int row,
                             final int column )
        {
        return this.pieceMatrix[ row ][ column ] ;
        }


    public void setPlayerCell( final int row,
                               final int column,
                               final int player )
        {
        this.playerMatrix[ row ][ column ] = player ;
        }


    public void setPieceCell( final int row,
                              final int column,
                              final int piece )
        {
        this.pieceMatrix[ row ][ column ] = piece ;
        }


    public int[][] getPlayerMatrix()
        {
        return this.playerMatrix ;
        }


    public int[][] getPieceMatrix()
        {
        return this.pieceMatrix ;
        }


    public boolean checkWinner( final int currentPlayer )
        {

        int checkPlayer = 0 ;

        if ( currentPlayer == 1 )
            {
            checkPlayer = 2 ;
            }
        else
            {
            checkPlayer = 1 ;
            }

        for ( int row = 0 ; row < 8 ; row++ )
            {
            for ( int column = 0 ; column < 8 ; column++ )
                {
                // If the enemy's king still remains
                if ( ( this.playerMatrix[ row ][ column ] == checkPlayer ) &&
                     ( this.pieceMatrix[ row ][ column ] == 5 ) )
                    {
                    return false ;
                    }
                }
            }
        return true ;
        }
    } // end class CellMatrix
