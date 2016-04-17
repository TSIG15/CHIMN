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

    <div class="container-fluid">
      <div class="row">
        <div id="navbarSidebar" class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li><a href="parametrages.jsp">Paramétrages</a></li>
            <li class="active"><a href="#">Préférences<span class="sr-only">(current)</span></a></li>
            <li><a href="index.jsp">Déconnexion</a></li>
              </div>
          </ul>
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

        <!--téléversement-->

        <h1 class="page-header" style="margin-top:0;" onclick="toggle_div(this,'televersements');">Téléversements</h1>

        <div id="televersements" style="display:none;">

         <!--formulaire formats -->

         <form id="format-form" name="formFormats" class="form-horizontal" action="" method=""><!-- appel de la resource java dans action -->


          <h3 class="sub-header">Formats</h3>

          <!-- formulaire des formats vecteurs-->
          <h5 style="font-style:italic;">Formats vecteur</h5>

          <label class="checkbox-inline">
            <input type="checkbox" name="shp"> shp
          </label>
          <label class="checkbox-inline">
            <input type="checkbox" name="dxf"> dxf
          </label>
          <label class="checkbox-inline">
            <input type="checkbox" name="gml"> gml
          </label>

		<!-- formulaire des formats raster-->
          <h5 style="font-style:italic;">Formats raster</h5>

          <label class="checkbox-inline">
            <input type="checkbox" name="geotiff"> geotiff
          </label>
          <label class="checkbox-inline">
            <input type="checkbox" name="png"> png
          </label>
          <label class="checkbox-inline">
            <input type="checkbox" name="jpeg" > jpeg
          </label>

           <!--bouton d'envoi des formats mais l'appel de la resource java se trouve au niveau de la balise form -->
            <div class="form-group">
              <div class="boutonPref col-sm-10">
                <button type="submit" class="btn btn-default">Enregistrer les formats</button>
              </div>
            </div>
           </form>

        <!--formulaire SRS -->
         <form id="srs-form"name="formSRS" class="form-horizontal" action="" method=""><!-- appel de la resource java dans action -->

          <h3 class="sub-header">Systèmes de coordonnées</h3>

          <label class="checkbox-inline">
            <input type="checkbox" name="WebMercator" value="3857"> WGS84 (Web Mercator)
          </label>
          <label class="checkbox-inline">
            <input type="checkbox" name="l93" value="2154"> RGF93 (LAMBERT 93)
          </label>

           <label class="checkbox-inline">
            <input type="checkbox" name="wgs84" value="32631"> WGS84(UTM 31N)
          </label>

          <!--bouton d'envoi SRS mais l'appel de la resource java se trouve au niveau de la balise form-->
            <div class="form-group">
              <div class="boutonPref col-sm-10">
                <button type="submit" class="btn btn-default">Enregistrer les SRS</button>
              </div>
            </div>
           </form>

        </div>

        <!-- Publication -->

         <h1 class="page-header" onclick="toggle_div(this,'publications');">Publication</h1>

         <div id="publications" style="display:none;">

          	<!--formulaire Service -->

         	<form name="formSRS" class="form-horizontal" action="" method="get"><!-- appel de la resource java dans action -->

              <h3 class="sub-header">Services</h3>
              <!--formulaire des services-->
              <label class="checkbox-inline">
                <input type="checkbox" name="wms" value="WMS"> WMS
              </label>
              <label class="checkbox-inline">
                <input type="checkbox" name="wfs" value="WFS"> WFS
              </label>
              <label class="checkbox-inline">
                <input type="checkbox" name="wmts" value="WMTS"> WMTS
              </label>

           	<!--formulaire Styles -->

              <h3 class="sub-header">Style</h3>

              <div class="form-group">
                <div class="col-sm-10">
                  <label for="style">Appliquez un style :</label>
                  <input type="file" name="style">
                  <p class="help-block">Veuillez sélectionner votre style.</p>
                </div>
              </div>

            <!--bouton d'envoi publication mais l'appel de la resource java se trouve au niveau de la balise form-->
            	<div class="form-group">
              		<div class="col-sm-10">
                		<button type="submit" class="btn btn-default">Enregistrer les options de publications</button>
              		</div>
            	</div>
           	</form>
          </div>

          <!--Traitements-->

          <h1 class="page-header" onclick="toggle_div(this,'traitements');">Traitements</h1>

          <div id="traitements" style="display:none;">

          <!--formulaire Traitements -->

         <form name="formTrait" class="form-horizontal" action=""><!-- appel de la resource java dans action -->

            <h3 class="sub-header">Critères d'éligibilité</h3>

            <!--formulaire des critères-->

            <!-- licences-->

            <h5>Licence</h5>
            <div class="radio">
              <label>
                <input type="radio" name="optionsRadios" id="optionsRadios1" value="OUI">
                  OUI
                </label>
            </div>
            <div class="radio">
              <label>
                <input type="radio" name="optionsRadios" id="optionsRadios2" value="NON">
                  NON
              </label>
            </div>

            <!--mots-clés-->

            <h5>Tags</h5><!--fixé en dur à 6 choix pour le moment, il faudra revoir l'html en fonction de la liste récupérée -->
            <select  class="form-control" name="motscles" id="tags">
              <!-- <option>1</option>
              <option>2</option>
              <option>3</option>
              <option>4</option>
              <option>5</option>
              <option>6</option>-->
            </select>

			<input style="margin-top:10px;" type="text" class="form-control" id="keywords" placeholder="mots clés">
            <!--propriétaire : inclus dans les tags-->

            <!--périodicité-->

            <h5>Périodicité</h5>

            <label class="sr-only" for="periodicite">Nombre de jours</label>
                <div class="input-group col-sm-3">
                  <input type="number" class="form-control" name="periodicite" placeholder="Nbre de jours">
                  <div class="input-group-addon">jours</div>
                </div>

            <!--bouton d'envoi traitements mais l'appel de la resource java se trouve au niveau de la balise form-->

            	<div class="form-group">
              		<div class="boutonPref col-sm-10">
                		<button class="btn btn-default" id="saveCriteria">Enregistrer les options de traitements</button>
              		</div>
            	</div>
           </form>

          <!--traitement immédiat-->
          <form action="webapi/myresource/run/" method="">

          <h3 class="sub-header">Lancer les traitements immédiatement</h3>
              <button class="btn btn-default" type="submit">Go !</button>
			    </form>

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
        <script src="js/scripts_divers.js"> </script>
        <script src="js/script_tags_licence.js"> </script>
        <script>
        	$('#format-form').submit(function(e) {
        		e.preventDefault();

        		$.ajax({
        			'url' : '/chimn/webapi/myresource/formats',
        			'type' : 'POST',
     				'data' : {
     	        		shp: $('[name="shp"]').prop('checked'),
     	        		dxf: $('[name="dxf"]').prop('checked'),
     	        		gml: $('[name="gml"]').prop('checked'),
     	        		png: $('[name="png"]').prop('checked'),
     	        		geotiff: $('[name="geotiff"]').prop('checked'),
     	        		jpeg: $('[name="jpeg"]').prop('checked')
     	        	}
        		})
        		.done(function(data) {
        			console.log(data);
        		});
        	});
        </script>

         <script>
        	$('#srs-form').submit(function(e) {
        		e.preventDefault();

        		$.ajax({
        			'url' : '/chimn/webapi/myresource/srs',
        			'type' : 'POST',
     				'data' : {
     					WebMercator: $('[name="WebMercator"]').prop('checked'),
     					l93: $('[name="l93"]').prop('checked'),
     					wgs84: $('[name="wgs84"]').prop('checked')

     	        	}
        		})
        		.done(function(data) {
        			console.log(data);
        		});
        	});
        </script>

           </body>
    </html>
