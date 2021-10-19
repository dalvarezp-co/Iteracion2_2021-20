package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLPuesto_De_Atencion {
	
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaBancAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaBancAndes pp;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLPuesto_De_Atencion (PersistenciaBancAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un USUARIO a la base de datos de VacuAndes
	 */
	public long adicionarPuesto_De_Atencion (PersistenceManager pm, long id, String tipo, String localizacion, char es_fisico, long oficina, long banco_asignado) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPuesto_De_Atencion() + "(id, tipo, localizacion, es_fisico, oficina, banco_asignado) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(id, tipo, localizacion, es_fisico, oficina, banco_asignado);
        return (long) q.executeUnique();
	}

}
