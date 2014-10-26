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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Diálogo para asociar una enfermedad a un síntoma.
 */
public class DialogoAsociarEnfermedad extends JDialog implements ActionListener
{

    // -----------------------------------------------
    // Constantes
    // -----------------------------------------------

    /**
     * Constante de botón asociar enfermedad.
     */
    private final static String ASOCIAR_ENFERMEDAD = "ASOCIAR_ENFERMEDAD";

    /**
     * Constante  del botón cancelar.
     */
    private final static String CANCELAR = "CANCELAR";

    /**
     * Constante para ingresar una enfermedad.
     */
    public final static String ENFERMEDAD = "¿Cuál enfermedad padece el paciente?";

    // -----------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------

    /**
     * Etiqueta descripción síntoma.
     */
    private JLabel labelSintoma;

    /**
     * Combo para seleccionar el síntoma.
     */
    private JComboBox comboSintoma;

    /**
     * Etiqueta descripción enfermedad.
     */
    private JLabel labelNombreEnfermedad;

    /**
     * Label para la tasa de mortalidad de la enfermedad.
     */
    private JLabel labelTasaEnfermedad;

    /**
     * Campo de texto con el nombre de la enfermedad.
     */
    private JTextField textNombreEnfermedad;

    /**
     * Campo de texto con la tasa de mortalidad de la enfermedad.
     */
    private JTextField textTasa;

    /**
     * Panel con la información de la enfermedad.
     */
    private JPanel panelInfo;

    /**
     * Botón para ingresar la enfermedad.
     */
    private JButton btnAsignarEnfermedad;

    /**
     * Botón para cancelar la operación.
     */
    private JButton btnCancelar;

    /**
     * Ventana principal de la aplicación.
     */
    private InterfazSistemaExperto principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el diálogo e inicializa los elementos de la interfaz.
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal!=null.
     * @param pListaSintomas Lista de síntomas registrados actualmente en el sistema. pListaSintomas!=null.
     */
    public DialogoAsociarEnfermedad( InterfazSistemaExperto pPrincipal, Collection pListaSintomas )
    {
        super( pPrincipal, true );

        this.principal = pPrincipal;
        setTitle( "Asociar enfermedad" );
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

        labelNombreEnfermedad = new JLabel( ENFERMEDAD );
        panelInfo.add( labelNombreEnfermedad );
        textNombreEnfermedad = new JTextField( );
        panelInfo.add( textNombreEnfermedad );

        labelTasaEnfermedad = new JLabel( "Tasa de mortalidad" );
        panelInfo.add( labelTasaEnfermedad );
        textTasa = new JTextField( );
        panelInfo.add( textTasa );

        BorderLayout border = new BorderLayout( );
        border.setHgap( 5 );
        border.setVgap( 5 );
        add( panelInfo, BorderLayout.NORTH );

        JPanel botones = new JPanel( );

        btnAsignarEnfermedad = new JButton( "Asociar enfermedad" );
        btnAsignarEnfermedad.setActionCommand( ASOCIAR_ENFERMEDAD );
        btnAsignarEnfermedad.addActionListener( this );
        botones.add( btnAsignarEnfermedad );

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

        if( comando.equals( ASOCIAR_ENFERMEDAD ) )
        {
            try
            {
                String nombre = textNombreEnfermedad.getText( );
                if( nombre == null || nombre.equals( "" ) )
                {
                    JOptionPane.showMessageDialog( this, "Ingrese el nombre de la enfermedad.", "Asignar enfermedad", JOptionPane.ERROR_MESSAGE );
                    return;
                }

                String sTasa = textTasa.getText( );
                int tasa = Integer.valueOf( sTasa ).intValue( );
                if( tasa < 0 || tasa > 100 )
                {
                    JOptionPane.showMessageDialog( this, "Ingrese una tasa de mortalidad válida.", "Asignar enfermedad", JOptionPane.ERROR_MESSAGE );
                    return;
                }

                principal.asociarEnfermedad( ( String )comboSintoma.getSelectedItem( ), nombre, tasa );

                setVisible( false );
                dispose( );
            }
            catch( NumberFormatException ne )
            {
                JOptionPane.showMessageDialog( this, "Formato de número no válido.", "Asignar enfermedad", JOptionPane.ERROR_MESSAGE );
                return;
            }
        }
        else
        {
            setVisible( false );
            dispose( );
        }
    }

}
