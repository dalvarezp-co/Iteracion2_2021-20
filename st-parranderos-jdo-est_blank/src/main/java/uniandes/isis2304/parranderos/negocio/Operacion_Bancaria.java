package uniandes.isis2304.parranderos.negocio;

import java.sql.Date;

public class Operacion_Bancaria implements VOOperacion_Bancaria {
	
	private long id;
	
	private String tipo;
	
	private int valor;
	
	private Date fecha;
	
	private long cliente_asignado;
	
	private long cuenta_asignada;
	
	private long prestamo_asignado;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Operacion_Bancaria()
	{
		this.id = 0;
		this.tipo = "";
		this.valor = 0;
		this.fecha = null;
		this.cliente_asignado = 0;
		this.cuenta_asignada = 0;
		this.prestamo_asignado = 0;
	}
	
	public Operacion_Bancaria(long id, String tipo, int valor, Date fecha, long cliente_asignado, long cuenta_asignada,
			long prestamo_asignado) {
		this.id = id;
		this.tipo = tipo;
		this.valor = valor;
		this.fecha = fecha;
		this.cliente_asignado = cliente_asignado;
		this.cuenta_asignada = cuenta_asignada;
		this.prestamo_asignado = prestamo_asignado;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public long getCliente_asignado() {
		return cliente_asignado;
	}

	public void setCliente_asignado(long cliente_asignado) {
		this.cliente_asignado = cliente_asignado;
	}

	public long getCuenta_asignada() {
		return cuenta_asignada;
	}

	public void setCuenta_asignada(long cuenta_asignada) {
		this.cuenta_asignada = cuenta_asignada;
	}

	public long getPrestamo_asignado() {
		return prestamo_asignado;
	}

	public void setPrestamo_asignado(long prestamo_asignado) {
		this.prestamo_asignado = prestamo_asignado;
	}

	@Override
	public String toString() {
		return "Operacion_Bancaria [id=" + id + ", tipo=" + tipo + ", valor=" + valor + ", fecha=" + fecha
				+ ", cliente_asignado=" + cliente_asignado + ", cuenta_asignada=" + cuenta_asignada
				+ ", prestamo_asignado=" + prestamo_asignado + "]";
	}
	
	/**
	 * @param tipo - El TipoBebida a comparar
	 * @return True si tienen el mismo nombre
	 */
	public boolean equals(Object tipo) 
	{
		Operacion_Bancaria ob = (Operacion_Bancaria) tipo;
		return id == ob.id;
	}

}
