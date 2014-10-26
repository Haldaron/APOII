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
 * Clase que representa una enfermedad registrada en el sistema experto.<br>
 * <b>inv: </b> <br>
 * nombre != null && nombre != "".<br>
 * tasaMortalidad entre 0 y 100.
 */
public class Enfermedad implements Serializable
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Indicador de versión para la serialización.
     */
    private static final long serialVersionUID = 200L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Nombre de la enfermedad.
     */
    private String nombre;

    /**
     * Tasa de mortalidad de la enfermedad.
     */
    private int tasaMortalidad;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una enfermedad. <br>
     * <b> post: </b> Inicializa el nombre de la enfermedad y la tasa de mortalidad con los parámetros. <br>
     * @param pNombre Nombre de la enfermedad. pNombre != null && pNombre != "".
     * @param pTasaMortalidad Tasa de mortalidad de la enfermedad. Tasa entre 0 y 100.
     */
    public Enfermedad( String pNombre, int pTasaMortalidad )
    {
        nombre = pNombre;
        tasaMortalidad = pTasaMortalidad;

        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre de la enfermedad.
     * @return Nombre de la enfermedad.
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna la cadena con la que se representa la enfermedad en la interfaz gráfica.
     * @return Cadena con la que se representa la enfermedad en la interfaz gráfica.
     */
    public String toString( )
    {
        return nombre + " - mortalidad del " + tasaMortalidad + "%";
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Método que verifica la invariante de la clase.<br>
     * <b>inv: </b> <br>
     * nombre != null && nombre != "".<br>
     * tasaMortalidad entre 0 y 100.
     * 
     */
    private void verificarInvariante( )
    {
        assert ( nombre != null && !nombre.equals( "" ) ) : "El nombre de la enfermedad no es válido.";
        assert ( tasaMortalidad >= 0 && tasaMortalidad <= 100 ) : "La tasa de mortalidad no es válida.";
    }

}