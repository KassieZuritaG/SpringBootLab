function searchByNivel(){
	var criteria = $("#txtNivel").val();
	$.ajax({
		url : "/materia/search/" + criteria,
		method : 'GET',
		success : function(response){
			$("#cursoid").empty();			
			var count = Object.keys(response).length;			
			if(count > 0){								
				$("#cursoid").addClass('visible').removeClass('invisible');
				$.each( response, function(index, materia ) {					
					$("#cursoid").append("<option value='"+ materia.idmateria +"'>" + materia.nombre + "</option>");					
				});
			}
			else{
				$("#cursoid").addClass('invisible').removeClass('visible');
				console.log("No hay materias para el nivel: " + criteria);				
			}			
		},
		error : function(err){
			console.error(err);
		}		
	});
}

function save(){	
	var dataForm = objectifyForm($("#frmMatricula").serializeArray());	
	var requestBody = JSON.stringify(dataForm);
	console.log(requestBody);			
	$.ajax({
		url : developURL + "matricula/save",
		method : 'POST',
		contentType : "application/json",
		headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()},		
		data : requestBody,
		success : function(response){
			console.log(response);
		},
		error : function(err){
			console.log(err);
		}		
	});
	
}

function create(){
	var id = $("#idalumno").val();	
	$.ajax({
		url : "/matricula/create/" + id,
		method : 'GET',
		success : function(response){			
			$("#contentMatricula").empty();
			$("#contentMatricula").html(response);
		},
		error : function(err){
			console.log(err);
		}		
	});
}

function list(){
	var id = $("#idalumno").val();
	$.ajax({
		url : "/matricula/list/" + id,
		method : 'GET',
		success : function(response){
			$("#listMatricula").empty();
			$("#listMatricula").html(response);
		},
		error : function(err){
			console.log(err);
		}		
	});	
}

$(document).ready(function(){
	
	console.log("Página cargada...");
	
	$("#btnAdd").click(function(){
		create();		
	});
	
	$("#btnSubmit").click(function(){
		save();		
	});
	
	$("#tab--2").click(function(){
		list();		
	});
	
});
