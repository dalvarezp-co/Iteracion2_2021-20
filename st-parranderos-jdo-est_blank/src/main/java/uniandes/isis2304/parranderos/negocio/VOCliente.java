package uniandes.isis2304.parranderos.negocio;

public interface VOCliente {
	
	public long getId();
	
	public String getTipo_documento();
	
	public String getLogin_usuario();
	
	public String getContrasenia();
	
	public String getTipo_persona();
	
	public String getNombre();
	
	public String getNacionalidad();
	
	public String getDir_fisica();
	
	public int getTelefono();
	
	public String getCiudad();
	
	public String getDepartamento();
	
	public int getCod_postal();
	
	public long getDir_electronica();
	
	public String toString();
	
	public boolean equals(Object tipo);

}
