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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.sistemaExperto.mundo.Sintoma;

/**
 * Panel donde se ingresan los datos de los nuevos s�ntomas.
 */
public class PanelDatosSintoma extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
	/**
	 * Constante de bot�n agregar.
	 */
    private static final String AGREGAR = "AGREGAR";

    /**
     * Constante del bot�n cancelar.
     */
    private static final String CANCELAR = "CANCELAR";

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta descripci�n.
     */
    private JLabel etiquetaDescripcion;

    /**
     * Campo de texto para la descripci�n.
     */
    private JTextField txtDescripcion;

    /**
     * Etiqueta para el s�ntoma padre.
     */
    private JLabel etiquetaPadre;

    /**
     * Combo con los s�ntomas actuales del sistema.
     */
    private JComboBox comboPadre;

    /**
     * Bot�n para agregar un s�ntoma.
     */
    private JButton botonAgregar;

    /**
     * Bot�n para cancelar el di�logo
     */
    private JButton botonCancelar;

    /**
     * Di�logo que contiene este panel.
     */
    private DialogoAgregarSintoma dialogo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes.
     * @param pPrincipal Ventana principal de la aplicaci�n. pPrincipal!=null.
     * @param pSintomas Lista con los s�ntomas que actualmente existen en el sistema. pSintomas!=null.
     */
    public PanelDatosSintoma( DialogoAgregarSintoma pPrincipal, Collection pSintomas )
    {
        dialogo = pPrincipal;

        setBorder( new TitledBorder( "Datos del s�ntoma" ) );
        setLayout( new GridBagLayout( ) );

        JPanel panelInformacion = new JPanel( );
        panelInformacion.setLayout( new GridBagLayout( ) );

        GridBagConstraints gbcEtiqueta = new GridBagConstraints( 0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );

        gbcEtiqueta.gridy = 0;
        etiquetaPadre = new JLabel( "S�ntoma padre: " );
        panelInformacion.add( etiquetaPadre, gbcEtiqueta );

        gbcEtiqueta.gridy = 1;
        etiquetaDescripcion = new JLabel( "Descripci�n:" );
        panelInformacion.add( etiquetaDescripcion, gbcEtiqueta );

        GridBagConstraints gbcCampos = new GridBagConstraints( 1, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets( 5, 5, 5, 5 ), 0, 0 );

        gbcCampos.gridy = 0;
        comboPadre = new JComboBox( );
        inicializarCombo( comboPadre, pSintomas );
        panelInformacion.add( comboPadre, gbcCampos );

        gbcCampos.gridy = 1;
        txtDescripcion = new JTextField( "", 10 );
        panelInformacion.add( txtDescripcion, gbcCampos );

        gbcCampos.gridx = 0;
        add( panelInformacion, gbcCampos );

        JPanel panelBotones = new JPanel( );
        panelBotones.setLayout( new GridBagLayout( ) );

        GridBagConstraints gbcBotones = new GridBagConstraints( 0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );

        botonAgregar = new JButton( "Agregar" );
        botonAgregar.setActionCommand( AGREGAR );
        botonAgregar.addActionListener( this );
        panelBotones.add( botonAgregar, gbcBotones );

        gbcBotones.gridx = 1;
        botonCancelar = new JButton( "Cancelar" );
        botonCancelar.setActionCommand( CANCELAR );
        botonCancelar.addActionListener( this );
        panelBotones.add( botonCancelar, gbcBotones );

        gbcBotones.gridx = 0;
        gbcBotones.gridy = 2;
        add( panelBotones, gbcBotones );

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicializa el combo especificado con los s�ntomas.
     * @param pCombo Combo a ser inicializado.
     * @param pListaSintomas Lista de s�ntomas existentes.
     */
    private void inicializarCombo( JComboBox pCombo, Collection pListaSintomas )
    {
        Iterator it = pListaSintomas.iterator( );

        while( it.hasNext( ) )
        {
            pCombo.addItem( it.next( ) );
        }

        if( !pListaSintomas.isEmpty( ) )
            pCombo.setSelectedIndex( 0 );
    }

    /**
     * Ejecuta una acci�n seg�n el bot�n sobre el que se haya hecho click.
     * @param pEvento Evento del click sobre uno de los botones. pEvento!=null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );

        if( CANCELAR.equals( comando ) )
        {
            dialogo.dispose( );
        }
        else if( AGREGAR.equals( comando ) )
        {
            String padre = ( String )comboPadre.getSelectedItem( );
            if( padre == null )
            {
                dialogo.agregarSintoma( padre, txtDescripcion.getText( ) );
            }
            else
            {
                dialogo.agregarSintoma( padre, txtDescripcion.getText( ) );
            }
        }
    }
}
