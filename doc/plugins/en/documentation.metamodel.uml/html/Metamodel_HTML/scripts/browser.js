function visibilite(thingId) {
  var targetElement = document.getElementById(thingId) ;
  if (targetElement.style.display == "none") {
    targetElement.style.display = "" ;
  } else {
    targetElement.style.display = "none" ;
  }
}

String.prototype.endsWith = function(str) {
  return (this.match(str+"$")==str)
}

function roll_over(img_name) {
    var img = document.images[img_name];
    var currentImg = img.src;
    if (currentImg.endsWith("plus.png")) {
      img.src = "img/minus.png";
    } else {
      img.src = "img/plus.png";    
    }
}
