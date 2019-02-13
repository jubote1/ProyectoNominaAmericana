package capaModelo;

public class Empleado {
	
	private int idempleado;
	private String nombreempleado;
	private String identificacion;
	private String telefono;
	private String cuenta;
	public String getNombreempleado() {
		return nombreempleado;
	}
	public void setNombreempleado(String nombreempleado) {
		this.nombreempleado = nombreempleado;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	
	
	public int getIdempleado() {
		return idempleado;
	}
	public void setIdempleado(int idempleado) {
		this.idempleado = idempleado;
	}
	public Empleado(int idempleado, String nombreempleado, String identificacion, String telefono, String cuenta) {
		super();
		this.idempleado = idempleado;
		this.nombreempleado = nombreempleado;
		this.identificacion = identificacion;
		this.telefono = telefono;
		this.cuenta = cuenta;
	}

	
	
	

}
