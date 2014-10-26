/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
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
 * Panel de operaciones sobre el �rbol de s�ntomas.
 */
public class PanelOperaciones extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	/**
     * Comando del bot�n agregar s�ntoma.
     */
    private final static String AGREGAR_SINTOMA = "AGREGAR_SINTOMA";
    
    /**
     * Comando del bot�n eliminar s�ntoma.
     */
    private final static String ELIMINAR_SINTOMA = "ELIMINAR_SINTOMA";
    
    /**
     * Comando del bot�n asociar una enfemedad a un s�ntoma.
     */
    private final static String ASOCIAR_ENFERMEDAD = "ASOCIAR_ENFERMEDAD";
    
    /**
     * Comando del bot�n asociar un farmaco a un s�ntoma.
     */
    private final static String ASOCIAR_FARMACO = "ASOCIAR_FARMACO";
    
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Ventana principal de la aplicaci�n.
     */
    private InterfazSistemaExperto principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------
    
    /**
     * Bot�n para agregar un s�ntoma.
     */
    private JButton btnAgregarSintoma;
    
    /**
     * Bot�n para eliminar un s�ntoma.
     */
    private JButton btnEliminarSintoma;
    
    /**
     * Bot�n para asociar una enfermadad a un s�ntoma.
     */
    private JButton btnAsociarEnfermedad;
    
    /**
     * Bot�n para asociar un farmaco a un s�ntoma.
     */
    private JButton btnAsociarFarmaco;
    
    /**
     * Construye el panel de operaciones.
     * @param pPrincipal Venta principal de la aplicaci�n. pPrincipal!=null.
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
        btnAgregarSintoma.setToolTipText( "Agregar s�ntoma" );
        add(btnAgregarSintoma);
        
        btnEliminarSintoma = new JButton( new ImageIcon( "./data/imagenes/cancel.png" ) );
        btnEliminarSintoma.setPreferredSize( new Dimension( 40, 40 ) );
        btnEliminarSintoma.setActionCommand( ELIMINAR_SINTOMA );
        btnEliminarSintoma.addActionListener( this );
        btnEliminarSintoma.setToolTipText( "Eliminar s�ntoma" );
        add(btnEliminarSintoma);
        
        btnAsociarFarmaco = new JButton( new ImageIcon( "./data/imagenes/farmaco.png" ) );
        btnAsociarFarmaco.setPreferredSize( new Dimension( 40, 40 ) );
        btnAsociarFarmaco.setActionCommand( ASOCIAR_FARMACO );
        btnAsociarFarmaco.addActionListener( this );
        btnAsociarFarmaco.setToolTipText( "Asociar f�rmaco" );
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
     * @param pEvento Acci�n que gener� el evento.
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
