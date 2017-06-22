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
}/*
function veranderForeignKey(med_id, array){	
	$('#pasAan').show();
	console.log(med_id);
	
	med_id_storage = localStorage.getItem("medewerkers_id");
	var myDiv = document.getElementById("pasAan");
	var selectList = document.createElement("select");
	selectList.id = "mySelect";
	myDiv.appendChild(selectList);
	
	
	
	for (var i = 0; i < array.length; i++) {
		if(med_id!=i){
		var option = document.createElement("option");
	    option.value = array[i];
	    option.text = array[i];
	    selectList.appendChild(option);
		}
	}; 
	
	
	
	var uri = "/firstapp/restservices/project/" + med_id;
	 $.ajax(uri, {
		 type: "put",
		 data: $("#updateMedId").serialize(),
		 success: function() {
			 console.log("medewerkers ID aangepast!");
		 },
		 error: function() {
			 console.log("niet aangepast!");
	 }
	 });
};*/
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
	window.open("/login.html","_self");
}

projecten();
