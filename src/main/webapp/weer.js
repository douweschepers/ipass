function initPage(){
$.getJSON("http://ip-api.com/json/", function(data) {
    document.querySelector('#code').innerHTML = data.countryCode;
    document.querySelector('#land').innerHTML = data.country;
    document.querySelector('#regio').innerHTML = data.region;
    document.querySelector('#stad').innerHTML = data.city;
    $('#stad').click(function() {
		   showWeather(data.lat,data.lon,data.city);
		 });
    document.querySelector('#postcode').innerHTML = data.country;
    document.querySelector('#lat').innerHTML = data.lat;
    document.querySelector('#long').innerHTML = data.lon;
    document.querySelector('#ip').innerHTML = data.query;
    document.querySelector('#kop2').innerHTML = "Het weer in " + data.city;
    
    var latitude = data.lat;
    var longitude = data.lon;
    var city = data.city;
    
    window.localStorage.clear();
    showWeather(latitude, longitude, city);
    loadCountries();
    
    
});}


function showWeather(latitude, longitude, city){
	document.querySelector('#kop2').innerHTML = "Het weer in " + city;
	var code = city;
	if(localStorage.getItem(city)){
		console.log("hij zit erin"); 
		
		dataParsed = JSON.parse(localStorage.getItem(city));
		console.log(dataParsed);
		document.querySelector('#temp').innerHTML =  graden(dataParsed.main.temp)+ " " + String.fromCharCode(176) +"C";
		document.querySelector('#vocht').innerHTML = dataParsed.main.humidity + "%";
		document.querySelector('#snelheid').innerHTML = dataParsed.wind.speed.toFixed(1) + " km/u";
		document.querySelector('#richting').innerHTML = degreeToDirection(dataParsed.wind.deg);
		var times = timeConverter(dataParsed.sys.sunrise,dataParsed.sys.sunset);
		document.querySelector('#opgang').innerHTML = times[0];
		document.querySelector('#ondergang').innerHTML = times[1];
	}
	else{
		console.log("nu laad ik nieuw in");
			
	
	$.getJSON("http://api.openweathermap.org/data/2.5/weather?lat="+ latitude +"&lon=" + longitude + "&appid=4df3876236dd114fc5d99ea0d09c8753",function(data){
		var graad = data.main.temp;
		var deg = data.wind.deg;
		var sunrise = data.sys.sunrise;
		var sunset = data.sys.sunset;
		var snelheid = data.wind.speed * 3.6;
		timeConverter(sunrise,sunset);
		degreeToDirection(deg);
		graden(graad);		
		document.querySelector('#temp').innerHTML = graden(graad) + " " + String.fromCharCode(176) +"C";
		document.querySelector('#vocht').innerHTML = data.main.humidity + "%";
		document.querySelector('#snelheid').innerHTML = snelheid.toFixed(1) + " km/u";
		document.querySelector('#richting').innerHTML = degreeToDirection(deg);
		var times = timeConverter(sunrise,sunset);
		document.querySelector('#opgang').innerHTML = times[0];
		document.querySelector('#ondergang').innerHTML = times[1];
		

		
		
	    localStorage.setItem(city,JSON.stringify(data));
	    console.log(localStorage);
		
		/*window.localStorage.setItem('temp',graden(graad));
		window.localStorage.setItem('vocht',data.main.humidity);
		window.localStorage.setItem('snelheid', snelheid.toFixed(1));
		window.localStorage.setItem('richting', degreeToDirection(deg));
		window.localStorage.setItem('opgang', times[0]);
		window.localStorage.setItem('ondergang',times[1]);*/
	})
	}
	
};

function graden(graad){
	graadNew = (graad - 273.15).toFixed(1);
	return graadNew;
}

function degreeToDirection(deg){
	var direction = "";
	switch(true){
	case(deg = 0):
		direction = "Noord";
		break;
	case(deg < 90):
		direction = "Noordoost";
		break;
	case(deg = 90):
		direction = "Oost";
		break;
	case(deg < 180):
		direction = "Zuidoost";
		break;
	case(deg = 180):
		direction = "Zuid";
		break;
	case(deg < 270):
		direction = "Zuidwest";
		break;
	case(deg = 270):
		direction = "West";
		break;
	case(deg < 360):
		direction = "Noordwest";
		break;
	case(deg = 360):
		direction = "Noord";
		break;
	}
	
	return direction;
}

function timeConverter(sunrise,sunset){
	var sunrise = new Date(sunrise*1000);
	var hoursRise = sunrise.getHours();
	var minutesRise = "0" + sunrise.getMinutes();
	var secondsRise = "0" + sunrise.getSeconds();

	var sunset = new Date(sunrise*1000);
	var hoursSet = sunset.getHours();
	var minutesSet = "0" + sunset.getMinutes();
	var secondsSet = "0" + sunset.getSeconds();

	var formattedTimeRise = hoursRise + ':' + minutesRise.substr(-2) + ':' + secondsRise.substr(-2);
	var formattedTimeSet= hoursSet + ':' + minutesSet.substr(-2) + ':' + secondsSet.substr(-2);
	
	var list = [
	    formattedTimeRise,
	    formattedTimeSet,
	];
	return list;

}

function loadCountries(){
	$.getJSON("http://localhost:4711/firstapp/restservices/country", function(data) {
		$(data).each(function( index , value ) {
				$('#landen').append('<tr id="rows'+index+'">' +
						'<td>' + value.name + '</td>'+ 
						'<td>' + value.capital + '</td>'+ 
						'<td>' + value.region + '</td>'+ 
						'<td>' + value.surface + '</td>'+ 
						'<td>' + value.population + '</td>'+ 
						'</tr>');
				$('#rows'+index ).click(function() {
					showWeather(value.lat,value.lng,value.capital);	
					timePassed();
						});
					
					   
					 });
								
			});
	};


function timePassed(){
	var past = new Date().getTime();
	var fiveMin = 1000 * 60 * 1; 
	if (new Date().getTime() - past < fiveMin){
		return true;
	}
	else{
		return false;
	}
}


initPage();
