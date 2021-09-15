

function deleteProfesor(){
	swal({	  
	  text: "¿Desea eliminar el registro?",
	  icon: 'warning',
	  buttons: ["Cancelar", "Confirmar"],
	}).then((result) => {
		console.log(result);
		
		if (result.value=true) {
			var id = $("#idprofesor").val();
			console.log(id);
			
			window.location.href = "http://localhost:8080/profesor/delete/" + id;
		}
	});
}

