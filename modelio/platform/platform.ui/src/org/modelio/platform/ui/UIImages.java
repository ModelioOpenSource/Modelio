/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.modelio.platform.ui;

import java.net.URL;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.modelio.platform.ui.plugin.UI;
import org.osgi.framework.Bundle;

/**
 * The UIImages defines the normalized icons in Modelio General GUI.
 */
@objid ("23eee14b-ae7f-4a4a-9f41-ad4f3c57462c")
public class UIImages {
    /**
     * Generic 24x24 icon used to indicate an "Accept" or "Ok" action.
     */
    @objid ("d87237c7-8013-4ea3-83c1-147eae74b8e8")
    public static final Image ACCEPT = UIImages.getImage("icons/accept.png");

    /**
     * Generic 24x24 icon used to indicate an "Add" action. The dialog/button context must be relevant enough for the user to guess what will be added...
     */
    @objid ("2eebc711-794f-4e9e-9131-d7a4bdcbb674")
    public static final Image ADD = UIImages.getImage("icons/add.png");

    /**
     * A generic 24x24 icon used to indicate that a field supports assistance.
     */
    @objid ("b48a778f-2733-4a78-9651-22fd364a6a25")
    public static final Image ASSIST = UIImages.getImage("icons/assist.png");

    /**
     * Generic 24x24 icon used to indicate an "Cancel" or "Refuse" action.
     */
    @objid ("82715696-f062-488f-b752-8069dfdf0ca0")
    public static final Image CANCEL = UIImages.getImage("icons/cancel.png");

    /**
     * Generic 24x24 icon used to indicate that on operation (create/add/edit) will occur in a separated dialog a "Delete" or "Remove" action. The dialog/button context must be relevant enough for the user to guess what will happen ...
     */
    @objid ("c893ee98-71e5-4d18-8e3e-da9ad611b397")
    public static final Image CHOOSER = UIImages.getImage("icons/chooser.png");

    /**
     * Generic 24x24 icon used to indicate a standard copy operation.
     */
    @objid ("2c2357db-e426-4ab7-930c-9361565c0e07")
    public static final Image COPY = UIImages.getImage("icons/copy.png");

    /**
     * Generic 24x24 icon used to indicate a standard cut operation.
     */
    @objid ("0316a77f-c741-4139-b716-8b7ca5fdccec")
    public static final Image CUT = UIImages.getImage("icons/cut.png");

    /**
     * Generic 24x24 icon used to indicate a "Delete" action.
     */
    @objid ("1d65c7e9-4b73-4851-b6cf-dfdbbd325377")
    public static final Image DELETE = UIImages.getImage("icons/delete.png");

    /**
     * Generic 24x24 icon used to indicate an element without icon.
     */
    @objid ("4c647ad2-c1a3-4d84-9e8f-d5122b3178b6")
    public static final Image DOT = UIImages.getImage("icons/dot.png");

    /**
     * Generic 24x24 icon used to indicate a "down" move or slide operation
     */
    @objid ("6cbdde20-c139-4cda-bbb7-4fd8a5968fbd")
    public static final Image DOWNARROW = UIImages.getImage("icons/downarrow.png");

    /**
     * A generic 24x24 icon used to indicate that a field supports D&D.
     */
    @objid ("453dac12-c66b-416c-b031-b3d527bc6f1b")
    public static final Image DROPAREA = UIImages.getImage("icons/droparea.png");

    /**
     * Generic 24x24 icon used to indicate an "Edit" action. The dialog/button context must be relevant enough for the user to guess what will be edited...
     */
    @objid ("58e31faa-7156-4d79-bbb0-ea4528e723ee")
    public static final Image EDIT = UIImages.getImage("icons/edit.png");

    /**
     * Generic 24x24 icon used to indicate a "filechooser" action
     */
    @objid ("e5a50ff1-454f-4e96-8f48-767d683e81f0")
    public static final Image FILECHOOSE = UIImages.getImage("icons/filechooser.png");

    /**
     * Generic 24x24 icon used to indicate a 'filter' operation.
     */
    @objid ("af6eb24c-0ee0-4f77-a3a4-35d520e485b8")
    public static final Image FILTER = UIImages.getImage("icons/filter.png");

    /**
     * A generic 24x24 icon used for help buttons.
     */
    @objid ("487001e0-9c1e-4e41-be4c-961409880ef3")
    public static final Image HELP = UIImages.getImage("icons/help.png");

    /**
     * A generic icon used to indicate that a field supports picking service.
     */
    @objid ("e66a400b-00d3-41c5-bb2c-addebb6c11bd")
    public static final Image INDICATOR = UIImages.getImage("icons/indicator.png");

    /**
     * Generic 24x24 icon used to indicate a "left" move or slide operation
     */
    @objid ("df08f4a0-c3e8-4020-99a7-367a5f84e91b")
    public static final Image LEFTARROW = UIImages.getImage("icons/leftarrow.png");

