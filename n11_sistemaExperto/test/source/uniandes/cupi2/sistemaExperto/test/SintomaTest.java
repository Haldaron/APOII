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

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
import uniandes.cupi2.sistemaExperto.mundo.Enfermedad;
import uniandes.cupi2.sistemaExperto.mundo.Farmaco;
import uniandes.cupi2.sistemaExperto.mundo.Sintoma;
import uniandes.cupi2.sistemaExperto.mundo.SistemaExpertoException;

/**
 * Clase usada para verificar que los métodos de la clase Sintoma estén correctamente implementados.
 */
public class SintomaTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Síntoma de prueba.
     */
    private Sintoma sintoma;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Escenario 1: Construye un nuevo síntoma.
     */
    private void setupEscenario1( )
    {
        sintoma = new Sintoma( "Síntoma0" );
    }

    /**
     * Escenario 2: Construye un nuevo síntoma con síntomas hijos, enfermedades y fármacos.
     */
    private void setupEscenario2( )
    {
        try
        {
            setupEscenario1( );

            Sintoma p1 = new Sintoma( "Síntoma01" );
            Sintoma p2 = new Sintoma( "Síntoma02" );
            Sintoma p3 = new Sintoma( "Síntoma03" );

            sintoma.agregarSintoma( p1 );
            sintoma.agregarSintoma( p2 );
            sintoma.agregarSintoma( p3 );

            Sintoma p11 = new Sintoma( "Síntoma11" );
            Sintoma p12 = new Sintoma( "Síntoma12" );
            Sintoma p13 = new Sintoma( "Síntoma13" );

            p1.agregarSintoma( p11 );
            p1.agregarSintoma( p12 );
            p1.agregarSintoma( p13 );

            p3.asociarEnfermedad( "Enfermedad03", 1 );
            p2.asociarFarmaco( "Fármaco02", true );
            p12.asociarEnfermedad( "Enfermedad12", 2 );
            p12.asociarFarmaco( "Fármaco12", true );
        }
        catch( SistemaExpertoException e )
        {
            fail( e.getMessage( ) );
        }
    }

    /**
     * Prueba 1: Verificar que el método darEnfermedadesSintoma se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el método encuentre las enfermedades correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. La lista resultado para Síntoma0 tiene 2 elementos.<br>
     * 2. La lista resultado para Síntoma12 tiene 1 elementos.<br>
     * 3. La lista resultado para Síntoma3 tiene 1 elementos.<br>
     * 4. La lista resultado para Síntoma11 tiene 0 elementos.<br>
     */
    public void testDarListaEnfermedadesSintoma( )
    {
        setupEscenario2( );
        Collection c = new LinkedList( );
        sintoma.darEnfermedadesSintoma( c );
        assertEquals( "La lista de enfermedades fue mal calculada", 2, c.size( ) );

        c = new LinkedList( );
        sintoma.buscarSintoma( "Síntoma12" ).darEnfermedadesSintoma( c );
        assertEquals( "La lista de enfermedades fue mal calculada", 1, c.size( ) );

        c = new LinkedList( );
        sintoma.buscarSintoma( "Síntoma03" ).darEnfermedadesSintoma( c );
        assertEquals( "La lista de enfermedades fue mal calculada", 1, c.size( ) );

        c = new LinkedList( );
        sintoma.buscarSintoma( "Síntoma11" ).darEnfermedadesSintoma( c );
        assertEquals( "La lista de enfermedades fue mal calculada", 0, c.size( ) );
    }

    /**
     * Prueba 2: Verificar que el método esHoja se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el método calcula si el síntoma es hoja correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Escenario1: El síntoma es hoja.<br>
     * 2. Escenario2: El síntoma no es hoja.<br>
     */
    public void testEsHoja( )
    {
        setupEscenario1( );
        assertTrue( "El síntoma si es hoja", sintoma.esHoja( ) );
        setupEscenario2( );
        assertFalse( "El síntoma no es hoja", sintoma.esHoja( ) );
    }

    /**
     * Prueba 3: Verificar que el método darSintomas se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el método encuentre los síntomas hijos correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Escenario1: La lista resultado tiene 0 elementos.<br>
     * 2. Escenario2: La lista resultado tiene 3 elementos.<br>
     */
    public void testDarHijos( )
    {
        setupEscenario1( );
        assertEquals( "Los síntomas hijos están mal calculados", 0, sintoma.darSintomasHijos( ).size( ) );
        setupEscenario2( );
        assertEquals( "Los síntomas hijos están mal calculados", 3, sintoma.darSintomasHijos( ).size( ) );
    }

    /**
     * Prueba 4: Verificar que el método darListaSintomas se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el método encuentre los síntomas correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. La lista resultado tiene 7 elementos.<br>
     * 2. La lista está bien formada y tiene el orden correcto.<br>
     */
    public void testDarListaSintomas( )
    {
        setupEscenario2( );
        List c = new LinkedList( );
        sintoma.darListaSintomas( c );
        assertEquals( "La lista de síntomas fue mal calculada", 7, c.size( ) );
        assertEquals( "La lista de síntomas fue mal calculada", "Síntoma0", ( ( Sintoma )c.get( 0 ) ).darNombre( ) );
        assertEquals( "La lista de síntomas fue mal calculada", "Síntoma01", ( ( Sintoma )c.get( 1 ) ).darNombre( ) );
        assertEquals( "La lista de síntomas fue mal calculada", "Síntoma11", ( ( Sintoma )c.get( 2 ) ).darNombre( ) );
        assertEquals( "La lista de síntomas fue mal calculada", "Síntoma12", ( ( Sintoma )c.get( 3 ) ).darNombre( ) );
        assertEquals( "La lista de síntomas fue mal calculada", "Síntoma13", ( ( Sintoma )c.get( 4 ) ).darNombre( ) );
        assertEquals( "La lista de síntomas fue mal calculada", "Síntoma02", ( ( Sintoma )c.get( 5 ) ).darNombre( ) );
        assertEquals( "La lista de síntomas fue mal calculada", "Síntoma03", ( ( Sintoma )c.get( 6 ) ).darNombre( ) );
    }

    /**
     * Prueba 5: Verificar que el método darListaFarmacos se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el método encuentre los fármacos correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. La lista resultado tiene 2 elemento.<br>
     * 2. La lista está bien formada y tiene el orden correcto.<br>
     */
    public void testDarListaFarmacos( )
    {
        setupEscenario2( );
        List c = new LinkedList( );
        sintoma.darListaFarmacos( c );
        assertEquals( "La lista de fármacos fue mal calculada", 2, c.size( ) );
        assertEquals( "La lista de fármacos fue mal calculada", "Fármaco12", ( ( Farmaco )c.get( 0 ) ).darNombre( ) );
        assertEquals( "La lista de fármacos fue mal calculada", "Fármaco02", ( ( Farmaco )c.get( 1 ) ).darNombre( ) );
    }

    /**
     * Prueba 6: Método se encarga de verificar que el método darListaEnfermedades se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el método encuentre las enfermedades correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. La lista resultado tiene 2 elementos.<br>
     * 2. La lista está bien formada y tiene el orden correcto.<br>
     */
    public void testDarListaEnfermedades( )
    {
        setupEscenario2( );
        List c = new LinkedList( );
        sintoma.darListaEnfermedades( c );
        assertEquals( "La lista de enfermedades fue mal calculada", 2, c.size( ) );
        assertEquals( "La lista de enfermedades fue mal calculada", "Enfermedad12", ( ( Enfermedad )c.get( 0 ) ).darNombre( ) );
        assertEquals( "La lista de enfermedades fue mal calculada", "Enfermedad03", ( ( Enfermedad )c.get( 1 ) ).darNombre( ) );
    }

    /**
     * Prueba 7: Verificar que el método darFarmacosEnfermedad se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el método encuentre los fármacos correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Fármaco12 es encontrado en Enfermedad12.<br>
     * 2. Enfermedad03 no tiene fármaco.<br>
     * 3. Enfermedad5 arroja excepción.<br>
     */
    public void testDarFarmacosEnfermedad( )
    {
        setupEscenario2( );
        try
        {
            List hijos = new LinkedList( );
            sintoma.darFarmacosEnfermedad( "Enfermedad03", hijos );
            assertEquals( "Enfermedad1 no tiene fármaco", 0, hijos.size( ) );
            sintoma.darFarmacosEnfermedad( "Enfermedad12", hijos );
            assertEquals( "Enfermedad1 no tiene fármaco", 1, hijos.size( ) );
            assertEquals( "No se encontró el fármaco de Enfermedad12", "Fármaco12", ( ( Farmaco )hijos.get( 0 ) ).darNombre( ) );
        }
        catch( SistemaExpertoException e )
        {
            fail( e.getMessage( ) );
        }

        try
        {
            sintoma.darFarmacosEnfermedad( "Enfermedad5", new LinkedList( ) );
            fail( "Enfermedad5 no existe" );
        }
        catch( SistemaExpertoException e )
        {
            // Comportamiento esperado
        }
    }
    /**
     * Prueba 8: Verificar el método constructor de la clase.<br>
     * <b> Objetivo: </b> Probar que le método constructor inicialice correctamente los atributos.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. El nombre del síntoma fue inicializado.<br>
     * 2. La lista de hijos fue inicializada correctamente. <br>
     */
    public void testConstructor( )
    {
        setupEscenario1( );

        assertEquals( "Error al inicializar el nombre del síntoma", "Síntoma0", sintoma.darNombre( ) );
        assertNotNull( "No se inicializó la lista de hijos", sintoma.darSintomasHijos( ) );
        assertEquals( "Error al inicializar los síntomas hijos", 0, sintoma.darSintomasHijos( ).size( ) );
    }

    /**
     * Prueba 9: Verificar que el método agregarSintoma se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el método agregue correctamente los síntomas.<br>
     * <b> Resultados esperados después de agregar 3 síntomas: </b> <br>
     * 1. El síntoma tiene tres síntomas hijos<br>
     */
    public void testAgregarSintoma( )
    {
        setupEscenario1( );

        try
        {
            sintoma.agregarSintoma( new Sintoma( "descripcion2" ) );
            sintoma.agregarSintoma( new Sintoma( "descripcion3" ) );
            sintoma.agregarSintoma( new Sintoma( "descripcion4" ) );
        }
        catch( SistemaExpertoException e )
        {
            fail( e.getMessage( ) );
        }

        assertEquals( "El número de hijos no es correcto", 3, sintoma.darSintomasHijos( ).size( ) );
    }

    /**
     * Prueba 10: Verificar que el método buscarEnfermedad se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el método encuentre las enfermedades correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. La Enfermedad03 es encontrada en Síntoma03.<br>
     * 2. La Enfermedad6 no es encontrada.<br>
     */
    public void testBuscarEnfermedad( )
    {
        setupEscenario2( );
        assertNotNull( "La enfermedad no fue encontrada", sintoma.buscarEnfermedad( "Enfermedad03" ) );
        assertEquals( "La enfermedad no fue encontrada", "Enfermedad03", sintoma.buscarEnfermedad( "Enfermedad03" ).darNombre( ) );
        assertNull( "La enfermedad fue encontrada", sintoma.buscarEnfermedad( "Enfermedad6" ) );
    }

    /**
     * Prueba 11: Verificar que el método buscarFarmaco se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el método encuentre los fármacos correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Fármaco12 es encontrado en Sintoma12.<br>
     * 2. Fármaco11 no es encontrado.<br>
     */
    public void testBuscarFarmaco( )
    {
        setupEscenario2( );
        assertNotNull( "El fármaco no fue encontrado", sintoma.buscarFarmaco( "Fármaco12" ) );
        assertEquals( "El fármaco no fue encontrado", "Fármaco12", sintoma.buscarFarmaco( "Fármaco12" ).darNombre( ) );
        assertNull( "El fármaco fue encontrado", sintoma.buscarFarmaco( "Fármaco11" ) );
    }
    
    
    /**
     * Prueba 12: Verificar que el método darAltura se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el método que da la altura de un síntoma.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Fármaco0 tiene altura de 1.<br>
     * 2. Fármaco11 tiene altura de 3.<br>
     */
    public void testDarAltura()
    {
    	setupEscenario2( );
    	assertEquals( "La altura no fue la correcta.", 3, sintoma.darAltura() );
    	assertEquals( "La altura no fue la correcta.", 1, sintoma.buscarSintoma( "Síntoma11" ).darAltura() );
    }
    
    /**
     * Prueba 12: Verificar que el método darEnfermedadMasSintomas() se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el método que devuelve la enfermedad con más síntomas.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Escenario 1: Null.<br>
     * 2. Escenario 2: Enfermedad12.<br>
     */
    public void testDarEnfermedadMasSintomas()
    {
    	setupEscenario1();
    	assertEquals( "La enfermedad no fue la esperada.", null, sintoma.darEnfermedadMasSintomas() );
    	
    	setupEscenario2();
    	assertEquals( "La enfermedad no fue la esperada.", "Enfermedad12", sintoma.darEnfermedadMasSintomas().darNombre() );
    	
    }
    
    /**
     * Prueba 13: Verificar que el método eliminarSintoma() se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el método que elimina un síntoma.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al eliminar el síntoma Síntoma01 devuelve un error no es una hoja.<br>
     * 2. Al eliminar un hoja no se debe encontrar el elemento.<br>
     */
    public void testEliminarSintoma()
    {
    	setupEscenario2();
    	try{
    		sintoma.eliminarSintoma("Síntoma01");
    		fail("Debio generar error.");
    	}
    	catch(Exception e){
    		
    	}
    	
    	try{
    		sintoma.eliminarSintoma("Síntoma13");
    		assertNull("Debio eliminarse el objeto.", sintoma.buscarSintoma("Síntoma13"));
    	}
    	catch(Exception e){
    		
    	}
    	
    }
}
