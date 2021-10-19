package uniandes.isis2304.parranderos.negocio;

public class Banco implements VOBanco {
	
	private long id;
	
	private String nombre;
	
	private long id_gerente_gen;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Banco()
	{
		this.id = 0;
		this.nombre = "";
		this.id_gerente_gen = 0;
	}
	
	public Banco(long id, String nombre, long id_gerente_gen) {
		this.id = id;
		this.nombre = nombre;
		this.id_gerente_gen = id_gerente_gen;
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

	public long getId_gerente_gen() {
		return id_gerente_gen;
	}

	public void setId_gerente_gen(long id_gerente_gen) {
		this.id_gerente_gen = id_gerente_gen;
	}

	@Override
	public String toString() {
		return "Banco [id=" + id + ", nombre=" + nombre + ", id_gerente_gen=" + id_gerente_gen + "]";
	}
	
	/**
	 * @param tipo - El TipoBebida a comparar
	 * @return True si tienen el mismo nombre
	 */
	public boolean equals(Object tipo) 
	{
		Banco bc = (Banco) tipo;
		return id == bc.id;
	}

}
