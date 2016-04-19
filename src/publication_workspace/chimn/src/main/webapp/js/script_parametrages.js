$('#form').submit(function(e) {
   e.preventDefault();

   $.ajax({
     'url' : '/chimn/webapi/myresource/parameters',
     'type' : 'POST',
     'data' : {
    	 	hoteBDD : $('[name="hoteBDD"]').val(),
    	 	portBDD : $('[name="portBDD"]').val(),
    	 	bdd : $('[name="bdd"]').val(),
    	 	userBDD : $('[name="userBDD"]').val(),
     		passwordBDD : $('[name="passwordBDD"]').val(),
     		hoteGS : $('[name="hoteGS"]').val(),
     		portGS : $('[name="portGS"]').val(),
     		userGS : $('[name="userGS"]').val(),
     		passwordGS : $('[name="passwordGS"]').val(),
     		idI : $('[name="idI"]').val(),
     		secretI : $('[name="secretI"]').val(),
     		urlSD : $('[name="urlSD"]').val()
     }
   })
   .done(function(data) {
     alert("Enregistrement effectué avec succès !");
     console.log(data);
   });
 })

 $(".testconnexion").click(function(){
   setTimeout(function() {
    alert("Connexion établie");
  }, 1000);
 })
