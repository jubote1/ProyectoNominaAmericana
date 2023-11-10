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
import capaDAO.LiquidacionIntCesantiasDAO;
import capaDAO.LiquidacionNominaDAO;
import capaDAO.LiquidacionPrimaDAO;
import capaModelo.Correo;
import capaModelo.CorreoElectronico;
import capaModelo.InteresesCesantias;
import capaModelo.LiquidacionNomina;
import capaModelo.LiquidacionPrima;
import utilidades.ControladorEnvioCorreo;

public class LiquidacionInteresesCesantias {
	
	public static void main(String args[])
	{
		LiquidacionInteresesCesantias liqNomina = new LiquidacionInteresesCesantias();
		liqNomina.enviarCorreoLiquidacionPrima();
	}
	
	public void enviarCorreoLiquidacionPrima()
	{
		//Obtenemos la liquidación de nómina del período recibido como parámetro
		ArrayList<InteresesCesantias> liquidacionIntCesantias = LiquidacionIntCesantiasDAO.retornarLiquidacionIntCesantias();
		int cantidadCorreosEnviados = 0;
		String rutaArchivoCorreo = "";
        String[] rutasArchivos = new String[1];
		for(int i = 0;  i < liquidacionIntCesantias.size(); i++)
		{
			InteresesCesantias intCesantia = liquidacionIntCesantias.get(i);
			rutasArchivos = new String[1];
			rutaArchivoCorreo = "C:\\reportes\\";
			rutasArchivos[0] = rutaArchivoCorreo;
			rutaArchivoCorreo = "C:\\reportes\\" + intCesantia.getCedula() +".pdf";
			rutaArchivoCorreo = rutaArchivoCorreo + "%&" + intCesantia.getCedula() +".pdf";
			rutasArchivos[0] = rutaArchivoCorreo;
			//Verificamos si tiene correo para realizar el envío
			if(intCesantia.getCorreo().length() > 0)
			{
				cantidadCorreosEnviados++;
				CorreoElectronico infoCorreo = ControladorEnvioCorreo.recuperarCorreo("CUENTACORREONOMINA", "CLAVECORREONOMINA");
				Correo correo = new Correo();
				correo.setAsunto("Liquidacion Intereses Cesantias " + intCesantia.getAno());
				correo.setContrasena(infoCorreo.getClaveCorreo());
				ArrayList correos = new ArrayList();
				correos.add(new String(intCesantia.getCorreo()));
				String strLiquidacionNomina = generarHtmlLiquidacionCesantia(intCesantia);
				//En este punto vamos a intervenir para generar un PDF con el HTML
				try 
				{
				    FileOutputStream file = new FileOutputStream(new File("C:\\reportes\\" + intCesantia.getCedula() + ".pdf"));
				    Document document = new Document();
				    Image imagen = Image.getInstance("C:\\reportes\\LogoPizzaAmericanapeque.png");
				    PdfWriter.getInstance(document, file);
				    document.open();
				    imagen.setAlignment(Element.ALIGN_LEFT);
					//imagen.setAbsolutePosition(240f, 650f);
					document.add(imagen);
				    HTMLWorker htmlWorker = new HTMLWorker(document);
				    htmlWorker.parse(new StringReader(strLiquidacionNomina));
				    document.close();
				    file.close();
				} catch (Exception e) {
				    e.printStackTrace();
				}
				correo.setUsuarioCorreo(infoCorreo.getCuentaCorreo());
				correo.setMensaje("Hola " + intCesantia.getNombre() + " " + intCesantia.getApellido() + " en el archivo anexo a continuación encontrarás la liquidación de tus intereses de cesantias para el año : " + intCesantia.getAno() + "\n");
				//correo.setMensaje("A continuación la liquidación de nomina : " + liqNomina.getQuincena() + "\n" + strLiquidacionNomina);
				correo.setRutasArchivos(rutasArchivos);
				ControladorEnvioCorreo contro = new ControladorEnvioCorreo(correo, correos);
				contro.enviarCorreo();
				System.out.println("CORREO ENVIADO A " + intCesantia.getNombre() + " " + intCesantia.getApellido());
			}
		}
		System.out.println("LA CANTIDAD DE CORREOS ENVIADOS FUERON: " + cantidadCorreosEnviados);
	}
	
	
	public String generarHtmlLiquidacionCesantia(InteresesCesantias intCesantia)
	{
		DecimalFormat formatea = new DecimalFormat("###,###");
		String respuesta = "";
		respuesta = respuesta + "<table border='2'> <tr><td colspan='3'><H1 align='center'> PIZZA AMERICANA S.A.S </H1></td> </tr>";
		respuesta = respuesta + "<tr><td colspan='3'>" + " Intereses de Cesantia "  + intCesantia.getAno() + "</td></tr>";
		respuesta = respuesta + "<tr>"
				+  "<td colspan='2'><strong>TOTAL A PAGAR</strong></td>"
				+  "<td><strong>"+ formatea.format(intCesantia.getValor()) +"</strong></td>"
				+  "</tr>";
		respuesta = respuesta + "<tr><td colspan='3'><strong>" + intCesantia.getValorLetras() + "</strong></td></tr>";
		respuesta = respuesta + "<tr><td colspan='3'><strong> EMPLEADO " + intCesantia.getApellido() + " " + intCesantia.getNombre() + " con identificacion " + intCesantia.getCedula() + "</strong></td></tr>";
		respuesta = respuesta + "</table> <br/>";
		return(respuesta);
	}

}
