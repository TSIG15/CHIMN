/**
 *JQuery to say that the document is ready
 */
$(document).ready(function(){

	//Send the AJAX call to the server
	  $.ajax({
	  //The URL to process the request
	    'url' : 'webapi/myresource/tags',
	  //The type of request, also known as the "method" in HTML forms
	  //Can be 'GET' or 'POST'
	    'type' : 'POST',
	  //Any post-data/get-data parameters
	  //This is optional
	    'data' : {
	      "q":""
	    },
	  //The response from the server
	    'success' : function(data) {
	    //You can use any jQuery/JavaScript here!!!

	    	/*var first=data.substring(1, data.length);
	    	var last =first.slice(0,-1);
	    	var items = last.split(",");*/

	        /*$.each(items, function (i, item) {
	    	    $('#tags').append($('<option>', {
	    	        value: item,
	    	        text : item
	    	    }));
	    	});*/
	      for(k in data)
	    	  {
	    	  $('#tags').append($('<option>', {
	    	        value: k,
	    	        text : data[k]
	    	    }));
	    	  }
	    }
	  });


	  $('select').on('change', function() {

		$('#keywords').val($('#keywords').val() + " "+ this.value);
		  var newkeywordsTofilterOn= $('#keywords').val();
		  $.ajax({
			  //The URL to process the request
			    'url' : '/chimn/webapi/myresource/tags',
			  //The type of request, also known as the "method" in HTML forms
			  //Can be 'GET' or 'POST'
			    'type' : 'POST',
			  //Any post-data/get-data parameters
			  //This is optional
			    'data' : {
			      "q":newkeywordsTofilterOn
			    },
			  //The response from the server
			    'success' : function(data) {
			    	$('#tags').html('');
			    	for(k in data)
		    	  {
			    	  $('#tags').append($('<option>', {
			    	        value : k,
			    	        text : data[k]
			    	    }));
			      }
			    }
			  });

		});

})
