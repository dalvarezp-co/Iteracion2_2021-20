package uniandes.isis2304.parranderos.negocio;

public class Cuenta implements VOCuenta{
	
	private long id;
	
	private String tipo;
	
	private char activa;
	
	private int dinero;
	
	private long cliente_asignado;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Cuenta()
	{
		this.id = 0;
		this.tipo = "";
		this.activa = 'T';
		this.dinero = 0;
		this.cliente_asignado = 0;
	}
	
	public Cuenta(long id, String tipo, char activa, int dinero, long cliente_asignado) {
		this.id = id;
		this.tipo = tipo;
		this.activa = activa;
		this.dinero = dinero;
		this.cliente_asignado = cliente_asignado;
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

	public char getActiva() {
		return activa;
	}

	public void setActiva(char activa) {
		this.activa = activa;
	}

	public int getDinero() {
		return dinero;
	}

	public void setDinero(int dinero) {
		this.dinero = dinero;
	}

	public long getCliente_asignado() {
		return cliente_asignado;
	}

	public void setCliente_asignado(long cliente_asignado) {
		this.cliente_asignado = cliente_asignado;
	}

	@Override
	public String toString() {
		return "Cuenta [id=" + id + ", tipo=" + tipo + ", activa=" + activa + ", dinero=" + dinero
				+ ", cliente_asignado=" + cliente_asignado + "]";
	}
	
	/**
	 * @param tipo - El TipoBebida a comparar
	 * @return True si tienen el mismo nombre
	 */
	public boolean equals(Object tipo) 
	{
		Cuenta cu = (Cuenta) tipo;
		return id == cu.id;
	}

}
