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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Di�logo para asociar un f�rmaco a un s�ntoma.
 */
public class DialogoAsociarFarmaco extends JDialog implements ActionListener
{
    // -----------------------------------------------
    // Constantes
    // -----------------------------------------------

    /**
     * Constante del bot�n de asignar una enfermedad.
     */
    private final static String ASOCIAR_FARMACO = "ASOCIAR_FARMACO";

    /**
     * Constante del bot�n cancelar.
     */
    private final static String CANCELAR = "CANCELAR";

    /**
     * Constante para ingresar un f�rmaco.
     */
    public final static String FARMACO = "�Cu�l f�rmaco recomienda?";

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta s�ntoma.
     */
    private JLabel labelSintoma;

    /**
     * Combo con el s�ntoma.
     */
    private JComboBox comboSintoma;

    /**
     * Etiqueta nombre f�rmaco.
     */
    private JLabel labelNombreFarmaco;

    /**
     * Check box para el tipo de venta del f�rmaco.
     */
    private JCheckBox chbVenta;

    /**
     * Texto con el nombre del f�rmaco.
     */
    private JTextField textNombreFarmaco;

    /**
     * Panel con la informaci�n del f�rmaco.
     */
    private JPanel panelInfo;

    /**
     * Bot�n para asignar el f�rmaco.
     */
    private JButton btnAsignarFarmaco;

    /**
     * Bot�n para cancelar la operaci�n.
     */
    private JButton btnCancelar;

    /**
     * Ventana principal de la aplicaci�n.
     */
    private InterfazSistemaExperto principal;

    // -----------------------------------------------
    // M�todos
    // -----------------------------------------------

    /**
     * Construye el di�logo e inicializa los elementos de la interfaz.
     * @param pPrincipal Ventana principal de la aplicaci�n.  pPrincipal!=null.
     * @param pListaSintomas S�ntomas presentes en el sistema.  pListaSintomas!=null.
     */
    public DialogoAsociarFarmaco( InterfazSistemaExperto pPrincipal, Collection pListaSintomas )
    {
        super( pPrincipal, true );

        this.principal = pPrincipal;
        setTitle( "Asignar f�rmaco" );
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

        btnAsignarFarmaco = new JButton( "Asignar f�rmaco" );
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

        if( comando.equals( ASOCIAR_FARMACO ) )
        {
            String nombre = textNombreFarmaco.getText( );
            if( nombre == null || nombre.equals( "" ) )
            {
                JOptionPane.showMessageDialog( this, "Datos inv�lidos", "Asignar f�rmaco", JOptionPane.ERROR_MESSAGE );
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
