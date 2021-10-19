package uniandes.isis2304.parranderos.negocio;

public interface VOCuenta {
	
	public long getId();
	
	public String getTipo();
	
	public char getActiva();
	
	public int getDinero();
	
	public long getCliente_asignado();
	
	public String toString();
	
	public boolean equals(Object tipo);

}
