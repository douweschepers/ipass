function projecten(){
	med_id = localStorage.getItem("medewerkers_id");
	lijst = [];
	var select = document.getElementById("collapse");
	$.getJSON("http://localhost:4711/firstapp/restservices/project", function(data) {
		$(data).each(function( index_pjct , value_pjct ) {
			if(value_pjct.medewerkers_id == med_id){
				$('#projecten').append('<tr id="rows'+index_pjct+'">' +
						'<td>' + value_pjct.projectNaam + '</td>'+ 
						'<td>' + value_pjct.straatnaam + '</td>'+ 
						'<td>' + value_pjct.huisnr + '</td>'+ 
						'<td>' + value_pjct.telefoonnr + '</td>'+ 
						'<td>' + value_pjct.type + '</td>'+ 
						'<td>' + value_pjct.segment + '</td>'+ 
						'<td>' + value_pjct.solution + '</td>'+ 
						'<td>' + value_pjct.begindatum + '</td>'+ 
						'</tr>');
			}
			else{
				document.getElementById('projecten').innerHTML = 'Geen projecten gevonden!';
			}
		});
	});
	$('#nieuwProject').click(function() {
		var dad = $(this).parent().parent();
		dad.find('input[type="text"]').show().focus();
        dad.find('#segment').show().focus();
        dad.find('#type').show().focus();
        dad.find('#solution').show().focus(); 
        dad.find('#begindatum').show().focus();
        dad.find('#nieuwProjectOpslaan').show().focus();
        dad.find('#nieuwProjectAnnuleren').show().focus();
	});
	$("#nieuwProjectOpslaan").click(function(response) {
		var datum = $("#begindatum").val();
		var data = {"project_id": $("#project_id").val(), 
				"huisnr": $("#huisnr").val(), 
				"straatnaam": $("#straatnaam").val(),
				"segment": $("#segment").val(),
				"type": $("#type").val(), 
				"solution": $("#solution").val(),
				"telefoonnr": $("#telnr").val(),
				"medewerkers_id": $("#med_id").val(), 
				"begindatum": datum, 
				"projectnaam": $("#projectnaam").val(), 
				"status": $("#status").val()};
		
		console.log(data);
		$.ajax({
			type: 'POST',    
			url:'/firstapp/restservices/project/newProject',
			data:data,
			success: function(){
			    console.log('succes')
			         }
			     });
		window.open("http://localhost:4711/firstapp/IPASS/projecten.html","_self");
	});
	$("#nieuwProjectAnnuleren").click(function() {
		window.open("http://localhost:4711/firstapp/IPASS/projecten.html","_self");
	});
	checkAdmin();
}
function reloadHome(){
	window.location = "maps.html";
}
function filter(){
	 // Declare variables 
	console.log("filter");
	  var input, filter, table, tr, td, i;
	  input = document.getElementById("zoek");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("projectInfo");
	  tr = table.getElementsByTagName("tr");
	  var nummer = $("#optie").val();
	  // Loop through all table rows, and hide those who don't match the search query
	  for (i = 0; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[nummer];
	    if (td) {
	      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    } 
	  }
}
function checkAdmin(){
	rol = localStorage.getItem("rol");
	if(rol== "admin"){
		$('#verw').show();
	};
}



projecten();