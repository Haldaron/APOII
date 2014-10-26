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
import uniandes.cupi2.sistemaExperto.mundo.Farmaco;

/**
 * Clase usada para verificar que los métodos de la clase Farmaco estén correctamente implementados.
 */
public class FarmacoTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Farmaco de prueba.
     */
    private Farmaco farmaco;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Escenario 1: Construye un nuevo fármaco de venta libre.
     */
    private void setupEscenario1( )
    {
        farmaco = new Farmaco( "farmaco1", true );
    }

    /**
     * Prueba 1: Verificar el método constructor de la clase.<br>
     * <b> Objetivo: </b> Probar que le método constructor inicialice correctamente los atributos.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. El nombre del fármaco es el nombre que se recibe por parámetro.<br>
     * 2. El fármaco es de venta libre.
     */
    public void testConstructor( )
    {
        setupEscenario1( );

        assertEquals( "Error al inicializar el nombre", "farmaco1", farmaco.darNombre( ) );
        assertTrue( "El fármaco es de venta libre", farmaco.esVentaLibre( ) );

    }

}