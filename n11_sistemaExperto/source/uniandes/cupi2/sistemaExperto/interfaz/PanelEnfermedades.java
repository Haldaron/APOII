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
import java.awt.Color;

import java.util.Collection;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

/**
 * Panel para visualizar las enfermedades que maneja el sistema experto.
 */
public class PanelEnfermedades extends JPanel
{

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Scroll del panel enfermedades.
     */
    private JScrollPane scroll;

    /**
     * Lista donde se visualizan las enfermedades.
     */
    private JList listaEnfermedades;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel.
     */
    public PanelEnfermedades( )
    {

        setBorder( new TitledBorder( "Enfermedades" ) );
        setLayout( new BorderLayout( ) );

        listaEnfermedades = new JList( );
        listaEnfermedades.setBackground( Color.PINK );
        scroll = new JScrollPane( );
        scroll.setViewportView( listaEnfermedades );

        add( scroll, BorderLayout.CENTER );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza la lista de enfermedades.
     * @param pListaEnfermedades Lista con las enfermedades conocidas por el sistema.
     */
    public void actualizar( Collection pListaEnfermedades )
    {
        if( pListaEnfermedades != null )
            listaEnfermedades.setListData( pListaEnfermedades.toArray( ) );
    }

}
