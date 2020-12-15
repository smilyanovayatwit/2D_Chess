
package Game ;

/**
 * @author Veerle
 */

@SuppressWarnings( "javadoc" )
public class ChessPieces
    {

    protected int finalDesRow = 0 ;
    protected int finalDesColumn = 0 ;
    protected String strErrorMsg = "" ; // error message for chess pieces

    public ChessPieces()
        {}


    // checks the cell to make sure it is empty
    private boolean checkAxisMove( final int newRow,
                                   final int newColumn,
                                   final int[][] playerMatrix )
        {

        // if not empty
        if ( playerMatrix[ newRow ][ newColumn ] != 0 )
            {
            this.strErrorMsg = "A piece is blocking the route" ; // Error message
            return false ;
            }
        return true ;
        }


    // checks path to the destination to make sure nothing is in the way
    protected boolean axisMove( final int startRow,
                                final int startColumn,
                                final int desRow,
                                final int desColumn,
                                final int[][] playerMatrix,
                                final boolean straightAxis )
        {

        // if moving along a straight axis (rook/queen)
        if ( straightAxis )
            {

            // if moving along the same column
            if ( ( startColumn == desColumn ) && ( startRow != desRow ) )
                {

                // if moving north
                if ( desRow < startRow )
                    {

                    // checks each cell between the start row - 1 (since don't need
                    // to check the cell it is in) to the destination cell
                    for ( int newRow = ( startRow - 1 ) ;
                          newRow > desRow ;
                          newRow-- )
                        {

                        // checks if the cell is empty
                        if ( !checkAxisMove( newRow, desColumn, playerMatrix ) )
                            {
                            return false ;
                            }
                        }
                    }
                // if moving south
                else
                    {

                    for ( int newRow = ( startRow + 1 ) ;
                          newRow < desRow ;
                          newRow++ )
                        {
                        if ( !checkAxisMove( newRow, desColumn, playerMatrix ) )
                            {
                            return false ;
                            }
                        }
                    }
                }
            // if moving along the same row
            else if ( ( startRow == desRow ) && ( startColumn != desColumn ) )
                {

                // if moving west
                if ( desColumn < startColumn )
                    {
                    for ( int newColumn = ( startColumn - 1 ) ;
                          newColumn > desColumn ;
                          newColumn-- )
                        {
                        if ( !checkAxisMove( desRow, newColumn, playerMatrix ) )
                            {
                            return false ;
                            }
                        }
                    }
                // if moving east
                else
                    {

                    for ( int newColumn = ( startColumn + 1 ) ;
                          newColumn < desColumn ;
                          newColumn++ )
                        {
                        if ( !checkAxisMove( desRow, newColumn, playerMatrix ) )
                            {
                            return false ;
                            }
                        }
                    }
                }
            // if moved diagonally
            else
                {

                this.strErrorMsg = "Should not see this error message" ;
                return false ;
                }
            }
        // if moving diagonally (bishop/queen)
        else
            {

            // default error message
            this.strErrorMsg = "The destination is not on the same diagonal line" ;
            int newColumn = 0 ;

            // if moved northwest
            if ( ( desRow < startRow ) && ( desColumn < startColumn ) )
                {

                // the number of cells moved horizontally should equal the number of
                // cells moved vertically
                if ( ( desRow - startRow ) == ( desColumn - startColumn ) )
                    {

                    for ( int newRow = ( startRow - 1 ) ;
                          newRow > desRow ;
                          newRow-- )
                        {

                        newColumn = startColumn - ( startRow - newRow ) ;

                        if ( !checkAxisMove( newRow, newColumn, playerMatrix ) )
                            {
                            return false ;
                            }
                        }
                    }
                else
                    {
                    return false ;
                    }
                }
            // if moved northeast
            else if ( ( desRow < startRow ) && ( desColumn > startColumn ) )
                {

                if ( ( desRow - startRow ) == ( startColumn - desColumn ) )
                    {

                    for ( int newRow = ( startRow - 1 ) ;
                          newRow > desRow ;
                          newRow-- )
                        {

                        newColumn = startColumn + ( startRow - newRow ) ;

                        if ( !checkAxisMove( newRow, newColumn, playerMatrix ) )
                            {
                            return false ;
                            }
                        }
                    }
                else
                    {
                    return false ;
                    }
                }
            // if moved southwest
            else if ( ( desRow > startRow ) && ( desColumn < startColumn ) )
                {

                if ( ( startRow - desRow ) == ( desColumn - startColumn ) )
                    {

                    for ( int newRow = ( startRow + 1 ) ;
                          newRow < desRow ;
                          newRow++ )
                        {

                        newColumn = startColumn - ( newRow - startRow ) ;

                        if ( !checkAxisMove( newRow, newColumn, playerMatrix ) )
                            {
                            return false ;
                            }
                        }
                    }
                else
                    {
                    return false ;
                    }
                }
            // if moved southeast
            else if ( ( desRow > startRow ) && ( desColumn > startColumn ) )
                {

                if ( ( startRow - desRow ) == ( startColumn - desColumn ) )
                    {

                    for ( int newRow = ( startRow + 1 ) ;
                          newRow < desRow ;
                          newRow++ )
                        {

                        newColumn = startColumn + ( newRow - startRow ) ;

                        if ( !checkAxisMove( newRow, newColumn, playerMatrix ) )
                            {
                            return false ;
                            }
                        }
                    }
                else
                    {
                    return false ;
                    }
                }
            // if not a diagonal move
            else
                {

                this.strErrorMsg = "Should never see this error message" ;
                return false ;
                }
            }

        this.finalDesRow = desRow ;
        this.finalDesColumn = desColumn ;

        return true ;
        }


    public int getDesRow()
        {
        return this.finalDesRow ;
        }


    public int getDesColumn()
        {
        return this.finalDesColumn ;
        }


    public String getErrorMsg()
        {
        return this.strErrorMsg ;
        }
    } // end class ChessPieces