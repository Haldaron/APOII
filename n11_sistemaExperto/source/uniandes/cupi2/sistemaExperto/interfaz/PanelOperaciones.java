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

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel de operaciones sobre el árbol de síntomas.
 */
public class PanelOperaciones extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	/**
     * Comando del botón agregar síntoma.
     */
    private final static String AGREGAR_SINTOMA = "AGREGAR_SINTOMA";
    
    /**
     * Comando del botón eliminar síntoma.
     */
    private final static String ELIMINAR_SINTOMA = "ELIMINAR_SINTOMA";
    
    /**
     * Comando del botón asociar una enfemedad a un síntoma.
     */
    private final static String ASOCIAR_ENFERMEDAD = "ASOCIAR_ENFERMEDAD";
    
    /**
     * Comando del botón asociar un farmaco a un síntoma.
     */
    private final static String ASOCIAR_FARMACO = "ASOCIAR_FARMACO";
    
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Ventana principal de la aplicación.
     */
    private InterfazSistemaExperto principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------
    
    /**
     * Botón para agregar un síntoma.
     */
    private JButton btnAgregarSintoma;
    
    /**
     * Botón para eliminar un síntoma.
     */
    private JButton btnEliminarSintoma;
    
    /**
     * Botón para asociar una enfermadad a un síntoma.
     */
    private JButton btnAsociarEnfermedad;
    
    /**
     * Botón para asociar un farmaco a un síntoma.
     */
    private JButton btnAsociarFarmaco;
    
    /**
     * Construye el panel de operaciones.
     * @param pPrincipal Venta principal de la aplicación. pPrincipal!=null.
     */
    public PanelOperaciones(InterfazSistemaExperto pPrincipal)
    {
    	principal = pPrincipal;
    	setBorder( new TitledBorder( "Operaciones" ) );
        setLayout( new GridLayout( 8, 2 ) );
        
        btnAgregarSintoma = new JButton( new ImageIcon( "./data/imagenes/add.png" ) );
        btnAgregarSintoma.setPreferredSize( new Dimension( 40, 40 ) );
        btnAgregarSintoma.setActionCommand( AGREGAR_SINTOMA );
        btnAgregarSintoma.addActionListener( this );
        btnAgregarSintoma.setToolTipText( "Agregar síntoma" );
        add(btnAgregarSintoma);
        
        btnEliminarSintoma = new JButton( new ImageIcon( "./data/imagenes/cancel.png" ) );
        btnEliminarSintoma.setPreferredSize( new Dimension( 40, 40 ) );
        btnEliminarSintoma.setActionCommand( ELIMINAR_SINTOMA );
        btnEliminarSintoma.addActionListener( this );
        btnEliminarSintoma.setToolTipText( "Eliminar síntoma" );
        add(btnEliminarSintoma);
        
        btnAsociarFarmaco = new JButton( new ImageIcon( "./data/imagenes/farmaco.png" ) );
        btnAsociarFarmaco.setPreferredSize( new Dimension( 40, 40 ) );
        btnAsociarFarmaco.setActionCommand( ASOCIAR_FARMACO );
        btnAsociarFarmaco.addActionListener( this );
        btnAsociarFarmaco.setToolTipText( "Asociar fármaco" );
        add(btnAsociarFarmaco);
        
        btnAsociarEnfermedad = new JButton( new ImageIcon( "./data/imagenes/enfermedad.png" ) );
        btnAsociarEnfermedad.setPreferredSize( new Dimension( 40, 40 ) );
        btnAsociarEnfermedad.setActionCommand( ASOCIAR_ENFERMEDAD );
        btnAsociarEnfermedad.addActionListener( this );
        btnAsociarEnfermedad.setToolTipText( "Asociar enfermedad" );
        add(btnAsociarEnfermedad);
    }
    
    
    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acción que generó el evento.
     */
	public void actionPerformed(ActionEvent pEvento) {
		if( AGREGAR_SINTOMA.equals( pEvento.getActionCommand( ) ) )
        {
            principal.mostrarDialogoAgregarSintoma( );
        }
		else if( ASOCIAR_ENFERMEDAD.equals( pEvento.getActionCommand( ) ) )
        {
			principal.mostrarDialogoAsociarEnfermedad( );
        }
		
		else if( ASOCIAR_FARMACO.equals( pEvento.getActionCommand( ) ) )
        {
			principal.mostrarDialogoAsociarFarmaco( );
        }
		else if( ELIMINAR_SINTOMA.equals( pEvento.getActionCommand( ) ) )
        {
			principal.mostrarDialogoEliminarSintoma();
        }
		
		
	}

}
