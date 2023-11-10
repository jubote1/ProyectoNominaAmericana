package utilidades;

import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import capaDAO.ParametrosDAO;
import capaModelo.Correo;
import capaModelo.CorreoElectronico;

public class ControladorEnvioCorreo {

private Correo  c;
private ArrayList correos;

public ControladorEnvioCorreo(Correo co, ArrayList correosenv)
{
	this.c= co;
	this.correos = correosenv;
}

public boolean enviarCorreo()
{
	boolean bandera = true;
	int contadorErrores = 0;
	while(bandera)
	{
		try
		{
			Properties p = new Properties();
			p.put("mail.smtp.host", "smtp.gmail.com");
			p.put("mail.smtp.ssl.protocols", "TLSv1.2");
			p.setProperty("mail.smtp.starttls.enable", "true");
			p.setProperty("mail.smtp.port", "587");
			p.setProperty("mail.smtp.user", c.getUsuarioCorreo());
			p.setProperty("mail.smtp.auth", "true");
			
			Session s = Session.getDefaultInstance(p, null);
			BodyPart texto = new MimeBodyPart();
			texto.setContent(c.getMensaje(), "text/html; charset=utf-8");
			//texto.setText(c.getMensaje());
			MimeMultipart m = new MimeMultipart();
			
			m.addBodyPart(texto);
			//Agregamos los archivos Anexos
			String[] archAnexos = c.getRutasArchivos();
			for(int i = 0; i < archAnexos.length; i++)
			{
				BodyPart adjunto = new MimeBodyPart();
				String cadenaCompleta = archAnexos[i];
				StringTokenizer tokens = new StringTokenizer(cadenaCompleta,"%&");
				String ruta = tokens.nextToken();
				String nombreArchivo = tokens.nextToken();
				adjunto.setDataHandler(new DataHandler(new FileDataSource(ruta)));
				adjunto.setFileName(nombreArchivo);
				m.addBodyPart(adjunto);
			}
			MimeMessage mensaje = new MimeMessage(s);
			mensaje.setFrom(new InternetAddress(c.getUsuarioCorreo()));
			for(int i = 0; i< correos.size(); i++)
			{
				mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress((String)correos.get(i)));
			}
			mensaje.setSubject(c.getAsunto());
			mensaje.setContent(m, "text/html");
			Transport t = s.getTransport("smtp");
			t.connect(c.getUsuarioCorreo(),c.getContrasena());
			t.sendMessage(mensaje, mensaje.getAllRecipients());
			t.close();
			bandera = false;
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			contadorErrores ++;
			try
			{
				Thread.sleep(10000);
			}catch(Exception ex)
			{
				
			}
			if (contadorErrores == 4)
			{
				bandera = false;
			}
		}
	}
	if(contadorErrores < 4)
	{
		return(true);
	}
	else
	{
		return(false);
	}
}

public boolean enviarCorreoInstitucional()
{
	try
	{
		Properties p = new Properties();
		p.put("mail.smtp.host", "mail.pizzaamericana.co");
		//p.setProperty("mail.smtp.starttls.enable", "true");
		p.setProperty("mail.smtp.port", "587");
		p.setProperty("mail.smtp.user", c.getUsuarioCorreo());
		p.setProperty("mail.smtp.auth", "true");
		
		Session s = Session.getDefaultInstance(p, null);
		BodyPart texto = new MimeBodyPart();
		texto.setContent(c.getMensaje(), "text/html; charset=utf-8");
		//texto.setText(c.getMensaje());
		MimeMultipart m = new MimeMultipart();
		m.addBodyPart(texto);
		//Agregamos los archivos Anexos
		String[] archAnexos = c.getRutasArchivos();
		for(int i = 0; i < archAnexos.length; i++)
		{
			BodyPart adjunto = new MimeBodyPart();
			String cadenaCompleta = archAnexos[i];
			StringTokenizer tokens = new StringTokenizer(cadenaCompleta,"%&");
			String ruta = tokens.nextToken();
			String nombreArchivo = tokens.nextToken();
			adjunto.setDataHandler(new DataHandler(new FileDataSource(ruta)));
			adjunto.setFileName(nombreArchivo);
			m.addBodyPart(adjunto);
		}
		
		MimeMessage mensaje = new MimeMessage(s);
		mensaje.setFrom(new InternetAddress(c.getUsuarioCorreo()));
		for(int i = 0; i< correos.size(); i++)
		{
			mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress((String)correos.get(i)));
		}
		mensaje.setSubject(c.getAsunto());
		mensaje.setContent(m);
		Transport t = s.getTransport("smtp");
		t.connect(c.getUsuarioCorreo(),c.getContrasena());
		t.sendMessage(mensaje, mensaje.getAllRecipients());
		t.close();
		return(true);
		
	}
	catch(Exception e)
	{
		System.out.println(e.toString() + e.getMessage() + e.getStackTrace().toString());
		return(false);
	}
	
}

public static CorreoElectronico recuperarCorreo(String variableCuenta, String variableClave)
{
	String cuentaCorreo = ParametrosDAO.retornarValorAlfanumerico(variableCuenta);
	String claveCorreo = ParametrosDAO.retornarValorAlfanumerico(variableClave);
	CorreoElectronico respuesta = new CorreoElectronico(cuentaCorreo, claveCorreo);
	return(respuesta);
}
	
}
