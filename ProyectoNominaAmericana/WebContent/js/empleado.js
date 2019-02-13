var server;
var table;


$(document).ready(function() {

	//Obtenemos el valor de la variable server
	var loc = window.location;
	var pathName = loc.pathname.substring(0, loc.pathname.lastIndexOf('/') + 1);
	server = loc.href.substring(0, loc.href.length - ((loc.pathname + loc.search + loc.hash).length - pathName.length));
	

		//Lo primero que realizaremos es validar si está logueado
	
		
	    table = $('#grid-empleado').DataTable( {
    		"aoColumns": [
            { "mData": "idempleado" },
            { "mData": "identificacion" },
            { "mData": "nombreempleado" },
            { "mData": "telefono" },
            { "mData": "cuenta" },
            {
                "mData": "accion",
                className: "center",
                defaultContent: '<input type="button" class="btn btn-default btn-xs" onclick="eliminarEmpleado()" value="Eliminar"></button> <input type="button" class="btn btn-default btn-xs" onclick="EditarEmpleado()" value="Editar"></button>'
            }
        ]
    	} );
  	  	


  	  	//
  	  	$('#userForm')
        .bootstrapValidator({
            framework: 'bootstrap',
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                nombreempleadoedit: {
                    validators: {
                        notEmpty: {
                            message: 'El nombre del Empleado es requerido'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z\s]+$/,
                            message: 'El nombre del empleado debe contener solo letras'
                        }
                    }
                }
            }
        })
        
    	//
		pintarEmpleados();
});

function guardarEmpleado()
{
	console.log("pase por aca");
	var nombreempleado = $('#nombreempleado').val();
	var identificacion = $('#identificacion').val();
	var telefono = $('#telefono').val();
	var cuenta = $('#cuenta').val();
	$.getJSON(server + 'CRUDEmpleado?idoperacion=1&nombreempleado=' + nombreempleado + "&identificacion=" + identificacion + "&telefono=" + telefono + "&cuenta=" + cuenta, function(data){
		
				
	});
	$('#nombreempleado').val('');
	$('#identificacion').val('');
	$('#telefono').val('');
	$('#cuenta').val('');
	$('#addData').modal('hide');
	pintarEmpleados();
}

function eliminarEmpleado(idempleado)
{
	$.confirm({
			'title'		: 'Confirmacion Eliminación Empleado',
			'content'	: 'Desea confirmar la eliminación del Empleado ' + idempleado + '.',
			'buttons'	: {
				'Si'	: {
					'class'	: 'blue',
					'action': function(){
					
						var resultado
						$.ajax({ 
	    				url: server + 'CRUDEmpleado?idoperacion=3&idempleado=' + idempleado , 
	    				dataType: 'json', 
	    				async: false, 
	    				success: function(data){ 
								resultado = data[0];
								//

								if ( $.fn.dataTable.isDataTable( '#grid-empleado' ) ) {
							    	table = $('#grid-empleado').DataTable();
							    }
								
								pintarEmpleados();

								//

								
							} 
						});
						


					}
				},
				'No'	: {
					'class'	: 'gray',
					'action': function(){}	// Nothing to do in this case. You can as well omit the action property.
				}
			}
		});
}

function pintarEmpleados()
{
	$.getJSON(server + 'CRUDEmpleado?idoperacion=5' , function(data1){
			table.clear().draw();
			for(var i = 0; i < data1.length;i++){
				table.row.add({
					"idempleado": data1[i].idempleado, 
					"nombreempleado": data1[i].nombreempleado,
					"identificacion": data1[i].identificacion,
					"telefono": data1[i].telefono, 
					"cuenta": data1[i].cuenta,  
					"accion":'<input type="button" class="btn btn-default btn-xs" onclick="eliminarEmpleado(' +data1[i].idempleado + ')" value="Eliminar"></button> <input type="button" onclick="editarEmpleado('+data1[i].idempleado +')" class="btn btn-default btn-xs editButton" ' + 'data-id="' + data1[i].idempleado + '" value="Edición"></button>'
				}).draw();
				//table.row.add(data1[i]).draw();
			}
		});
}

function editarEmpleado(idempleado)
{
	//
				
	// Get the record's ID via attribute
	
		$.ajax({ 
    				url: server + 'CRUDEmpleado?idoperacion=4&idempleado=' + idempleado, 
    				dataType: 'json', 
    				async: false, 
    				success: function(data){ 
						var respuesta = data[0];
						    $('#userForm')
				                .find('[name="idempleadoedit"]').val(respuesta.idempleado).end()
				                .find('[name="nombreempleadoedit"]').val(respuesta.nombreempleado).end()
				                .find('[name="identificacionedit"]').val(respuesta.identificacion).end()
				                .find('[name="telefonoedit"]').val(respuesta.telefono).end()
				                .find('[name="cuentaedit"]').val(respuesta.cuenta).end()
				                

				            // Show the dialog
				            bootbox
				                .dialog({
				                    title: 'Editar Empleado',
				                    message: $('#userForm'),
				                    show: false // We will show it manually later
				                })
				                .on('shown.bs.modal', function() {
				                    $('#userForm')
				                        .show()                             // Show the login form
				                        .bootstrapValidator('resetForm'); // Reset form
				                })
				                .on('hide.bs.modal', function(e) {
				                    // Bootbox will remove the modal (including the body which contains the login form)
				                    // after hiding the modal
				                    // Therefor, we need to backup the form
				                    $('#userForm').hide().appendTo('body');
				                })
				                .modal('show');
					} 
		});

		//
}

function confirmarEditarEmpleado()
{
	
           
            
                var idempleado =  $('input:text[name=idempleadoedit]').val();
                var nombreempleado = $('input:text[name=nombreempleadoedit]').val();
                var identificacion = $('input:text[name=identificacionedit]').val();
                var telefono = $('input:text[name=telefonoedit]').val();
                var cuenta = $('input:text[name=cuentaedit]').val();
            // The url and method might be different in your application
            $.ajax({ 
    				url: server + 'CRUDEmpleado?idoperacion=2&idempleado='+ idempleado+'&nombreempleado=' + nombreempleado + "&identificacion=" + identificacion + "&telefono=" + telefono + "&cuenta=" + cuenta, 
    				dataType: 'json', 
    				async: false, 
    				success: function(data){
    					pintarEmpleados();
               			 // Hide the dialog
                		$('#userForm').parents('.bootbox').modal('hide');

                		// You can inform the user that the data is updated successfully
                		// by highlighting the row or showing a message box
                		bootbox.alert('El empleado ha sido actualizado');
    				} 
			}); 
            //
            

        

}