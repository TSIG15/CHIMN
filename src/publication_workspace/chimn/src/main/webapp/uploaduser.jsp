<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" href="img/Logo_titre.png"/>

    <title>CHIM'N</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/dashboard.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <!--navbar du haut-->
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">

        <div class="navbar-header">
          <img src="img/Logo.png" alt="logoTSIG"/>
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbarSidebar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>

        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-left">
            <li><p id="titre_appli">Livraison et publication de données automatisées<p></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Aide</a></li>
          </ul>
        </div>
      </div>
    </nav>
    <!--fin navbar-->

<!--- HTML : PAGE DE TELECHARGEMENT DE DONNEES POUR L UTILISATEUR : Choix du format et du SRS -->
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

<button id="tlvs" type="submit" class="btn btn-primary btn-block"
  style="background-color:#ADCA87;border-color:#ADCA87;">TÉLÉCHARGER</button>
    <p>Donnée téléchargée <span id="clicks">0</span> fois.</p>
    <a href="http://ZOE/publication">Lien de publication</a>

<!--fin téléversements-->

</div>
</div>
</div>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="js/jquery.min.js"><\/script>')</script>
    <script src="js/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="js/holder.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="js/ie10-viewport-bug-workaround.js"></script>
    <!-- script maison-->
    <script src="js/scripts_divers.js"></script>
  </body>
</html>
