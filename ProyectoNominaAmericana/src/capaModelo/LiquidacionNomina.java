package capaModelo;

public class LiquidacionNomina {

	private int liquidacionNominaId;
	private String apellido;
	private String nombre;
	private String cedula;
	private double salarioBase;
	private double valorQuincena;
	private double horaExtraOrd;
	private double valorExtraOrd;
	private double recargoNocturno;
	private double valorRecargoNocturno;
	private double recargoHoraDominical;
	private double valorHoraDominical;
	private double horaFestivaDominical;
	private double valorHoraFestivaDominical;
	private double horaExtraFestivaDominical;
	private double valorFestiva;
	private String tipoAuxilio;
	private double valorAuxilio;
	//private double auxilioTransporte;
	private double totalExtras;
	private double subtotalDevengado;
	private double deduccionSeguridadSocial;
	private double vales;
	private double pizzaAhorro;
	private double abonoAPrestamo;
	private String otros;
	private double valorOtros;
	private double totalDeducciones;
	private double totalAPagar;
	private String totalPagarLetras;
	private String quincena;
	private String quincenaPago;
	private String correoElectronico;
	private String tipo;
	private String cargo;
	private String vinculacion;
	
	
	
	
	public int getLiquidacionNominaId() {
		return liquidacionNominaId;
	}
	public void setLiquidacionNominaId(int liquidacionNominaId) {
		this.liquidacionNominaId = liquidacionNominaId;
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
	public double getSalarioBase() {
		return salarioBase;
	}
	public void setSalarioBase(double salarioBase) {
		this.salarioBase = salarioBase;
	}
	public double getValorQuincena() {
		return valorQuincena;
	}
	public void setValorQuincena(double valorQuincena) {
		this.valorQuincena = valorQuincena;
	}
	public double getHoraExtraOrd() {
		return horaExtraOrd;
	}
	public void setHoraExtraOrd(double horaExtraOrd) {
		this.horaExtraOrd = horaExtraOrd;
	}
	public double getValorExtraOrd() {
		return valorExtraOrd;
	}
	public void setValorExtraOrd(double valorExtraOrd) {
		this.valorExtraOrd = valorExtraOrd;
	}
	public double getRecargoNocturno() {
		return recargoNocturno;
	}
	public void setRecargoNocturno(double recargoNocturno) {
		this.recargoNocturno = recargoNocturno;
	}
	public double getValorRecargoNocturno() {
		return valorRecargoNocturno;
	}
	public void setValorRecargoNocturno(double valorRecargoNocturno) {
		this.valorRecargoNocturno = valorRecargoNocturno;
	}
	public double getRecargoHoraDominical() {
		return recargoHoraDominical;
	}
	public void setRecargoHoraDominical(double recargoHoraDominical) {
		this.recargoHoraDominical = recargoHoraDominical;
	}
	public double getValorHoraDominical() {
		return valorHoraDominical;
	}
	public void setValorHoraDominical(double valorHoraDominical) {
		this.valorHoraDominical = valorHoraDominical;
	}
	public double getHoraFestivaDominical() {
		return horaFestivaDominical;
	}
	public void setHoraFestivaDominical(double horaFestivaDominical) {
		this.horaFestivaDominical = horaFestivaDominical;
	}
	public double getValorHoraFestivaDominical() {
		return valorHoraFestivaDominical;
	}
	public void setValorHoraFestivaDominical(double valorHoraFestivaDominical) {
		this.valorHoraFestivaDominical = valorHoraFestivaDominical;
	}
	public double getHoraExtraFestivaDominical() {
		return horaExtraFestivaDominical;
	}
	public void setHoraExtraFestivaDominical(double horaExtraFestivaDominical) {
		this.horaExtraFestivaDominical = horaExtraFestivaDominical;
	}
	public double getValorFestiva() {
		return valorFestiva;
	}
	public void setValorFestiva(double valorFestiva) {
		this.valorFestiva = valorFestiva;
	}

	public String getTipoAuxilio() {
		return tipoAuxilio;
	}
	public void setTipoAuxilio(String tipoAuxilio) {
		this.tipoAuxilio = tipoAuxilio;
	}
	
	public double getValorAuxilio() {
		return valorAuxilio;
	}
	public void setValorAuxilio(double valorAuxilio) {
		this.valorAuxilio = valorAuxilio;
	}
	public double getTotalExtras() {
		return totalExtras;
	}
	public void setTotalExtras(double totalExtras) {
		this.totalExtras = totalExtras;
	}
	public double getSubtotalDevengado() {
		return subtotalDevengado;
	}
	public void setSubtotalDevengado(double subtotalDevengado) {
		this.subtotalDevengado = subtotalDevengado;
	}
	public double getDeduccionSeguridadSocial() {
		return deduccionSeguridadSocial;
	}
	public void setDeduccionSeguridadSocial(double deduccionSeguridadSocial) {
		this.deduccionSeguridadSocial = deduccionSeguridadSocial;
	}
	public double getVales() {
		return vales;
	}
	public void setVales(double vales) {
		this.vales = vales;
	}
	public double getPizzaAhorro() {
		return pizzaAhorro;
	}
	public void setPizzaAhorro(double pizzaAhorro) {
		this.pizzaAhorro = pizzaAhorro;
	}
	public double getAbonoAPrestamo() {
		return abonoAPrestamo;
	}
	public void setAbonoAPrestamo(double abonoAPrestamo) {
		this.abonoAPrestamo = abonoAPrestamo;
	}
	public String getOtros() {
		return otros;
	}
	public void setOtros(String otros) {
		this.otros = otros;
	}
	public double getValorOtros() {
		return valorOtros;
	}
	public void setValorOtros(double valorOtros) {
		this.valorOtros = valorOtros;
	}
	public double getTotalDeducciones() {
		return totalDeducciones;
	}
	public void setTotalDeducciones(double totalDeducciones) {
		this.totalDeducciones = totalDeducciones;
	}
	public double getTotalAPagar() {
		return totalAPagar;
	}
	public void setTotalAPagar(double totalAPagar) {
		this.totalAPagar = totalAPagar;
	}
	public String getTotalPagarLetras() {
		return totalPagarLetras;
	}
	public void setTotalPagarLetras(String totalPagarLetras) {
		this.totalPagarLetras = totalPagarLetras;
	}
	public String getQuincena() {
		return quincena;
	}
	public void setQuincena(String quincena) {
		this.quincena = quincena;
	}
	public String getQuincenaPago() {
		return quincenaPago;
	}
	public void setQuincenaPago(String quincenaPago) {
		this.quincenaPago = quincenaPago;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getVinculacion() {
		return vinculacion;
	}
	public void setVinculacion(String vinculacion) {
		this.vinculacion = vinculacion;
	}
	public LiquidacionNomina()
	{
		super();
	}
	
	public LiquidacionNomina(int liquidacionNominaId, String apellido, String nombre, String cedula, double salarioBase,
			double valorQuincena, double horaExtraOrd, double valorExtraOrd, double recargoNocturno,
			double valorRecargoNocturno, double recargoHoraDominical, double valorHoraDominical,
			double horaFestivaDominical, double valorHoraFestivaDominical, double horaExtraFestivaDominical,
			double valorFestiva, String tipoAuxilio, double valorAuxilio, double totalExtras, double subtotalDevengado,
			double deduccionSeguridadSocial, double vales, double pizzaAhorro, double abonoAPrestamo, String otros,
			double valorOtros, double totalDeducciones, double totalAPagar, String totalPagarLetras, String quincena,
			String quincenaPago, String correoElectronico) {
		super();
		this.liquidacionNominaId = liquidacionNominaId;
		this.apellido = apellido;
		this.nombre = nombre;
		this.cedula = cedula;
		this.salarioBase = salarioBase;
		this.valorQuincena = valorQuincena;
		this.horaExtraOrd = horaExtraOrd;
		this.valorExtraOrd = valorExtraOrd;
		this.recargoNocturno = recargoNocturno;
		this.valorRecargoNocturno = valorRecargoNocturno;
		this.recargoHoraDominical = recargoHoraDominical;
		this.valorHoraDominical = valorHoraDominical;
		this.horaFestivaDominical = horaFestivaDominical;
		this.valorHoraFestivaDominical = valorHoraFestivaDominical;
		this.horaExtraFestivaDominical = horaExtraFestivaDominical;
		this.valorFestiva = valorFestiva;
		this.tipoAuxilio = tipoAuxilio;
		this.valorAuxilio = valorAuxilio;
		this.totalExtras = totalExtras;
		this.subtotalDevengado = subtotalDevengado;
		this.deduccionSeguridadSocial = deduccionSeguridadSocial;
		this.vales = vales;
		this.pizzaAhorro = pizzaAhorro;
		this.abonoAPrestamo = abonoAPrestamo;
		this.otros = otros;
		this.valorOtros = valorOtros;
		this.totalDeducciones = totalDeducciones;
		this.totalAPagar = totalAPagar;
		this.totalPagarLetras = totalPagarLetras;
		this.quincena = quincena;
		this.quincenaPago = quincenaPago;
		this.correoElectronico = correoElectronico;
	}
		

	public LiquidacionNomina(int liquidacionNominaId, String apellido, String nombre, String cedula, double salarioBase,
			double valorQuincena, double horaExtraOrd, double valorExtraOrd, double recargoNocturno,
			double valorRecargoNocturno, double recargoHoraDominical, double valorHoraDominical,
			double horaFestivaDominical, double valorHoraFestivaDominical, double horaExtraFestivaDominical,
			double valorFestiva, String tipoAuxilio, double valorAuxilio, double totalExtras, double subtotalDevengado,
			double deduccionSeguridadSocial, double vales, double pizzaAhorro, double abonoAPrestamo, String otros,
			double valorOtros, double totalDeducciones, double totalAPagar, String totalPagarLetras, String quincena,
			String quincenaPago, String correoElectronico, String tipo, String cargo, String vinculacion) {
		super();
		this.liquidacionNominaId = liquidacionNominaId;
		this.apellido = apellido;
		this.nombre = nombre;
		this.cedula = cedula;
		this.salarioBase = salarioBase;
		this.valorQuincena = valorQuincena;
		this.horaExtraOrd = horaExtraOrd;
		this.valorExtraOrd = valorExtraOrd;
		this.recargoNocturno = recargoNocturno;
		this.valorRecargoNocturno = valorRecargoNocturno;
		this.recargoHoraDominical = recargoHoraDominical;
		this.valorHoraDominical = valorHoraDominical;
		this.horaFestivaDominical = horaFestivaDominical;
		this.valorHoraFestivaDominical = valorHoraFestivaDominical;
		this.horaExtraFestivaDominical = horaExtraFestivaDominical;
		this.valorFestiva = valorFestiva;
		this.tipoAuxilio = tipoAuxilio;
		this.valorAuxilio = valorAuxilio;
		this.totalExtras = totalExtras;
		this.subtotalDevengado = subtotalDevengado;
		this.deduccionSeguridadSocial = deduccionSeguridadSocial;
		this.vales = vales;
		this.pizzaAhorro = pizzaAhorro;
		this.abonoAPrestamo = abonoAPrestamo;
		this.otros = otros;
		this.valorOtros = valorOtros;
		this.totalDeducciones = totalDeducciones;
		this.totalAPagar = totalAPagar;
		this.totalPagarLetras = totalPagarLetras;
		this.quincena = quincena;
		this.quincenaPago = quincenaPago;
		this.correoElectronico = correoElectronico;
		this.tipo = tipo;
		this.cargo = cargo;
		this.vinculacion = vinculacion;
	}
	
	
	
}
