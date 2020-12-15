
package Multiplayer ;

/**
 * @author Tiffany Phan and Yuliya Smilyanova
 */
import java.awt.event.MouseAdapter ;
import java.awt.event.MouseEvent ;
import java.awt.event.MouseListener ;
import java.awt.event.WindowAdapter ;
import java.awt.event.WindowEvent ;
import java.awt.event.WindowListener ;
import java.io.BufferedReader ;
import java.io.DataOutputStream ;
import java.io.IOException ;
import java.io.InputStreamReader ;
import java.net.Socket ;
import java.util.Scanner ;

import javax.swing.JFrame ;
import javax.swing.WindowConstants ;

import Game.ChessBoard ;
import Game.ChessGameGUI ;

@SuppressWarnings( "javadoc" )
public class ChessClient extends ChessBoard
    {

    static ChessGameGUI chessWindow ;

    private static final long serialVersionUID = 8129795847503729878L ;

    public static int currentPlayer = 1 ;

    private static int startRow = 0 ;
    private static int startColumn = 0 ;
    private static int endRow = 0 ;
    private static int endColumn = 0 ;

    private static boolean hasWon = false ;

    // for writing to the server
    static class WriteThread implements Runnable
        {

        DataOutputStream outToServer ;

        @SuppressWarnings( "hiding" )
        public WriteThread( final DataOutputStream outToServer )
            {
            this.outToServer = outToServer ;
            }


        @Override
        public void run()
            {
            try
                {
                processRequest() ;
                }
            catch ( final Exception e )
                {
                System.out.println( e ) ;
                }

            }


        // console input from client to send to server
        // mostly for sending the {quit} message to server
        @SuppressWarnings( "resource" )
        private void processRequest() throws Exception
            {
            final Scanner s = new Scanner( System.in ) ;
            final String inputMessage = s.nextLine() ;
            this.outToServer.writeBytes( inputMessage + "\r\n" ) ;
            }
        }

    // play the game
    @SuppressWarnings( "resource" )
    public static void main( final String argv[] ) throws Exception
        {
        final Socket connectionSocket = new Socket( "localhost", 12345 ) ;

        final DataOutputStream outToServer =
                                        new DataOutputStream( connectionSocket.getOutputStream() ) ;
        final BufferedReader inFromServer =
                                        new BufferedReader( new InputStreamReader( connectionSocket.getInputStream() ) ) ;

        // get welcome message from the server
        final String welcomeMessage = inFromServer.readLine() ;
        System.out.println( welcomeMessage ) ;

        // enter name and send it to the server
        final Scanner s = new Scanner( System.in ) ;
        final String name = s.nextLine() ;
        outToServer.writeBytes( name + "\r\n" ) ;

        // get hello message from the server
        final String helloMessage = inFromServer.readLine() ;
        System.out.println( helloMessage ) ;

        // creates the game window and sets frame settings
        JFrame.setDefaultLookAndFeelDecorated( true ) ;
        final JFrame frame = new JFrame( "2D Chess" ) ;
        chessWindow = new ChessGameGUI() ;
        frame.setContentPane( chessWindow.createGUI( frame ) ) ;
        frame.addWindowFocusListener( chessWindow ) ;
        frame.setResizable( false ) ;
        frame.setVisible( true ) ;
        frame.pack() ;
        frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE ) ;

        // window event
        final WindowListener listener = new WindowAdapter()
            {

            // closing the window will also send {quit} message to server
            @Override
            public void windowClosing( final WindowEvent evt )
                {
                try
                    {
                    outToServer.writeBytes( "{quit}\r\n" ) ;
                    }
                catch ( final IOException e )
                    {
                    e.printStackTrace() ;
                    }
                }
            } ;
        frame.addWindowListener( listener ) ;

        // start write thread
        final WriteThread write = new WriteThread( outToServer ) ;
        final Thread thread = new Thread( write ) ;
        thread.start() ;

        // mouse events
        final MouseListener mlistener = new MouseAdapter()
            {

            // gets starting coordinates of the chess piece and sends them to the
            // server
            @Override
            public void mousePressed( final MouseEvent e )
                {
                if ( !ChessClient.hasWon )
                    {
                    final int x = e.getX() ;
                    final int y = e.getY() ;

                    // in the correct bounds
                    if ( ( ( x > 60 ) && ( x < 430 ) ) &&
                         ( ( y > 60 ) && ( y < 430 ) ) )
                        {
                        ChessClient.startRow = findWhichTileSelected( y ) ;
                        ChessClient.startColumn = findWhichTileSelected( x ) ;
                        final int piece = chessWindow.mainChessBoard.cellMatrix.getPieceCell( ChessClient.startRow,
                                                                                              ChessClient.startColumn ) ;

                        // sends START message to the server with the coordinates and
                        // the chess piece
                        try
                            {
                            outToServer.writeBytes( "START" + " " +
                                                    ChessClient.startRow + " " +
                                                    ChessClient.startColumn + " " +
                                                    piece + "\r\n" ) ;
                            }
                        catch ( final IOException e1 )
                            {
                            e1.printStackTrace() ;
                            }
                        }
                    }
                }


            // gets ending coordinates of the chess piece and sends them to the
            // server
            @Override
            public void mouseReleased( final MouseEvent e )
                {
                final int x = e.getX() ;
                final int y = e.getY() ;
                ChessClient.endRow = findWhichTileSelected( y ) ;
                ChessClient.endColumn = findWhichTileSelected( x ) ;
                final int p = chessWindow.mainChessBoard.cellMatrix.getPieceCell( ChessClient.endRow,
                                                                                  ChessClient.endColumn ) ;

                // sends END message to the server with the coordinates and the chess
                // piece
                try
                    {
                    outToServer.writeBytes( "END" + " " + ChessClient.endRow + " " +
                                            ChessClient.endColumn + " " + p +
                                            "\r\n" ) ;
                    }
                catch ( final IOException e1 )
                    {
                    e1.printStackTrace() ;
                    }
                }
            } ;
        chessWindow.mainChessBoard.addMouseListener( mlistener ) ;

        // receive different types of broadcast messages from the server
        String serverMessage = null ;
        while ( true )
            {
            while ( ( serverMessage = inFromServer.readLine() ) != null )
                {
                // {quit} message to close the game
                if ( serverMessage.equals( "{quit}" ) )
                    {
                    System.exit( 0 ) ;
                    break ;
                    }

                // START and END messages to update the chess board for both players
                else if ( serverMessage.startsWith( "START" ) )
                    {
                    int row, column ;
                    final String[] message = serverMessage.split( " " ) ;
                    if ( message[ 0 ].equals( "START" ) )
                        {
                        row = Integer.parseInt( message[ 1 ] ) ;
                        column = Integer.parseInt( message[ 2 ] ) ;
                        processClear( row, column ) ;
                        }
                    }
                else if ( serverMessage.startsWith( "END" ) )
                    {
                    int row, column, piece ;
                    final String[] message = serverMessage.split( " " ) ;
                    if ( message[ 0 ].equals( "END" ) )
                        {
                        row = Integer.parseInt( message[ 1 ] ) ;
                        column = Integer.parseInt( message[ 2 ] ) ;
                        piece = Integer.parseInt( message[ 3 ] ) ;
                        processMove( row, column, piece ) ;
                        }
                    }

                // 1 and 2 messages to update the current player
                else if ( serverMessage.startsWith( "1" ) ||
                          serverMessage.startsWith( "2" ) )
                    {
                    currentPlayer = Integer.parseInt( serverMessage ) ;
                    chessWindow.mainChessBoard.currentPlayer = currentPlayer ;
                    System.out.println( "Opponent has moved!" ) ;
                    }

                // other messages
                else
                    {
                    System.out.println( serverMessage ) ;
                    }
                }
            }
        }


    // clears starting coordinates of the chess piece and updates the board
    @SuppressWarnings( "hiding" )
    public static void processClear( final int startRow,
                                     final int startColumn )
        {
        // the chess piece for 6 is just an empty space
        chessWindow.mainChessBoard.cellMatrix.setPieceCell( startRow,
                                                            startColumn,
                                                            6 ) ;
        // no current player in that space
        chessWindow.mainChessBoard.cellMatrix.setPlayerCell( startRow,
                                                             startColumn,
                                                             0 ) ;
        chessWindow.mainChessBoard.repaint() ;
        }


    // adds/moves the chess piece to its new coordinates and updates the board
    @SuppressWarnings( "hiding" )
    public static void processMove( final int endRow,
                                    final int endColumn,
                                    final int piece )
        {
        chessWindow.mainChessBoard.cellMatrix.setPieceCell( endRow,
                                                            endColumn,
                                                            piece ) ;

        // retains color of the chess piece after being moved
        chessWindow.mainChessBoard.cellMatrix.setPlayerCell( endRow,
                                                             endColumn,
                                                             currentPlayer ) ;
        chessWindow.mainChessBoard.repaint() ;
        }
    }