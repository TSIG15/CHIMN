<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="height:100%" lang="en">
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

  <body style="height:100%">

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


    <div style="height:100%" class="container-fluid">
      <div style="height:100%" class="row">

        <!-- sidebar-->
          <div id="navbarSidebar" class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
              <li class="active"><a href="#">Catalogue de métadonnées<span class="sr-only">(current)</span></a></li>
              <li><a href="index.jsp">Retour à l'accueil</a></li>
            </ul>
          </div>

          <div id="catalog" style="padding-left:15px;padding-right:0px;height:100%" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2">
         
          <!-- open catalog-->
          <iframe id="isogeocatalog" style="height:100%" src="http://open.isogeo.com/s/d107b19c310c44b7954844c369dbd617/Xif-bJGI5P4oRwziFj6EEAunGTcq0"
            name="isogeo" width="100%" height="100%" ></iframe>
            
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
      <script src="js/script_json.js"></script>
    </body>
  </html>
