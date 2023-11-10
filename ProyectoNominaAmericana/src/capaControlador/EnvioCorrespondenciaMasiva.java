package capaControlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;

import capaDAO.GeneralDAO;
import capaDAO.LiquidacionNominaDAO;
import capaModelo.Correo;
import capaModelo.CorreoElectronico;
import capaModelo.LiquidacionNomina;
import utilidades.ControladorEnvioCorreo;

public class EnvioCorrespondenciaMasiva {
	
	public static void main(String args[])
	{
		EnvioCorrespondenciaMasiva liqNomina = new EnvioCorrespondenciaMasiva();
		liqNomina.enviarCorreoLiquidacionNomina("2020-09-I");
	}
	
	public void enviarCorreoLiquidacionNomina(String quincenaPago)
	{
		//Obtenemos la liquidación de nómina del período recibido como parámetro
		ArrayList<LiquidacionNomina> nominasLiquidadas = LiquidacionNominaDAO.retornarLiquidacionNomina(quincenaPago);
		int cantidadCorreosEnviados = 0;
		String rutaArchivoCorreo = "";
        String[] rutasArchivos = new String[1];
		for(int i = 0;  i < nominasLiquidadas.size(); i++)
		{
			LiquidacionNomina liqNomina = nominasLiquidadas.get(i);
			rutasArchivos = new String[1];
			rutaArchivoCorreo = "C:\\reportes\\";
			rutasArchivos[0] = rutaArchivoCorreo;
			rutaArchivoCorreo = "C:\\reportes\\CIRCULAR INCAP 2020 SEP.pdf";
			rutaArchivoCorreo = rutaArchivoCorreo + "%&CIRCULAR INCAP 2020 SEP.pdf";
			rutasArchivos[0] = rutaArchivoCorreo;
			//Verificamos si tiene correo para realizar el envío
			if(liqNomina.getCorreoElectronico().length() > 0)
			{
				cantidadCorreosEnviados++;
				CorreoElectronico infoCorreo = ControladorEnvioCorreo.recuperarCorreo("CUENTACORREONOMINA", "CLAVECORREONOMINA");
				Correo correo = new Correo();
				correo.setAsunto("Circular Incapacidades Septiembre 2020");
				correo.setContrasena(infoCorreo.getClaveCorreo());
				ArrayList correos = new ArrayList();
				correos.add(new String(liqNomina.getCorreoElectronico()));
				correo.setUsuarioCorreo(infoCorreo.getCuentaCorreo());
				correo.setMensaje("Hola " + liqNomina.getNombre() + " " + liqNomina.getApellido() + " en el archivo anexo a continuación encontrarás información de tu interés." + "\n");
				//correo.setMensaje("A continuación la liquidación de nomina : " + liqNomina.getQuincena() + "\n" + strLiquidacionNomina);
				correo.setRutasArchivos(rutasArchivos);
				ControladorEnvioCorreo contro = new ControladorEnvioCorreo(correo, correos);
				contro.enviarCorreo();
				System.out.println("CORREO ENVIADO A " + liqNomina.getNombre() + " " + liqNomina.getApellido());
			}
		}
		System.out.println("LA CANTIDAD DE CORREOS ENVIADOS FUERON: " + cantidadCorreosEnviados);
	}
	
	
	public String generarHtmlLiquidacionNomina(LiquidacionNomina liqNomina)
	{
		DecimalFormat formatea = new DecimalFormat("###,###");
		String respuesta = "";
		respuesta = respuesta + "<table border='2'> <tr><td colspan='3'><H1 align='center'> PIZZA AMERICANA S.A.S </H1></td> </tr>";
		respuesta = respuesta + "<tr><td colspan='3'>" + "QUINCENA:" + liqNomina.getQuincena() + "</td></tr>";
		respuesta = respuesta + "<tr>"
				+  "<td><strong>CONCEPTO</strong></td>"
				+  "<td><strong>HORAS</strong></td>"
				+  "<td><strong>TOTAL</strong></td>"
				+  "</tr>";
		respuesta = respuesta + "<tr>"
				+  "<td colspan='2'>SUELDO</td>"
				+  "<td>"+ formatea.format(liqNomina.getSalarioBase()) +"</td>"
				+  "</tr>";
		respuesta = respuesta + "<tr>"
				+  "<td>HORA EXTRA ORDINARIA</td>"
				+  "<td>" + liqNomina.getHoraExtraOrd() + "</td>"
				+  "<td>"+ formatea.format(liqNomina.getValorExtraOrd())+"</td>"
				+  "</tr>";
		respuesta = respuesta + "<tr>"
				+  "<td>RECARGO NOCTURNO</td>"
				+  "<td>" + liqNomina.getRecargoNocturno() + "</td>"
				+  "<td>"+ formatea.format(liqNomina.getValorRecargoNocturno())+"</td>"
				+  "</tr>";
		respuesta = respuesta + "<tr>"
				+  "<td>RECARGO HORA DOMINICAL</td>"
				+  "<td>" + liqNomina.getRecargoHoraDominical()+ "</td>"
				+  "<td>"+ formatea.format(liqNomina.getValorHoraDominical())+"</td>"
				+  "</tr>";
		respuesta = respuesta + "<tr>"
				+  "<td>HORA FESTIVA DOMINICAL</td>"
				+  "<td>" + liqNomina.getHoraFestivaDominical()+ "</td>"
				+  "<td>"+ formatea.format(liqNomina.getValorHoraFestivaDominical())+"</td>"
				+  "</tr>";
		respuesta = respuesta + "<tr>"
				+  "<td>HORA EXTRA FESTIVA DOMINICAL</td>"
				+  "<td>" + liqNomina.getHoraExtraFestivaDominical()+ "<"
						+ "/td>"
				+  "<td>"+ formatea.format(liqNomina.getValorFestiva())+"</td>"
				+  "</tr>";
		respuesta = respuesta + "<tr>"
				+  "<td colspan='2'>TOTAL EXTRAS</td>"
				+  "<td>"+ liqNomina.getTotalExtras() +"</td>"
				+  "</tr>";
		respuesta = respuesta + "<tr>"
				+  "<td colspan='2'><strong>SUBTOTAL DEVENGADO(Total Extras + Sueldo)</strong></td>"
				+  "<td>"+ formatea.format(liqNomina.getSubtotalDevengado()) +"</td>"
				+  "</tr>";
		respuesta = respuesta + "<tr><td colspan='3'></td></tr>";
		String tipoAuxilio = "";
		if(liqNomina.getTipoAuxilio() == null)
		{
			tipoAuxilio = "";
		}else
		{
			tipoAuxilio = liqNomina.getTipoAuxilio();
		}
		respuesta = respuesta + "<tr>"
				+  "<td><strong>AUXILIO</strong></td>"
				+  "<td><strong>" + tipoAuxilio + "</strong></td>"
				+  "<td>"+ formatea.format(liqNomina.getValorAuxilio()) +"</td>"
				+  "</tr>";
		respuesta = respuesta + "<tr><td colspan='3'><strong>DEDUCCIONES</strong></td></tr>";
		respuesta = respuesta + "<tr>"
				+  "<td colspan='2' >APORTES SEGURIDAD SOCIAL</td>"
				+  "<td>"+ formatea.format(liqNomina.getDeduccionSeguridadSocial()) +"</td>"
				+  "</tr>";
		respuesta = respuesta + "<tr>"
				+  "<td colspan='2'>VALES</td>"
				+  "<td>"+ formatea.format(liqNomina.getVales()) +"</td>"
				+  "</tr>";
		respuesta = respuesta + "<tr>"
				+  "<td colspan='2'>PIZZA AHORRO</td>"
				+  "<td>"+ formatea.format(liqNomina.getPizzaAhorro()) +"</td>"
				+  "</tr>";
		respuesta = respuesta + "<tr>"
				+  "<td colspan='2'>ABONO A PRÉSTAMO</td>"
				+  "<td>"+ formatea.format(liqNomina.getAbonoAPrestamo()) +"</td>"
				+  "</tr>";
		respuesta = respuesta + "<tr>"
				+  "<td colspan='2'>OTROS" + liqNomina.getOtros() + "</td>"
				+  "<td>"+ formatea.format(liqNomina.getValorOtros())+"</td>"
				+  "</tr>";
		respuesta = respuesta + "<tr>"
				+  "<td colspan='2'>TOTAL DEDUCCIONES</td>"
				+  "<td><strong>"+ formatea.format(liqNomina.getTotalDeducciones()) +"</strong></td>"
				+  "</tr>";
		respuesta = respuesta + "<tr>"
				+  "<td colspan='2'><strong>TOTAL A PAGAR(Subtotal devengado + Auxilio de transporte - Total devengado)</strong></td>"
				+  "<td><strong>"+ formatea.format(liqNomina.getTotalAPagar()) +"</strong></td>"
				+  "</tr>";
		respuesta = respuesta + "<tr><td colspan='3'><strong>" + liqNomina.getTotalPagarLetras() + "</strong></td></tr>";
		respuesta = respuesta + "<tr><td colspan='3'><strong> EMPLEADO " + liqNomina.getApellido() + " " + liqNomina.getNombre() + "</strong></td></tr>";
		respuesta = respuesta + "</table> <br/>";
		return(respuesta);
	}

}
