
package Game ;

/**
 * @author Veerle
 */

@SuppressWarnings( "javadoc" )
public class PaintInstructions
    {

    private int startRow = 0, startColumn = 0, rowCells = 0, columnCells = 0 ;

    public PaintInstructions()
        {}


    public PaintInstructions( final int firstRow, final int firstColumn )
        {
        calculateRedrawCells( firstRow, firstColumn ) ;
        }


    @SuppressWarnings( "hiding" )
    public PaintInstructions( final int startRow,
                              final int startColumn,
                              final int numCells )
        {
        this.startRow = startRow ;
        this.startColumn = startColumn ;
        this.rowCells = numCells ;
        this.columnCells = numCells ;
        }


    private void calculateRedrawCells( final int firstRow,
                                       final int firstColumn )
        {

        if ( firstRow == 0 )
            {
            this.startRow = firstRow ;
            }
        else
            {
            this.startRow = firstRow - 1 ;
            }

        if ( firstColumn == 0 )
            {
            this.startColumn = firstColumn ;
            }
        else
            {
            this.startColumn = firstColumn - 1 ;
            }

        if ( firstRow <= 5 )
            {
            this.rowCells = 3 ;
            }
        else
            {
            this.rowCells = 8 - this.startRow ;
            }

        if ( firstColumn <= 5 )
            {
            this.columnCells = 3 ;
            }
        else
            {
            this.columnCells = 8 - this.startColumn ;
            }
        }


    public void setMatrix( final int firstRow,
                           final int firstColumn )
        {
        calculateRedrawCells( firstRow, firstColumn ) ;
        }


    @SuppressWarnings( "hiding" )
    public void setMatrix( final int startRow,
                           final int startColumn,
                           final int numCells )
        {

        this.startRow = startRow ;
        this.startColumn = startColumn ;
        this.rowCells = numCells ;
        this.columnCells = numCells ;
        }


    public int getStartRow()
        {
        return this.startRow ;
        }


    public int getStartColumn()
        {
        return this.startColumn ;
        }


    public int getRowCells()
        {
        return this.rowCells ;
        }


    public int getColumnCells()
        {
        return this.columnCells ;
        }
    }