package uniandes.isis2304.parranderos.negocio;

public interface VOOficina {
	
	public long getId();
	
	public String getNombre();
	
	public String getDireccion();
	
	public int getNum_puntos();
	
	public long getId_gerente_oficina();
	
	public String toString();
	
	public boolean equals(Object tipo);

}
