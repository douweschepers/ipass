function login(){
	$.getJSON("src/main/java/nl/hu/v1wac/firstapp/webservices/restservices/medewerker/", function(data) {
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
}
		
		
	