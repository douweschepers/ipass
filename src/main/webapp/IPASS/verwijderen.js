function projecten(){
	med_id_storage = localStorage.getItem("medewerkers_id");
	//roep alle medewerkers aan
	$.getJSON("/restservices/medewerker", function(data) {
		var array = [];
		$(data).each(function( index_med , value_med ) {	
			array.push(value_med.medewerkers_id);
			//voeg alle medewerkers toe behalve de gebruiker
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
	//check voor admin
	checkAdmin();
	 $('#loguit').click(function(){
		 uitloggen();
	 })
};
//als op delete word geklikt maak ajax call
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
}

//als de gebruiker admin is laat verwijder medewerker zien
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
