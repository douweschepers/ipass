function projecten(){
	med_id_storage = localStorage.getItem("medewerkers_id");

	$.getJSON("/restservices/medewerker", function(data) {
		var array = [];
		$(data).each(function( index_med , value_med ) {	
			array.push(value_med.medewerkers_id);
			if(value_med.medewerkers_id!=med_id_storage){
				$('#medewerkers').append('<tr id="rows'+index_med+'">' +
						'<td>' + value_med.medewerkers_id + '</td>'+ 
						'<td>' + value_med.voornaam + '</td>'+ 
						'<td>' + value_med.achternaam + '</td>'+ 
						'<td>' + '<form><input type="button" id="delete'+index_med+'" value="Delete" /></form>' + '</td>' +
						'</tr>');	
			}
					
					
				verwijderen(index_med, value_med.medewerkers_id,array);
		});
	});
	checkAdmin();
	 $('#loguit').click(function(){
		 uitloggen();
	 })
};

function verwijderen(index,value, array){
	
	$('#delete'+index).click(function() {
		
		var uri = "/restservices/medewerker/" + value;
		
		$.ajax(uri, {
			type: "delete",
			data: index,
			success: function(response) {
				alert(value + " has been deleted!");
			},
			error: function(response) {
				alert(value + " has NOT been deleted!");
			}
		})
	});
	//checkForProject(value, array);
}
function checkForProject(med_id, array){
	var lijst = []
	$.getJSON("/restservices/project", function(data) {
		$(data).each(function( index_pjct , value_pjct) {	
			lijst.push(value_pjct.projectNaam)
			if(med_id == value_pjct.medewerkers_id){
				veranderForeignKey(value_pjct.medewerkers_id, array);
			}
		})
	})
}

function checkAdmin(){
	rol = localStorage.getItem("rol");
	 if(rol== "admin"){
			$('#verw').show();
			$("#persoonsgegevens").attr('class', 'border-admin');
			$("#projecten").attr('class', 'border-admin');
		}
}
function uitloggen(){
	localStorage.clear();
	window.open("/IPASS/login.html","_self");
}

projecten();
