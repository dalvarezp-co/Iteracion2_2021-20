package uniandes.isis2304.parranderos.negocio;

public class Puesto_De_Atencion implements VOPuesto_De_Atencion {
	
	private long id;
	
	private String tipo;
	
	private String localizacion;
	
	private char es_fisico;
	
	private long oficina;
	
	private long banco_asignado;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Puesto_De_Atencion()
	{
		this.id = 0;
		this.tipo = "";
		this.localizacion = "";
		this.es_fisico = 'F';
		this.oficina = 0;
		this.banco_asignado = 0;
	}
	
	public Puesto_De_Atencion(long id, String tipo, String localizacion, char es_fisico, long oficina,
			long banco_asignado) {
		this.id = id;
		this.tipo = tipo;
		this.localizacion = localizacion;
		this.es_fisico = es_fisico;
		this.oficina = oficina;
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

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public char getEs_fisico() {
		return es_fisico;
	}

	public void setEs_fisico(char es_fisico) {
		this.es_fisico = es_fisico;
	}

	public long getOficina() {
		return oficina;
	}

	public void setOficina(long oficina) {
		this.oficina = oficina;
	}

	public long getBanco_asignado() {
		return banco_asignado;
	}

	public void setBanco_asignado(long banco_asignado) {
		this.banco_asignado = banco_asignado;
	}

	@Override
	public String toString() {
		return "Puesto_De_Atencion [id=" + id + ", tipo=" + tipo + ", localizacion=" + localizacion + ", es_fisico="
				+ es_fisico + ", oficina=" + oficina + ", banco_asignado=" + banco_asignado + "]";
	}
	
	/**
	 * @param tipo - El TipoBebida a comparar
	 * @return True si tienen el mismo nombre
	 */
	public boolean equals(Object tipo) 
	{
		Puesto_De_Atencion pa = (Puesto_De_Atencion) tipo;
		return id == pa.id;
	}

}
