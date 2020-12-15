
package Game ;

/**
 * @author Veerle
 */

import java.awt.Color ;
import java.awt.Graphics ;
import java.awt.Image ;
import java.awt.event.MouseEvent ;
import java.awt.event.MouseListener ;
import java.awt.event.MouseMotionListener ;

import javax.swing.JDialog ;
import javax.swing.JOptionPane ;

import Pieces.Bishop ;
import Pieces.King ;
import Pieces.Knight ;
import Pieces.Pawn ;
import Pieces.Queen ;
import Pieces.Rook ;

@SuppressWarnings( "javadoc" )
public class WindowChessBoard extends ChessBoard
    implements MouseListener, MouseMotionListener
    {

    private static final long serialVersionUID = 8129795847503729878L ;

    private final int refreshRate = 5 ; // amount of pixels moved before screen is
                                        // refreshed

    private final Image[][] imgPlayer = new Image[ 2 ][ 6 ] ;
    public final String[] strPlayerName = { "Client 1", "Client 2" } ;
    public String strStatusMsg = "" ;
    public final CellMatrix cellMatrix = new CellMatrix() ;
    public int currentPlayer = 1 ;

    private int startRow = 0 ;

    private int startColumn = 0 ;

    public int pieceBeingDragged = 0 ;

    private int currentX = 0 ;

    private int currentY = 0 ;

    private int refreshCounter = 0 ;
    private boolean hasWon = false ;

    public boolean isDragging = false ;

    private final Pawn pawnObject = new Pawn() ;
    private final Rook rookObject = new Rook() ;
    private final Knight knightObject = new Knight() ;
    private final Bishop bishopObject = new Bishop() ;
    private final Queen queenObject = new Queen() ;
    private final King kingObject = new King() ;

    public WindowChessBoard()
        {
        addMouseListener( this ) ;
        addMouseMotionListener( this ) ;
        }


    public String getPlayerMsg()
        {

        if ( this.hasWon )
            {

            return "Congrats " + this.strPlayerName[ this.currentPlayer - 1 ] +
                   ", you are the winner!" ;

            }

        return "It is " + this.strPlayerName[ this.currentPlayer - 1 ] + "'s move" ;
        }


    public void resetBoard()
        {
        this.hasWon = false ;
        this.currentPlayer = 1 ;
        this.strStatusMsg = getPlayerMsg() ;
        this.cellMatrix.resetMatrix() ;
        repaint() ;
        }


    public void setupImages( final Image[] imgRed,
                             final Image[] imgBlue )
        {
        this.imgPlayer[ 0 ] = imgRed ;
        this.imgPlayer[ 1 ] = imgBlue ;
        resetBoard() ;
        }


    public void setNames( final String strPlayer1Name,
                          final String strPlayer2Name )
        {
        this.strPlayerName[ 0 ] = strPlayer1Name ;
        this.strPlayerName[ 1 ] = strPlayer2Name ;
        repaint() ;
        }


    @SuppressWarnings( "unused" )
    @Override
    protected void drawExtra( final Graphics g )
        {

        for ( int i = 0 ; i < this.vecPaintInstructions.size() ; i++ )
            {

            this.currentInstruction = this.vecPaintInstructions.elementAt( i ) ;
            final int paintStartRow = this.currentInstruction.getStartRow() ;
            final int paintStartColumn = this.currentInstruction.getStartColumn() ;
            final int rowCells = this.currentInstruction.getRowCells() ;
            final int columnCells = this.currentInstruction.getColumnCells() ;

            for ( int row = paintStartRow ;
                  row < ( paintStartRow + rowCells ) ;
                  row++ )
                {

                for ( int column = paintStartColumn ;
                      column < ( paintStartColumn + columnCells ) ;
                      column++ )
                    {

                    final int playerCell = this.cellMatrix.getPlayerCell( row,
                                                                          column ) ;
                    final int pieceCell = this.cellMatrix.getPieceCell( row,
                                                                        column ) ;

                    if ( playerCell != 0 )
                        {

                        try
                            {
                            g.drawImage( this.imgPlayer[ playerCell -
                                                         1 ][ pieceCell ],
                                         ( ( column + 1 ) * 50 ),
                                         ( ( row + 1 ) * 50 ),
                                         this ) ;
                            }
                        catch ( final ArrayIndexOutOfBoundsException e )
                            {}
                        }
                    }
                }
            }

        if ( this.isDragging )
            {
            g.drawImage( this.imgPlayer[ this.currentPlayer -
                                         1 ][ this.pieceBeingDragged ],
                         ( this.currentX - 25 ),
                         ( this.currentY - 25 ),
                         this ) ;
            }

        g.setColor( new Color( 0, 0, 0 ) ) ;

        this.vecPaintInstructions.clear() ; // clear all paint instructions
        }


    public void newGame()
        {
        resetBoard() ;
        }


    @SuppressWarnings( "unused" )
    public void checkMove( final int desRow,
                           final int desColumn )
        {

        boolean legalMove = false ;

        // if moving the piece onto one of your own pieces
        if ( this.cellMatrix.getPlayerCell( desRow, desColumn ) ==
             this.currentPlayer )
            {

            this.strStatusMsg = "Can not move onto a piece that is yours" ;

            }
        // if not, check is the move is legal for each piece
        else
            {

            switch ( this.pieceBeingDragged )
                {
                case 0:
                    legalMove = this.pawnObject.legalMove( this.startRow,
                                                           this.startColumn,
                                                           desRow,
                                                           desColumn,
                                                           this.cellMatrix.getPlayerMatrix(),
                                                           this.currentPlayer ) ;
                    break ;
                case 1:
                    legalMove = this.rookObject.legalMove( this.startRow,
                                                           this.startColumn,
                                                           desRow,
                                                           desColumn,
                                                           this.cellMatrix.getPlayerMatrix() ) ;
                    break ;
                case 2:
                    legalMove = this.knightObject.legalMove( this.startRow,
                                                             this.startColumn,
                                                             desRow,
                                                             desColumn,
                                                             this.cellMatrix.getPlayerMatrix() ) ;
                    break ;
                case 3:
                    legalMove = this.bishopObject.legalMove( this.startRow,
                                                             this.startColumn,
                                                             desRow,
                                                             desColumn,
                                                             this.cellMatrix.getPlayerMatrix() ) ;
                    break ;
                case 4:
                    legalMove = this.queenObject.legalMove( this.startRow,
                                                            this.startColumn,
                                                            desRow,
                                                            desColumn,
                                                            this.cellMatrix.getPlayerMatrix() ) ;
                    break ;
                case 5:
                    legalMove = this.kingObject.legalMove( this.startRow,
                                                           this.startColumn,
                                                           desRow,
                                                           desColumn,
                                                           this.cellMatrix.getPlayerMatrix() ) ;
                    break ;
                default:
                    break ;
                }
            }

        // if the move is legal
        if ( legalMove )
            {

            int newDesRow = 0 ;
            int newDesColumn = 0 ;

            // get the piece being dragged
            switch ( this.pieceBeingDragged )
                {

                case 0:
                    newDesRow = this.pawnObject.getDesRow() ;
                    newDesColumn = this.pawnObject.getDesColumn() ;
                    break ;
                case 1:
                    newDesRow = this.rookObject.getDesRow() ;
                    newDesColumn = this.rookObject.getDesColumn() ;
                    break ;
                case 2:
                    newDesRow = this.knightObject.getDesRow() ;
                    newDesColumn = this.knightObject.getDesColumn() ;
                    break ;
                case 3:
                    newDesRow = this.bishopObject.getDesRow() ;
                    newDesColumn = this.bishopObject.getDesColumn() ;
                    break ;
                case 4:
                    newDesRow = this.queenObject.getDesRow() ;
                    newDesColumn = this.queenObject.getDesColumn() ;
                    break ;
                case 5:
                    newDesRow = this.kingObject.getDesRow() ;
                    newDesColumn = this.kingObject.getDesColumn() ;
                    break ;
                default:
                    break ;
                }

            // update the player for the current piece
            this.cellMatrix.setPlayerCell( newDesRow,
                                           newDesColumn,
                                           this.currentPlayer ) ;

            // if pawn gets to the other side of the board
            if ( ( this.pieceBeingDragged == 0 ) &&
                 ( ( newDesRow == 0 ) || ( newDesRow == 7 ) ) )
                {

                boolean canPass = false ;
                int newPiece = 2 ;
                String strNewPiece = "Rook" ;
                final String[] strPieces = { "Rook", "Knight", "Bishop", "Queen" } ;
                final JOptionPane digBox = new JOptionPane( "Choose the piece to change your pawn into",
                                                            JOptionPane.QUESTION_MESSAGE,
                                                            JOptionPane.OK_CANCEL_OPTION,
                                                            null,
                                                            strPieces,
                                                            "Rook" ) ;
                final JDialog dig = digBox.createDialog( null,
                                                         "Pawn at end of board" ) ;

                do
                    {
                    dig.setVisible( true ) ;

                    try
                        {
                        strNewPiece = digBox.getValue().toString() ;

                        for ( int i = 0 ; i < strPieces.length ; i++ )
                            {

                            if ( strNewPiece.equalsIgnoreCase( strPieces[ i ] ) )
                                {
                                canPass = true ;
                                newPiece = i + 1 ;
                                }
                            }
                        }
                    catch ( final NullPointerException e )
                        {
                        canPass = false ;
                        }

                    }
                while ( canPass == false ) ;

                this.cellMatrix.setPieceCell( newDesRow, newDesColumn, newPiece ) ;

                }
            // update the board
            else
                {
                this.cellMatrix.setPieceCell( newDesRow,
                                              newDesColumn,
                                              this.pieceBeingDragged ) ;
                }

            // if checkmate
            if ( this.cellMatrix.checkWinner( this.currentPlayer ) )
                {

                this.hasWon = true ;
                this.strStatusMsg = getPlayerMsg() ;

                }
            // update the current player
            else
                {

                if ( this.currentPlayer == 1 )
                    {

                    this.currentPlayer = 2 ;

                    }
                else
                    {

                    this.currentPlayer = 1 ;

                    }
                this.strStatusMsg = getPlayerMsg() ;
                }
            }
        // error messages for each of the pieces if not a legal move
        else
            {
            switch ( this.pieceBeingDragged )
                {
                case 0:
                    this.strStatusMsg = this.pawnObject.getErrorMsg() ;
                    break ;
                case 1:
                    this.strStatusMsg = this.rookObject.getErrorMsg() ;
                    break ;
                case 2:
                    this.strStatusMsg = this.knightObject.getErrorMsg() ;
                    break ;
                case 3:
                    this.strStatusMsg = this.bishopObject.getErrorMsg() ;
                    break ;
                case 4:
                    this.strStatusMsg = this.queenObject.getErrorMsg() ;
                    break ;
                case 5:
                    this.strStatusMsg = this.kingObject.getErrorMsg() ;
                    break ;
                default:
                    break ;
                }
            unsucessfullDrag( desRow, desColumn ) ;
            }
        }


    private void unsucessfullDrag( final int desRow,
                                   final int desColumn )
        {

        this.cellMatrix.setPieceCell( this.startRow,
                                      this.startColumn,
                                      this.pieceBeingDragged ) ;
        this.cellMatrix.setPlayerCell( this.startRow,
                                       this.startColumn,
                                       this.currentPlayer ) ;
        }


    public void updatePaintInstructions( final int desRow,
                                         final int desColumn )
        {
        this.currentInstruction = new PaintInstructions( this.startRow,
                                                         this.startColumn,
                                                         1 ) ;
        this.vecPaintInstructions.addElement( this.currentInstruction ) ;

        this.currentInstruction = new PaintInstructions( desRow, desColumn ) ;
        this.vecPaintInstructions.addElement( this.currentInstruction ) ;
        }


    @Override
    public void mouseClicked( final MouseEvent e )
        {}


    @Override
    public void mouseEntered( final MouseEvent e )
        {}


    @Override
    public void mouseExited( final MouseEvent e )
        {
        mouseReleased( e ) ;
        }


    @Override
    public void mousePressed( final MouseEvent e )
        {

        // gets starting coordinates of the chess piece
        if ( !this.hasWon )
            {

            final int x = e.getX() ;
            final int y = e.getY() ;

            // if in the correct bounds
            if ( ( ( x > 60 ) && ( x < 430 ) ) && ( ( y > 60 ) && ( y < 430 ) ) )
                {
                this.startRow = findWhichTileSelected( y ) ;
                this.startColumn = findWhichTileSelected( x ) ;

                if ( this.cellMatrix.getPlayerCell( this.startRow,
                                                    this.startColumn ) ==
                     this.currentPlayer )
                    {
                    this.pieceBeingDragged = this.cellMatrix.getPieceCell( this.startRow,
                                                                           this.startColumn ) ;
                    this.cellMatrix.setPieceCell( this.startRow,
                                                  this.startColumn,
                                                  6 ) ;
                    this.cellMatrix.setPlayerCell( this.startRow,
                                                   this.startColumn,
                                                   0 ) ;
                    this.isDragging = true ;

                    }
                else
                    {
                    this.isDragging = false ;
                    }
                }
            }
        }


    @Override
    public void mouseReleased( final MouseEvent e )
        {

        // gets ending coordinates of the chess piece
        if ( this.isDragging )
            {

            this.isDragging = false ;

            final int desRow = findWhichTileSelected( this.currentY ) ;
            final int desColumn = findWhichTileSelected( this.currentX ) ;
            checkMove( desRow, desColumn ) ;
            repaint() ;
            }
        }


    @Override
    public void mouseDragged( final MouseEvent e )
        {

        // follow the piece and update the board
        if ( this.isDragging )
            {

            final int x = e.getX() ;
            final int y = e.getY() ;

            // if in the correct bounds
            if ( ( ( x > 60 ) && ( x < 430 ) ) && ( ( y > 60 ) && ( y < 430 ) ) )
                {

                if ( this.refreshCounter >= this.refreshRate )
                    {
                    this.currentX = x ;
                    this.currentY = y ;
                    this.refreshCounter = 0 ;
                    final int desRow = findWhichTileSelected( this.currentY ) ;
                    final int desColumn = findWhichTileSelected( this.currentX ) ;

                    updatePaintInstructions( desRow, desColumn ) ;
                    repaint() ;
                    }
                else
                    {
                    this.refreshCounter++ ;
                    }
                }
            }
        }


    @Override
    public void mouseMoved( final MouseEvent e )
        {}


    public void gotFocus()
        {
        repaint() ;
        }
    } // end class WindowChessBoard