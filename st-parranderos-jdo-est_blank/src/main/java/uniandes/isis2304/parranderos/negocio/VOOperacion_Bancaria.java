package uniandes.isis2304.parranderos.negocio;

import java.sql.Date;

public interface VOOperacion_Bancaria {
	
	public long getId();
	
	public String getTipo();
	
	public int getValor();
	
	public Date getFecha();
	
	public long getCliente_asignado();
	
	public long getCuenta_asignada();
	
	public long getPrestamo_asignado();
	
	public String toString();
	
	public boolean equals(Object tipo);

}
