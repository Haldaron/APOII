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
import javax.swing.JPanel;

/**
 * Di�logo para eliminar un s�ntoma.
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
     * Constante del bot�n cancelar.
     */
    private final static String CANCELAR = "CANCELAR";

    
    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------


    /**
     * Combo con la lista de los s�ntomas.
     */
    private JComboBox comboSintoma;

    /**
     * Panel con la principal del dialogo.
     */
    private JPanel panelInfo;

    /**
     * Bot�n para eliminar el s�ntoma.
     */
    private JButton btnEliminarSintoma;

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
     * @param pPrincipal Ventana principal de la aplicaci�n. pPrincipal!=null.
     * @param pListaSintomas S�ntomas presentes en el sistema. pListaSintomas!=null.
     */
    public DialogoEliminarSintoma( InterfazSistemaExperto pPrincipal, Collection pListaSintomas )
    {
        super( pPrincipal, true );

        this.principal = pPrincipal;
        setTitle( "Eliminar una s�ntoma" );
        setResizable( false );
        setLayout( new java.awt.BorderLayout( ) );

        panelInfo = new JPanel( );
        GridLayout layout = new GridLayout( 2, 2, 5, 10 );
        panelInfo.setLayout( layout );

        JLabel labelSintoma = new JLabel( "S�ntoma:" );
        panelInfo.add( labelSintoma );

        comboSintoma = new JComboBox( );
        inicializarCombo( comboSintoma, pListaSintomas );
        panelInfo.add( comboSintoma );

        BorderLayout border = new BorderLayout( );
        border.setHgap( 5 );
        border.setVgap( 5 );
        add( panelInfo, BorderLayout.NORTH );

        JPanel botones = new JPanel( );

        btnEliminarSintoma = new JButton( "Eliminar s�ntoma" );
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
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicializa el combo especificado con los s�ntomas.
     * @param pCombo Combo a ser inicializado.
     * @param pListaSintomas Lista de s�ntomas.
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
