
package Multiplayer ;

/**
 * @author Tiffany Phan and Yuliya Smilyanova
 */

import java.io.BufferedReader ;
import java.io.DataOutputStream ;
import java.io.IOException ;
import java.io.InputStreamReader ;
import java.net.ServerSocket ;
import java.net.Socket ;
import java.util.ArrayList ;
import java.util.HashMap ;
import java.util.Map ;

import Game.WindowChessBoard ;

@SuppressWarnings( "javadoc" )
class ChessServer
    {

    private static Map<String, DataOutputStream> clientsOutputs = new HashMap<>() ;
    static ArrayList<String> client ;
    static WindowChessBoard board ;

    // for writing to the client
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


        // different messages from the board class
        // mostly for sending these to the client
        private void processBoard() throws IOException
            {
            String gameMessage = null ;

            while ( true )
                {
                while ( ( gameMessage = board.getPlayerMsg() ) != null )
                    {
                    if ( gameMessage.startsWith( "Congrats " ) )
                        {
                        broadcast( "", "The game is over!" ) ;
                        break ;
                        }
                    broadcast( "", gameMessage ) ;
                    return ;
                    }
                }
            }
        }


    // for handling the clients and game
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


        @SuppressWarnings( { "resource" } )
        private void processRequest() throws Exception
            {
            final BufferedReader inFromClient =
                                            new BufferedReader( new InputStreamReader( this.connectionSocket.getInputStream() ) ) ;
            final DataOutputStream outToClient =
                                            new DataOutputStream( this.connectionSocket.getOutputStream() ) ;

            // send welcome message to the client
            final String welcomeMessage = "Welcome! Please enter your name" ;
            outToClient.writeBytes( welcomeMessage + "\r\n" ) ;

            // get name from the client
            final String clientName = inFromClient.readLine() ;
            System.out.println( clientName + " has joined the game!" ) ;

            // send hello message to the client
            final String helloMessage = "Hello " + clientName +
                                        "! Type {quit} to exit" ;
            outToClient.writeBytes( helloMessage + "\r\n" ) ;

            // if there's another player broadcast the message to them
            if ( !clientsOutputs.isEmpty() )
                {
                broadcast( clientName, " has joined the game!" ) ;
                }

            // add client to the HashMap
            clientsOutputs.put( clientName, outToClient ) ;

            // allow only 2 players per game
            if ( clientsOutputs.size() == 2 )
                {
                // adds just the names of the clients to a new ArrayList
                client = new ArrayList<>( clientsOutputs.keySet() ) ;

                // create a new chess board
                board = new WindowChessBoard() ;

                // set names of each player
                board.setNames( client.get( 0 ), client.get( 1 ) ) ;

                // start the game
                board.newGame() ;
                broadcast( "", "White makes the first move" ) ;

                // start write thread
                final WriteThread write = new WriteThread( outToClient ) ;
                final Thread thread = new Thread( write ) ;
                thread.start() ;
                }

            // receive different types of messages from the client
            String clientMessage = null ;
            while ( true )
                {
                while ( ( clientMessage = inFromClient.readLine() ) != null )
                    {
                    // {quit} message to close the game
                    if ( clientMessage.equals( "{quit}" ) )
                        {
                        System.out.println( clientName + " has left the game!" ) ;
                        outToClient.writeBytes( clientMessage + "\r\n" ) ;
                        clientsOutputs.remove( clientName ) ;
                        if ( !clientsOutputs.isEmpty() )
                            {
                            broadcast( clientName, " has left the game!" ) ;
                            }
                        break ;
                        }

                    // START and END messages to update the chess board for both
                    // players
                    else if ( clientMessage.startsWith( "START" ) )
                        {
                        broadcast( "", clientMessage ) ;
                        }
                    else if ( clientMessage.startsWith( "END" ) )
                        {
                        String currentPlayer ;
                        if ( board.currentPlayer == 1 )
                            {
                            board.currentPlayer = 2 ;
                            currentPlayer = "2" ;
                            }
                        else
                            {
                            board.currentPlayer = 1 ;
                            currentPlayer = "1" ;
                            }
                        broadcast( "", clientMessage ) ;

                        // update and send message for whose turn it is
                        broadcast( "", currentPlayer ) ;
                        broadcast( "", board.getPlayerMsg() ) ;
                        }
                    }
                break ;
                }
            }
        }

    // broadcast messages to both clients
    public static void broadcast( final String other,
                                  final String message )
        throws IOException
        {
        for ( final DataOutputStream os : clientsOutputs.values() )
            {
            os.writeBytes( other + message + "\r\n" ) ;
            }
        }


    // open server for accepting clients
    @SuppressWarnings( "resource" )
    public static void main( final String argv[] ) throws Exception
        {
        // create server socket
        final ServerSocket serverSocket = new ServerSocket( 12345 ) ;

        System.out.println( "This server is ready to receive" ) ;

        while ( true )
            {
            final Socket connectionSocket = serverSocket.accept() ;

            // start clients thread
            final ClientRequest request = new ClientRequest( connectionSocket ) ;
            final Thread thread = new Thread( request ) ;
            thread.start() ;
            }
        }
    }