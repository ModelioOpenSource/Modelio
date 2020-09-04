var ContentHeight = 150;
var TimeToSlide = 250.0;


function hasClass(ele,cls) {
	return ele.className.match(new RegExp('(\\s|^)'+cls+'(\\s|$)'));
}
 
function addClass(ele,cls) {
	if (!this.hasClass(ele,cls)) {
		ele.className = ele.className.replace(/^\s+|\s+$/g,'')
		ele.className += " "+cls;
	}
}
 
function removeClass(ele,cls) {
	if (hasClass(ele,cls)) {
		var reg = new RegExp('(\\s|^)'+cls+'(\\s|$)');
		ele.className=ele.className.replace(reg,' ');
	}
}


