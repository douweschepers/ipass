function reloadHome(){
	//als er op het logo word geklikt herlaad dan de pagina en ga naar de maps
	window.location = "maps.html";
}
function gegevens(){
	console.log(localStorage)
	med_id = localStorage.getItem("medewerkers_id");
	console.log(med_id);
	lijst = [];
	var select = document.getElementById("huidige_projecten");
	//roep de reevices aan med het huidige medewerkersID
	$.getJSON("/restservices/medewerker/"+med_id, function(data) {
		$(data).each(function( index_med , value_med ) {
			//laat de informatie over de medewerker in
			document.querySelector('#voornaam').innerHTML = value_med.voornaam;
			document.querySelector('#achternaam').innerHTML = value_med.achternaam;
			document.querySelector('#straat').innerHTML = value_med.straatnaam;
			document.querySelector('#huisnr').innerHTML = value_med.huisnr;
			document.querySelector('#med_id').innerHTML = value_med.medewerkers_id;
		});
	});
	//laad alle projecten in
	$.getJSON("/restservices/project", function(data) {
		$(data).each(function( index_pjct , value_pjct ) {
			//kijk waar de medewerkers id van gebruiker gelijk staat aan die van project
			if(value_pjct.medewerkers_id == med_id){
				lijst.push(value_pjct.projectNaam);				
				//voeg die toe aan tabel
				$('#projecten').append('<tr id="rows'+index_pjct+'">' +
						'<td>' + value_pjct.projectNaam + '</td>'+ 
						'</tr>');
			}			
		});
	});
	// als op aanpassen word geklikt laat invoervelden zien
	 $('#aanpassen').click(function() {
		 var dad = $(this).parent().parent();
	        dad.find('label').hide();
	        dad.find('#aanpassen').hide();
	        dad.find('input[type="text"]').show().focus();
	        dad.find('#opslaan').show().focus();
	        dad.find('#annuleren').show().focus();
		 });
	// ajax call voor opslaan van aanpassingen
 $('#opslaan').click(function(){
	 console.log(med_id);
	 var uri = "/restservices/medewerker/" + med_id;
	 $.ajax(uri, {
		 type: "put",
		 data: $("#updateCustomerForm").serialize(),
		 success: function() {
			 console.log("Customer saved!");
		 },
		 error: function() {
			 console.log("Could not update customer!");
	 }
	 });
	 window.open("/persoonsgegevens.html","_self");
	 });
 $('#annuleren').click(function(){
	 window.open("/persoonsgegevens.html","_self");
 });
 //check of gebruiker admin is
 checkAdmin()
 
 $('#loguit').click(function(){
	 uitloggen();
 })
}
function checkAdmin(){
	rol = localStorage.getItem("rol");
	//als admin laat dan ook deleten medewerker zien
	 if(rol== "admin"){
			$('#verw').show();
			$("#persoonsgegevens").attr('class', 'border-admin');
			$("#projecten").attr('class', 'border-admin');
		};
}
//als uitlog clear localstorage en ga terug naar inlog scherm
function uitloggen(){
	localStorage.clear();
	window.open("/IPASS/login.html","_self");
}
gegevens();

