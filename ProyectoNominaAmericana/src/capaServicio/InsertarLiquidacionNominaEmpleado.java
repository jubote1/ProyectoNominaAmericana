package capaServicio;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import capaControlador.NominaCtrl;

/**
 * Servlet implementation class InsertarLiquidacionEmpleado
 */
@WebServlet("/InsertarLiquidacionNominaEmpleado")
public class InsertarLiquidacionNominaEmpleado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarLiquidacionNominaEmpleado() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");	
		Logger logger = Logger.getLogger("log_file");
		HttpSession sesion = request.getSession();
		response.addHeader("Access-Control-Allow-Origin", "*");
		int idnomina;
		int idempleado;
		int idconcepto;
		double valor;
		try
		{
			idnomina = Integer.parseInt(request.getParameter("idnomina"));
		}catch(Exception e)
		{
			logger.info("error en el idnomina");
			idnomina = 0;
		}
		try
		{
			idempleado = Integer.parseInt(request.getParameter("idempleado"));
		}catch(Exception e)
		{
			logger.info("error en el idempleado");
			idempleado = 0;
		}
		try
		{
			idconcepto = Integer.parseInt(request.getParameter("idconcepto"));
		}catch(Exception e)
		{
			logger.info("error en el idconcepto");
			idconcepto = 0;
		}
		try
		{
			valor = Double.parseDouble(request.getParameter("valor"));
		}catch(Exception e)
		{
			logger.info("error en el valor");
			valor = 0;
		}
		NominaCtrl NominaCtrl = new NominaCtrl();
		NominaCtrl.insertarLiquidacionNominaEmpleado(idnomina, idempleado, idconcepto, valor);
		PrintWriter out = response.getWriter();
		out.write("exitoso");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
