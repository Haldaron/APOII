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
 * Nombres de las enfermedades son �nicos. <br>
 * Nombres de los s�ntomas son �nicos. <br>
 * Nombres de los f�rmacos son �nicos.
 */
public class SistemaExperto
{
    	
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ra�z del �rbol de s�ntomas del sistema.
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
     * @param pRutaArchivo Archivo en el que se va a guardar los s�ntomas del sistema. pRutaArchivo != null && pRutaArchivo != "".
     * @throws SistemaExpertoException Si hay problemas al recuperar los s�ntomas del archivo.
     */
    public SistemaExperto( String pRutaArchivo ) throws SistemaExpertoException
    {
        archivoSistema = pRutaArchivo;

        File archi = new File( archivoSistema );

        // Si el archivo existe: se debe recuperar de all� el estado del modelo del mundo
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
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna la ra�z del �rbol de s�ntomas.
     * @return Primer s�ntoma del sistema.
     */
    public Sintoma darPrimerSintoma( )
    {
    	//TODO Complete el m�todo seg�n la documentaci�n
    }
    
    /**
     * Devuelve el n�mero total de s�ntomas.
     * @return Numero total de s�ntomas.
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
     * Retorna la enfermedad con m�s s�ntomas.
     * @return Enfermedad con m�s s�ntomas. Null en caso de no haber enfermedades registradas.
     */
    public Enfermedad darEnfermedadConMasSintomas(){
    	Enfermedad enfermedad =null;
    	if(primerSintoma!=null){
    		enfermedad = primerSintoma.darEnfermedadMasSintomas();
    	}
    	
    	return enfermedad;
    }

    /**
	 * Retorna las enfermedades del sub�rbol del s�ntoma dado por par�metro.
	 * @param pNombreSintoma Nombre del s�ntoma a buscar en las enfermedades del sistema. pNombreSintoma!=null && pNombreSintoma!="".
	 * @return Lista con las enfermedades que presentan el s�ntoma seleccionado.
	 */
	public Collection darEnfermedadesAsociadasSintoma( String pNombreSintoma )
	{
		//TODO Complete el m�todo seg�n la documentaci�n
	}

	/**
	 * Retorna los f�rmacos asociados a los s�ntomas de la rama a la cual pertence la enfermedad dada por par�metro.
	 * @param pNombreEnfermedad Nombre de la enfermedad a tratar. pNombreEnfermedad!=null && pNombreEnfermedad!="".
	 * @return Lista de f�rmacos adecuados para tratar los s�ntomas de la enfermedad seleccionada.
	 * @throws SistemaExpertoException Si la enfermedad seleccionada no existe.
	 */
	public Collection darFarmacosEnfermedad( String pNombreEnfermedad ) throws SistemaExpertoException
	{
		//TODO Complete el m�todo seg�n la documentaci�n
	}

	/**
	 * Retorna una lista con todos los s�ntomas del sistema en preorden.
	 * @return Lista con todos los s�ntomas del sistema.
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
	 * Retorna una lista con todos los nombres de los s�ntomas del sistema en preorden.
	 * @return Lista con todos los nombres de los s�ntoma del sistema.
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
	 * Retorna una lista con todos los f�rmacos del sistema en preorden.
	 * @return Lista con todos los f�rmacos del sistema.
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
	 * Busca y retorna un s�ntoma dado su nombre.
	 * @param pNombreSintoma Nombre del s�ntoma a buscar. pNombreSintoma != null && pNombreSintoma != "".
	 * @return S�ntoma buscado. Null si no se encuentra.
	 */
	public Sintoma buscarSintoma( String pNombreSintoma )
	{
		//TODO Complete el m�todo seg�n la documentaci�n
	}

	/**
	 * Busca y retorna el f�rmaco con el nombre dado.
	 * @param pNombreFarmaco Nombre del f�rmaco que se busca. pNombreFarmaco != null && pNombreFarmaco != "".
	 * @return F�rmaco buscado. Null si no se encuentra.
	 */
	public Farmaco buscarFarmaco( String pNombreFarmaco )
	{
	    return primerSintoma == null ? null : primerSintoma.buscarFarmaco( pNombreFarmaco );
	}

	/**
	 * Agrega un s�ntoma al sistema.<br>
	 * <b>post: </b>El nuevo s�ntoma es agregado debajo del padre ingresado por par�metro.
	 * @param pNombreSintomaPadre Nombre del s�ntoma bajo el cual se agregar� el nuevo s�ntoma. pNombreSintomaPadre != null && pNombreSintomaPadre != "".
	 * @param pNombreSintoma Nombre del s�ntoma a agregar. pNombreSintoma != null && pNombreSintoma != "".
	 * @throws SistemaExpertoException Si ya existe un s�ntoma con el nombre ingresado por par�metro.
	 * @throws SistemaExpertoException Si el s�ntoma padre es null.
	 * @throws SistemaExpertoException Si el s�ntoma padre tiene una enfermedad asociada.
	 */
	public void agregarSintoma( String pNombreSintomaPadre, String pNombreSintoma ) throws SistemaExpertoException
	{
		//TODO Complete el m�todo seg�n la documentaci�n
	}

	/**
     * Asocia una enfermedad a un s�ntoma.<br>
     * <b>post: </b> El s�ntoma ingresado es asociado con la enfermedad ingresada.
     * @param pNombreSintoma S�ntoma al que se va a asociar la enfermedad. pNombreSintoma != null && pNombreSintoma!="".
     * @param pNombreEnfermedad Nombre de la nueva enfermedad. pNombreEnfermedad != null && pNombreEnfermedad != "".
     * @param pTasaMortalidad Tasa de mortalidad de la enfermedad. pTasaMortalidad entre 0 y 100.
     * @throws SistemaExpertoException Si ya existe una enfermedad con el mismo nombre o si se trata de asociar a un s�ntoma no hoja.
     * @throws SistemaExpertoException Si no existe el s�ntoma con el nombre ingresado.
     */
    public void asociarEnfermedad( String pNombreSintoma, String pNombreEnfermedad, int pTasaMortalidad ) throws SistemaExpertoException
    {
    	//TODO Complete el m�todo seg�n la documentaci�n
    }

    /**
     * Asocia un f�rmaco con un s�ntoma. <br>
     * <b>post: </b>El s�ntoma ingresado es asociado con el f�rmaco ingresado.
     * @param pNombreSintoma S�ntoma que se va a asociar con el f�rmaco. pNombreSintoma != null && pNombreSintoma != "".
     * @param pNombreFarmaco Nombre del nuevo f�rmaco. pNombreFarmaco != null && pNombreFarmaco != "".
     * @param pVentaLibre Tipo de venta del f�rmaco. True si es Libre, false de lo contrario.
     * @throws SistemaExpertoException Si ya existe un f�rmaco con el mismo nombre.
     * @throws SistemaExpertoException Si no existe el s�ntoma con el nombre ingresado.
     */
    public void asociarFarmaco( String pNombreSintoma, String pNombreFarmaco, boolean pVentaLibre ) throws SistemaExpertoException
    {
        Farmaco temp = buscarFarmaco( pNombreFarmaco );
        Sintoma sintoma = buscarSintoma(pNombreSintoma);
        if( temp != null )
            throw new SistemaExpertoException( "El f�rmaco ya existe." );
        if(sintoma==null)
        	throw new SistemaExpertoException( "El s�ntoma no existe." );
        sintoma.asociarFarmaco( pNombreFarmaco, pVentaLibre );
        verificarInvariante( );
    }

    /**
     * Elimina un s�ntoma que tiene el nombre especificado.
     * @param pNombreSintoma Nombre del s�ntoma que se quiere eliminar. pNombreSintoma!=null && pNombreSintoma!="".
     * @throws SistemaExpertoException Si no existe un s�ntoma con ese nombre.
     * @throws SistemaExpertoException Si el s�ntoma que se quiere eliminar no es hoja.
     */
    public void eliminarSintoma(String pNombreSintoma) throws SistemaExpertoException
    {
		
    	//TODO Complete el m�todo seg�n la documentaci�n
		
	}

    /**
     * Genera el reporte con la informaci�n de los s�ntomas, los f�rmacos y las enfermedades en la aplicaci�n.
     * @param pRutaArchivo Ruta donde se va a guardar el reporte.
     * @param pNombreArchivo Nombre del archivo donde se va a guadar el reporte.
     * @throws IOException Si existe un problema a escribir el archivo.
     */
    public void generarReporte(String pRutaArchivo, String pNombreArchivo) throws IOException
    {
    	//TODO Complete el m�todo seg�n la documentaci�n
        
    }

    // -----------------------------------------------------------------
    // M�todos auxiliares
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
     * M�todo que verifica el invariante de la clase.<br>
     * <b>inv: </b> <br>
     * Nombres de las enfermedades son �nicos. <br>
     * Nombres de los s�ntomas son �nicos. <br>
     * Nombres de los f�rmacos son �nicos.
     */
    private void verificarInvariante( )
    {
        Collection preguntas = darListaSintomas( );

        Iterator it = preguntas.iterator( );
        while( it.hasNext( ) )
        {
            Sintoma sintoma = ( Sintoma )it.next( );
            assert ( contarOcurrenciasNombreSintoma( sintoma.darNombre( ) ) == 1 ) : "El nombre del s�ntoma deber�a ser �nico.";

            Enfermedad e = sintoma.darEnfermedad( );
            if( e != null )
                assert ( contarOcurrenciasNombreEnfermedad( e.darNombre( ) ) == 1 ) : "El nombre de la enfermedad deber�a ser �nico.";

            Farmaco f = sintoma.darFarmaco( );
            if( f != null )
                assert ( contarOcurrenciasNombreFarmaco( f.darNombre( ) ) == 1 ) : "El nombre del f�rmaco deber�a ser �nico.";
        }
    }

    /**
     * Cuenta las ocurrencias del nombre de una enfermedad en el sistema.
     * @param pNombreEnfermedad Nombre de la enfermedad que se van a contar las ocurrencias. pNombreEnfermedad != null && pNombreEnfermedad!="".
     * @return N�mero de ocurrencias del nombre de la enfermedad.
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
     * Cuenta las ocurrencias del nombre de un f�rmaco en el sistema.
     * @param pNombreFarmaco Nombre del f�rmaco que se van a contar las ocurrencias. pNombreFarmaco != null && pNombreFarmaco!="".
     * @return N�mero de ocurrencias del nombre del f�rmaco.
     */
    private int contarOcurrenciasNombreFarmaco( String pNombreFarmaco )
    {
    	//TODO Complete el m�todo seg�n la documentaci�n
    }

    /**
     * Cuenta las ocurrencias del nombre de un s�ntoma en el sistema.
     * @param pNombreSintoma Nombre de la s�ntoma del que se van a contar las ocurrencias. pNombreSintoma != null && pNombreSintoma!="".
     * @return N�mero de ocurrencias del nombre del s�ntoma.
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
     * @throws SistemaExpertoException Si hay problemas al tratar de guardar los s�ntomas del sistema.
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
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1.
     * @return respuesta1.
     */
    public String metodo1( )
    {
        return "Respuesta 1.";
    }

    /**
     * M�todo para la extensi�n2.
     * @return respuesta2.
     */
    public String metodo2( )
    {
        return "Respuesta 2.";
    }


}