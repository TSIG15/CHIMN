<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>CHIM'N</title>


	<!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/dashboard.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <!--script src="js/ie-emulation-modes-warning.js"></script-->

	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" href="img/Logo_titre.png"/>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>

    <link rel="stylesheet" href="css/leaflet.css" />
    <link rel="stylesheet" href="css/leaflet.draw.css"/>
    <link rel="stylesheet" href="css/spectrum.css"/>

    <!-- scripts -->
    <script type="text/javascript" src="http://cdn.leafletjs.com/leaflet-0.6.2/leaflet.js"></script>
    <script src="js/leaflet-src.js"></script>

    <script src="js/Leaflet.draw.js"></script>

    <script src="js/edit/handler/Edit.Poly.js"></script>
    <script src="js/edit/handler/Edit.SimpleShape.js"></script>
    <script src="js/edit/handler/Edit.Circle.js"></script>
    <script src="js/edit/handler/Edit.Rectangle.js"></script>

    <script src="js/draw/handler/Draw.Feature.js"></script>
    <script src="js/draw/handler/Draw.Polyline.js"></script>
    <script src="js/draw/handler/Draw.Polygon.js"></script>
    <script src="js/draw/handler/Draw.SimpleShape.js"></script>
    <script src="js/draw/handler/Draw.Rectangle.js"></script>
    <script src="js/draw/handler/Draw.Circle.js"></script>
    <script src="js/draw/handler/Draw.Marker.js"></script>

    <script src="js/ext/TouchEvents.js"></script>
    <script src="js/ext/LatLngUtil.js"></script>
    <script src="js/ext/GeometryUtil.js"></script>
    <script src="js/ext/LineUtil.Intersect.js"></script>
    <script src="js/ext/Polyline.Intersect.js"></script>
    <script src="js/ext/Polygon.Intersect.js"></script>

    <script src="js/Control.Draw.js"></script>
    <script src="js/Tooltip.js"></script>
    <script src="js/Toolbar.js"></script>

    <script src="js/draw/DrawToolbar.js"></script>
    <script src="js/edit/EditToolbar.js"></script>
    <script src="js/edit/handler/EditToolbar.Edit.js"></script>
    <script src="js/edit/handler/EditToolbar.Delete.js"></script>

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

  <div class="container-fluid">
    <div class="row">
      <div id="navbarSidebar" class="col-sm-3 col-md-2 sidebar">
        <ul class="nav nav-sidebar">
          <li class="active"><a href="#">Extraction dynamique<span class="sr-only">(current)</span></a></li>
          <li><a href="index.jsp">Retour à l'accueil</a></li>
        </ul>
      </div>
      <!--<div id="navbarSidebar" class="col-sm-3 col-md-2 sidebar">
        <ul class="nav nav-sidebar">
          <li class="active"><a href="#" onclick="toggle_div(this,'map');">OÙ ?<span class="sr-only">(current)</span></a></li>
          <li><a href="#" onclick="toggle_div(this,'formquoi');">QUOI ?</a></li>
          <li><a href="#" onclick="toggle_div(this,'formqui');">QUI ?</a></li>
        </ul>
      </div>-->

      <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

        <h1 class="page-header" style="margin-top:0;">Choix de l'emprise géographique souhaitée</h1>

<!--LA MAP !!!-->

	      <div id="map"></div>

<!--QUOI-->
       <h1 class="page-header">Formulaire de commande</h1>

       <!--formulaire-->
      <form id="formquoi" class="form-horizontal">

	    <div class="form-group">
            <label for="srs" class="col-sm-2 control-label">SRS <span class="obligatoire">*</span></label>
              <div class="col-sm-10">
                 <SELECT name="srs" class="form-control" id="srs" onchange="verifier(event);" >
			                <option>  </option>
		            </SELECT>
              </div>
	      </div>

  		<div class="form-group">
                <label for="formatsortie" class="col-sm-2 control-label">Format de Sortie <span class="obligatoire">*</span></label>
                <div class="col-sm-10">
                    <SELECT name="formatsortie"  class="form-control" id="formatsortie" onchange="verifier(event);">
			                   <option> format de sortie </option>
		                </SELECT>
		            </div>
      </div>

			<div class="form-group">
                <label for="date" class="col-sm-2 control-label">Date de Commande </label>
                  <div class="col-sm-10" >
                    <input type="text" class="form-control" id="date" disabled="disabled">
			            </div>
      </div>

      <div class="form-group">
                <label for="heure" class="col-sm-2 control-label">Heure de Commande </label>
                  <div class="col-sm-10" >
                    <input type="text" class="form-control" id="heure" disabled="disabled">
                  </div>
      </div>

  <!--QUI-->

      <div class="form-group">
              <label for="username" class="col-sm-2 control-label">Nom <span class="obligatoire">*</span></label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="username" placeholder="ex : jassmin" oninput="verifier(event);">
                </div>
	    </div>

  		<div class="form-group">
                <label for="usermail" class="col-sm-2 control-label">E-Mail <span class="obligatoire">*</span></label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="usermail" placeholder="ex : jassmin@gmail.com" oninput="verifier(event);">
			            </div>
      </div>

      <div class="form-group">
          <label class="col-sm-2 control-label"></label>
              <div class="col-sm-10">
           		  <button class="btn btn-default" id="valider" style="width:188px;font-size:12px;"
                  type="submit" disabled="disabled" onclick="cmd(event);">Valider la commande</button>
				            <p align="left"><span class="obligatoire">*</span> Champ obligatoire </p>
              </div>
      </div>
    </form>
    <!-- fin formulaire-->

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
      <script src="js/commande.js"></script>

    </body>
  </html>
