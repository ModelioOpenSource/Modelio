/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
// Note: Mozilla/Firefox does not allow unprivileged scripts to invoke the cut,
// copy and paste commands. The Javascript must either be signed
// (see http://www.mozilla.org/projects/security/components/signed-scripts.html),
// or the users must change their preferences
// (see http://www.mozilla.org/editor/midasdemo/securityprefs.html).
// Alternatively, the users can use the ctrl-x, ctrl-c and ctrl-v keys.
//------------------------------------------------------------------------------
// Note: The SWT component in eclipse 3.4.X and above has a bug that 
// keyReleased event can't be notified to listener. See (https://bugs.eclipse.org/bugs/show_bug.cgi?id=280146).
// This issue has blocked EPF upgrade to 1.5.0.5. As a workaround, capture keyReleased event in 
// Javascript. Once this issue is resolved in SWT, we need to resume it  
var STATUS_NOP = 0;
var STATUS_INITIALIZED = 1;
var STATUS_MODIFIED = 2;
var STATUS_GET_TEXT = 3;
var STATUS_KEY_DOWN = 4;
var STATUS_KEY_UP = 5;
var STATUS_SELECT_TEXT = 6;
var STATUS_SELECT_CONTROL = 7;
var STATUS_SELECT_NONE = 8;
var STATUS_EXEC_CMD = 9;
var STATUS_FOCUS_GOT = 11;
var STATUS_FOCUS_LOST = 12;
var STATUS_INITIALIZATION_FAILED = 13;

var KEY_BACKSPACE = 8;
var KEY_TAB = 9;
var KEY_ENTER = 13;
var KEY_PAGE_UP = 33;
var KEY_PAGE_DOWN = 34;
var KEY_END = 35;
var KEY_HOME = 36;
var KEY_ARROW_LEFT = 37;
var KEY_ARROW_UP = 38;
var KEY_ARROW_RIGHT = 39;
var KEY_ARROW_DOWN = 40;

var KEY_A = 65;
var KEY_B = 66;
var KEY_C = 67;
var KEY_F = 70;
var KEY_I = 73;
var KEY_S = 83;
var KEY_U = 85;
var KEY_V = 86;
var KEY_X = 88;
var KEY_Z = 90;

var CMD_COPY = "copy";
var CMD_CUT = "cut";
var CMD_FIND_TEXT = "findText";
var CMD_PASTE = "paste";
var CMD_SAVE = "save";
var CMD_SAVE_ALL = "saveAll";

var TABLE_HEADERS_NONE = 0;
var TABLE_HEADERS_COLS = 1;
var TABLE_HEADERS_ROWS = 2;
var TABLE_HEADERS_BOTH = 3;

var BOLD = 1;
var ITALIC = BOLD << 1;
var UNDERLINE = ITALIC << 1;
var SUBSCRIPT = UNDERLINE << 1;
var SUPERSCRIPT = SUBSCRIPT << 1;


var editorId;
var editorCSS;
var baseHREF;
var supportRichTextEditing = true;
var editorDoc;
var selection;
var selectionRange;
var readOnly = false;
var initialized = false;
var modified = false;
var checkResizeElement;
var selectionInfo = null;

//BinBinLi&ShiJin	2013-12-03	 Enhancement42506 Add or delete rows/columns to existing table in RTE
var STATUS_SELECT_TABLE = 51;
var currentSelectedTable = null;

var isWebKit = navigator.userAgent.toLowerCase().indexOf("webkit") > 0;

// Initializes the editor.
function initEditor(id, css, baseURL) {
	editorId = id;
	editorCSS = css;
	baseHREF = baseURL;
	try {
		log ("initEditor() in progress...");
		enableRichTextEditing('');
		initialized = true;
		setStatus(STATUS_INITIALIZED, null);
		
		window.onerror = handleOnError;
	}
	catch (e) {
		log("ERROR initEditor() exception:" + e.name + "\nmessage:" + e.message);
		
		supportRichTextEditing = false;
		setStatus(STATUS_INITIALIZATION_FAILED, null);

		//alert("name:" + e.name + "\nmessage:" + e.message)
	}
}


function handleOnError (e) {
	log(" global exception:" + e.name + "\nmessage:" + e.message + "\nstack:"+ e.stack);
}

function log (msg) {
	console.log("LOG:"+msg);
	window.status = "LOG:"+msg;
}

var excludeModify = new Array(16, KEY_ARROW_DOWN, KEY_ARROW_LEFT,
		KEY_ARROW_RIGHT, KEY_ARROW_UP, KEY_END, KEY_HOME, KEY_PAGE_DOWN,
		KEY_PAGE_UP, KEY_TAB);

function keyReleased(event) {
	var keyCode = event.keyCode;
	if (keyCode == 0 && !document.all) {
		keyCode = event.charCode;
		switch (keyCode) {
		    case 98:
		     	keyCode = KEY_B;
				break;
			case 99:
				keyCode = KEY_C;
				break;
			case 102:
				keyCode = KEY_F;
				break;
		    case 105:
		        keyCode = KEY_I;
		        break;
			case 115:
				keyCode = KEY_S;
				break;
		    case 117:
		        keyCode = KEY_U;
		        break;
			case 118:
				keyCode = KEY_V;
				break;
			case 120:
				keyCode = KEY_X;
				break;
			case 122:
				keyCode = KEY_Z;
				break;
		}
	}
	var ctrlKey = event.ctrlKey;
	var shiftKey = event.shiftKey;
	var altKey = event.altKey;

	if ( !ctrlKey && !altKey ) {
       var modified = true;
	   for (var i = 0; i < excludeModify.length; i++ ) {
	     if ( keyCode == excludeModify[i] ) {
	       modified = false;
	       break;
	     }
	   }
	   if ( modified == true ) {
		   setTimeout("setStatus(STATUS_MODIFIED, null);", 10);
	   }
	}
}

/**
 * Handle the <ENTER> key pressed : on <shift>+<enter> insert a <BR> instead of closing the <div>/<p>
 * @see http://wadmiraal.net/lore/2012/06/14/contenteditable-hacks-returning-like-a-pro/
 * @see http://stackoverflow.com/questions/18552336/prevent-contenteditable-adding-div-on-enter-chrome
 **/
