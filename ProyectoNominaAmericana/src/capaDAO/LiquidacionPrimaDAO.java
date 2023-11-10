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
import capaModelo.LiquidacionPrima;
import capaModelo.Nomina;

import java.sql.ResultSet;
import org.apache.log4j.Logger;
/**
 * Clase que se encarga de implementar todos aquellos métodos que tienen una interacción directa con la base de datos
 * @author JuanDavid
 *
 */
public class LiquidacionPrimaDAO {
	
	
public static ArrayList<LiquidacionPrima> retornarLiquidacionPrima()
{
	Logger logger = Logger.getLogger("log_file");
	ConexionBaseDatos con = new ConexionBaseDatos();
	Connection con1 = con.obtenerConexionBDPrincipal();
	ArrayList<LiquidacionPrima> liquidacionPrimas = new ArrayList();
	LiquidacionPrima liqPrima = new LiquidacionPrima();
	try
	{
		Statement stm = con1.createStatement();
		String consulta = "select * from  liquidacion_prima "; 
		logger.info(consulta);
		ResultSet rs = stm.executeQuery(consulta);
		int liquidacionPrimaId;
		String apellido;
		String nombre;
		String cedula;
		double totalaPagar;
		String totalaPagarLetras;
		String semestrePrima;
		String correoElectronico;
		while(rs.next()){
			liquidacionPrimaId = rs.getInt("liquidacion_prima_id");
			apellido = rs.getString("apellido");
			nombre = rs.getString("nombre");
			cedula = rs.getString("cedula");
			totalaPagar = rs.getDouble("total_a_pagar");
			totalaPagarLetras = rs.getString("total_a_pagar_letras");
			apellido = rs.getString("apellido");
			semestrePrima = rs.getString("semestre_prima");
			correoElectronico = rs.getString("correo");
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
			liqPrima = new LiquidacionPrima(liquidacionPrimaId,apellido, nombre, cedula, totalaPagar,totalaPagarLetras, semestrePrima, correoElectronico);
			liquidacionPrimas.add(liqPrima);
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
	return(liquidacionPrimas);
}

}
