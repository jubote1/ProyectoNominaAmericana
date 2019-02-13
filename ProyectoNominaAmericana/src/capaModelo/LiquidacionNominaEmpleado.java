package capaModelo;

public class LiquidacionNominaEmpleado {

	private int idnomina;
	private String fechasNomina;
	private int idempleado;
	private String nombreEmpleado;
	private int idconcpeto;
	private String descripcionConcepto;
	private double valorConcepto;
	
	
	
	public double getValorConcepto() {
		return valorConcepto;
	}
	public void setValorConcepto(double valorConcepto) {
		this.valorConcepto = valorConcepto;
	}
	public int getIdnomina() {
		return idnomina;
	}
	public void setIdnomina(int idnomina) {
		this.idnomina = idnomina;
	}
	public String getFechasNomina() {
		return fechasNomina;
	}
	public void setFechasNomina(String fechasNomina) {
		this.fechasNomina = fechasNomina;
	}
	public int getIdempleado() {
		return idempleado;
	}
	public void setIdempleado(int idempleado) {
		this.idempleado = idempleado;
	}
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	public int getIdconcpeto() {
		return idconcpeto;
	}
	public void setIdconcpeto(int idconcpeto) {
		this.idconcpeto = idconcpeto;
	}
	public String getDescripcionConcepto() {
		return descripcionConcepto;
	}
	public void setDescripcionConcepto(String descripcionConcepto) {
		this.descripcionConcepto = descripcionConcepto;
	}
	public LiquidacionNominaEmpleado(int idnomina, int idempleado, int idconcpeto, double valorConcepto) {
		super();
		this.idnomina = idnomina;
		this.idempleado = idempleado;
		this.idconcpeto = idconcpeto;
		this.valorConcepto = valorConcepto;
	}
	public LiquidacionNominaEmpleado(int idnomina, String fechasNomina, int idempleado, String nombreEmpleado,
			int idconcpeto, String descripcionConcepto,double valorConcepto) {
		super();
		this.idnomina = idnomina;
		this.fechasNomina = fechasNomina;
		this.idempleado = idempleado;
		this.nombreEmpleado = nombreEmpleado;
		this.idconcpeto = idconcpeto;
		this.descripcionConcepto = descripcionConcepto;
		this.valorConcepto = valorConcepto;
	}
	
	
	
}