function keyEnterPressed(event, shiftKey, ctrlKey) {
		
	try {
		log ("keyEnterPressed(): shift="+shiftKey+", ctrl="+ctrlKey);

		if (! shiftKey) {
			// Convert <div> to <p>
			var blockStyle = editorDoc.queryCommandValue('formatBlock');
			log ("keyEnterPressed(): blockstyle="+blockStyle);
			if (blockStyle == "div" || blockStyle == "") {
				editorDoc.execCommand('formatblock', false, "p");
				blockStyle = editorDoc.queryCommandValue('formatBlock');
				log ("keyEnterPressed(): fixed blockstyle ="+blockStyle);
			}
			return ;
		} else {
			// insert <br> instead of closing tag
			var doxExec = false;
			try {
				// Gecko
				doxExec = document.execCommand('insertBrOnReturn', false, true);
			}
			catch (error) {
				// IE throws an error if it does not recognize the command...
			}

			if (doxExec) {
				// Hurray, no dirty hacks needed !
				return true;
			}
			// Standard : Webkit (and others)
			else if (window.getSelection) {
				var selection = window.getSelection();
				if (selection.rangeCount == 0 && editorDoc.getSelection)
					selection = editorDoc.getSelection();

				log ("keyEnterPressed(): Standard : Webkit (and others)");
				event.preventDefault(); //Prevent default browser behavior
				event.stopPropagation();

				var range = selection.getRangeAt(0);

				// Add <br> to the end of the field for chrome and safari to allow further insertion
				if(isWebKit)
				{
					if ( !editorDoc.body.lastChild || editorDoc.body.lastChild.nodeName.toLowerCase() != "br" ) {
						log("keyEnterPressed(): insert BR at end.");
						editorDoc.body.appendChild(document.createElement('br'));
					}
				}

				var br = document.createElement('br'),
				textNode = document.createTextNode("\u00a0"); //Passing " " directly will not end up being shown correctly

				range.deleteContents();
				range.insertNode(br);
				range.setStartAfter(br); // range.collapse(false) does not work on webkit
				range.setEndAfter(br);

				// This is needed on Webkit, in the other case the cursor stays before the <br>
				range.insertNode(textNode);
				range.selectNodeContents(textNode);

				selection.removeAllRanges();
				selection.addRange(range);
				window.setSelection (selection);

				return false;
			}
			// IE
			else if ($.browser.msie) {
				event.preventDefault();

				var range = document.selection.createRange();

				range.pasteHTML('<BR><SPAN class="--IE-BR-HACK"></SPAN>');

				// Move the caret after the BR
				range.moveStart('character', 1);

				return false;
			}
		}
	}
	catch (e) {
		log("ERROR keyEnterPressed() exception:" + e.name + "\nmessage:" + e.message);
	}

	// Last resort, just use the default browser behavior and pray...
	return true;
}

/**
 * Handles the key events.
 * @param event the key event
 */
function keyPressed(event) {
	var keyCode = event.keyCode;
	if (keyCode == 0 && !document.all) {
		keyCode = event.charCode;
		switch (keyCode) {
		case 97:
			keyCode = KEY_A;
			break;
		case 98:
			keyCode = KEY_B;
			break;
		case 99:
			keyCode = KEY_C;
			break;
		case 102:
			keyCode = KEY_F;
			break;
		case 105:
			keyCode = KEY_I;
			break;
		case 115:
			keyCode = KEY_S;
			break;
		case 117:
			keyCode = KEY_U;
			break;
		case 118:
			keyCode = KEY_V;
			break;
		case 120:
			keyCode = KEY_X;
			break;
		case 122:
			keyCode = KEY_Z;
			break;
		}
	}
	var ctrlKey = event.ctrlKey;
	var shiftKey = event.shiftKey;

	try {
		//log ("key pressed:" + keyCode + ", shift=" + shiftKey + ", ctrl=" + ctrlKey);

		switch(keyCode) {
		case KEY_A:
		case KEY_ARROW_DOWN:
		case KEY_ARROW_LEFT:
		case KEY_ARROW_RIGHT:
		case KEY_ARROW_UP:
		case KEY_END:
		case KEY_HOME:
		case KEY_PAGE_DOWN:
		case KEY_PAGE_UP:
			break;
		case KEY_ENTER:
			keyEnterPressed(event, shiftKey, ctrlKey);
			break;
		case KEY_TAB:
			if ( readOnly || !ctrlKey)
				break;

			var editorWindow = document.getElementById(editorId).contentWindow;
			var editorDocument = editorWindow.document;
			var tabText = "&nbsp;&nbsp;&nbsp;&nbsp;";

			if(document.all ){
				// IE specific
				event.keyCode = -1;
				event.returnValue=false; //IE: cancel the default behavior
				var rng=editorDocument.selection.createRange();
				rng.pasteHTML(tabText);  //insert 4 spaces for one tab key 
			} else  {
				// Other browsers
				event.preventDefault();
				editorDoc.execCommand("insertHTML","",tabText);
			}

			setTimeout("setStatus(STATUS_MODIFIED, null);", 10);
			break;
		case KEY_BACKSPACE:
			if (!readOnly) {
				setTimeout("setStatus(STATUS_MODIFIED, null);", 10);
			}
			break;
		case KEY_B:
		case KEY_U:
		case KEY_I:
			if (!readOnly && ctrlKey) {
				setTimeout("setStatus(STATUS_MODIFIED, null);",10);
			}
			break;
		case KEY_C:
			if (ctrlKey) {
				setStatus(STATUS_KEY_DOWN, CMD_COPY);
			}
			else if (!document.all && readOnly) {
				event.preventDefault();
			}
			break;			
		case KEY_F:
			if (ctrlKey) {
				if (document.all) {
					event.keyCode = -1;
					event.returnValue = false;
				}
				else {
					event.preventDefault();
				}
				setStatus(STATUS_KEY_DOWN, CMD_FIND_TEXT);
			}
			else if (!document.all && readOnly) {
				event.preventDefault();
			}
			break;
		case KEY_S:
			if (!readOnly && ctrlKey) {
				if (document.all) {
					event.keyCode = -1;
					event.returnValue = false;
				}
				else {
					event.preventDefault();
				}
				if (shiftKey) {
					setStatus(STATUS_KEY_DOWN, CMD_SAVE_ALL);
				}
				else {
					setStatus(STATUS_KEY_DOWN, CMD_SAVE);
				}
			}
			else if (!document.all && readOnly) {
				event.preventDefault();
			}			
			break;
		case KEY_V:
			if (ctrlKey) {		
				if (document.all) {
					event.keyCode = -1;
					event.returnValue = false;
					if (!readOnly) {
						setStatus(STATUS_KEY_DOWN, CMD_PASTE);
					}
				}
				else {
					if (!readOnly) {
						// Workaround Mozilla/Firefox paste issues.
						setTimeout("setStatus(STATUS_KEY_DOWN, CMD_PASTE);", 10);
					}
					else {
						event.preventDefault();
					}
				}
			}
			else if (!document.all && readOnly) {
				event.preventDefault();
			}
			break;
		case KEY_X:
			if (ctrlKey) {
				setStatus(STATUS_KEY_DOWN, CMD_CUT);
			}
			else if (!document.all && readOnly) {
				event.preventDefault();
			}
			break;
		case KEY_Z:
			if (!readOnly && ctrlKey) {
				setTimeout("setStatus(STATUS_MODIFIED, null);", 10);
			}
			else if (!document.all && readOnly) {
				event.preventDefault();
			}			
			break;
		default:
			if (!document.all && readOnly) {
				event.preventDefault();
			}
		}

	} catch (e) {
		log("keyPressed() exception:" + e.name + "\nmessage:" + e.message)
	}

}

function selChanged(event) {
	updateSelection();
}

