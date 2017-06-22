function login(){
	$.getJSON("https://***.herokuapp.com:443/restservices/medewerker/", function(data) {
		$(data).each(function( index , value ) {
			var username = $('#username').val();
			var password = $('#password').val();
			if(username==value.gebruikersnaam){
				if(password ==value.wachtwoord){
					localStorage.setItem("medewerkers_id", value.medewerkers_id);
					localStorage.setItem("rol", value.rol);
					window.open("/persoonsgegevens.html","_self");
				}
				else{
					alert("Gebruikersnaam/wachtwoord komen niet overeen!");
				}
			}
		});
	});
}
		
		
	/*var data = $("#loginform").serialize();
	console.log(data);
	var uri="restservices/medewerker/authentication";
	 $.ajax(uri, {
		 type: "post",
		 data: data,
		 success: function() {
			 console.log("ingelogd");
		 },
		 error: function() {
			 console.log("error");
		 }
	 });
		
		$("#login").on("click", function() {
			var data = $("#loginform").serialize();
			console.log(data);
			$.post("../restservices/authentication", data, function(response) {
			window.sessionStorage.setItem("sessionToken", response);
			$.ajaxSetup({
				beforeSend: function(xhr){
			 var token = window.sessionStorage.getItem("sessionToken");
			 xhr.setRequestHeader( 'Authorization', 'Bearer ' + token);
				}
			})
			$.getJSON("http://localhost:4711/firstapp/restservices/medewerker/", function(data) {
			$(data).each(function( index , value ) {
				
				var username = $('#username').val();
				var password = $('#password').val();
				if(username==value.gebruikersnaam){
					if(password ==value.wachtwoord){
						localStorage.setItem("medewerkers_id", value.medewerkers_id);
						localStorage.setItem("rol", value.rol);
						window.open("persoonsgegevens.html","_self");
					}
					else{
						alert("Gebruikersnaam/wachtwoord komen niet overeen!");
					}
				}
			});
		});
			
			
			window.open("persoonsgegevens.html","_self");
			}).fail(function(jqXHR, textStatus, errorThrown) {
			console.log(textStatus);
			console.log(errorThrown);
			});

	
		});*/

