/**
 * @license Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	
	//Modelio
	config.skin = 'office2013';
	
	// The toolbar groups arrangement, optimized for two toolbar rows.
    config.toolbar = [
	  //{ name: 'document', groups: [ 'mode', 'document', 'doctools' ], items: [ 'Source', '-', 'Save', 'NewPage', 'Preview', 'Print', '-', 'Templates' ] },
	  //{ name: 'clipboard', groups: [ 'clipboard', 'undo' ], items: [ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo' ] },
	  //{ name: 'editing', groups: [ 'find', 'selection', 'spellchecker' ], items: [ 'Find', 'Replace', '-', 'SelectAll', '-', 'Scayt' ] },
	  //{ name: 'forms', items: [ 'Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField' ] },
	  
	  { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ], items: [ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript', '-', 'RemoveFormat' ] },
	  { name: 'paragraph', groups: [ 'list', 'indent' ], items: [ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent' ] },
	  //{ name: 'links', items: [ 'Link', 'Unlink', 'Anchor' ] },
	  { name: 'insert', items: [ 'Table', 'HorizontalRule' ] },
	  
	  { name: 'styles', items: [  'Format', 'Font', 'FontSize' ] },
	  { name: 'colors', items: [ 'TextColor', 'BGColor' ] },
	  //{ name: 'tools', items: [ 'Maximize', 'ShowBlocks' ] },
	  //{ name: 'others', items: [ '-' ] },
	  //{ name: 'about', items: [ 'About' ] }
];
	
	// Remove unused plugins
	config.removePlugins = 'elementspath, save, about, clipboard, link, undo, a11yhelp, blockquote, contextmenu, filebrowser,  magicline,  pastetext, pastefromword, sourcearea, scaayt, stylescombo, bidi, div, flash, forms, iframe, newpage, pagebreak, preview, save, showblocks, selectall  ' ;

	// Remove some buttons provided by the standard plugins, which are
	// not needed in the Standard(s) toolbar.
	//config.removeButtons = 'Underline,Subscript,Superscript,Smiley,Image,Specialchar';

	// Set the most common block elements.
	config.format_tags = 'p;h1;h2;h3;h4';

	// Simplify the dialog windows.
	config.removeDialogTabs = 'image:advanced;link:advanced';
	
	
	config.fontSize_sizes = '8/8px;9/9px;10/10px;11/11px;12/12px;14/14px;16/16px;18/18px;22/22px;24/24px;36/36px;48/48px;';
	
	
	config.resize_enabled = false;
	config.toolbarLocation = 'top';
	config.toolbarCanCollapse = false; 
	config.toolbarStartupExpanded = true;
	config.scayt_autoStartup = false;
	config.startupFocus = true;
	
	
};
