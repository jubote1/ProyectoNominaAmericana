package capaModelo;

public class ConceptoNomina {

	private int idconcepto;
	private String descripcion;
	
	public int getIdconcepto() {
		return idconcepto;
	}
	public void setIdconcepto(int idconcepto) {
		this.idconcepto = idconcepto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public ConceptoNomina(int idconcepto, String descripcion) {
		super();
		this.idconcepto = idconcepto;
		this.descripcion = descripcion;
	}
	
	
	
	
}
