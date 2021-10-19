package uniandes.isis2304.parranderos.negocio;

public class Prestamo implements VOPrestamo {
	
	private long id;
	
	private String tipo;
	
	private int cantidad;
	
	private String estado;
	
	private long cliente_asignado;
	
	private long gerente_asignado;
	
	private long banco_asignado;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Prestamo()
	{
		this.id = 0;
		this.tipo = "";
		this.cantidad = 0;
		this.estado = "";
		this.cliente_asignado = 0;
		this.gerente_asignado = 0;
		this.banco_asignado = 0;
	}
	
	public Prestamo(long id, String tipo, int cantidad, String estado, long cliente_asignado, long gerente_asignado,
			long banco_asignado) {
		this.id = id;
		this.tipo = tipo;
		this.cantidad = cantidad;
		this.estado = estado;
		this.cliente_asignado = cliente_asignado;
		this.gerente_asignado = gerente_asignado;
		this.banco_asignado = banco_asignado;
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

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public long getCliente_asignado() {
		return cliente_asignado;
	}

	public void setCliente_asignado(long cliente_asignado) {
		this.cliente_asignado = cliente_asignado;
	}

	public long getGerente_asignado() {
		return gerente_asignado;
	}

	public void setGerente_asignado(long gerente_asignado) {
		this.gerente_asignado = gerente_asignado;
	}

	public long getBanco_asignado() {
		return banco_asignado;
	}

	public void setBanco_asignado(long banco_asignado) {
		this.banco_asignado = banco_asignado;
	}

	@Override
	public String toString() {
		return "Prestamo [id=" + id + ", tipo=" + tipo + ", cantidad=" + cantidad + ", estado=" + estado
				+ ", cliente_asignado=" + cliente_asignado + ", gerente_asignado=" + gerente_asignado
				+ ", banco_asignado=" + banco_asignado + "]";
	}
	
	/**
	 * @param tipo - El TipoBebida a comparar
	 * @return True si tienen el mismo nombre
	 */
	public boolean equals(Object tipo) 
	{
		Prestamo pr = (Prestamo) tipo;
		return id == pr.id;
	}

}
