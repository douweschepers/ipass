var map;
var geocoder;


function loadMap(){
	map = new google.maps.Map(document.getElementById('map'), {
	      zoom: 8,
	      center: {lat:52.090737,lng:5.121420}
	    });
    
    checkAdmin();
    $('#adres').click(function(){})
    $('#loguit').click(function(){
   	 uitloggen();
    })
    getAdress()
}
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
    
	/*marker.addListener('click', function() {
	    infowindow.open(map, marker);
	  });   */
	
  }


function getAdress(){
	var lijst= []
	var straat = undefined;
	var huisnummer = undefined;
	var adres= undefined;
	$.getJSON("http://localhost:4711/firstapp/restservices/project/", function(data) {
		$(data).each(function( index_pjct , value_pjct ) {
			lijst.push(value_pjct.project_id);
			straat = value_pjct.straatnaam
			huisnummer = value_pjct.huisnr;
			adres= straat + huisnummer;
			getLatLon(adres,lijst);
		});
	});
}
function getLatLon(adres,lijst){
	$.getJSON("https://maps.googleapis.com/maps/api/geocode/json?address="+ adres+"&key=AIzaSyA2hhwsyvOFvwf6YJABI74dDS8ccSyGvf8", function(data) {
		$(data).each(function( index_pjct , value_pjct ) {
			if(value_pjct.status!="ZERO_RESULTS"){
				codeAddress(value_pjct.results[0].geometry.location,lijst);
			}
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
		$("#persoonsgegevens").attr('class', 'border-admin');
		$("#projecten").attr('class', 'border-admin');
	};
}
function uitloggen(){
	localStorage.clear();
	window.open("http://localhost:4711/firstapp/IPASS/login.html","_self");
}
