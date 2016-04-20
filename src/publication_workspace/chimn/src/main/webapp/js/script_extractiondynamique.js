  	var ls;
  	var northEastlat ;
	var northEastlng ;
	var southWestlat ;
	var southWestlng ;
	var northWestlat ;
	var northWestlng ;
	var southEastlat ;
	var southEastlng ;
	var title = document.location.search;
	var titledata = title.substring(title.lastIndexOf("=")+1);

$(document).ready(function(){
//window.onload=function Commander



      /*ajoute date et heure du jour dans la fpage html*/
			var maintenant=new Date();

				j = maintenant.getDate();
				m = maintenant.getMonth()+1;
				an = maintenant.getFullYear();
				h = maintenant.getHours();
				min = maintenant.getMinutes();
				sec = maintenant.getSeconds();

document.getElementById('date').value =  j+ "/" +m+ "/" +an  ;
document.getElementById('heure').value =  h+ ":" +min+ ":" +sec  ;



  /*ajoute la carte*/
	var osmUrl = 'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
			osmAttrib = '&copy; <a href="http://openstreetmap.org/copyright">OpenStreetMap</a> contributors',
			osm = L.tileLayer(osmUrl, {maxZoom: 18, attribution: osmAttrib}),
			map = new L.Map('map', {layers: [osm], center: new L.LatLng(46.204824, 4.252924), zoom: 7 });


  var drawnItems = new L.FeatureGroup();

        /*ajoute barre d'outils à la carte*/
        map.addLayer(drawnItems);

        // Set the title to show on the polygon button
        L.drawLocal.draw.toolbar.buttons.polygon = 'Draw a sexy polygon!';

  var drawControl = new L.Control.Draw({
            position: 'topright',
            draw: {
                    polyline: false,
                    polygon: false,
                    circle: false,
                    marker: false
                  },
            edit: {
                    featureGroup: drawnItems,
                    remove: true
                  }
        });


    map.addControl(drawControl);

        /*fonctions relatives à dessin sur la carte*/
    map.on('draw:created', function (e) {

            var type = e.layerType,
                layer = e.layer;

			  drawnItems.clearLayers();
        drawnItems.addLayer(layer);

	            northEastlat = layer.getBounds().getNorthEast().lat;
	            northEastlng = layer.getBounds().getNorthEast().lng;
	            southWestlat = layer.getBounds().getSouthWest().lat;
	            southWestlng = layer.getBounds().getSouthWest().lng;
	            northWestlat = layer.getBounds().getNorthWest().lat;
	            northWestlng = layer.getBounds().getNorthWest().lng;
	            southEastlat = layer.getBounds().getSouthEast().lat;
	            southEastlng = layer.getBounds().getSouthEast().lng;

             /* alert (
                'point 1 north ouest latitude:  ' + northWestlat +'\n'+
                'point 1 north ouest longitude: ' + northWestlng +'\n'+
                'point 2 north east latitude :  ' + northEastlat +'\n'+
		            'point 2 north east longitude :  ' + northEastlng +'\n'+
		            'point 3 sud east latitude:  ' + southEastlat +'\n'+
                'point 3 sud east longitude: ' + southEastlng +'\n'+
		            'point 4 sud ouest latitude:  ' + southWestlat +'\n'+
                'point 4 sud ouest longitude: ' + southWestlng
              );*/

	            verifier();

                console.log('point1 north ouest lat ',northWestlat);
	              console.log('point1 north ouest long',northWestlng);
	              console.log(northEastlat);
	              console.log(northEastlng);
                console.log(southEastlat);
	              console.log(southEastlng);
	              console.log(southWestlat);
	              console.log(southWestlng);

              });


		var countOfEditedLayers = 0;

      map.on('draw:edited', function (e) {

              var layers = e.layers;


                  layers.eachLayer(function(layer) {

                      countOfEditedLayers++;
			                   console.log(countOfEditedLayers);

	                          northEastlat = layer.getBounds().getNorthEast().lat;
	                          northEastlng = layer.getBounds().getNorthEast().lng;
	                          southWestlat = layer.getBounds().getSouthWest().lat;
	                          southWestlng = layer.getBounds().getSouthWest().lng;
	                          northWestlat = layer.getBounds().getNorthWest().lat;
	                          northWestlng = layer.getBounds().getNorthWest().lng;
	                          southEastlat = layer.getBounds().getSouthEast().lat;
                            southEastlng = layer.getBounds().getSouthEast().lng;
	                          console.log('point1 north ouest lat ',northWestlat);
	                          console.log('point1 north ouest long',northWestlng);
	                          console.log(northEastlat);
	                          console.log(northEastlng);
                            console.log(southEastlat);
	                          console.log(southEastlng);
	                          console.log(southWestlat);
	                          console.log(southWestlng);
		                });


			           console.log("Edited " + countOfEditedLayers + " layers");

          });


    /*AJAX/JQUERY POUR PROPOSER LES SRS*/

      //Send the AJAX call to the server
    $.ajax({
        	  //The URL to process the request
        	    'url' : 'webapi/myresource/cmdSrs',
        	  //The type of request, also known as the "method" in HTML forms
        	  //Can be 'GET' or 'POST'
        	    'type' : 'GET',
        	  //Any post-data/get-data parameters
        	  //This is optional
        	    'data' : {},

        	  //The response from the server
        	    'success' : function(datasrs) {

        	       for(k in datasrs)
        	    	   {
        	    	       $('#srs').append($('<option>', {
        	    	          value : k,
        	    	          text : datasrs[k]
        	    	    }));
        	    	  }
        	    }
        	  });

        /*AJAX/JQUERY POUR PROPOSER LES FORMATS */

    //Send the AJAX call to the server
    $.ajax({
            //The URL to process the request
              	'url' : 'webapi/myresource/cmdFormat',
              	//The type of request, also known as the "method" in HTML forms
              	//Can be 'GET' or 'POST'
              	'type' : 'GET',
              	//Any post-data/get-data parameters
              	//This is optional
              	'data' : {},

              	//The response from the server
              	'success' : function(dataformat) {

              	       for(k in dataformat)
              	    	   {
              	    	       $('#formatsortie').append($('<option>', {
              	    	          value : k,
              	    	          text : dataformat[k]
              	    	    }));
              	    	  }
              	    }
              	  });
          });//fin document ready


    //   METHODE AJAX IMANE
		// /* préparer la requête AJAX*/
		// var ajax = new XMLHttpRequest();
    //
		// 	// destination et type de la requête AJAX (asynchrone ou non)
    //
		// 	ajax.open('GET','commande.php?', true);
    //
		// 	// evenement de changement d'état de la requête
		// 	ajax.addEventListener('readystatechange',  function(e) {
		// 		// si l'état est le numéro 4 et que la ressource est trouvée
		// 		if(ajax.readyState == 4 && ajax.status == 200) {
		// 			// le texte de la réponse
		// 			var rep =ajax.responseText;
		// 			document.getElementById('srs').innerHTML='<option>'+rep+'</option>';
		// 		}
		// 	});
    //
    //
		// 	// envoi de la requête
		// 	ajax.send();





