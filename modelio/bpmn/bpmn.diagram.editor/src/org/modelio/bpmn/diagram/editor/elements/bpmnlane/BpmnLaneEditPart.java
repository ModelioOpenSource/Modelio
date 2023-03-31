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
package org.modelio.bpmn.diagram.editor.elements.bpmnlane;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.GroupRequest;
import org.modelio.bpmn.diagram.editor.elements.diagrams.GmBpmnDiagramStyleKeys;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.commands.DeleteInDiagramCommand;
import org.modelio.diagram.elements.core.figures.MinimumSizeLayout;
import org.modelio.diagram.elements.core.figures.borders.TLBRBorder;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.policies.AutoExpandEditPolicy;
import org.modelio.diagram.elements.core.policies.DefaultDeleteNodeEditPolicy;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * EditPart for an Partition Node.
 */
@objid ("6115efaa-55b6-11e2-877f-002564c97630")
public class BpmnLaneEditPart extends AbstractNodeEditPart {
    @objid ("6115efae-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        final MObject relatedElement = ((GmBpmnLane) getModel()).getRelatedElement();
        BpmnLaneFigure fig = null;
        
        if (relatedElement instanceof BpmnLane) {
            BpmnLane lane = (BpmnLane) relatedElement;
        
            if (lane.getLaneSet() == null || lane.getLaneSet().getProcess() != null) {
                fig = new BpmnLaneFigure();
                fig.setLayoutManager(new BorderLayout());
                MinimumSizeLayout.apply(fig, 10, 10);
            } else {
                fig = new BpmnSubLaneFigure();
                fig.setLayoutManager(new BorderLayout());
                MinimumSizeLayout.apply(fig, 10, 10);
            }
        } else {
            fig = new BpmnLaneFigure();
            fig.setLayoutManager(new BorderLayout());
            MinimumSizeLayout.apply(fig, 10, 10);
        }
        
        // set style independent properties
        fig.setOpaque(true);
        
        // set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        
        // return the figure
        return fig;
    }

    @objid ("6115efb3-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        // Add auto expand edit policy.
        installEditPolicy(AutoExpandEditPolicy.class, new AutoExpandEditPolicy());
        
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START, new LinkedNodeStartCreationEditPolicy());
        
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new DefaultDeleteNodeEditPolicy() {
            @Override
            protected Command getDeleteCommand(GroupRequest request) {
                // Same as super, except we don't ask for selectability.
                DeleteInDiagramCommand ret = new DeleteInDiagramCommand();
                ret.setNodetoDelete((GmModel) getHost().getModel());
                return ret;
            }
        });
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
        
        // Needed to allow unmasking notes
        installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy(false));
    }

    /**
     * Refresh this EditPart's visuals.
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @objid ("6115efb6-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        GmBpmnLane partitionModel = (GmBpmnLane) getModel();
        getFigure().getParent().setConstraint(getFigure(), partitionModel.getLayoutData());
        
    }

    @objid ("6115efba-55b6-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
        // Positional reading: see GmLane constructor for details of indices.
        if (index == 0) {
            if (isHorizontalLaneOrientation()) {
                // Horizontal lanes have their header on the left
                child.setBorder(new TLBRBorder(false, false, false, true));
                getContentPane().add(child, BorderLayout.LEFT);
            } else {
                // Horizontal lanes have their header at the top
                child.setBorder(new TLBRBorder(false, false, true, false));
                getContentPane().add(child, BorderLayout.TOP);
            }
        
            // refresh style for the new border
            refreshFromStyle(child, getModelStyle());
        } else if (index == 1) {
            // body: free zone: go in center and take all available space.
            getContentPane().add(child, BorderLayout.CENTER);
        } else {
            throw new IllegalArgumentException("Unexpected child");
        }
        
    }

    @objid ("6115efbf-55b6-11e2-877f-002564c97630")
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

    @objid ("6115efc6-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return true;
    }

    /**
     * @return whether lanes should be displayed horizontally or vertically.
     */
    @objid ("e871f7c7-63c4-4a0c-921c-ae2d0d395f4a")
    private boolean isHorizontalLaneOrientation() {
        return getModel().getDisplayedStyle().getProperty(GmBpmnDiagramStyleKeys.HORIZONTAL_LANES);
    }

}
