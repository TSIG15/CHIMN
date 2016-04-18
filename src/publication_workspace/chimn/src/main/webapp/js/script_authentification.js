/* Au click() du bouton id=auth dans authentifcation*/
console.log();
$("#auth").click(function(){

var ident = $("#login").val();
var pwd = $("#mdp").val();

$.ajax({
//The URL to process the request
  url : 'webapi/myresource/authentification',
//The type of request, also known as the "method" in HTML forms
//Can be 'GET' or 'POST'
  type : 'POST',
//Any post-data/get-data parameters
//This is optional
  data : {"usern": ident,"passw" : pwd},

  dataType: 'text',

//The response from the server
  complete : function(success) {

	if(success.responseText == "success") {
        window.location.href="parametrages.jsp";
      }
    else {
    	alert("Votre identifiant ou votre de mot de passe est incorrect !");
    }
    }
  });
})
