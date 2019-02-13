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

import org.apache.log4j.Logger;
/**
 * Servlet implementation class CRUDEspecialidad
 * Servicio que encarga de implementar los servicios CRUD para la entidad Especialidad.
 */
@WebServlet("/CRUDEmpleado")
public class CRUDEmpleado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CRUDEmpleado() {
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
			EmpleadoCtrl EmpleadoCtrl = new EmpleadoCtrl();
			int operacion;
			String respuesta="";
			int idempleado = 0;
			try
			{
				operacion = Integer.parseInt(idoperacion);
			}catch(Exception e){
				operacion = 0;
			}
			if (operacion ==1)
			{
				String nombreempleado = request.getParameter("nombreempleado");
				String identificacion = request.getParameter("identificacion");
				String telefono = request.getParameter("telefono");
				String cuenta = request.getParameter("cuenta");
				logger.info("insertar empleado con parametros nombre "+ nombreempleado + " identificacion " + identificacion);
				respuesta = EmpleadoCtrl.insertarEmpleado(nombreempleado, identificacion, telefono, cuenta);
			}else if (operacion ==2)
			{
				idempleado= Integer.parseInt(request.getParameter("idempleado"));
				String nombredit= request.getParameter("nombreempleado");
				String idenedit = request.getParameter("identificacion");
				String tele = request.getParameter("telefono");
				String cuen = request.getParameter("cuenta");
				logger.info("editar empleado con parámetros idempleado " + idempleado + " nombre " + nombredit  + " identificacion " + idenedit);
				respuesta = EmpleadoCtrl.editarEmpleado(idempleado,nombredit, idenedit, tele, cuen);
			}else if (operacion ==3 )
			{
				idempleado = Integer.parseInt(request.getParameter("idempleado"));
				respuesta = EmpleadoCtrl.eliminarEmpleado(idempleado);
			}else if (operacion == 4)
			{
				idempleado = Integer.parseInt(request.getParameter("idempleado"));
				respuesta = EmpleadoCtrl.retornarEmpleado(idempleado);
			}else if(operacion == 5)
			{
				respuesta = EmpleadoCtrl.retornarEmpleados();
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
