function eliminar(id,nombres){
	swal({
		  title: "Está seguro que desea eliminar el registro de "+nombres +"?",
		  icon: "warning",
		  buttons: ["Cancelar", "Confirmar"],
		  dangerMode: true,
		})
		.then((OK) => {
		  if (OK) {
			  $.ajax({
				  url:"/profesor/delete/"+ id,
				  success: function(res) {
					  console.log(res);
				  }
				  
			  });
		    swal("Registro eliminado correctamente", {
		      icon: "success",
		    }).then((OK)=>{
		    	if(OK){
		    		location.href="/profesor/list";
		    	}
		    });
		  } 
		});
	
}
