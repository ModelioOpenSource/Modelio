/*******************************************************************************
 * Copyright (c) 2010 Tom Seidel, Remus Software
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *
 * Contributors:
 *     Tom Seidel - initial API and implementation
 *******************************************************************************/
 
 function doExternalConfiguration(config) {
 	configureAutoParagraph(config);
 	configureEnterMode(config);
 	configureShiftEnterMode(config);
 	configureUseEntities(config);
 }
 
 function configureAutoParagraph(config) {
 	var paragraph = stringToBoolean(getQuerystring("autoParagraph","true"));
	config.autoParagraph = paragraph;
  }
 
 function configureEnterMode(config) {
 	
 	var enterMode = getQuerystring("enterMode","");
	if (enterMode == "P"){
		config.enterMode = CKEDITOR.ENTER_P;
	} else if (enterMode == "BR") {
		config.enterMode = CKEDITOR.ENTER_BR;
	} else if (enterMode == "DIV") {
		config.enterMode = CKEDITOR.ENTER_DIV;
	}
  }
function configureShiftEnterMode(config) {
 	
 	var enterMode = getQuerystring("shiftEnterMode","");
	if (enterMode == "P"){
		config.shiftEnterMode = CKEDITOR.ENTER_P;
	} else if (enterMode == "BR") {
		config.shiftEnterMode = CKEDITOR.ENTER_BR;
	} else if (enterMode == "DIV") {
		config.shiftEnterMode = CKEDITOR.ENTER_DIV;
	}
  }
  
function configureUseEntities(config) {
	var useEntities = stringToBoolean(getQuerystring("entities","true"));
	config.entities = useEntities;
}
 
 function getQuerystring(key, default_) {
    if (default_==null) {
        default_ = "";
    }
    var search = unescape(location.search);
    if (search == "") {
        return default_;
    }
    search = search.substr(1);
    var params = search.split("&");
    for (var i = 0; i < params.length; i++) {
        var pairs = params[i].split("=");
        if(pairs[0] == key) {
            return pairs[1];
        }
    }
    return default_;
}

function stringToBoolean(string){
        switch(string.toLowerCase()){
                case "true": case "yes": case "1": return true;
                case "false": case "no": case "0": case null: return false;
                default: return Boolean(string);
        }
}
