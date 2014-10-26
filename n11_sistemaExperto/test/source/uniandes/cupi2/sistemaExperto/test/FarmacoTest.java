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
import uniandes.cupi2.sistemaExperto.mundo.Farmaco;

/**
 * Clase usada para verificar que los m�todos de la clase Farmaco est�n correctamente implementados.
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
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Escenario 1: Construye un nuevo f�rmaco de venta libre.
     */
    private void setupEscenario1( )
    {
        farmaco = new Farmaco( "farmaco1", true );
    }

    /**
     * Prueba 1: Verificar el m�todo constructor de la clase.<br>
     * <b> Objetivo: </b> Probar que le m�todo constructor inicialice correctamente los atributos.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. El nombre del f�rmaco es el nombre que se recibe por par�metro.<br>
     * 2. El f�rmaco es de venta libre.
     */
    public void testConstructor( )
    {
        setupEscenario1( );

        assertEquals( "Error al inicializar el nombre", "farmaco1", farmaco.darNombre( ) );
        assertTrue( "El f�rmaco es de venta libre", farmaco.esVentaLibre( ) );

    }

}