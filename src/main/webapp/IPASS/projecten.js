function projecten(){
	med_id = localStorage.getItem("medewerkers_id");
	lijst = [];
	var select = document.getElementById("collapse");
	//laad alle project in
	$.getJSON("/restservices/project", function(data) {
		$(data).each(function( index_pjct , value_pjct ) {
			//kijk welke projecten gelijk staan aan de medewerkers_id van de gebruiker
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
		});
	});
	//als op nieuw project geklikt word laat dan de invoervelden zien
	$('#nieuwProject').click(function() {
		var dad = $(this).parent().parent();
			dad.find('#projectInfo').hide();
			dad.find('td').hide();
			dad.find('input[type="text"]').show().focus();
	        dad.find('#segment').show().focus();
	        dad.find('#type').show().focus();
	        dad.find('#solution').show().focus(); 
	        dad.find('#begindatum').show().focus();
	        dad.find('#nieuwProjectOpslaan').show().focus();
	        dad.find('#nieuwProjectAnnuleren').show().focus();
	        dad.find('#status').show().focus();
	});
	// bij opslaan maak de ajax call
	$("#nieuwProjectOpslaan").click(function() {
		var datum = $("#begindatum").val();
		//geen serialize omdat er verschillende soorten invoer velden zijn
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
			url:'/restservices/project/newProject',
			data:data,
			success: function(){
			    console.log('succes');
			         }
			     });
		window.open("/projecten.html","_self");
	});
	$("#nieuwProjectAnnuleren").click(function() {
		window.open("/projecten.html","_self");
	})
	checkAdmin();
	 $('#loguit').click(function(){
		 uitloggen();
	 });
}
// als op logo geklikt word herlaad dan de maps
function reloadHome(){
	window.location = "maps.html";
}
//check of gebruiker admin is zo ja laat dan ook delete medewerker zien
function checkAdmin(){
	rol = localStorage.getItem("rol");
	if(rol== "admin"){
		$('#verw').show();
		$("#persoonsgegevens").attr('class', 'border-admin');
		$("#projecten").attr('class', 'border-admin');
	};
}
//als word uitgelogd clear de localstorage en ga naar inlog scherm
function uitloggen(){
	localStorage.clear();
	window.open("/IPASS/login.html","_self");
}

projecten();