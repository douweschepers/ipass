var map;
var geocoder;


function loadMap(){
	//laad de googlemaps api in
	map = new google.maps.Map(document.getElementById('map'), {
	      zoom: 8,
	      //centreer op utrecht
	      center: {lat:52.090737,lng:5.121420}
	    });
    
    checkAdmin();
    $('#loguit').click(function(){
   	 uitloggen();
    })
    getAdress()
}
//functie om alle longitudes en lattitudes toe te voegen als pins op de map
function codeAddress(longlat,lijst) {
	var contentString= undefined;
	for(var i =0; i< lijst.length;i++){
		contentString = lijst[i].toString();
	}
	contentString = lijst[1].toString();

	var infowindow = new google.maps.InfoWindow({
	    content: contentString
	  });
	
    map.setCenter(longlat);
    var marker = new google.maps.Marker({
        map: map,
        position: longlat
    });
    
	
  }

//functie die uit me restservice alle longitude en latitudes haalt
function getAdress(){
	var lijst= []
	var straat = undefined;
	var huisnummer = undefined;
	var adres= undefined;
	$.getJSON("/restservices/project/", function(data) {
		$(data).each(function( index_pjct , value_pjct ) {
			lijst.push(value_pjct.project_id);
			straat = value_pjct.straatnaam
			huisnummer = value_pjct.huisnr;
			adres= straat + huisnummer;
			getLatLon(adres,lijst);
		});
	});
}
//functie geocoding, google maps zet adres om in latitude longitude
function getLatLon(adres,lijst){
	$.getJSON("https://maps.googleapis.com/maps/api/geocode/json?address="+ adres+"&key=AIzaSyA2hhwsyvOFvwf6YJABI74dDS8ccSyGvf8", function(data) {
		$(data).each(function( index_pjct , value_pjct ) {
			if(value_pjct.status!="ZERO_RESULTS"){
				codeAddress(value_pjct.results[0].geometry.location,lijst);
			}
		});
	});
}
//als er op het logo word geklikt ga dan terug naar de maps
function reloadHome(){
	window.location = "maps.html";
}
//kijk of de gebruiker admin is als zo is voeg dan delete medewerker toe
function checkAdmin(){
	rol = localStorage.getItem("rol");
	if(rol== "admin"){
		$('#verw').show();
		$("#persoonsgegevens").attr('class', 'border-admin');
		$("#projecten").attr('class', 'border-admin');
	};
}
//als op loguit geklikt word clear localstorage en ga naar inlog scherm
function uitloggen(){
	localStorage.clear();
	window.open("/IPASS/login.html","_self");
}
