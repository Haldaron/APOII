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
package uniandes.cupi2.sistemaExperto.test;

import junit.framework.TestCase;
import uniandes.cupi2.sistemaExperto.mundo.Enfermedad;

/**
 * Clase usada para verificar que los m�todos de la clase Enfermedad est�n correctamente implementados.
 */
public class EnfermedadTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Enfermedad de prueba.
     */
    private Enfermedad enfermedad;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Escenario 1: Construye una nueva enfermedad.
     */
    private void setupEscenario1( )
    {
        enfermedad = new Enfermedad( "enfermedad1", 10 );
    }

    /**
     * Prueba 1: Verificar el m�todo constructor de la clase.<br>
     * <b> Objetivo: </b> Probar que le m�todo constructor inicialice correctamente los atributos.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. El nombre de la enfermedad es el nombre que se recibe por par�metro.<br>
     */
    public void testConstructor( )
    {
        setupEscenario1( );

        assertEquals( "Error al inicializar el nombre", "enfermedad1", enfermedad.darNombre( ) );
    }

    /**
     * Prueba 2: Verificar que el m�todo toString se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el m�todo retorne el nombre de la enfermedad.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. El m�todo retorna una cadena que contiene el nombre de la enfermedad.
     */
    public void testToString( )
    {
        setupEscenario1( );
        assertEquals( "Error al dar la representaci�n String de la enfermedad", "enfermedad1 - mortalidad del 10%", enfermedad.toString( ) );
    }

}