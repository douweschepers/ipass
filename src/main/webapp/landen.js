function loadCountries(){
	$.getJSON("http://localhost:4711/firstapp/restservices/country", function(data) {
		$(data).each(function( index , value ) {
				$('#landenInhoud').append('<tr id="rows' + index + '">' +
						'<td>' + value.name + '</td>'+ 
						'<td>' + value.capital + '</td>'+ 
						'<td>' + value.code + '</td>'+ 
						'<td>' + value.region + '</td>'+ 
						'<td>' + value.surface + '</td>'+ 
						'<td>' + value.population + '</td>'+ 
						'<td>' + '<form><input type="button" id="delete'+index+'" value="Delete" /></form>' + '</td>' +
						'</tr>');
				$('#delete'+index).click(function() {
					console.log("MOTHERFOKIN CLICK BUTTON")
					var uri = "restservices/country/" + value.code;
					
					$.ajax(uri, {
						type: "delete",
						success: function(response) {
							alert(value.name + " has been deleted!");
						},
						error: function(response) {
							alert(value.name + " has NOT been deleted!");
						}
					})
				});
				filter();
		});
	})

};

function filter(){
	 // Declare variables 
	  var input, filter, table, tr, td, i;
	  input = document.getElementById("zoek");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("landenInhoud");
	  tr = table.getElementsByTagName("tr");
	  var nummer = $("#optie").val();
	  // Loop through all table rows, and hide those who don't match the search query
	  for (i = 0; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[nummer];
	    if (td) {
	      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    } 
	  }
}
loadCountries();
