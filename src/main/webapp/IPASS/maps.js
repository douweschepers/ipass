var map;
/*function getPosition(){
	$.getJSON("http://ip-api.com/json/", function(data) {
		var lat = data.lat;
		var long = data.lon;
		loadMap(lat,long);
	});
}*/

function loadMap(){
	var map = new google.maps.Map(document.getElementById('map'), {
	      zoom: 8,
	      center: {lat:52.090737,lng:5.121420}
	    });
    var marker = new google.maps.Marker({
      position: {lat: 52.090737,lng:5.121420},
      map: map
    });
    map.addListener('click', function(event) {
 	   placeMarker(event.latLng.lat(),event.latLng.lng());
 	});
    checkAdmin();
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
function reloadHome(){
	window.location = "maps.html";
}
function checkAdmin(){
	rol = localStorage.getItem("rol");
	if(rol== "admin"){
		$('#verw').show();
	};
}
