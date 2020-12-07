
package Game ;

/**
 * @author Veerle
 */

import java.awt.BorderLayout ;
import java.awt.Color ;
import java.awt.Container ;
import java.awt.Dimension ;
import java.awt.Image ;
import java.awt.MediaTracker ;
import java.awt.event.WindowEvent ;
import java.awt.event.WindowFocusListener ;

import javax.swing.JFrame ;
import javax.swing.JOptionPane ;
import javax.swing.JPanel ;

@SuppressWarnings( "javadoc" )
public class ChessGameGUI implements WindowFocusListener
    {

    public WindowChessBoard mainChessBoard ;
    private CreateAppletImage createImage ;

    private final String[] strRedPieces =
                                    { "wpawn.gif", "wrook.gif", "wknight.gif",
                                      "wbishop.gif", "wqueen.gif", "wking.gif" } ;
    private final String[] strBluePieces =
                                    { "bpawn.gif", "brook.gif", "bknight.gif",
                                      "bbishop.gif", "bqueen.gif", "bking.gif" } ;

    private final Color clrBlue = new Color( 75, 141, 221 ) ;
    private MediaTracker mt ;

    public ChessGameGUI()
        {}


// FOR FUTURE REFERENCE //// FOR FUTURE REFERENCE //// FOR FUTURE REFERENCE //
    @SuppressWarnings( "unused" )
    public Container createGUI( final JFrame mainApp )
        {

        final JPanel panRoot = new JPanel( new BorderLayout() ) ;
        panRoot.setOpaque( true ) ;
        panRoot.setPreferredSize( new Dimension( 500, 500 ) ) ;

        this.mainChessBoard = new WindowChessBoard() ;
        this.createImage = new CreateAppletImage() ;

        this.mainChessBoard.setSize( new Dimension( 500, 500 ) ) ;

        // chess piece images
        try
            {

            final Image[] imgRed = new Image[ 6 ] ;
            final Image[] imgBlue = new Image[ 6 ] ;
            this.mt = new MediaTracker( mainApp ) ;

            for ( int i = 0 ; i < 6 ; i++ )
                {
                imgRed[ i ] = this.createImage.getImage( this,
                                                         "images/" +
                                                               this.strRedPieces[ i ],
                                                         5000 ) ;
                imgBlue[ i ] = this.createImage.getImage( this,
                                                          "images/" +
                                                                this.strBluePieces[ i ],
                                                          5000 ) ;
                this.mt.addImage( imgRed[ i ], 0 ) ;
                this.mt.addImage( imgBlue[ i ], 0 ) ;
                }

            try
                {
                this.mt.waitForID( 0 ) ;
                }
            catch ( final InterruptedException e )
                {}

            this.mainChessBoard.setupImages( imgRed, imgBlue ) ;

            }
        catch ( final NullPointerException e )
            {

            JOptionPane.showMessageDialog( null,
                                           "Unable to load images. There should be a folder called images with all the chess pieces in it. Try downloading this programme again",
                                           "Unable to load images",
                                           JOptionPane.WARNING_MESSAGE ) ;
            }

        final JPanel panBottomHalf = new JPanel( new BorderLayout() ) ;
        panRoot.add( this.mainChessBoard, BorderLayout.NORTH ) ;
        panRoot.add( panBottomHalf, BorderLayout.SOUTH ) ;
        panRoot.setBackground( this.clrBlue ) ;
        panBottomHalf.setBackground( this.clrBlue ) ;

        return panRoot ;
        }
// FOR FUTURE REFERENCE //// FOR FUTURE REFERENCE //// FOR FUTURE REFERENCE //


    @Override
    public void windowGainedFocus( final WindowEvent e )
        {
        this.mainChessBoard.gotFocus() ;
        }


    @Override
    public void windowLostFocus( final WindowEvent e )
        {}

    } // end class ChessGameGUI
