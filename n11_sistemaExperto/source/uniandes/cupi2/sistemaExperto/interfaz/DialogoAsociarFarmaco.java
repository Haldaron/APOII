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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Diálogo para asociar un fármaco a un síntoma.
 */
public class DialogoAsociarFarmaco extends JDialog implements ActionListener
{
    // -----------------------------------------------
    // Constantes
    // -----------------------------------------------

    /**
     * Constante del botón de asignar una enfermedad.
     */
    private final static String ASOCIAR_FARMACO = "ASOCIAR_FARMACO";

    /**
     * Constante del botón cancelar.
     */
    private final static String CANCELAR = "CANCELAR";

    /**
     * Constante para ingresar un fármaco.
     */
    public final static String FARMACO = "¿Cuál fármaco recomienda?";

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta síntoma.
     */
    private JLabel labelSintoma;

    /**
     * Combo con el síntoma.
     */
    private JComboBox comboSintoma;

    /**
     * Etiqueta nombre fármaco.
     */
    private JLabel labelNombreFarmaco;

    /**
     * Check box para el tipo de venta del fármaco.
     */
    private JCheckBox chbVenta;

    /**
     * Texto con el nombre del fármaco.
     */
    private JTextField textNombreFarmaco;

    /**
     * Panel con la información del fármaco.
     */
    private JPanel panelInfo;

    /**
     * Botón para asignar el fármaco.
     */
    private JButton btnAsignarFarmaco;

    /**
     * Botón para cancelar la operación.
     */
    private JButton btnCancelar;

    /**
     * Ventana principal de la aplicación.
     */
    private InterfazSistemaExperto principal;

    // -----------------------------------------------
    // Métodos
    // -----------------------------------------------

    /**
     * Construye el diálogo e inicializa los elementos de la interfaz.
     * @param pPrincipal Ventana principal de la aplicación.  pPrincipal!=null.
     * @param pListaSintomas Síntomas presentes en el sistema.  pListaSintomas!=null.
     */
    public DialogoAsociarFarmaco( InterfazSistemaExperto pPrincipal, Collection pListaSintomas )
    {
        super( pPrincipal, true );

        this.principal = pPrincipal;
        setTitle( "Asignar fármaco" );
        setResizable( false );
        setLayout( new java.awt.BorderLayout( ) );

        panelInfo = new JPanel( );

        panelInfo.setBorder( new TitledBorder( "Información" ) );
        GridLayout layout = new GridLayout( 3, 2, 5, 10 );
        panelInfo.setLayout( layout );

        labelSintoma = new JLabel( "Síntoma" );
        panelInfo.add( labelSintoma );

        comboSintoma = new JComboBox( );
        inicializarCombo( comboSintoma, pListaSintomas );
        panelInfo.add( comboSintoma );

        labelNombreFarmaco = new JLabel( FARMACO );
        panelInfo.add( labelNombreFarmaco );
        textNombreFarmaco = new JTextField( 15 );
        panelInfo.add( textNombreFarmaco );

        chbVenta = new JCheckBox( "Venta libre?" );
        panelInfo.add( chbVenta );

        BorderLayout border = new BorderLayout( );
        border.setHgap( 5 );
        border.setVgap( 5 );
        add( panelInfo, BorderLayout.NORTH );

        JPanel botones = new JPanel( );

        btnAsignarFarmaco = new JButton( "Asignar fármaco" );
        btnAsignarFarmaco.setActionCommand( ASOCIAR_FARMACO );
        btnAsignarFarmaco.addActionListener( this );
        botones.add( btnAsignarFarmaco );

        btnCancelar = new JButton( "Cancelar" );
        btnCancelar.setActionCommand( CANCELAR );
        btnCancelar.addActionListener( this );
        botones.add( btnCancelar );

        add( botones, BorderLayout.SOUTH );

        pack( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicializa el combo especificado con los síntomas dadas.
     * @param pCombo Combo a ser inicializado.
     * @param pListaSintomas Lista de síntomas existentes.
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
     * Método que recoge las acciones sobre los objetos que está escuchando.
     * @param pEvento Evento que se realizó.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );

        if( comando.equals( ASOCIAR_FARMACO ) )
        {
            String nombre = textNombreFarmaco.getText( );
            if( nombre == null || nombre.equals( "" ) )
            {
                JOptionPane.showMessageDialog( this, "Datos inválidos", "Asignar fármaco", JOptionPane.ERROR_MESSAGE );
            }
            else
            {
                boolean seleccionado = chbVenta.isSelected( );
                principal.asociarFarmaco( ( String )comboSintoma.getSelectedItem( ), nombre, seleccionado );
                setVisible( false );
                dispose( );
            }
        }
        else
        {
            setVisible( false );
            dispose( );
        }
    }

}
