package uniandes.isis2304.parranderos.negocio;

public class Oficina implements VOOficina {
	
	private long id;
	
	private String nombre;
	
	private String direccion;
	
	private int num_puntos;
	
	private long id_gerente_oficina;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Oficina()
	{
		this.id = 0;
		this.nombre = "";
		this.direccion = "";
		this.num_puntos = 0;
		this.id_gerente_oficina = 0;
	}
	
	public Oficina(long id, String nombre, String direccion, int num_puntos, long id_gerente_oficina) {
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.num_puntos = num_puntos;
		this.id_gerente_oficina = id_gerente_oficina;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getNum_puntos() {
		return num_puntos;
	}

	public void setNum_puntos(int num_puntos) {
		this.num_puntos = num_puntos;
	}

	public long getId_gerente_oficina() {
		return id_gerente_oficina;
	}

	public void setId_gerente_oficina(long id_gerente_oficina) {
		this.id_gerente_oficina = id_gerente_oficina;
	}

	@Override
	public String toString() {
		return "Oficina [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", num_puntos=" + num_puntos
				+ ", id_gerente_oficina=" + id_gerente_oficina + "]";
	}
	
	/**
	 * @param tipo - El TipoBebida a comparar
	 * @return True si tienen el mismo nombre
	 */
	public boolean equals(Object tipo) 
	{
		Oficina of = (Oficina) tipo;
		return id == of.id;
	}
}
