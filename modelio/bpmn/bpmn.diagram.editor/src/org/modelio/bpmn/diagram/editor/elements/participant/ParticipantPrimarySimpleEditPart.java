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
package org.modelio.bpmn.diagram.editor.elements.participant;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnlane.BpmnLaneFigure;
import org.modelio.bpmn.diagram.editor.elements.common.policies.BpmnCreateLinkEditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.MinimumSizeLayout;
import org.modelio.diagram.elements.core.figures.borders.TLBRBorder;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;

/**
 * EditPart for {@link GmBpmnParticipantPrimaryNode} in structured mode.
 */
@objid ("a7e8e05d-b493-43ad-b9ae-2f398fa09c4c")
public class ParticipantPrimarySimpleEditPart extends AbstractNodeEditPart {
    @objid ("80c45dea-41bc-4a01-9e22-f677de09a210")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("631cb44e-b541-4173-ae09-e007a075cdfa")
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
        // Positional reading: see GmBpmnParticipantPrimarySimple constructor for details of indices.
        if (index == 0) {
            // header, on the left.
            child.setBorder(new TLBRBorder(false, false, false, true));
            getContentPane().add(child, BorderLayout.LEFT);
        
            // refresh style for the new border
            refreshFromStyle(getContentPane(), getModelStyle());
        } else if (index == 1) {
            // footer, on the right.
            getContentPane().add(child, BorderLayout.RIGHT);
        } else {
            throw new IllegalArgumentException(String.format("Unexpected '%s' child at index %d.", childEditPart, index));
        }
        
    }

    @objid ("14bc32cd-84e6-4a73-a6cc-acecb444383d")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.NODE_ROLE, new BpmnCreateLinkEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START, new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
        installEditPolicy(ModelElementDropRequest.TYPE, new BpmnParticipantElementDropEditPolicy());
        
    }

    @objid ("7e1a4abd-8c90-45ac-af6b-283cea2c3ee9")
    @Override
    protected IFigure createFigure() {
        BpmnLaneFigure fig = new BpmnLaneFigure();
        fig.setLayoutManager(new BorderLayout());
        MinimumSizeLayout.apply(fig, 700, 200);
        
        // set style independent properties
        fig.setOpaque(true);
        
        // set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        
        // return the figure
        return fig;
    }

    @objid ("9d958176-ede3-455a-9ae9-a6071f763604")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (aFigure instanceof BpmnLaneFigure) {
            if (!switchRepresentationMode()) {
                super.refreshFromStyle(aFigure, style);
        
                final GmModel gmModel = getModel();
                if (aFigure.getChildren().size() > 0) {
                    IFigure headerFigure = (IFigure) aFigure.getChildren().get(0);
                    ((TLBRBorder) headerFigure.getBorder()).setColor(style.getColor(gmModel.getStyleKey(MetaKey.LINECOLOR)));
                }
            }
        } else {
            super.refreshFromStyle(aFigure, style);
        }
        
    }

    @objid ("455aa655-3f9a-4a36-95b3-222730215ed3")
    @Override
    protected void refreshVisuals() {
        GmBpmnParticipantPrimaryNode model = (GmBpmnParticipantPrimaryNode) getModel();
        getFigure().getParent().setConstraint(getFigure(), model.getLayoutData());
        
    }

}
