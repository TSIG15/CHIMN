    var ls;
    var northEastlat ;
	var northEastlng ;
	var southWestlat ;
	var southWestlng ;
	var northWestlat ;
	var northWestlng ;
	var southEastlat ;
	var southEastlng ;
window.onload=function Commander()
			{
		
			var maintenant=new Date();

				j = maintenant.getDate();
				m = maintenant.getMonth()+1;
				an = maintenant.getFullYear();
				h = maintenant.getHours();
				min = maintenant.getMinutes();
				sec = maintenant.getSeconds();
			
//document.getElementById("date").innerHTML = "j+"/"+m+"/"+an"; 
                                    // '<p>'+a+'/'+m+'/'+j+'</p>' ;
document.getElementById('date').value =  an+ "/" +m+ "/" +j  ;
document.getElementById('heure').value =  h+ ":" +min+ ":" +sec  ;

		  
		
			
	var osmUrl = 'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
			osmAttrib = '&copy; <a href="http://openstreetmap.org/copyright">OpenStreetMap</a> contributors',
			osm = L.tileLayer(osmUrl, {maxZoom: 18, attribution: osmAttrib}),
			map = new L.Map('map', {layers: [osm], center: new L.LatLng(46.204824, 4.252924), zoom: 7 });

		
			
        var drawnItems = new L.FeatureGroup();
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
                remove: true,
				
            }
        });
        map.addControl(drawControl);
        
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

   
   alert (
        'point 1 north ouest latitude:  ' + northWestlat +'\n'+
        'point 1 north ouest longitude: ' + northWestlng +'\n'+
        'point 2 north east latitude :  ' + northEastlat +'\n'+
		'point 2 north east longitude :  ' + northEastlng +'\n'+
		'point 3 sud east latitude:  ' + southEastlat +'\n'+
        'point 3 sud east longitude: ' + southEastlng +'\n'+
		'point 4 sud ouest latitude:  ' + southWestlat +'\n'+
        'point 4 sud ouest longitude: ' + southWestlng 
		 
)
    console.log('point1 north ouest lat ',northWestlat);
	console.log('point1 north ouest long',northWestlng);
	console.log(northEastlat);
	console.log(northEastlng);	
    console.log(southEastlat);
	console.log(southEastlng);
	console.log(southWestlat );
	console.log(southWestlng);	
			
			
			
	
        });
        
		var countOfEditedLayers = 0;
        map.on('draw:edited', function (e) {
            var layers = e.layers;
            //var countOfEditedLayers = 0;
			
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
	console.log(southWestlat );
	console.log(southWestlng);
		}

		);		
			 console.log("Edited " + countOfEditedLayers + " layers");
        });		
		 
	
			/* préparer la requête AJAX*/
			var ajax = new XMLHttpRequest();
			
			// destination et type de la requête AJAX (asynchrone ou non)
			
			ajax.open('GET','commande.php?', true);

			// evenement de changement d'état de la requête
			ajax.addEventListener('readystatechange',  function(e) {
				// si l'état est le numéro 4 et que la ressource est trouvée
				if(ajax.readyState == 4 && ajax.status == 200) {
					// le texte de la réponse
					var rep =ajax.responseText;
					document.getElementById('srs').innerHTML='<option>'+rep+'</option>';
				}
			}); 

		
			// envoi de la requête
			ajax.send();
			  			  
				 		
			}

			
			
			
			
function cmd(ev) {
/* 	var srrs = document.getElementByID('srs').value;
    var format = document.getElementByID('formatsortie').value;
    srrs=false; format=false; alerte="";
if (ev.srrs.value)   
{ 
alerte += "Indiquer votre srs \n";  
}
else { */

/* préparer la requête AJAX*/
			var ajax = new XMLHttpRequest();
			
			
			var srs=document.getElementById('srs').value;
			var format=document.getElementById('formatsortie').value;
			var name=document.getElementById('username').value;
			var mail=document.getElementById('usermail').value;
			var datecmd=document.getElementById('date').value;
			var heurecmd=document.getElementById('heure').value;
			
			
			/*données GET éventuelles de la requête AJAX*/
			 var formulaire = {
				srs: srs,
				format: format,
				name: name,
				mail: mail,
				date:datecmd,
				heure:heurecmd,
				pointnorthlat: northWestlat,
				pointnorthlng: northWestlng,
				pointsouthlat: southEastlat,
				pointsouthlng: southEastlng
				
			}; 
			
				// destination et type de la requête AJAX (asynchrone ou non)
			
			ajax.open('GET','commande.php?donnees='+JSON.stringify(formulaire), true);

			// evenement de changement d'état de la requête
			ajax.addEventListener('readystatechange',  function(e) {
				// si l'état est le numéro 4 et que la ressource est trouvée
				if(ajax.readyState == 4 && ajax.status == 200) {
				
				
					
				
				}
			}); 

		
			// envoi de la requête
			ajax.send();


return true;
}			






	




