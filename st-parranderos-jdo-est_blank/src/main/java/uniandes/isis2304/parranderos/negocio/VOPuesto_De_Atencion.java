package uniandes.isis2304.parranderos.negocio;

public interface VOPuesto_De_Atencion {
	
	public long getId();
	
	public String getTipo();
	
	public String getLocalizacion();
	
	public char getEs_fisico();
	
	public long getOficina();
	
	public long getBanco_asignado();
	
	public String toString();
	
	public boolean equals(Object tipo);

}
