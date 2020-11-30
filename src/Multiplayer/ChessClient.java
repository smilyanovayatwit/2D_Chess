/**
 *
 */

package Multiplayer ;

import java.io.BufferedReader ;
import java.io.DataOutputStream ;
import java.io.InputStreamReader ;
import java.net.Socket ;
import java.util.Scanner ;

@SuppressWarnings( "javadoc" )
class ChessClient
    {

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


        @SuppressWarnings( "resource" )
        private void processRequest() throws Exception
            {
            // enter {quit} in the console
            final Scanner s = new Scanner( System.in ) ;
            final String quitMsg = s.nextLine() ;
            this.outToServer.writeBytes( quitMsg + "\r\n" ) ;
            }
        }

    @SuppressWarnings( "resource" )
    public static void main( final String argv[] ) throws Exception
        {
        final Socket connectionSocket = new Socket( "localhost", 12345 ) ;

        final DataOutputStream outToServer =
                                        new DataOutputStream( connectionSocket.getOutputStream() ) ;
        final BufferedReader inFromServer =
                                        new BufferedReader( new InputStreamReader( connectionSocket.getInputStream() ) ) ;

        // get welcome msg from server
        final String welcomeMsg = inFromServer.readLine() ;
        System.out.println( welcomeMsg ) ;

        // enter name from console
        final Scanner s = new Scanner( System.in ) ;
        final String name = s.nextLine() ;

        // send the name to the chat server
        outToServer.writeBytes( name + "\r\n" ) ;

        // get hello msg from server
        final String helloMsg = inFromServer.readLine() ;
        System.out.println( helloMsg ) ;

        // start write thread
        final WriteThread write = new WriteThread( outToServer ) ;
        final Thread thread = new Thread( write ) ;
        thread.start() ;

        // get msg from server
        String serverMessage = null ;
        while ( true )
            {
            while ( ( serverMessage = inFromServer.readLine() ) != null )
                {
                if ( serverMessage.equals( "{quit}" ) )
                    {
                    return ;
                    }
                System.out.println( serverMessage ) ;
                }
            }
        }
    } // end class ChessClient