function enableRichTextEditing(html) {
	var doc = document.getElementById(editorId).contentWindow.document;
	doc.designMode = "on";
	
	var htmlSrc = '<html><head><title></title>';
	
	if (editorCSS != null && editorCSS != '') {
		htmlSrc += '<link rel="StyleSheet" href="' + editorCSS + '" type="text/css"/>';
	}
	
	if (baseHREF != null && baseHREF != '') {	
		htmlSrc += '<base href="' + baseHREF + '"/>';
	}
	
	if (!document.all && html == '') {
		// Mozilla/Firefox will only display the caret if <br/> is added to the HTML body.
		// Adding <br/> also enables the backspace and delete key by default. Otherwise, the
		// user need to enter some text before these 2 keys start to function.
		html = "<br />";
	}
	
	htmlSrc += '</head><body><p>' + html + '</p></body></html>';
	
	doc.open();
	doc.write(htmlSrc);
	doc.close();
	
	modified = false;

	if ("attachEvent" in doc) {
		// Internet Explorer way
		doc.attachEvent("onkeydown", keyPressed);
		doc.attachEvent("onkeyup", keyReleased); 
		doc.attachEvent("onselectionchange", selChanged);
		// for DnD (internal)
		doc.body.attachEvent("ondrop", checkModified);
		// for image/table resizing:
		doc.body.attachEvent("onresizeend", checkModified);
	}	
	if ("addEventListener" in doc) {
		doc.addEventListener("keypress", keyPressed, true);
		doc.addEventListener("keyrelease", keyReleased, true);
		doc.addEventListener("keypress", selChanged, false);
		doc.addEventListener("mouseup", selChanged, false);
		doc.addEventListener("dragdrop", checkModified, false);
		
		// check mouseup event for image/table resizing
		doc.addEventListener("mouseup", checkModified, false);
	}

	// Add focus listeners on plenty places because depending on the Browser and platforms
	// the focus event arrives on different objects I didn't have time to determine.
	addFocusListener(document);
	addFocusListener(doc);
	addFocusListener(doc.body);
	addFocusListener(window);
	w = window;
	while (w.top != w.self) {
		w = w.top;
	}
	addFocusListener(w);

	setStatus(STATUS_EXEC_CMD, 1);
}



/**
 *  Called by Java, remove all listeners added by enableRichTextEditing() 
 *  to avoid crash in Linux SWT Webkit when the editor is destroyed.
 */
function dispose() { /*
	window.status = "dispose(): removing listeners"
	console.log("dispose(): removing listeners")
	
	var doc = document.getElementById(editorId).contentWindow.document;

	if ("attachEvent" in doc) {
		// Internet Explorer way
		doc.detachEvent("onkeydown", keyPressed);
		doc.detachEvent("onkeyup", keyReleased); 
		doc.detachEvent("onselectionchange", selChanged);
		// for DnD (internal)
		doc.body.detachEvent("ondrop", checkModified);
		// for image/table resizing:
		doc.body.detachEvent("onresizeend", checkModified);
	}	
	if ("addEventListener" in doc) {
		doc.removeEventListener("keypress", keyPressed, true);
		doc.removeEventListener("keyrelease", keyReleased, true);
		doc.removeEventListener("keypress", selChanged, false);
		doc.removeEventListener("mouseup", selChanged, false);
		doc.removeEventListener("dragdrop", checkModified, false);
		
		// check mouseup event for image/table resizing
		doc.removeEventListener("mouseup", checkModified, false);
	}

	removeFocusListener(document);
	removeFocusListener(doc);
	removeFocusListener(doc.body);
	removeFocusListener(window);
	
	w = window;
	while (w.top != w.self) {
		w = w.top;
	}
	removeFocusListener(w);	
	*/
	log("dispose(): listeners removed")
	setStatus(STATUS_EXEC_CMD, 1);
	
}

/**
 * Remove focust listeners added by addFocusListener().
 * @param anitem the DOM element whose focus listenre s must be removed
 */
function removeFocusListener(anitem) {
	// IE 9 and lower:
    if ('onfocusin' in item)
        item.onfocusin = item.onfocusout = null;
    // All others:
    else
        item.onpageshow = item.onpagehide 
            = item.onfocus = item.onblur = null;
}

function addFocusListener(anitem) {
    var hidden = "hidden";
	var item = anitem;

	// IE 9 and lower:
    if ('onfocusin' in item)
        item.onfocusin = item.onfocusout = onchange;
    // All others:
    else
        item.onpageshow = item.onpagehide 
            = item.onfocus = item.onblur = onchange;

    function onchange (evt) {
		//log("addVisibilityListener.onchange on "+item+": "+evt+" type="+evt.type);

        var v = 'visible', h = 'hidden',
            evtMap = { 
                focus:v, focusin:v, pageshow:v, blur:h, focusout:h, pagehide:h 
            };

        evt = evt || window.event;

        if (evt.type in evtMap) {
			if (evtMap[evt.type] == v) {
				jsendFocusGot(evt)
			} else {
				jsendFocusLost(evt);
			}
        }
    }
}


function jsendFocusGot(event) {
	//setTimeout("setStatus(STATUS_FOCUS_GOT, null);", 10);
	setStatus(STATUS_FOCUS_GOT, null);
	sendFocusGot();

	/*selectionInfo = getSelectionInfo();*/
	if (selectionInfo != null) {
		//setTimeout("setSelection(selectionInfo);", 10);
		setSelection(selectionInfo);
	} else {
		log("jsendFocusGot(): no selection");
	}

}

/**
 * Call the "sendFocusLost()" Java callback . 
 * @param event the focus lost event
 */
function jsendFocusLost(event) {
	//log ("jsendFocusLost() : stored selection is "  + printSelection(selectionInfo)) ;
	selectionInfo = getSelectionInfo();
	//log ("jsendFocusLost() : new stored selection is "  + printSelection(selectionInfo)) ;

	setStatus(STATUS_FOCUS_LOST, null);

	// test the Java callback function still exist before calling it.
	if (typeof(sendFocusLost) == "function")
		sendFocusLost();
}

// this one is for modification check on drag n drop within the RTE
// checkModified listener
function checkModified(event) {
	setTimeout("setStatus(STATUS_MODIFIED, null);", 10);
}

// Sets the height of the editor.
function setHeight(height) {
	if (initialized) {
		document.getElementById(editorId).height = height + "px";
	}
}

// Sets the status.
// Note: By default, Firefox disables changes to the status bar. For this to work, the user
// must set the global preference "dom.disable_window_status_change" to false.
// For Firefox 1.0.x, this setting can be made in /usr/firefox-1.0.7/defaults/pref/firefox.js.
function setStatus(type, value) {
	console.debug("status :"+ type+ " " + value)

	var status = '$$$' + type;
	if (value != null && value != '') {
		status += ('$' + value);		
	}
	window.status = status;
	window.status = '$$$' + STATUS_NOP;

}

// Returns the HTML source.
function getHTML() {
	var html = document.getElementById(editorId).contentWindow.document.body.innerHTML;
	if (html == "<P>&nbsp;</P>") {
		html = "";
	}
	if (html != null && html != '') {
		var regEx = new RegExp("\"file\:([^=]*)(/resources/)([^\"]+)\"", "g");
		html = html.replace(regEx, "\"./resources/$3\"");
		regEx = new RegExp("\"file\:([^=]*)/#([^\"]+)\"", "g");
		html = html.replace(regEx, "\"#$2\"");
	}
	return html;
}

//Returns the HTML source to the Java layer
function getText() {
	var html = getHTML();
	setStatus(STATUS_GET_TEXT, html);
	return html;
}

