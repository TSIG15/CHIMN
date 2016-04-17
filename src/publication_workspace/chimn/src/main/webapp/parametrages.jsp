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
            <li class="active"><a href="#">Paramétrages<span class="sr-only">(current)</span></a></li>
            <li><a href="preferences.jsp">Préférences</a></li>
            <li><a href="index.jsp">Déconnexion</a></li>
            <li>
               <!--<button style="margin-left:8px;width:188px;font-size:12px;" type="submit" class="btn btn-default">Enregistrer les préferences</button>-->
            </li>
          </ul>
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

          <!--formulaire général pour récupérer tous les paramètres-->
          <form id="form" name="formGeneral" class="form-horizontal" action="webapi/myresource/parameters/" method="post">

           <!--Base de données source-->
           <h1 id="titreBDD" class="page-header" style="margin-top:0;" onclick="toggle_div(this,'formBDD');">Base de données source</h1>

           <div id="formBDD" style="display:none;">

            <!--hôte-->
            <div class="form-group">
              <label for="hoteBDD" class="col-sm-2 control-label">Hôte</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" name="hoteBDD" placeholder="ex : localhost" value="localhost">
              </div>
            </div>
            <!--port-->
            <div class="form-group">
              <label for="portBDD" class="col-sm-2 control-label">Port</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" name="portBDD" placeholder="ex : 5432" value="5432">
              </div>
            </div>
            <!--base de données-->
            <div class="form-group">
              <label for="bdd" class="col-sm-2 control-label">Base de données</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" name="bdd" placeholder="ex : forêts" value="chimnsource">
              </div>
            </div>
            <!--utilisateur-->
            <div class="form-group">
              <label for="userBDD" class="col-sm-2 control-label">Nom d'utilisateur</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" name="userBDD" placeholder="ex : tsig2015" value="postgres">
              </div>
            </div>
            <!--mot de passe-->
            <div class="form-group">
              <label for="passwordBDD" class="col-sm-2 control-label">Mot de passe</label>
              <div class="col-sm-10">
                <input type="password" class="form-control" name="passwordBDD" placeholder="ex : ********" value="postgres">
              </div>
            </div>
           </div>

          <!--Geoserver-->
          <h1 class="page-header" onclick="toggle_div(this,'formGS');">Serveur cartographique</h1>

          <div id="formGS" style="display:none;">

          	<!--hôte-->
            <div class="form-group">
              <label for="hoteGS" class="col-sm-2 control-label">Hôte</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" name="hoteGS" placeholder="ex : localhost" value="localhost">
              </div>
            </div>
            <!--port-->
            <div class="form-group">
              <label for="portGS" class="col-sm-2 control-label">Port</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" name="portGS" placeholder="ex : 5432" value="8081">
              </div>
            </div>
            <!--utilisateur-->
            <div class="form-group">
              <label for="userGS" class="col-sm-2 control-label">Nom d'utilisateur</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" name="userGS" placeholder="ex : tsig2015" value="admin">
              </div>
            </div>
            <!--mot de passe-->
            <div class="form-group">
              <label for="passwordGS" class="col-sm-2 control-label">Mot de passe</label>
              <div class="col-sm-10">
                <input type="password" class="form-control" name="passwordGS" placeholder="ex : ********" value="geoserver">
              </div>
            </div>
           </div>


          <!--Isogeo-->
          <h1 class="page-header" onclick="toggle_div(this,'formI');">Isogeo</h1>

          <div id="formI" style="display:none;">

          	<!--id-->
            <div class="form-group">
              <label for="idI" class="col-sm-2 control-label">ID</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" name="idI" placeholder="id Isogeo" value="projet-ensg-d2e472b0f92940ee87f9d1ac6e3e90d0">
              </div>
            </div>
            <!--secret-->
            <div class="form-group">
              <label for="secretI" class="col-sm-2 control-label">Secret</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" name="secretI" placeholder="secret Isogeo" value="jvdMBbVJXiiOSQshFxHFPdlZCNhfvCdJlSkKrZA3npEHns9zOBY1bQuYqtV3xLTd">
              </div>
            </div>
		</div>

          <!--téléversement-->
          <h1 class="page-header" onclick="toggle_div(this,'formT');">Téléversement des données</h1>

          <div id="formT" style="display:none;">

          <!--serveur distant-->
            <div class="form-group">
              <label for="urlSD" class="col-sm-2 control-label">Serveur distant</label>
              <div class="col-sm-10">
                <input type="url" class="form-control" name="urlSD" placeholder="http://localhost:8888/">
              </div>
            </div>
           </div>

           <!--bouton d'envoi-->
            <div class="form-group">
              <div class="col-sm-10">
                <button type="submit" class="btn btn-default">Enregistrer les paramétrages</button>
              </div>
            </div>

         </form>

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
