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

function saveCriteria()
{
	$('#saveCriteria').click(function( event ) {
		event.preventDefault();
		Console.log("blblablaa");
		console.log("blblablaa");
		alert('blalal');
	});
	
}
/*vérification simple authentification admin*/
/*source : http://www.clubic.com/forum/logiciel-general/exercice-javascript-mot-de-passe-id139868-page1.html*/

/*function Login(form)
{
  var username = form.username.value;
  var password = form.password.value;

  if (username == "admin" && password == "MaDdPmin")
  {
    document.location.href="parametrages.html";
  }
    else
  {
    alert("Mot de passe invalide !");
  }
}*/
