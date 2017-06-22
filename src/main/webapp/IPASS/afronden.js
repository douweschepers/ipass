function projecten(){
	med_id = localStorage.getItem("medewerkers_id");
	$.getJSON("/restservices/project", function(data) {
		$(data).each(function( index_pjct , value_pjct ) {
			if(value_pjct.medewerkers_id == med_id && value_pjct.status=="actief"){
				$('#projecten').append('<tr id="rows'+index_pjct+'">' +
						'<td>' + value_pjct.projectNaam + '</td>'+ 
						'<td>' + '<form><input type="button" class="afronden-button" id="afronden'+index_pjct+'" value="afronden" /></form>' + '</td>' +
						'</tr>');
				$('#afronden'+index_pjct).click(function(){
					afronden_project(value_pjct.project_id);
				})
			}			
			if(value_pjct.medewerkers_id == med_id && value_pjct.status=="afgerond"){
				$('#afgeronden-projecten').append('<tr id="rows'+index_pjct+'">' +
						'<td>' + value_pjct.projectNaam + '</td>'+ 
						'</tr>');
			}		
		});
	});
	checkAdmin();
	 $('#loguit').click(function(){
		 uitloggen();
	 })

}
function checkAdmin(){
	rol = localStorage.getItem("rol");
	 if(rol== "admin"){
			$('#verw').show();
			$("#persoonsgegevens").attr('class', 'border-admin');
			$("#projecten").attr('class', 'border-admin');
			$("#afgeronden-projecten").attr('class', 'border-admin');
		};
}
function afronden_project(project_id){

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
function uitloggen(){
	localStorage.clear();
	window.open("/IPASS/login.html","_self");
}
projecten();