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
@WebServlet("/ConsultarLiquidacionNomina")
public class ConsultarLiquidacionNomina extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarLiquidacionNomina() {
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
		try
		{
			idnomina = Integer.parseInt(request.getParameter("idnomina"));
		}catch(Exception e)
		{
			logger.info("error en el idnomina");
			idnomina = 0;
		}
		
		NominaCtrl NominaCtrl = new NominaCtrl();
		String respuesta = NominaCtrl.consultarLiquidacionNomina(idnomina);
		PrintWriter out = response.getWriter();
		out.write(respuesta);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
