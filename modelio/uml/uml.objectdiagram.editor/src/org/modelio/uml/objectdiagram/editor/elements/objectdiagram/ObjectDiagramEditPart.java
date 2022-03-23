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
package org.modelio.uml.objectdiagram.editor.elements.objectdiagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.common.abstractdiagram.DiagramElementDropEditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeFinishCreationEditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.policies.CreateLinkIntermediateEditPolicy;
import org.modelio.diagram.elements.core.requests.CreateLinkConstants;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.uml.statikdiagram.editor.elements.naryconnector.NConnectorFinalizationEditPolicy;
import org.modelio.uml.statikdiagram.editor.elements.narylink.NLinkFinalizationEditPolicy;

/**
 * EditPart (== controller in the GEF model) for Object diagram background.
 */
@objid ("9d64c77e-55b6-11e2-877f-002564c97630")
public class ObjectDiagramEditPart extends AbstractDiagramEditPart {
    /**
     * Creates the Figure to be used as this part's visuals
     * @see AbstractDiagramEditPart#createFigure()
     */
    @objid ("9d64c782-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        Figure diagramFigure = new ObjectDiagramFigure();
        IStyle style = ((GmAbstractObject) getModel()).getDisplayedStyle();
        
        // Set style independent properties
        
        // Set style dependent properties
        refreshFromStyle(diagramFigure, style);
        return diagramFigure;
    }

    /**
     * @see AbstractDiagramEditPart#createEditPolicies()
     */
    @objid ("9d64c788-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        // Policy to add nodes on the diagram
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new ObjectDiagramEditLayoutPolicy());
        
        // Policy to create notes
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_END,
                new LinkedNodeFinishCreationEditPolicy());
        
        // Policy to add bend points to connections being created
        installEditPolicy(CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT,
                new CreateLinkIntermediateEditPolicy());
        
        // Remove the default DIRECT_EDIT policy: we don't want the diagram
        // background to delegate direct edit requests.
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, null);
        
        // Override the default drop policy with a specific one that handles instances differently.
        installEditPolicy(ModelElementDropRequest.TYPE, new DiagramElementDropEditPolicy());
        
        // Allow creation of n-ary associations, links, connectors
        installEditPolicy("nary-link", new NLinkFinalizationEditPolicy());
        installEditPolicy("nary-connector", new NConnectorFinalizationEditPolicy());
        
    }

}
