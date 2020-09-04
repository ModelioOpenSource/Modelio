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

package org.modelio.diagram.editor.statik.elements.namespacinglink.redraw;

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.tools.AbstractConnectionCreationTool;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.diagram.editor.handlers.redrawlink.RedrawConnectionTool;
import org.modelio.diagram.editor.statik.elements.namespacinglink.CompositionLinkEditPart;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Handler for the "Redraw link" contextual command. Simply sets the current tool to a custom tool with a custom made
 * creation factory.
 * 
 * @author fpoyer
 */
@objid ("35bbcd70-55b7-11e2-877f-002564c97630")
public class RedrawLinkHandler {
    @objid ("35bbcd72-55b7-11e2-877f-002564c97630")
    @Execute
    public Object execute(@Named(IServiceConstants.ACTIVE_SELECTION) IStructuredSelection selection) {
        // First get the current selection
        AbstractConnectionEditPart selectedLink = getLinkToProcess(selection);
        
        // Now instantiate the tool.
        EditDomain editDomain = selectedLink.getViewer().getEditDomain();
        AbstractConnectionCreationTool redrawTool = null;
        if (selectedLink instanceof LinkEditPart) {
            redrawTool = new RedrawConnectionTool((LinkEditPart) selectedLink);
        } else if (selectedLink instanceof CompositionLinkEditPart) {
            redrawTool = new RedrawCompositionConnectionTool((CompositionLinkEditPart) selectedLink);
        }
        // Set the tool as the active one.
         if (redrawTool != null) {
            editDomain.setActiveTool(redrawTool);
        }
        return null;
    }

    @objid ("205c5c75-a4a6-468d-9ea4-3ec659c7e77d")
    @CanExecute
    public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) IStructuredSelection selection) {
        final AbstractConnectionEditPart selectedLink = getLinkToProcess(selection);
        // Has one link selected
        if (selectedLink == null) {
            return false;
        }
        
        // At least one link must support changing router
        if (selectedLink instanceof LinkEditPart) {            
            final GmLink link = (GmLink) selectedLink.getModel();
            
            if (! link.isUserEditable()) {
                return false;
            }
            
            // Deactivate command on Naries...            
            MObject relatedEl = link.getRelatedElement();
            if (relatedEl instanceof NaryAssociationEnd 
                    || relatedEl instanceof NaryLinkEnd 
                    || relatedEl instanceof ImpactLink
                    || relatedEl instanceof Constraint) {
                return false;
            }
            
            final StyleKey styleKey = link.getStyleKey(MetaKey.CONNECTIONROUTER);
            if (styleKey != null) {
                return true;
            }
        } else if (selectedLink instanceof CompositionLinkEditPart) {
            return true;
        }
        return false;
    }

    @objid ("0f37f134-366a-41e6-9d09-0fdc550c7017")
    protected AbstractConnectionEditPart getLinkToProcess(IStructuredSelection selection) {
        AbstractConnectionEditPart selectedLink = null;
        if (selection != null && selection.size() == 1) {
            final Object selectedObject = selection.getFirstElement();
            if (selectedObject instanceof LinkEditPart || selectedObject instanceof CompositionLinkEditPart) {
                selectedLink = (AbstractConnectionEditPart) selectedObject;
            }
        }
        return selectedLink;
    }

}
