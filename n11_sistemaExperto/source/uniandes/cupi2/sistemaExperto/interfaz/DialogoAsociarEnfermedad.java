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
 * Di�logo para asociar una enfermedad a un s�ntoma.
 */
public class DialogoAsociarEnfermedad extends JDialog implements ActionListener
{

    // -----------------------------------------------
    // Constantes
    // -----------------------------------------------

    /**
     * Constante de bot�n asociar enfermedad.
     */
    private final static String ASOCIAR_ENFERMEDAD = "ASOCIAR_ENFERMEDAD";

    /**
     * Constante  del bot�n cancelar.
     */
    private final static String CANCELAR = "CANCELAR";

    /**
     * Constante para ingresar una enfermedad.
     */
    public final static String ENFERMEDAD = "�Cu�l enfermedad padece el paciente?";

    // -----------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------

    /**
     * Etiqueta descripci�n s�ntoma.
     */
    private JLabel labelSintoma;

    /**
     * Combo para seleccionar el s�ntoma.
     */
    private JComboBox comboSintoma;

    /**
     * Etiqueta descripci�n enfermedad.
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
     * Panel con la informaci�n de la enfermedad.
     */
    private JPanel panelInfo;

    /**
     * Bot�n para ingresar la enfermedad.
     */
    private JButton btnAsignarEnfermedad;

    /**
     * Bot�n para cancelar la operaci�n.
     */
    private JButton btnCancelar;

    /**
     * Ventana principal de la aplicaci�n.
     */
    private InterfazSistemaExperto principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el di�logo e inicializa los elementos de la interfaz.
     * @param pPrincipal Ventana principal de la aplicaci�n. pPrincipal!=null.
     * @param pListaSintomas Lista de s�ntomas registrados actualmente en el sistema. pListaSintomas!=null.
     */
    public DialogoAsociarEnfermedad( InterfazSistemaExperto pPrincipal, Collection pListaSintomas )
    {
        super( pPrincipal, true );

        this.principal = pPrincipal;
        setTitle( "Asociar enfermedad" );
        setResizable( false );
        setLayout( new java.awt.BorderLayout( ) );

        panelInfo = new JPanel( );

        panelInfo.setBorder( new TitledBorder( "Informaci�n" ) );
        GridLayout layout = new GridLayout( 3, 2, 5, 10 );
        panelInfo.setLayout( layout );

        labelSintoma = new JLabel( "S�ntoma" );
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
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicializa el combo especificado con los s�ntomas dadas.
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
     * M�todo que recoge las acciones sobre los objetos que est� escuchando.
     * @param pEvento Evento que se realiz�.
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
                    JOptionPane.showMessageDialog( this, "Ingrese una tasa de mortalidad v�lida.", "Asignar enfermedad", JOptionPane.ERROR_MESSAGE );
                    return;
                }

                principal.asociarEnfermedad( ( String )comboSintoma.getSelectedItem( ), nombre, tasa );

                setVisible( false );
                dispose( );
            }
            catch( NumberFormatException ne )
            {
                JOptionPane.showMessageDialog( this, "Formato de n�mero no v�lido.", "Asignar enfermedad", JOptionPane.ERROR_MESSAGE );
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
