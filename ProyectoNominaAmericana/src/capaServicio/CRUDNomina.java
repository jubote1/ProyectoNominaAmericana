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

import capaControlador.EmpleadoCtrl;
import capaControlador.NominaCtrl;

/**
 * Servlet implementation class CRUDNomina
 */
@WebServlet("/CRUDNomina")
public class CRUDNomina extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CRUDNomina() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.addHeader("Access-Control-Allow-Origin", "*");	
		Logger logger = Logger.getLogger("log_file");
		HttpSession sesion = request.getSession();
		response.addHeader("Access-Control-Allow-Origin", "*");
		String idoperacion = request.getParameter("idoperacion");
		NominaCtrl NominaCtrl = new NominaCtrl();
		int operacion;
		String respuesta="";
		String fechaInicial = "";
		String fechaFinal = "";
		int idnomina = 0;
		try
		{
			operacion = Integer.parseInt(idoperacion);
		}catch(Exception e){
			operacion = 0;
		}
		
		if (operacion ==1)
		{
			fechaInicial = request.getParameter("fechainicial");
			fechaFinal  = request.getParameter("fechafinal");
			logger.info("insertar nomina fechainicial "+ fechaInicial + " fechafinal " + fechaFinal);
			respuesta = NominaCtrl.insertarNomina(fechaInicial, fechaFinal);
		}else if (operacion ==2)
		{
			idnomina= Integer.parseInt(request.getParameter("idnomina"));
			fechaInicial = request.getParameter("fechainicial");
			fechaFinal = request.getParameter("fechafinal");
			logger.info("editar nomina con parámetros fechainicial " + fechaInicial + " fechafinal " + fechaFinal);
			respuesta = NominaCtrl.editarNomina(idnomina, fechaInicial, fechaFinal);
		}else if (operacion ==3 )
		{
			idnomina = Integer.parseInt(request.getParameter("idnomina"));
			respuesta = NominaCtrl.eliminarNomina(idnomina);
		}else if (operacion == 4)
		{
			idnomina = Integer.parseInt(request.getParameter("idnomina"));
			respuesta = NominaCtrl.retornarNomina(idnomina);
		}else if(operacion == 5)
		{
			respuesta = NominaCtrl.retornarNominas();
		}
		System.out.println(respuesta);
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
