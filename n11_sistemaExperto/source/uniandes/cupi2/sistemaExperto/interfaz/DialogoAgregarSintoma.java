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

import java.util.Collection;
import javax.swing.JDialog;

/**
 * Di�logo usado para agregar un s�ntoma al sistema.
 */
public class DialogoAgregarSintoma extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n.
     */
    private InterfazSistemaExperto ventanaPrincipal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel donde se introducen los datos del nuevo s�ntoma.
     */
    private PanelDatosSintoma panelDatos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el di�logo e inicializa los elementos de la interfaz.
     * @param pPrincipal Ventana principal de la aplicaci�n. pPrincipal!=null.
     * @param pListaSintomas Lista con los s�ntomas que actualmente est�n en el sistema. pListaSintomas!=null.
     */
    public DialogoAgregarSintoma( InterfazSistemaExperto pPrincipal, Collection pListaSintomas )
    {
        super( pPrincipal, true );
        ventanaPrincipal = pPrincipal;

        panelDatos = new PanelDatosSintoma( this, pListaSintomas );
        add( panelDatos );

        pack( );
        setTitle( "Agregar s�ntoma" );
        setResizable( false );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Agrega un nuevo s�ntoma al sistema.
     * @param pNombrePadreSintoma Nombre del s�ntoma bajo el cual se quiere agregar el nuevo s�ntoma. pNombrePadreSintoma != null && pNombrePadreSintoma != "".
     * @param pDescripcion Descripci�n del nuevo s�ntoma. pDescripcion != null && pDescripcion != "".
     */
    public void agregarSintoma( String pNombrePadreSintoma, String pDescripcion )
    {
        ventanaPrincipal.agregarSintoma( pNombrePadreSintoma, pDescripcion );
        setVisible( false );
    }

}
