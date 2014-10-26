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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel de manejo de extensiones.
 */
public class PanelExtension extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando del bot�n opci�n 1.
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando del bot�n opci�n 2.
     */
    private static final String OPCION_2 = "OPCION_2";

    /**
     * Comando del bot�n buscar enfermedades.
     */
    private static final String BUSCAR_ENFERMEDADES = "BUSCAR_ENFERMEDADES";

    /**
     * Comando del bot�n buscar f�rmaco.
     */
    private static final String BUSCAR_FARMACO = "BUSCAR_FARMACO";
    
    /**
     * Comando del bot�n imprimir reporte.
     */
    private static final String IMPRIMIR_REPORTE = "IMPRIMIR_REPORTE";
    
    /**
     * Comando del bot�n imprimir reporte.
     */
    private static final String ENFERMEDAD_MAS_SINTOMAS = "ENFERMEDAD_MAS_SINTOMAS";

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
     * Bot�n opci�n 1.
     */
    private JButton btnOpcion1;

    /**
     * Bot�n opci�n 2.
     */
    private JButton btnOpcion2;

    /**
     * Bot�n para buscar las enfermedades asociadas a un s�ntoma.
     */
    private JButton btnBuscarEnfermedades;

    /**
     * Bot�n para buscar si un f�rmaco es apropiado para un s�ntoma dado.
     */
    private JButton btnBuscarSintomaFarmaco;
    
    /**
     * Bot�n para generar el reporte.
     */
    private JButton btnGenerarReporte;
    
    /**
     * Bot�n para buscar la enfermedad con m�s s�ntomas.
     */
    private JButton btnBuscarEnfermedadMasSintomas;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel.
     * @param pPrincipal Ventana principal de la aplicaci�n. pPrincipal!=null.
     */
    public PanelExtension( InterfazSistemaExperto pPrincipal )
    {
        principal = pPrincipal;

        setBorder( new TitledBorder( "Opciones" ) );
        setLayout( new GridLayout( 2, 4 ) );

        btnBuscarEnfermedades = new JButton( "Buscar enfermedades por s�ntomas" );
        btnBuscarEnfermedades.setActionCommand( BUSCAR_ENFERMEDADES );
        btnBuscarEnfermedades.addActionListener( this );
        add( btnBuscarEnfermedades );

        btnBuscarSintomaFarmaco = new JButton( "Buscar f�rmacos por enfermedad" );
        btnBuscarSintomaFarmaco.setActionCommand( BUSCAR_FARMACO );
        btnBuscarSintomaFarmaco.addActionListener( this );
        add( btnBuscarSintomaFarmaco );
        
        btnGenerarReporte = new JButton( "Generar reporte" );
        btnGenerarReporte.setActionCommand( IMPRIMIR_REPORTE );
        btnGenerarReporte.addActionListener( this );
        add( btnGenerarReporte );
        
        btnBuscarEnfermedadMasSintomas = new JButton( "Buscar enfermedad con m�s s�ntomas" );
        btnBuscarEnfermedadMasSintomas.setActionCommand( ENFERMEDAD_MAS_SINTOMAS );
        btnBuscarEnfermedadMasSintomas.addActionListener( this );
        add( btnBuscarEnfermedadMasSintomas );

        // Bot�n opci�n 1
        btnOpcion1 = new JButton( "Opci�n 1" );
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );
        add( btnOpcion1 );

        // Bot�n opci�n 2
        btnOpcion2 = new JButton( "Opci�n 2" );
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener( this );
        add( btnOpcion2 );

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acci�n que gener� el evento. pEvento!=null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        if( OPCION_1.equals( pEvento.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion1( );
        }
        else if( OPCION_2.equals( pEvento.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion2( );
        }
        else if( BUSCAR_ENFERMEDADES.equals( pEvento.getActionCommand( ) ) )
        {
            principal.buscarEnfermedadesPorSintoma( );
        }
        else if( BUSCAR_FARMACO.equals( pEvento.getActionCommand( ) ) )
        {
            principal.buscarFarmacosEnfermedad( );
        }
        else if( IMPRIMIR_REPORTE.equals( pEvento.getActionCommand( ) ) )
        {
            principal.generarReporte();
        }
        else if( ENFERMEDAD_MAS_SINTOMAS.equals( pEvento.getActionCommand( ) ) )
        {
            principal.darEnfermedadMasSintomas();
        }
    }

}
