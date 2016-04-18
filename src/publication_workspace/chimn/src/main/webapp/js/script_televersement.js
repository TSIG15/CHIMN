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
    })

/*click bouton télécharger*/

    var title = document.location.search;
    var titledata = title.substring(title.lastIndexOf("=")+1);
    var titleweb = title.substring(title.lastIndexOf(".")+1);
    var tlurl;
    var result;
    var FormatChoice;
    var srsChoice;
    var clicks = 0;


    $("#tlvs").click(function(){

      //alert (tlurl);
    /*récupère le nom de la donnée dans l'url pour le téléchargement*/

    alert(tlurl + titleweb);

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
