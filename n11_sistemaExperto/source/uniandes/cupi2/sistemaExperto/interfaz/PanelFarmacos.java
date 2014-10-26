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
import java.awt.Color;
import java.util.Collection;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

/**
 * Panel para visualizar los f�rmacos que maneja el sistema experto.
 */
public class PanelFarmacos extends JPanel
{

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Scroll del panel f�rmacos.
     */
    private JScrollPane scroll;

    /**
     * Lista con los f�rmacos.
     */
    private JList listaFarmacos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel.
     */
    public PanelFarmacos( )
    {
        setBorder( new TitledBorder( "F�rmacos" ) );
        setLayout( new BorderLayout( ) );

        listaFarmacos = new JList( );
        listaFarmacos.setBackground( Color.LIGHT_GRAY );
        scroll = new JScrollPane( );
        scroll.setViewportView( listaFarmacos );

        add( scroll, BorderLayout.CENTER );

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Actualiza la lista de f�rmacos.
     * @param pListaFarmacos Lista con los f�rmacos conocidos por el sistema.
     */
    public void actualizar( Collection pListaFarmacos )
    {
        if( pListaFarmacos != null )
            listaFarmacos.setListData( pListaFarmacos.toArray( ) );
    }

}
