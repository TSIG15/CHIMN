
/*pour afficher ou masquer une div au clic*/
function toggle_div (bouton, id) {

  $(document).ready(function(){

  var div = document.getElementById(id);

  if(div.style.display == "none") {
    div.style.display = "block";
  }
  else {
    div.style.display = "none";
  }
})
}
/*
* Ecoutons l'évènement click() du bouton auth
*/
$("#auth").click(function(){

var ident = $("#login").val();
var pwd = $("#mdp").val();

$.ajax({
//The URL to process the request
  'url' : 'webapi/myresource/authentification',
//The type of request, also known as the "method" in HTML forms
//Can be 'GET' or 'POST'
  'type' : 'POST',
//Any post-data/get-data parameters
//This is optional
  'data' : {"username": ident,"password" : pwd}

//The response from the server
  
  }).done(function(data) {
		alert(data);
	});;
});

function saveCriteria()
{
	$('#saveCriteria').click(function( event ) {
		event.preventDefault();
		Console.log("blblablaa");
		console.log("blblablaa");
		alert('blalal');
	});

}



/*
* Ecoutons l'évènement click() du bouton auth
*/
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
  data : {"username": ident,"password" : pwd},

  dataType: 'text',

//The response from the server
  complete : function(success) {
  //You can use any jQuery/JavaScript here!!!

	  //alert(success.responseText);
	if(success.responseText == "success") {
        window.location.href="parametrages.jsp";
      }
    else {
    	alert("Votre identifiant ou votre de mot de passe est incorrect !");
    }
    }
  });
});
