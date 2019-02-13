	

var server;
var urlTienda ="";




// Se arma el valor de la variable global server, con base en la cual se realiza llamado a los servicios.
$(document).ready(function() {

	//Obtenemos el valor de la variable server
	var loc = window.location;
	var pathName = loc.pathname.substring(0, loc.pathname.lastIndexOf('/') + 1);
	server = loc.href.substring(0, loc.href.length - ((loc.pathname + loc.search + loc.hash).length - pathName.length));
	

    

	} );

//Se obtiene el listado de tiendas con el fin de seleccionar la tienda a surtir.

$(function(){
	
	getListaNominas();
	
});


function getListaNominas(){
	$.getJSON(server + 'CRUDNomina?idoperacion=5', function(data){
		nominas = data;
		var str = '';
		for(var i = 0; i < data.length;i++){
			var cadaNomina  = data[i];
			str +='<option value="'+ cadaNomina.fechainicial + "//" + cadaNomina.fechafinal +'" id ="'+ cadaNomina.idnomina +'">' + cadaNomina.fechainicial + "//" + cadaNomina.fechafinal +'</option>';
		}
		$('#selectNominas').html(str);
		// Realizamos cambio para que la tienda no esté seleccionada por defecto
		$("#selectNominas").val('');
	});
}

function imprimirDivInventario(nombreDiv)
{
	var contenido= document.getElementById(nombreDiv).innerHTML;
    var contenidoOriginal= document.body.innerHTML;
    document.body.innerHTML = contenido;
    window.print();
    document.body.innerHTML = contenidoOriginal;
}


//Método que invoca el servicio para obet
function getListaTiendas(){
	$.getJSON(server + 'GetTiendas', function(data){
		tiendas = data;
		var str = '';
		for(var i = 0; i < data.length;i++){
			var cadaTienda  = data[i];
			str +='<option value="'+ cadaTienda.nombre +'" id ="'+ cadaTienda.id +'">' + cadaTienda.nombre +'</option>';
		}
		$('#selectTiendas').html(str);
	});
}



//Método principal invocado para validar los datos de los parámetros y adicionalmente ejecutar los servicios para 
//recuperar la informacion y pintarla en pantalla.
function consultarLiquidacionNomina() 
{

	var idnomina = $("#selectNominas option:selected").attr('id');
	if (idnomina == "" || idnomina == null || idnomina == undefined)
	{
		alert("Debe seleccionar una nómina para consultar");
		return;
	}
	
		
	$.ajax({ 
	    		url: server + 'ConsultarLiquidacionNomina?idnomina=' + idnomina , 
	    		dataType: 'json', 
	    		async: false, 
	    		success: function(data1){ 
	    				
	    				liquidaciones = data1;
	    				bandera = true;
	    				var liquidacion;
						console.log(liquidaciones)
						var strInv='';
						
						strInv += '<table id="liquidacionnomina" class="table table-bordered table-striped">';
						strInv += '<thead><tr><th COLSPAN="1"><img src="LogoPizzaAmericanapeque.png" class="img-circle" /></th>';
						strInv += '<th COLSPAN="2"> <h3>'+ "LIQUIDACION NÓMINA " + liquidaciones[0].fechasnomina +'</h3></th></tr>'
						strInv += '<tr><th>NombreEmpleado</th><th>Concepto Nómina</th><th>Valor Concepto</th></tr></thead>';
				        strInv += '<tbody>';
				        for (var i = 0; i < liquidaciones.length; i++)
						{
								liquidacion = liquidaciones[i];
								strInv +='<tr> ';
								strInv +='<td> <font size=1>';
								strInv +='<label>' + liquidacion.nombreempleado + '</label> </font> </td>';
								strInv +='<td> <font size=1>';
								strInv += '<label>' + liquidacion.descripcion + '</label> </font> </td>';
								strInv +='<td> <font size=1>';
								strInv +='<label>' + liquidacion.valor + '</label> </font> </td>';
								strInv +='</tr> ';
						}
						strInv +='</tbody> ';
						$('#nomina').html(strInv);
					
				} 
			});
	 
	
}

function generarExcel()
{
	$("#nomina").table2excel({
	    filename: "LiquidacionNomina"
	  });

}



