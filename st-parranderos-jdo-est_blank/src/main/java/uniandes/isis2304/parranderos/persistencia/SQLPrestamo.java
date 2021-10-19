package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLPrestamo {
	
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
	public SQLPrestamo (PersistenciaBancAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un USUARIO a la base de datos de VacuAndes
	 */
	public long adicionarPrestamo (PersistenceManager pm, long id, String tipo, int cantidad, String estado, long cliente_asignado, long gerente_asignado, long banco_asignado) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPrestamo() + "(id, tipo, cantidad, estado, cliente_asignado, gerente_asignado, banco_asignado) values (?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, tipo, cantidad, estado, cliente_asignado, gerente_asignado, banco_asignado);
        return (long) q.executeUnique();
	}
	
	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar el estado_vacunacion de un usuario en la 
	 * base de datos de VacuAndes
	 */
	public long actualizarSaldoPrestamo (PersistenceManager pm, long id_operacion_bancaria, long cliente_asignado) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaPrestamo() + " SET cantidad = cantidad+(SELECT valor FROM " + pp.darTablaOperacion_Bancaria() + " WHERE id = ?) WHERE cliente_asignado = ?");
	     q.setParameters(id_operacion_bancaria, cliente_asignado);
	     return (long) q.executeUnique();
	}
	
//	UPDATE
//	PRESTAMO
//	SET cantidad = cantidad+(SELECT valor
//	                         FROM OPERACION_BANCARIA
//	                         WHERE ID = 1)
//	WHERE cliente_asignado = 1;
	
	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar el estado_vacunacion de un usuario en la 
	 * base de datos de VacuAndes
	 */
	public long cerrarPrestamo (PersistenceManager pm, long id) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaPrestamo() + " SET estado = 'CERRADO' WHERE id = ?");
	     q.setParameters(id);
	     return (long) q.executeUnique();            
	}

}
