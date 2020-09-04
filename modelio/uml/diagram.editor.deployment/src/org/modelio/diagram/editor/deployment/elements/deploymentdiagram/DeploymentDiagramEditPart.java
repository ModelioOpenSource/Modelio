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

package org.modelio.diagram.editor.deployment.elements.deploymentdiagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.modelio.diagram.editor.statik.elements.naryconnector.NConnectorFinalizationEditPolicy;
import org.modelio.diagram.editor.statik.elements.narylink.NLinkFinalizationEditPolicy;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.common.abstractdiagram.DiagramEditLayoutPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeFinishCreationEditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.policies.CreateLinkIntermediateEditPolicy;
import org.modelio.diagram.elements.core.requests.CreateLinkConstants;
import org.modelio.diagram.styles.core.IStyle;

/**
 * EditPart (== controller in the GEF model) for Object diagram background.
 */
@objid ("97250c1a-55b6-11e2-877f-002564c97630")
public class DeploymentDiagramEditPart extends AbstractDiagramEditPart {
    /**
     * Creates the Figure to be used as this part's visuals
     * @see AbstractDiagramEditPart#createFigure()
     */
    @objid ("97250c1e-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        Figure diagramFigure = new DeploymentDiagramFigure();
        IStyle style = ((GmAbstractObject) this.getModel()).getDisplayedStyle();
        
        // Set style independent properties
        
        // Set style dependent properties
        refreshFromStyle(diagramFigure, style);
        return diagramFigure;
    }

    /**
     * @see AbstractDiagramEditPart#createEditPolicies()
     */
    @objid ("97250c24-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        // Policy to add nodes on the diagram
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new DiagramEditLayoutPolicy());
        
        // Policy to create notes
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_END,
                new LinkedNodeFinishCreationEditPolicy());
        
        // Policy to add bend points to connections being created
        installEditPolicy(CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT,
                new CreateLinkIntermediateEditPolicy());
        
        // Allow creation of n-ary associations, links, connectors
        
        installEditPolicy("nary-link", new NLinkFinalizationEditPolicy());
        installEditPolicy("nary-connector", new NConnectorFinalizationEditPolicy());
    }

}
