
package Game ;

import javax.swing.JFrame ;
import javax.swing.WindowConstants ;

// Chess piece images from http://world.std.com/~wij/fixation/chess-sets.html

@SuppressWarnings( "javadoc" )
public class ChessGame extends JFrame
    {

    /**
     *
     */
    private static final long serialVersionUID = 1124395750313728651L ;

// With applications, you have to specify a main method (not with applets)
    public static void main( final String[] args )
        {

        // Make it look nice
        JFrame.setDefaultLookAndFeelDecorated( true ) ;

        // Title
        final JFrame frame = new JFrame( "Chess Game" ) ; // Title
        frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE ) ;

        final ChessGameGUI chessWindow = new ChessGameGUI() ;
        frame.setContentPane( chessWindow.createGUI( frame ) ) ;
        frame.addWindowFocusListener( chessWindow ) ;

        frame.setSize( 550, 650 ) ;
        frame.setResizable( false ) ;
        frame.setVisible( true ) ;
        frame.pack() ;
        }
    } // end class Game