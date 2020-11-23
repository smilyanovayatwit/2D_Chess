
package Game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;

//Chess piece images from http://world.std.com/~wij/fixation/chess-sets.html

/**
 * 
 * @author Veerle
 *
 */
public class ChessGame extends JFrame {

//With applications, you have to specify a main method (not with applets)
public static void main(String[] args)  {

    //Make it look nice
    JFrame.setDefaultLookAndFeelDecorated(true); 
    
    //Title
    JFrame frame = new JFrame("Chess Game"); //Title
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    ChessGameGUI chessWindow = new ChessGameGUI();
    frame.setContentPane(chessWindow.createGUI(frame));
    frame.addWindowFocusListener(chessWindow);
    
    frame.setSize(550,650);
    frame.setResizable(false);
    frame.setVisible(true);  
    frame.pack();
  }   
} // end class Game