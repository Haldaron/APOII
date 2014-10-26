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

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
import uniandes.cupi2.sistemaExperto.mundo.Enfermedad;
import uniandes.cupi2.sistemaExperto.mundo.Farmaco;
import uniandes.cupi2.sistemaExperto.mundo.Sintoma;
import uniandes.cupi2.sistemaExperto.mundo.SistemaExpertoException;

/**
 * Clase usada para verificar que los m�todos de la clase Sintoma est�n correctamente implementados.
 */
public class SintomaTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * S�ntoma de prueba.
     */
    private Sintoma sintoma;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Escenario 1: Construye un nuevo s�ntoma.
     */
    private void setupEscenario1( )
    {
        sintoma = new Sintoma( "S�ntoma0" );
    }

    /**
     * Escenario 2: Construye un nuevo s�ntoma con s�ntomas hijos, enfermedades y f�rmacos.
     */
    private void setupEscenario2( )
    {
        try
        {
            setupEscenario1( );

            Sintoma p1 = new Sintoma( "S�ntoma01" );
            Sintoma p2 = new Sintoma( "S�ntoma02" );
            Sintoma p3 = new Sintoma( "S�ntoma03" );

            sintoma.agregarSintoma( p1 );
            sintoma.agregarSintoma( p2 );
            sintoma.agregarSintoma( p3 );

            Sintoma p11 = new Sintoma( "S�ntoma11" );
            Sintoma p12 = new Sintoma( "S�ntoma12" );
            Sintoma p13 = new Sintoma( "S�ntoma13" );

            p1.agregarSintoma( p11 );
            p1.agregarSintoma( p12 );
            p1.agregarSintoma( p13 );

            p3.asociarEnfermedad( "Enfermedad03", 1 );
            p2.asociarFarmaco( "F�rmaco02", true );
            p12.asociarEnfermedad( "Enfermedad12", 2 );
            p12.asociarFarmaco( "F�rmaco12", true );
        }
        catch( SistemaExpertoException e )
        {
            fail( e.getMessage( ) );
        }
    }

    /**
     * Prueba 1: Verificar que el m�todo darEnfermedadesSintoma se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el m�todo encuentre las enfermedades correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. La lista resultado para S�ntoma0 tiene 2 elementos.<br>
     * 2. La lista resultado para S�ntoma12 tiene 1 elementos.<br>
     * 3. La lista resultado para S�ntoma3 tiene 1 elementos.<br>
     * 4. La lista resultado para S�ntoma11 tiene 0 elementos.<br>
     */
    public void testDarListaEnfermedadesSintoma( )
    {
        setupEscenario2( );
        Collection c = new LinkedList( );
        sintoma.darEnfermedadesSintoma( c );
        assertEquals( "La lista de enfermedades fue mal calculada", 2, c.size( ) );

        c = new LinkedList( );
        sintoma.buscarSintoma( "S�ntoma12" ).darEnfermedadesSintoma( c );
        assertEquals( "La lista de enfermedades fue mal calculada", 1, c.size( ) );

        c = new LinkedList( );
        sintoma.buscarSintoma( "S�ntoma03" ).darEnfermedadesSintoma( c );
        assertEquals( "La lista de enfermedades fue mal calculada", 1, c.size( ) );

        c = new LinkedList( );
        sintoma.buscarSintoma( "S�ntoma11" ).darEnfermedadesSintoma( c );
        assertEquals( "La lista de enfermedades fue mal calculada", 0, c.size( ) );
    }

    /**
     * Prueba 2: Verificar que el m�todo esHoja se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el m�todo calcula si el s�ntoma es hoja correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Escenario1: El s�ntoma es hoja.<br>
     * 2. Escenario2: El s�ntoma no es hoja.<br>
     */
    public void testEsHoja( )
    {
        setupEscenario1( );
        assertTrue( "El s�ntoma si es hoja", sintoma.esHoja( ) );
        setupEscenario2( );
        assertFalse( "El s�ntoma no es hoja", sintoma.esHoja( ) );
    }

    /**
     * Prueba 3: Verificar que el m�todo darSintomas se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el m�todo encuentre los s�ntomas hijos correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Escenario1: La lista resultado tiene 0 elementos.<br>
     * 2. Escenario2: La lista resultado tiene 3 elementos.<br>
     */
    public void testDarHijos( )
    {
        setupEscenario1( );
        assertEquals( "Los s�ntomas hijos est�n mal calculados", 0, sintoma.darSintomasHijos( ).size( ) );
        setupEscenario2( );
        assertEquals( "Los s�ntomas hijos est�n mal calculados", 3, sintoma.darSintomasHijos( ).size( ) );
    }

    /**
     * Prueba 4: Verificar que el m�todo darListaSintomas se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el m�todo encuentre los s�ntomas correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. La lista resultado tiene 7 elementos.<br>
     * 2. La lista est� bien formada y tiene el orden correcto.<br>
     */
    public void testDarListaSintomas( )
    {
        setupEscenario2( );
        List c = new LinkedList( );
        sintoma.darListaSintomas( c );
        assertEquals( "La lista de s�ntomas fue mal calculada", 7, c.size( ) );
        assertEquals( "La lista de s�ntomas fue mal calculada", "S�ntoma0", ( ( Sintoma )c.get( 0 ) ).darNombre( ) );
        assertEquals( "La lista de s�ntomas fue mal calculada", "S�ntoma01", ( ( Sintoma )c.get( 1 ) ).darNombre( ) );
        assertEquals( "La lista de s�ntomas fue mal calculada", "S�ntoma11", ( ( Sintoma )c.get( 2 ) ).darNombre( ) );
        assertEquals( "La lista de s�ntomas fue mal calculada", "S�ntoma12", ( ( Sintoma )c.get( 3 ) ).darNombre( ) );
        assertEquals( "La lista de s�ntomas fue mal calculada", "S�ntoma13", ( ( Sintoma )c.get( 4 ) ).darNombre( ) );
        assertEquals( "La lista de s�ntomas fue mal calculada", "S�ntoma02", ( ( Sintoma )c.get( 5 ) ).darNombre( ) );
        assertEquals( "La lista de s�ntomas fue mal calculada", "S�ntoma03", ( ( Sintoma )c.get( 6 ) ).darNombre( ) );
    }

    /**
     * Prueba 5: Verificar que el m�todo darListaFarmacos se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el m�todo encuentre los f�rmacos correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. La lista resultado tiene 2 elemento.<br>
     * 2. La lista est� bien formada y tiene el orden correcto.<br>
     */
    public void testDarListaFarmacos( )
    {
        setupEscenario2( );
        List c = new LinkedList( );
        sintoma.darListaFarmacos( c );
        assertEquals( "La lista de f�rmacos fue mal calculada", 2, c.size( ) );
        assertEquals( "La lista de f�rmacos fue mal calculada", "F�rmaco12", ( ( Farmaco )c.get( 0 ) ).darNombre( ) );
        assertEquals( "La lista de f�rmacos fue mal calculada", "F�rmaco02", ( ( Farmaco )c.get( 1 ) ).darNombre( ) );
    }

    /**
     * Prueba 6: M�todo se encarga de verificar que el m�todo darListaEnfermedades se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el m�todo encuentre las enfermedades correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. La lista resultado tiene 2 elementos.<br>
     * 2. La lista est� bien formada y tiene el orden correcto.<br>
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
     * Prueba 7: Verificar que el m�todo darFarmacosEnfermedad se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el m�todo encuentre los f�rmacos correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. F�rmaco12 es encontrado en Enfermedad12.<br>
     * 2. Enfermedad03 no tiene f�rmaco.<br>
     * 3. Enfermedad5 arroja excepci�n.<br>
     */
    public void testDarFarmacosEnfermedad( )
    {
        setupEscenario2( );
        try
        {
            List hijos = new LinkedList( );
            sintoma.darFarmacosEnfermedad( "Enfermedad03", hijos );
            assertEquals( "Enfermedad1 no tiene f�rmaco", 0, hijos.size( ) );
            sintoma.darFarmacosEnfermedad( "Enfermedad12", hijos );
            assertEquals( "Enfermedad1 no tiene f�rmaco", 1, hijos.size( ) );
            assertEquals( "No se encontr� el f�rmaco de Enfermedad12", "F�rmaco12", ( ( Farmaco )hijos.get( 0 ) ).darNombre( ) );
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
     * Prueba 8: Verificar el m�todo constructor de la clase.<br>
     * <b> Objetivo: </b> Probar que le m�todo constructor inicialice correctamente los atributos.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. El nombre del s�ntoma fue inicializado.<br>
     * 2. La lista de hijos fue inicializada correctamente. <br>
     */
    public void testConstructor( )
    {
        setupEscenario1( );

        assertEquals( "Error al inicializar el nombre del s�ntoma", "S�ntoma0", sintoma.darNombre( ) );
        assertNotNull( "No se inicializ� la lista de hijos", sintoma.darSintomasHijos( ) );
        assertEquals( "Error al inicializar los s�ntomas hijos", 0, sintoma.darSintomasHijos( ).size( ) );
    }

    /**
     * Prueba 9: Verificar que el m�todo agregarSintoma se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el m�todo agregue correctamente los s�ntomas.<br>
     * <b> Resultados esperados despu�s de agregar 3 s�ntomas: </b> <br>
     * 1. El s�ntoma tiene tres s�ntomas hijos<br>
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

        assertEquals( "El n�mero de hijos no es correcto", 3, sintoma.darSintomasHijos( ).size( ) );
    }

    /**
     * Prueba 10: Verificar que el m�todo buscarEnfermedad se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el m�todo encuentre las enfermedades correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. La Enfermedad03 es encontrada en S�ntoma03.<br>
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
     * Prueba 11: Verificar que el m�todo buscarFarmaco se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el m�todo encuentre los f�rmacos correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. F�rmaco12 es encontrado en Sintoma12.<br>
     * 2. F�rmaco11 no es encontrado.<br>
     */
    public void testBuscarFarmaco( )
    {
        setupEscenario2( );
        assertNotNull( "El f�rmaco no fue encontrado", sintoma.buscarFarmaco( "F�rmaco12" ) );
        assertEquals( "El f�rmaco no fue encontrado", "F�rmaco12", sintoma.buscarFarmaco( "F�rmaco12" ).darNombre( ) );
        assertNull( "El f�rmaco fue encontrado", sintoma.buscarFarmaco( "F�rmaco11" ) );
    }
    
    
    /**
     * Prueba 12: Verificar que el m�todo darAltura se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el m�todo que da la altura de un s�ntoma.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. F�rmaco0 tiene altura de 1.<br>
     * 2. F�rmaco11 tiene altura de 3.<br>
     */
    public void testDarAltura()
    {
    	setupEscenario2( );
    	assertEquals( "La altura no fue la correcta.", 3, sintoma.darAltura() );
    	assertEquals( "La altura no fue la correcta.", 1, sintoma.buscarSintoma( "S�ntoma11" ).darAltura() );
    }
    
    /**
     * Prueba 12: Verificar que el m�todo darEnfermedadMasSintomas() se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el m�todo que devuelve la enfermedad con m�s s�ntomas.<br>
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
     * Prueba 13: Verificar que el m�todo eliminarSintoma() se encuentre correctamente implementado.<br>
     * <b> Objetivo: </b> Probar que el m�todo que elimina un s�ntoma.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al eliminar el s�ntoma S�ntoma01 devuelve un error no es una hoja.<br>
     * 2. Al eliminar un hoja no se debe encontrar el elemento.<br>
     */
    public void testEliminarSintoma()
    {
    	setupEscenario2();
    	try{
    		sintoma.eliminarSintoma("S�ntoma01");
    		fail("Debio generar error.");
    	}
    	catch(Exception e){
    		
    	}
    	
    	try{
    		sintoma.eliminarSintoma("S�ntoma13");
    		assertNull("Debio eliminarse el objeto.", sintoma.buscarSintoma("S�ntoma13"));
    	}
    	catch(Exception e){
    		
    	}
    	
    }
}
