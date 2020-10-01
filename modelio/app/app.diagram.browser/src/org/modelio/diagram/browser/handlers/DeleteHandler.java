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
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.diagram.browser.model.core.DiagramRef;
import org.modelio.diagram.browser.view.DiagramBrowserView;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("001a35a6-0d4f-10c6-842f-001ec947cd2a")
public class DeleteHandler extends AbstractBrwModelHandler {
    /**
     * Selected object are either:
     * <ul>
     * <li>ModelElementAdapter for diagram and diagram set
     * <li>BrowserDiagramAdapter for diagram reference
     * </ul>
     * 
     * Deleting a diagram or a diagram set is straight forward => treat the delete at the model level. Diagram reference
     * a a little bit more tricky. If the reference has a non-null getReferenceOwner() it actually belongs to a diagram
     * set => the delete can be done.
     */
    @objid ("001a3e02-0d4f-10c6-842f-001ec947cd2a")
    @Override
    protected void doExecute(DiagramBrowserView browserView, List<Object> selectedObjects, ICoreSession session) {
        // DiagramRef are treated for deletion by only removing the reference
        for (Object obj : selectedObjects) {
            if (obj instanceof DiagramRef) {
                DiagramRef ref = (DiagramRef) obj;
                if (ref.getReferenceOwner() != null) {
                    ref.getReferenceOwner().getReferencedDiagram().remove(ref.getReferencedDiagram());
                }
            } else if (obj instanceof MObject) {
                ((MObject) obj).delete();
            }
        }
    }

    /**
     * Rule for enabling a multiple selection => all selected elements are modifiable
     * 
     * @param selection the eclipse selection
     * @return if the selection may be deleted.
     */
    @objid ("001a886c-0d4f-10c6-842f-001ec947cd2a")
    @CanExecute
    public boolean isEnabled(@Named(IServiceConstants.ACTIVE_SELECTION) IStructuredSelection selection) {
        List<Object> selectedObjects = getSelected(selection);
        
        if (selectedObjects.isEmpty()) {
            return false;
        }
        
        for (Object obj : selectedObjects) {
            boolean valid = canDelete(obj);
            
            if (!valid) {
                return false;
            }
        }
        return true;
    }

    @objid ("5c3e66d0-31f7-4b01-8638-44457d3ce106")
    private boolean canDelete(Object obj) {
        if (obj instanceof DiagramRef) {
            // diagram ref that has no reference diagram set owner cannot be deleted
            return ((DiagramRef) obj).getReferenceOwner() != null;
        } else if (obj instanceof DiagramSet) {
            DiagramSet set = (DiagramSet) obj;
            DiagramSet parent = set.getParent();
            if (parent == null) {
                // Root DiagramSet can't be deleted
                return false;
            } else {
                // Standard deletion rules
                return MTools.getAuthTool().canRemoveFrom(set, parent);
            }
        } else if (obj instanceof MObject) {
            // Standard deletion rules
            MObject element = (MObject) obj;
            return MTools.getAuthTool().canRemoveFrom(element, element.getCompositionOwner());
        } else {
            return false;
        }
    }

}
