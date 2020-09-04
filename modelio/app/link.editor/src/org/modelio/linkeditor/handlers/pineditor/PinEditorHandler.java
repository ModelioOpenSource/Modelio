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
package org.modelio.linkeditor.handlers.pineditor;

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.linkeditor.view.ILinkEditorView;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Toggles the "pinned" state of the LinkEditor.
 */
@objid ("1b535ddd-5e33-11e2-b81d-002564c97630")
public class PinEditorHandler {
    @objid ("1b535ddf-5e33-11e2-b81d-002564c97630")
    @Execute
    public Object execute(MPart part, @Named(IServiceConstants.ACTIVE_SELECTION) IStructuredSelection selection) {
        if (part.getObject() instanceof ILinkEditorView) {
            final ILinkEditorView linkEditorView = (ILinkEditorView) part.getObject();
            final boolean newEditMode = ! linkEditorView.getLinkEditor().isEditMode();
            
            linkEditorView.getLinkEditor().setEditMode( newEditMode);
            
            MObject mObj = SelectionHelper.getFirst(selection, MObject.class);
            if (mObj != null) {
                linkEditorView.getLinkEditor().setInput(mObj);
            }
        }
        return null;
    }

}
