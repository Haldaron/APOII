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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que representa un síntoma en el sistema experto. <br>
 * <b>inv: </b> <br>
 * sintomasHijos != null.<br>
 * nombreSintoma != null. <br>
 * nombreSintoma != "". <br>
 * si enfermedad != null entonces el síntoma es hoja.
 */
public class Sintoma implements Serializable
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Indicador de versión para la serialización.
     */
    private static final long serialVersionUID = 100L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Nombre del síntoma.
     */
    private String nombre;

    /**
     * Enfermedad asociada al síntoma.
     */
    private Enfermedad enfermedad;

    /**
     * Fármaco asociado al síntoma.
     */
    private Farmaco farmaco;

    /**
     * Síntomas hijos.
     */
    private List sintomasHijos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo síntoma.
     * @param pNombre Nombre del síntoma. pNombre != null && pNombre != "".
     */
    public Sintoma( String pNombre )
    {
        sintomasHijos = new ArrayList( );
        nombre = pNombre;
        enfermedad = null;
        farmaco = null;
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre del síntoma.
     * @return Nombre del síntoma.
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna la enfermedad asociada al síntoma.
     * @return Enfermedad asociada al síntoma. Null si no tiene una enfermedad asociada.
     */
    public Enfermedad darEnfermedad( )
    {
        return enfermedad;
    }

    /**
     * Retorna el fármaco asociado al síntoma.
     * @return Fármaco asociado al síntoma. Null si no tiene un fármaco asociado.
     */
    public Farmaco darFarmaco( )
    {
        return farmaco;
    }

    /**
     * Retorna la lista de síntomas hijos.
     * @return Lista de síntomas hijos.
     */
    public List darSintomasHijos( )
    {
        return sintomasHijos;
    }

    /**
     * Retorna el número de síntomas del subárbol, se esta incluyendo a si mismo.
     * @return Número total de síntomas del subárbol.
     */
    public int darTotalSintomas() 
    {
		int total = 1;
		
		for(int i =0; i< sintomasHijos.size();i++)
		{
			total+=((Sintoma)sintomasHijos.get(i)).darTotalSintomas();
		}
		return total;
	}
    
    /**
	 * Retorna la altura del síntoma del subárbol.
	 * @return Altura del síntoma del subárbol.
	 */
	public int darAltura()
	{
		//TODO Complete el método según la documentación
	}

	/**
	 * Retorna la enfermedad con más síntomas del subárbol.
	 * @return Enfermedad con más síntomas del subárbol.
	 */
	public Enfermedad darEnfermedadMasSintomas() 
	{
		Enfermedad temp = null;
		if(esHoja() && darEnfermedad()!=null){
			temp = darEnfermedad();
		}
		else{
			int max = -1;
			for(int i =0; i< sintomasHijos.size();i++)
			{
				Enfermedad actual = ((Sintoma)sintomasHijos.get(i)).darEnfermedadMasSintomas();
				if(actual!=null && ((Sintoma)sintomasHijos.get(i)).darAltura()>max ){
					max = ((Sintoma)sintomasHijos.get(i)).darAltura();
					temp = actual;
				}
			}
		}
		return temp;
	}

	/**
	 * Agrega los fármacos registrados a la lista que recibe como parámetro, en preorden, utilizando la técnica de acumulación de parámetros.
	 * @param pListaFarmacos Lista de los fármacos registrados. pListaFarmacos != null.
	 */
	public void darListaFarmacos( Collection pListaFarmacos )
	{
	    if( darFarmaco( ) != null )
	        pListaFarmacos.add( darFarmaco( ) );
	
	    Iterator it = sintomasHijos.iterator( );
	    while( it.hasNext( ) )
	    {
	        Sintoma hijo = ( Sintoma )it.next( );
	        hijo.darListaFarmacos( pListaFarmacos );
	    }
	}

	/**
	 * Agrega las enfermedades registradas a la lista que recibe como parámetro, en posorden, utilizando la técnica de acumulación de parámetros.
	 * @param pListaEnfermedades Lista de las enfermedades registadas. pListaEnfermedades != null.
	 */
	public void darListaEnfermedades( Collection pListaEnfermedades )
	{
		//TODO Complete el método según la documentación
	}

	/**
	 * Agrega los síntomas registrados a la lista que recibe como parámetro, en preorden, utilizando la técnica de acumulación de parámetros.
	 * @param pListaSintomas Lista de los sintomas registrados. pListaSintomas != null.
	 */
	public void darListaSintomas( Collection pListaSintomas )
	{
		//TODO Complete el método según la documentación
	}

	/**
	 * Agrega todas las enfermedades del subarbol de este síntoma a la lista que recibe como parámetro, utilizando la técnica de acumulación de parámetros. <br>
	 * Esto es, todas las enfermedades que tienen este síntoma dentro de su serie de síntomas asociados. <br>
	 * @param pListaEnfermedades Lista de enfermedades que tiene un síntoma en común. pListaEnfermedades!=null.
	 */
	public void darEnfermedadesSintoma( Collection pListaEnfermedades )
	{
		//TODO Complete el método según la documentación
	}

	/**
	 * Agrega todos los fármacos asociados a los síntomas de una enfermedad a la lista que recibe como parámetro, utilizando la técnica de acumulación de parámetros.
	 * @param pNombreEnfermedad Nombre de la enfermedad a tratar.
	 * @param pListaFarmacos Lista de fármacos para una enfermedad. pListaFarmacos != null.
	 * @throws SistemaExpertoException Si la enfermedad buscada no existe.
	 */
	public void darFarmacosEnfermedad( String pNombreEnfermedad, Collection pListaFarmacos ) throws SistemaExpertoException
	{
		//TODO Complete el método según la documentación
	}

	/**
	 * Busca un fármaco dado su nombre.
	 * @param pNombreFarmaco Nombre del fármaco a buscar. pNombreFarmaco != null &&  pNombreFarmaco != "".
	 * @return Farmcaco buscado. Null en caso de no encontrarlo.
	 */
	public Farmaco buscarFarmaco( String pNombreFarmaco )
	{
	    if( farmaco != null && farmaco.darNombre( ).equalsIgnoreCase( pNombreFarmaco ) )
	        return farmaco;
	    else
	    {
	        Iterator it = sintomasHijos.iterator( );
	        while( it.hasNext( ) )
	        {
	            Sintoma hijo = ( Sintoma )it.next( );
	            Farmaco temp = hijo.buscarFarmaco( pNombreFarmaco );
	            if( temp != null )
	                return temp;
	        }
	        return null;
	    }
	}

	/**
	 * Busca una enfermedad dado su nombre.
	 * @param pNombreEnfermedad Nombre de la enfermedad. pNombreEnfermedad != null && pNombreEnfermedad != "".
	 * @return Enfermedad buscada. Null en caso de no encontrarlo.
	 */
	public Enfermedad buscarEnfermedad( String pNombreEnfermedad )
	{
		//TODO Complete el método según la documentación
	}

	/**
	 * Busca un síntoma dado su nombre.
	 * @param pNombreSintoma Nombre del síntoma a buscar. pNombreSintoma != null &&  pNombreSintoma !="".
	 * @return Síntoma buscado. Null en caso de no encontrarlo.
	 */
	public Sintoma buscarSintoma( String pNombreSintoma )
	{
		//TODO Complete el método según la documentación
	}

	/**
     * Agrega un síntoma a la lista de síntomas hijos.<br>
     * <b>post: </b> En caso de no tener una enfermedad asociada, se agrega el nuevo síntoma a la lista de síntomas hijos.<br>
     * @param pSintoma Nuevo síntoma a agregar. pSintoma != null.
     * @throws SistemaExpertoException Si el síntoma tiene alguna enfermedad asociada.
     */
    public void agregarSintoma( Sintoma pSintoma ) throws SistemaExpertoException
    {
        if( enfermedad != null )
            throw new SistemaExpertoException( "El síntoma tiene una enfermedad asociada. No puede tener síntomas hijos." );
        sintomasHijos.add( pSintoma );
        verificarInvariante( );
    }

    /**
	 * Asocia al síntoma la enfermedad con los datos especificados.<br>
	 * <b>post: </b>Si el síntoma es hoja, se asocia la enfermedad ingresada al síntoma.
	 * @param pNombreEnfermedad Nombre de la enfermedad. pNombreEnfermedad != null && pNombreEnfermedad != "".
	 * @param pTasaMortalidad Tasa de mortalidad de la enfermedad. pTasaMortalidad entre 0 y 100.
	 * @throws SistemaExpertoException Si el síntoma no es hoja del árbol.
	 */
	public void asociarEnfermedad( String pNombreEnfermedad, int pTasaMortalidad ) throws SistemaExpertoException
	{
	    if( !esHoja( ) )
	        throw new SistemaExpertoException( "Sólo las hojas del árbol pueden tener enfermedades asociadas." );
	    
	    enfermedad = new Enfermedad( pNombreEnfermedad, pTasaMortalidad );
	    verificarInvariante( );
	}

	/**
	 * Asocia al síntoma el fármaco con los datos especificados.<br>
	 * <b>post: </b>El fármaco ingresado es asociado al síntoma.
	 * @param pNombreFarmaco Nombre del fármaco. pNombreFarmaco != null && pNombreFarmaco != "".
	 * @param pVentaLibre Tipo de venta del fármaco.
	 */
	public void asociarFarmaco( String pNombreFarmaco, boolean pVentaLibre )
	{
	    farmaco = new Farmaco( pNombreFarmaco, pVentaLibre );
	    verificarInvariante( );
	}

	/**
	 * Elimina un síntoma que tiene el nombre especificado.
	 * <b>post: </b> Si el síntoma es hoja, se elimina el síntoma del árbol.<br>
	 * @param pNombreSintoma Nombre del síntoma que se quiere eliminar.
	 * @throws SistemaExpertoException Si el síntoma que se quiere eliminar no es hoja.
	 */
	public void eliminarSintoma(String pNombreSintoma) throws SistemaExpertoException
	{
		//TODO Complete el método según la documentación
	}

	/**
     * Verifica si el síntoma es una hoja del árbol.
     * @return True si el síntoma es una hoja o false en caso contrario.
     */
    public boolean esHoja( )
    {
        //TODO Complete el método según la documentación
    }

    /**
     * Retorna la representación del síntoma en la interfaz gráfica.
     * @return Representación del síntoma en la interfaz gráfica.
     */
    public String toString( )
    {
        return nombre;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Método que verifica la invariante de la clase.<br>
     * sintomasHijos != null.<br>
     * nombreSintoma != null.<br>
     * nombreSintoma != "".<br>
     * si enfermedad != null entonces el síntoma es hoja.
     */
    private void verificarInvariante( )
    {
        assert ( sintomasHijos != null ) : "La lista de sintomas hijos no debería ser null.";
        assert ( nombre != null && !nombre.equals( "" ) ) : "El síntoma debe tener un nombre.";

        if( enfermedad != null && !esHoja( ) )
            assert ( false ) : "Solo las hojas del árbol pueden tener enfermedades asociadas.";
    }

    
    /**
     * Cuenta las ocurrencias del nombre de un síntoma en el síntoma y en sus síntomas hijos.
     * @param pNombreSintoma Nombre de la síntoma del que se van a contar las ocurrencias. pNombreSintoma != null && pNombreSintoma!="".
     * @return Número de ocurrencias del nombre del síntoma.
     */
	public int contarOcurrenciasNombreSintoma(String pNombreSintoma) 
	{
		int cont =0;
		if(nombre.equalsIgnoreCase(pNombreSintoma)){
			cont++;
		}
		for(int i =0; i< sintomasHijos.size();i++){
			cont+=((Sintoma)sintomasHijos.get(i)).contarOcurrenciasNombreSintoma(pNombreSintoma);
		}
		return cont;
	}

	/**
     * Cuenta las ocurrencias del nombre de un fármaco en el síntoma y en sus síntomas hijos.
     * @param pNombreFarmaco Nombre del fármaco que se van a contar las ocurrencias. pNombreFarmaco != null && pNombreFarmaco !="".
     * @return Número de ocurrencias del nombre del fármaco.
     */
	public int contarOcurrenciasNombreFarmaco(String pNombreFarmaco) 
	{
		//TODO Complete el método según la documentación
	}

	
	/**
     * Cuenta las ocurrencias del nombre de una enfermedad en el síntoma y en sus síntomas hijos.
     * @param pNombreEnfermedad Nombre de la enfermedad que se van a contar las ocurrencias. pNombreEnfermedad != null && pNombreEnfermedad!="".
     * @return Número de ocurrencias del nombre de la enfermedad.
     */
	public int contarOcurrenciasNombreEnfermedad(String pNombreEnfermedad) 
	{
		int cont =0;
		if(enfermedad!=null  && enfermedad.darNombre().equalsIgnoreCase(pNombreEnfermedad)){
			cont++;
		}
		for(int i =0; i< sintomasHijos.size();i++){
			cont+=((Sintoma)sintomasHijos.get(i)).contarOcurrenciasNombreEnfermedad(pNombreEnfermedad);
		}

		return cont;
	}

}