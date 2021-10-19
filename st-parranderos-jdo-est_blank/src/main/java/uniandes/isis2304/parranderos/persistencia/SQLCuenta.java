package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Cuenta;

public class SQLCuenta {
	
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
	public SQLCuenta (PersistenciaBancAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un USUARIO a la base de datos de VacuAndes
	 */
	public long adicionarCuenta (PersistenceManager pm, long id, String tipo, char activa, int dinero, long cliente_asignado) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCuenta() + "(id, tipo, activa, dinero, cliente_asignado) values (?, ?, ?, ?, ?)");
        q.setParameters(id, tipo, activa, dinero, cliente_asignado);
        return (long) q.executeUnique();
	}
	
	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar el estado_vacunacion de un usuario en la 
	 * base de datos de VacuAndes
	 */
	public long cerrarCuenta (PersistenceManager pm, long cliente_asignado) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaCuenta() + " SET activa = 'F', dinero = 0 WHERE cliente_asignado = ?");
	     q.setParameters(cliente_asignado);
	     return (long) q.executeUnique();            
	}
	
	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar el estado_vacunacion de un usuario en la 
	 * base de datos de VacuAndes
	 */
	public long actualizarSaldoCuenta (PersistenceManager pm, long id_operacion_bancaria, long cliente_asignado) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaCuenta() + " SET dinero = dinero+(SELECT valor FROM " + pp.darTablaOperacion_Bancaria() + " WHERE id = ?) WHERE cliente_asignado = ?");
	     q.setParameters(id_operacion_bancaria, cliente_asignado);
	     return (long) q.executeUnique();
	}
//	UPDATE
//	CUENTA
//	SET dinero = dinero+(SELECT valor
//	                   FROM OPERACION_BANCARIA
//	                   WHERE CLIENTE_ASIGNADO = 1)
//	WHERE CLIENTE_ASIGNADO = 1;
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN USUARIO de la 
	 * base de datos de VacuAndes, por su identificador
	 */
	public List<Cuenta> darCuentasPorCliente_Asignado (PersistenceManager pm, long cliente_asignado) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCuenta () + " WHERE cliente_asignado = ?");
		q.setResultClass(Cuenta.class);
		q.setParameters(cliente_asignado);
		return (List<Cuenta>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS USUARIOS de la 
	 * base de datos de VacuAndes
	 */
	public List<Cuenta> darCuentas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCuenta ());
		q.setResultClass(Cuenta.class);
		return (List<Cuenta>) q.executeList();
	}

}
