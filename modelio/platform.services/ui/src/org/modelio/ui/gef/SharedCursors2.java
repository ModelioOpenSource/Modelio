/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.ui.gef;

import java.net.URL;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.SharedCursors;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.Bundle;

/**
 * An extension of the shared collection of Cursors.
 * 
 * @since 2.0
 */
@objid ("92bf67da-1eae-11e2-8cad-001ec947c8cc")
public class SharedCursors2 extends SharedCursors {
    /**
     * Cursor for reparenting nodes
     */
    @objid ("92bf67dc-1eae-11e2-8cad-001ec947c8cc")
    public static final Cursor CURSOR_REPARENT;

    @objid ("92bf67df-1eae-11e2-8cad-001ec947c8cc")
    public static final Cursor CURSOR_REPARENT_NOT;

    /**
     * Neutral cursor for picking
     */
    @objid ("92bf67e1-1eae-11e2-8cad-001ec947c8cc")
    public static final Cursor CURSOR_PICKING;

    /**
     * Cursor for picking used when hovered element is accepted.
     */
    @objid ("92bf67e4-1eae-11e2-8cad-001ec947c8cc")
    public static final Cursor CURSOR_PICKING_YES;

    /**
     * Cursor for picking used when hovered element is not accepted.
     */
    @objid ("92bf67e7-1eae-11e2-8cad-001ec947c8cc")
    public static final Cursor CURSOR_PICKING_NO;

    @objid ("92bf67ea-1eae-11e2-8cad-001ec947c8cc")
    public static final Cursor CURSOR_CLONE_GRAPHIC_OPTIONS;

    @objid ("92bf67ec-1eae-11e2-8cad-001ec947c8cc")
    public static final Cursor CURSOR_CLONE_ALL_OPTIONS;

    /**
     * Cursor used when diagram is in pan/grab mode.
     */
    @objid ("92bf67ee-1eae-11e2-8cad-001ec947c8cc")
    public static final Cursor CURSOR_GRAB_HAND;

    /**
     * Cursor to be used to indicate that a click will terminate a link creation operation by a user choice popup menu.
     * The menu will display several options that define the exact link creation conditions.
     */
    @objid ("0981f332-1b1a-418e-ae1b-7a6004a7aa7b")
    public static final Cursor CURSOR_LINK_END_MENU;

    /**
     * Cursor to be used while creating a link to indicate that a click will create a bendpoint in the link.
     */
    @objid ("f8fed455-d7ad-4f9e-bc0e-e64baa04b65e")
    public static final Cursor CURSOR_LINK_BENDPOINT;

    @objid ("92bf67f1-1eae-11e2-8cad-001ec947c8cc")
    private static Cursor createCursor(String sourceName, String maskName, int hotX, int hotY) {
        Bundle bundle = org.eclipse.core.runtime.Platform.getBundle("org.modelio.ui");
        URL urlSrc = FileLocator.find(bundle, new Path(sourceName), null);
        ImageDescriptor src = ImageDescriptor.createFromURL(urlSrc);
        ImageDescriptor mask = null;
        if (maskName != null) {
            URL urlMask = FileLocator.find(bundle, new Path(maskName), null);
            mask = ImageDescriptor.createFromURL(urlMask);
        }
        return new Cursor(null, src.getImageData(), (mask != null) ? mask.getImageData() : null, hotX, hotY);
    }

    @objid ("92bf67f9-1eae-11e2-8cad-001ec947c8cc")
    private static Cursor createCursor(String cursorFilename, int hotX, int hotY) {
        Bundle imageBundle = Platform.getBundle("org.modelio.ui");
        URL bitmapUrl = FileLocator.find(imageBundle, new Path(cursorFilename), null);
        ImageData bitmapData = ImageDescriptor.createFromURL(bitmapUrl).getImageData();
        return new Cursor(Display.getCurrent(), bitmapData, hotX, hotY);
    }


static {

        CURSOR_REPARENT = createCursor("cursors/reparent.bmp", //$NON-NLS-1$
                                       "cursors/reparent_mask.bmp", 1, 11); //$NON-NLS-1$
        CURSOR_REPARENT_NOT = createCursor("cursors/reparent_not.bmp", //$NON-NLS-1$
                                           "cursors/reparent_not_mask.bmp", 1, 11); //$NON-NLS-1$

        CURSOR_PICKING = createCursor("cursors/picking_cursor32x32.png", 1, 11); //$NON-NLS-1$
        CURSOR_PICKING_YES = createCursor("cursors/picking_cursor_yes32x32.png", 1, 11); //$NON-NLS-1$
        CURSOR_PICKING_NO = createCursor("cursors/picking_cursor_no32x32.png", 1, 11); //$NON-NLS-1$
        CURSOR_CLONE_GRAPHIC_OPTIONS = createCursor("cursors/clone_graphic_options19x19.png", 1, 1); //$NON-NLS-1$
        CURSOR_CLONE_ALL_OPTIONS = createCursor("cursors/clone_all_options19x19.png", 1, 1); //$NON-NLS-1$
        CURSOR_GRAB_HAND = createCursor("cursors/grab_hand22x23.png",1, 11); //$NON-NLS-1$
        
        CURSOR_LINK_END_MENU       = createCursor("cursors/link_end_menu32x32.png",1, 1); //$NON-NLS-1$
        CURSOR_LINK_BENDPOINT      = createCursor("cursors/link_bendpoint32x32.png",1, 1);//$NON-NLS-1$
        
    }
}
