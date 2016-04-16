<?php
    $conn = pg_pconnect("host=localhost port=5432 dbname=chimn2 user=postgres");
    if (!$conn) {
        echo "Une erreur s'est produite.\n";
        exit;
    }
    
    $result = pg_query($conn, "SELECT tlurl FROM parameters");
    if (!$result) {
        echo "Une erreur s'est produite.\n";
        exit;
    }
    
    while ($row = pg_fetch_row($result)) {
        $chemin = "$row[0]";
    }
    ?>


//A FAIRE :
//- Mettre la variable $chemin dans le chemin de sortie (alert JS)
//- Transformer l alerte en une sortie : return ?
