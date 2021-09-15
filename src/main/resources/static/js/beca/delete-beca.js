function alertDeleteBeca(id) {
	
	Swal.fire({
		  title: '¿Deseas borrar la beca?',
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#d33',
		  cancelButtonColor: '#939393',
		  confirmButtonText: 'Sí, eliminar',
		  cancelButtonText: 'No, cancelar'
		}).then((result) => {
		  if (result.value) {
		    deleteBeca(id)
		  }
		})
}


function deleteBeca(id) {
	$.ajax({
		url : "/beca/delete/" + id,
		method : 'GET',
		success : function(response){
			Swal.fire(
		      'Beca eliminada',
		      'La beca se ha eliminado correctamente.',
		      'success'
		    ).then((result) => {
		    	 $('#row-beca-' + id).hide('slow');
			})		
		},
		error : function(err){
			Swal.fire(
		      'Ha ocurrido un error',
		      'No se ha podido eliminar la beca, intente nuevamente.',
		      'warning'
		    )
			console.error(err);
		}		
	});
}