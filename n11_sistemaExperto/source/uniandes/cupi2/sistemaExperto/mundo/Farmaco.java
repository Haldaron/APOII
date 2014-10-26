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
package uniandes.cupi2.sistemaExperto.mundo;

import java.io.Serializable;

/**
 * Clase que representa un f�rmaco registrado en el sistema experto.<br>
 * <b>inv: </b> <br>
 * nombre != null && nombre != "".<br>
 */
public class Farmaco implements Serializable
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Indicador de versi�n para la serializaci�n.
     */
    private static final long serialVersionUID = 300L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Nombre del f�rmaco.
     */
    private String nombre;

    /**
     * Indica si el medicamento es de venta libre o no.
     */
    private boolean ventaLibre;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un f�rmaco. <br>
     * <b> post: </b> Inicializa los atributos (nombre, venta libre) con los valores ingresados por par�metro. <br>
     * @param pNombre Nombre del f�rmaco. pNombre != null && pNombre != "".
     * @param pVentaLibre Si el f�rmaco es de venta libre. True si es de venta libre. False en caso contrario.
     */
    public Farmaco( String pNombre, boolean pVentaLibre )
    {
        nombre = pNombre;
        ventaLibre = pVentaLibre;

        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre del f�rmaco.
     * @return Nombre del f�rmaco.
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna si el f�rmaco es de venta libre.
     * @return True si el f�rmaco es de venta libre. False en caso contrario.
     */
    public boolean esVentaLibre( )
    {
        return ventaLibre;
    }

    /**
     * Retorna la cadena con la que se representa el f�rmaco en la interfaz gr�fica.
     * @return Cadena con la que se representa el f�rmaco en la interfaz gr�fica.
     */
    public String toString( )
    {
        return nombre + " - venta libre: " + ( ( ventaLibre == true ) ? "SI" : "NO" );
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * M�todo que verifica el invariante de la clase.<br>
     * <b>inv: </b> <br>
     * nombre != null && nombre != "".<br>
     */
    private void verificarInvariante( )
    {
        assert ( nombre != null && !nombre.equals( "" ) ) : "El nombre del f�rmaco no es v�lido.";
    }

}