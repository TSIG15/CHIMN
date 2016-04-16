<?php
include ('includes/authAdmin.php');
include ('includes/enTete.php');
?>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main authentification">

        <!--deuxième bloc administrateur-->
        <div class="col-sm-6">
          <div class="panel panel-primary">
            <div class="panel-heading">
              <!-- formulaire-->
              <form class="form-horizontal" action="<?php echo htmlspecialchars($_SERVER['REQUEST_URI'], ENT_QUOTES); ?>" method="post">
                <div class="form-group">
                  <label for="login" class="col-sm-4 control-label">Identifiant</label>
                  <div class="col-sm-7">
                    <input type="text" class="form-control" id="login" name ="login" placeholder="ex : toto" value="<?php if(!empty($_POST['login'])) { echo htmlspecialchars($_POST['login'], ENT_QUOTES); } ?>">
                  </div>
                </div>
                <div class="form-group">
                  <label for="password" class="col-sm-4 control-label">Mot de passe</label>
                  <div class="col-sm-7">
                    <input type="password" class="form-control" id="password" name="mdp" placeholder="ex : ********">
                  </div>
                </div>
                <!--bouton d'envoi-->
                <div class="form-group">
                  <label for="submit" class="col-sm-4 control-label"><?php if(!empty($message)) : echo $message; endif; ?></label>
                  <div class="col-sm-1">
                    <button type="submit" class="btn btn-default">Valider</button>
                  </div>
                </div>

                <div class="form-group">
                  <label for="accueil" class="col-sm-4 control-label"></label>
                  <div class="col-sm-7">
                    <p style="text-align:left;"><a href="index.php">Retour à l'accueil</a></p>
                  </div>
                </div>


              </form>
                <!-- fin formulaire-->
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
<?php
include ('includes/piedDePage.php');
 ?>
