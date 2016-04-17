<?php
    $pgsql_conn = pg_connect("host=localhost port=5432 dbname=test user=postgres password=postgres");

    /*if ($pgsql_conn) {
        print " Connecté à la BDD " . pg_host($pgsql_conn) . "";
    } else {
        print " Non Connecté ";
        exit;
    }*/
?>
