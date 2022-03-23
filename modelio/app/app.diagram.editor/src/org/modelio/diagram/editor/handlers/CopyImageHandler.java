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
package org.modelio.diagram.editor.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.modelio.diagram.editor.AbstractDiagramEditor;
import org.modelio.diagram.elements.common.abstractdiagram.ImageBuilder;
import org.modelio.platform.ui.swt.PngTransfer;

@objid ("65afb5e5-33f7-11e2-95fe-001ec947c8cc")
public class CopyImageHandler {
    @objid ("ba8aa06e-645a-48aa-be7b-80395fee921b")
    @Execute
    public Object execute(@Named(IServiceConstants.ACTIVE_PART) final MPart part) {
        if (! (part.getObject() instanceof AbstractDiagramEditor)) {
            return null;
        }
        
        AbstractDiagramEditor editor = (AbstractDiagramEditor) part.getObject();
        
        ImageBuilder imageBuilder = new ImageBuilder();
        Image img = imageBuilder.makeImage(editor.getRootEditPart());
        
        Clipboard clipboard = new Clipboard(Display.getCurrent());
        if (img != null) {
            Transfer transfer = SWT.getPlatform().equals("gtk") ? PngTransfer.getInstance() : ImageTransfer.getInstance();
            
            clipboard.setContents(new Object[] { img.getImageData() }, new Transfer[] { transfer });
            img.dispose();
        }
        return null;
    }

    @objid ("510c0e01-a697-4a13-8507-f7dffef93e34")
    @CanExecute
    public boolean canExecute() {
        // Nothing to do
        return true;
    }

}
