package capaDAO;
import conexion.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import capaModelo.Empleado;
import capaModelo.Nomina;

import java.sql.ResultSet;
import org.apache.log4j.Logger;
/**
 * Clase que se encarga de implementar todos aquellos métodos que tienen una interacción directa con la base de datos
 * @author JuanDavid
 *
 */
public class NominaDAO {
	
	
/**
 * Método que se encarga de insertar en base de datos la información de la entidad Especialidad
 * @param Espe recibe como parámetro un objeto Modelo Especialidad con base en el cual se realiza la inserción de la
 * especialidad.
 * @return Se retonra valor entero con el id de la especiliadad insertada.
 */
public static int insertarNomina(Nomina nomi)
{
	Logger logger = Logger.getLogger("log_file");
	int idNominaIns = 0;
	ConexionBaseDatos con = new ConexionBaseDatos();
	Connection con1 = con.obtenerConexionBDPrincipal();
	Date fechaTemporal = new Date();
	DateFormat formatoFinal = new SimpleDateFormat("yyyy-MM-dd");
	String fechaInicial ="";
	String fechaFinal = "";
	try
	{
		fechaTemporal = new SimpleDateFormat("dd/MM/yyyy").parse(nomi.getFechaInicial());
		fechaInicial = formatoFinal.format(fechaTemporal);
		
	}catch(Exception e){
		logger.error(e.toString());
		return(0);
	}
	try
	{
		fechaTemporal = new SimpleDateFormat("dd/MM/yyyy").parse(nomi.getFechaFinal());
		fechaFinal = formatoFinal.format(fechaTemporal);
		
	}catch(Exception e){
		logger.error(e.toString());
		return(0);
	}
	
	try
	{
		Statement stm = con1.createStatement();
		String insert = "insert into nomina (fecha_inicial, fecha_final) values ('" + fechaInicial + "' , '" + fechaFinal + "')"; 
		logger.info(insert);
		stm.executeUpdate(insert);
		ResultSet rs = stm.getGeneratedKeys();
		if (rs.next()){
			idNominaIns=rs.getInt(1);
			logger.info("idempleado insertada en bd " + idNominaIns);
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
		return(0);
	}
	return(idNominaIns);
}

/**
 * Método que se encarga de eliminar una especialidad en la base de datos.
 * @param idespecialidad Se recibe como parámetro el id especialidad qeu se desea eliminar.
 */
public static void eliminarNomina(int idnomina)
{
	Logger logger = Logger.getLogger("log_file");
	ConexionBaseDatos con = new ConexionBaseDatos();
	Connection con1 = con.obtenerConexionBDPrincipal();
	try
	{
		Statement stm = con1.createStatement();
		String delete = "delete from nomina  where idnomina = " + idnomina; 
		logger.info(delete);
		stm.executeUpdate(delete);
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
	
}

/**
 * Método que se encarga de retornar una especialidad dado un idespecialidad
 * @param idespecialidad recibe como parámetro un intero id especialidad y con base en esto, realiza la consulta
 * en base de datos y retorna la información.
 * @return Se retorna la información de la especialidad en un objeto Modelo Especialidad.
 */
public static Nomina retornarNomina(int idnomina)
{
	Logger logger = Logger.getLogger("log_file");
	ConexionBaseDatos con = new ConexionBaseDatos();
	Connection con1 = con.obtenerConexionBDPrincipal();
	Nomina nomi = new Nomina("","",0);
	try
	{
		Statement stm = con1.createStatement();
		String consulta = "select idnomina, fecha_inicial, fecha_final from  nomina  where idnomina = " + idnomina; 
		logger.info(consulta);
		ResultSet rs = stm.executeQuery(consulta);
		int idnomi = 0;
		String fechaInicial = "";
		String fechaFinal = "";
		while(rs.next()){
			idnomi = rs.getInt("idnomina");
			fechaInicial = rs.getString("fecha_inicial");
			fechaFinal = rs.getString("fecha_final");
			break;
		}
		nomi = new Nomina(fechaInicial, fechaFinal, idnomi);
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
	return(nomi);
}

public static ArrayList<Nomina> retornarNominas()
{
	Logger logger = Logger.getLogger("log_file");
	ConexionBaseDatos con = new ConexionBaseDatos();
	Connection con1 = con.obtenerConexionBDPrincipal();
	ArrayList<Nomina> nominas = new ArrayList();
	Nomina nomi = new Nomina("","",0);
	try
	{
		Statement stm = con1.createStatement();
		String consulta = "select idnomina, fecha_inicial, fecha_final from  nomina"; 
		logger.info(consulta);
		ResultSet rs = stm.executeQuery(consulta);
		int idnomi = 0;
		String fechaInicial = "";
		String fechaFinal = "";
		while(rs.next()){
			idnomi = rs.getInt("idnomina");
			fechaInicial = rs.getString("fecha_inicial");
			fechaFinal = rs.getString("fecha_final");
			nomi = new Nomina(fechaInicial, fechaFinal, idnomi);
			nominas.add(nomi);
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
	return(nominas);
}

/**
 * Método que tiene como objetivo modificar una especialidad.
 * @param Espe Recibe como parámetro un objeto Modelo Especiliadad con base en la cual se hará la modificación.
 * @return Se retorna un string indicadno si el proceso fue exitoso o no.
 */
public static String editarNomina(Nomina nomi)
{
	Logger logger = Logger.getLogger("log_file");
	ConexionBaseDatos con = new ConexionBaseDatos();
	Connection con1 = con.obtenerConexionBDPrincipal();
	String resultado = "";
	try
	{
		Statement stm = con1.createStatement();
		String update = "update nomina set fecha_inicial ='" + nomi.getFechaInicial() + "', fecha_final =  '" + nomi.getFechaFinal()  +  "' where idnomina = " + nomi.getIdnomina(); 
		logger.info(update);
		stm.executeUpdate(update);
		resultado = "exitoso";
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
		resultado = "error";
	}
	return(resultado);
}


}
