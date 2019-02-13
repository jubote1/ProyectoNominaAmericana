package capaDAO;
import conexion.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import capaModelo.Empleado;
import java.sql.ResultSet;
import org.apache.log4j.Logger;
/**
 * Clase que se encarga de implementar todos aquellos métodos que tienen una interacción directa con la base de datos
 * @author JuanDavid
 *
 */
public class EmpleadoDAO {
	
	
/**
 * Método que se encarga de insertar en base de datos la información de la entidad Especialidad
 * @param Espe recibe como parámetro un objeto Modelo Especialidad con base en el cual se realiza la inserción de la
 * especialidad.
 * @return Se retonra valor entero con el id de la especiliadad insertada.
 */
public static int insertarEmpleado(Empleado Empe)
{
	Logger logger = Logger.getLogger("log_file");
	int idEmpleadoIns = 0;
	ConexionBaseDatos con = new ConexionBaseDatos();
	Connection con1 = con.obtenerConexionBDPrincipal();
	try
	{
		Statement stm = con1.createStatement();
		String insert = "insert into empleado (nombre_empleado, identificacion, telefono, cuenta) values ('" + Empe.getNombreempleado() + "', '" +Empe.getIdentificacion() + "' , '" + Empe.getTelefono() + "' , '" + Empe.getCuenta() + "')"; 
		logger.info(insert);
		stm.executeUpdate(insert);
		ResultSet rs = stm.getGeneratedKeys();
		if (rs.next()){
			idEmpleadoIns=rs.getInt(1);
			logger.info("idempleado insertada en bd " + idEmpleadoIns);
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
	return(idEmpleadoIns);
}

/**
 * Método que se encarga de eliminar una especialidad en la base de datos.
 * @param idespecialidad Se recibe como parámetro el id especialidad qeu se desea eliminar.
 */
public static void eliminarEmpleado(int idempleado)
{
	Logger logger = Logger.getLogger("log_file");
	ConexionBaseDatos con = new ConexionBaseDatos();
	Connection con1 = con.obtenerConexionBDPrincipal();
	try
	{
		Statement stm = con1.createStatement();
		String delete = "delete from empleado  where idempleado = " + idempleado; 
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
public static Empleado retornarEmpleado(int idempleado)
{
	Logger logger = Logger.getLogger("log_file");
	int idEspecialidadEli = 0;
	ConexionBaseDatos con = new ConexionBaseDatos();
	Connection con1 = con.obtenerConexionBDPrincipal();
	Empleado Empe = new Empleado(0,"", "","", "");
	try
	{
		Statement stm = con1.createStatement();
		String consulta = "select idempleado, nombre_empleado, identificacion, telefono, cuenta from  empleado  where idempleado = " + idempleado; 
		logger.info(consulta);
		ResultSet rs = stm.executeQuery(consulta);
		int idemple = 0;
		String nombr = "";
		String ident = "";
		String tele = "";
		String cuen = "";
		while(rs.next()){
			idemple = rs.getInt("idempleado");
			nombr = rs.getString("nombre_empleado");
			ident = rs.getString("identificacion");
			tele = rs.getString("telefono");
			cuen = rs.getString("cuenta");
			break;
		}
		Empe = new Empleado(idemple, nombr, ident, tele,cuen);
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
	return(Empe);
}

public static ArrayList<Empleado> retornarEmpleados()
{
	Logger logger = Logger.getLogger("log_file");
	ConexionBaseDatos con = new ConexionBaseDatos();
	Connection con1 = con.obtenerConexionBDPrincipal();
	Empleado Empe = new Empleado(0,"", "","", "");
	ArrayList<Empleado> empleados = new ArrayList();
	try
	{
		Statement stm = con1.createStatement();
		String consulta = "select idempleado, nombre_empleado, identificacion, telefono, cuenta from  empleado"; 
		logger.info(consulta);
		ResultSet rs = stm.executeQuery(consulta);
		int idemple = 0;
		String nombr = "";
		String ident = "";
		String tele = "";
		String cuen = "";
		while(rs.next()){
			idemple = rs.getInt("idempleado");
			nombr = rs.getString("nombre_empleado");
			ident = rs.getString("identificacion");
			tele = rs.getString("telefono");
			cuen = rs.getString("cuenta");
			Empe = new Empleado(idemple, nombr, ident, tele,cuen);
			empleados.add(Empe);
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
	return(empleados);
}

/**
 * Método que tiene como objetivo modificar una especialidad.
 * @param Espe Recibe como parámetro un objeto Modelo Especiliadad con base en la cual se hará la modificación.
 * @return Se retorna un string indicadno si el proceso fue exitoso o no.
 */
public static String editarEmpleado(Empleado Emple)
{
	Logger logger = Logger.getLogger("log_file");
	ConexionBaseDatos con = new ConexionBaseDatos();
	Connection con1 = con.obtenerConexionBDPrincipal();
	String resultado = "";
	try
	{
		Statement stm = con1.createStatement();
		String update = "update empleado set nombre_empleado ='" + Emple.getNombreempleado() + "', identificacion =  '" +Emple.getIdentificacion()  + "', telefono =  '" +Emple.getTelefono()  + "', cuenta =  '" +Emple.getCuenta() +  "' where idempleado = " + Emple.getIdempleado(); 
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