    /**
     * Generic 24x24 icon used to indicate a "maximize" operation
     */
    @objid ("06481452-42f1-45c5-8125-6c5051c649bf")
    public static final Image MAXIMIZE = UIImages.getImage("icons/maximize.png");

    /**
     * Generic 24x24 icon used to indicate a "minimize" operation
     */
    @objid ("8a30ccb3-dd2b-493c-b646-31ff8068cda9")
    public static final Image MINIMIZE = UIImages.getImage("icons/minimize.png");

    /**
     * Generic 24x24 icon used to indicate a standard paste operation.
     */
    @objid ("f27a925a-18f6-48e3-be72-fda6b51d3b58")
    public static final Image PASTE = UIImages.getImage("icons/paste.png");

    /**
     * Generic 24x24 icon used to indicate an icon place holder
     */
    @objid ("981cad54-28a8-4433-8dbf-bfc4f6f48aba")
    public static final Image PLACEHOLDER = UIImages.getImage("icons/placeholder.png");

    /**
     * Generic 48x48 image used to indicate an image place holder
     */
    @objid ("2306e6d0-e9ec-4510-a330-0803350548b3")
    public static final Image PLACEHOLDER_48 = UIImages.getImage("images/placeholder48x48.png");

    /**
     * Generic 64x64 image used to indicate an image place holder
     */
    @objid ("ca86b443-9383-4195-8e48-218026d2be60")
    public static final Image PLACEHOLDER_64 = UIImages.getImage("images/placeholder64x64.png");

    /**
     * Generic 24x24 icon used to indicate a "Remove" action.
     */
    @objid ("69a7366b-30f4-46dc-853c-9bf0117f1fa6")
    public static final Image REMOVE = UIImages.getImage("icons/remove.png");

    /**
     * Generic 24x24 icon used to indicate a "right" move or slide operation
     */
    @objid ("e24428b3-31a9-4313-9f9e-54b9003882df")
    public static final Image RIGHTARROW = UIImages.getImage("icons/rightarrow.png");

    /**
     * Generic 24x24 icon used to indicate a 'search' operation.
     */
    @objid ("5e2e40c0-6737-46ee-9770-0fe2fdd5f43f")
    public static final Image SEARCH = UIImages.getImage("icons/search.png");

    /**
     * Generic 24x24 icon used to indicate a select in explorer operation
     */
    @objid ("4b5a299c-a6b7-4722-8e37-67078cc9e9c3")
    public static final Image SELECTINBROWSER = UIImages.getImage("icons/selectinbrowser.png");

    /**
     * Generic 24x24 icon used to indicate a "up" move or slide operation
     */
    @objid ("e83658ab-6262-43d1-a97a-f7440be8369b")
    public static final Image UPARROW = UIImages.getImage("icons/uparrow.png");

    /**
     * Generic 24x24 icon used to represent a "checked" box.
     */
    @objid ("746e498d-b54b-44b8-9c45-c5d852196f5a")
    public static final Image CHECKED = UIImages.getImage("icons/checked.png");

    /**
     * Generic 24x24 icon used to represent an "unchecked" box.
     */
    @objid ("bcde037f-281b-42fb-917f-9f6456fc4a5f")
    public static final Image UNCHECKED = UIImages.getImage("icons/unchecked.png");

    /**
     * Generic 24x24 icon used to represent the concept or ERROR
     */
    @objid ("cdd40126-062c-4adf-877d-416ca962442d")
    public static final Image ERROR = UIImages.getImage("icons/error.png");

    /**
     * Generic 24x24 icon used to represent the concept of WARNING
     */
    @objid ("25c6d036-9e70-4c95-8a36-f0e711710d62")
    public static final Image WARNING = UIImages.getImage("icons/warning.png");

    /**
     * Generic 24x24 icon used to represent the concept of TIP.
     */
    @objid ("9b21a4f4-f0a4-4edc-87f1-6227d22b9d50")
    public static final Image TIP = UIImages.getImage("icons/tip.png");

    @objid ("01174469-5237-43b3-b9c2-6bc49edeeb84")
    public static final Image CONFIG = UIImages.getImage("icons/config.png");

    /**
     * Generic 24x24 icon used to represent the concept of INFO.
     */
    @objid ("354423b1-a8a4-41ac-bad8-5f587136ea7c")
    public static final Image INFO = UIImages.getImage("icons/info.png");

    @objid ("e507fdf4-3f36-4f39-8351-82a02b963917")
    private  UIImages() {
        
    }

    @objid ("165b4af5-07f9-4a23-93e2-c53c9917a9ee")
    private static Image getImage(final String relativePath) {
        final Bundle bundle = UI.getContext().getBundle();
        final URL url = bundle.getEntry(relativePath);
        final ImageDescriptor desc = ImageDescriptor.createFromURL(url);
        final Image image = desc.createImage();
        return image;
    }

}
