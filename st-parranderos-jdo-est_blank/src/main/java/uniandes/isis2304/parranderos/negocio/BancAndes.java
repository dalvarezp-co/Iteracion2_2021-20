package uniandes.isis2304.parranderos.negocio;

import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import uniandes.isis2304.parranderos.persistencia.PersistenciaBancAndes;

public class BancAndes {
	
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(BancAndes.class.getName());

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaBancAndes pp;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public BancAndes ()
	{
		pp = PersistenciaBancAndes.getInstance ();
	}

	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public BancAndes (JsonObject tableConfig)
	{
		pp = PersistenciaBancAndes.getInstance (tableConfig);
	}

	/**
	 * Cierra la conexión con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		pp.cerrarUnidadPersistencia ();
	}
	/* ****************************************************************
	 * 			Métodos para manejar los CLIENTE
	 *****************************************************************/
	
	/**
	 * Adiciona de manera persistente un usuario 
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto USUARIO adicionado. null si ocurre alguna Excepción
	 */
	public Cliente adicionarCliente (long id, String tipo_documento, String login_usuario, String contrasenia, String tipo_persona, 
			String nombre, String nacionalidad, String dir_fisica, int telefono, String ciudad, String departamento, int cod_postal, 
			long dir_electronica)
	{
		log.info ("Adicionando Cliente: " + id);
		Cliente cliente = pp.adicionarCliente (id, tipo_documento, login_usuario, contrasenia, tipo_persona, nombre, nacionalidad, dir_fisica, telefono, ciudad, departamento, cod_postal, dir_electronica);
		log.info ("Adicionando Cliente: " + cliente);
		return cliente;
	}
	
