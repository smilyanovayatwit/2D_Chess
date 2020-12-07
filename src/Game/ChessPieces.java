
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

        // Moving along a straight axis (rook, queen)
        if ( straightAxis )
            {

            // Moving along the same column
            if ( ( startColumn == desColumn ) && ( startRow != desRow ) )
                {

                // Moving N
                if ( desRow < startRow )
                    {

                    // Checks each cell between the start row - 1 (since don't need
                    // to check the cell it is in) to the destination cell
                    for ( int newRow = ( startRow - 1 ) ;
                          newRow > desRow ;
                          newRow-- )
                        {

                        // Checks the cell is empty
                        if ( !checkAxisMove( newRow, desColumn, playerMatrix ) )
                            {
                            return false ;
                            }
                        }
                    }
                else
                    { // Moving S

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
            else if ( ( startRow == desRow ) && ( startColumn != desColumn ) )
                { // Moving along the same row

                // Moving W
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
                else
                    { // Moving E

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
            else
                { // If moved diagonally

                this.strErrorMsg = "Should not see this error message" ;
                return false ;
                }
            }
        else
            { // Moving diagonal (bishop/queen)

            // Default error message
            this.strErrorMsg = "The destination is not on the same diagonal line" ;
            int newColumn = 0 ;

            // If moved NW
            if ( ( desRow < startRow ) && ( desColumn < startColumn ) )
                {

                // The number of cells moved horizontal should equal the number of
                // cells moved vertical
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
            else if ( ( desRow < startRow ) && ( desColumn > startColumn ) )
                { // If moved NE

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
            else if ( ( desRow > startRow ) && ( desColumn < startColumn ) )
                { // If moved SW

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
            else if ( ( desRow > startRow ) && ( desColumn > startColumn ) )
                { // If moved SE

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
            else
                { // Not a diagonal move

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
    }