<?php

    // connexion à la base de données
 
	$dbconn = pg_connect("host=localhost dbname=projet user=postgres password=postgres port=5432"); 

    // requête qui récupère les srs
    $requete = "SELECT DISTINCT srs FROM commande";
    $result =pg_query($dbconn,$requete);

	//remplissage de la liste déroulante SRS 
	 
    $srs= array();
    while ($ligne = pg_fetch_row($result)) {
	array_push($srs,$ligne[0]);
     }
    foreach($srs as $e)
					{
						echo "<option>".$e."</option>";
					}
			
								
   
   // echo json_encode($result1);
	echo $_GET['donnees'];
	
	//l'envoie de la commande a la table de la BDD
	if (isset($_GET['donnees']))
{
  
// Décoder le JSON
$json_source=$_GET['donnees'];
$json_data = json_decode($json_source,true);

//echo("<script>console.log('PHP: ".json_data."');</script>");
//print_r($json_data['name']);

//insertion dans la base de données			
$name=$json_data['name'];
$mail=$json_data['mail'];
$srs=$json_data['srs'];
$format=$json_data['format'];

$point1lat=$json_data['pointnorthlat'];
$point1lng=$json_data['pointnorthlng'];
$point2lat=$json_data['pointsouthlat'];
$point2lng=$json_data['pointsouthlng'];

$query ="INSERT INTO commande(nameuser,email,srs,format,point1lat,point1lng,point2lat,point2lng) VALUES('$name','$mail','$srs','$format','$point1lat','$point1lng','$point2lat','$point2lng');"; 

    pg_query($dbconn, $query);
	
}
else echo "erreur";


	
	
?>
