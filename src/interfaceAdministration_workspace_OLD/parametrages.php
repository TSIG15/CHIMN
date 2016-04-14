<?php
include ('includes/insertParametrages.php');
include ('includes/enTete.php');
?>

    <div class="container-fluid">
      <div class="row">
        <div id="navbarSidebar" class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li class="active"><a href="#">Paramétrages<span class="sr-only">(current)</span></a></li>
            <li><a href="preferences.php">Préférences</a></li>
            <li><a href="index.php">Déconnexion</a></li>
            <li>
                  <button style="margin-left:8px;width:188px;font-size:12px;" type="submit" class="btn btn-default">Enregistrer les paramétrages</button>
            </li>
          </ul>
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

          <!--BDD-->
          <h1 id="titreBDD" class="page-header" style="margin-top:0;" onclick="toggle_div(this,'formBDD');">Base de données source</h1>
          <!--formulaire-->
          <form id="formBDD" class="form-horizontal" style="display:none;">
            <div class="form-group">
              <label for="connexionBDD" class="col-sm-2 control-label">Nom de la connexion</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="connexionBDD" placeholder="ex : arbres">
              </div>
            </div>
            <div class="form-group">
              <label for="hoteBDD" class="col-sm-2 control-label">Hôte</label>
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
            <!--base de données-->
            <div class="form-group">
              <label for="bdd" class="col-sm-2 control-label">Base de données</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="bdd" placeholder="ex : forêts">
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
          <!--hôte-->
            <div class="form-group">
              <label for="hoteGS" class="col-sm-2 control-label">Hôte</label>
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
          <h1 class="page-header" onclick="toggle_div(this,'formI');">Isogeo</h1>
          <!--formulaire-->
          <form id="formI" class="form-horizontal" style="display:none;" action="<?php echo htmlspecialchars($_SERVER['REQUEST_URI'], ENT_QUOTES); ?>" method="post">
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

          <h1 class="page-header" onclick="toggle_div(this,'formT');">Téléversement des données</h1>
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

<?php
include ('includes/piedDePage.php');
?>
