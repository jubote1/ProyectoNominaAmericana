package capaModelo;

public class Nomina {
	
	private String fechaInicial;
	private String fechaFinal;
	private int idnomina;
	
	
	
	public int getIdnomina() {
		return idnomina;
	}
	public void setIdnomina(int idnomina) {
		this.idnomina = idnomina;
	}
	public String getFechaInicial() {
		return fechaInicial;
	}
	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	public String getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public Nomina(String fechaInicial, String fechaFinal, int idnomina) {
		super();
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.idnomina = idnomina;
	}

	
	
	

}
