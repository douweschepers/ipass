function login(){
	//roep alle medewerkers aan
	$.getJSON("/restservices/medewerker/", function(data) {
		$(data).each(function( index , value ) {
			var username = $('#username').val();
			var password = $('#password').val();
			//kijk waar de inlog naam gelijk staat aan  een in de db
			if(username==value.gebruikersnaam){
				//kijk dan of wachtwoord gelijk staan aan die daarbij horende in db
				if(password ==value.wachtwoord){
					//als gelijk set dan de localstorage
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
		
		
	