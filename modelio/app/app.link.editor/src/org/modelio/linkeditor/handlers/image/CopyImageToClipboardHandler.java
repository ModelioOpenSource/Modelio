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
/**
 * 
 */
package org.modelio.linkeditor.handlers.image;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.modelio.linkeditor.panel.ILinkEditor;
import org.modelio.linkeditor.view.ILinkEditorView;
import org.modelio.platform.ui.swt.PngTransfer;

/**
 * Handler that will copy the content of the Link Editor view into the clipboard so that it may be pasted into other applications.
 */
@objid ("1b4515ac-5e33-11e2-b81d-002564c97630")
public class CopyImageToClipboardHandler {
    @objid ("1b4515ae-5e33-11e2-b81d-002564c97630")
    @Execute
    public Object execute(@Named(IServiceConstants.ACTIVE_PART) final MPart part) {
        if (!(part.getObject() instanceof ILinkEditorView)) {
            return null;
        }
        
        ILinkEditor editor = ((ILinkEditorView) part.getObject()).getLinkEditor();
        
        Image img = editor.getImage();
        if (img != null) {
            Clipboard clipboard = new Clipboard(Display.getCurrent());
            // mantis 13256 [LINUX] "Copy as graphic" doesn't work in Link editor view and in diagram view
            Transfer transfer = SWT.getPlatform().equals("gtk") ? PngTransfer.getInstance() : ImageTransfer.getInstance();
            clipboard.setContents(new Object[] { img.getImageData() }, new Transfer[] { transfer });
            img.dispose();
        }
        return null;
    }

}
