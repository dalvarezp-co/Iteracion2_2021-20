package uniandes.isis2304.parranderos.negocio;

public class Direccion_Electronica implements VODireccion_Electronica {
	
	private long id;
	
	private String direccion;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Direccion_Electronica()
	{
		this.id = 0;
		this.direccion = "";
	}
	
	public Direccion_Electronica(long id, String direccion) {
		this.id = id;
		this.direccion = direccion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Direccion_Electronica [id=" + id + ", direccion=" + direccion + "]";
	}
	
	/**
	 * @param tipo - El TipoBebida a comparar
	 * @return True si tienen el mismo nombre
	 */
	public boolean equals(Object tipo) 
	{
		Direccion_Electronica de = (Direccion_Electronica) tipo;
		return id == de.id;
	}

}
