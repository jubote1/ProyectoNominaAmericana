package capaControlador;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import capaDAO.EmpleadoDAO;
import capaModelo.Empleado;
import java.util.*;

public class EmpleadoCtrl {
	
	//EMPLEADO
	
		/**
		 * Método en la capa controlador que recibe parámetros desde la capa de Presentación para la creación de especialidades
		 * @param nombre Nombre de la especialidad
		 * @param abreviatura Abreviatura de la especialidad
		 * @return Retorna el id de la especialidad creado y devuelvo por la base de datos
		 */
		public String insertarEmpleado(String nombreempleado, String identificacion, String telefono, String cuenta)
		{
			JSONArray listJSON = new JSONArray();
			Empleado Empe = new Empleado(0,nombreempleado, identificacion, telefono, cuenta);
			int idEmpIns = EmpleadoDAO.insertarEmpleado(Empe);
			JSONObject ResultadoJSON = new JSONObject();
			ResultadoJSON.put("idempleado", idEmpIns);
			listJSON.add(ResultadoJSON);
			return listJSON.toJSONString();
		}
		
		/**
		 * 
		 * @param idespecialidad Recibe como parámetro el idespecialidad que se va  a consultar
		 * @return Retorna en formato JSON la información de la especialidad de acuerdo al parámetro de idespecialidad.
		 */
		public String retornarEmpleado(int idempleado)
		{
			JSONArray listJSON = new JSONArray();
			Empleado Espe = EmpleadoDAO.retornarEmpleado(idempleado);
			JSONObject ResultadoJSON = new JSONObject();
			ResultadoJSON.put("idempleado", Espe.getIdempleado());
			ResultadoJSON.put("nombreempleado", Espe.getNombreempleado());
			ResultadoJSON.put("identificacion", Espe.getIdentificacion());
			ResultadoJSON.put("telefono", Espe.getTelefono());
			ResultadoJSON.put("cuenta", Espe.getCuenta());
			listJSON.add(ResultadoJSON);
			return listJSON.toJSONString();
		}
		
		public String retornarEmpleados()
		{
			JSONArray listJSON = new JSONArray();
			ArrayList<Empleado> empleados = EmpleadoDAO.retornarEmpleados();
			for(Empleado emple: empleados)
			{
				JSONObject ResultadoJSON = new JSONObject();
				ResultadoJSON.put("idempleado", emple.getIdempleado());
				ResultadoJSON.put("nombreempleado", emple.getNombreempleado());
				ResultadoJSON.put("identificacion", emple.getIdentificacion());
				ResultadoJSON.put("telefono", emple.getTelefono());
				ResultadoJSON.put("cuenta", emple.getCuenta());
				listJSON.add(ResultadoJSON);
			}
			return listJSON.toJSONString();
		}
		
		
		/**
		 * 
		 * @param idespecialidad Se pasa como parámetro el idespecialidad que desea se eliminado
		 * @return Se retorna el valor de exitoso en caso de que se elimine de manera exitosa la especialidad.
		 */
		public String eliminarEmpleado(int idempleado)
		{
			JSONArray listJSON = new JSONArray();
			EmpleadoDAO.eliminarEmpleado(idempleado);
			JSONObject ResultadoJSON = new JSONObject();
			ResultadoJSON.put("resultado", "exitoso");
			listJSON.add(ResultadoJSON);
			return listJSON.toJSONString();
		}
		
		/**
		 * 
		 * @param idespecialidad Parámetro con base en el cual se edita la especialidad.
		 * @param nombre Valor de nombre de la especialidad que va a ser editado.
		 * @param abreviatura  Valor abreviatura de la especialidad que va a  ser editado.
		 * @return Se retorna el valor resultado.
		 */
		public String editarEmpleado(int idempleado , String nombreempleado, String identificacion, String telefono, String cuenta)
		{
			JSONArray listJSON = new JSONArray();
			Empleado Empe = new Empleado(idempleado, nombreempleado, identificacion, telefono, cuenta);
			String resultado = EmpleadoDAO.editarEmpleado(Empe);
			JSONObject ResultadoJSON = new JSONObject();
			ResultadoJSON.put("resultado", resultado);
			listJSON.add(ResultadoJSON);
			return listJSON.toJSONString();
		}

}
