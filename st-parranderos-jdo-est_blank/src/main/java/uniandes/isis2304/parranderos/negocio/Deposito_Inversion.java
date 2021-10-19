package uniandes.isis2304.parranderos.negocio;

public class Deposito_Inversion implements VODeposito_Inversion {
	
	private long id;
	
	private int num_acciones;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Deposito_Inversion()
	{
		this.id = 0;
		this.num_acciones = 0;
	}
	
	public Deposito_Inversion(long id, int num_acciones) {
		this.id = id;
		this.num_acciones = num_acciones;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNum_acciones() {
		return num_acciones;
	}

	public void setNum_acciones(int num_acciones) {
		this.num_acciones = num_acciones;
	}

	@Override
	public String toString() {
		return "Deposito_Inversion [id=" + id + ", num_acciones=" + num_acciones + "]";
	}
	
	
	/**
	 * @param tipo - El TipoBebida a comparar
	 * @return True si tienen el mismo nombre
	 */
	public boolean equals(Object tipo) 
	{
		Deposito_Inversion di = (Deposito_Inversion) tipo;
		return id == di.id;
	}
}
