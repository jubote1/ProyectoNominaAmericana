package capaServicio;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import capaControlador.EmpleadoCtrl;
import capaControlador.NominaCtrl;

import org.apache.log4j.Logger;
/**
 * Servlet implementation class CRUDEspecialidad
 * Servicio que encarga de implementar los servicios CRUD para la entidad Especialidad.
 */
@WebServlet("/CRUDConcepto")
public class CRUDConcepto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CRUDConcepto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Este servicio recibe un idoperación que puede ser 1 insertar 2 editar 3 Eliminar  4 Consultar
	 * dependiendo el valor de idoperacion, se recibirán los diferentes parámetros de la entidad especialidad.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//Operación idoperacion 1 insertar 2 editar 3 Eliminar  4 Consultar
			response.addHeader("Access-Control-Allow-Origin", "*");	
			Logger logger = Logger.getLogger("log_file");
			HttpSession sesion = request.getSession();
			response.addHeader("Access-Control-Allow-Origin", "*");
			String idoperacion = request.getParameter("idoperacion");
			NominaCtrl NominaCtrl = new NominaCtrl();
			int operacion;
			String respuesta="";
			int idconcepto = 0;
			try
			{
				operacion = Integer.parseInt(idoperacion);
			}catch(Exception e){
				operacion = 0;
			}
			if (operacion ==1)
			{
				String descripcion = request.getParameter("descripcion");
				logger.info("insertar concept con parametros descripcion "+ descripcion);
				respuesta = NominaCtrl.insertarConcepto(descripcion);
			}else if (operacion ==2)
			{
				idconcepto= Integer.parseInt(request.getParameter("idconcepto"));
				String descripcion = request.getParameter("descripcion");
				logger.info("editar concepto con parámetros idconcepto " + idconcepto + " descripcion " + descripcion);
				respuesta = NominaCtrl.editarConcepto(idconcepto, descripcion);
			}else if (operacion ==3 )
			{
				idconcepto = Integer.parseInt(request.getParameter("idconcepto"));
				respuesta = NominaCtrl.eliminarConcepto(idconcepto);
			}else if (operacion == 4)
			{
				idconcepto = Integer.parseInt(request.getParameter("idconcepto"));
				respuesta = NominaCtrl.retornarConcepto(idconcepto);
			}else if(operacion == 5)
			{
				respuesta = NominaCtrl.retornarConceptos();
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