function setInnerHTML(html) {
	if (document.all) {
		// IE has problem setting complex HTML set via doc.body.innerHTML.
		enableRichTextEditing(html);
	}
	else {
		if (html == '') {
			// Mozilla/Firefox will only display the caret if <br/> is added to the HTML body.
			html = "<br/>";
		}
		var doc = document.getElementById(editorId).contentWindow.document;
		if (doc.body != null) {
			doc.body.innerHTML = html;
		}
		else {
			// Mozilla/Firefox can take a while to initialize document.body
			// after document.write().
			try {
				setTimeout("setInnerHTML('" + html + "');", 10);
			}
			catch (e) {
			}
		}
	}
}

// Sets the HTML source.
function setText(html) {
	if (supportRichTextEditing) {
		html = decodeString(html);
		selectionInfo = getSelectionInfo();
		setInnerHTML(html);
		if (selectionInfo != null) {
			setTimeout("setSelection(selectionInfo);", 10);
		}
		modified = false;
		setStatus(STATUS_EXEC_CMD, 1);
	}
}

/**
Set the browser selection.
*/
function setSelection(selectionInfo) {
	if (!supportRichTextEditing) {
		return;
	}
	
	contentWindow = document.getElementById(editorId).contentWindow;
	editorDoc = contentWindow.document;
	
	try {
		if (document.all) {
			var startOffset = selectionInfo.start;
			var len = selectionInfo.len;
			if (startOffset == 0 && len == 0) {
				return;
			}
			var tempRange = editorDoc.body.createTextRange();
			tempRange.moveStart('character', startOffset);
			tempRange.collapse();
			tempRange.moveEnd('character', len);
			tempRange.select();
			tempRange.scrollIntoView();
		} else {
			selection = this.window.getSelection();
			var startContainer = selectionInfo.startContainer;
			var start = selectionInfo.start;
			var endContainer = selectionInfo.endContainer;
			var end = selectionInfo.end;
			var tempRange = document.createRange();
			tempRange.setStart(startContainer, start);
			tempRange.setEnd(endContainer, end);
			selection.removeAllRanges();
			selection.addRange(tempRange);
			contentWindow.focus();
		}
	} catch (e) {
		log("ERROR in setSelection(): " + e.name + ": "+ e.message);
	}
}

/**
 * Get the browser selection.
 */
function getSelectionInfo() {
	if (!supportRichTextEditing) {
		return null;
	}	
	
	contentWindow = document.getElementById(editorId).contentWindow;
	editorDoc = contentWindow.document;
	
	var tempSelRange;
	try {
	    if (document.all) {
			selection = editorDoc.selection;
			if (selection != null) {
				tempSelRange = selection.createRange();
			}
			// length of selection
			var tempSelLen = tempSelRange.text.length;
			// create new range
			var tempRange = editorDoc.body.createTextRange();
			// set end of new range to start of selection
			// this will throw an exception if tempSelRange is not in editor.doc.body (ie, at the start of the RTE).
			tempRange.setEndPoint("EndToStart", tempSelRange);
			// length of new range is the start offset
			var tempText = tempRange.text;
			// IE counts newlines as 2 characters for length property, but they count as 1 when using moveStart so remove the \r to make the count the same
			tempText = tempText.replace(/\r/g, "");
			var startOffset = tempText.length;
			
			return {start:startOffset, len:tempSelLen, text:tempRange.text, range:tempRange};
	    } else {
			selection = contentWindow.getSelection();
			if (selection != null) {
				tempSelRange = selection.getRangeAt(selection.rangeCount - 1).cloneRange();
			}
			return {startContainer: tempSelRange.startContainer, start:tempSelRange.startOffset, 
				endContainer: tempSelRange.endContainer, end:tempSelRange.endOffset, text:selection, range:tempSelRange};
	    }
	} catch (e) {
		log("ERROR: getSelectionInfo() : " + e.name + " " + e.message);
		return null;
	}
}

function printSelection(sel) {
	if (sel == null) {
		return "<null>";
	}
	return "{start="+ sel.start+", end="+sel.end + ", length="+ sel.len+", text='" + sel.text+"'}"
}

// Decodes the HTML passed from the Java layer.
function decodeString(str) {
	if (str != null && str != '') {
		if (document.all) {
			str = str.replace(/%sq%/g, "'");
			str = str.replace(/%EOL%/g, "\n");
		}
		else {
			str = str.replace(/%sq%/g, "&apos;");
			str = str.replace(/%EOL%/g, "");
			str = str.replace(/\n/g, "");
		}
	}
	return str;
}

// updates selection without notifying the Java layer of the selection state
function internalUpdateSelection() {
	if (!supportRichTextEditing) {
		return false;
	}	
	
	contentWindow = document.getElementById(editorId).contentWindow;
	editorDoc = contentWindow.document;
	
	if (document.all) {
		selection = editorDoc.selection;
		if (selection != null) {
			selectionRange = selection.createRange();
			reformatElementLinks();
		}
	}
	else {
		selection = contentWindow.getSelection();
		if (selection != null) {
			selectionRange = selection.getRangeAt(selection.rangeCount - 1).cloneRange();
			if (selectionRange.startContainer.nodeName == "HTML" &&
					selectionRange.endContainer.nodeName == "HTML") {
				// Mozilla selects the whole document when there's no RTE content, so select just the body
				selectionRange = editorDoc.createRange();
				selectionRange.setStart(editorDoc.body, 0);
				selectionRange.setEnd(editorDoc.body, 0);
			}
		}
	}
	return true;
}

/**
 *  Updates the current selection and selection range.
 */
