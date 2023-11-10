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
import capaDAO.LiquidacionPrimaDAO;
import capaModelo.Correo;
import capaModelo.CorreoElectronico;
import capaModelo.LiquidacionNomina;
import capaModelo.LiquidacionPrima;
import utilidades.ControladorEnvioCorreo;

public class LiquidacionPrimaCtrl {
	
	public static void main(String args[])
	{
		LiquidacionPrimaCtrl liqNomina = new LiquidacionPrimaCtrl();
		liqNomina.enviarCorreoLiquidacionPrima();
	}
	
	public void enviarCorreoLiquidacionPrima()
	{
		//Obtenemos la liquidación de nómina del período recibido como parámetro
		ArrayList<LiquidacionPrima> primasLiquidadas = LiquidacionPrimaDAO.retornarLiquidacionPrima();
		int cantidadCorreosEnviados = 0;
		String rutaArchivoCorreo = "";
        String[] rutasArchivos = new String[1];
		for(int i = 0;  i < primasLiquidadas.size(); i++)
		{
			LiquidacionPrima liqPrima = primasLiquidadas.get(i);
			rutasArchivos = new String[1];
			rutaArchivoCorreo = "C:\\reportes\\";
			rutasArchivos[0] = rutaArchivoCorreo;
			rutaArchivoCorreo = "C:\\reportes\\" + liqPrima.getCedula() +".pdf";
			rutaArchivoCorreo = rutaArchivoCorreo + "%&" + liqPrima.getCedula() +".pdf";
			rutasArchivos[0] = rutaArchivoCorreo;
			//Verificamos si tiene correo para realizar el envío
			if(liqPrima.getCorreoElectronico().length() > 0)
			{
				cantidadCorreosEnviados++;
				CorreoElectronico infoCorreo = ControladorEnvioCorreo.recuperarCorreo("CUENTACORREONOMINA", "CLAVECORREONOMINA");
				Correo correo = new Correo();
				correo.setAsunto("Liquidacion Prima " + liqPrima.getSemestrePrima());
				correo.setContrasena(infoCorreo.getClaveCorreo());
				ArrayList correos = new ArrayList();
				correos.add(new String(liqPrima.getCorreoElectronico()));
				String strLiquidacionNomina = generarHtmlLiquidacionPrima(liqPrima);
				//En este punto vamos a intervenir para generar un PDF con el HTML
				try 
				{
				    FileOutputStream file = new FileOutputStream(new File("C:\\reportes\\" + liqPrima.getCedula() + ".pdf"));
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
				correo.setMensaje("Hola " + liqPrima.getNombre() + " " + liqPrima.getApellido() + " en el archivo anexo a continuación encontrarás la liquidación de tu Prima : " + liqPrima.getSemestrePrima() + "\n");
				//correo.setMensaje("A continuación la liquidación de nomina : " + liqNomina.getQuincena() + "\n" + strLiquidacionNomina);
				correo.setRutasArchivos(rutasArchivos);
				ControladorEnvioCorreo contro = new ControladorEnvioCorreo(correo, correos);
				contro.enviarCorreo();
				System.out.println("CORREO ENVIADO A " + liqPrima.getNombre() + " " + liqPrima.getApellido());
			}
		}
		System.out.println("LA CANTIDAD DE CORREOS ENVIADOS FUERON: " + cantidadCorreosEnviados);
	}
	
	
	public String generarHtmlLiquidacionPrima(LiquidacionPrima liqPrima)
	{
		DecimalFormat formatea = new DecimalFormat("###,###");
		String respuesta = "";
		respuesta = respuesta + "<table border='2'> <tr><td colspan='3'><H1 align='center'> PIZZA AMERICANA S.A.S </H1></td> </tr>";
		respuesta = respuesta + "<tr><td colspan='3'>"  + liqPrima.getSemestrePrima() + "</td></tr>";
		respuesta = respuesta + "<tr>"
				+  "<td colspan='2'><strong>TOTAL A PAGAR</strong></td>"
				+  "<td><strong>"+ formatea.format(liqPrima.getTotalaPagar()) +"</strong></td>"
				+  "</tr>";
		respuesta = respuesta + "<tr><td colspan='3'><strong>" + liqPrima.getTotalaPagarLetras() + "</strong></td></tr>";
		respuesta = respuesta + "<tr><td colspan='3'><strong> EMPLEADO " + liqPrima.getApellido() + " " + liqPrima.getNombre() + "</strong></td></tr>";
		respuesta = respuesta + "</table> <br/>";
		return(respuesta);
	}

}
