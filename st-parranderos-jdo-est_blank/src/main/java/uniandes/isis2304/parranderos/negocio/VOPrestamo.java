package uniandes.isis2304.parranderos.negocio;

public interface VOPrestamo {
	
	public long getId();
	
	public String getTipo();
	
	public int getCantidad();
	
	public String getEstado();
	
	public long getCliente_asignado();
	
	public long getGerente_asignado();
	
	public long getBanco_asignado();
	
	public String toString();
	
	public boolean equals(Object tipo);

}
