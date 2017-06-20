function reloadHome(){
	window.location = "maps.html";
}
function gegevens(){
	med_id = localStorage.getItem("medewerkers_id");
	lijst = [];
	var select = document.getElementById("huidige_projecten");
	
	$.getJSON("http://localhost:4711/firstapp/restservices/medewerker/"+med_id, function(data) {
		$(data).each(function( index_med , value_med ) {
			document.querySelector('#voornaam').innerHTML = value_med.voornaam;
			document.querySelector('#achternaam').innerHTML = value_med.achternaam;
			document.querySelector('#straat').innerHTML = value_med.straatnaam;
			document.querySelector('#huisnr').innerHTML = value_med.huisnr;
			document.querySelector('#med_id').innerHTML = value_med.medewerkers_id;
		});
	});
	$.getJSON("http://localhost:4711/firstapp/restservices/project", function(data) {
		$(data).each(function( index_pjct , value_pjct ) {
			if(value_pjct.medewerkers_id == med_id){
				lijst.push(value_pjct.projectNaam);				
				$('#projecten').append('<tr id="rows'+index_pjct+'">' +
						'<td>' + value_pjct.projectNaam + '</td>'+ 
						'</tr>');
			}			
		});
	});
	
	 $('#aanpassen').click(function() {
		 var dad = $(this).parent().parent();
	        dad.find('label').hide();
	        dad.find('#aanpassen').hide();
	        dad.find('input[type="text"]').show().focus();
	        dad.find('#opslaan').show().focus();
	        dad.find('#annuleren').show().focus();
		 });
	
 $('#opslaan').click(function(){
	 console.log(med_id);
	 var uri = "/firstapp/restservices/medewerker/" + med_id;
	 $.ajax(uri, {
		 type: "put",
		 beforeSend: function(xhr){
			 var token = window.sessionStorage.getItem("sessionToken");
			 xhr.setRequestHeader( 'Authorization', 'Bearer ' + token);

		 },
		 data: $("#updateCustomerForm").serialize(),
		 success: function() {
			 console.log("Customer saved!");
		 },
		 error: function() {
			 console.log("Could not update customer!");
	 }
	 });
	 window.open("http://localhost:4711/firstapp/IPASS/persoonsgegevens.html","_self");
	 });
 $('#annuleren').click(function(){
	 window.open("http://localhost:4711/firstapp/IPASS/persoonsgegevens.html","_self");
 });
 checkAdmin()
 
 $('#loguit').click(function(){
	 uitloggen();
 })
}
function checkAdmin(){
	rol = localStorage.getItem("rol");
	 if(rol== "admin"){
			$('#verw').show();
		};
}
function uitloggen(){
	localStorage.clear();
	window.open("http://localhost:4711/firstapp/IPASS/login.html","_self");
}
gegevens();

