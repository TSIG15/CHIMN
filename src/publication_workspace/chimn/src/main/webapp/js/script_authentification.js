var title = document.location.search;
var titledata = title.substring(title.lastIndexOf("=")+1);

/* Au click() du bouton id=auth dans authentifcation*/
$("#auth").submit(function(e){
	 e.preventDefault();

$.ajax({
//The URL to process the request
  'url' : 'webapi/myresource/authentification',
//The type of request, also known as the "method" in HTML forms
//Can be 'GET' or 'POST'
  'type' : 'POST',
//Any post-data/get-data parameters
//This is optional
  'data' : {
	   ident : $('[name="usern"]').val(),
	   pwd : $('[name="passw"]').val()
	  }

	})
	.done(function(success) {
		//alert("vous y etes");
		console.log(success);

	if(success == "successAdmin") {
		//alert("Votre identifiant ou votre de mot de passe est correct !");
        window.location.href="parametrages.jsp";
      	}
    else if (success == "successUser") {
				window.location.href="extractiondynamique.jsp?name="+titledata;
		}
    else {
    		alert("Votre identifiant ou votre de mot de passe est incorrect !");
    }

    });
});

/*pour passer le titre de la variable à la page authentification*/
$(document).ready(function(){
   e.preventDefault();

   $.ajax({
     'url' : 'authentification.jsp',
     'type' : 'GET',
     'data' : {
    	 	name : titledata
     }
   })
   .done(function(data) {
         window.location.href="authentification.jsp?name="+titledata;
   });
 })
