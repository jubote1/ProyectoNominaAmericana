package capaModelo;

public class LiquidacionPrima {
	
	private int liquidacionPrimaId;
	private String apellido;
	private String nombre;
	private String cedula;
	private double totalaPagar;
	private String totalaPagarLetras;
	private String semestrePrima;
	private String correoElectronico;
	
	
	public String getSemestrePrima() {
		return semestrePrima;
	}
	public void setSemestrePrima(String semestrePrima) {
		this.semestrePrima = semestrePrima;
	}
	public int getLiquidacionPrimaId() {
		return liquidacionPrimaId;
	}
	public void setLiquidacionPrimaId(int liquidacionPrimaId) {
		this.liquidacionPrimaId = liquidacionPrimaId;
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
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public double getTotalaPagar() {
		return totalaPagar;
	}
	public void setTotalaPagar(double totalaPagar) {
		this.totalaPagar = totalaPagar;
	}
	public String getTotalaPagarLetras() {
		return totalaPagarLetras;
	}
	public void setTotalaPagarLetras(String totalaPagarLetras) {
		this.totalaPagarLetras = totalaPagarLetras;
	}
	
	

	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	
	
	public LiquidacionPrima(int liquidacionPrimaId, String apellido, String nombre, String cedula, double totalaPagar,
			String totalaPagarLetras, String semestrePrima, String correoElectronico) {
		super();
		this.liquidacionPrimaId = liquidacionPrimaId;
		this.apellido = apellido;
		this.nombre = nombre;
		this.cedula = cedula;
		this.totalaPagar = totalaPagar;
		this.totalaPagarLetras = totalaPagarLetras;
		this.semestrePrima = semestrePrima;
		this.correoElectronico = correoElectronico;
	}
	public LiquidacionPrima() 
	{
	}

}
