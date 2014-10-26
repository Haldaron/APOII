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
import javax.swing.JPanel;

/**
 * Diálogo para eliminar un síntoma.
 */
public class DialogoEliminarSintoma extends JDialog implements ActionListener
{
    // -----------------------------------------------
    // Constantes
    // -----------------------------------------------

    /**
     * Constante del comando de eliminar un sintoma.
     */
    private final static String ELIMINAR_SINTOMA = "ELIMINAR_SINTOMA";

    /**
     * Constante del botón cancelar.
     */
    private final static String CANCELAR = "CANCELAR";

    
    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------


    /**
     * Combo con la lista de los síntomas.
     */
    private JComboBox comboSintoma;

    /**
     * Panel con la principal del dialogo.
     */
    private JPanel panelInfo;

    /**
     * Botón para eliminar el síntoma.
     */
    private JButton btnEliminarSintoma;

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
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal!=null.
     * @param pListaSintomas Síntomas presentes en el sistema. pListaSintomas!=null.
     */
    public DialogoEliminarSintoma( InterfazSistemaExperto pPrincipal, Collection pListaSintomas )
    {
        super( pPrincipal, true );

        this.principal = pPrincipal;
        setTitle( "Eliminar una síntoma" );
        setResizable( false );
        setLayout( new java.awt.BorderLayout( ) );

        panelInfo = new JPanel( );
        GridLayout layout = new GridLayout( 2, 2, 5, 10 );
        panelInfo.setLayout( layout );

        JLabel labelSintoma = new JLabel( "Síntoma:" );
        panelInfo.add( labelSintoma );

        comboSintoma = new JComboBox( );
        inicializarCombo( comboSintoma, pListaSintomas );
        panelInfo.add( comboSintoma );

        BorderLayout border = new BorderLayout( );
        border.setHgap( 5 );
        border.setVgap( 5 );
        add( panelInfo, BorderLayout.NORTH );

        JPanel botones = new JPanel( );

        btnEliminarSintoma = new JButton( "Eliminar síntoma" );
        btnEliminarSintoma.setActionCommand( ELIMINAR_SINTOMA );
        btnEliminarSintoma.addActionListener( this );
        botones.add( btnEliminarSintoma );

        btnCancelar = new JButton( "Cancelar" );
        btnCancelar.setActionCommand( CANCELAR );
        btnCancelar.addActionListener( this );
        botones.add( btnCancelar );

        add( botones, BorderLayout.SOUTH );

        pack( );
        setLocationRelativeTo( null );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicializa el combo especificado con los síntomas.
     * @param pCombo Combo a ser inicializado.
     * @param pListaSintomas Lista de síntomas.
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

        if( comando.equals( ELIMINAR_SINTOMA ) )
        {
        	principal.eliminarSintoma( (String)comboSintoma.getSelectedItem( ));
        	setVisible( false );
        	dispose( );
        }
        else
        {
            setVisible( false );
            dispose( );
        }
    }

}
