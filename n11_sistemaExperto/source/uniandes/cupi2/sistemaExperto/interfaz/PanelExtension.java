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
     * Comando del botón opción 1.
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando del botón opción 2.
     */
    private static final String OPCION_2 = "OPCION_2";

    /**
     * Comando del botón buscar enfermedades.
     */
    private static final String BUSCAR_ENFERMEDADES = "BUSCAR_ENFERMEDADES";

    /**
     * Comando del botón buscar fármaco.
     */
    private static final String BUSCAR_FARMACO = "BUSCAR_FARMACO";
    
    /**
     * Comando del botón imprimir reporte.
     */
    private static final String IMPRIMIR_REPORTE = "IMPRIMIR_REPORTE";
    
    /**
     * Comando del botón imprimir reporte.
     */
    private static final String ENFERMEDAD_MAS_SINTOMAS = "ENFERMEDAD_MAS_SINTOMAS";

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
     * Botón opción 1.
     */
    private JButton btnOpcion1;

    /**
     * Botón opción 2.
     */
    private JButton btnOpcion2;

    /**
     * Botón para buscar las enfermedades asociadas a un síntoma.
     */
    private JButton btnBuscarEnfermedades;

    /**
     * Botón para buscar si un fármaco es apropiado para un síntoma dado.
     */
    private JButton btnBuscarSintomaFarmaco;
    
    /**
     * Botón para generar el reporte.
     */
    private JButton btnGenerarReporte;
    
    /**
     * Botón para buscar la enfermedad con más síntomas.
     */
    private JButton btnBuscarEnfermedadMasSintomas;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel.
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal!=null.
     */
    public PanelExtension( InterfazSistemaExperto pPrincipal )
    {
        principal = pPrincipal;

        setBorder( new TitledBorder( "Opciones" ) );
        setLayout( new GridLayout( 2, 4 ) );

        btnBuscarEnfermedades = new JButton( "Buscar enfermedades por síntomas" );
        btnBuscarEnfermedades.setActionCommand( BUSCAR_ENFERMEDADES );
        btnBuscarEnfermedades.addActionListener( this );
        add( btnBuscarEnfermedades );

        btnBuscarSintomaFarmaco = new JButton( "Buscar fármacos por enfermedad" );
        btnBuscarSintomaFarmaco.setActionCommand( BUSCAR_FARMACO );
        btnBuscarSintomaFarmaco.addActionListener( this );
        add( btnBuscarSintomaFarmaco );
        
        btnGenerarReporte = new JButton( "Generar reporte" );
        btnGenerarReporte.setActionCommand( IMPRIMIR_REPORTE );
        btnGenerarReporte.addActionListener( this );
        add( btnGenerarReporte );
        
        btnBuscarEnfermedadMasSintomas = new JButton( "Buscar enfermedad con más síntomas" );
        btnBuscarEnfermedadMasSintomas.setActionCommand( ENFERMEDAD_MAS_SINTOMAS );
        btnBuscarEnfermedadMasSintomas.addActionListener( this );
        add( btnBuscarEnfermedadMasSintomas );

        // Botón opción 1
        btnOpcion1 = new JButton( "Opción 1" );
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );
        add( btnOpcion1 );

        // Botón opción 2
        btnOpcion2 = new JButton( "Opción 2" );
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener( this );
        add( btnOpcion2 );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acción que generó el evento. pEvento!=null.
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
