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

import java.util.List;

import junit.framework.TestCase;
import uniandes.cupi2.sistemaExperto.mundo.Enfermedad;
import uniandes.cupi2.sistemaExperto.mundo.Farmaco;
import uniandes.cupi2.sistemaExperto.mundo.Sintoma;
import uniandes.cupi2.sistemaExperto.mundo.SistemaExperto;
import uniandes.cupi2.sistemaExperto.mundo.SistemaExpertoException;

/**
 * Clase usada para verificar que los m�todos de la clase SistemaExperto est�n correctamente implementados.
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
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Escenario 1: Construye un SistemaExperto vac�o.
     */
    private void setupEscenario1( )
    {
        try
        {
            sistemaExperto = new SistemaExperto( "S�ntoma0" );
        }
        catch( SistemaExpertoException e )
        {
            fail( e.getMessage( ) );
        }
    }

    /**
     * Escenario 2: Construye un SistemeExperto a partir de un archivo de datos<br> .
     * La informaci�n de este archivo es la mostrada en las im�genes del documento de descripci�n del sistema.
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
     * Prueba 1: M�todo se encarga de verificar el m�todo constructor de la clase.<br>
     * <b> Objetivo: </b> Probar que le m�todo constructor inicialice correctamente los atributos.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. La lista de s�ntomas no es null.<br>
     * 2. El sistema tiene 7 s�ntomas. <br>
     */
    public void testConstructor( )
    {
        setupEscenario2( );
        assertNotNull( "La primera enfermedad no deber�a ser nula", sistemaExperto.darPrimerSintoma( ) );
        assertEquals( "No se inicializ� bien el sistema", 7, sistemaExperto.darListaSintomas( ).size( ) );
    }

    /**
     * Prueba 2: M�todo se encarga de verificar el m�todo agregarSintoma.<br>
     * <b> Objetivo: </b> Probar que le m�todo funcione correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se agregan los s�ntomas correctamente bajo el s�ntoma Cefalea.<br>
     * 2. Al agregar el s�ntoma Tos se genera una excepci�n 3. Al agregar un s�ntoma bajo otro inexistente se genera un excepci�n.
     */
    public void testAgregarSintoma( )
    {
        setupEscenario2( );
        Sintoma s = sistemaExperto.buscarSintoma( "Cefalea" );
        try
        {
            assertTrue( s.esHoja( ) );
            sistemaExperto.agregarSintoma( "Cefalea", "S�ntoma1" );
            assertEquals( "No se agreg� bien el s�ntoma", 8, sistemaExperto.darListaSintomas( ).size( ) );
            assertFalse( "No se agreg� bien el s�ntoma", s.esHoja( ) );
            assertEquals( "No se agreg� bien el s�ntoma", 1, s.darSintomasHijos( ).size( ) );
        }
        catch( SistemaExpertoException e )
        {
            fail( e.getMessage( ) );
        }

        try
        {
            sistemaExperto.agregarSintoma( "Cefalea", "Tos" );
            fail( "El s�ntoma Tos ya existe en el �rbol de s�ntomas" );
        }
        catch( SistemaExpertoException e )
        {
            // Comportamiento esperado
        }

        try
        {
            sistemaExperto.agregarSintoma(  "inexistente" , "S�ntoma 2" );
            fail( "El s�ntoma padre no pertenece al �rbol de s�ntomas" );
        }
        catch( SistemaExpertoException e )
        {
            // Comportamiento esperado
        }
    }

    /**
     * Prueba 3: M�todo se encarga de verificar el m�todo buscarFarmaco.<br>
     * <b> Objetivo: </b> Probar que le m�todo funcione correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. El f�rmaco far no es encontrado.<br>
     * 2. El f�rmaco Acetaminof�n es encontrado.<br>
     */
    public void testBuscarFarmaco( )
    {
        setupEscenario2( );
        assertNull( "El f�rmaco no existe", sistemaExperto.buscarFarmaco( "far" ) );
        assertNotNull( "El f�rmaco si existe", sistemaExperto.buscarFarmaco( "Acetaminofen" ) );
    }

    /**
     * Prueba 4: M�todo se encarga de verificar el m�todo darEnfermedadesAsociadasSintoma.<br>
     * <b> Objetivo: </b> Probar que le m�todo funcione correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. El s�ntoma sint no tiene enfermedades asociadas.<br>
     * 2. El s�ntoma Flemas tiene asociada 1 enfermedad.<br>
     * 3. El s�ntoma Ahogos tiene asociada 2 enfermedad.<br>
     * 4. El s�ntoma Tos tiene asociada 3 enfermedad.<br>
     */
    public void testDarEnfermedadesAsociadasSintoma( )
    {
        setupEscenario2( );
        assertEquals( "El s�ntoma sint no tiene enfermedades asociadas", 0, sistemaExperto.darEnfermedadesAsociadasSintoma( "sint" ).size( ) );
        assertEquals( "El s�ntoma flemas tiene 1 enfermedades asociadas", 1, sistemaExperto.darEnfermedadesAsociadasSintoma( "Flemas" ).size( ) );
        assertEquals( "El s�ntoma ahogos tiene 3 enfermedades asociadas", 3, sistemaExperto.darEnfermedadesAsociadasSintoma( "Ahogos" ).size( ) );
        assertEquals( "El s�ntoma tos tiene 3 enfermedades asociadas", 3, sistemaExperto.darEnfermedadesAsociadasSintoma( "Tos" ).size( ) );
    }

    /**
     * Prueba 5: M�todo se encarga de verificar el m�todo darListaEnfermedades.<br>
     * <b> Objetivo: </b> Probar que le m�todo funcione correctamente.<br>
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
        assertEquals( "Lista de enfermedades mal calculada", "Gripa com�n", ( ( Enfermedad )enfermedades.get( 2 ) ).darNombre( ) );
    }

    /**
     * Prueba 6: M�todo se encarga de verificar el m�todo darListaFarmacos.<br>
     * <b> Objetivo: </b> Probar que le m�todo funcione correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Escenario1: no hay f�rmacos.<br>
     * 2. Escenario2: hay 3 f�rmaco.<br>
     */
    public void testDarListaFarmacos( )
    {
        setupEscenario1( );
        assertEquals( "Lista de f�rmacos mal calculada", 0, sistemaExperto.darListaFarmacos( ).size( ) );
        setupEscenario2( );
        List farmacos = sistemaExperto.darListaFarmacos( );
        assertEquals( "Lista de f�rmacos mal calculada", 2, farmacos.size( ) );
        assertEquals( "Lista de f�rmacos mal calculada", "Acetaminofen", ( ( Farmaco )farmacos.get( 0 ) ).darNombre( ) );
        assertEquals( "Lista de f�rmacos mal calculada", "Noxpirin", ( ( Farmaco )farmacos.get( 1 ) ).darNombre( ) );
        
    }

    /**
     * Prueba 7: M�todo se encarga de verificar el m�todo darListaSintomas.<br>
     * <b> Objetivo: </b> Probar que le m�todo funcione correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Escenario1: no hay s�ntomas.<br>
     * 2. Escenario2: hay 7 s�ntomas.<br>
     */
    public void testDarListaSintomas( )
    {
        setupEscenario1( );
        assertEquals( "Lista de s�ntomas mal calculada", 0, sistemaExperto.darListaSintomas( ).size( ) );
        setupEscenario2( );
        List sintomas = sistemaExperto.darListaSintomas( );
        assertEquals( "Lista de s�ntomas mal calculada", 7, sintomas.size( ) );
        assertEquals( "Lista de s�ntomas mal calculada", "Tos", ( ( Sintoma )sintomas.get( 0 ) ).darNombre( ) );
        assertEquals( "Lista de s�ntomas mal calculada", "Ahogos", ( ( Sintoma )sintomas.get( 1 ) ).darNombre( ) );
        assertEquals( "Lista de s�ntomas mal calculada", "Ruido al respirar", ( ( Sintoma )sintomas.get( 2 ) ).darNombre( ) );
        assertEquals( "Lista de s�ntomas mal calculada", "Fiebre", ( ( Sintoma )sintomas.get( 3 ) ).darNombre( ) );
        assertEquals( "Lista de s�ntomas mal calculada", "Flemas", ( ( Sintoma )sintomas.get( 4 ) ).darNombre( ) );
        assertEquals( "Lista de s�ntomas mal calculada", "Congesti�n nasal", ( ( Sintoma )sintomas.get( 5 ) ).darNombre( ) );
        assertEquals( "Lista de s�ntomas mal calculada", "Cefalea", ( ( Sintoma )sintomas.get( 6 ) ).darNombre( ) );
    }
    
    /**
     * Prueba 9: M�todo se encarga de verificar el m�todo darEnfermedadConMasSintoma.<br>
     * <b> Objetivo: </b> Probar que le m�todo funcione correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Escenario1: no hay s�ntomas.<br>
     * 2. Escenario2: la enfermedad con m�s s�ntomas es el de Gripa com�n.<br>
     */
    public void testDarEnfermedadConMasSintomas()
    {
    	setupEscenario1( );
    	assertNull("No debe haber s�ntomas.", sistemaExperto.darEnfermedadConMasSintomas());
    	setupEscenario2( );
    	assertEquals( "La enfermedad con m�s s�ntomas es ", "Gripa com�n", sistemaExperto.darEnfermedadConMasSintomas().darNombre() );
    }
    
    /**
     * Prueba 10: M�todo se encarga de verificar el m�todo eliminarSintoma.<br>
     * <b> Objetivo: </b> Probar que le m�todo funcione correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se elimina un s�ntoma que no existe debe arrojar exepci�n..<br>
     * 2. Se trata de eliminar un s�ntoma que no es hoja debe arrojar excepci�n.<br>
     * 3. Se elimina una hoja del �rbol, al buscarlo no debe existir.<br>
     */
    public void testEliminarSintoma()
    {
    	setupEscenario2();
    	try{
    		sistemaExperto.eliminarSintoma("S�ntoma que no existe");
    		fail("El s�ntoma no existe deberia botar exepci�n.");
    	}
    	catch(Exception e){
    		
    	}
    	
    	try{
    		sistemaExperto.eliminarSintoma("Tos");
    		fail("El s�ntoma no existe deberia botar exepci�n.");
    	}
    	catch(Exception e){
    		
    	}
    	
    	try{
    		sistemaExperto.eliminarSintoma("Cefalea");
    		assertNull("No deber�a existir ese s�ntoma", sistemaExperto.buscarSintoma("Cefalea"));
    		
    	}
    	catch(Exception e){
    		
    	}
    	
    }
    
    /**
     * Prueba 11: M�todo se encarga de verificar el m�todo asociarEnfermedad.<br>
     * <b> Objetivo: </b> Probar que le m�todo funcione correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se trata de asociar una enfermedad que ya existe debe arrojar excepci�n.<br>
     * 2. Se trata de asociar una enfermeda a un s�ntoma que no existe debe arrojar excepci�n.<br>
     * 3. Se agrega asocia la enfermedad.<br>
     */
    public void testAsociarEnfermedad()
    {
    	setupEscenario2();
    	try{
    		sistemaExperto.asociarEnfermedad("No existe", "1", 10);
    		fail("No existe el s�ntoma con el nombre dado.");
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
     * Prueba 12: M�todo se encarga de verificar el m�todo asociarFarmaco.<br>
     * <b> Objetivo: </b> Probar que le m�todo funcione correctamente.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se trata de asociar un farmaco que ya existe debe arrojar excepci�n.<br>
     * 2. Se trata de asociar un farmaco a un s�ntoma que no existe debe arrojar excepci�n.<br>
     * 3. Se agrega asociar un farmaco.<br>
     */
    public void testAsociarFarmaco()
    {
    	setupEscenario2();
    	try{
    		sistemaExperto.asociarFarmaco("No existe", "1", true);
    		fail("No existe el s�ntoma con el nombre dado.");
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
    		assertNotNull("Debe tener asociada un f�rmaco",sistemaExperto.buscarSintoma("Cefalea").darFarmaco());
    		sistemaExperto.asociarFarmaco("Cefalea", "Farmaco2", true);
    		assertTrue("Se tuvo que cambiar la enfermedad",sistemaExperto.buscarSintoma("Cefalea").darFarmaco().darNombre().equals("Farmaco2"));
    	}
    	catch(Exception e){
    		
    	}
    }
    

}