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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que representa el sistema experto.<br>
 * <b>inv: </b> <br>
 * Nombres de las enfermedades son únicos. <br>
 * Nombres de los síntomas son únicos. <br>
 * Nombres de los fármacos son únicos.
 */
public class SistemaExperto
{
    	
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Raíz del árbol de síntomas del sistema.
     */
    private Sintoma primerSintoma;

    /**
     * Nombre del archivo para la persistencia.
     */
    private String archivoSistema;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un sistema experto.
     * @param pRutaArchivo Archivo en el que se va a guardar los síntomas del sistema. pRutaArchivo != null && pRutaArchivo != "".
     * @throws SistemaExpertoException Si hay problemas al recuperar los síntomas del archivo.
     */
    public SistemaExperto( String pRutaArchivo ) throws SistemaExpertoException
    {
        archivoSistema = pRutaArchivo;

        File archi = new File( archivoSistema );

        // Si el archivo existe: se debe recuperar de allí el estado del modelo del mundo
        if( archi.exists( ) )
        {
            try
            {
                ObjectInputStream ois = new ObjectInputStream( new FileInputStream( archivoSistema ) );
                primerSintoma = ( Sintoma )ois.readObject( );
                ois.close( );
                verificarInvariante( );
            }
            catch( Exception e )
            {
                throw new SistemaExpertoException( "Error fatal: imposible restaurar el estado del programa (" + e.getMessage( ) + ")" );
            }
        }
        else
        {
            primerSintoma = null;
            verificarInvariante( );
        }
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna la raíz del árbol de síntomas.
     * @return Primer síntoma del sistema.
     */
    public Sintoma darPrimerSintoma( )
    {
    	//TODO Complete el método según la documentación
    }
    
    /**
     * Devuelve el número total de síntomas.
     * @return Numero total de síntomas.
     */
    public int darTotalSintomas()
    {
    	int total =0;
    	if(primerSintoma!=null)
    	{
    		total = primerSintoma.darTotalSintomas();
    	}
    	return total;
    }
    
    /**
     * Retorna la enfermedad con más síntomas.
     * @return Enfermedad con más síntomas. Null en caso de no haber enfermedades registradas.
     */
    public Enfermedad darEnfermedadConMasSintomas(){
    	Enfermedad enfermedad =null;
    	if(primerSintoma!=null){
    		enfermedad = primerSintoma.darEnfermedadMasSintomas();
    	}
    	
    	return enfermedad;
    }

    /**
	 * Retorna las enfermedades del subárbol del síntoma dado por parámetro.
	 * @param pNombreSintoma Nombre del síntoma a buscar en las enfermedades del sistema. pNombreSintoma!=null && pNombreSintoma!="".
	 * @return Lista con las enfermedades que presentan el síntoma seleccionado.
	 */
	public Collection darEnfermedadesAsociadasSintoma( String pNombreSintoma )
	{
		//TODO Complete el método según la documentación
	}

	/**
	 * Retorna los fármacos asociados a los síntomas de la rama a la cual pertence la enfermedad dada por parámetro.
	 * @param pNombreEnfermedad Nombre de la enfermedad a tratar. pNombreEnfermedad!=null && pNombreEnfermedad!="".
	 * @return Lista de fármacos adecuados para tratar los síntomas de la enfermedad seleccionada.
	 * @throws SistemaExpertoException Si la enfermedad seleccionada no existe.
	 */
	public Collection darFarmacosEnfermedad( String pNombreEnfermedad ) throws SistemaExpertoException
	{
		//TODO Complete el método según la documentación
	}

	/**
	 * Retorna una lista con todos los síntomas del sistema en preorden.
	 * @return Lista con todos los síntomas del sistema.
	 */
	public List darListaSintomas( )
	{
	    List lista = new ArrayList( );
	    if( primerSintoma != null )
	    {
	        primerSintoma.darListaSintomas( lista );
	    }
	    return lista;
	}
	
	/**
	 * Retorna una lista con todos los nombres de los síntomas del sistema en preorden.
	 * @return Lista con todos los nombres de los síntoma del sistema.
	 */
	public List darNombresSintomas()
	{
		List lista = new ArrayList( );
	    List sintomas = darListaSintomas();
		for(int i = 0; i<sintomas.size();i++)
		{
			lista.add(((Sintoma)sintomas.get(i)).darNombre());
		}
		
	    return lista;
	}

	/**
	 * Retorna una lista con todas las enfermedades del sistema en posorden.
	 * @return Lista con todos las enfermedades del sistema.
	 */
	public List darListaEnfermedades( )
	{
	    List lista = new ArrayList( );
	    if( primerSintoma != null )
	    {
	        primerSintoma.darListaEnfermedades( lista );
	    }
	    return lista;
	}

	/**
	 * Retorna una lista con todos los fármacos del sistema en preorden.
	 * @return Lista con todos los fármacos del sistema.
	 */
	public List darListaFarmacos( )
	{
	    List lista = new ArrayList( );
	    if( primerSintoma != null )
	    {
	        primerSintoma.darListaFarmacos( lista );
	    }
	    return lista;
	}

	/**
	 * Busca y retorna un síntoma dado su nombre.
	 * @param pNombreSintoma Nombre del síntoma a buscar. pNombreSintoma != null && pNombreSintoma != "".
	 * @return Síntoma buscado. Null si no se encuentra.
	 */
	public Sintoma buscarSintoma( String pNombreSintoma )
	{
		//TODO Complete el método según la documentación
	}

	/**
	 * Busca y retorna el fármaco con el nombre dado.
	 * @param pNombreFarmaco Nombre del fármaco que se busca. pNombreFarmaco != null && pNombreFarmaco != "".
	 * @return Fármaco buscado. Null si no se encuentra.
	 */
	public Farmaco buscarFarmaco( String pNombreFarmaco )
	{
	    return primerSintoma == null ? null : primerSintoma.buscarFarmaco( pNombreFarmaco );
	}

	/**
	 * Agrega un síntoma al sistema.<br>
	 * <b>post: </b>El nuevo síntoma es agregado debajo del padre ingresado por parámetro.
	 * @param pNombreSintomaPadre Nombre del síntoma bajo el cual se agregará el nuevo síntoma. pNombreSintomaPadre != null && pNombreSintomaPadre != "".
	 * @param pNombreSintoma Nombre del síntoma a agregar. pNombreSintoma != null && pNombreSintoma != "".
	 * @throws SistemaExpertoException Si ya existe un síntoma con el nombre ingresado por parámetro.
	 * @throws SistemaExpertoException Si el síntoma padre es null.
	 * @throws SistemaExpertoException Si el síntoma padre tiene una enfermedad asociada.
	 */
	public void agregarSintoma( String pNombreSintomaPadre, String pNombreSintoma ) throws SistemaExpertoException
	{
		//TODO Complete el método según la documentación
	}

	/**
     * Asocia una enfermedad a un síntoma.<br>
     * <b>post: </b> El síntoma ingresado es asociado con la enfermedad ingresada.
     * @param pNombreSintoma Síntoma al que se va a asociar la enfermedad. pNombreSintoma != null && pNombreSintoma!="".
     * @param pNombreEnfermedad Nombre de la nueva enfermedad. pNombreEnfermedad != null && pNombreEnfermedad != "".
     * @param pTasaMortalidad Tasa de mortalidad de la enfermedad. pTasaMortalidad entre 0 y 100.
     * @throws SistemaExpertoException Si ya existe una enfermedad con el mismo nombre o si se trata de asociar a un síntoma no hoja.
     * @throws SistemaExpertoException Si no existe el síntoma con el nombre ingresado.
     */
    public void asociarEnfermedad( String pNombreSintoma, String pNombreEnfermedad, int pTasaMortalidad ) throws SistemaExpertoException
    {
    	//TODO Complete el método según la documentación
    }

    /**
     * Asocia un fármaco con un síntoma. <br>
     * <b>post: </b>El síntoma ingresado es asociado con el fármaco ingresado.
     * @param pNombreSintoma Síntoma que se va a asociar con el fármaco. pNombreSintoma != null && pNombreSintoma != "".
     * @param pNombreFarmaco Nombre del nuevo fármaco. pNombreFarmaco != null && pNombreFarmaco != "".
     * @param pVentaLibre Tipo de venta del fármaco. True si es Libre, false de lo contrario.
     * @throws SistemaExpertoException Si ya existe un fármaco con el mismo nombre.
     * @throws SistemaExpertoException Si no existe el síntoma con el nombre ingresado.
     */
    public void asociarFarmaco( String pNombreSintoma, String pNombreFarmaco, boolean pVentaLibre ) throws SistemaExpertoException
    {
        Farmaco temp = buscarFarmaco( pNombreFarmaco );
        Sintoma sintoma = buscarSintoma(pNombreSintoma);
        if( temp != null )
            throw new SistemaExpertoException( "El fármaco ya existe." );
        if(sintoma==null)
        	throw new SistemaExpertoException( "El síntoma no existe." );
        sintoma.asociarFarmaco( pNombreFarmaco, pVentaLibre );
        verificarInvariante( );
    }

    /**
     * Elimina un síntoma que tiene el nombre especificado.
     * @param pNombreSintoma Nombre del síntoma que se quiere eliminar. pNombreSintoma!=null && pNombreSintoma!="".
     * @throws SistemaExpertoException Si no existe un síntoma con ese nombre.
     * @throws SistemaExpertoException Si el síntoma que se quiere eliminar no es hoja.
     */
    public void eliminarSintoma(String pNombreSintoma) throws SistemaExpertoException
    {
		
    	//TODO Complete el método según la documentación
		
	}

    /**
     * Genera el reporte con la información de los síntomas, los fármacos y las enfermedades en la aplicación.
     * @param pRutaArchivo Ruta donde se va a guardar el reporte.
     * @param pNombreArchivo Nombre del archivo donde se va a guadar el reporte.
     * @throws IOException Si existe un problema a escribir el archivo.
     */
    public void generarReporte(String pRutaArchivo, String pNombreArchivo) throws IOException
    {
    	//TODO Complete el método según la documentación
        
    }

    // -----------------------------------------------------------------
    // Métodos auxiliares
    // -----------------------------------------------------------------

    /**
     * Busca y retorna la enfermedad con el nombre dado.
     * @param pNombreEnfermedad Nombre de la enfermedad que se busca. pNombreEnfermedad != null && pNombreEnfermedad != "".
     * @return Enfermedad buscada o null si no se encuentra.
     */
    private Enfermedad buscarEnfermedad( String pNombreEnfermedad )
    {
        return primerSintoma == null ? null : primerSintoma.buscarEnfermedad( pNombreEnfermedad );
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Método que verifica el invariante de la clase.<br>
     * <b>inv: </b> <br>
     * Nombres de las enfermedades son únicos. <br>
     * Nombres de los síntomas son únicos. <br>
     * Nombres de los fármacos son únicos.
     */
    private void verificarInvariante( )
    {
        Collection preguntas = darListaSintomas( );

        Iterator it = preguntas.iterator( );
        while( it.hasNext( ) )
        {
            Sintoma sintoma = ( Sintoma )it.next( );
            assert ( contarOcurrenciasNombreSintoma( sintoma.darNombre( ) ) == 1 ) : "El nombre del síntoma debería ser único.";

            Enfermedad e = sintoma.darEnfermedad( );
            if( e != null )
                assert ( contarOcurrenciasNombreEnfermedad( e.darNombre( ) ) == 1 ) : "El nombre de la enfermedad debería ser único.";

            Farmaco f = sintoma.darFarmaco( );
            if( f != null )
                assert ( contarOcurrenciasNombreFarmaco( f.darNombre( ) ) == 1 ) : "El nombre del fármaco debería ser único.";
        }
    }

    /**
     * Cuenta las ocurrencias del nombre de una enfermedad en el sistema.
     * @param pNombreEnfermedad Nombre de la enfermedad que se van a contar las ocurrencias. pNombreEnfermedad != null && pNombreEnfermedad!="".
     * @return Número de ocurrencias del nombre de la enfermedad.
     */
    private int contarOcurrenciasNombreEnfermedad( String pNombreEnfermedad )
    {
        int ocurrencias = 0;

        if(primerSintoma!=null){
        	ocurrencias += primerSintoma.contarOcurrenciasNombreEnfermedad(pNombreEnfermedad);
        }

        return ocurrencias;
    }

    /**
     * Cuenta las ocurrencias del nombre de un fármaco en el sistema.
     * @param pNombreFarmaco Nombre del fármaco que se van a contar las ocurrencias. pNombreFarmaco != null && pNombreFarmaco!="".
     * @return Número de ocurrencias del nombre del fármaco.
     */
    private int contarOcurrenciasNombreFarmaco( String pNombreFarmaco )
    {
    	//TODO Complete el método según la documentación
    }

    /**
     * Cuenta las ocurrencias del nombre de un síntoma en el sistema.
     * @param pNombreSintoma Nombre de la síntoma del que se van a contar las ocurrencias. pNombreSintoma != null && pNombreSintoma!="".
     * @return Número de ocurrencias del nombre del síntoma.
     */
    private int contarOcurrenciasNombreSintoma( String pNombreSintoma )
    {
        int ocurrencias = 0;

        if(primerSintoma!=null){
        	ocurrencias += primerSintoma.contarOcurrenciasNombreSintoma(pNombreSintoma);
        }

        return ocurrencias;
    }

    // -----------------------------------------------------------------
    // Persistencia
    // -----------------------------------------------------------------

    /**
     * Guarda el estado actual en el que se encuentra el sistema.
     * @throws SistemaExpertoException Si hay problemas al tratar de guardar los síntomas del sistema.
     */
    public void guardar( ) throws SistemaExpertoException
    {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( archivoSistema ) );
            oos.writeObject( primerSintoma );
            oos.close( );
        }
        catch( IOException e )
        {
            throw new SistemaExpertoException( "Problemas con la persistencia." );
        }
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1.
     * @return respuesta1.
     */
    public String metodo1( )
    {
        return "Respuesta 1.";
    }

    /**
     * Método para la extensión2.
     * @return respuesta2.
     */
    public String metodo2( )
    {
        return "Respuesta 2.";
    }


}