function updateSelection() {
	log("Start updateSelection() ");
	if (!supportRichTextEditing) {
		return false;
	}	
	
	contentWindow = document.getElementById(editorId).contentWindow;
	editorDoc = contentWindow.document;
	
	var tempSelRange;
	var selOffsetStart = 0;
	var selectedText = "";
	var fontName = "";
	var fontSize = "";
	var blockStyle = "";
	var textFlags = 0;
	
	
	if (document.all) {
		selection = editorDoc.selection;
		if (selection != null) {
			selectionRange = selection.createRange();
			if (selectionRange != null)
			{
				if(selection.type != "Control") {
					tempSelRange = selectionRange.duplicate();
				} else {
					setStatus(STATUS_SELECT_CONTROL, null);
					if (selectionRange(0).tagName == "TABLE") {
						currentSelectedTable = selectionRange(0);
						setStatus(STATUS_SELECT_TABLE, null);
					}
				}
			}
			reformatElementLinks();
		}
	}
	else {
		selection = contentWindow.getSelection();
		if (selection != null) {
			selectionRange = selection.getRangeAt(selection.rangeCount - 1).cloneRange();
			var tableNode = null;
			if (selectionRange.startContainer && selectionRange.endContainer &&
              selectionRange.startContainer == selectionRange.endContainer &&
              selectionRange.startContainer.nodeType == 1) 
		    {
		    	setStatus(STATUS_SELECT_CONTROL, null);
				if (selectionRange.startContainer.tagName == "TD") {
					tableNode = selectionRange.startContainer;
				} else if (selectionRange.endOffset - selectionRange.startOffset == 1 && selectionRange.startContainer.childNodes[selectionRange.startOffset].tagName == "TD") {
					tableNode = selectionRange.startContainer.childNodes[selectionRange.startOffset];
				}
			}
			if (tableNode != null){
				currentSelectedTable = tableNode;
				while(currentSelectedTable.nodeName != "TABLE") {
					currentSelectedTable = currentSelectedTable.parentNode;
				}
				setStatus(STATUS_SELECT_TABLE, null);
			} else {
			  tempSelRange = selectionRange.cloneRange();
			} 
		}
	}

	if (tempSelRange != null) {
		try {
			if (document.all) {
				if (selectionRange.text) {
					selectedText = selectionRange.text;
				}
				/* for getting selection offset - commented because we can't select the
				 * proper location in the HTML source tab because JTidy's reformatting of the HTML
				var html = getHTML();
	            var tempSelLen = tempSelRange.htmlText.length;			
	            tempSelRange.moveStart('character', -html.length);
	            selOffsetStart = tempSelRange.htmlText.length - tempSelLen;
	            */
				var selParent = tempSelRange.parentElement();
				fontName = tempSelRange.queryCommandValue('fontName');
				fontSize = tempSelRange.queryCommandValue('fontSize');
				blockStyle = tempSelRange.queryCommandValue('formatBlock');
				if (blockStyle == "Normal") {
					if (selParent.className == "quote") {
						blockStyle = "<quote>";
					} else if (selParent.className == "codeSample") {
						blockStyle = "<code>";
					} else {
						blockStyle = "<p>";
					}
				} else if (blockStyle == "Heading 1") {
					blockStyle = "<h1>";
				} else if (blockStyle == "Heading 2") {
					blockStyle = "<h2>";
				} else if (blockStyle == "Heading 3") {
					blockStyle = "<h3>";
				} else if (blockStyle == "Heading 4") {
					blockStyle = "<h4>";
				} else if (blockStyle == "Heading 5") {
					blockStyle = "<h5>";
				} else if (blockStyle == "Heading 6") {
					blockStyle = "<h5>";
				} else if (blockStyle == "" || blockStyle == null) {
					blockStyle = "<p>";
				}
				if (tempSelRange.queryCommandValue('bold') == true) {
					textFlags |= BOLD;
				}
				if (tempSelRange.queryCommandValue('italic') == true) {
					textFlags |= ITALIC;
				}
				if (tempSelRange.queryCommandValue('underline') == true) {
					textFlags |= UNDERLINE;
				}
				if (tempSelRange.queryCommandValue('subscript') == true) {
					textFlags |= SUBSCRIPT;
				}
				if (tempSelRange.queryCommandValue('superscript') == true) {
					textFlags |= SUPERSCRIPT;
				}
				setStatus(STATUS_SELECT_TEXT, /* selOffsetStart + "$" + */
						fontName + "$" + fontSize + "$" + blockStyle + "$" + textFlags + "$" + selectedText);
			} else {
				if (selectionRange != null) {
					selectedText = selectionRange.toString();
				}
				var selParent = selection.focusNode;
				fontName = editorDoc.queryCommandValue('fontName');
				if (fontName == "") {
					fontName = "default";
				}
				fontSize = editorDoc.queryCommandValue('fontSize');
				if (fontSize == "") {
					fontSize = "default";
				}
				blockStyle = editorDoc.queryCommandValue('formatBlock');
				if (blockStyle == "p") {
					if (selParent.parentNode.className == "quote") {
						blockStyle = "<quote>";
					} else if (selParent.parentNode.className == "codeSample") {
						blockStyle = "<code>";
					} else {
						blockStyle = "<p>";
					}
				} else if (blockStyle == "h1") {
					blockStyle = "<h1>";
				} else if (blockStyle == "h2") {
					blockStyle = "<h2>";
				} else if (blockStyle == "h3") {
					blockStyle = "<h3>";
				} else if (blockStyle == "h4") {
					blockStyle = "<h4>";
				} else if (blockStyle == "h5") {
					blockStyle = "<h5>";
				} else if (blockStyle == "h6") {
					blockStyle = "<h6>";
				} else if (blockStyle == "") {
					blockStyle = "<p>";
				}
				if (editorDoc.queryCommandState('bold') == true) {
					textFlags |= BOLD;
				}
				if (editorDoc.queryCommandState('italic') == true) {
					textFlags |= ITALIC;
				}
				if (editorDoc.queryCommandState('underline') == true) {
					textFlags |= UNDERLINE;
				}
				if (editorDoc.queryCommandState('subscript') == true) {
					textFlags |= SUBSCRIPT;
				}
				if (editorDoc.queryCommandState('superscript') == true) {
					textFlags |= SUPERSCRIPT;
				}
				setStatus(STATUS_SELECT_TEXT, /* selOffsetStart + "$" + */
						fontName + "$" + fontSize + "$" + blockStyle + "$" + textFlags + "$" + selectedText);
			}
		} catch (e) { 
			log ("updateSelection() error:" + e.name + " : " + e.message);
		}
	} else {
			log ("updateSelection(): no selection.")
	}

	return true;
}

// Sets focus to this editor.
function setFocus() {
	if (!supportRichTextEditing) {
		return;
	}	
	if (document.all) {
		iframe = document.getElementById(editorId);
		iframe.focus();
	} else {
		contentWindow = document.getElementById(editorId).contentWindow;
		contentWindow.focus();
	}
	setStatus(STATUS_EXEC_CMD, 1);	
}

// Reformats element links created via drag & drop.
function reformatElementLinks() {
	var linksReformatted = 0;
	var elements = editorDoc.getElementsByTagName('A');
	for (var i = 0; i < elements.length; i++) {
		var element = elements[i];
		if (element.className.toLowerCase() == 'elementlink' ||
				element.className.toLowerCase() == 'elementlinkwithtype' ||
				element.className.toLowerCase() == 'elementlinkwithusertext') {
 			if (element.firstChild != null && element.firstChild.firstChild != null &&
 				element.firstChild.firstChild.firstChild != null) {
 				var linkText = element.firstChild.firstChild.firstChild.nodeValue;
 				element.removeChild(element.firstChild);
 				element.appendChild(editorDoc.createTextNode(linkText));
 				linksReformatted++;
 			}
		}
	}
	if (linksReformatted > 0) {
		setStatus(STATUS_REFORMAT_LINKS, null);
	}
}


function restoreSavedSelection() {
	var curSel = getSelectionInfo();
	if (curSel == null || curSel.text=='' || curSel.text==null) {
		//window.status = "formatText() : current selection is null.";
		if (selectionInfo != null) {
			//window.status = "formatText() : set current selection to " + printSelection(selectionInfo) ;
			setSelection(selectionInfo);
			//window.status = "formatText() : current selection is now "  + printSelection(selectionInfo) ;
		} else {
			//window.status = "formatText() : no saved selection info either.";
		}
	} else {
		//window.status = "formatText() : current selection is "  + printSelection(selectionInfo) ;
	}
}

// Formats the selected text.
function formatText(command, option) {
	window.status = "formatText() : begin";

	restoreSavedSelection();

	if (!readOnly /*&& internalUpdateSelection()*/) {
		var success = false;
		// execCommand doc : http://help.dottoro.com/ljcvtcaw.php
		// list of commands: http://help.dottoro.com/larpvnhw.php
		success = editorDoc.execCommand(command, false, option)
		if (success) {
			setStatus(STATUS_EXEC_CMD, 1);		
			setStatus(STATUS_MODIFIED, null);
		} else {
			window.status = "formatText("+command+") on "  + printSelection(selectionInfo)+" failed!" ;
		}
	}
}

