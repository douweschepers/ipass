function login(){
	/*$.getJSON("http://localhost:4711/firstapp/restservices/medewerker/", function(data) {
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
	});*/

		
		
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
	 });*/
		
		$("#login").click(function(event) {
			var data = $("#loginform").serialize();
			$.post("restservices/medewerker/authentication", data, function(response) {
			window.sessionStorage.setItem("sessionToken", response);
			}).fail(function(jqXHR, textStatus, errorThrown) {
			console.log(textStatus);
			console.log(errorThrown);
			});

	
		});
}
login();
