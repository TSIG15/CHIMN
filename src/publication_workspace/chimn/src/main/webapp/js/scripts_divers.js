
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
});*/

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
* Au click() du bouton id=auth dans authentifcation
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

	if(success.responseText == "success") {
        window.location.href="parametrages.jsp";
      }
    else {
    	alert("Votre identifiant ou votre de mot de passe est incorrect !");
    }
    }
  });
});

  var titledata = "testFichier";
  var tlurl;
  var result;
  var FormatChoice;
  var srsChoice;
  var clicks = 0;


  $(document).ready(function(){

    $.ajax({
      //The URL to process the request
      url : 'webapi/myresource/televersement',
      //The type of request, also known as the "method" in HTML forms
      //Can be 'GET' or 'POST'
      type : 'GET',
      //Any post-data/get-data parameters
      //This is optional
      data : {},

      //The response from the server
      'success' : function(data) {
          tlurl=data;
        }
      });
    });

/*dans l'idéal, désactiver le bouton et le réactiver dans success !*/

  $("#tlvs").click(function(){

    //alert (tlurl);

    /*compte le nombre de clic sur télécharger*/
  clicks += 1;
  document.getElementById("clicks").innerHTML = clicks;

  FormatChoice = "vide";
  srsChoice = "vide";

  if (document.getElementById('shp').checked) {
      var FormatChoice = 'shp';
  }
  else if (document.getElementById('dxf').checked) {
      var FormatChoice = 'dxf';
  }
  else if (document.getElementById('gml').checked) {
      var FormatChoice = 'gml';
  }
  else if (document.getElementById('kml').checked) {
      var FormatChoice = 'kml';
  }
  else if (FormatChoice == "vide" && srsChoice !== "vide")
  {
      alert("Veuillez selectionner un format")
  }


  if (document.getElementById('2154').checked) {
      var srsChoice = '2154';
  }
  else if (document.getElementById('3857').checked) {
      var srsChoice = '3857';
  }
  else if (document.getElementById('32631').checked) {
      var srsChoice = '32631';
  }
  else if (srsChoice =="vide" && FormatChoice !== "vide")
  {
      alert("Veuillez selectionner un système de coordonnées")
  }


  if (FormatChoice !=="vide" && srsChoice !=="vide"){
      var result = tlurl + FormatChoice + "/EPSG" + srsChoice + "/" + titledata + ".zip";
      /*url pour le téléversement*/
     
      window.location.href=result;
  }
  else if (FormatChoice =="vide" && srsChoice =="vide"){
      alert("Veuillez selectionner un format et un système de coordonnées")
  }
});



/*window.onload = recupTitle;
function recupTitle(){

  /*var frame = document.getElementById("isogeocatalog");
  var frame2 = frame.contentDocument.innerHTML;
  alert(frame2);

  var CheminComplet = document.location.href;
  var CheminRepertoire = CheminComplet.substring( 0 ,CheminComplet.lastIndexOf( "/" ) );
  var NomDuFichier = CheminComplet.substring(CheminComplet.lastIndexOf( "/" )+1 );
  alert ("NomDuFichier : "+NomDuFichier+" "+"CheminRepertoire : "+CheminRepertoire+ " "+"CheminComplet : "+
  CheminComplet);

}*/
