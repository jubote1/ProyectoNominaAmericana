package capaDAO;
import conexion.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import capaModelo.ConceptoNomina;
import capaModelo.Empleado;
import capaModelo.Nomina;

import java.sql.ResultSet;
import org.apache.log4j.Logger;
/**
 * Clase que se encarga de implementar todos aquellos métodos que tienen una interacción directa con la base de datos
 * @author JuanDavid
 *
 */
public class ConceptoNominaDAO {
	
	
/**
 * Método que se encarga de insertar en base de datos la información de la entidad Especialidad
 * @param Espe recibe como parámetro un objeto Modelo Especialidad con base en el cual se realiza la inserción de la
 * especialidad.
 * @return Se retonra valor entero con el id de la especiliadad insertada.
 */
public static int insertarConcepto(ConceptoNomina nomi)
{
	Logger logger = Logger.getLogger("log_file");
	int idConceptoNominaIns = 0;
	ConexionBaseDatos con = new ConexionBaseDatos();
	Connection con1 = con.obtenerConexionBDPrincipal();
	try
	{
		Statement stm = con1.createStatement();
		String insert = "insert into concepto_nomina (descripcion) values ('" + nomi.getDescripcion()  + "')"; 
		logger.info(insert);
		stm.executeUpdate(insert);
		ResultSet rs = stm.getGeneratedKeys();
		if (rs.next()){
			idConceptoNominaIns=rs.getInt(1);
			logger.info("id concepto nómina insertada en bd " + idConceptoNominaIns);
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
	return(idConceptoNominaIns);
}

/**
 * Método que se encarga de eliminar una especialidad en la base de datos.
 * @param idespecialidad Se recibe como parámetro el id especialidad qeu se desea eliminar.
 */
public static void eliminarConcepto(int idconcepto)
{
	Logger logger = Logger.getLogger("log_file");
	ConexionBaseDatos con = new ConexionBaseDatos();
	Connection con1 = con.obtenerConexionBDPrincipal();
	try
	{
		Statement stm = con1.createStatement();
		String delete = "delete from concepto_nomina  where idconcepto = " + idconcepto; 
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
public static ConceptoNomina retornarConcepto(int idconcepto)
{
	Logger logger = Logger.getLogger("log_file");
	ConexionBaseDatos con = new ConexionBaseDatos();
	Connection con1 = con.obtenerConexionBDPrincipal();
	ConceptoNomina nomi = new ConceptoNomina(0,"");
	try
	{
		Statement stm = con1.createStatement();
		String consulta = "select idconcepto, descripcion from  concepto_nomina  where idconcepto = " + idconcepto; 
		logger.info(consulta);
		ResultSet rs = stm.executeQuery(consulta);
		int idconce = 0;
		String descripcion = "";
		while(rs.next()){
			idconce = rs.getInt("idconcepto");
			descripcion = rs.getString("descripcion");
			break;
		}
		nomi = new ConceptoNomina(idconce, descripcion);
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

public static ArrayList<ConceptoNomina> retornarConceptos()
{
	Logger logger = Logger.getLogger("log_file");
	ConexionBaseDatos con = new ConexionBaseDatos();
	Connection con1 = con.obtenerConexionBDPrincipal();
	ArrayList<ConceptoNomina> ConceptosConominas = new ArrayList();
	ConceptoNomina nomi = new ConceptoNomina(0, "");
	try
	{
		Statement stm = con1.createStatement();
		String consulta = "select idconcepto, descripcion from  concepto_nomina"; 
		logger.info(consulta);
		ResultSet rs = stm.executeQuery(consulta);
		int idconce = 0;
		String descripcion = "";
		while(rs.next()){
			idconce = rs.getInt("idconcepto");
			descripcion = rs.getString("descripcion");
			nomi = new ConceptoNomina(idconce,descripcion);
			ConceptosConominas.add(nomi);
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
	return(ConceptosConominas);
}

/**
 * Método que tiene como objetivo modificar una especialidad.
 * @param Espe Recibe como parámetro un objeto Modelo Especiliadad con base en la cual se hará la modificación.
 * @return Se retorna un string indicadno si el proceso fue exitoso o no.
 */
public static String editarConcepto(ConceptoNomina nomi)
{
	Logger logger = Logger.getLogger("log_file");
	ConexionBaseDatos con = new ConexionBaseDatos();
	Connection con1 = con.obtenerConexionBDPrincipal();
	String resultado = "";
	try
	{
		Statement stm = con1.createStatement();
		String update = "update concepto_nomina set descripcion ='" + nomi.getDescripcion() +   "' where idconcepto = " + nomi.getIdconcepto(); 
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
