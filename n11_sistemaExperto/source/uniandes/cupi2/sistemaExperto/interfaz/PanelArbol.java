/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_sistemaExperto
 * Autor: Equipo Cupi2 2014
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.sistemaExperto.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import uniandes.cupi2.sistemaExperto.mundo.Sintoma;

/**
 * Panel donde se muestra gráficamente el árbol de síntomas.
 */
public class PanelArbol extends JPanel
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Ancho en píxeles de la representación de un síntoma.
     */
    private static final int ANCHO = 100;

    /**
     * Alto en píxeles de la representación de un síntoma.
     */
    private static final int ALTO = 50;

    /**
     * Tamaño de los bordes izquierdo y derecho.
     */
    private static final int BORDE_X = 5;

    /**
     * Tamaño de los bordes superior e inferior.
     */
    private static final int BORDE_Y = 20;

    /**
     * Color del borde de las cajas.
     */
    private static final Color COLOR_BORDE = Color.BLACK;

    /**
     * Color de la caja seleccionada.
     */
    private static final Color COLOR_FONDO_SELECCIONADO = new Color( 179, 195, 255 );

    /**
     * Color de las líneas que unen los síntomas.
     */
    private static final Color COLOR_LINEAS = new Color( 89, 89, 89 );

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Imagen generada con el sistema experto.
     */
    private BufferedImage imagenArbol;

    /**
     * Ventana principal de la aplicación.
     */
    private InterfazSistemaExperto principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus atributos.
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal!=null.
     */
    public PanelArbol( InterfazSistemaExperto pPrincipal )
    {
        setDoubleBuffered( true );
        principal = pPrincipal;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Repintar el panel copiando la imagen generada.
     * @param pSuperficie Superficie del panel donde debe pintarse la imagen.
     */
    public void paintComponent( Graphics pSuperficie )
    {
        super.paintComponent( pSuperficie );
        dibujarArbol( pSuperficie );
    }

    /**
     * Dibuja la imagen generada sobre la superficie.
     * @param pSuperfice Superficie del panel donde debe pintarse la imagen.
     */
    private void dibujarArbol( Graphics pSuperfice )
    {
        setBackground( Color.WHITE );
        pSuperfice.clearRect( 0, 0, getWidth( ), getHeight( ) );

        if( imagenArbol == null )
            actualizarImagen( );

        // Copiar la imagen generada
        if( imagenArbol.getWidth( ) > getWidth( ) )
            pSuperfice.drawImage( imagenArbol, 0, 0, null );
        else
        {
            pSuperfice.drawImage( imagenArbol, ( getWidth( ) - imagenArbol.getWidth( ) ) / 2, 0, null );
        }
    }

    /**
     * Actualiza la imagen generada con la información actual del mundo.
     */
    public void actualizarImagen( )
    {
        if( principal != null )
        {
            Sintoma primeraSintoma = principal.darPrimerSintoma( );
            if( primeraSintoma != null )
            {
                imagenArbol = dibujarSintomasConSintomasHijas( primeraSintoma );
                setPreferredSize( new Dimension( imagenArbol.getWidth( ), imagenArbol.getHeight( ) ) );
                setSize( new Dimension( imagenArbol.getWidth( ), imagenArbol.getHeight( ) ) );
            }
            else
            {
                imagenArbol = new BufferedImage( 1, 1, BufferedImage.TYPE_INT_RGB );
                setSize( new Dimension( getWidth( ) - 1, getHeight( ) - 1 ) );
            }
        }

    }

    /**
     * Dibuja un síntoma y a todos los que están debajo de él.
     * @param pSintoma Síntoma que debe ser dibujado.
     * @return Imagen del síntoma.
     */
    private BufferedImage dibujarSintomasConSintomasHijas( Sintoma pSintoma )
    {
        if( pSintoma.esHoja( ) )
        {
            return dibujarSintoma( pSintoma );
        }
        else
        {
            ArrayList hijos = ( ArrayList )pSintoma.darSintomasHijos( );
            ArrayList imagenes = new ArrayList( );

            int ancho = 0;
            int alto = 0;

            for( int i = 0; i < hijos.size( ); i++ )
            {
                Sintoma hijo = ( Sintoma )hijos.get( i );
                BufferedImage imagenHijo = dibujarSintomasConSintomasHijas( hijo );
                ancho += imagenHijo.getWidth( );
                alto = Math.max( alto, imagenHijo.getHeight( ) );
                imagenes.add( imagenHijo );
            }

            alto += ALTO + BORDE_Y * 2;
            BufferedImage imagenElemento = dibujarSintoma( pSintoma );
            if(ancho < PanelArbol.ANCHO*3)
				ancho = 3*(PanelArbol.ANCHO + PanelArbol.BORDE_X * 2);

            // Crear la imagen que va a contener al elemento y a sus hijos
            BufferedImage imagenCompleta = new BufferedImage( ancho, alto, BufferedImage.TYPE_INT_RGB );
            Graphics2D superficie = imagenCompleta.createGraphics( );
            superficie.setColor( Color.WHITE );
            superficie.fillRect( 0, 0, ancho, alto );

            // Dibujar el elemento
            int posElemento = ( ancho / 2 ) - ( ANCHO / 2 ) - BORDE_X;
            superficie.drawImage( imagenElemento, posElemento, 0, null );

            // Agregar a la imagen completa las imágenes de cada uno de los hijos
            int posXActual = 0;
            int posYActual = ALTO + BORDE_Y * 2;

            for( int i = 0; i < imagenes.size( ); i++ )
            {
                BufferedImage imagenHijo = ( BufferedImage )imagenes.get( i );
                superficie.drawImage( imagenHijo, posXActual, posYActual, null );
                int anchoHijo = imagenHijo.getWidth( );

                posXActual += anchoHijo;
            }

            posXActual = 0;
            // Dibujar las líneas
            for( int i = 0; i < imagenes.size( ); i++ )
            {
                BufferedImage imagenHijo = ( BufferedImage )imagenes.get( i );
                int anchoHijo = imagenHijo.getWidth( );
                superficie.setPaint( COLOR_LINEAS );
                superficie.drawLine( ancho / 2, ALTO + BORDE_Y, posXActual + anchoHijo / 2, posYActual + BORDE_Y );
                posXActual += anchoHijo;

            }
            return imagenCompleta;
        }

    }

    /**
     * Dibuja un síntoma (sin dibujar síntomas hijos si los tiene).
     * @param pSintoma Síntoma a ser dibujado.
     * @return Imagen donde está dibujado el síntoma.
     */
    private BufferedImage dibujarSintoma( Sintoma pSintoma )
    {
    	BufferedImage imagen=null;
    	Graphics2D g=null;
        // Crear la superficie para dibujar el elemento
    	if( pSintoma.darFarmaco( ) == null && pSintoma.darEnfermedad()==null)
        {
	        imagen = new BufferedImage( (ANCHO + BORDE_X * 2), ALTO + BORDE_Y * 2, BufferedImage.TYPE_INT_RGB );
	        g = imagen.createGraphics( );
	        g.setColor( Color.WHITE );
	        g.fillRect( 0, 0, ANCHO + BORDE_X * 2, ALTO + BORDE_Y * 2 );
	
	        // Dibujar el rectángulo
	        Rectangle2D rec = new Rectangle2D.Double( BORDE_X, BORDE_Y, ANCHO, ALTO );
	        g.setPaint( COLOR_FONDO_SELECCIONADO );	
	        g.fill( rec );
	        g.setPaint( COLOR_BORDE );
	        g.draw( rec );
	        
	        dibujarTextoSintoma(pSintoma,g);
	        
        }
        // Dibujar el icono si el síntoma tiene un fármaco asociado
    	else if( pSintoma.darFarmaco( ) != null && pSintoma.darEnfermedad()==null)
        {   	
        	imagen = new BufferedImage( 2*(ANCHO + BORDE_X * 2), ALTO + BORDE_Y * 2, BufferedImage.TYPE_INT_RGB );
	        g = imagen.createGraphics( );
	        g.setColor( Color.WHITE );
	        g.fillRect( 0, 0, 2*(ANCHO + BORDE_X * 2), ALTO + BORDE_Y * 2 );
	
	        // Dibujar el rectángulo
	        Rectangle2D rec = new Rectangle2D.Double( BORDE_X, BORDE_Y, ANCHO, ALTO );
	        g.setPaint( COLOR_FONDO_SELECCIONADO );
	
	        g.fill( rec );
	        g.setPaint( COLOR_BORDE );
	        g.draw( rec );
	        
	        Rectangle2D rec2 = new Rectangle2D.Double( BORDE_X+ANCHO, BORDE_Y, ANCHO, ALTO );
	        g.setPaint( new Color( 233, 179, 255 ) );
	
	        g.fill( rec2 );
	        g.setPaint( COLOR_BORDE );
	        g.draw( rec2 );
	        
	        dibujarTextoSintoma(pSintoma,g);
	        
	        String texto = pSintoma.darFarmaco().darNombre().substring( 0, Math.min( 18, pSintoma.darFarmaco().darNombre().length( ) ) );
	        int centroX =(ANCHO + BORDE_X * 2 )/ 2+ANCHO + BORDE_X;
	        int centroY =( ALTO + BORDE_Y * 2 )/ 2;

	        Font fuente = new Font( "Arial", Font.PLAIN, 11 );
	        g.setFont( fuente );
	        g.setColor( Color.BLACK );

	        FontMetrics metrics = g.getFontMetrics( );
	        int ancho = metrics.stringWidth( texto );
	        g.drawString( texto, centroX - ancho / 2, centroY +  5  );
	        
            try
            {
                imagenArbol = ImageIO.read( new File( "./data/imagenes/farmaco.png" ) );
            }
            catch( IOException e )
            {
                imagenArbol = new BufferedImage( 1, 1, BufferedImage.TYPE_INT_RGB );
            }
            g.drawImage( imagenArbol, 105, 11, null );
        }
        // Dibujar el icono si el síntoma tiene una enfermedad asociada
    	else if( pSintoma.darFarmaco( ) == null && pSintoma.darEnfermedad( ) != null )
        {
    		imagen = new BufferedImage( (ANCHO + BORDE_X * 2), 2*(ALTO + BORDE_Y * 2), BufferedImage.TYPE_INT_RGB );
	        g = imagen.createGraphics( );
	        g.setColor( Color.WHITE );
	        g.fillRect( 0, 0, ANCHO + BORDE_X * 2, 2*(ALTO + BORDE_Y * 2) );
	
	        // Dibujar el rectángulo
	        Rectangle2D rec = new Rectangle2D.Double( BORDE_X, BORDE_Y, ANCHO, ALTO );
	        g.setPaint( COLOR_FONDO_SELECCIONADO );	
	        g.fill( rec );
	        g.setPaint( COLOR_BORDE );
	        g.draw( rec );
	        
	        // Dibujar el texto
	        dibujarTextoSintoma(pSintoma,g);
	        
	        g.drawLine( (ANCHO + BORDE_X * 2 )/ 2, ALTO + BORDE_Y , (ANCHO + BORDE_X * 2 )/ 2, ALTO + BORDE_Y * 2+20 );
	        
            try
            {
                imagenArbol = ImageIO.read( new File( "./data/imagenes/enfermedad.png" ) );
            }
            catch( IOException e )
            {
                imagenArbol = new BufferedImage( 1, 1, BufferedImage.TYPE_INT_RGB );
            }
            g.drawImage( imagenArbol, 38, 90, null );
            
            String texto = pSintoma.darEnfermedad().darNombre().substring( 0, Math.min( 18, pSintoma.darEnfermedad().darNombre().length( ) ) );
	        int centroX =(ANCHO + BORDE_X * 2 )/ 2;
	        int centroY =( ALTO + BORDE_Y * 2 )/ 2+ALTO + BORDE_Y * 2;

	        Font fuente = new Font( "Arial", Font.PLAIN, 11 );
	        g.setFont( fuente );
	        g.setColor( Color.BLACK );

	        FontMetrics metrics = g.getFontMetrics( );
	        int ancho = metrics.stringWidth( texto );
	        g.drawString( texto, centroX - ancho / 2, centroY + 5 );
        }
    	else{
    		imagen = new BufferedImage( 2*(ANCHO + BORDE_X * 2), 2*(ALTO + BORDE_Y * 2), BufferedImage.TYPE_INT_RGB );
	        g = imagen.createGraphics( );
	        g.setColor( Color.WHITE );
	        g.fillRect( 0, 0, 2*(ANCHO + BORDE_X * 2), 2*(ALTO + BORDE_Y * 2) );
	
	        // Dibujar el rectángulo
	        Rectangle2D rec = new Rectangle2D.Double( BORDE_X, BORDE_Y, ANCHO, ALTO );
	        g.setPaint( COLOR_FONDO_SELECCIONADO );
	
	        g.fill( rec );
	        g.setPaint( COLOR_BORDE );
	        g.draw( rec );
	        
	        Rectangle2D rec2 = new Rectangle2D.Double( BORDE_X+ANCHO, BORDE_Y, ANCHO, ALTO );
	        g.setPaint( new Color( 233, 179, 255 ) );
	
	        g.fill( rec2 );
	        g.setPaint( COLOR_BORDE );
	        g.draw( rec2 );
	        
	        dibujarTextoSintoma(pSintoma,g);
	        
	        g.drawLine( (ANCHO + BORDE_X * 2 )/ 2, ALTO + BORDE_Y , (ANCHO + BORDE_X * 2 )/ 2, ALTO + BORDE_Y * 2+20 );
	        
	        String texto = pSintoma.darFarmaco().darNombre().substring( 0, Math.min( 18, pSintoma.darFarmaco().darNombre().length( ) ) );
	        int centroX =(ANCHO + BORDE_X * 2 )/ 2+ANCHO + BORDE_X;
	        int centroY =( ALTO + BORDE_Y * 2 )/ 2;

	        Font fuente = new Font( "Arial", Font.PLAIN, 11 );
	        g.setFont( fuente );
	        g.setColor( Color.BLACK );

	        FontMetrics metrics = g.getFontMetrics( );
	        int ancho = metrics.stringWidth( texto );
	        g.drawString( texto, centroX - ancho / 2, centroY +  5  ); 
            try
            {
                imagenArbol = ImageIO.read( new File( "./data/imagenes/farmaco.png" ) );
            }
            catch( IOException e )
            {
                imagenArbol = new BufferedImage( 1, 1, BufferedImage.TYPE_INT_RGB );
            }
            g.drawImage( imagenArbol, 105, 11, null );
            
            
            try
            {
                imagenArbol = ImageIO.read( new File( "./data/imagenes/enfermedad.png" ) );
            }
            catch( IOException e )
            {
                imagenArbol = new BufferedImage( 1, 1, BufferedImage.TYPE_INT_RGB );
            }
            g.drawImage( imagenArbol, 38, 90, null );
            
            texto = pSintoma.darEnfermedad().darNombre().substring( 0, Math.min( 18, pSintoma.darEnfermedad().darNombre().length( ) ) );
	        centroX =(ANCHO + BORDE_X * 2 )/ 2;
	        centroY =( ALTO + BORDE_Y * 2 )/ 2+ALTO + BORDE_Y * 2;

	        g.setFont( fuente );
	        g.setColor( Color.BLACK );

	        metrics = g.getFontMetrics( );
	        ancho = metrics.stringWidth( texto );
	        g.drawString( texto, centroX - ancho / 2, centroY +  5   );
    	}

        return imagen;
    }
    
    /**
     * Dibujo el texto del síntoma.
     * @param pSintoma Síntoma del cual se quiere pintar su texto.
     * @param pSuperficie Superficie donde se quiere pintar el texto del síntoma.
     */
    private void dibujarTextoSintoma(Sintoma pSintoma, Graphics2D pSuperficie)
    {
    	 String texto = pSintoma.darNombre( ).substring( 0, Math.min( 18, pSintoma.darNombre( ).length( ) ) );
	     int centroX = Math.abs( ANCHO + BORDE_X * 2 ) / 2;
	     int centroY = Math.abs( ALTO + BORDE_Y * 2 ) / 2;

	     Font fuente = new Font( "Arial", Font.PLAIN, 11 );
	     pSuperficie.setFont( fuente );
	     pSuperficie.setColor( Color.BLACK );

	     FontMetrics metrics = pSuperficie.getFontMetrics( );
	     int ancho = metrics.stringWidth( texto );
	     pSuperficie.drawString( texto, centroX - ancho / 2, centroY + 5 );
    }
}
