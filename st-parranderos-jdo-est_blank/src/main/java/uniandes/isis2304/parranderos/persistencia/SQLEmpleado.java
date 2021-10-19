package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLEmpleado {
	
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
	public SQLEmpleado (PersistenciaBancAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un USUARIO a la base de datos de VacuAndes
	 */
	public long adicionarEmpleado (PersistenceManager pm, long id, String tipo_documento, String login_usuario, String contrasenia, String tipo_empleado, 
			String nombre, String nacionalidad, String dir_fisica, int telefono, String ciudad, String departamento, int cod_postal, long dir_electronica) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaEmpleado() + "(id, tipo_documento, login_usuario, contrasenia, tipo_empleado, nombre, nacionalidad, dir_fisica, telefono, ciudad, departamento, cod_postal, dir_electronica) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, tipo_documento, login_usuario, contrasenia, tipo_empleado, nombre, nacionalidad, dir_fisica, telefono, ciudad, departamento, cod_postal, dir_electronica);
        return (long) q.executeUnique();
	}

}
