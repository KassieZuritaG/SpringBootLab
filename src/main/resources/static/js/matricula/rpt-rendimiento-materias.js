var config;
var ctx = document.getElementById('chart-area-2').getContext('2d');

function report(id){	
	$.ajax({
		url : "/matricula/dataRptRendimientoMateria/" + id,
		method : 'GET',
		success : function(response){
			console.log(response);
			$("#title").html(response.nombre);
						
			config = {
				type: 'pie',
				data: {
					datasets: [{
						data: [
							response.aprobados,
							response.reprobados
						],
						backgroundColor: [
							getRandomColor(),
							getRandomColor()
						],
						label: response.nombre
					}],
					labels: [
						'Aprobados ' + response.aprobados,
						'Reprobados ' + response.reprobados					
					]
				},
				options: {
					responsive: true
				}
			};
			
			window.myPie = new Chart(ctx, config);								
			
		},
		error : function(err){
			console.log(err);
		}		
	});	
}

function list(id){	
	$.ajax({
		url : "/matricula/listRptRendimientoMateria/" + id,
		method : 'GET',
		success : function(response){
			console.log(response);
			$("#lstMatriculas").empty();
			$("#lstMatriculas").html(response);			
		},
		error : function(err){
			console.log(err);
		}		
	});	
}




$(document).ready(function(){
			
	
	
	$("#txtMateria").change(function() {
		let id =  $("#txtMateria").val();
		report(id);
		list(id);
	});		
	
});
