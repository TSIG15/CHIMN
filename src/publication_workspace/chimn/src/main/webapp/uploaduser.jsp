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


    <!-- sidebar-->
      <div id="navbarSidebar" class="col-sm-3 col-md-2 sidebar">
        <ul class="nav nav-sidebar">
          <li class="active"><a href="#">Téléchargement et web services<span class="sr-only">(current)</span></a></li>
          <li><a href="index.jsp">Retour à l'accueil</a></li>
        </ul>
      </div>

<!--- HTML : PAGE DE TELECHARGEMENT DE DONNEES POUR L UTILISATEUR : Choix du format et du SRS -->
<div class="container-fluid">
<div class="row">
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main index">

<div class="col-sm-5 col-sm-offset-1 col-md-5 col-md-offset-1" style="word-wrap: break-word;">
<h1 class="page-header" style="margin-top:0;">Télécharger la donnée</h1> <!-- Ajouter une variable pour récupérer title donnee-->
<h3 style="font-style:italic;margin-bottom:25px;color:#5C9610" id="titledata"></h3>


    <!--formulaire des formats-->
<h3 class="sub-header">Choisir un format</h3>
<form id="formatchoice">
<label class="radio-inline"><input type="radio" name="optradio" id="shp" value="shp">Shapefile (ESRI)</label>
<label class="radio-inline"><input type="radio" name="optradio" id="dxf" value="dxf">DXF (AutoCad)</label>
<label class="radio-inline"><input type="radio" name="optradio" id="gml" value="gml">GML</label>
<label class="radio-inline" style="margin-left:0px;"><input type="radio" name="optradio" id="kmlg" value="kml">KML (Google)</label>

<h5 style="font-style:italic;font-weight:bold;">Formats raster</h5>
<label class="radio-inline"><input type="radio" name="optradio" id="geotiff" value="geotiff">GEOTIFF</label>
<label class="radio-inline"><input type="radio" name="optradio" id="png" value="png">PNG</label>
</form>
  <!--fin formulaire-->

    <!--formulaire des SRS-->
<h3 class="sub-header">Choisir un système de coordonnées</h3>
<form id="SRSchoice">
<label class="radio-inline"><input type="radio" name="optradio" id="2154" value="2154">Lambert 93</label>
<label class="radio-inline"><input type="radio" name="optradio" id="3857" value="3857">Web Mercator</label>
<label class="radio-inline"><input type="radio" name="optradio" id="32631" value="32631">WGS 84 (32631)</label>
<label class="radio-inline" style="margin-left:0px;"><input type="radio" name="optradio" id="4326" value="4326">WGS 84 (4326)</label>
</form>
    <!--fin formulaire-->
</br>

<!-- bouton de téléchargement-->
<button id="tlvs" type="submit" class="btn btn-primary btn-block"
  style="background-color:#ADCA87;border-color:#ADCA87;">TÉLÉCHARGER</button>
    <p>Donnée téléchargée <span id="clicks">0</span> fois.</p>
</div>

<!--- HTML : WEBSERVICES -->
<div class="col-sm-5 col-sm-offset-1 col-md-5 col-md-offset-1">
<h1 class="page-header" style="margin-top:0px;">Web Services</h1>

<!--liens de publication-->
<h3 class="sub-header">Choisir un format</h3>
<form id="formatweb">
<label class="checkbox-inline"><input type="checkbox" name="csv" id="csv" value="csv">csv</label>
<label class="checkbox-inline"><input type="checkbox" name="kmlweb" id="kml" value="kmlweb">kml</label>
<label class="checkbox-inline"><input type="checkbox" name="shape-zip" id="shape-zip" value="shape-zip">shape-zip</label>
<label class="checkbox-inline"><input type="checkbox" name="json" id="json" value="json">json</label>
<label class="checkbox-inline"><input type="checkbox" name="gml2" id="gml2" value="gml2">gml2</label>
<label class="checkbox-inline"><input type="checkbox" name="gml3" id="gml3" value="gml3">gml3</label>
</form>
<!--bouton-->
<br/>

  <button id="liensWeb" type="submit" class="btn btn-primary btn-block"
        style="background-color:#ADCA87;border-color:#ADCA87;">AFFICHER LES LIENS</button>

<br/>
<!--écriture des liens-->
<div class="webserv">
      <p class="webserv" id="wmsweb"></p>
      <p class="webserv" id="wmtsweb"></p>
      <p class="webserv" id="wfsweb"></p>
</div>



<!--- HTML : EXTRACTION DYNAMIQUE -->
<h1 class="page-header">Extraction dynamique</h1>
<p>La donnée que vous souhaitez télécharger n'est pas disponible aux formats et
  SRS de votre choix ?<br/><br/>Vous avez un compte utilisateur ?<br/><br/>Vous pouvez commandez votre propre donnée en vous connectant avec votre compte.<br/><br/></p>

	<button class="btn btn-primary btn-block"
	style="background-color:#ADCA87;border-color:#ADCA87;">
	<a href="authentification.jsp" style="color:#fff;">SE CONNECTER ET COMMANDER</a>
	</button>
</div>
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
    <script src="js/script_televersement.js"></script>
  </body>
</html>
