<?php
// Connexion à la base de données
$pgsql_conn = pg_connect("host=localhost port=5432 dbname=test user=postgres password=postgres");
$id= $_POST['idI'];
$secret= $_POST['secretI'];
$groupeTravail= $_POST['groupeTI'];

// met à jour la table isogeo
$result_isogeo = pg_query_params($pgsql_conn,
                            'INSERT INTO public.isogeo
                            (id, secret, "groupeTravail")
                            VALUES ($1, $2, $3);',

                            array($id, $secret, $groupeTravail));
?>