function verifier(){

        if (
            document.getElementById('srs').value!="" &&
            document.getElementById('formatsortie').value !="" &&
            document.getElementById('username').value !="" &&
            document.getElementById('usermail').value !="" &&
            northEastlng!=null
          )
            {
            document.getElementById('valider').disabled=false;
            }
	}//fin fonction verifier






  $("#formquoi").submit(function(e) {
      e.preventDefault();

      $.ajax({
        'url' : '/chimn/webapi/myresource/saveCmd',
        'type' : 'POST',
        'data' : {
          		username : $('[name="username"]').val(),
          		email : $('[name="usermail"]').val(),
              srs: $('[name="srs"]').val(),
          		format : $('[name="formatsortie"]').val(),
          		point1lat : northWestlat,
          		point1lng : northWestlng,
          		point2lat : southEastlat,
          		point2lng : southEastlng,
              datecmd : $('[name="date"]').val(),
          		heurecmd : $('[name="heure"]').val(),
              titledata : titledata
          	   }
             }).done(function(data) {
                alert("Enregistrement effectué avec succès !");
                console.log(data);
              });
    })


        //   METHODE PHP IMANE
        //
        //  function cmd(ev) {
        //
        //   /* préparer la requête AJAX*/
        // 	var ajax = new XMLHttpRequest();
        //
        // 	var srs=document.getElementById('srs').value;
        // 	var format=document.getElementById('formatsortie').value;
        // 	var name=document.getElementById('username').value;
        // 	var mail=document.getElementById('usermail').value;
        // 	var datecmd=document.getElementById('date').value;
        // 	var heurecmd=document.getElementById('heure').value;
        //
        //
        // 	/*données GET éventuelles de la requête AJAX*/
        // 	 var formulaire = {
        // 		srs: srs,
        // 		format: format,
        // 		name: name,
        // 		mail: mail,
        // 		date:datecmd,
        // 		heure:heurecmd,
        // 		pointnorthlat: northWestlat,
        // 		pointnorthlng: northWestlng,
        // 		pointsouthlat: southEastlat,
        // 		pointsouthlng: southEastlng
        // 	   };
        //
        // 	// destination et type de la requête AJAX (asynchrone ou non)
        //
        // 	ajax.open('GET','commande.php?donnees='+JSON.stringify(formulaire), true);
        //
        // 	// evenement de changement d'état de la requête
        // 	ajax.addEventListener('readystatechange',  function(e) {
        // 		// si l'état est le numéro 4 et que la ressource est trouvée
        // 		if(ajax.readyState == 4 && ajax.status == 200) {
        // 		    }
        // 	});
        //
        //
        // 	// envoi de la requête
        // 	ajax.send();
        //
        //   return true;
        //
        // }//fin fonction cmd
