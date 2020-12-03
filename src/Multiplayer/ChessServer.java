
package Multiplayer ;

import java.io.BufferedReader ;
import java.io.DataOutputStream ;
import java.io.IOException ;
import java.io.InputStreamReader ;
import java.net.ServerSocket ;
import java.net.Socket ;
import java.util.ArrayList ;
import java.util.HashMap ;
import java.util.Map ;

import Game.ChessGame ;
import Game.WindowChessBoard ;

@SuppressWarnings( "javadoc" )
class ChessServer
    {

    private static Map<String, DataOutputStream> clients = new HashMap<>() ;
    static ChessGame game ;
    static WindowChessBoard board ;

    // writing to the client
    static class WriteThread implements Runnable
        {

        DataOutputStream outToClient ;

        @SuppressWarnings( "hiding" )
        public WriteThread( final DataOutputStream outToClient )
            {
            this.outToClient = outToClient ;
            }


        @Override
        public void run()
            {
            try
                {
                processBoard() ;
                }
            catch ( final Exception e )
                {
                System.out.println( e ) ;
                }

            }


        private void processBoard() throws Exception
            {
            String gameMessage = null ;
            
            while ( true )
                {
                while ( ( gameMessage = board.strStatusMsg ) != null )
                    {
                    if ( gameMessage.startsWith( "Congrats " ) )
                        {
                        broadcast( "", "The game is over!" ) ;
                        break ;
                        }
                    else if ( board.currentPlayer == 1 )
                        {
                        broadcast( "", "Player 1's turn") ;
                        }
                    else if ( board.currentPlayer == 2 )
                        {
                        broadcast( "", "Player 2's turn" ) ;
                        }
                    broadcast( "", gameMessage ) ;
                    return ;
                    }
                }
            }
        }


    // for the client
    static class ClientRequest implements Runnable
        {

        Socket connectionSocket ;

        public ClientRequest( final Socket socket )
            {
            this.connectionSocket = socket ;
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


        @SuppressWarnings( "resource" )
        private void processRequest() throws Exception
            {
            final BufferedReader inFromClient =
                                            new BufferedReader( new InputStreamReader( this.connectionSocket.getInputStream() ) ) ;
            final DataOutputStream outToClient =
                                            new DataOutputStream( this.connectionSocket.getOutputStream() ) ;

            // send welcome message to client
            final String welcomeMsg = "Welcome! Please enter your name" ;
            outToClient.writeBytes( welcomeMsg + "\r\n" ) ;

            // get name from client
            final String clientName = inFromClient.readLine() ;
            System.out.println( clientName + " has joined the game!" ) ;

            // send hello message to client
            final String helloMsg = "Hello " + clientName + "! Type {quit} to exit" ;
            outToClient.writeBytes( helloMsg + "\r\n" ) ;

            // broadcast the message
            if ( !clients.isEmpty() )
                {
                broadcast( clientName, " has joined the game!" ) ;
                }

            // add client to the map
            clients.put( clientName, outToClient ) ;

            // for choosing chess piece color
// if ( clients.size() == 1 )
// {
// final String pieceColorMsg = "Please choose the color of your chess pieces (W or
// B)." ;
// outToClient.writeBytes( pieceColorMsg + "\r\n" ) ;
// final String clientChoice = inFromClient.readLine() ;
// if ( clientChoice == "W" )
// {
// broadcast( "", "Your chess piece color is white.") ;
// }
// broadcast( "", "Your chess piece color is black.") ;
// }

            // only two players per game
            if ( clients.size() >= 2 )
                {
                // set player names as entered
                final ArrayList<String> client = new ArrayList<>( clients.keySet() ) ;
                board = new WindowChessBoard() ;
                board.setNames( client.get( 0 ), client.get( 1 ) ) ;
                
                // start the game
                board.newGame() ;
                broadcast( "", "White makes the first move" ) ;
                
                // start write thread
                final WriteThread write = new WriteThread( outToClient ) ;
                final Thread thread = new Thread( write ) ;
                thread.start() ;
                }

// while ( board.strStatusMsg != null )
// {
// broadcast( "", board.strStatusMsg );
// if ( board.strStatusMsg.startsWith( "Congrats " ) )
// {
// broadcast( "", board.strStatusMsg ) ;
// broadcast( "", "Now closing the game...") ;
// for (int i = clients.size(); i > 0; i--)
// {
// clients.remove( clientName ) ;
// }
// break;
// }
// return;
// }

            // get message from client
// System.out.println( board.strStatusMsg ) ;
// if ( board.currentPlayer == 1 )
// {
// broadcast( "", board.strStatusMsg ) ;
// }
// else
// {
// broadcast( "", "It is Client 2's move") ;
// }

            // broadcast( "", board.strStatusMsg ) ;
            String clientMessage = null ;
            
            while ( true )
                {
                while ( ( clientMessage = inFromClient.readLine() ) != null )
                    {
                    if ( clientMessage.equals( "{quit}" ) )
                        {
                        System.out.println( clientName + " has left the game!" ) ;
                        outToClient.writeBytes( clientMessage + "\r\n" ) ;
                        clients.remove( clientName ) ;
                        if ( !clients.isEmpty() )
                            {
                            broadcast( clientName, " has left the game!" ) ;
                            }
                        return ;
                        }
                    }
                }
                // when a player wins the game
// if ( CellMatrix.checkWinner( 0 ) == true )
// {
// broadcast( "", gameMessage ) ;
// broadcast( "", "Now closing the game...") ;
// for (int i = clients.size() - 1; i > 0; i--)
// {
// clients.remove( clientName ) ;
// }
// break;
// }

// System.out.println( gameMessage ) ;
// broadcast( "", gameMessage ) ;
// return ;

            }
        }

    // broadcast messages to both players
    public static void broadcast( final String other,
                                  final String message )
        throws IOException
        {
        for ( final DataOutputStream os : clients.values() )
            {
            os.writeBytes( other + message + "\r\n" ) ;
            }
        } // broadcast


    @SuppressWarnings( "resource" )
    public static void main( final String argv[] ) throws Exception
        {
        // create server socket
        final ServerSocket serverSocket = new ServerSocket( 12345 ) ;

        System.out.println( "This server is ready to receive" ) ;

        while ( true )
            {
            final Socket connectionSocket = serverSocket.accept() ;

            // create client request instance
            final ClientRequest request = new ClientRequest( connectionSocket ) ;

            // create a new thread to handle the client request
            final Thread thread = new Thread( request ) ;

            // start the thread
            thread.start() ;
            }

        }
    }
