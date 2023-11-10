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

public class GenerarCertificacionesOperCtrl {
	
	public static void main(String args[])
	{
		GenerarCertificacionesOperCtrl liqNomina = new GenerarCertificacionesOperCtrl();
		liqNomina.enviarCorreoCertificacionesNomina();
	}
	
	public void enviarCorreoCertificacionesNomina()
	{
		//Obtenemos la liquidación de nómina del período recibido como parámetro
		ArrayList<LiquidacionNomina> nominasLiquidadas = LiquidacionNominaDAO.retornarCertificado();
		int cantidadCorreosEnviados = 0;
		//Definición de temas del documento
		Document document = new Document(PageSize.LETTER , 36, 36, 54, 36);
        Paragraph parrafo, parrafo2, parrafo3, parrafo4, parrafo5, parrafo6;
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
			//Verificamos si tiene correo para realizar el envío
			//Creamos los párrafos

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
				parrafo = new Paragraph("Medellín, 04 de mayo del 2021", fuenteEncabezado);
				parrafo.setAlignment(Element.ALIGN_CENTER);
				document.add(parrafo);
				document.add( Chunk.NEWLINE );
				parrafo2 = new Paragraph("A QUIEN PUEDA INTERESAR", fuentePOEncabezado);
				parrafo2.setAlignment(Element.ALIGN_CENTER);
				document.add(parrafo2);
				document.add( Chunk.NEWLINE );
				parrafo3 = new Paragraph("ALEXARDER VIGOYA MURCIA, actuando como representante legal de la empresa PIZZA AMERICANA S.A.S con NIT 901290745-1.\r\n" + 
						"Datos de contacto de la empresa:\r\n" + 
						"–Domicilio: Carrera 48 No 63A - 35\r\n" + 
						"–Teléfono: 4804669\r\n" + 
						"–Correo electrónico: gerenteadministrativo@pizzaamericana.com.co\r\n" + 
						"",fuente);
				parrafo3.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(parrafo3);
				document.add( Chunk.NEWLINE );
				parrafo4 = new Paragraph("");
				parrafo4 = new Paragraph("Declara responsablemente:\r\n" + 
						"Que " + liqNomina.getNombre() + " " + liqNomina.getApellido() + " con cédula de ciudadanía número " + liqNomina.getCedula() + " es trabajador/a de esta empresa y reúne las condiciones para no acogerse al Decreto 2021070001593 “Por medio del cual se declara toque de queda, pico y cédula, ley seca por la vida y se dictan otras disposiciones en los municipios del departamento de Antioquia”; en sus artículos:\r\n" + 
								"\r\n" + 
								"Artículo 1°. Parágrafo. Se encuentran exceptuados del TOQUE DE QUEDA POR LA VIDA citado, los habitantes del departamento de Antioquia, que en atención a sus actividades laborales o por razones de emergencia deban desplazarse, además de los diferentes servicios de domicilios. \r\n" + 
								"\r\n" + 
								"Artículo 5°. Para que el TOQUE DE QUEDA POR LA VIDA DE FORMA CONTINUA en los municipios del departamento de Antioquia, garantice el derecho a la vida, a la salud en conexidad con la vida y la supervivencia, los alcaldes de los entes territoriales en el marco de la emergencia sanitaria por causa del COVID-19, permitirán el derecho de circulación de las personas en los siguientes casos o actividades: 18. La comercialización de los productos de los establecimientos y locales gastronómicos únicamente mediante plataformas de comercio electrónico y/o por entrega a domicilio.\r\n" + 
								"" + 
								". \r\n" + 
						"", fuente);
				parrafo4.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(parrafo4);
				document.add( Chunk.NEWLINE );
				//Agregamos la parte de vigencia
				parrafo6 = new Paragraph("Para que conste a los efectos de facilitar los trayectos necesarios entre su lugar de trabajo y su lugar de residencia, los días: \r\n"
						+ "* Desde el martes 04 de mayo de 2021 hasta el viernes 07 de mayo de 2021 y desde el lunes 10 de mayo hasta el martes 11 de mayo de 2021, entre las 8:00 pm y las 5:00 am.\r\n"
						+ "* Desde las 8:00 p.m. del viernes 07 de mayo de 2021 hasta las 5:00 a.m. del lunes 10 de mayo de 2021.", fuente); 
				parrafo6.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(parrafo6);
				document.add( Chunk.NEWLINE );
				//Agregamos la última parte
				parrafo5 = new Paragraph("Agradecemos la colaboración prestada, si presenta alguna inquietud con gusto será atendida,"
						+ " en el teléfono 4804669.", fuente); 
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
					correo.setAsunto("Certificado Laboral - Certificado para Transitar 4 Mayo - 10 Mayo 2021 - " + liqNomina.getCedula());
					correo.setContrasena(infoCorreo.getClaveCorreo());
					ArrayList correos = new ArrayList();
					correos.add(new String(liqNomina.getCorreoElectronico()));
					correo.setUsuarioCorreo(infoCorreo.getCuentaCorreo());
					correo.setMensaje("Buen Día " + liqNomina.getNombre() + " " + liqNomina.getApellido() + " , en este correo encontrarás anexo un certificado laboral, que podrás usar para efectos de facilitar los trayectos necesarios entre su lugar de trabajo y su lugar de residencia en los días 4 Mayo - 10 Mayo 2021.");
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
