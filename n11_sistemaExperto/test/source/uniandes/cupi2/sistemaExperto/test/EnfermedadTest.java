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
package uniandes.cupi2.sistemaExperto.test;

import junit.framework.TestCase;
import uniandes.cupi2.sistemaExperto.mundo.Enfermedad;

/**
 * Clase usada para verificar que los métodos de la clase Enfermedad estén correctamente implementados.
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
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Escenario 1: Construye una nueva enfermedad.
     */
    private void setupEscenario1( )
    {
        enfermedad = new Enfermedad( "enfermedad1", 10 );
    }

    /**
     * Prueba 1: Verificar el método constructor de la clase.<br>
     * <b> Objetivo: </b> Probar que le método constructor inicialice correctamente los atributos.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. El nombre de la enfermedad es el nombre que se recibe por parámetro.<br>
     */
    public void testConstructor( )
    {
        setupEscenario1( );

        assertEquals( "Error al inicializar el nombre", "enfermedad1", enfermedad.darNombre( ) );
    }

    /**
     * Prueba 2: Verificar que el método toString se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el método retorne el nombre de la enfermedad.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. El método retorna una cadena que contiene el nombre de la enfermedad.
     */
    public void testToString( )
    {
        setupEscenario1( );
        assertEquals( "Error al dar la representación String de la enfermedad", "enfermedad1 - mortalidad del 10%", enfermedad.toString( ) );
    }

}