	/**
	 * Encuentra un usuario y su información básica, según su identificador
	 * @return Un objeto Bebedor que corresponde con el identificador buscado y lleno con su información básica
	 * 			null, si un bebedor con dicho identificador no existe
	 */
	public Cliente darClientePorId (long id)
	{
		log.info ("Dar información de un Cliente por id: " + id);
		Cliente cliente = pp.darClientePorId (id);
		log.info ("Buscando Cliente por Id: " + cliente != null ? cliente : "NO EXISTE");
		return cliente;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los EMPLEADO
	 *****************************************************************/
	
	/**
	 * Adiciona de manera persistente un usuario 
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto USUARIO adicionado. null si ocurre alguna Excepción
	 */
	public Empleado adicionarEmpleado (long id, String tipo_documento, String login_usuario, String contrasenia, String tipo_empleado, 
			String nombre, String nacionalidad, String dir_fisica, int telefono, String ciudad, String departamento, int cod_postal, 
			long dir_electronica)
	{
		log.info ("Adicionando Empleado: " + id);
		Empleado empleado = pp.adicionarEmpleado (id, tipo_documento, login_usuario, contrasenia, tipo_empleado, nombre, nacionalidad, dir_fisica, telefono, ciudad, departamento, cod_postal, dir_electronica);
		log.info ("Adicionando Empleado: " + empleado);
		return empleado;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los CUENTA
	 *****************************************************************/
	
	/**
	 * Adiciona de manera persistente un usuario 
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto USUARIO adicionado. null si ocurre alguna Excepción
	 */
	public Cuenta adicionarCuenta (String tipo, char activa, int dinero, long cliente_asignado)
	{
		log.info ("Adicionando Cuenta: " + activa);
		Cuenta cuenta = pp.adicionarCuenta (tipo, activa, dinero, cliente_asignado);
		log.info ("Adicionando Cuenta: " + cuenta);
		return cuenta;
	}
	
	/**
	 * Cambia el nombre de un usuario dado su identificador
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que un bebedor con ese identificador no existe
	 */
	public long cerrarCuenta (long cliente_asignado)
	{
		log.info ("Cerrando cuenta de cliente: " + cliente_asignado);
		long cambios = pp.cerrarCuenta (cliente_asignado);
		return cambios;
	}
	
	public long actualizarSaldoCuenta (long id_operacion_bancaria, long cliente_asignado)
	{
		log.info ("Cambiando saldo de cuenta del cliente: " + cliente_asignado);
		long cambios = pp.actualizarSaldoCuenta (id_operacion_bancaria, cliente_asignado);
		return cambios;
	}
	
	/**
	 * Encuentra todos los usuarios en 
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos usuario con todos las usuario que conoce la aplicación, llenos con su información básica
	 */
	public List<Cuenta> darCuentasPorCliente_Asignado (long cliente_asignado)
	{
		log.info ("Listando Cuentas de cliente: " + cliente_asignado);
		List<Cuenta> cuentas = pp.darCuentasPorCliente_Asignado (cliente_asignado);	
		log.info ("Listando: " + cuentas.size() + " Cuentas existentes");
		return cuentas;
	}
	
	/**
	 * Encuentra todos los usuarios en 
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos usuario con todos las usuario que conoce la aplicación, llenos con su información básica
	 */
	public List<Cuenta> darCuentas ()
	{
		log.info ("Listando Cuentas");
		List<Cuenta> cuentas = pp.darCuentas ();	
		log.info ("Listando: " + cuentas.size() + " Cuentas existentes");
		return cuentas;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los OFICINA
	 *****************************************************************/
	
	/**
	 * Adiciona de manera persistente un usuario 
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto USUARIO adicionado. null si ocurre alguna Excepción
	 */
	public Oficina adicionarOficina (String nombre, String direccion, int num_puntos, long id_gerente_oficina)
	{
		log.info ("Adicionando Oficina: " + nombre);
		Oficina oficina = pp.adicionarOficina (nombre, direccion, num_puntos, id_gerente_oficina);
		log.info ("Adicionando Oficina: " + oficina);
		return oficina;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los OPERACION_BANCARIA
	 *****************************************************************/
	
	/**
	 * Adiciona de manera persistente un usuario 
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto USUARIO adicionado. null si ocurre alguna Excepción
	 */
	public Operacion_Bancaria adicionarOperacion_Bancaria (String tipo, int valor, Date fecha, long cliente_asignado, long cuenta_asignada, long prestamo_asignado)
	{
		log.info ("Adicionando Operacion_Bancaria: " + tipo);
		Operacion_Bancaria operacion_bancaria = pp.adicionarOperacion_Bancaria (tipo, valor, fecha, cliente_asignado, cuenta_asignada, prestamo_asignado);
		log.info ("Adicionando Operacion_Bancaria: " + operacion_bancaria);
		return operacion_bancaria;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los PRESTAMO
	 *****************************************************************/
	
	/**
	 * Adiciona de manera persistente un usuario 
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto USUARIO adicionado. null si ocurre alguna Excepción
	 */
	public Prestamo adicionarPrestamo (String tipo, int cantidad, String estado, long cliente_asignado, long gerente_asignado, long banco_asignado)
	{
		log.info ("Adicionando Prestamo: " + tipo);
		Prestamo prestamo = pp.adicionarPrestamo (tipo, cantidad, estado, cliente_asignado, gerente_asignado, banco_asignado);
		log.info ("Adicionando Prestamo: " + prestamo);
		return prestamo;
	}
	
	public long actualizarSaldoPrestamo (long id_operacion_bancaria, long cliente_asignado)
	{
		log.info ("Cambiando saldo de prestamo del cliente: " + cliente_asignado);
		long cambios = pp.actualizarSaldoPrestamo (id_operacion_bancaria, cliente_asignado);
		return cambios;
	}
	
	/**
	 * Cambia el nombre de un usuario dado su identificador
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que un bebedor con ese identificador no existe
	 */
	public long cerrarPrestamo (long id)
	{
		log.info ("Cerrando prestamo: " + id);
		long cambios = pp.cerrarPrestamo (id);
		return cambios;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los PUESTO_DE_ATENCION
	 *****************************************************************/
	
	/**
	 * Adiciona de manera persistente un usuario 
	 * Adiciona entradas al log de la aplicación
	 * @return El objeto USUARIO adicionado. null si ocurre alguna Excepción
	 */
	public Puesto_De_Atencion adicionarPuesto_De_Atencion (String tipo, String localizacion, char es_fisico, long oficina, long banco_asignado)
	{
		log.info ("Adicionando Puesto_De_Atencion: " + tipo);
		Puesto_De_Atencion puesto_de_atencion = pp.adicionarPuesto_De_Atencion (tipo, localizacion, es_fisico, oficina, banco_asignado);
		log.info ("Adicionando Puesto_De_Atencion: " + puesto_de_atencion);
		return puesto_de_atencion;
	}
	
	

}
