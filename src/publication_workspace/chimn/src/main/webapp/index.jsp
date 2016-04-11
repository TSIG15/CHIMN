<html>
<body>
    <h2>Jersey RESTful Web Application!</h2>
    <p><a href="webapi/myresource">Jersey resource</a>
    <p>Visit <a href="http://jersey.java.net">Project Jersey website</a>
    for more information on Jersey!
</body>
</html>
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
            <li><p id="titre_appli">Livraison et publication de donn�es automatis�es<p></li>
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
            <li class="active"><a href="#">Param�trages<span class="sr-only">(current)</span></a></li>
            <li><a href="preferences.php">Pr�f�rences</a></li>
            <li><a href="index.php">D�connexion</a></li>
            <li>
                  <button style="margin-left:8px;width:188px;font-size:12px;" type="submit" class="btn btn-default">Enregistrer les param�trages</button>
            </li>
          </ul>
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

          <!--BDD-->
          <h1 id="titreBDD" class="page-header" style="margin-top:0;" onclick="toggle_div(this,'formBDD');">Base de donn�es source</h1>
          <!--formulaire-->
          <form id="formBDD" class="form-horizontal" style="display:none;">
            <div class="form-group">
              <label for="connexionBDD" class="col-sm-2 control-label">Nom de la connexion</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="connexionBDD" placeholder="ex : arbres">
              </div>
            </div>
            <div class="form-group">
              <label for="hoteBDD" class="col-sm-2 control-label">H�te</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="hoteBDD" placeholder="ex : localhost">
              </div>
            </div>
            <div class="form-group">
              <label for="portBDD" class="col-sm-2 control-label">Port</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="portBDD" placeholder="ex : 5432">
              </div>
            </div>
            <!--base de donn�es-->
            <div class="form-group">
              <label for="bdd" class="col-sm-2 control-label">Base de donn�es</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="bdd" placeholder="ex : for�ts">
              </div>
            </div>
            <!--utilisateur-->
            <div class="form-group">
              <label for="userBDD" class="col-sm-2 control-label">Nom d'utilisateur</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="userBDD" placeholder="ex : tsig2015">
              </div>
            </div>
            <!--mot de passe-->
            <div class="form-group">
              <label for="passwordBDD" class="col-sm-2 control-label">Mot de passe</label>
              <div class="col-sm-10">
                <input type="password" class="form-control" id="passwordBDD" placeholder="ex : ********">
              </div>
            </div>
            <!--bouton d'envoi-->
            <!--<div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Valider</button>
              </div>
            </div>-->
          </form>

          <!--Geoserver-->
          <h1 class="page-header" onclick="toggle_div(this,'formGS');">Serveur cartographique</h1>
          <!--formulaire-->
          <form id="formGS" class="form-horizontal" style="display:none;">
          <!--h�te-->
            <div class="form-group">
              <label for="hoteGS" class="col-sm-2 control-label">H�te</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="hoteGS" placeholder="ex : localhost">
              </div>
            </div>
            <div class="form-group">
              <label for="portGS" class="col-sm-2 control-label">Port</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="portGS" placeholder="ex : 5432">
              </div>
            </div>
            <!--utilisateur-->
            <div class="form-group">
              <label for="userGS" class="col-sm-2 control-label">Nom d'utilisateur</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="userGS" placeholder="ex : tsig2015">
              </div>
            </div>
            <!--mot de passe-->
            <div class="form-group">
              <label for="passwordGS" class="col-sm-2 control-label">Mot de passe</label>
              <div class="col-sm-10">
                <input type="password" class="form-control" id="passwordGS" placeholder="ex : ********">
              </div>
            </div>
            <!--bouton d'envoi-->
            <!--<div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Valider</button>
              </div>
            </div>-->
          </form>

          <!--Isogeo-->
          <h1 class="page-header">Isogeo</h1>
          <!--formulaire-->
          <form id="formI" class="form-horizontal" action="webapi/myresource" >
          <!--id-->
            <div class="form-group">
              <label for="idI" class="col-sm-2 control-label">ID</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" name="idI" placeholder="id Isogeo">
              </div>
            </div>
            <!--secret-->
            <div class="form-group">
              <label for="secretI" class="col-sm-2 control-label">Secret</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" name="secretI" placeholder="secret Isogeo">
              </div>
            </div>
            <!--groupe de travail-->
            <div class="form-group">
              <label for="groupeTI" class="col-sm-2 control-label">Groupe de travail</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" name="groupeTI" placeholder="groupe de travail Isogeo">
              </div>
            </div>
            <!--bouton d'envoi-->
            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Valider</button>
              </div>
            </div>
          </form>

          <h1 class="page-header" onclick="toggle_div(this,'formT');">T�l�versement des donn�es</h1>
          <!--formulaire-->
          <form id="formT" class="form-horizontal" style="display:none;">
          <!--serveur distant-->
            <div class="form-group">
              <label for="urlSD" class="col-sm-2 control-label">Serveur distant</label>
              <div class="col-sm-10">
                <input type="url" class="form-control" id="urlSD" placeholder="http://localhost:8888/">
              </div>
            </div>
            <!--bouton d'envoi-->
            <!--<div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Valider</button>
              </div>
            </div>-->
          </form>

        </div>
      </div>
    </div>

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