// Adds HTML.
function addHTML(html) {
	if (!readOnly && html != "")  {
		html = decodeString(html);
		if (internalUpdateSelection()) {
			if (document.all) {
				if (selectionRange.text != null) {
					selectionRange.pasteHTML(html);
					setStatus(STATUS_EXEC_CMD, 1);
					setStatus(STATUS_MODIFIED, null);
				}
			}
			else {
				selectionRange.deleteContents();
				var documentFragment = selectionRange.createContextualFragment(html);
				selectionRange.insertNode(documentFragment);
				setStatus(STATUS_EXEC_CMD, 1);
				setStatus(STATUS_MODIFIED, null);
			}
		}
	}
}

// Adds an image.
function addImage(url, height, width, alt) {
	if (internalUpdateSelection()) {
		if (document.all) {
			if (url != null && url != '') {
				formatText('insertimage', url);
			}
			if (selection != null && selection.type == 'Control' && selectionRange != null) {
				if (height != null && height != '') selectionRange.item().height = height;
				if (width != null && width != '') selectionRange.item().width = width;
				if (alt != null) selectionRange.item().alt = alt;		
			}
		} else {
			var START_MARKER = "A_-_-_";
			var END_MARKER = ":.:.:";
				// mark img links with START_MARKER + id + END_MARKER in the id, for later recovery
			var elements = editorDoc.getElementsByTagName('img');
			for (var i = 0; i < elements.length; i++) {
				var element = elements[i];
				element.id = START_MARKER + element.id + END_MARKER;
			}
			if (url != null && url != '') {
				formatText('insertimage', url);
			}
			if (internalUpdateSelection()) {
				var regExID = new RegExp(START_MARKER + "(.*?)" + END_MARKER);
				var elements = editorDoc.getElementsByTagName('img');
				for (var i = 0; i < elements.length; i++) {
					var element = elements[i];
					var id = element.id;
					if (id != null && id != '') {
						RegExp.lastIndex=0;
						var matchArray = id.match(regExID);
						if (matchArray != null && matchArray.length > 0) {
							var newId = matchArray[1];
							if (newId.length > 0) {
								element.id = newId;
							} else {
								element.removeAttribute('id');
							}
						}
					} else {
						// no id, must be the new img
						if (height != null && height != '') element.height = height;
						if (width != null && width != '') element.width = width;
						if (alt != null) element.alt = alt;		
					}
				}
			}
		}
		setStatus(STATUS_MODIFIED, null);
	}
}

// Adds a horizontal line.
function addLine() {
	formatText('inserthorizontalrule', null);
}

// Adds a link.
function addLink(url) {
	if (!readOnly && url != null && url != '' && internalUpdateSelection()) {
		if (document.all) {
			if (selectionRange.text == null || selectionRange.text == '') {
				selectionRange.text = url;
				setStatus(STATUS_EXEC_CMD, 1);
				setStatus(STATUS_MODIFIED, null);
			}
			else if (selectionRange.execCommand('createlink', false, url)) {
				setStatus(STATUS_EXEC_CMD, 1);
				setStatus(STATUS_MODIFIED, null);
			}
		}
		else {
			if (selection == null || selection == "") {		
				var urlTextNode = editorDoc.createTextNode(url);
				insertNodeAtSelection(document.getElementById(editorFrameId).contentWindow, urlTextNode);
			}			
			if (editorDoc.execCommand('createlink', false, url)) {
				setStatus(STATUS_EXEC_CMD, 1);
				setStatus(STATUS_MODIFIED, null);
			}
		}
	}
}

// Adds an ordered list.
function addOrderedList() {
	formatText('insertorderedlist', null);
}

// Adds a table.
function addTable(rows, cols, width, summary, caption, tableheaders) {
	if (readOnly) return;
	if (rows == 0) rows = 2;
	if (cols == 0) cols = 2;
	if (width == 0) width = "85%";
	if (internalUpdateSelection()) {
		var table = editorDoc.createElement("table");
		table.cellPadding = "2";
		table.cellSpacing = "0";
		table.border = "1";
		table.width = width;
		table.title = "";
		if (summary != null && summary != '') {
			table.summary = summary;
		}
		if (caption != null && caption != '') {
			table.title = caption;
			table.createCaption();
			var captionNode = editorDoc.createTextNode(caption);
			table.caption.appendChild(captionNode);
		}
		tbody = editorDoc.createElement("tbody");
		for (var i = 0; i < rows; i++) {
			tr = editorDoc.createElement("tr");
			for (var j = 0; j < cols; j++) {
				if (i == 0 && (tableheaders == TABLE_HEADERS_COLS || tableheaders == TABLE_HEADERS_BOTH)) {
					th = editorDoc.createElement("th");
					th.scope = "col";
					th.id = "";
					th.abbr = th.id;
					var headerNode = editorDoc.createTextNode(th.id);
					th.appendChild(headerNode);
					if (!document.all) {
						br = editorDoc.createElement("br");
						th.appendChild(br);
					}
					tr.appendChild(th);
				}
				else if (j == 0 && (tableheaders == TABLE_HEADERS_ROWS || tableheaders == TABLE_HEADERS_BOTH)) {
					th = editorDoc.createElement("th");
					th.scope = "row";
					th.id = "";
					th.abbr = th.id;
					var headerNode = editorDoc.createTextNode(th.id);
					th.appendChild(headerNode);
					if (!document.all) {
						br = editorDoc.createElement("br");
						th.appendChild(br);
					}
					tr.appendChild(th);
				}
				else {
					td = editorDoc.createElement("td");
					if (!document.all) {
						br = editorDoc.createElement("br");
						td.appendChild(br);
					}
					tr.appendChild(td);
				}
			}
			tbody.appendChild(tr);
    	}
		table.appendChild(tbody);
		if (document.all) {
			selectionRange.parentElement().appendChild(table);
		}
		else {
			selectionRange.insertNode(table);
		}
		setStatus(STATUS_EXEC_CMD, 1);
		setStatus(STATUS_MODIFIED, null);			
	}
}

// Adds an unordered list.
function addUnorderedList() {
	formatText('insertunorderedlist', null);
}

// Sets the background color of the selected text.
function backColor(color) {
	if (color != null && color != '') {
		formatText('backcolor', color);
	}
}

// Toggles the 'bold' attribute of the selected text.
function bold() {
	formatText('bold', null);
}

// Copies the selected text to the clipboard.
function copy() {
	if (internalUpdateSelection()) {
		if (editorDoc.execCommand('copy', false, null)) {
			setStatus(STATUS_EXEC_CMD, 1);
		}
	}
}

// Cuts the selected text to the clipboard.
function cut() {
	formatText('cut', null);
}

// Deletes the selected text.
function deleteText() {
	formatText('delete', null);
}

