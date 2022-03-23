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
package org.modelio.bpmn.diagram.editor.elements.bpmnparallelgateway;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.modelio.bpmn.diagram.editor.elements.common.editpart.AbstractBpmnSmallNodeEditPart;
import org.modelio.bpmn.diagram.editor.elements.common.policies.BpmnCreateLinkEditPolicy;
import org.modelio.bpmn.diagram.editor.elements.common.policies.KeepNodeRatioResizableEditPolicy;
import org.modelio.bpmn.diagram.editor.plugin.DiagramEditorBpmn;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.ColorizableImageFigure;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.metamodel.bpmn.gateways.BpmnParallelGateway;

/**
 * EditPart for an {@link GmBpmnParallelGatewayPrimaryNode}.
 */
@objid ("6180bf5a-55b6-11e2-877f-002564c97630")
public final class BpmnParallelGatewayEditPart extends AbstractBpmnSmallNodeEditPart {
    @objid ("6180bf5e-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("6183cc9a-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.NODE_ROLE, new BpmnCreateLinkEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START, new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
        
    }

    @objid ("6183cc9d-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        Image image = DiagramEditorBpmn.getImageRegistry().getImage(BpmnParallelGateway.MNAME);
        ColorizableImageFigure imageFigure = new ColorizableImageFigure(image);
        imageFigure.setPreferredSize(40, 40);
        imageFigure.setMinimumSize(new Dimension(40, 40));
        
        // set style dependent properties
        refreshFromStyle(imageFigure, getModelStyle());
        
        // return the figure
        return imageFigure;
    }

    @objid ("6183cca2-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (!switchRepresentationMode()) {
            super.refreshFromStyle(aFigure, style);
        
            if (aFigure instanceof ColorizableImageFigure) {
                ColorizableImageFigure cFigure = (ColorizableImageFigure) aFigure;
                final GmModel gmModel = getModel();
                Color color = style.getColor(gmModel.getStyleKey(MetaKey.FILLCOLOR));
                cFigure.setColor(color);
            }
        }
        
    }

    @objid ("6183cca9-55b6-11e2-877f-002564c97630")
    @Override
    public SelectionEditPolicy getPreferredDragRolePolicy(final String requestType) {
        return new KeepNodeRatioResizableEditPolicy();
    }

    @objid ("6183ccb0-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        GmBpmnParallelGatewayPrimaryNode initialNodeModel = (GmBpmnParallelGatewayPrimaryNode) this.getModel();
        getFigure().getParent().setConstraint(getFigure(), initialNodeModel.getLayoutData());
        
    }

}
