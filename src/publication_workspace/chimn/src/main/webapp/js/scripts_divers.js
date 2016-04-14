/*pour afficher ou masquer une div au clic*/
function toggle_div (bouton, id) {
  var div = document.getElementById(id);

  if(div.style.display == "none") {
    div.style.display = "block";
  }
  else {
    div.style.display = "none";
  }
}

//load function that gets tags
/*$(document).ready(function() {
	var items=['aa','bb','cc'];
	//Send the AJAX call to the server
	  $.ajax({
	  //The URL to process the request
	    'url' : 'webapi/myresource/tags',
	  //The type of request, also known as the "method" in HTML forms
	  //Can be 'GET' or 'POST'
	    'type' : 'GET',
	  //Any post-data/get-data parameters
	  //This is optional
	    'data' : {
	      
	    },
	  //The response from the server
	    'success' : function(data) {
	    //You can use any jQuery/JavaScript here!!!
	    	var first=data.substring(1, data.length);
	    	var last =first.slice(0,-1);
	    	var items = last.split(",");
	        alert(data);
	        $.each(items, function (i, item) {
	    	    $('#tags').append($('<option>', { 
	    	        value: 1,
	    	        text : item 
	    	    }));
	    	});
	      
	    }
	  });	
})*/

function saveCriteria()
{
	$('#saveCriteria').click(function( event ) {
		event.preventDefault();
		Console.log("blblablaa");
		console.log("blblablaa");
		alert('blalal');
	});
	
}

