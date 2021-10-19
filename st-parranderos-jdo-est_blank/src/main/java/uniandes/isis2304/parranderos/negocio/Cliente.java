package uniandes.isis2304.parranderos.negocio;

public class Cliente implements VOCliente {
	
	private long id;
	
	private String tipo_documento;
	
	private String login_usuario;
	
	private String contrasenia;
	
	private String tipo_persona;
	
	private String nombre;
	
	private String nacionalidad;
	
	private String dir_fisica;
	
	private int telefono;
	
	private String ciudad;
	
	private String departamento;
	
	private int cod_postal;
	
	private long dir_electronica;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Cliente()
	{
		this.id = 0;
		this.tipo_documento = "";
		this.login_usuario = "";
		this.contrasenia = "";
		this.tipo_persona = "";
		this.nombre = "";
		this.nacionalidad = "";
		this.dir_fisica = "";
		this.telefono = 0;
		this.ciudad = "";
		this.departamento = "";
		this.cod_postal = 0;
		this.dir_electronica = 0;
	}
	
	/**
	 * Constructor con valores
	 * @param id - El id del usuario
	 * @param nombre - El nombre del usuario
	 * @param ciudad - La ciudad del usuario
	 * @param presupuesto - El presupuesto del usuario (ALTO, MEDIO, BAJO)
	 */
	public Cliente(long id, String tipo_documento, String login_usuario, String contrasenia, String tipo_persona, 
			String nombre, String nacionalidad, String dir_fisica, int telefono, String ciudad, String departamento, int cod_postal, 
			long dir_electronica) 
	{
		this.id = id;
		this.tipo_documento = tipo_documento;
		this.login_usuario = login_usuario;
		this.contrasenia = contrasenia;
		this.tipo_persona = tipo_persona;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.dir_fisica = dir_fisica;
		this.telefono = telefono;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.cod_postal = cod_postal;
		this.dir_electronica = dir_electronica;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTipo_documento() {
		return tipo_documento;
	}

	public void setTipo_documento(String tipo_documento) {
		this.tipo_documento = tipo_documento;
	}

	public String getLogin_usuario() {
		return login_usuario;
	}

	public void setLogin_usuario(String login_usuario) {
		this.login_usuario = login_usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getTipo_persona() {
		return tipo_persona;
	}

	public void setTipo_persona(String tipo_persona) {
		this.tipo_persona = tipo_persona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getDir_fisica() {
		return dir_fisica;
	}

	public void setDir_fisica(String dir_fisica) {
		this.dir_fisica = dir_fisica;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public int getCod_postal() {
		return cod_postal;
	}

	public void setCod_postal(int cod_postal) {
		this.cod_postal = cod_postal;
	}

	public long getDir_electronica() {
		return dir_electronica;
	}

	public void setDir_electronica(long dir_electronica) {
		this.dir_electronica = dir_electronica;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", tipo_documento=" + tipo_documento + ", login_usuario=" + login_usuario
				+ ", contrasenia=" + contrasenia + ", tipo_persona=" + tipo_persona + ", nombre=" + nombre
				+ ", nacionalidad=" + nacionalidad + ", dir_fisica=" + dir_fisica + ", telefono=" + telefono
				+ ", ciudad=" + ciudad + ", departamento=" + departamento + ", cod_postal=" + cod_postal
				+ ", dir_electronica=" + dir_electronica + "]";
	}
	
	/**
	 * @param tipo - El TipoBebida a comparar
	 * @return True si tienen el mismo nombre
	 */
	public boolean equals(Object tipo) 
	{
		Cliente cl = (Cliente) tipo;
		return id == cl.id;
	}

}
