function DeleteArea(id) {
	
	Swal.fire({
		  title: '¿Deseas borrar la area?',
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#d33',
		  cancelButtonColor: '#939393',
		  confirmButtonText: 'Sí, eliminar',
		  cancelButtonText: 'No, cancelar'
		}).then((result) => {
		  if (result.value) {
		    EliminarArea(id)
		  }
		})
}


function EliminarArea(id) {
	$.ajax({
		url : "/area/delete/" + id,
		method : 'GET',
		success : function(response){
			Swal.fire(
		      'Area eliminada',
		      'La area se ha eliminado correctamente.',
		      'success'
		    ).then((result) => {
		    	 $('#row-area-' + id).hide('slow');
			})		
		},
		error : function(err){
			Swal.fire(
		      'Ha ocurrido un error',
		      'No se ha podido eliminar la area, intente nuevamente.',
		      'warning'
		    )
			console.error(err);
		}		
	});
}