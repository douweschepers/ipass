var map;
var geocoder;
/*function getPosition(){
	$.getJSON("http://ip-api.com/json/", function(data) {
		var lat = data.lat;
		var long = data.lon;
		loadMap(lat,long);
	});
}*/

function loadMap(){
	map = new google.maps.Map(document.getElementById('map'), {
	      zoom: 8,
	      center: {lat:52.090737,lng:5.121420}
	    });
    var marker = new google.maps.Marker({
      position: {lat: 52.090737,lng:5.121420},
      map: map
    });
    /*map.addListener('click', function(event) {
 	   placeMarker(event.latLng.lat(),event.latLng.lng());
 	});*/
    
    checkAdmin();
    $('#adres').click(function(){getAdress()})
    $('#loguit').click(function(){
   	 uitloggen();
    })
}
function codeAddress(longlat) {
	var contentString = "infoooooo";
	var infowindow = new google.maps.InfoWindow({
	    content: contentString
	  });
    map.setCenter(longlat);
    var marker = new google.maps.Marker({
        map: map,
        position: longlat
    });
       
	marker.addListener('click', function() {
	    infowindow.open(map, marker);
	  });   
	
  }


function placeMarker(lati,longi) {
	console.log("nieuwe marker");

    /*var marker2 = new google.maps.Marker({
        position: {lat:lati,lng:longi},
        map: google.maps.Map(document.getElementById('map'),{
        	zoom:8,
        	center:{lat:lati,lng:longi}
        })
    });*/
}
function getAdress(){
	var lijst= []
	var straat = undefined;
	var huisnummer = undefined;
	var adres= undefined;
	$.getJSON("http://localhost:4711/firstapp/restservices/project/", function(data) {
		$(data).each(function( index_pjct , value_pjct ) {
			straat = value_pjct.straatnaam
			huisnummer = value_pjct.huisnr;
			adres= straat + huisnummer;
			$.getJSON("https://maps.googleapis.com/maps/api/geocode/json?address="+ adres+"&key=AIzaSyA2hhwsyvOFvwf6YJABI74dDS8ccSyGvf8", function(data) {
				$(data).each(function( index_pjct , value_pjct ) {
					if(value_pjct.status!="ZERO_RESULTS"){
						codeAddress(value_pjct.results[0].geometry.location);
					}
				});
			});
		});
	});
}
function reloadHome(){
	window.location = "maps.html";
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
