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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que representa un s�ntoma en el sistema experto. <br>
 * <b>inv: </b> <br>
 * sintomasHijos != null.<br>
 * nombreSintoma != null. <br>
 * nombreSintoma != "". <br>
 * si enfermedad != null entonces el s�ntoma es hoja.
 */
public class Sintoma implements Serializable
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Indicador de versi�n para la serializaci�n.
     */
    private static final long serialVersionUID = 100L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Nombre del s�ntoma.
     */
    private String nombre;

    /**
     * Enfermedad asociada al s�ntoma.
     */
    private Enfermedad enfermedad;

    /**
     * F�rmaco asociado al s�ntoma.
     */
    private Farmaco farmaco;

    /**
     * S�ntomas hijos.
     */
    private List sintomasHijos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo s�ntoma.
     * @param pNombre Nombre del s�ntoma. pNombre != null && pNombre != "".
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
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre del s�ntoma.
     * @return Nombre del s�ntoma.
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna la enfermedad asociada al s�ntoma.
     * @return Enfermedad asociada al s�ntoma. Null si no tiene una enfermedad asociada.
     */
    public Enfermedad darEnfermedad( )
    {
        return enfermedad;
    }

    /**
     * Retorna el f�rmaco asociado al s�ntoma.
     * @return F�rmaco asociado al s�ntoma. Null si no tiene un f�rmaco asociado.
     */
    public Farmaco darFarmaco( )
    {
        return farmaco;
    }

    /**
     * Retorna la lista de s�ntomas hijos.
     * @return Lista de s�ntomas hijos.
     */
    public List darSintomasHijos( )
    {
        return sintomasHijos;
    }

    /**
     * Retorna el n�mero de s�ntomas del sub�rbol, se esta incluyendo a si mismo.
     * @return N�mero total de s�ntomas del sub�rbol.
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
	 * Retorna la altura del s�ntoma del sub�rbol.
	 * @return Altura del s�ntoma del sub�rbol.
	 */
	public int darAltura()
	{
		//TODO Complete el m�todo seg�n la documentaci�n
	}

	/**
	 * Retorna la enfermedad con m�s s�ntomas del sub�rbol.
	 * @return Enfermedad con m�s s�ntomas del sub�rbol.
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
	 * Agrega los f�rmacos registrados a la lista que recibe como par�metro, en preorden, utilizando la t�cnica de acumulaci�n de par�metros.
	 * @param pListaFarmacos Lista de los f�rmacos registrados. pListaFarmacos != null.
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
	 * Agrega las enfermedades registradas a la lista que recibe como par�metro, en posorden, utilizando la t�cnica de acumulaci�n de par�metros.
	 * @param pListaEnfermedades Lista de las enfermedades registadas. pListaEnfermedades != null.
	 */
	public void darListaEnfermedades( Collection pListaEnfermedades )
	{
		//TODO Complete el m�todo seg�n la documentaci�n
	}

	/**
	 * Agrega los s�ntomas registrados a la lista que recibe como par�metro, en preorden, utilizando la t�cnica de acumulaci�n de par�metros.
	 * @param pListaSintomas Lista de los sintomas registrados. pListaSintomas != null.
	 */
	public void darListaSintomas( Collection pListaSintomas )
	{
		//TODO Complete el m�todo seg�n la documentaci�n
	}

	/**
	 * Agrega todas las enfermedades del subarbol de este s�ntoma a la lista que recibe como par�metro, utilizando la t�cnica de acumulaci�n de par�metros. <br>
	 * Esto es, todas las enfermedades que tienen este s�ntoma dentro de su serie de s�ntomas asociados. <br>
	 * @param pListaEnfermedades Lista de enfermedades que tiene un s�ntoma en com�n. pListaEnfermedades!=null.
	 */
	public void darEnfermedadesSintoma( Collection pListaEnfermedades )
	{
		//TODO Complete el m�todo seg�n la documentaci�n
	}

	/**
	 * Agrega todos los f�rmacos asociados a los s�ntomas de una enfermedad a la lista que recibe como par�metro, utilizando la t�cnica de acumulaci�n de par�metros.
	 * @param pNombreEnfermedad Nombre de la enfermedad a tratar.
	 * @param pListaFarmacos Lista de f�rmacos para una enfermedad. pListaFarmacos != null.
	 * @throws SistemaExpertoException Si la enfermedad buscada no existe.
	 */
	public void darFarmacosEnfermedad( String pNombreEnfermedad, Collection pListaFarmacos ) throws SistemaExpertoException
	{
		//TODO Complete el m�todo seg�n la documentaci�n
	}

	/**
	 * Busca un f�rmaco dado su nombre.
	 * @param pNombreFarmaco Nombre del f�rmaco a buscar. pNombreFarmaco != null &&  pNombreFarmaco != "".
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
		//TODO Complete el m�todo seg�n la documentaci�n
	}

	/**
	 * Busca un s�ntoma dado su nombre.
	 * @param pNombreSintoma Nombre del s�ntoma a buscar. pNombreSintoma != null &&  pNombreSintoma !="".
	 * @return S�ntoma buscado. Null en caso de no encontrarlo.
	 */
	public Sintoma buscarSintoma( String pNombreSintoma )
	{
		//TODO Complete el m�todo seg�n la documentaci�n
	}

	/**
     * Agrega un s�ntoma a la lista de s�ntomas hijos.<br>
     * <b>post: </b> En caso de no tener una enfermedad asociada, se agrega el nuevo s�ntoma a la lista de s�ntomas hijos.<br>
     * @param pSintoma Nuevo s�ntoma a agregar. pSintoma != null.
     * @throws SistemaExpertoException Si el s�ntoma tiene alguna enfermedad asociada.
     */
    public void agregarSintoma( Sintoma pSintoma ) throws SistemaExpertoException
    {
        if( enfermedad != null )
            throw new SistemaExpertoException( "El s�ntoma tiene una enfermedad asociada. No puede tener s�ntomas hijos." );
        sintomasHijos.add( pSintoma );
        verificarInvariante( );
    }

    /**
	 * Asocia al s�ntoma la enfermedad con los datos especificados.<br>
	 * <b>post: </b>Si el s�ntoma es hoja, se asocia la enfermedad ingresada al s�ntoma.
	 * @param pNombreEnfermedad Nombre de la enfermedad. pNombreEnfermedad != null && pNombreEnfermedad != "".
	 * @param pTasaMortalidad Tasa de mortalidad de la enfermedad. pTasaMortalidad entre 0 y 100.
	 * @throws SistemaExpertoException Si el s�ntoma no es hoja del �rbol.
	 */
	public void asociarEnfermedad( String pNombreEnfermedad, int pTasaMortalidad ) throws SistemaExpertoException
	{
	    if( !esHoja( ) )
	        throw new SistemaExpertoException( "S�lo las hojas del �rbol pueden tener enfermedades asociadas." );
	    
	    enfermedad = new Enfermedad( pNombreEnfermedad, pTasaMortalidad );
	    verificarInvariante( );
	}

	/**
	 * Asocia al s�ntoma el f�rmaco con los datos especificados.<br>
	 * <b>post: </b>El f�rmaco ingresado es asociado al s�ntoma.
	 * @param pNombreFarmaco Nombre del f�rmaco. pNombreFarmaco != null && pNombreFarmaco != "".
	 * @param pVentaLibre Tipo de venta del f�rmaco.
	 */
	public void asociarFarmaco( String pNombreFarmaco, boolean pVentaLibre )
	{
	    farmaco = new Farmaco( pNombreFarmaco, pVentaLibre );
	    verificarInvariante( );
	}

	/**
	 * Elimina un s�ntoma que tiene el nombre especificado.
	 * <b>post: </b> Si el s�ntoma es hoja, se elimina el s�ntoma del �rbol.<br>
	 * @param pNombreSintoma Nombre del s�ntoma que se quiere eliminar.
	 * @throws SistemaExpertoException Si el s�ntoma que se quiere eliminar no es hoja.
	 */
	public void eliminarSintoma(String pNombreSintoma) throws SistemaExpertoException
	{
		//TODO Complete el m�todo seg�n la documentaci�n
	}

	/**
     * Verifica si el s�ntoma es una hoja del �rbol.
     * @return True si el s�ntoma es una hoja o false en caso contrario.
     */
    public boolean esHoja( )
    {
        //TODO Complete el m�todo seg�n la documentaci�n
    }

    /**
     * Retorna la representaci�n del s�ntoma en la interfaz gr�fica.
     * @return Representaci�n del s�ntoma en la interfaz gr�fica.
     */
    public String toString( )
    {
        return nombre;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * M�todo que verifica la invariante de la clase.<br>
     * sintomasHijos != null.<br>
     * nombreSintoma != null.<br>
     * nombreSintoma != "".<br>
     * si enfermedad != null entonces el s�ntoma es hoja.
     */
    private void verificarInvariante( )
    {
        assert ( sintomasHijos != null ) : "La lista de sintomas hijos no deber�a ser null.";
        assert ( nombre != null && !nombre.equals( "" ) ) : "El s�ntoma debe tener un nombre.";

        if( enfermedad != null && !esHoja( ) )
            assert ( false ) : "Solo las hojas del �rbol pueden tener enfermedades asociadas.";
    }

    
    /**
     * Cuenta las ocurrencias del nombre de un s�ntoma en el s�ntoma y en sus s�ntomas hijos.
     * @param pNombreSintoma Nombre de la s�ntoma del que se van a contar las ocurrencias. pNombreSintoma != null && pNombreSintoma!="".
     * @return N�mero de ocurrencias del nombre del s�ntoma.
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
     * Cuenta las ocurrencias del nombre de un f�rmaco en el s�ntoma y en sus s�ntomas hijos.
     * @param pNombreFarmaco Nombre del f�rmaco que se van a contar las ocurrencias. pNombreFarmaco != null && pNombreFarmaco !="".
     * @return N�mero de ocurrencias del nombre del f�rmaco.
     */
	public int contarOcurrenciasNombreFarmaco(String pNombreFarmaco) 
	{
		//TODO Complete el m�todo seg�n la documentaci�n
	}

	
	/**
     * Cuenta las ocurrencias del nombre de una enfermedad en el s�ntoma y en sus s�ntomas hijos.
     * @param pNombreEnfermedad Nombre de la enfermedad que se van a contar las ocurrencias. pNombreEnfermedad != null && pNombreEnfermedad!="".
     * @return N�mero de ocurrencias del nombre de la enfermedad.
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