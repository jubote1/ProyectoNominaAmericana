package capaControlador;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import capaDAO.ConceptoNominaDAO;
import capaDAO.EmpleadoDAO;
import capaDAO.LiquidarNominaEmpleadoDAO;
import capaDAO.NominaDAO;
import capaModelo.ConceptoNomina;
import capaModelo.Empleado;
import capaModelo.LiquidacionNominaEmpleado;
import capaModelo.Nomina;
import java.util.ArrayList;

public class NominaCtrl {
	
	//EMPLEADO
	
		/**
		 * Método en la capa controlador que recibe parámetros desde la capa de Presentación para la creación de especialidades
		 * @param nombre Nombre de la especialidad
		 * @param abreviatura Abreviatura de la especialidad
		 * @return Retorna el id de la especialidad creado y devuelvo por la base de datos
		 */
		public String insertarNomina(String fechaInicial, String fechaFinal)
		{
			JSONArray listJSON = new JSONArray();
			Nomina nomi = new Nomina(fechaInicial, fechaFinal, 0);
			int idNomIns = NominaDAO.insertarNomina(nomi);
			JSONObject ResultadoJSON = new JSONObject();
			ResultadoJSON.put("idnomina", idNomIns);
			listJSON.add(ResultadoJSON);
			return listJSON.toJSONString();
		}
		
		public String insertarLiquidacionNominaEmpleado(int idnomina, int idempleado, int idconcepto, double valor)
		{
			JSONArray listJSON = new JSONArray();
			LiquidacionNominaEmpleado nomi = new LiquidacionNominaEmpleado(idnomina, idempleado, idconcepto, valor);
			LiquidarNominaEmpleadoDAO.insertarLiquidacionNominaEmpleado(nomi);
			JSONObject ResultadoJSON = new JSONObject();
			ResultadoJSON.put("resultado", "exitoso");
			listJSON.add(ResultadoJSON);
			return listJSON.toJSONString();
		}
		
		/**
		 * 
		 * @param idespecialidad Recibe como parámetro el idespecialidad que se va  a consultar
		 * @return Retorna en formato JSON la información de la especialidad de acuerdo al parámetro de idespecialidad.
		 */
		public String retornarNomina(int idnomina)
		{
			JSONArray listJSON = new JSONArray();
			Nomina nom = NominaDAO.retornarNomina(idnomina);
			JSONObject ResultadoJSON = new JSONObject();
			ResultadoJSON.put("idnomina", nom.getIdnomina());
			ResultadoJSON.put("fechainicial", nom.getFechaInicial());
			ResultadoJSON.put("fechafinal", nom.getFechaFinal());
			listJSON.add(ResultadoJSON);
			return listJSON.toJSONString();
		}
		
		/**
		 * 
		 * @param idespecialidad Recibe como parámetro el idespecialidad que se va  a consultar
		 * @return Retorna en formato JSON la información de la especialidad de acuerdo al parámetro de idespecialidad.
		 */
		public String retornarNominas()
		{
			JSONArray listJSON = new JSONArray();
			
			ArrayList<Nomina> nominas = NominaDAO.retornarNominas();
			for (Nomina nom : nominas)
			{
				JSONObject ResultadoJSON = new JSONObject();
				ResultadoJSON.put("idnomina", nom.getIdnomina());
				ResultadoJSON.put("fechainicial", nom.getFechaInicial());
				ResultadoJSON.put("fechafinal", nom.getFechaFinal());
				listJSON.add(ResultadoJSON);
			}
			return listJSON.toJSONString();
		}
		
		
		public String consultarLiquidacionNomina(int idnomina)
		{
			JSONArray listJSON = new JSONArray();
			
			ArrayList<LiquidacionNominaEmpleado> liquidaciones  = LiquidarNominaEmpleadoDAO.consultarLiquidacionNomina(idnomina);
			for (LiquidacionNominaEmpleado nom : liquidaciones)
			{
				JSONObject ResultadoJSON = new JSONObject();
				ResultadoJSON.put("idnomina", nom.getIdnomina());
				ResultadoJSON.put("fechasnomina", nom.getFechasNomina());
				ResultadoJSON.put("idempleado", nom.getIdempleado());
				ResultadoJSON.put("nombreempleado", nom.getNombreEmpleado());
				ResultadoJSON.put("idconcepto", nom.getIdconcpeto());
				ResultadoJSON.put("descripcion", nom.getDescripcionConcepto());
				ResultadoJSON.put("valor", nom.getValorConcepto());
				listJSON.add(ResultadoJSON);
			}
			return listJSON.toJSONString();
		}
		
