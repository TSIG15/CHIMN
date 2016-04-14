<?php
include ('includes/enTete.php');
?>

    <div class="container-fluid">
      <div class="row">
        <div id="navbarSidebar" class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li><a href="parametrages.php">Paramétrages</a></li>
            <li class="active"><a href="#">Préférences<span class="sr-only">(current)</span></a></li>
            <li><a href="index.php">Déconnexion</a></li>
            <li>
                  <button style="margin-left:8px;width:188px;font-size:12px;" type="submit" class="btn btn-default">Enregistrer les préferences</button>
            </li>
              </div>
          </ul>
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header" style="margin-top:0;" onclick="toggle_div(this,'televersements');">Téléversements</h1>

        <!--téléversement-->
        <div id="televersements" style="display:none;">
          <h3 class="sub-header">Formats</h3>
          <h5 style="font-style:italic;">Formats vecteur</h5>
          <!-- formulaire des formats vecteurs-->
          <label class="checkbox-inline">
            <input type="checkbox" id="shp" value="shp"> shp
          </label>
          <label class="checkbox-inline">
            <input type="checkbox" id="dxf" value="dxf"> dxf
          </label>
          <label class="checkbox-inline">
            <input type="checkbox" id="gml" value="gml"> gml
          </label>
          <!--fin formulaire-->

          <h5 style="font-style:italic;">Formats raster</h5>
          <!-- formulaire des formats vecteurs-->
          <label class="checkbox-inline">
            <input type="checkbox" id="geotiff" value="geotiff"> geotiff
          </label>
          <label class="checkbox-inline">
            <input type="checkbox" id="png" value="png"> png
          </label>
          <label class="checkbox-inline">
            <input type="checkbox" id="jpeg" value="jpeg"> jpeg
          </label>
          <!--fin formulaire-->

          <h3 class="sub-header">Systèmes de coordonnées</h3>
          <!--formulaire des SRS-->
          <label class="checkbox-inline">
            <input type="checkbox" id="WebMercator" value="Web Mercator"> Web Mercator
          </label>
          <label class="checkbox-inline">
            <input type="checkbox" id="l93" value="Lambert 93"> Lambert 93
          </label>
          <!--fin formulaire-->
        </div>
        <!--fin téléversements-->

          <h1 class="page-header" onclick="toggle_div(this,'publications');">Publication</h1>

          <!--publications-->
          <div id="publications" style="display:none;">
              <h3 class="sub-header">Services</h3>
              <!--formulaire des services-->
              <label class="checkbox-inline">
                <input type="checkbox" id="wms" value="WMS"> WMS
              </label>
              <label class="checkbox-inline">
                <input type="checkbox" id="wfs" value="WFS"> WFS
              </label>
              <label class="checkbox-inline">
                <input type="checkbox" id="wmts" value="WMTS"> WMTS
              </label>
              <!--fin formulaire-->

              <h3 class="sub-header">Systèmes de coordonnées</h3>
              <!--formulaire des SRS-->
              <label class="checkbox-inline">
                <input type="checkbox" id="WebMercator" value="Web Mercator"> Web Mercator
              </label>
              <label class="checkbox-inline">
                <input type="checkbox" id="l93" value="Lambert 93"> Lambert 93
              </label>

              <h3 class="sub-header">Style</h3>
              <!--formulaire des styles-->
              <div class="form-group">
                <label for="style">Appliquez un style :</label>
                <input type="file" id="style">
                <p class="help-block">Veuillez sélectionner votre style.</p>
              </div>
              <!--fin formulaire-->
            </div>
            <!--fin publications-->

          <h1 class="page-header" onclick="toggle_div(this,'traitements');">Traitements</h1>

          <!--traitements-->
          <div id="traitements" style="display:none;">
            <h3 class="sub-header">Critères d'éligibilité</h3>
            <!--formulaire des critères-->
            <!-- licences-->
            <h5>Licence</h5>
            <select class="form-control">
              <option>1</option>
              <option>2</option>
              <option>3</option>
              <option>4</option>
              <option>5</option>
            </select>
            <!--mots-clés-->
            <h5>Mot-clé</h5>
            <select class="form-control">
              <option>1</option>
              <option>2</option>
              <option>3</option>
              <option>4</option>
              <option>5</option>
            </select>

            <!--propriétaire-->
            <h5>Propriétaire</h5>
            <select class="form-control">
              <option>1</option>
              <option>2</option>
              <option>3</option>
              <option>4</option>
              <option>5</option>
            </select>

            <!--périodicité-->
            <h5>Périodicité</h5>
            <form class="form-inline">
              <div class="form-group">
                <label class="sr-only" for="periodicite">Nombre de jours</label>
                <div class="input-group">
                  <div class="input-group-addon"></div>
                  <input type="number" class="form-control" id="periodicite" placeholder="Nbre de jours">
                  <div class="input-group-addon">jours</div>
                </div>
              </div>
            </form>

            <!-- éligibilité-->
            <h5 class="sub-header"></h5>
            <div class="checkbox" style="padding-bottom:10px;">
              <label class="checkbox-inline">
                <input type="checkbox" id="eligibilite" value="oui"> Eligible au téléchargement ?
              </label>
            </div>

          <!--traitement immédiat-->
          <h3 class="sub-header">Lancer les traitements immédiatement</h3>
              <button type="submit" class="btn btn-default">Go!</button>

          </div>
          <!-- fin traitements-->


        </div>
      </div>
    </div>

<?php
include ('includes/piedDePage.php');
?>
