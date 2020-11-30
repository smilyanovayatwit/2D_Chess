
package Game ;

import java.awt.BorderLayout ;
import java.awt.Color ;
import java.awt.Container ;
import java.awt.Dimension ;
import java.awt.GridLayout ;
import java.awt.Image ;
import java.awt.MediaTracker ;
import java.awt.event.ActionEvent ;
import java.awt.event.ActionListener ;
import java.awt.event.KeyEvent ;
import java.awt.event.KeyListener ;
import java.awt.event.WindowEvent ;
import java.awt.event.WindowFocusListener ;

import javax.swing.JButton ;
import javax.swing.JFrame ;
import javax.swing.JLabel ;
import javax.swing.JOptionPane ;
import javax.swing.JPanel ;
import javax.swing.JTextField ;

@SuppressWarnings( "javadoc" )
public class ChessGameGUI implements ActionListener, KeyListener, WindowFocusListener
    {

    private WindowChessBoard mainChessBoard ;
    private CreateAppletImage createImage ;
    private JButton cmdSetNames ;
    private JTextField txtPlayerOne, txtPlayerTwo ;
    public static JLabel lblPlayerOne ;

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
        panRoot.setPreferredSize( new Dimension( 550, 650 ) ) ;

        this.mainChessBoard = new WindowChessBoard() ;
        this.createImage = new CreateAppletImage() ;

        this.mainChessBoard.setSize( new Dimension( 500, 500 ) ) ;

        this.cmdSetNames = new JButton( "Set Names" ) ;

        this.cmdSetNames.addActionListener( this ) ;

        // name fields
        this.txtPlayerOne = new JTextField( "Client 1", 10 ) ;
        this.txtPlayerTwo = new JTextField( "Client 2", 10 ) ;

        this.txtPlayerOne.addKeyListener( this ) ;
        this.txtPlayerTwo.addKeyListener( this ) ;

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

            this.cmdSetNames.setEnabled( false ) ;
            }

        final JPanel panBottomHalf = new JPanel( new BorderLayout() ) ;
        final JPanel panNameArea = new JPanel( new GridLayout( 3, 1, 2, 2 ) ) ;
        final JPanel panPlayerOne = new JPanel() ;
        final JPanel panPlayerTwo = new JPanel() ;
        final JPanel panNameButton = new JPanel() ;
        final JPanel panNewGame = new JPanel() ;

        panRoot.add( this.mainChessBoard, BorderLayout.NORTH ) ;
        panRoot.add( panBottomHalf, BorderLayout.SOUTH ) ;
        panBottomHalf.add( panNameArea, BorderLayout.WEST ) ;
        panNameArea.add( panPlayerOne ) ;
        panPlayerOne.add( this.txtPlayerOne ) ;
        panNameArea.add( panPlayerTwo ) ;
        panPlayerTwo.add( this.txtPlayerTwo ) ;
        panNameArea.add( panNameButton ) ;
        panNameButton.add( this.cmdSetNames ) ;
        panBottomHalf.add( panNewGame, BorderLayout.SOUTH ) ;
        panRoot.setBackground( this.clrBlue ) ;
        panBottomHalf.setBackground( this.clrBlue ) ;
        panNameArea.setBackground( this.clrBlue ) ;
        panPlayerOne.setBackground( this.clrBlue ) ;
        panPlayerTwo.setBackground( this.clrBlue ) ;
        panNameButton.setBackground( this.clrBlue ) ;
        panNewGame.setBackground( this.clrBlue ) ;

        // ChessGameGUI.lblPlayerOne.setBackground( new Color( 236, 17, 17 ) ) ; //
        // red
        // this.lblPlayerTwo.setBackground( new Color( 17, 27, 237 ) ) ; // blue

        return panRoot ;

        }
// FOR FUTURE REFERENCE //// FOR FUTURE REFERENCE //// FOR FUTURE REFERENCE //


    @Override
    public void actionPerformed( final ActionEvent e )
        {

        // if button for "Set Names" is pressed
        if ( e.getSource() == this.cmdSetNames )
            {
            // default name for player 1
            if ( this.txtPlayerOne.getText().equals( "" ) )
                {
                this.txtPlayerOne.setText( "Client 1" ) ;
                }

            // default name for player 2
            if ( this.txtPlayerTwo.getText().equals( "" ) )
                {
                this.txtPlayerTwo.setText( "Client 2" ) ;
                }

            // sets names as entered
            this.mainChessBoard.setNames( this.txtPlayerOne.getText(),
                                          this.txtPlayerTwo.getText() ) ;

            }

        }


    @Override
    public void keyTyped( final KeyEvent e )
        {

        String strBuffer = "" ;
        final char c = e.getKeyChar() ;

        if ( e.getSource() == this.txtPlayerOne )
            {
            strBuffer = this.txtPlayerOne.getText() ;
            }
        else
            {
            strBuffer = this.txtPlayerTwo.getText() ;
            }

        // sets limit for name length
        if ( ( strBuffer.length() == 20 ) &&
             ! ( ( c == KeyEvent.VK_BACK_SPACE ) || ( c == KeyEvent.VK_DELETE ) ) )
            {
            e.consume() ;
            }

        }


    @Override
    public void keyPressed( final KeyEvent e )
        {}


    @Override
    public void keyReleased( final KeyEvent e )
        {}


    @Override
    public void windowGainedFocus( final WindowEvent e )
        {
        this.mainChessBoard.gotFocus() ;
        }


    @Override
    public void windowLostFocus( final WindowEvent e )
        {}

    } // end class ChessGameGUI
