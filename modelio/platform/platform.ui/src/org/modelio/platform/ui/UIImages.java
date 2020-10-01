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
    @objid ("c9cd83f5-e925-438e-9b34-6a0e3cf7b15e")
    public static final Image ACCEPT = UIImages.getImage("icons/accept.png");

    /**
     * Generic 24x24 icon used to indicate an "Add" action. The dialog/button context must be relevant enough for the user to guess what will be added...
     */
    @objid ("2ee0cec7-10a7-46d0-a346-525cea9b8468")
    public static final Image ADD = UIImages.getImage("icons/add.png");

    /**
     * A generic 24x24 icon used to indicate that a field supports assistance.
     */
    @objid ("bf8b975a-6650-4b07-b45d-80782f6e87d5")
    public static final Image ASSIST = UIImages.getImage("icons/assist.png");

    /**
     * Generic 24x24 icon used to indicate an "Cancel" or "Refuse" action.
     */
    @objid ("87ffb305-d103-42e0-b26a-09de23524c05")
    public static final Image CANCEL = UIImages.getImage("icons/cancel.png");

    /**
     * Generic 24x24 icon used to indicate that on operation (create/add/edit) will occur in a separated dialog a "Delete" or "Remove" action. The dialog/button context must be relevant enough for the user to guess what will happen ...
     */
    @objid ("0f6d8658-e690-4381-aa3f-3ac31adc2b55")
    public static final Image CHOOSER = UIImages.getImage("icons/chooser.png");

    /**
     * Generic 24x24 icon used to indicate a standard copy operation.
     */
    @objid ("44138aad-f020-4567-92b4-be01075b1567")
    public static final Image COPY = UIImages.getImage("icons/copy.png");

    /**
     * Generic 24x24 icon used to indicate a standard cut operation.
     */
    @objid ("c952da49-1fa0-43e2-9a28-7f311e6aa445")
    public static final Image CUT = UIImages.getImage("icons/cut.png");

    /**
     * Generic 24x24 icon used to indicate a "Delete" action.
     */
    @objid ("6091e948-c92b-4435-9f60-23128147f65f")
    public static final Image DELETE = UIImages.getImage("icons/delete.png");

    /**
     * Generic 24x24 icon used to indicate an element without icon.
     */
    @objid ("4fd765b7-c73d-4466-9a7a-b8735ec1bfaa")
    public static final Image DOT = UIImages.getImage("icons/dot.png");

    /**
     * Generic 24x24 icon used to indicate a "down" move or slide operation
     */
    @objid ("2dfa3fd7-59b3-4743-9b0d-686a59fb144d")
    public static final Image DOWNARROW = UIImages.getImage("icons/downarrow.png");

    /**
     * A generic 24x24 icon used to indicate that a field supports D&D.
     */
    @objid ("9b64a02b-ebca-466a-a4a4-927e4855ea6e")
    public static final Image DROPAREA = UIImages.getImage("icons/droparea.png");

    /**
     * Generic 24x24 icon used to indicate an "Edit" action. The dialog/button context must be relevant enough for the user to guess what will be edited...
     */
    @objid ("ab7e1b91-217e-4fcc-8278-bc94038a51a5")
    public static final Image EDIT = UIImages.getImage("icons/edit.png");

    /**
     * Generic 24x24 icon used to indicate a "filechooser" action
     */
    @objid ("ea019318-e3d2-47b0-ac5d-f74cfbfc5b26")
    public static final Image FILECHOOSE = UIImages.getImage("icons/filechooser.png");

    /**
     * Generic 24x24 icon used to indicate a 'filter' operation.
     */
    @objid ("5db7f13c-e89f-443a-939d-5600db704cf3")
    public static final Image FILTER = UIImages.getImage("icons/filter.png");

    /**
     * A generic 24x24 icon used for help buttons.
     */
    @objid ("cf03f645-b761-429d-be11-be2d20ef600a")
    public static final Image HELP = UIImages.getImage("icons/help.png");

    /**
     * A generic icon used to indicate that a field supports picking service.
     */
    @objid ("b08e19b7-cf3a-4fd9-b691-19e141ea2863")
    public static final Image INDICATOR = UIImages.getImage("icons/indicator.png");

    /**
     * Generic 24x24 icon used to indicate a "left" move or slide operation
     */
    @objid ("bc91c2ca-6335-4fb3-a37e-a6b549f7cceb")
    public static final Image LEFTARROW = UIImages.getImage("icons/leftarrow.png");

    /**
     * Generic 24x24 icon used to indicate a "maximize" operation
     */
    @objid ("336e5370-cb46-4f54-aec9-6b6545ee6f3e")
    public static final Image MAXIMIZE = UIImages.getImage("icons/maximize.png");

    /**
     * Generic 24x24 icon used to indicate a "minimize" operation
     */
    @objid ("3fe3c552-5098-4f58-9ffe-9acff5d23df8")
    public static final Image MINIMIZE = UIImages.getImage("icons/minimize.png");

    /**
     * Generic 24x24 icon used to indicate a standard paste operation.
     */
    @objid ("8736580a-f7ba-41b7-becc-4c2a2ad99f95")
    public static final Image PASTE = UIImages.getImage("icons/paste.png");

    /**
     * Generic 24x24 icon used to indicate an icon place holder
     */
    @objid ("c84173bd-95b2-4fdf-85f0-f3dbba8d3f6d")
    public static final Image PLACEHOLDER = UIImages.getImage("icons/placeholder.png");

    /**
     * Generic 48x48 image used to indicate an image place holder
     */
    @objid ("f0e25e10-b8ad-4bee-91f0-ba10b49063f6")
    public static final Image PLACEHOLDER_48 = UIImages.getImage("images/placeholder48x48.png");

    /**
     * Generic 64x64 image used to indicate an image place holder
     */
    @objid ("8ec51056-f575-477f-bd74-faed410c042b")
    public static final Image PLACEHOLDER_64 = UIImages.getImage("images/placeholder64x64.png");

    /**
     * Generic 24x24 icon used to indicate a "Remove" action.
     */
    @objid ("1bcfa7e0-d691-4f49-8765-80ce4da88bc4")
    public static final Image REMOVE = UIImages.getImage("icons/remove.png");

    /**
     * Generic 24x24 icon used to indicate a "right" move or slide operation
     */
    @objid ("5e75604f-12f7-448a-a4a1-ad57686b9045")
    public static final Image RIGHTARROW = UIImages.getImage("icons/rightarrow.png");

    /**
     * Generic 24x24 icon used to indicate a 'search' operation.
     */
    @objid ("24842338-2cbf-431d-b3c9-79f6517c93fb")
    public static final Image SEARCH = UIImages.getImage("icons/search.png");

    /**
     * Generic 24x24 icon used to indicate a select in explorer operation
     */
    @objid ("c9d98a5b-269d-44de-aee2-68eb90c7aacc")
    public static final Image SELECTINBROWSER = UIImages.getImage("icons/selectinbrowser.png");

    /**
     * Generic 24x24 icon used to indicate a "up" move or slide operation
     */
    @objid ("65fa108d-9bb4-4ff3-b060-3a158cc65144")
    public static final Image UPARROW = UIImages.getImage("icons/uparrow.png");

    /**
     * Generic 24x24 icon used to represent a "checked" box.
     */
    @objid ("ade8acd7-2968-41bc-bbd1-1d6b5d4ca8dc")
    public static final Image CHECKED = UIImages.getImage("icons/checked.png");

    /**
     * Generic 24x24 icon used to represent an "unchecked" box.
     */
    @objid ("707b5be1-4d1d-42b5-8335-c935d6ccfd74")
    public static final Image UNCHECKED = UIImages.getImage("icons/unchecked.png");

    @objid ("e507fdf4-3f36-4f39-8351-82a02b963917")
    private UIImages() {
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
