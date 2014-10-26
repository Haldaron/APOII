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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import uniandes.cupi2.sistemaExperto.mundo.Enfermedad;
import uniandes.cupi2.sistemaExperto.mundo.Sintoma;
import uniandes.cupi2.sistemaExperto.mundo.SistemaExperto;
import uniandes.cupi2.sistemaExperto.mundo.SistemaExpertoException;

/**
 * Ventana principal de la aplicación.
 */
public class InterfazSistemaExperto extends JFrame
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

	/**
	 * Constante de la ruta del archivo donde se va a salvar la aplicación.
	 */
    private static final String ARCHIVO = "./data/sistema.dat";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo.
     */
    private SistemaExperto sistemaExperto;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con las extensiones.
     */
    private PanelExtension panelExtension;

    /**
     * Panel con la imagen del encabezado.
     */
    private PanelImagen panelImagen;

    /**
     * Panel con el árbol de síntomas.
     */
    private PanelArbol panelArbol;

    /**
     * Panel con la lista de los fármacos.
     */
    private PanelFarmacos panelFarmacos;

    /**
     * Panel con la lista de las enfermedades.
     */
    private PanelEnfermedades panelEnfermedades;
    
    /**
     * Panel con las operaciones sobre los síntomas.
     */
    private PanelOperaciones panelOperaciones;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye de la ventana principal de la aplicación.
     * @param pSistemaExperto Sistema experto del que se va a manejar la información.
     */
    public InterfazSistemaExperto( SistemaExperto pSistemaExperto )
    {
        // Crea la clase principal
        sistemaExperto = pSistemaExperto;

        // Construye la forma
        getContentPane( ).setLayout( new BorderLayout( ) );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        setTitle( "Sistema experto" );

        // Creación de los paneles aquí
        panelImagen = new PanelImagen( );
        add( panelImagen, BorderLayout.NORTH );

        panelArbol = new PanelArbol( this );
        JScrollPane contArbol = new JScrollPane( panelArbol );
        contArbol.setPreferredSize( new Dimension( 550, 400 ) );
        add( contArbol, BorderLayout.CENTER );

        panelExtension = new PanelExtension( this );
        add( panelExtension, BorderLayout.SOUTH );

        JPanel panelOeste = new JPanel( new GridLayout( 2, 1 ) );
        panelOeste.setPreferredSize( new Dimension( 200, 400 ) );
        panelFarmacos = new PanelFarmacos( );
        panelOeste.add( panelFarmacos );

        panelEnfermedades = new PanelEnfermedades( );
        panelOeste.add( panelEnfermedades );

        add( panelOeste, BorderLayout.WEST );
        
        panelOperaciones = new PanelOperaciones(this);
        panelOperaciones.setPreferredSize( new Dimension( 100, 400 ) );
        add( panelOperaciones, BorderLayout.EAST );

        actualizar( );
        pack( );
        setLocationRelativeTo( null );
    }
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    
    /**
     * Retorna el primer sintoma del sistema experto.
     */
    public Sintoma darPrimerSintoma(){
    	return sistemaExperto.darPrimerSintoma();
    }

    /**
	 * Retorna en un mensaje la enfermedad con más sintomas.
	 * Si no hay enfermedades registradas en el sistema muestra un mensaje de error.
	 */
	public void darEnfermedadMasSintomas() 
	{
		Enfermedad temp = sistemaExperto.darEnfermedadConMasSintomas();
		if(temp!=null)
			JOptionPane.showMessageDialog( null, "La enfermedad con más síntomas es: " + temp.darNombre(), "Enfermedad con más síntomas", JOptionPane.INFORMATION_MESSAGE );
		else
			JOptionPane.showMessageDialog( null, "No hay enfermedades registradas en el sistema.", "Enfermedad con más síntomas", JOptionPane.INFORMATION_MESSAGE );
	}

	/**
	 * Busca las enfermedades asociadas a un síntoma dado.
	 * Si no hay enfermedades asociadas al síntoma dado se muestra un mensaje de error.
	 */
	public void buscarEnfermedadesPorSintoma( )
	{
	    String res = ( String )JOptionPane.showInputDialog( this, "Seleccione un síntoma", "Buscar enfermedades", JOptionPane.PLAIN_MESSAGE, null, sistemaExperto.darNombresSintomas().toArray( ), null );
	    if( res != null )
	    {
	        Collection enfermedades = sistemaExperto.darEnfermedadesAsociadasSintoma( res );
	        if( enfermedades.size( ) == 0 )
	            JOptionPane.showMessageDialog( this, "No hay enfermedades asociadas al síntoma dado", "Buscar enfermedades", JOptionPane.INFORMATION_MESSAGE );
	        else
	        {
	            JList lista = new JList( enfermedades.toArray( ) );
	            JOptionPane.showMessageDialog( this, new JScrollPane( lista ), "Enfermedades que presentan " + res, JOptionPane.PLAIN_MESSAGE );
	        }
	    }
	}

	/**
	 * Busca los fármacos asociados a los síntomas de una enfermedad.
	 * Si no hay fármacos asociadas a los síntomas de una enfermedad muestra un mensaje con la información.
	 */
	public void buscarFarmacosEnfermedad( )
	{
	    Collection nombres = new ArrayList( );
	    for( Iterator iterator = sistemaExperto.darListaEnfermedades( ).iterator( ); iterator.hasNext( ); )
	    {
	        Enfermedad enfermedad = ( Enfermedad )iterator.next( );
	        nombres.add( enfermedad.darNombre( ) );
	    }
	    String enfermedad = ( String )JOptionPane.showInputDialog( this, "Seleccione una enfermedad", "Buscar fármacos", JOptionPane.PLAIN_MESSAGE, null, nombres.toArray( ), null );
	
	    if( enfermedad != null )
	    {
	        try
	        {
	            Collection farmacos = sistemaExperto.darFarmacosEnfermedad( enfermedad );
	            if( farmacos.size( ) == 0 )
	                JOptionPane.showMessageDialog( this, "No hay fármacos para tratar la enfermedad dada", "Buscar fármaco", JOptionPane.INFORMATION_MESSAGE );
	            else
	            {
	                JList lista = new JList( farmacos.toArray( ) );
	                JOptionPane.showMessageDialog( this, new JScrollPane( lista ), "Fármacos para " + enfermedad, JOptionPane.PLAIN_MESSAGE );
	            }
	        }
	        catch( SistemaExpertoException e )
	        {
	            JOptionPane.showMessageDialog( this, e.getMessage( ), "Buscar fármaco", JOptionPane.INFORMATION_MESSAGE );
	        }
	    }
	}

	/**
	 * Asocia una nueva enfermedad al síntoma con el nombre dado por parámetro.
	 * Si el síntoma dado no es una hoja muestra un mensaje de error.
	 * @param pNombreSintoma Nombre del síntoma al cual se le quiere asociar la enfermedad. pNombreSintoma != null && pNombreSintoma!="".
	 * @param pNombreEnfermedad Nombre de la enfermedad a ingresar. pNombreEnfermedad != null && pNombreEnfermedad != "".
	 * @param pTasaMortalidad Tasa de mortalidad de la enfermedad a ingresar. pTasaMortalidad entre 0 y 100.
	 */
	public void asociarEnfermedad( String pNombreSintoma, String pNombreEnfermedad, int pTasaMortalidad )
	{
	    try
	    {
	        sistemaExperto.asociarEnfermedad( pNombreSintoma, pNombreEnfermedad, pTasaMortalidad );
	    }
	    catch( SistemaExpertoException e )
	    {
	        JOptionPane.showMessageDialog( this, e.getMessage( ), "Asociar enfermedad", JOptionPane.ERROR_MESSAGE );
	    }
	    actualizar( );
	}

	/**
	 * Asocia un fármaco al síntoma con el nombre dado por parámetro.
	 * @param pNombreSintoma Nombre del síntoma al cual se le va a asociar el nuevo fármaco. pNombreSintoma != null && pNombreSintoma!="".
	 * @param pNombreFarmaco Nombre del nuevo fármaco. pNombreFarmaco != null && pNombreFarmaco != "".
	 * @param pVentaLibre Indicador de venta libre del fármaco. true si el fármaco es de venta libre o false en caso contrario.
	 */
	public void asociarFarmaco( String pNombreSintoma, String pNombreFarmaco, boolean pVentaLibre )
	{
	    try
	    {
	        sistemaExperto.asociarFarmaco( pNombreSintoma, pNombreFarmaco, pVentaLibre );
	    }
	    catch( SistemaExpertoException e )
	    {
	        JOptionPane.showMessageDialog( this, e.getMessage( ), "Asociar fármaco", JOptionPane.ERROR_MESSAGE );
	    }
	    actualizar( );
	}

	/**
	 * Agrega un síntoma al sistema experto.
	 * Si el síntoma dado ya existe muestra un mensaje de error.
	 * @param pNombrePadreSintoma Síntoma bajo el cual se quiere agregar el nuevo síntoma. pNombrePadreSintoma!=null && pNombrePadreSintoma!="".
	 * @param pDescripcion Descripcion del nuevo síntoma. pDescripcion!=null && pDescripcion!="".
	 */
	public void agregarSintoma( String pNombrePadreSintoma, String pDescripcion )
	{
	
	    if( pDescripcion.equals( "" ) )
	    {
	        JOptionPane.showMessageDialog( this, "Ingrese un síntoma válido", "Error", JOptionPane.ERROR_MESSAGE );
	    }
	    else
	    {
	        try
	        {
	            sistemaExperto.agregarSintoma( pNombrePadreSintoma, pDescripcion );
	        }
	        catch( SistemaExpertoException e )
	        {
	            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
	        }
	
	    }
	    actualizar( );
	
	}

	/**
	 * Elimina un síntoma del sistema.
	 * Si el síntoma dado no es una hoja muestra un mensaje de error.
	 * @param pNombreSintoma Nombre del síntoma que se quiere eliminar. pNombreSintoma!=null && pNombreSintoma!="".
	 */
	public void eliminarSintoma(String pNombreSintoma) 
	{
		try{
			sistemaExperto.eliminarSintoma(pNombreSintoma);
			
		}
		catch(SistemaExpertoException e){
			JOptionPane.showMessageDialog( this, e.getMessage( ), "Eliminar síntoma", JOptionPane.ERROR_MESSAGE );
		}
		actualizar( );
	}

	/**
     * Vizualiza el diálogo que sirve para agregar un nuevo síntoma.
     */
    public void mostrarDialogoAgregarSintoma( )
    {
        DialogoAgregarSintoma d = new DialogoAgregarSintoma( this, sistemaExperto.darNombresSintomas() );
        d.setLocationRelativeTo(this);
        d.setVisible( true );
        
    }
    
    /**
     * Vizualiza el diálogo que sirve para eliminar un síntoma.
     */
    public void mostrarDialogoEliminarSintoma( )
    {
        DialogoEliminarSintoma d = new DialogoEliminarSintoma( this, sistemaExperto.darNombresSintomas( ) );
        d.setLocationRelativeTo(this);
        d.setVisible( true );
    }

    /**
	 * Vizualiza el diálogo para recomendar un fármaco.
	 */
	public void mostrarDialogoAsociarFarmaco( )
	{
	    DialogoAsociarFarmaco d = new DialogoAsociarFarmaco( this, sistemaExperto.darNombresSintomas( ) );
	    d.setLocationRelativeTo(this);
	    d.setVisible( true );
	    
	}
	/**
	 * Vizualiza el dialogo para asociar una enfermedad a un síntoma.
	 */
	public void mostrarDialogoAsociarEnfermedad( )
	{
	    DialogoAsociarEnfermedad d = new DialogoAsociarEnfermedad( this, sistemaExperto.darNombresSintomas( ) );
	    d.setLocationRelativeTo(this);
	    d.setVisible( true );
	    
	}

	/**
     * Actualiza la ventana principal.
     */
    public void actualizar( )
    {
        panelArbol.actualizarImagen( );
        panelFarmacos.actualizar( sistemaExperto.darListaFarmacos( ) );
        panelEnfermedades.actualizar( sistemaExperto.darListaEnfermedades( ) );
    }

    /**
     * Guarda el estado actual en el que se encuentra el sistema experto.
     */
    private void guardar( )
    {
        try
        {
            sistemaExperto.guardar( );
        }
        catch( SistemaExpertoException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }
    
    /**
     * Generar el reporte del sistema experto.
     * Si hay problema al escribir el archivo se muestra un mensaje de error.
     */
	public void generarReporte() 
	{
		try{
			JFileChooser chooser = new JFileChooser( "./data" );
            int returnVal = chooser.showSaveDialog( this );
            if( returnVal == JFileChooser.APPROVE_OPTION )
            {
                String pathArchivo = chooser.getSelectedFile( ).getParent( );
                String nombreArchivo = chooser.getSelectedFile( ).getName( );
                sistemaExperto.generarReporte(pathArchivo,nombreArchivo);
                JOptionPane.showMessageDialog( null, "Generación correcta.", "Generación de reporte", JOptionPane.INFORMATION_MESSAGE );
            }
			
			
		}
		catch(Exception e){
			JOptionPane.showMessageDialog( null, "Error al generar el reporte.", "Generación de reporte", JOptionPane.ERROR_MESSAGE );
		}
		
	}
	
	/**
     * Salva la información del sistema, justo antes de cerrar la aplicación.
     */
    public void dispose( )
    {
        guardar( );
        super.dispose( );
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1.
     */
    public void reqFuncOpcion1( )
    {
        String resultado = sistemaExperto.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2.
     */
    public void reqFuncOpcion2( )
    {
        String resultado = sistemaExperto.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }


    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Ejecuta la aplicación, creando una nueva interfaz.
     * @param args Argumentos para la ejecución de la aplicación. En este caso no son necesarios.
     */
    public static void main( String[] args )
    {
        SistemaExperto sistema = null;
        try
        {
            sistema = new SistemaExperto( ARCHIVO );
        }
        catch( SistemaExpertoException e )
        {
            JOptionPane.showMessageDialog( null, e.getMessage( ), "Carga del sistema", JOptionPane.ERROR_MESSAGE );
        }
        InterfazSistemaExperto interfaz = new InterfazSistemaExperto( sistema );
        interfaz.setVisible( true );
    }


}