// Finds text.
function findText(text, dir, options) {
    if (text == null || text == "") {
		return;
	}
	else {
		text = decodeString(text);
	}
	
	if (internalUpdateSelection()) {
		if (document.all) {
			selectionRange.collapse(dir < 0);
			if (selectionRange.findText(text, dir, options)) {
				selectionRange.scrollIntoView();
				selectionRange.select();
				selectionRange.collapse(dir < 0);
				setStatus(STATUS_EXEC_CMD, 1);
			}
		}
		else {	
			// find(text, caseSensitive, backwards, wrapAround, wholeWord, searchInFrames, showDialog)
			var caseSensitive = true;
			var backwards = false;
			var wholeWord = true;
			if ((options & 4) == 0) caseSensitive = false;
			if (dir == -1) backwards = true;
			if ((options & 2) == 0) wholeWord = false;
			
			if ( wholeWord ) 
			{
				while ( contentWindow.find(text, caseSensitive, backwards, false, wholeWord, false, false) )
				{
	        		var ffSelection = contentWindow.getSelection();
	        		selectionRange = ffSelection.getRangeAt(0).cloneRange();
					if (selectionRange.startOffset > 0 )
						selectionRange.setStart(selectionRange.startContainer, selectionRange.startOffset -1);
					if (selectionRange.endOffset < selectionRange.endContainer.length )
					{
						selectionRange.setEnd(selectionRange.endContainer, selectionRange.endOffset+1);
					}
					var newText = selectionRange.toString();
					var	regex = new RegExp("\\b"+text+"\\b");
	
					if ( newText.match(regex))
					{				 	
					   setStatus(STATUS_EXEC_CMD, 1);
					   break;
					}
				 } 
			} else if ( contentWindow.find(text, caseSensitive, backwards, false, wholeWord, false, false) ) {
			       setStatus(STATUS_EXEC_CMD, 1);
			}
		}
	}
}

// Sets the foreground color of the selected text.
function foreColor(color) {
	if (color != null && color != '') {
		formatText('forecolor', color);
	}
}

// Formats the selected text using the given HTML heading tag.
function formatBlock(tag) {
	if (tag != null && tag != '') {
		formatText('formatblock', tag);
	}
}


var INDENTED_LIST_BAD_HTML_IE = "</li>.*<li style=\"list-style: none\">";
var INDENTED_LIST_BAD_HTML_MOZ = "</li>.*<li style=\"list-style-type: none; list-style-image: none; list-style-position: outside;\">";

// Indents the selected text.
function indent() {
	formatText('indent', null);
	// fix for sub-lists
	var html = document.getElementById(editorId).contentWindow.document.body.innerHTML;
	if (document.all) {
		html = html.replace(INDENTED_LIST_BAD_HTML_IE, "");
	} else {
		// firefox sometimes puts the same as IE, sometimes more junk
		html = html.replace(INDENTED_LIST_BAD_HTML_IE, "");
		html = html.replace(INDENTED_LIST_BAD_HTML_MOZ, "");
	}
	setText(html);
}

// Toggles the 'italic' attribute of the selected text.
function italic() {
	formatText('italic', null);
}

// Center justifies the selected text.
function justifyCenter() {
	formatText('justifycenter', null);
}

// Fully justifies the selected text.
function justifyFull() {
	formatText('justifyfull', null);
}

// Left justifies the selected text.
function justifyLeft() {
	formatText('justifyleft', null);
}

// Right justifies the selected text.
function justifyRight() {
	formatText('justifyright', null);
}

// Outdents the selected text.
function outdent() {
	formatText('outdent', null);
}

// Pastes text from the clipboard.
function paste(sourceURL) {
	if (sourceURL == null) {
		sourceURL = "";
	}
	else {
		sourceURL = decodeString(sourceURL);
	}
	if (document.all) {
		var START_MARKER = "A_-_-_";
		var END_MARKER = ":.:.:";
		// mark img and <a /> links with START_MARKER + src/href + END_MARKER in the id, for later recovery
		var elements = editorDoc.getElementsByTagName('img');
		for (var i = 0; i < elements.length; i++) {
			var element = elements[i];
			var id = element.id;
			element.id = START_MARKER + element.src + END_MARKER + id;
		}
		var elements = editorDoc.getElementsByTagName('a');
		for (var i = 0; i < elements.length; i++) {
			var element = elements[i];
			var id = element.id;
			element.id = START_MARKER + element.href + END_MARKER + id;
		}

		// change the <base> of the document
		var oldBaseHREF = editorDoc.getElementsByTagName('base')[0].href;
		editorDoc.getElementsByTagName('base')[0].href = sourceURL;

		formatText('paste', null);
		
		// restore <base>
		editorDoc.getElementsByTagName('base')[0].href = oldBaseHREF;
	}
	else {
		setStatus(STATUS_EXEC_CMD, 1);
		setStatus(STATUS_MODIFIED, null);
	}
	if (internalUpdateSelection()) {
		try {
			var regExRes = new RegExp("file\:([^=]+)(/resources/)(.+)", "g");
			var regExRef = new RegExp("(.+)(#.+)");
			var regEx = new RegExp("file\:([^=]+)/([^/]+)", "g");	
			var regExID = new RegExp(START_MARKER + "(.*?)" + END_MARKER + "(.*?)");
			var elements = editorDoc.getElementsByTagName('img');
			for (var i = 0; i < elements.length; i++) {
				var element = elements[i];
				var id = element.id;
				if (id != null && id != '') {
					RegExp.lastIndex=0;
					var matchArray = id.match(regExID);
					if (matchArray != null && matchArray.length > 1) {
						element.src = matchArray[1];
						if (matchArray.length > 2 && matchArray[2].length > 0) {
							element.id = matchArray[2];
						}
						else {
							element.removeAttribute('id');
						}
						continue;
					}
				}
				var src = element.src;
				if (src != null && src != '') {
					if (src.indexOf('about:./resources') != -1) {
						// fix for IE 7 when pasting from another RTE
						// IE7 resolves these as "about:./resources/<file>"
						// so remove the "about:."
						src = src.replace("about:", "");
					}
					if (src.indexOf('about:resources') != -1) {
						// fix for IE 7 when pasting from another RTE
						// IE7 sometimes resolves these as "about:resources/<file>"
						// so remove the "about:" and put in "./"
						src = src.replace("about:", "./");
					}
					if (src.indexOf('resources') != -1) {
						element.src = src.replace(regExRes, "./resources/$3");
					}
					else {
						element.src = src.replace(regEx, "./resources/$2");
					}
				}
			}
			var elements = editorDoc.getElementsByTagName('a');
			for (var i = 0; i < elements.length; i++) {
				var element = elements[i];
				var id = element.id;
				if (id != null && id != '') {
					RegExp.lastIndex=0;
					var matchArray = id.match(regExID);
					if (matchArray != null && matchArray.length > 1) {
						element.href = matchArray[1];
						if (matchArray.length > 2 && matchArray[2].length > 0) {
							element.id = matchArray[2];
						}
						else {
							element.removeAttribute('id');
						}
						continue;
					}
				}
				var href = element.href;
				if (href != null && href != '') {
					// fix self-referencing hrefs
					if (href.indexOf('#') != -1) {
						RegExp.lastIndex=0;
						var matchArray = href.match(regExRef);
						if (matchArray != null && matchArray.length > 2) {
							var hrefFile = matchArray[1];
							var ref = matchArray[2];
							if (hrefFile == sourceURL) {
								element.href = ref;
								continue;
							}
						}
					}
					// fix hrefs already in resources
					if (href.indexOf('resources') != -1) {
						element.href = href.replace(regExRes, "./resources/$3");
					}
					// fix hrefs not in resources
					else {
						element.href = href.replace(regEx, "./resources/$2");
					}
				}
			}
		}
		catch (e) {
		}
	}
}

