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

import java.util.Collection;
import javax.swing.JDialog;

/**
 * Diálogo usado para agregar un síntoma al sistema.
 */
public class DialogoAgregarSintoma extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación.
     */
    private InterfazSistemaExperto ventanaPrincipal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel donde se introducen los datos del nuevo síntoma.
     */
    private PanelDatosSintoma panelDatos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el diálogo e inicializa los elementos de la interfaz.
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal!=null.
     * @param pListaSintomas Lista con los síntomas que actualmente están en el sistema. pListaSintomas!=null.
     */
    public DialogoAgregarSintoma( InterfazSistemaExperto pPrincipal, Collection pListaSintomas )
    {
        super( pPrincipal, true );
        ventanaPrincipal = pPrincipal;

        panelDatos = new PanelDatosSintoma( this, pListaSintomas );
        add( panelDatos );

        pack( );
        setTitle( "Agregar síntoma" );
        setResizable( false );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Agrega un nuevo síntoma al sistema.
     * @param pNombrePadreSintoma Nombre del síntoma bajo el cual se quiere agregar el nuevo síntoma. pNombrePadreSintoma != null && pNombrePadreSintoma != "".
     * @param pDescripcion Descripción del nuevo síntoma. pDescripcion != null && pDescripcion != "".
     */
    public void agregarSintoma( String pNombrePadreSintoma, String pDescripcion )
    {
        ventanaPrincipal.agregarSintoma( pNombrePadreSintoma, pDescripcion );
        setVisible( false );
    }

}
