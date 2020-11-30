
package Game ;

import javax.swing.JFrame ;
import javax.swing.WindowConstants ;

@SuppressWarnings( "javadoc" )
public class ChessGame extends JFrame
    {

    private static final long serialVersionUID = 1124395750313728651L ;

    public void window()
        {

        // make it look nice
        JFrame.setDefaultLookAndFeelDecorated( true ) ;

        // game window
        final JFrame frame = new JFrame( "2D Chess" ) ; // window title
        frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE ) ;

        final ChessGameGUI chessWindow = new ChessGameGUI() ;
        frame.setContentPane( chessWindow.createGUI( frame ) ) ;
        frame.addWindowFocusListener( chessWindow ) ;

        frame.setSize( 550, 650 ) ;
        frame.setResizable( false ) ;
        frame.setVisible( true ) ;
        frame.pack() ;
        }
    } // end class ChessGame