		/**
		 * 
		 * @param idespecialidad Se pasa como parámetro el idespecialidad que desea se eliminado
		 * @return Se retorna el valor de exitoso en caso de que se elimine de manera exitosa la especialidad.
		 */
		public String eliminarNomina(int idnomina)
		{
			JSONArray listJSON = new JSONArray();
			NominaDAO.eliminarNomina(idnomina);
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
		public String editarNomina(int idnomina, String fechaInicial, String fechaFinal)
		{
			JSONArray listJSON = new JSONArray();
			Nomina nom = new Nomina(fechaInicial, fechaFinal, idnomina);
			String resultado = NominaDAO.editarNomina(nom);
			JSONObject ResultadoJSON = new JSONObject();
			ResultadoJSON.put("resultado", resultado);
			listJSON.add(ResultadoJSON);
			return listJSON.toJSONString();
		}

		//TODO PARA LOS CONCEPTOS DE NOMINA
		
		public String insertarConcepto(String descripcion)
		{
			JSONArray listJSON = new JSONArray();
			ConceptoNomina nomi = new ConceptoNomina(0,descripcion);
			int idConNomIns = ConceptoNominaDAO.insertarConcepto(nomi);
			JSONObject ResultadoJSON = new JSONObject();
			ResultadoJSON.put("idconcepto", idConNomIns);
			listJSON.add(ResultadoJSON);
			return listJSON.toJSONString();
		}
		
		/**
		 * 
		 * @param idespecialidad Recibe como parámetro el idespecialidad que se va  a consultar
		 * @return Retorna en formato JSON la información de la especialidad de acuerdo al parámetro de idespecialidad.
		 */
		public String retornarConcepto(int idconcepto)
		{
			JSONArray listJSON = new JSONArray();
			ConceptoNomina nom = ConceptoNominaDAO.retornarConcepto(idconcepto);
			JSONObject ResultadoJSON = new JSONObject();
			ResultadoJSON.put("idconcepto", nom.getIdconcepto());
			ResultadoJSON.put("descripcion", nom.getDescripcion());
			listJSON.add(ResultadoJSON);
			return listJSON.toJSONString();
		}
		
		/**
		 * 
		 * @param idespecialidad Recibe como parámetro el idespecialidad que se va  a consultar
		 * @return Retorna en formato JSON la información de la especialidad de acuerdo al parámetro de idespecialidad.
		 */
		public String retornarConceptos()
		{
			JSONArray listJSON = new JSONArray();
			
			ArrayList<ConceptoNomina> nominas = ConceptoNominaDAO.retornarConceptos();
			for (ConceptoNomina nom : nominas)
			{
				JSONObject ResultadoJSON = new JSONObject();
				ResultadoJSON.put("idconcepto", nom.getIdconcepto());
				ResultadoJSON.put("descripcion", nom.getDescripcion());
				listJSON.add(ResultadoJSON);
			}
			return listJSON.toJSONString();
		}
		
		/**
		 * 
		 * @param idespecialidad Se pasa como parámetro el idespecialidad que desea se eliminado
		 * @return Se retorna el valor de exitoso en caso de que se elimine de manera exitosa la especialidad.
		 */
		public String eliminarConcepto(int idconcepto)
		{
			JSONArray listJSON = new JSONArray();
			ConceptoNominaDAO.eliminarConcepto(idconcepto);;
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
		public String editarConcepto(int idconcepto, String descripcion)
		{
			JSONArray listJSON = new JSONArray();
			ConceptoNomina nom = new ConceptoNomina(idconcepto,descripcion);
			String resultado = ConceptoNominaDAO.editarConcepto(nom);
			JSONObject ResultadoJSON = new JSONObject();
			ResultadoJSON.put("resultado", resultado);
			listJSON.add(ResultadoJSON);
			return listJSON.toJSONString();
		}

}
