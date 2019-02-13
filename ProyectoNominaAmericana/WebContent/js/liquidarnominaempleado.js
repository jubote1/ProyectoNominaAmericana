var server;
var table;
var nominas;
var conceptos;


$(document).ready(function() {

	//Obtenemos el valor de la variable server
	var loc = window.location;
	var pathName = loc.pathname.substring(0, loc.pathname.lastIndexOf('/') + 1);
	server = loc.href.substring(0, loc.href.length - ((loc.pathname + loc.search + loc.hash).length - pathName.length));
	

		//Lo primero que realizaremos es validar si está logueado
		getListaNominas();
		getListaEmpleados();
		getObtenerConceptos();
		$("#selectEmpleados").change(function(){
			var idnomina = $("#selectNominas option:selected").attr('id');
			var idempleado = $("#selectEmpleados option:selected").attr('id');
			
			if(idempleado != "" && idnomina != "" && idempleado != null && idnomina != null && idempleado != undefined && idnomina != undefined)
			{
				getPintarConceptos();
				console.log(conceptos);
				
			}
    	
    	});
	  
});

function getObtenerConceptos()
{
	$.ajax({ 
	    		url: server + 'CRUDConcepto?idoperacion=5' , 
	    		dataType: 'json', 
	    		async: false, 
	    		success: function(data1){ 
	    			conceptos = data1;
	    		} 
			});

}

function getPintarConceptos()
{
	 
	    				
	    				
	    				bandera = true;
	    				var concepto;
						
						var strInv='';
						
						strInv += '<table id="conceptosempleado" class="table table-bordered">';
						strInv += '<thead><tr><th COLSPAN="2">CONCEPTOS POR EMPLEADO</th>';
						strInv += '</tr>'
						strInv += '<tr><th>DESCRIPCIÓN CONCEPTO</th><th>VALOR</th></tr></thead>';
				        strInv += '<tbody>';
						for (var i = 0; i < conceptos.length; i++)
						{
							concepto = conceptos[i];
							strInv +='<tr> ';
							strInv +='<td> ';
							strInv +='<label>' + concepto.descripcion + '</label> </td>';
							strInv +='<td> ';
							strInv += '<input type="text" value="" id="concepto'+  concepto.idconcepto +'" maxlength="20"> </td>';
							strInv +='</tr> ';
						}
						strInv +='</tbody> ';
						$('#conceptos').html(strInv);
						console.log(strInv);
					
				
}

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



function guardarLiquidacionEmpleado()
{
	var idnomina = $("#selectNominas option:selected").attr('id');
	var idempleado = $("#selectEmpleados option:selected").attr('id');
	for (var i = 0; i < conceptos.length; i++)
	{
		var concepto = conceptos[i];
		var valor = $("#concepto"+concepto.idconcepto).val();
		$.ajax({ 
			url: server + 'InsertarLiquidacionNominaEmpleado?idnomina=' + idnomina + "&idempleado=" + idempleado + "&idconcepto=" + concepto.idconcepto + "&valor=" + valor , 
			dataType: 'json',
			type: 'get', 
			async: false, 
			success: function(data2){
						    
			} 
		});
		
	}
	reiniciarLiquidacion();
	alert("Se ingreso la liquidación");
	
	
	
}

function reiniciarLiquidacion()
{
	$("#selectNominas").val('');
	$("#selectEmpleados").val('');
	$('#conceptos').html('');
}

function getListaEmpleados(){
	$.getJSON(server + 'CRUDEmpleado?idoperacion=5', function(data){
		empleados = data;
		var str = '';
		for(var i = 0; i < data.length;i++){
			var cadaEmpleado  = data[i];
			str +='<option value="'+ cadaEmpleado.nombreempleado + '" id ="'+ cadaEmpleado.idempleado +'">' + cadaEmpleado.nombreempleado +'</option>';
		}
		$('#selectEmpleados').html(str);
		// Realizamos cambio para que la tienda no esté seleccionada por defecto
		$("#selectEmpleados").val('');
	});
}
