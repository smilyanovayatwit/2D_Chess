
package Game ;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JOptionPane;

/*
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

import javax.swing.BorderFactory ;
import javax.swing.JButton ;
import javax.swing.JFrame ;
import javax.swing.JLabel ;
import javax.swing.JOptionPane ;
import javax.swing.JPanel ;
import javax.swing.JTextField ;
import javax.swing.SwingConstants ;*/

@SuppressWarnings( "javadoc" )
public class ChessGameGUI implements ActionListener, KeyListener, WindowFocusListener
    {

    private WindowChessBoard mainChessBoard ;  // -----------------------
    private CreateAppletImage createImage ;
    private JButton cmdNewGame, cmdSetNames ;
    private JTextField txtPlayerOne, txtPlayerTwo ;
    private JLabel lblPlayerOne, lblPlayerTwo ;
   // private String[] strRedPieces = {"redPawn.gif","redRock.gif","redKnight.gif","redBishop.gif","redQueen.gif","redKing.gif"};
	//private String[] strBluePieces = {"bluePawn.gif","blueRock.gif","blueKnight.gif","blueBishop.gif","blueQueen.gif","blueKing.gif"};
    private String[] strRedPieces = {"wpawn.gif","wrook.gif","wknight.gif","wbishop.gif","wqueen.gif","wking.gif"};
	private String[] strBluePieces = {"bpawn.gif","brook.gif","bknight.gif","bbishop.gif","bqueen.gif","bking.gif"}; 
   /* private final String[] strRedPieces = { "wpawn.gif", "wrook.gif",
            								"wknight.gif", "wbishop.gif",
            								"wqueen.gif", "wking.gif" } ;
    private final String[] strBluePieces = { "bpawn.gif", "brook.gif",
    										 "bknight.gif", "bbishop.gif",
    										 "bqueen.gif", "bking.gif" } ;
    */
    private final Color clrBlue = new Color( 75, 141, 221 ) ;
    private MediaTracker mt ;

    public ChessGameGUI()
        {}


    @SuppressWarnings( "unused" )
    public Container createGUI( final JFrame mainApp )
        {

        final JPanel panRoot = new JPanel( new BorderLayout() ) ;
        panRoot.setOpaque( true ) ;
        panRoot.setPreferredSize( new Dimension( 550, 650 ) ) ;

        this.mainChessBoard = new WindowChessBoard() ; // ---------------------------
        this.createImage = new CreateAppletImage() ;

        this.mainChessBoard.setSize( new Dimension( 500, 500 ) ) ;

        this.cmdNewGame = new JButton( "New Game" ) ;
        this.cmdSetNames = new JButton( "Set Names" ) ;

        this.cmdNewGame.addActionListener( this ) ;
        this.cmdSetNames.addActionListener( this ) ;

        this.txtPlayerOne = new JTextField( "Veerle", 10 ) ;
        this.txtPlayerTwo = new JTextField( "Natasja", 10 ) ;

        this.txtPlayerOne.addKeyListener( this ) ;
        this.txtPlayerTwo.addKeyListener( this ) ;

        this.lblPlayerOne = new JLabel( "    ", SwingConstants.RIGHT ) ;
        this.lblPlayerTwo = new JLabel( "    ", SwingConstants.RIGHT ) ;

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
            this.cmdNewGame.setEnabled( false ) ;
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
        panPlayerOne.add( this.lblPlayerOne ) ;
        panPlayerOne.add( this.txtPlayerOne ) ;
        panNameArea.add( panPlayerTwo ) ;
        panPlayerTwo.add( this.lblPlayerTwo ) ;
        panPlayerTwo.add( this.txtPlayerTwo ) ;
        panNameArea.add( panNameButton ) ;
        panNameButton.add( this.cmdSetNames ) ;
        panBottomHalf.add( panNewGame, BorderLayout.SOUTH ) ;
        panNewGame.add( this.cmdNewGame ) ;

        panRoot.setBackground( this.clrBlue ) ;
        panBottomHalf.setBackground( this.clrBlue ) ;
        panNameArea.setBackground( this.clrBlue ) ;
        panPlayerOne.setBackground( this.clrBlue ) ;
        panPlayerTwo.setBackground( this.clrBlue ) ;
        panNameButton.setBackground( this.clrBlue ) ;
        panNewGame.setBackground( this.clrBlue ) ;

        this.lblPlayerOne.setBackground( new Color( 236, 17, 17 ) ) ; // red
        this.lblPlayerTwo.setBackground( new Color( 17, 27, 237 ) ) ; // blue

        this.cmdNewGame.setBorder( BorderFactory.createEmptyBorder( 5, 5, 5, 5 ) ) ;

        return panRoot ;

        }


    @Override
    public void actionPerformed( final ActionEvent e )
        {

        if ( e.getSource() == this.cmdSetNames )
            {
            if ( this.txtPlayerOne.getText().equals( "" ) )
                {
                this.txtPlayerOne.setText( "Veerle" ) ;
                }

            if ( this.txtPlayerTwo.getText().equals( "" ) )
                {
                this.txtPlayerTwo.setText( "Natasja" ) ;
                }

            this.mainChessBoard.setNames( this.txtPlayerOne.getText(),
                                          this.txtPlayerTwo.getText() ) ;

            }
        else if ( e.getSource() == this.cmdNewGame )
            {
            this.mainChessBoard.newGame() ;
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

        if ( ( strBuffer.length() > 10 ) &&
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
