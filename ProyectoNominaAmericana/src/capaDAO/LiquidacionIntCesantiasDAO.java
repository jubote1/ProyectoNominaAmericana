package capaDAO;
import conexion.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import capaModelo.Empleado;
import capaModelo.InteresesCesantias;
import capaModelo.LiquidacionNomina;
import capaModelo.LiquidacionPrima;
import capaModelo.Nomina;

import java.sql.ResultSet;
import org.apache.log4j.Logger;
/**
 * Clase que se encarga de implementar todos aquellos métodos que tienen una interacción directa con la base de datos
 * @author JuanDavid
 *
 */
public class LiquidacionIntCesantiasDAO {
	
	
public static ArrayList<InteresesCesantias> retornarLiquidacionIntCesantias()
{
	Logger logger = Logger.getLogger("log_file");
	ConexionBaseDatos con = new ConexionBaseDatos();
	Connection con1 = con.obtenerConexionBDPrincipal();
	ArrayList<InteresesCesantias> liqIntCesantias = new ArrayList();
	InteresesCesantias intCesantias = new InteresesCesantias();
	try
	{
		Statement stm = con1.createStatement();
		String consulta = "select * from  intereses_cesantias"; 
		logger.info(consulta);
		ResultSet rs = stm.executeQuery(consulta);
		int interesesCesantiasId;
		String apellido;
		String nombre;
		String cedula;
		String tipo;
		double valor;
		String valorLetras;
		String ano;
		String correo;
		while(rs.next()){
			interesesCesantiasId = rs.getInt("intereses_cesantias_id");
			apellido = rs.getString("apellido");
			nombre = rs.getString("nombre");
			tipo = rs.getString("tipo");
			cedula = rs.getString("cedula");
			valor = rs.getDouble("valor");
			valorLetras = rs.getString("valorletras");
			apellido = rs.getString("apellido");
			ano = rs.getString("ano");
			correo = rs.getString("correo");
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
			intCesantias = new InteresesCesantias(interesesCesantiasId, apellido, nombre, tipo, cedula,
			valor, valorLetras, ano, correo);
			liqIntCesantias.add(intCesantias);
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
	return(liqIntCesantias);
}

}
