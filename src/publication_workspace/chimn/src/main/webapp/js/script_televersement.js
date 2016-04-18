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
    document.getElementById("titledata").innerHTML = titledata;
    var tlurl;
    var result;
    var FormatChoice;
    var srsChoice;
    var clicks = 0;

    $("#tlvs").click(function(){

    //alert(titledata);

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
    else if (document.getElementById('kmlg').checked) {
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



/* variables pour les liens des webservices*/
    var wmts = document.location.origin+"/geoserver/gwc/service/wmts?request=GetCapabilities";
    //document.getElementById("wmts").innerHTML = wmts;
    var wms = document.location.origin+"/geoserver/chimn_workspace/wms?service=WMS&version=1.1.0&request=GetMap&layers=chimn_workspace:"+title.substring(title.lastIndexOf(".")+1);
    //document.getElementById("wms").innerHTML = wms;
    var wfs = document.location.origin+"chimn_workspace/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=chimn_workspace:"+title.substring(title.lastIndexOf(".")+1)+"&outputFormat=";
    var format;

/*pour afficher les liens webservices*/
$("#liensWeb").click(function(){

        $("#wmsweb").empty();
        $("#wmtsweb").empty();
        $("#wfsweb").empty();

        $("<a href="+wms+">"+wms+"</a><br/>").appendTo("#wmsweb");/*style="+"list-style-type:"+"square;"*/
        $("<a href="+wmts+">"+wmts+"</a><br/>").appendTo("#wmtsweb");

        $("input[type='checkbox']:checked").each(function() {

                  format = $(this).attr('id');

                   $("<a href="+wfs+format+">"+wfs+format+"</a><br/>").appendTo("#wfsweb");

                  });
})
