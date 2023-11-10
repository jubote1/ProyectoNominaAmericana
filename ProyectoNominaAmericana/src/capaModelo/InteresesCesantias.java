package capaModelo;

public class InteresesCesantias {

	private int interesesCesantiasId;
	private String apellido;
	private String nombre;
	private String tipo;
	private String cedula;
	private double valor;
	private String valorLetras;
	private String ano;
	private String correo;
	public int getInteresesCesantiasId() {
		return interesesCesantiasId;
	}
	public void setInteresesCesantiasId(int interesesCesantiasId) {
		this.interesesCesantiasId = interesesCesantiasId;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getValorLetras() {
		return valorLetras;
	}
	public void setValorLetras(String valorLetras) {
		this.valorLetras = valorLetras;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public InteresesCesantias(int interesesCesantiasId, String apellido, String nombre, String tipo, String cedula,
			double valor, String valorLetras, String ano, String correo) {
		super();
		this.interesesCesantiasId = interesesCesantiasId;
		this.apellido = apellido;
		this.nombre = nombre;
		this.tipo = tipo;
		this.cedula = cedula;
		this.valor = valor;
		this.valorLetras = valorLetras;
		this.ano = ano;
		this.correo = correo;
	}
	public InteresesCesantias() {
		super();
	}

	
		
	
}
