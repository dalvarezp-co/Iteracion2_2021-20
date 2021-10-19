package uniandes.isis2304.parranderos.persistencia;

import java.sql.Date;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLOperacion_Bancaria {
	
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
	public SQLOperacion_Bancaria (PersistenciaBancAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un USUARIO a la base de datos de VacuAndes
	 */
	public long adicionarOperacion_Bancaria (PersistenceManager pm, long id, String tipo, int valor, Date fecha, long cliente_asignado, long cuenta_asignada, long prestamo_asignado) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOperacion_Bancaria() + "(id, tipo, valor, fecha, cliente_asignado, cuenta_asignada, prestamo_asignado) values (?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, tipo, valor, fecha, cliente_asignado, cuenta_asignada, prestamo_asignado);
        return (long) q.executeUnique();
	}

}
