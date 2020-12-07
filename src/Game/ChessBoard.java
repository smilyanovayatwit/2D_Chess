
package Game ;

/**
 * @author Veerle
 */

// Standard chess board which can be used for chess, draughts etc
import java.awt.Canvas ;
import java.awt.Color ;
import java.awt.Graphics ;
import java.util.Vector ;

@SuppressWarnings( "javadoc" )
public class ChessBoard extends Canvas
    {

    private static final long serialVersionUID = -8965615572309003893L ;
    protected PaintInstructions currentInstruction = null ;
    protected Vector<PaintInstructions> vecPaintInstructions = new Vector<>() ;

    public void chessBoard()
        {}


    @Override
    public void update( final Graphics g )
        {
        paint( g ) ;
        }


    @Override
    public void paint( final Graphics g )
        {

        if ( this.vecPaintInstructions.size() == 0 )
            {

            g.setColor( new Color( 75, 141, 221 ) ) ; // Light blue
            g.fillRect( 0, 0, 500, 50 ) ; // North border
            g.fillRect( 0, 0, 50, 500 ) ; // West border
            g.fillRect( 0, 450, 500, 50 ) ; // South border
            g.fillRect( 450, 0, 50, 500 ) ; // East border

            this.currentInstruction = new PaintInstructions( 0, 0, 8 ) ;
            this.vecPaintInstructions.addElement( this.currentInstruction ) ;

            }

        g.setColor( new Color( 75, 141, 221 ) ) ;
        g.fillRect( 50, 450, 450, 50 ) ; // Paint over the current status text

        for ( int i = 0 ; i < this.vecPaintInstructions.size() ; i++ )
            {

            this.currentInstruction = this.vecPaintInstructions.elementAt( i ) ;
            final int startRow = this.currentInstruction.getStartRow() ;
            final int startColumn = this.currentInstruction.getStartColumn() ;
            final int rowCells = this.currentInstruction.getRowCells() ;
            final int columnCells = this.currentInstruction.getColumnCells() ;

            for ( int row = startRow ; row < ( startRow + rowCells ) ; row++ )
                {
                for ( int column = startColumn ;
                      column < ( startColumn + columnCells ) ;
                      column++ )
                    {
                    drawTile( row, column, g ) ;
                    }
                }
            }

        drawExtra( g ) ;
        }


// FOR FUTURE REFERENCE //// FOR FUTURE REFERENCE //// FOR FUTURE REFERENCE //
    private static void drawTile( final int row,
                                  final int column,
                                  final Graphics g )
        {

        // for changing color of the board
        final Color white = new Color( 255, 255, 255 ) ;
        final Color other = new Color( 50, 50, 50 ) ;

        // fills color for odd rows of the chess board
        if ( ( ( row + 1 ) % 2 ) == 0 )
            {
            // white squares
            if ( ( ( column + 1 ) % 2 ) == 0 )
                {
                g.setColor( white ) ;
                }
            // color gray
            else
                {
                g.setColor( other ) ;
                }
            }
        // fills color for even/other rows of the chess board
        else
            {
            // color black
            if ( ( ( column + 1 ) % 2 ) == 0 )
                {
                g.setColor( other ) ;
                }
            // color white
            else
                {
                g.setColor( white ) ;
                }
            }

        // fills the grid with the 2 colors
        g.fillRect( ( 50 + ( column * 50 ) ), ( 50 + ( row * 50 ) ), 50, 50 ) ;
        }
// FOR FUTURE REFERENCE //// FOR FUTURE REFERENCE //// FOR FUTURE REFERENCE //


    // Protected means it can only be used by this class, and classes extending it
    // Any class extending the chess board can use this method to add extra things
    // like player pieces
    protected void drawExtra( final Graphics g )
        {}


    // finds which tile the mouse is over
    protected static int findWhichTileSelected( final int coor )
        {

        for ( int i = 0 ; i < 8 ; i++ )
            {
            if ( ( coor >= ( 50 + ( i * 50 ) ) ) &&
                 ( coor <= ( 100 + ( i * 50 ) ) ) )
                {
                return i ;
                }
            }

        return -1 ;
        }

    } // end class Board