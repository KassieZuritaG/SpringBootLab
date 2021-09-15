function searchByCedula(){
	var criteria = $("#txtCedula").val();
	$.ajax({
		url : "/alumno/search/" + criteria,
		method : 'GET',
		success : function(response){
			$("#estudianteid").empty();			
			var count = Object.keys(response).length;			
			if(count > 0){								
				$("#estudianteid").addClass('visible').removeClass('invisible');
				$.each( response, function(index, alumno ) {					
					$("#estudianteid").append("<option value='"+ alumno.idalumno +"'>" + alumno.nombres + " " + alumno.apellidos + "</option>");					
				});
			}
			else{
				$("#estudianteid").addClass('invisible').removeClass('visible');
				console.log("No hay alumnos para ese inicio de cédula: " + criteria);				
			}			
		},
		error : function(err){
			console.error(err);
		}		
	});
}

function create(){		
	$.ajax({
		url : "/participante/create",
		method : 'GET',
		success : function(response){
			console.log(response);
			$("#contentFrmParticipante").empty();
			$("#contentFrmParticipante").html(response);
		},
		error : function(err){
			console.log(err);
		}		
	});
}

function list(){	
	$.ajax({
		url : "/gira/students/",
		method : 'GET',
		success : function(response){
			$("#listParticipantes").empty();
			$("#listParticipantes").html(response);
		},
		error : function(err){
			console.log(err);
		}		
	});	
}

function save(){	
	var dataForm = objectifyForm($("#frmParticipante").serializeArray());	
	var requestBody = JSON.stringify(dataForm);
	console.log(requestBody);			
	$.ajax({
		url : developURL + "gira/add",
		method : 'POST',
		contentType : "application/json",
		headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()},		
		data : requestBody,
		success : function(response){
			console.log(response);			 
			list();
		},
		error : function(err){
			console.log(err);
		}		
	});
	
}



$(document).ready(function(){
	
	list();
	
	$("#btnAdd").click(function(){
		create();		
	});
	
	$("#btnSubmit").click(function(){
		save();		
	});
		
});