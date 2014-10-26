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
package uniandes.cupi2.sistemaExperto.mundo;

import java.io.Serializable;

/**
 * Clase que representa un fármaco registrado en el sistema experto.<br>
 * <b>inv: </b> <br>
 * nombre != null && nombre != "".<br>
 */
public class Farmaco implements Serializable
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Indicador de versión para la serialización.
     */
    private static final long serialVersionUID = 300L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Nombre del fármaco.
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
     * Construye un fármaco. <br>
     * <b> post: </b> Inicializa los atributos (nombre, venta libre) con los valores ingresados por parámetro. <br>
     * @param pNombre Nombre del fármaco. pNombre != null && pNombre != "".
     * @param pVentaLibre Si el fármaco es de venta libre. True si es de venta libre. False en caso contrario.
     */
    public Farmaco( String pNombre, boolean pVentaLibre )
    {
        nombre = pNombre;
        ventaLibre = pVentaLibre;

        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre del fármaco.
     * @return Nombre del fármaco.
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna si el fármaco es de venta libre.
     * @return True si el fármaco es de venta libre. False en caso contrario.
     */
    public boolean esVentaLibre( )
    {
        return ventaLibre;
    }

    /**
     * Retorna la cadena con la que se representa el fármaco en la interfaz gráfica.
     * @return Cadena con la que se representa el fármaco en la interfaz gráfica.
     */
    public String toString( )
    {
        return nombre + " - venta libre: " + ( ( ventaLibre == true ) ? "SI" : "NO" );
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Método que verifica el invariante de la clase.<br>
     * <b>inv: </b> <br>
     * nombre != null && nombre != "".<br>
     */
    private void verificarInvariante( )
    {
        assert ( nombre != null && !nombre.equals( "" ) ) : "El nombre del fármaco no es válido.";
    }

}