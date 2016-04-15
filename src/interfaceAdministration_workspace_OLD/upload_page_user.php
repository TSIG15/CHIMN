<?php
    include ('includes/enTete.php');
    ?>

<div class="container-fluid">
<div class="row">
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main index">


<div class="col-sm-9 col-sm-offset-3 col-md-7 col-md-offset-2 main">
<h1 class="page-header" style="margin-top:0;" onclick="(this,'televersements');">Télécharger la donnée XXX</h1> <!-- Ajouter une variable pour récupérer title donnee-->

<!--téléversement-->
<div id="televersements">

    <h3 class="sub-header">Choisir un format :</h3>
    <!--<h5 style="font-style:italic;">Formats vecteur</h5>-->

    <!-- formulaire des formats vecteurs-->
<label class="radio-inline"><input type="radio" name="optradio" id="shp" value="shp">Shapefile (ESRI)</label>
<label class="radio-inline"><input type="radio" name="optradio" id="dxf" value="dxf">DXF (AutoCad)</label>
<label class="radio-inline"><input type="radio" name="optradio" id="gml" value="gml">GML</label>
<label class="radio-inline"><input type="radio" name="optradio" id="kml" value="kml">KML (Google)</label>
    <!--fin formulaire-->

    <!--formulaire des SRS-->
<h3 class="sub-header">Choisir un système de coordonnées :</h3>
<label class="radio-inline"><input type="radio" name="optradio" id="2154" value="2154">Lambert 93</label>
<label class="radio-inline"><input type="radio" name="optradio" id="3857" value="3857">Web Mercator</label>
<label class="radio-inline"><input type="radio" name="optradio" id="32631" value="32631">WGS 84</label>
    <!--fin formulaire-->

</div>
</br>
<button type="button" class="btn btn-primary btn-block"
        style="color:black;background-color:#ADCA87;border-color:#ADCA87;"
        onclick="fonctionDownload()">TÉLÉCHARGER</button>
<!--Créer la fonction JS(?) pour le chemin-->

<!--fin téléversements-->





</div>
</div>
</div>


<?php
    include ('includes/piedDePage.php');
    ?>