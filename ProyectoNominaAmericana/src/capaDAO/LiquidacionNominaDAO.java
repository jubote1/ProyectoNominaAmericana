package capaDAO;
import conexion.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import capaModelo.Empleado;
import capaModelo.LiquidacionNomina;
import capaModelo.Nomina;

import java.sql.ResultSet;
import org.apache.log4j.Logger;
/**
 * Clase que se encarga de implementar todos aquellos métodos que tienen una interacción directa con la base de datos
 * @author JuanDavid
 *
 */
public class LiquidacionNominaDAO {
	
	
public static ArrayList<LiquidacionNomina> retornarLiquidacionNomina(String quincenaPago)
{
	Logger logger = Logger.getLogger("log_file");
	ConexionBaseDatos con = new ConexionBaseDatos();
	Connection con1 = con.obtenerConexionBDPrincipal();
	ArrayList<LiquidacionNomina> liquidacionNominas = new ArrayList();
	LiquidacionNomina liqNomina = new LiquidacionNomina();
	try
	{
		Statement stm = con1.createStatement();
		String consulta = "select * from  liquidacion_nomina where quincena_pago ='"+ quincenaPago + "'"; 
		logger.info(consulta);
		ResultSet rs = stm.executeQuery(consulta);
		 int liquidacionNominaId;
		 String apellido;
		 String nombre;
		 String cedula;
		 double salarioBase;
		 double valorQuincena;
		 double horaExtraOrd;
		 double valorExtraOrd;
		 double recargoNocturno;
		 double valorRecargoNocturno;
		 double recargoHoraDominical;
		 double valorHoraDominical;
		 double horaFestivaDominical;
		 double valorHoraFestivaDominical;
		 double horaExtraFestivaDominical;
		 double valorFestiva;
		 String tipoAuxilio;
		 double valorAuxilio;
		 double totalExtras;
		 double subtotalDevengado;
		 double deduccionSeguridadSocial;
		 double vales;
		 double pizzaAhorro;
		 double abonoAPrestamo;
		 String otros;
		 double valorOtros;
		 double totalDeducciones;
		 double totalAPagar;
		 String totalPagarLetras;
		 String quincena;
		 String correoElectronico;
		while(rs.next()){
			liquidacionNominaId = rs.getInt("liquidacion_nomina_id");
			apellido = rs.getString("apellido");
			if(apellido == null )
			{
				apellido = "";
			}
			nombre = rs.getString("nombre");
			if(nombre == null )
			{
				nombre = "";
			}
			cedula = rs.getString("cedula");
			if(cedula == null )
			{
				cedula = "";
			}
			salarioBase = rs.getDouble("salario_base");
			valorQuincena = rs.getDouble("valor_quincena");
			horaExtraOrd = rs.getDouble("hora_extra_ord");
			valorExtraOrd = rs.getDouble("valor_extra_ord");
			recargoNocturno = rs.getDouble("recargo_nocturno");
			valorRecargoNocturno = rs.getDouble("valor_rec_nocturno");
			recargoHoraDominical = rs.getDouble("recargo_hora_dominical");
			valorHoraDominical = rs.getDouble("valor_hora_dominical");
			horaFestivaDominical = rs.getDouble("hora_festiva_dominical");
			valorHoraFestivaDominical = rs.getDouble("valor_hora_festiva_dominical");
			horaExtraFestivaDominical = rs.getDouble("hora_extra_festiva_dominical");
			valorFestiva = rs.getDouble("valor_festiva");
			tipoAuxilio = rs.getString("tipo_auxilio");
			valorAuxilio = rs.getDouble("valor_auxilio");
			totalExtras = rs.getDouble("total_extras");
			subtotalDevengado = rs.getDouble("subtotal_devengado");
			deduccionSeguridadSocial = rs.getDouble("deduccion_seguridad_social");
			vales = rs.getDouble("vales");
			pizzaAhorro = rs.getDouble("pizza_ahorro");
			abonoAPrestamo = rs.getDouble("abono_a_prestamo");
			otros = rs.getString("otros");
			if(otros == null )
			{
				otros = "";
			}
			valorOtros = rs.getDouble("valor_otros");
			totalDeducciones = rs.getDouble("total_deducciones");
			totalAPagar = rs.getDouble("total_a_pagar");
			totalPagarLetras = rs.getString("total_pagar_letras");
			if(totalPagarLetras == null )
			{
				totalPagarLetras = "";
			}
			quincena = rs.getString("quincena");
			if(quincena == null )
			{
				quincena = "";
			}
			correoElectronico = rs.getString("correo_electronico");
			if(correoElectronico == null )
			{
				correoElectronico = "";
			}
			liqNomina = new LiquidacionNomina(liquidacionNominaId, apellido, nombre, cedula,  salarioBase,
					valorQuincena,  horaExtraOrd,  valorExtraOrd,  recargoNocturno,
					valorRecargoNocturno,  recargoHoraDominical,  valorHoraDominical,
					horaFestivaDominical, valorHoraFestivaDominical,  horaExtraFestivaDominical,
					valorFestiva, tipoAuxilio, valorAuxilio, totalExtras, subtotalDevengado,
					deduccionSeguridadSocial, vales, pizzaAhorro, abonoAPrestamo, otros,
					valorOtros, totalDeducciones, totalAPagar, totalPagarLetras, quincena,
					quincenaPago, correoElectronico);
			liquidacionNominas.add(liqNomina);
		}
		
		stm.close();
		con1.close();
	}
	catch (Exception e){
		logger.error(e.toString());
		try
		{
			con1.close();
		}catch(Exception e1)
		{
		}
	}
	return(liquidacionNominas);
}


public static ArrayList<LiquidacionNomina> retornarCertificado()
{
	Logger logger = Logger.getLogger("log_file");
	ConexionBaseDatos con = new ConexionBaseDatos();
	Connection con1 = con.obtenerConexionBDPrincipal();
	ArrayList<LiquidacionNomina> liquidacionNominas = new ArrayList();
	LiquidacionNomina liqNomina = new LiquidacionNomina();
	try
	{
		Statement stm = con1.createStatement();
		String consulta = "select * from  liquidacion_nomina"; 
		logger.info(consulta);
		ResultSet rs = stm.executeQuery(consulta);
		 int liquidacionNominaId;
		 String apellido;
		 String nombre;
		 String cedula;
		 double salarioBase;
		 double valorQuincena;
		 double horaExtraOrd;
		 double valorExtraOrd;
		 double recargoNocturno;
		 double valorRecargoNocturno;
		 double recargoHoraDominical;
		 double valorHoraDominical;
		 double horaFestivaDominical;
		 double valorHoraFestivaDominical;
		 double horaExtraFestivaDominical;
		 double valorFestiva;
		 String tipoAuxilio;
		 double valorAuxilio;
		 double totalExtras;
		 double subtotalDevengado;
		 double deduccionSeguridadSocial;
		 double vales;
		 double pizzaAhorro;
		 double abonoAPrestamo;
		 String otros;
		 double valorOtros;
		 double totalDeducciones;
		 double totalAPagar;
		 String totalPagarLetras;
		 String quincena;
		 String correoElectronico;
		 String tipo;
		 String cargo;
		 String vinculacion;
		while(rs.next()){
			liquidacionNominaId = rs.getInt("liquidacion_nomina_id");
			apellido = rs.getString("apellido");
			if(apellido == null )
			{
				apellido = "";
			}
			nombre = rs.getString("nombre");
			if(nombre == null )
			{
				nombre = "";
			}
			cedula = rs.getString("cedula");
			if(cedula == null )
			{
				cedula = "";
			}
			salarioBase = rs.getDouble("salario_base");
			valorQuincena = rs.getDouble("valor_quincena");
			horaExtraOrd = rs.getDouble("hora_extra_ord");
			valorExtraOrd = rs.getDouble("valor_extra_ord");
			recargoNocturno = rs.getDouble("recargo_nocturno");
			valorRecargoNocturno = rs.getDouble("valor_rec_nocturno");
			recargoHoraDominical = rs.getDouble("recargo_hora_dominical");
			valorHoraDominical = rs.getDouble("valor_hora_dominical");
			horaFestivaDominical = rs.getDouble("hora_festiva_dominical");
			valorHoraFestivaDominical = rs.getDouble("valor_hora_festiva_dominical");
			horaExtraFestivaDominical = rs.getDouble("hora_extra_festiva_dominical");
			valorFestiva = rs.getDouble("valor_festiva");
			tipoAuxilio = rs.getString("tipo_auxilio");
			valorAuxilio = rs.getDouble("valor_auxilio");
			totalExtras = rs.getDouble("total_extras");
			subtotalDevengado = rs.getDouble("subtotal_devengado");
			deduccionSeguridadSocial = rs.getDouble("deduccion_seguridad_social");
			vales = rs.getDouble("vales");
			pizzaAhorro = rs.getDouble("pizza_ahorro");
			abonoAPrestamo = rs.getDouble("abono_a_prestamo");
			otros = rs.getString("otros");
			if(otros == null )
			{
				otros = "";
			}
			valorOtros = rs.getDouble("valor_otros");
			totalDeducciones = rs.getDouble("total_deducciones");
			totalAPagar = rs.getDouble("total_a_pagar");
			totalPagarLetras = rs.getString("total_pagar_letras");
			if(totalPagarLetras == null )
			{
				totalPagarLetras = "";
			}
			quincena = rs.getString("quincena");
			if(quincena == null )
			{
				quincena = "";
			}
			correoElectronico = rs.getString("correo_electronico");
			if(correoElectronico == null )
			{
				correoElectronico = "";
			}
			tipo = rs.getString("tipo");
			cargo = rs.getString("cargo");
			vinculacion = rs.getString("vinculacion");
			liqNomina = new LiquidacionNomina(liquidacionNominaId, apellido, nombre, cedula,  salarioBase,
					valorQuincena,  horaExtraOrd,  valorExtraOrd,  recargoNocturno,
					valorRecargoNocturno,  recargoHoraDominical,  valorHoraDominical,
					horaFestivaDominical, valorHoraFestivaDominical,  horaExtraFestivaDominical,
					valorFestiva, tipoAuxilio, valorAuxilio, totalExtras, subtotalDevengado,
					deduccionSeguridadSocial, vales, pizzaAhorro, abonoAPrestamo, otros,
					valorOtros, totalDeducciones, totalAPagar, totalPagarLetras, quincena,
					quincena, correoElectronico, tipo, cargo, vinculacion);
			liquidacionNominas.add(liqNomina);
		}
		
		stm.close();
		con1.close();
	}
	catch (Exception e){
		logger.error(e.toString());
		try
		{
			con1.close();
		}catch(Exception e1)
		{
		}
	}
	return(liquidacionNominas);
}

}
