function myIntervalFunction() {
	if(txt = document.getElementById("txtArea").value){
		txt = document.getElementById("txtArea").value
		console.log(txt)
	}
}
txt = undefined
var intervalID = setInterval(myIntervalFunction,5000);
