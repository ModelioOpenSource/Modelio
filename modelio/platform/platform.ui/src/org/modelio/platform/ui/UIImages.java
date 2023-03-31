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
    @objid ("5ac18b02-76a4-4717-a46f-822be247fc62")
    public static final Image ACCEPT = UIImages.getImage("icons/accept.png");

    /**
     * Generic 24x24 icon used to indicate an "Add" action. The dialog/button context must be relevant enough for the user to guess what will be added...
     */
    @objid ("e070240b-fb5f-4e83-8b86-b4a1413b9500")
    public static final Image ADD = UIImages.getImage("icons/add.png");

    /**
     * Generic 24x24 icon used to indicate a "Remove" action.
     */
    @objid ("985cebdb-4425-4118-b609-96f2889b60e4")
    public static final Image REMOVE = UIImages.getImage("icons/remove.png");

    /**
     * A generic 24x24 icon used to indicate that a field supports assistance.
     */
    @objid ("6697d3cd-73d4-48b7-aeb5-2c4b049a0895")
    public static final Image ASSIST = UIImages.getImage("icons/assist.png");

    /**
     * Generic 24x24 icon used to indicate an "Cancel" or "Refuse" action.
     */
    @objid ("354bd2f2-8c5c-4245-bf07-4385581d45e0")
    public static final Image CANCEL = UIImages.getImage("icons/cancel.png");

    /**
     * Generic 24x24 icon used to indicate that on operation (create/add/edit) will occur in a separated dialog a "Delete" or "Remove" action. The dialog/button context must be relevant enough for the user to guess what will happen ...
     */
    @objid ("89f6bced-1aa9-4792-ac70-fc691d44d05c")
    public static final Image CHOOSER = UIImages.getImage("icons/chooser.png");

    @objid ("84c803ad-2768-45b8-9cdc-5a09423bd44b")
    public static final Image CONFIG = UIImages.getImage("icons/config.png");

    /**
     * Generic 24x24 icon used to indicate a standard copy operation.
     */
    @objid ("4b096796-cb7a-4b8d-805a-569cc532df78")
    public static final Image COPY = UIImages.getImage("icons/copy.png");

    /**
     * Generic 24x24 icon used to indicate a standard cut operation.
     */
    @objid ("4a56bbe7-a003-44a4-9347-bcf998e35def")
    public static final Image CUT = UIImages.getImage("icons/cut.png");

    /**
     * Generic 24x24 icon used to indicate a standard paste operation.
     */
    @objid ("12fae199-113c-4116-8900-fdc404a0d36d")
    public static final Image PASTE = UIImages.getImage("icons/paste.png");

    /**
     * Generic 24x24 icon used to indicate a "Delete" action.
     */
    @objid ("7ee6e3ab-f0a5-4853-aa68-17a228c88e64")
    public static final Image DELETE = UIImages.getImage("icons/delete.png");

    /**
     * Generic 24x24 icon used to indicate an element without icon.
     */
    @objid ("6f544e3b-c2f4-49b6-a83a-e6d190009429")
    public static final Image DOT = UIImages.getImage("icons/dot.png");

    /**
     * A generic 24x24 icon used to indicate that a field supports D&D.
     */
    @objid ("659c2f13-ff09-43eb-84b5-75d76a37f48b")
    public static final Image DROPAREA = UIImages.getImage("icons/droparea.png");

    /**
     * Generic 24x24 icon used to indicate an "Edit" action. The dialog/button context must be relevant enough for the user to guess what will be edited...
     */
    @objid ("d8c69394-e680-4817-96a8-2e63a07bf832")
    public static final Image EDIT = UIImages.getImage("icons/edit.png");

    /**
     * Generic 24x24 icon used to indicate a "filechooser" action
     */
    @objid ("50c2a7aa-067e-4d97-83e2-2eca06e5b818")
    public static final Image FILECHOOSE = UIImages.getImage("icons/filechooser.png");

    /**
     * Generic 24x24 icon used to indicate a 'filter' operation.
     */
    @objid ("afdaa07a-a420-4dd6-9485-08919958c04c")
    public static final Image FILTER = UIImages.getImage("icons/filter.png");

    /**
     * A generic 24x24 icon used for help buttons.
     */
    @objid ("9fa1049a-4b58-49ef-829e-dfe4a384ce99")
    public static final Image HELP = UIImages.getImage("icons/help.png");

    /**
     * A generic icon used to indicate that a field supports picking service.
     */
    @objid ("0ba309d6-76f2-4550-a5c1-704349f19b94")
    public static final Image INDICATOR = UIImages.getImage("icons/indicator.png");

    /**
     * Generic 24x24 icon used to indicate a "maximize" operation
     */
    @objid ("c69cf93c-1faa-4eae-822f-b4ded4157b49")
    public static final Image MAXIMIZE = UIImages.getImage("icons/maximize.png");

    /**
     * Generic 24x24 icon used to indicate a "minimize" operation
     */
    @objid ("d44bc7f7-c00c-458a-8309-95649c9cc35a")
    public static final Image MINIMIZE = UIImages.getImage("icons/minimize.png");

    /**
     * Generic 24x24 icon used to indicate an "Open properties" action.
     * @since 5.2
     */
    @objid ("71bb3e89-9ced-4a9d-a484-9b049e8098fb")
    public static final Image OPENPROPERTIES = UIImages.getImage("icons/openproperties.png");

    /**
     * Generic 24x24 icon used to indicate an icon place holder
     */
    @objid ("9256f0ab-5bd6-40b6-8040-fef336dc1310")
    public static final Image PLACEHOLDER = UIImages.getImage("icons/placeholder.png");

    /**
     * Generic 48x48 image used to indicate an image place holder
     */
    @objid ("a85e068a-3e51-42a2-9f0b-df55a5606ca6")
    public static final Image PLACEHOLDER_48 = UIImages.getImage("images/placeholder48x48.png");

    /**
     * Generic 64x64 image used to indicate an image place holder
     */
    @objid ("485d6cc1-8bb8-44fd-af3f-3341f942fb68")
    public static final Image PLACEHOLDER_64 = UIImages.getImage("images/placeholder64x64.png");

    /**
     * Generic 24x24 icon used to indicate an icon place holder
     * Empty 24x24 icon used to indicate an icon place holder
     */
    @objid ("f81c7ece-faca-40b7-9284-050c9e03a047")
    public static final Image EMPTY = UIImages.getImage("icons/empty.png");

    /**
     * Generic 48x48 image used to indicate an image place holder
     * Empty 48x48 image used to indicate an image place holder
     */
    @objid ("ac2594b3-66d0-4bc5-a763-fa150338010e")
    public static final Image EMPTY_48 = UIImages.getImage("images/empty48x48.png");

    /**
     * Generic 64x64 image used to indicate an image place holder
     * Empty 64x64 image used to indicate an image place holder
     */
    @objid ("ea874b1d-50f8-4004-813a-ffd9d3a5d368")
    public static final Image EMPTY_64 = UIImages.getImage("images/empty64x64.png");

    /**
     * Generic 24x24 icon used to indicate a 'search' operation.
     */
    @objid ("a38a3189-b40b-4956-a77a-70552c07cfdb")
    public static final Image SEARCH = UIImages.getImage("icons/search.png");

    /**
     * Generic 24x24 icon used to indicate a select in explorer operation
     */
    @objid ("3328022d-efd1-4eb2-b85e-8577b4948d36")
    public static final Image SELECTINBROWSER = UIImages.getImage("icons/selectinbrowser.png");

    /**
     * Generic 24x24 icon used to indicate a "up" move or slide operation
     */
    @objid ("8f5d216f-29a4-4090-a6a2-2f5ed5d6cf8e")
    public static final Image UPARROW = UIImages.getImage("icons/uparrow.png");

    /**
     * Generic 24x24 icon used to indicate a "down" move or slide operation
     */
    @objid ("f2d022c1-43f6-42c7-9c2a-0e3acf715d05")
    public static final Image DOWNARROW = UIImages.getImage("icons/downarrow.png");

    /**
     * Generic 24x24 icon used to indicate a "left" move or slide operation
     */
    @objid ("fa63a9d5-66ec-47a0-829b-0c193ca24aa1")
    public static final Image LEFTARROW = UIImages.getImage("icons/leftarrow.png");

    /**
     * Generic 24x24 icon used to indicate a "right" move or slide operation
     */
    @objid ("02b3e9b3-b6ab-4274-a181-254293417600")
    public static final Image RIGHTARROW = UIImages.getImage("icons/rightarrow.png");

    /**
     * Generic 24x24 icon used to represent a "checked" box.
     */
    @objid ("64c5f34c-c4d0-4a0a-80ae-85f4a3d14dd2")
    public static final Image CHECKED = UIImages.getImage("icons/checked.png");

    /**
     * Generic 24x24 icon used to represent an "unchecked" box.
     */
    @objid ("06467501-d75f-4a5d-a9aa-0fd9cc45f418")
    public static final Image UNCHECKED = UIImages.getImage("icons/unchecked.png");

    /**
     * Generic 24x24 icon used to represent the concept or ERROR
     */
    @objid ("bd235c62-24f3-4a2a-aaee-219fb455ce35")
    public static final Image ERROR = UIImages.getImage("icons/error.png");

    /**
     * Generic 24x24 icon used to represent the concept of WARNING
     */
    @objid ("9ffc07ce-59a8-4003-83c9-3dd44b213ccc")
    public static final Image WARNING = UIImages.getImage("icons/warning.png");

    /**
     * Generic 24x24 icon used to represent the concept of TIP.
     */
    @objid ("d266480d-95b4-4398-9570-7012586e009c")
    public static final Image TIP = UIImages.getImage("icons/tip.png");

    /**
     * Generic 24x24 icon used to represent the concept of INFO.
     */
    @objid ("fef2d77b-6128-4b37-8831-f0b5358e8a45")
    public static final Image INFO = UIImages.getImage("icons/info.png");

    /**
     * Generic 24x24 icon used for Modelio itself.
     * @since 5.2
     */
    @objid ("e3a6b6a1-bff0-4e34-866e-d5dcb89f8dbd")
    public static final Image MODELIO = UIImages.getImage("icons/modelio.png");

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
