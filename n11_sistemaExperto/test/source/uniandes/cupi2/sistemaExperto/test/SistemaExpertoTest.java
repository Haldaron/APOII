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

import java.util.List;

import junit.framework.TestCase;
import uniandes.cupi2.sistemaExperto.mundo.Enfermedad;
import uniandes.cupi2.sistemaExperto.mundo.Farmaco;
import uniandes.cupi2.sistemaExperto.mundo.Sintoma;
import uniandes.cupi2.sistemaExperto.mundo.SistemaExperto;
import uniandes.cupi2.sistemaExperto.mundo.SistemaExpertoException;

/**
 * Clase usada para verificar que los métodos de la clase SistemaExperto estén correctamente implementados.
 */
public class SistemaExpertoTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Sistema experto de prueba.
     */
    private SistemaExperto sistemaExperto;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Escenario 1: Construye un SistemaExperto vacío.
     */
    private void setupEscenario1( )
    {
        try
        {
            sistemaExperto = new SistemaExperto( "Síntoma0" );
        }
        catch( SistemaExpertoException e )
        {
            fail( e.getMessage( ) );
        }
    }

    /**
     * Escenario 2: Construye un SistemeExperto a partir de un archivo de datos<br> .
     * La información de este archivo es la mostrada en las imágenes del documento de descripción del sistema.
     */
    private void setupEscenario2( )
    {
        try
        {
            sistemaExperto = new SistemaExperto( "./test/data/sistema.dat" );
        }
        catch( SistemaExpertoException e )
        {
            fail( e.getMessage( ) );
        }
    }

    /**
     * Prueba 1: Método se encarga de verificar el método constructor de la clase.<br>
     * <b> Objetivo: </b> Probar que le método constructor inicialice correctamente los atributos.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. La lista de síntomas no es null.<br>
     * 2. El sistema tiene 7 síntomas. <br>
     */
    public void testConstructor( )
    {
        setupEscenario2( );
        assertNotNull( "La primera enfermedad no debería ser nula", sistemaExperto.darPrimerSintoma( ) );
        assertEquals( "No se inicializó bien el sistema", 7, sistemaExperto.darListaSintomas( ).size( ) );
    }

    /**
     * Prueba 2: Método se encarga de verificar el método agregarSintoma.<br>
     * <b> Objetivo: </b> Probar que le método funcione correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se agregan los síntomas correctamente bajo el síntoma Cefalea.<br>
     * 2. Al agregar el síntoma Tos se genera una excepción 3. Al agregar un síntoma bajo otro inexistente se genera un excepción.
     */
    public void testAgregarSintoma( )
    {
        setupEscenario2( );
        Sintoma s = sistemaExperto.buscarSintoma( "Cefalea" );
        try
        {
            assertTrue( s.esHoja( ) );
            sistemaExperto.agregarSintoma( "Cefalea", "Síntoma1" );
            assertEquals( "No se agregó bien el síntoma", 8, sistemaExperto.darListaSintomas( ).size( ) );
            assertFalse( "No se agregó bien el síntoma", s.esHoja( ) );
            assertEquals( "No se agregó bien el síntoma", 1, s.darSintomasHijos( ).size( ) );
        }
        catch( SistemaExpertoException e )
        {
            fail( e.getMessage( ) );
        }

        try
        {
            sistemaExperto.agregarSintoma( "Cefalea", "Tos" );
            fail( "El síntoma Tos ya existe en el árbol de síntomas" );
        }
        catch( SistemaExpertoException e )
        {
            // Comportamiento esperado
        }

        try
        {
            sistemaExperto.agregarSintoma(  "inexistente" , "Síntoma 2" );
            fail( "El síntoma padre no pertenece al árbol de síntomas" );
        }
        catch( SistemaExpertoException e )
        {
            // Comportamiento esperado
        }
    }

    /**
     * Prueba 3: Método se encarga de verificar el método buscarFarmaco.<br>
     * <b> Objetivo: </b> Probar que le método funcione correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. El fármaco far no es encontrado.<br>
     * 2. El fármaco Acetaminofén es encontrado.<br>
     */
    public void testBuscarFarmaco( )
    {
        setupEscenario2( );
        assertNull( "El fármaco no existe", sistemaExperto.buscarFarmaco( "far" ) );
        assertNotNull( "El fármaco si existe", sistemaExperto.buscarFarmaco( "Acetaminofen" ) );
    }

    /**
     * Prueba 4: Método se encarga de verificar el método darEnfermedadesAsociadasSintoma.<br>
     * <b> Objetivo: </b> Probar que le método funcione correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. El síntoma sint no tiene enfermedades asociadas.<br>
     * 2. El síntoma Flemas tiene asociada 1 enfermedad.<br>
     * 3. El síntoma Ahogos tiene asociada 2 enfermedad.<br>
     * 4. El síntoma Tos tiene asociada 3 enfermedad.<br>
     */
    public void testDarEnfermedadesAsociadasSintoma( )
    {
        setupEscenario2( );
        assertEquals( "El síntoma sint no tiene enfermedades asociadas", 0, sistemaExperto.darEnfermedadesAsociadasSintoma( "sint" ).size( ) );
        assertEquals( "El síntoma flemas tiene 1 enfermedades asociadas", 1, sistemaExperto.darEnfermedadesAsociadasSintoma( "Flemas" ).size( ) );
        assertEquals( "El síntoma ahogos tiene 3 enfermedades asociadas", 3, sistemaExperto.darEnfermedadesAsociadasSintoma( "Ahogos" ).size( ) );
        assertEquals( "El síntoma tos tiene 3 enfermedades asociadas", 3, sistemaExperto.darEnfermedadesAsociadasSintoma( "Tos" ).size( ) );
    }

    /**
     * Prueba 5: Método se encarga de verificar el método darListaEnfermedades.<br>
     * <b> Objetivo: </b> Probar que le método funcione correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Escenario1: no hay enfermedades.<br>
     * 2. Escenario2: hay 2 enfermedades.<br>
     */
    public void testDarListaEnfermedades( )
    {
        setupEscenario1( );
        assertEquals( "Lista de enfermedades mal calculada", 0, sistemaExperto.darListaEnfermedades( ).size( ) );
        setupEscenario2( );
        List enfermedades = sistemaExperto.darListaEnfermedades( );
        assertEquals( "Lista de enfermedades mal calculada", 3, enfermedades.size( ) );
        assertEquals( "Lista de enfermedades mal calculada", "Asma", ( ( Enfermedad )enfermedades.get( 0 ) ).darNombre( ) );
        assertEquals( "Lista de enfermedades mal calculada", "Bronquitis", ( ( Enfermedad )enfermedades.get( 1 ) ).darNombre( ) );
        assertEquals( "Lista de enfermedades mal calculada", "Gripa común", ( ( Enfermedad )enfermedades.get( 2 ) ).darNombre( ) );
    }

    /**
     * Prueba 6: Método se encarga de verificar el método darListaFarmacos.<br>
     * <b> Objetivo: </b> Probar que le método funcione correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Escenario1: no hay fármacos.<br>
     * 2. Escenario2: hay 3 fármaco.<br>
     */
    public void testDarListaFarmacos( )
    {
        setupEscenario1( );
        assertEquals( "Lista de fármacos mal calculada", 0, sistemaExperto.darListaFarmacos( ).size( ) );
        setupEscenario2( );
        List farmacos = sistemaExperto.darListaFarmacos( );
        assertEquals( "Lista de fármacos mal calculada", 2, farmacos.size( ) );
        assertEquals( "Lista de fármacos mal calculada", "Acetaminofen", ( ( Farmaco )farmacos.get( 0 ) ).darNombre( ) );
        assertEquals( "Lista de fármacos mal calculada", "Noxpirin", ( ( Farmaco )farmacos.get( 1 ) ).darNombre( ) );
        
    }

    /**
     * Prueba 7: Método se encarga de verificar el método darListaSintomas.<br>
     * <b> Objetivo: </b> Probar que le método funcione correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Escenario1: no hay síntomas.<br>
     * 2. Escenario2: hay 7 síntomas.<br>
     */
    public void testDarListaSintomas( )
    {
        setupEscenario1( );
        assertEquals( "Lista de síntomas mal calculada", 0, sistemaExperto.darListaSintomas( ).size( ) );
        setupEscenario2( );
        List sintomas = sistemaExperto.darListaSintomas( );
        assertEquals( "Lista de síntomas mal calculada", 7, sintomas.size( ) );
        assertEquals( "Lista de síntomas mal calculada", "Tos", ( ( Sintoma )sintomas.get( 0 ) ).darNombre( ) );
        assertEquals( "Lista de síntomas mal calculada", "Ahogos", ( ( Sintoma )sintomas.get( 1 ) ).darNombre( ) );
        assertEquals( "Lista de síntomas mal calculada", "Ruido al respirar", ( ( Sintoma )sintomas.get( 2 ) ).darNombre( ) );
        assertEquals( "Lista de síntomas mal calculada", "Fiebre", ( ( Sintoma )sintomas.get( 3 ) ).darNombre( ) );
        assertEquals( "Lista de síntomas mal calculada", "Flemas", ( ( Sintoma )sintomas.get( 4 ) ).darNombre( ) );
        assertEquals( "Lista de síntomas mal calculada", "Congestión nasal", ( ( Sintoma )sintomas.get( 5 ) ).darNombre( ) );
        assertEquals( "Lista de síntomas mal calculada", "Cefalea", ( ( Sintoma )sintomas.get( 6 ) ).darNombre( ) );
    }
    
    /**
     * Prueba 9: Método se encarga de verificar el método darEnfermedadConMasSintoma.<br>
     * <b> Objetivo: </b> Probar que le método funcione correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Escenario1: no hay síntomas.<br>
     * 2. Escenario2: la enfermedad con más síntomas es el de Gripa común.<br>
     */
    public void testDarEnfermedadConMasSintomas()
    {
    	setupEscenario1( );
    	assertNull("No debe haber síntomas.", sistemaExperto.darEnfermedadConMasSintomas());
    	setupEscenario2( );
    	assertEquals( "La enfermedad con más síntomas es ", "Gripa común", sistemaExperto.darEnfermedadConMasSintomas().darNombre() );
    }
    
    /**
     * Prueba 10: Método se encarga de verificar el método eliminarSintoma.<br>
     * <b> Objetivo: </b> Probar que le método funcione correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se elimina un síntoma que no existe debe arrojar exepción..<br>
     * 2. Se trata de eliminar un síntoma que no es hoja debe arrojar excepción.<br>
     * 3. Se elimina una hoja del árbol, al buscarlo no debe existir.<br>
     */
    public void testEliminarSintoma()
    {
    	setupEscenario2();
    	try{
    		sistemaExperto.eliminarSintoma("Síntoma que no existe");
    		fail("El síntoma no existe deberia botar exepción.");
    	}
    	catch(Exception e){
    		
    	}
    	
    	try{
    		sistemaExperto.eliminarSintoma("Tos");
    		fail("El síntoma no existe deberia botar exepción.");
    	}
    	catch(Exception e){
    		
    	}
    	
    	try{
    		sistemaExperto.eliminarSintoma("Cefalea");
    		assertNull("No debería existir ese síntoma", sistemaExperto.buscarSintoma("Cefalea"));
    		
    	}
    	catch(Exception e){
    		
    	}
    	
    }
    
    /**
     * Prueba 11: Método se encarga de verificar el método asociarEnfermedad.<br>
     * <b> Objetivo: </b> Probar que le método funcione correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se trata de asociar una enfermedad que ya existe debe arrojar excepción.<br>
     * 2. Se trata de asociar una enfermeda a un síntoma que no existe debe arrojar excepción.<br>
     * 3. Se agrega asocia la enfermedad.<br>
     */
    public void testAsociarEnfermedad()
    {
    	setupEscenario2();
    	try{
    		sistemaExperto.asociarEnfermedad("No existe", "1", 10);
    		fail("No existe el síntoma con el nombre dado.");
    	}
    	catch(Exception e){
    		
    	}
    	
    	try{
    		sistemaExperto.asociarEnfermedad("Cefalea", "Asma", 10);
    		fail("Ya existe una enfermedad con el nombre ingresado.");
    	}
    	catch(Exception e){
    		
    	}
    	
    	try{
    		sistemaExperto.asociarEnfermedad("Cefalea", "Enfermedad1", 10);
    		assertNotNull("Debe tener asociada una enfermedad",sistemaExperto.buscarSintoma("Cefalea").darEnfermedad());
    		sistemaExperto.asociarEnfermedad("Cefalea", "Enfermedad2", 10);
    		assertTrue("Se tuvo que cambiar la enfermedad",sistemaExperto.buscarSintoma("Cefalea").darEnfermedad().darNombre().equals("Enfermedad2"));
    	}
    	catch(Exception e){
    		
    	}
    }
    
    /**
     * Prueba 12: Método se encarga de verificar el método asociarFarmaco.<br>
     * <b> Objetivo: </b> Probar que le método funcione correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se trata de asociar un farmaco que ya existe debe arrojar excepción.<br>
     * 2. Se trata de asociar un farmaco a un síntoma que no existe debe arrojar excepción.<br>
     * 3. Se agrega asociar un farmaco.<br>
     */
    public void testAsociarFarmaco()
    {
    	setupEscenario2();
    	try{
    		sistemaExperto.asociarFarmaco("No existe", "1", true);
    		fail("No existe el síntoma con el nombre dado.");
    	}
    	catch(Exception e){
    		
    	}
    	
    	try{
    		sistemaExperto.asociarFarmaco("Cefalea", "Noxpirin", true);
    		fail("Ya existe una enfermedad con el nombre ingresado.");
    	}
    	catch(Exception e){
    		
    	}
    	
    	try{
    		sistemaExperto.asociarFarmaco("Cefalea", "Farmaco1", true);
    		assertNotNull("Debe tener asociada un fármaco",sistemaExperto.buscarSintoma("Cefalea").darFarmaco());
    		sistemaExperto.asociarFarmaco("Cefalea", "Farmaco2", true);
    		assertTrue("Se tuvo que cambiar la enfermedad",sistemaExperto.buscarSintoma("Cefalea").darFarmaco().darNombre().equals("Farmaco2"));
    	}
    	catch(Exception e){
    		
    	}
    }
    

}