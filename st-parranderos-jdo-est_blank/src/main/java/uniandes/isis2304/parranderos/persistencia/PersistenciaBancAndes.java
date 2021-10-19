package uniandes.isis2304.parranderos.persistencia;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.parranderos.negocio.Cliente;
import uniandes.isis2304.parranderos.negocio.Cuenta;
import uniandes.isis2304.parranderos.negocio.Empleado;
import uniandes.isis2304.parranderos.negocio.Oficina;
import uniandes.isis2304.parranderos.negocio.Operacion_Bancaria;
import uniandes.isis2304.parranderos.negocio.Prestamo;
import uniandes.isis2304.parranderos.negocio.Puesto_De_Atencion;

public class PersistenciaBancAndes {

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaBancAndes.class.getName());

	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaBancAndes instance;

	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;

	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * Secuenciador, tipoBebida, bebida, bar, bebedor, gustan, sirven y visitan
	 */
	private List <String> tablas;

	/**
	 * Atributo para el acceso a las sentencias SQL propias a PersistenciaParranderos
	 */
	private SQLUtil sqlUtil;

	/**
	 * Atributo para el acceso a la tabla TIPOBEBIDA de la base de datos
	 */
	private SQLDireccion_Electronica sqlDireccion_Electronica;

	/**
	 * Atributo para el acceso a la tabla BEBIDA de la base de datos
	 */
	private SQLCliente sqlCliente;

	/**
	 * Atributo para el acceso a la tabla BEBIDA de la base de datos
	 */
	private SQLEmpleado sqlEmpleado;

	/**
	 * Atributo para el acceso a la tabla BAR de la base de datos
	 */
	private SQLCuenta sqlCuenta;

	/**
	 * Atributo para el acceso a la tabla BAR de la base de datos
	 */
	private SQLBanco sqlBanco;

	/**
	 * Atributo para el acceso a la tabla BAR de la base de datos
	 */
	private SQLPrestamo sqlPrestamo;

	/**
	 * Atributo para el acceso a la tabla BAR de la base de datos
	 */
	private SQLOperacion_Bancaria sqlOperacion_Bancaria;

	/**
	 * Atributo para el acceso a la tabla BAR de la base de datos
	 */
	private SQLOficina sqlOficina;
	
	/**
	 * Atributo para el acceso a la tabla BAR de la base de datos
	 */
	private SQLPuesto_De_Atencion sqlPuesto_De_Atencion;

	/**
	 * Atributo para el acceso a la tabla BAR de la base de datos
	 */
	private SQLDeposito_Inversion sqlDeposito_Inversion;

	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaBancAndes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("BancAndes");		
		crearClasesSQL ();

		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("bancandes_sequence");
		tablas.add ("DIRECCION_ELECTRONICA");
		tablas.add ("CLIENTE");
		tablas.add ("EMPLEADO");
		tablas.add ("CUENTA");
		tablas.add ("BANCO");
		tablas.add ("PRESTAMO");
		tablas.add ("OPERACION_BANCARIA");
		tablas.add ("OFICINA");
		tablas.add ("PUESTO_DE_ATENCION");
		tablas.add ("DEPOSITO_INVERSION");
	}
	
	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaBancAndes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}
	
	/**
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaBancAndes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaBancAndes ();
		}
		return instance;
	}
	
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaBancAndes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaBancAndes (tableConfig);
		}
		return instance;
	}
	
	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	
	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	
	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		sqlDireccion_Electronica = new SQLDireccion_Electronica(this);
		sqlCliente = new SQLCliente(this);
		sqlEmpleado = new SQLEmpleado(this);
		sqlCuenta = new SQLCuenta(this);
		sqlBanco = new SQLBanco(this);
		sqlPrestamo = new SQLPrestamo(this);
		sqlOperacion_Bancaria = new SQLOperacion_Bancaria(this);
		sqlOficina = new SQLOficina(this);
		sqlPuesto_De_Atencion = new SQLPuesto_De_Atencion(this);
		sqlDeposito_Inversion = new SQLDeposito_Inversion(this);
		sqlUtil = new SQLUtil(this);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre del secuenciador de parranderos
	 */
	public String darSeqBancAndes ()
	{
		return tablas.get (0);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de TipoBebida de parranderos
	 */
	public String darTablaDireccion_Electronica ()
	{
		return tablas.get (1);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bebida de parranderos
	 */
	public String darTablaCliente ()
	{
		return tablas.get (2);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bar de parranderos
	 */
	public String darTablaEmpleado ()
	{
		return tablas.get (3);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bebedor de parranderos
	 */
	public String darTablaCuenta ()
	{
		return tablas.get (4);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Gustan de parranderos
	 */
	public String darTablaBanco ()
	{
		return tablas.get (5);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Sirven de parranderos
	 */
	public String darTablaPrestamo ()
	{
		return tablas.get (6);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaOperacion_Bancaria ()
	{
		return tablas.get (7);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Sirven de parranderos
	 */
	public String darTablaOficina ()
	{
		return tablas.get (8);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaPuesto_De_Atencion ()
	{
		return tablas.get (9);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaDeposito_Inversion ()
	{
		return tablas.get (10);
	}
	
	/**
	 * Transacción para el generador de secuencia de Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de Parranderos
	 */
	private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }
	
	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los CLIENTE
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla BEBEDOR
	 * Adiciona entradas al log de la aplicación
	 * @param tipo_documento - El nombre del bebedor
	 * @param ciudad - La ciudad del bebedor
	 * @param presupuesto - El presupuesto del bebedor (ALTO, MEDIO, BAJO)
	 * @return El objeto BEBEDOR adicionado. null si ocurre alguna Excepción
	 */
	public Cliente adicionarCliente (long id, String tipo_documento, String login_usuario, String contrasenia, String tipo_persona, 
			String nombre, String nacionalidad, String dir_fisica, int telefono, String ciudad, String departamento, int cod_postal, 
			long dir_electronica) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlCliente.adicionarCliente(pmf.getPersistenceManager(), id, tipo_documento, login_usuario, contrasenia, tipo_persona, 
        		 nombre, nacionalidad, dir_fisica, telefono, ciudad, departamento, cod_postal, dir_electronica);
            tx.commit();

            log.trace ("Inserción de cliente: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Cliente (id, tipo_documento, login_usuario, contrasenia, tipo_persona, 
           		 nombre, nacionalidad, dir_fisica, telefono, ciudad, departamento, cod_postal, dir_electronica);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla USUARIO que tienen el identificador dado
	 */
	public Cliente darClientePorId (long id) 
	{
		return (Cliente) sqlCliente.darClientePorId (pmf.getPersistenceManager(), id);
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los EMPLEADO
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla BEBEDOR
	 * Adiciona entradas al log de la aplicación
	 * @param tipo_documento - El nombre del bebedor
	 * @param ciudad - La ciudad del bebedor
	 * @param presupuesto - El presupuesto del bebedor (ALTO, MEDIO, BAJO)
	 * @return El objeto BEBEDOR adicionado. null si ocurre alguna Excepción
	 */
	public Empleado adicionarEmpleado (long id, String tipo_documento, String login_usuario, String contrasenia, String tipo_empleado, 
			String nombre, String nacionalidad, String dir_fisica, int telefono, String ciudad, String departamento, int cod_postal, 
			long dir_electronica) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlEmpleado.adicionarEmpleado(pmf.getPersistenceManager(), id, tipo_documento, login_usuario, contrasenia, tipo_empleado, 
        		 nombre, nacionalidad, dir_fisica, telefono, ciudad, departamento, cod_postal, dir_electronica);
            tx.commit();

            log.trace ("Inserción de empleado: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Empleado (id, tipo_documento, login_usuario, contrasenia, tipo_empleado, 
           		 nombre, nacionalidad, dir_fisica, telefono, ciudad, departamento, cod_postal, dir_electronica);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los CUENTA
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla BEBEDOR
	 * Adiciona entradas al log de la aplicación
	 * @param tipo - El nombre del bebedor
	 * @param ciudad - La ciudad del bebedor
	 * @param presupuesto - El presupuesto del bebedor (ALTO, MEDIO, BAJO)
	 * @return El objeto BEBEDOR adicionado. null si ocurre alguna Excepción
	 */
	public Cuenta adicionarCuenta (String tipo, char activa, int dinero, long cliente_asignado) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlCuenta.adicionarCuenta(pmf.getPersistenceManager(), id, tipo, activa, dinero, cliente_asignado);
            tx.commit();

            log.trace ("Inserción de cuenta: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Cuenta (id, tipo, activa, dinero, cliente_asignado);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long cerrarCuenta (long cliente_asignado)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlCuenta.cerrarCuenta (pm, cliente_asignado);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long actualizarSaldoCuenta (long id_operacion_bancaria, long cliente_asignado)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlCuenta.actualizarSaldoCuenta (pm, id_operacion_bancaria, cliente_asignado);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla USUARIO
	 */
	public List<Cuenta> darCuentasPorCliente_Asignado (long cliente_asignado)
	{
		return sqlCuenta.darCuentasPorCliente_Asignado (pmf.getPersistenceManager(), cliente_asignado);
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla USUARIO
	 */
	public List<Cuenta> darCuentas ()
	{
		return sqlCuenta.darCuentas (pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los OFICINA
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla BEBEDOR
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del bebedor
	 * @param ciudad - La ciudad del bebedor
	 * @param presupuesto - El presupuesto del bebedor (ALTO, MEDIO, BAJO)
	 * @return El objeto BEBEDOR adicionado. null si ocurre alguna Excepción
	 */
	public Oficina adicionarOficina (String nombre, String direccion, int num_puntos, long id_gerente_oficina) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlOficina.adicionarOficina(pmf.getPersistenceManager(), id, nombre, direccion, num_puntos, id_gerente_oficina);
            tx.commit();

            log.trace ("Inserción de oficina: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Oficina (id, nombre, direccion, num_puntos, id_gerente_oficina);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los OPERACION_BANCARIA
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla BEBEDOR
	 * Adiciona entradas al log de la aplicación
	 * @param tipo - El nombre del bebedor
	 * @param ciudad - La ciudad del bebedor
	 * @param presupuesto - El presupuesto del bebedor (ALTO, MEDIO, BAJO)
	 * @return El objeto BEBEDOR adicionado. null si ocurre alguna Excepción
	 */
	public Operacion_Bancaria adicionarOperacion_Bancaria (String tipo, int valor, Date fecha, long cliente_asignado, long cuenta_asignada, long prestamo_asignado) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlOperacion_Bancaria.adicionarOperacion_Bancaria(pmf.getPersistenceManager(), id, tipo, valor, fecha, cliente_asignado, cuenta_asignada, prestamo_asignado);
            tx.commit();

            log.trace ("Inserción de operacion bancaria: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Operacion_Bancaria (id, tipo, valor, fecha, cliente_asignado, cuenta_asignada, prestamo_asignado);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los PRESTAMO
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla BEBEDOR
	 * Adiciona entradas al log de la aplicación
	 * @param tipo - El nombre del bebedor
	 * @param ciudad - La ciudad del bebedor
	 * @param presupuesto - El presupuesto del bebedor (ALTO, MEDIO, BAJO)
	 * @return El objeto BEBEDOR adicionado. null si ocurre alguna Excepción
	 */
	public Prestamo adicionarPrestamo (String tipo, int cantidad, String estado, long cliente_asignado, long gerente_asignado, long banco_asignado) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlPrestamo.adicionarPrestamo(pmf.getPersistenceManager(), id, tipo, cantidad, estado, cliente_asignado, gerente_asignado, banco_asignado);
            tx.commit();

            log.trace ("Inserción de operacion bancaria: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Prestamo (id, tipo, cantidad, estado, cliente_asignado, gerente_asignado, banco_asignado);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long actualizarSaldoPrestamo (long id_operacion_bancaria, long cliente_asignado)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPrestamo.actualizarSaldoPrestamo (pm, id_operacion_bancaria, cliente_asignado);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long cerrarPrestamo (long id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPrestamo.cerrarPrestamo (pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los PUESTO_DE_ATENCION
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla BEBEDOR
	 * Adiciona entradas al log de la aplicación
	 * @param tipo - El nombre del bebedor
	 * @param ciudad - La ciudad del bebedor
	 * @param presupuesto - El presupuesto del bebedor (ALTO, MEDIO, BAJO)
	 * @return El objeto BEBEDOR adicionado. null si ocurre alguna Excepción
	 */
	public Puesto_De_Atencion adicionarPuesto_De_Atencion (String tipo, String localizacion, char es_fisico, long oficina, long banco_asignado) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlPuesto_De_Atencion.adicionarPuesto_De_Atencion(pmf.getPersistenceManager(), id, tipo, localizacion, es_fisico, oficina, banco_asignado);
            tx.commit();

            log.trace ("Inserción de puesto de atencion: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Puesto_De_Atencion (id, tipo, localizacion, es_fisico, oficina, banco_asignado);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
}
