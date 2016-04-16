<?php
    include ('includes/enTete.php');
    include ('includes/requeteBDDupload.php');
    echo $chemin
    ?>

<div class="container-fluid">
<div class="row">
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main index">


<div class="col-sm-9 col-sm-offset-3 col-md-7 col-md-offset-2 main">
<h1 class="page-header" style="margin-top:0;" onclick="(this,'televersements');">Télécharger la donnée titledata</h1> <!-- Ajouter une variable pour récupérer title donnee-->

<!--téléversement-->
<div id="televersements">

    <h3 class="sub-header">Choisir un format :</h3>
    <!--<h5 style="font-style:italic;">Formats vecteur</h5>-->

    <!-- formulaire des formats vecteurs-->
<form id="formatchoice">
<label class="radio-inline"><input type="radio" name="optradio" id="shp" value="shp">Shapefile (ESRI)</label>
<label class="radio-inline"><input type="radio" name="optradio" id="dxf" value="dxf">DXF (AutoCad)</label>
<label class="radio-inline"><input type="radio" name="optradio" id="gml" value="gml">GML</label>
<label class="radio-inline"><input type="radio" name="optradio" id="kml" value="kml">KML (Google)</label>
</form>
    <!--fin formulaire-->

    <!--formulaire des SRS-->
<h3 class="sub-header">Choisir un système de coordonnées :</h3>
<form id="SRSchoice">
<label class="radio-inline"><input type="radio" name="optradio" id="2154" value="2154">Lambert 93</label>
<label class="radio-inline"><input type="radio" name="optradio" id="3857" value="3857">Web Mercator</label>
<label class="radio-inline"><input type="radio" name="optradio" id="32631" value="32631">WGS 84</label>
</form>
    <!--fin formulaire-->
</div>
</br>

<script language="JavaScript" type="text/javascript">
var titledata = "test.test";
var tlurl = "cheminVide\\";
var result;
var FormatChoice = "0";
var srsChoice = "0";

function cheminFinal() {
    var FormatChoice = "vide";
    var srsChoice = "vide";
    
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
        var result = tlurl + FormatChoice + "\\" + "EPSG:" + srsChoice + "\\" + titledata;
        alert(result)
    }
    else if (FormatChoice =="vide" && srsChoice =="vide"){
        alert("Veuillez selectionner un format et un système de coordonnées")
    }
}
</script>


<button type="button" class="btn btn-primary btn-block"
        id="button" style="color:black;background-color:#ADCA87;border-color:#ADCA87;"
        onclick="cheminFinal(this)">TÉLÉCHARGER</button>
<!--Créer la fonction JS(?) pour le chemin-->

<!--fin téléversements-->





</div>
</div>
</div>


<?php
    include ('includes/piedDePage.php');
    ?>