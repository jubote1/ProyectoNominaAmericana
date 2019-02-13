var server;
var table;


$(document).ready(function() {

	//Obtenemos el valor de la variable server
	var loc = window.location;
	var pathName = loc.pathname.substring(0, loc.pathname.lastIndexOf('/') + 1);
	server = loc.href.substring(0, loc.href.length - ((loc.pathname + loc.search + loc.hash).length - pathName.length));
	

		//Lo primero que realizaremos es validar si está logueado
	
		
	    table = $('#grid-concepto').DataTable( {
    		"aoColumns": [
            { "mData": "idconcepto" },
            { "mData": "descripcion" },
            {
                "mData": "accion",
                className: "center",
                defaultContent: '<input type="button" class="btn btn-default btn-xs" onclick="eliminarConcepto()" value="Eliminar"></button> <input type="button" class="btn btn-default btn-xs" onclick="EditarConcepto()" value="Editar"></button>'
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
                descripcionedit: {
                    validators: {
                        notEmpty: {
                            message: 'La descripción del concepto de nómina es requerida'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z\s.0-9]*$/,
                            message: 'El concepto de nómina debe tener solo letras y números'
                        }
                    }
                }
            }
        })
        
    	//
		pintarConceptos();
});

function guardarConcepto()
{
	var descripcion = $('#descripcion').val();
	$.getJSON(server + 'CRUDConcepto?idoperacion=1&descripcion=' + descripcion, function(data){
		
				
	});
	$('#descripcion').val('');
	$('#addData').modal('hide');
	pintarConceptos();
}

function eliminarConcepto(idconcepto)
{
	$.confirm({
			'title'		: 'Confirmacion Eliminación del Concepto de Nómina',
			'content'	: 'Desea confirmar la eliminación del Concepto de Nómina ' + idconcepto + '.',
			'buttons'	: {
				'Si'	: {
					'class'	: 'blue',
					'action': function(){
					
						var resultado
						$.ajax({ 
	    				url: server + 'CRUDConcepto?idoperacion=3&idconcepto=' + idconcepto , 
	    				dataType: 'json', 
	    				async: false, 
	    				success: function(data){ 
								resultado = data[0];
								//

								if ( $.fn.dataTable.isDataTable( '#grid-empleado' ) ) {
							    	table = $('#grid-concepto').DataTable();
							    }
								
								pintarConceptos();

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

function pintarConceptos()
{
	$.getJSON(server + 'CRUDConcepto?idoperacion=5' , function(data1){
			table.clear().draw();
			for(var i = 0; i < data1.length;i++){
				
				table.row.add({
					"idconcepto": data1[i].idconcepto, 
					"descripcion": data1[i].descripcion,
					"accion":'<input type="button" class="btn btn-default btn-xs" onclick="eliminarConcepto(' +data1[i].idconcepto+ ')" value="Eliminar"></button> <input type="button" onclick="editarConcepto('+data1[i].idconcepto +')" class="btn btn-default btn-xs editButton" ' + 'data-id="' + data1[i].idconcepto + '" value="Edición"></button>'
				}).draw();
				//table.row.add(data1[i]).draw();
			}
		});
}

function editarConcepto(idconcepto)
{
	//
				
	// Get the record's ID via attribute
	
		$.ajax({ 
    				url: server + 'CRUDConcepto?idoperacion=4&idconcepto=' + idconcepto, 
    				dataType: 'json', 
    				async: false, 
    				success: function(data){ 
						var respuesta = data[0];
						    $('#userForm')
				                .find('[name="idconceptoedit"]').val(respuesta.idconcepto).end()
				                .find('[name="descripcionedit"]').val(respuesta.descripcion).end()
				                 

				            // Show the dialog
				            bootbox
				                .dialog({
				                    title: 'Editar Concepto',
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

function confirmarEditarConcepto()
{
	
           
            
                var idconcepto=  $('input:text[name=idconceptoedit]').val();
                var descripcion = $('input:text[name=descripcionedit]').val();
                
            // The url and method might be different in your application
            $.ajax({ 
    				url: server + 'CRUDConcepto?idoperacion=2&idconcepto='+ idconcepto +'&descripcion=' + descripcion , 
    				dataType: 'json', 
    				async: false, 
    				success: function(data){
    					pintarConceptos();
               			 // Hide the dialog
                		$('#userForm').parents('.bootbox').modal('hide');

                		// You can inform the user that the data is updated successfully
                		// by highlighting the row or showing a message box
                		bootbox.alert('El Concepto ha sido actualizado');
    				} 
			}); 
            //
            

        

}