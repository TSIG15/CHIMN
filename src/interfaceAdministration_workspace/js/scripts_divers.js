/*pour afficher ou masquer une div au clic*/
function toggle_div (bouton, id) {
  var div = document.getElementById(id);

  if(div.style.display == "none") {
    div.style.display = "block";
  }
  else {
    div.style.display = "none";
  }
}
