
package Game ;

import java.awt.Image ;
import java.awt.Toolkit ;
import java.io.BufferedInputStream ;
import java.io.IOException ;

import javax.swing.ImageIcon ;

@SuppressWarnings( "javadoc" )
public class CreateAppletImage
    {

    private String strErrorMsg = "" ;

    public Image getImage( final Object parentClass,
                           final String path,
                           final int fileSize )
        {
        final byte buff[] = createImageArray( parentClass, path, fileSize ) ;
        return Toolkit.getDefaultToolkit().createImage( buff ) ;
        }


    public ImageIcon getImageIcon( final Object parentClass,
                                   final String path,
                                   final String description,
                                   final int fileSize )
        {
        final byte buff[] = createImageArray( parentClass, path, fileSize ) ;
        return new ImageIcon( Toolkit.getDefaultToolkit().createImage( buff ),
                              description ) ;
        }


    public CreateAppletImage()
        {}


    public String getErrorMsg()
        {
        return this.strErrorMsg ;
        }


    @SuppressWarnings( { "resource", "unused" } )
    private byte[] createImageArray( final Object parentClass,
                                     final String path,
                                     final int fileSize )
        {

        int count = 0 ;

        final BufferedInputStream imgStream =
                                        new BufferedInputStream( parentClass.getClass()
                                                                            .getResourceAsStream( path ) ) ;

        final byte buff[] = new byte[ fileSize ] ; // Create the array of bytes

        try
            {
            count = imgStream.read( buff ) ;
            }
        catch ( final IOException e )
            {
            this.strErrorMsg = "Error reading from file: " + path ;
            }

        try
            {
            imgStream.close() ; // Closes the stream
            }
        catch ( final IOException e )
            {
            this.strErrorMsg = "Error closing file: " + path ;
            }

        if ( count <= 0 )
            {
            this.strErrorMsg = "Error, empty file: " + path ;
            return null ;
            }
        return buff ;
        }
    } // end class CreateAppletImage