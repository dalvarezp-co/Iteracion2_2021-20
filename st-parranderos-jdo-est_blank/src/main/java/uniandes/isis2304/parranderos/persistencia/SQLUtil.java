/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLUtil
{
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
	public SQLUtil (PersistenciaBancAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqBancAndes () + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}

	/**
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @param pm - El manejador de persistencia
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarBancAndes (PersistenceManager pm)
	{
        Query qDireccion_Electronica = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaDireccion_Electronica ());          
        Query qCliente = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente ());
        Query qEmpleado = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEmpleado ());
        Query qCuenta = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCuenta ());
        Query qBanco = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBanco ());
        Query qPrestamo = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPrestamo ());
        Query qOperacion_Bancaria = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOperacion_Bancaria ());
        Query qOficina = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOficina ());
        Query qPuesto_De_Atencion = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPuesto_De_Atencion ());
        Query qDeposito_Inversion = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaDeposito_Inversion ());

        long direccion_ElectronicaEliminadas = (long) qDireccion_Electronica.executeUnique ();
        long clienteEliminados = (long) qCliente.executeUnique ();
        long empleadoEliminados = (long) qEmpleado.executeUnique ();
        long cuentaEliminadas = (long) qCuenta.executeUnique ();
        long bancoEliminados = (long) qBanco.executeUnique ();
        long prestamoEliminados = (long) qPrestamo.executeUnique ();
        long operacion_BancariaEliminadas = (long) qOperacion_Bancaria.executeUnique ();
        long oficinaEliminadas = (long) qOficina.executeUnique ();
        long puesto_De_AtencionEliminados = (long) qPuesto_De_Atencion.executeUnique ();
        long deposito_InversionEliminados = (long) qDeposito_Inversion.executeUnique ();
        return new long[] {direccion_ElectronicaEliminadas, clienteEliminados, empleadoEliminados, cuentaEliminadas, 
        		bancoEliminados, prestamoEliminados, operacion_BancariaEliminadas, oficinaEliminadas,
        		puesto_De_AtencionEliminados, deposito_InversionEliminados};
	}

}
