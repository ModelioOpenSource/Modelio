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

package org.modelio.diagram.browser.handlers;

import java.util.List;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.diagram.browser.model.core.DiagramRef;
import org.modelio.diagram.browser.view.DiagramBrowserView;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("001ad1c8-0d4f-10c6-842f-001ec947cd2a")
public class RenameHandler extends AbstractBrwModelHandler {
    @objid ("001adaba-0d4f-10c6-842f-001ec947cd2a")
    @Override
    protected void doExecute(DiagramBrowserView browserView, List<Object> selectedObjects, ICoreSession session) {
        if (browserView != null && !selectedObjects.isEmpty()) {
            Object firstObject = selectedObjects.get(0);
        
            if (firstObject instanceof IAdaptable) {
                IAdaptable adapter = (IAdaptable)firstObject;
                Object elementToEdit = null;
        
                // try a diagram ref
                DiagramRef diagramRef = adapter.getAdapter(DiagramRef.class);
                if (diagramRef != null) {
                    elementToEdit = diagramRef;
                } else {
                   elementToEdit = adapter.getAdapter(Element.class);
                }
                browserView.getComposite().getPanel().editElement(elementToEdit, 0);
            } else
                browserView.getComposite().getPanel().editElement(firstObject, 0);
        }
    }

    @objid ("001b194e-0d4f-10c6-842f-001ec947cd2a")
    @CanExecute
    public boolean isEnabled(@Named(IServiceConstants.ACTIVE_SELECTION) IStructuredSelection selection) {
        List<Object> selectedObjects = this.getSelected(selection);
        
        // No rename when selecting several or no elements
        if (selectedObjects.size() != 1) {
            return false;
        }
        
        // The element must be modifiable
        if (selectedObjects.get(0) instanceof MObject) {
            MObject e = (MObject) selectedObjects.get(0);
            return e.getStatus().isModifiable();
        }
        // Should be able to rename a DiagramRef
        if (selectedObjects.get(0) instanceof DiagramRef) {
            return true;
        }
        return false;
    }

    @objid ("ca3793eb-4b58-11e2-a4d3-002564c97630")
    public RenameHandler() {
        super();
    }

}
