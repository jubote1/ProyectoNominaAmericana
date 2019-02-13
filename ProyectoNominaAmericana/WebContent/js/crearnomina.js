	

var server;


// Se arma el valor de la variable global server, con base en la cual se realiza llamado a los servicios.
$(document).ready(function() {

	//Obtenemos el valor de la variable server
	var loc = window.location;
	var pathName = loc.pathname.substring(0, loc.pathname.lastIndexOf('/') + 1);
	server = loc.href.substring(0, loc.href.length - ((loc.pathname + loc.search + loc.hash).length - pathName.length));
	

    

	} );


//Método principal invocado para validar los datos de los parámetros y adicionalmente ejecutar los servicios para 
//recuperar la informacion y pintarla en pantalla.
function crearNomina() 
{

	var fechainicial = $("#fechainicial").val();
	var fechafinal = $("#fechafinal").val();
		
	if(fechainicial == '' || fechainicial == null)
	{
		$.alert('La fecha Inicial debe ser diferente a vacía');
		return;
	}

	if(fechafinal == '' || fechafinal == null)
	{
		$.alert('La fecha final debe ser diferente a vacía');
		return;
	}

	
	if(existeFecha(fechainicial))
	{
	}
	else
	{
		$.alert('La fecha Inicial no es correcta');
		return;
	}

		if(existeFecha(fechafinal))
	{
	}
	else
	{
		$.alert('La fecha Final no es correcta');
		return;
	}

	
	// Si pasa a este punto es porque paso las validaciones
	
	$.ajax({ 
	    		url: server + 'CRUDNomina?idoperacion=1&fechainicial=' + fechainicial + "&fechafinal=" + fechafinal, 
	    		dataType: 'json', 
	    		async: false, 
	    		success: function(data1){ 
	    				
	    				respuesta = data1[0];
	    				var idnomina = respuesta.idnomina;
	    				$.alert('Se ha creado la nómina con ID ' + idnomina);
					
				} 
			});
	 	
}




// Método creado para confirmar que una fecha exista
function existeFecha(fecha){
      var fechaf = fecha.split("/");
      var day = fechaf[0];
      var month = fechaf[1];
      var year = fechaf[2];
      var date = new Date(year,month,'0');
      if((day-0)>(date.getDate()-0)){
            return false;
      }
      return true;
}



