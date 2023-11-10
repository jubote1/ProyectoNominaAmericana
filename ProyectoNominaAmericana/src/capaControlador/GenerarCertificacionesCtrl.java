package capaControlador;

import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import capaDAO.GeneralDAO;
import capaDAO.LiquidacionNominaDAO;
import capaModelo.Correo;
import capaModelo.CorreoElectronico;
import capaModelo.LiquidacionNomina;
import utilidades.ControladorEnvioCorreo;

public class GenerarCertificacionesCtrl {
	
	public static void main(String args[])
	{
		GenerarCertificacionesCtrl liqNomina = new GenerarCertificacionesCtrl();
		liqNomina.enviarCorreoCertificacionesNomina();
	}
	
	public void enviarCorreoCertificacionesNomina()
	{
		//Obtenemos la liquidaci�n de n�mina del per�odo recibido como par�metro
		ArrayList<LiquidacionNomina> nominasLiquidadas = LiquidacionNominaDAO.retornarCertificado();
		int cantidadCorreosEnviados = 0;
		//Definici�n de temas del documento
		Document document = new Document(PageSize.LETTER , 36, 36, 54, 36);
        Paragraph parrafo, parrafo2, parrafo3, parrafo4, parrafo5;
        Font fuenteEncabezado= new Font(FontFamily.TIMES_ROMAN, 14, Font.BOLD);
        Font fuentePOEncabezado= new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        Font fuente= new Font(FontFamily.TIMES_ROMAN,10);
        String rutaArchivoCorreo = "";
        String[] rutasArchivos = new String[1];
		for(int i = 0;  i < nominasLiquidadas.size(); i++)
		{
			rutasArchivos = new String[1];
			LiquidacionNomina liqNomina = nominasLiquidadas.get(i);
			rutaArchivoCorreo = "C:\\reportes\\";
			rutasArchivos[0] = rutaArchivoCorreo;
			rutaArchivoCorreo = "C:\\reportes\\" + liqNomina.getCedula() +".pdf";
			rutaArchivoCorreo = rutaArchivoCorreo + "%&" + liqNomina.getCedula() +".pdf";
			rutasArchivos[0] = rutaArchivoCorreo;
			//Verificamos si tiene correo para realizar el env�o
			//Creamos los p�rrafos

			try
			{
				document = new Document(PageSize.LETTER , 36, 36, 54, 36);
				PdfWriter.getInstance(document, new FileOutputStream("C:\\reportes\\" + liqNomina.getCedula() + ".pdf"));
				Image imagen = Image.getInstance("C:\\reportes\\LogoPizzaAmericanapeque.png");
				Image imagenFirma = Image.getInstance("C:\\reportes\\ImagenFirma.png");
				document.open();
				imagen.setAlignment(Element.ALIGN_LEFT);
				//imagen.setAbsolutePosition(240f, 650f);
				document.add(imagen);
		        document.add( Chunk.NEWLINE );
		        document.add( Chunk.NEWLINE );
				parrafo = new Paragraph("CERTIFICADO LABORAL", fuenteEncabezado);
				parrafo.setAlignment(Element.ALIGN_CENTER);
				document.add(parrafo);
				document.add( Chunk.NEWLINE );
		        document.add( Chunk.NEWLINE );
				parrafo2 = new Paragraph("A QUIEN PUEDA INTERESAR", fuentePOEncabezado);
				parrafo2.setAlignment(Element.ALIGN_CENTER);
				document.add(parrafo2);
				document.add( Chunk.NEWLINE );
				parrafo3 = new Paragraph("De acuerdo al decreto 1076 del d�a 28 de Julio de 2020, por medio de la cual se imparten instrucciones en virtud de la emergencia sanitaria generada por la pandemia del coronavirus COVID-19 y el mantenimiento del orden p�blico, en los Municipios del �rea metropolitana del Valle de Aburra, en el departamento de Antioquia las excepciones que permiten la libre circulaci�n de los ciudadanos durante la contingencia sanitaria ocasionadas por virus COVID-19, en las cuales se autoriza la comercializaci�n de establecimiento gastron�micos mediante plataformas de comercio electr�nico o por entrega domicilio y el funcionamiento y operaci�n de centro de llamadas mediante que presente servicios a las plataformas de comercio electr�nico, todas estas actividades que son llevadas a cabo por parte de la empresa PIZZA AMERICANA S.A.S, registrada en el RUT, bajo el c�digo 5612 dedicada al autoservicio de comidas preparadas. ", fuente);
				parrafo3.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(parrafo3);
				document.add( Chunk.NEWLINE );
				parrafo4 = new Paragraph("");
				if (liqNomina.getTipo().equals(new String("Operativo")))
				{
					parrafo4 = new Paragraph("Motivo por el cual se expide la presente certificaci�n laboral, mediante la que se "
							+ "le informa a la autoridad correspondiente que la persona portadora de la presente certificaci�n " + liqNomina.getNombre() + " " + liqNomina.getApellido() + " identificada con " + liqNomina.getCedula() + ", "
							+ "labora para nuestra empresa desde " + liqNomina.getCargo() + ", con contrato a t�rmino indefinido, desempe�ando el "
							+ "cargo de " + liqNomina.getVinculacion() + " en el horario laboral comprendido entre las 11:30 a.m.  a las  11:45 p.m. "
							+ "( el tiempo de finalizaci�n del turno podr� extenderse por la alta demanda)  , de domingo a domingo "
							+ ",  con un d�a  compensatorio en semana,  dando cumplimiento de esta manera al par�grafo N�5 del art�culo N�3 del decreto anteriormente citado, el cual establece que �Las personas que desarrollen las actividades antes mencionadas deber�n estar acreditadas e identificadas en el ejercicio de sus funciones�.", fuente);
				}else
				{
					parrafo4 = new Paragraph("Motivo por el cual se expide la presente certificaci�n laboral, mediante la que se "
							+ "le informa a la autoridad correspondiente que la persona portadora de la presente certificaci�n " + liqNomina.getNombre() + " " + liqNomina.getApellido() + " identificada con " + liqNomina.getCedula() + ", "
							+ "labora para nuestra empresa desde " + liqNomina.getCargo() + ", con contrato a t�rmino indefinido, desempe�ando el "
							+ "cargo de " + liqNomina.getVinculacion() + " en el horario laboral comprendido entre las 6:30 a.m.  a las  11:00 p.m. "
							+ "( el tiempo de finalizaci�n del turno podr� extenderse por la alta demanda)  , de domingo a domingo "
							+ ",  con un d�a  compensatorio en semana,  dando cumplimiento de esta manera al par�grafo N�5 del art�culo N�3 del decreto anteriormente citado, el cual establece que �Las personas que desarrollen las actividades antes mencionadas deber�n estar acreditadas e identificadas en el ejercicio de sus funciones�.", fuente);
				}
				
				parrafo4.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(parrafo4);
				document.add( Chunk.NEWLINE );
				//Agregamos la �ltima parte
				parrafo5 = new Paragraph("Agradecemos la colaboraci�n prestada, si presenta alguna inquietud con gusto ser� atendida,"
						+ " en el tel�fono 4804669.", fuente); 
				parrafo5.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(parrafo5);
				document.add( Chunk.NEWLINE );
				parrafo5 = new Paragraph("Cordialmente", fuente); 
				parrafo5.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(parrafo5);
				document.add( Chunk.NEWLINE );
				imagenFirma.setAlignment(Element.ALIGN_LEFT);
				document.add(imagenFirma);
				document.add( Chunk.NEWLINE );
				parrafo5 = new Paragraph("ALEXANDER VIGOYA MURCIA", fuentePOEncabezado); 
				parrafo5.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(parrafo5);
				parrafo5 = new Paragraph("REPRESENTANTE LEGAL", fuente); 
				parrafo5.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(parrafo5);
				parrafo5 = new Paragraph("PIZZA AMERICANA SAS", fuente); 
				parrafo5.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(parrafo5);
				parrafo5 = new Paragraph("NIT: 901290745-1", fuente); 
				parrafo5.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(parrafo5);
				document.close();
				if(liqNomina.getCorreoElectronico().length() > 0)
				{
					cantidadCorreosEnviados++;
					CorreoElectronico infoCorreo = ControladorEnvioCorreo.recuperarCorreo("CUENTACORREONOMINA", "CLAVECORREONOMINA");
					Correo correo = new Correo();
					correo.setAsunto("Certificaci�n Para Movilidad 22-23 Agosto 2020" + liqNomina.getCedula());
					correo.setContrasena(infoCorreo.getClaveCorreo());
					ArrayList correos = new ArrayList();
					correos.add(new String(liqNomina.getCorreoElectronico()));
					correo.setUsuarioCorreo(infoCorreo.getCuentaCorreo());
					correo.setMensaje("Buen D�a " + liqNomina.getNombre() + " " + liqNomina.getApellido() + " , en este correo encontrar�s anexo tu certificado laboral con la autorizaci�n para tu desplazamiento, Recuerda que solo lo debes utilizar para desplazamientos relacionados con tu trabajo.");
					correo.setRutasArchivos(rutasArchivos);
					ControladorEnvioCorreo contro = new ControladorEnvioCorreo(correo, correos);
					contro.enviarCorreo();
					System.out.println("CORREO ENVIADO A " + liqNomina.getNombre() + " " + liqNomina.getApellido());
				}
			}catch(Exception e)
			{
				System.out.println(e.toString() + e.getMessage().toString());
			}
			
		}
		
		System.out.println("LA CANTIDAD DE CORREOS ENVIADOS FUERON: " + cantidadCorreosEnviados);
	}
	
}
