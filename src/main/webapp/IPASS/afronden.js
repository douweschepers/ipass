function projecten(){
	med_id = localStorage.getItem("medewerkers_id");
	$.getJSON("http://localhost:4711/firstapp/restservices/project", function(data) {
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

}
function checkAdmin(){
	rol = localStorage.getItem("rol");
	 if(rol== "admin"){
			$('#verw').show();
		};
}
function afronden_project(project_id){

		 console.log(med_id);
		 var uri = "/firstapp/restservices/project/status/" + project_id;
		 var data = {"status": "afgerond"};
		 $.ajax(uri, {
			 type: "put",
			 data: data,
			 success: function() {
				 console.log("afgerond");
			 }
		 });
		 window.open("http://localhost:4711/firstapp/IPASS/afronden.html","_self");

}
projecten();