// Redo the previous command.
function redo() {
	formatText('redo', null);
}

// Redo the previous command.
function removeFormat() {
	formatText('removeformat', null);
}



function _replaceAllText(findText, replaceText, options) {
	// this is IE only
	if (document.all) {
		var tempRange =  document.getElementById(editorId).contentWindow.document.body.createTextRange();
		tempRange.moveStart('character', -10000000000);
		do {
			tempRange.collapse();
			if (tempRange.findText(findText, 10000000000, options)) {
				tempRange.text = replaceText;
				tempRange.select();
			} else {		
				break;
			}
		} while (true);
	}
}

// Replaces all text.
function replaceAllText(findText, replaceText, options) {
	if (readOnly || findText == null || findText == "") {
		return;
	}
	else {
		findText = decodeString(findText);
	}
	if (replaceText == null) {
		replaceText = "";
	}
	else {
		replaceText = decodeString(replaceText);
	}
	
	if (document.all) {
		// TODO: Move the insertion point to the start of the HTML
		// and perform a search and replace in the forward direction. 
		_replaceAllText(findText, replaceText, options);
	}
	else {
		// TODO: Emulate the IE implementation.
		var html = document.getElementById(editorId).contentWindow.document.body.innerHTML;
		var optionStr = "/g";
		if ((options & 4) == 0) {
			optionStr += "i";
		}
		var regExp = eval("/" + findText + optionStr);
		html = html.replace(regExp, replaceText);
		setText(html);
	}
	
	setStatus(STATUS_EXEC_CMD, 1);
	setStatus(STATUS_MODIFIED, null);
}

// Replaces text.
function replaceText(replaceText, dir, options) {
	if (readOnly || !internalUpdateSelection()) {
		return;
	}
	if (replaceText == null) {
		replaceText = "";
	}
	else {
		replaceText = decodeString(replaceText);
	}
	if (document.all) {
		selectionRange.text = replaceText;
		if (replaceText != "") {
			selectionRange.moveStart("word", -1);
			selectionRange.select();
			selectionRange.collapse(dir < 0);
		}
	}
	else {
		selectionRange.deleteContents();
		selectionRange.insertNode(editorDoc.createTextNode(replaceText));
	}
	setStatus(STATUS_EXEC_CMD, 1);
	setStatus(STATUS_MODIFIED, null);
}

// Selects all text.
function selectAll() {
	if (internalUpdateSelection()) {
		if (editorDoc.execCommand('selectall', false, null)) {
		   if ( !document.all )
		   {
		      updateSelection();
		   }
			setStatus(STATUS_EXEC_CMD, 1);
		}
	}
}

// Sets the font name for the selected text.
function setFontName(name) {
	if (internalUpdateSelection()) {
		if (name != null) {
			if (name == '') {
				formatText('removeFormat');
			} else {
				formatText('fontName', name);
			}
		}
	}
}

// Sets the font size for the selected text.
function setFontSize(size) {
	
	if (internalUpdateSelection()) {
		if (size != null) {
			if (size == '') {
				formatText('removeFormat');
			} else {
				formatText('fontSize', size);
			}
		}
	}
}

// Sets the font style for the selected text.
function setFontStyle(style) { 
	if (!readOnly && style != null && style != '' && internalUpdateSelection()) {
		try {
			if (document.all) {
				selectionRange.execCommand("removeformat");
				selectionRange.parentElement().removeAttribute("className");
			}
		}
		catch (e) {
		}
		if (style == "<quote>") {
			formatText('formatblock', '<p>');
			if (document.all) {
				selectionRange.parentElement().className = "quote";
			}
			else {
				selection.focusNode.parentNode.className = "quote";
			}
		}
		else if (style == "<code>") {
			formatText('formatblock', '<p>');
			if (document.all) {
				selectionRange.parentElement().className = "codeSample";
			}
			else {
				selection.focusNode.parentNode.className = "codeSample";
			}
		}
		else {
			if (!document.all && style == "<p>") {
				// A hack to get rid of the "className" attribute in Mozilla/Firefox.
				formatText('formatblock', '<h4>');
			}
			formatText('formatblock', style);
		}
	}
}

// Sets whether the content can be edited.
function setEditable(editable) {
	var doc = document.getElementById(editorId).contentWindow.document;
    if (editable != null && editable == 'true') {
		if (document.all) {
			doc.body.contentEditable = "true";
		}
		else {
			doc.designMode = "on";
		}
		readOnly = false;
	}
	else {
		if (document.all) {		
			doc.body.contentEditable = "false";
		}
		else {
			doc.designMode = "off";
		}
		readOnly = true;
	}
	setStatus(STATUS_EXEC_CMD, 1);	
}

// Toggles the 'strike-through' attribute of the selected text.
function strikeThrough() {
	formatText('strikethrough', size);
}

// Toggles the 'subscript' attribute of the selected text.
function subscript() {
	formatText('subscript', null);
}

// Toggles the 'superscript' attribute of the selected text.
function superscript() {
	formatText('superscript', null);
}

// Toggles the 'underline' attribute of the selected text.
function underline() {
	formatText('underline', null);
}

// Converts a link to normal text.
function unlink() {
	formatText('unlink', null);
}

function ObjToString(object) {
	var ret = "Object " + object.name + " is [\n";
	for (var prop in object) {
		ret += "  " + prop + " is " + object[prop] + ";\n";
	}
	return ret + "]";
}


function addRow() {
	if (currentSelectedTable != null) {
		rowNums = currentSelectedTable.rows.length;
		cellNums = currentSelectedTable.rows[0].cells.length;
		var newRow = currentSelectedTable.insertRow(rowNums);
		for ( i = 0; i < cellNums; i++) {
			var newCell = newRow.insertCell(i);
			if (!document.all) {
				newCell.innerHTML = "<br/>";
			}
		}
		setStatus(STATUS_MODIFIED, null);
	}
}

function addColumn() {
	if (currentSelectedTable != null) {
		rowNums = currentSelectedTable.rows.length;
		cellNums = currentSelectedTable.rows[0].cells.length;
		for (var i = 0; i < rowNums; i++) {
			var newCell = currentSelectedTable.rows[i].insertCell(cellNums);
			if (!document.all) {
				newCell.innerHTML = "<br/>";
			}
		}
		setStatus(STATUS_MODIFIED, null);
	}
}

function deleteLastRow() {
	if (currentSelectedTable != null) {
		rowNums = currentSelectedTable.rows.length;
		if (rowNums == 1) {
			var parent = currentSelectedTable.parentNode;
			parent.removeChild(currentSelectedTable);
			currentSelectedTable = null;
		} else {
			currentSelectedTable.deleteRow(rowNums - 1);
		}
		setStatus(STATUS_MODIFIED, null);
	}
}

function deleteLastColumn() {
	if (currentSelectedTable != null) {
		rowNums = currentSelectedTable.rows.length;
		cellNums = currentSelectedTable.rows[0].cells.length;
		if (cellNums == 1) {
			var parent = currentSelectedTable.parentNode;
			parent.removeChild(currentSelectedTable);
			currentSelectedTable = null;
		} else {
			for (var i = 0; i < rowNums; i++) {
				currentSelectedTable.rows[i].deleteCell(cellNums - 1);
			}
		}
		setStatus(STATUS_MODIFIED, null);
	}
}
