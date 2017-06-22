function projecten(){
	//vraag het medewerkersID op uit de localstorage
	med_id = localStorage.getItem("medewerkers_id");
	$.getJSON("/restservices/project", function(data) {
		$(data).each(function( index_pjct , value_pjct ) {
			if(value_pjct.medewerkers_id == med_id && value_pjct.status=="actief"){
				//voeg projecten toe aan table als localstorage medewerkersid gelijk is aan med_id in DB
				$('#projecten').append('<tr id="rows'+index_pjct+'">' +
						'<td>' + value_pjct.projectNaam + '</td>'+ 
						'<td>' + '<form><input type="button" class="afronden-button" id="afronden'+index_pjct+'" value="afronden" /></form>' + '</td>' +
						'</tr>');
				$('#afronden'+index_pjct).click(function(){
					afronden_project(value_pjct.project_id);
				})
			}			
			//als de status is afgerond voeg toe aan de andere tabel
			if(value_pjct.medewerkers_id == med_id && value_pjct.status=="afgerond"){
				$('#afgeronden-projecten').append('<tr id="rows'+index_pjct+'">' +
						'<td>' + value_pjct.projectNaam + '</td>'+ 
						'</tr>');
			}		
		});
	});
	//altijd checken of de gebruiker een admin is
	checkAdmin();
	//als er op log uit geklikt word ga dan naar functie uitloggen
	 $('#loguit').click(function(){
		 uitloggen();
	 })

}
function checkAdmin(){
	rol = localStorage.getItem("rol");
	//als gebruiker admin is laat ook verwijderen medwerker zien
	 if(rol== "admin"){
			$('#verw').show();
			$("#persoonsgegevens").attr('class', 'border-admin');
			$("#projecten").attr('class', 'border-admin');
			$("#afgeronden-projecten").attr('class', 'border-admin');
		};
}
function afronden_project(project_id){
		//de ajax call om een project af te ronden
		 console.log(med_id);
		 var uri = "/restservices/project/status/" + project_id;
		 var data = {"status": "afgerond"};
		 $.ajax(uri, {
			 type: "put",
			 data: data,
			 success: function() {
				 console.log("afgerond");
			 }
		 });
		 window.open("/afronden.html","_self");

}
//bij uitloggen leeg de localstorage en ga terug naar inlog scherm
function uitloggen(){
	localStorage.clear();
	window.open("/IPASS/login.html","_self");
}
projecten();