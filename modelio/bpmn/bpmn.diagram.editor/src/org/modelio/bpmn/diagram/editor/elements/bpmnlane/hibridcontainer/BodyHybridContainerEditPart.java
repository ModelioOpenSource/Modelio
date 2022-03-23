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
package org.modelio.bpmn.diagram.editor.elements.bpmnlane.hibridcontainer;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.modelio.bpmn.diagram.editor.elements.bpmnlanesetcontainer.BpmnLaneSetContainerEditPart;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.common.freezone.FreeZoneEditPart;
import org.modelio.diagram.elements.common.freezone.FreeZoneLayout;
import org.modelio.diagram.elements.core.policies.ProgrammaticOnlyDragPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;

/**
 * Specific edit part for the {@link GmBodyHybridContainer} model. Main specificity is to install the {@link BodyHybridContainerLayoutEditPolicy}.
 */
@objid ("612fe05d-55b6-11e2-877f-002564c97630")
public class BodyHybridContainerEditPart extends FreeZoneEditPart {
    /**
     * Margin around the lane content in {@link Behaviour#FREE_ZONE} mode.
     */
    @objid ("e7289b25-e4dd-4197-a8b4-566101832d68")
    private static final int MARGIN = 10;

    @objid ("612fe061-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        // Let the super class handle most of the job
        super.createEditPolicies();
        // Now just override the layout policy with our own.
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new BodyHybridContainerLayoutEditPolicy());
        // Now just override the drop policy with our own.
        installEditPolicy(ModelElementDropRequest.TYPE, new BodyHybridContainerDropEditPolicy());
        
        installEditPolicy("ProgrammaticOnlyDragPolicy", new ProgrammaticOnlyDragPolicy());
        
    }

    /**
     * Disable layout policy decoration : {@link BodyHybridContainerLayoutEditPolicy} does it itself.
     */
    @objid ("a725efe1-e1fb-4e1b-8424-69c196a87641")
    @Override
    protected EditPolicy createLayoutPolicyDecorator(EditPolicy layoutPolicy) {
        return layoutPolicy;
    }

    @objid ("612fe064-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshChildren() {
        // Additional step: update the current layout manager of the figure and
        // the state of the hybrid policies, based on the children in the Gm.
        EditPolicy layoutPolicy = getEditPolicy(EditPolicy.LAYOUT_ROLE);
        if (layoutPolicy instanceof BodyHybridContainerLayoutEditPolicy) {
            Behaviour newBehaviour = computeBehaviourFromModel();
            if (((BodyHybridContainerLayoutEditPolicy) layoutPolicy).getBehaviour() != newBehaviour) {
                updateBehaviour(newBehaviour);
            }
        }
        super.refreshChildren();
        
    }

    @objid ("612fe067-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    /**
     * Overridden to use the free zone by default (rather than the lane layout) to prevent some {@link NullPointerException} cases with the policies.
     */
    @objid ("612fe06c-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        IFigure fig = super.createFigure();
        // Remove the margin border.
        // Note : NEVER use MarginBorder with negative insets : it breaks at least BorderLayout and StackLayout !
        fig.setBorder(new MarginBorder(MARGIN));
        
        // Default to FreeZoneLayout to prevent some problems with policies.
        fig.setLayoutManager(new FreeZoneLayout());
        return fig;
    }

    /**
     * Updates both the LayoutManager of the Figure and the behavior state of the LayoutEditPolicy.
     * @param newBehaviour the new behavior to adopt.
     */
    @objid ("612fe072-55b6-11e2-877f-002564c97630")
    private void updateBehaviour(Behaviour newBehaviour) {
        // Update the layout manager of the figure accordingly.
        IFigure fig = getFigure();
        switch (newBehaviour) {
        case HYBRID: {
            // Default to FreeZoneLayout to prevent some problems with
            // policies.
            FreeZoneLayout layoutManager = new FreeZoneLayout();
            fig.setLayoutManager(layoutManager);
            fig.setBorder(new MarginBorder(MARGIN));
            refreshFromStyle(fig, getModelStyle());
        
            break;
        }
        case FREE_ZONE: {
            FreeZoneLayout layoutManager = new FreeZoneLayout();
            fig.setLayoutManager(layoutManager);
            fig.setBorder(new MarginBorder(MARGIN));
            refreshFromStyle(fig, getModelStyle());
            break;
        }
        case LANE_CONTAINER: {
            fig.setLayoutManager(new StackLayout());
            fig.setBorder(null);
            refreshFromStyle(fig, getModelStyle());
        
            break;
        }
        }
        refreshVisuals();
        
        // Update state of hybrid policies
        ((BodyHybridContainerLayoutEditPolicy) getEditPolicy(EditPolicy.LAYOUT_ROLE)).setBehaviour(newBehaviour);
        // ((BodyHybridContainerDropEditPolicy) this.getEditPolicy(ModelElementDropRequest.TYPE)).setBehaviour(newBehaviour);
        
    }

    /**
     * Computes the behaviour to have based on the model.
     * @return the behaviour to adopt.
     */
    @objid ("612fe076-55b6-11e2-877f-002564c97630")
    private Behaviour computeBehaviourFromModel() {
        GmBodyHybridContainer model = (GmBodyHybridContainer) getModel();
        if (model.getChildren(GmBodyHybridContainer.SUB_LANE).isEmpty()) {
            // There are no children yet or there are only inner nodes: behave like
            // hybrid (accept everything!).
            return Behaviour.HYBRID;
        } else {
            // There are already some sublanes: behave like a lane
            // container only (do not accept inner nodes anymore).
            return Behaviour.LANE_CONTAINER;
        }
        
    }

    @objid ("613166e0-55b6-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        if (childEditPart instanceof BpmnLaneSetContainerEditPart) {
            IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
            getFigure().add(child, BorderLayout.CENTER, index);
        } else {
            super.addChildVisual(childEditPart, index);
        }
        
    }

    @objid ("613166e5-55b6-11e2-877f-002564c97630")
    @Override
    public EditPart getTargetEditPart(final Request request) {
        if (RequestConstants.REQ_SELECTION.equals(request.getType())) {
            // Walk up the composition stack until we meet the diagram, then apply selection to the last met lane container.
            EditPart parentPart = null;
            EditPart parentParentPart = this;
            do {
                parentPart = parentParentPart;
                parentParentPart = parentParentPart.getParent();
            } while (parentParentPart != null && !(parentParentPart instanceof AbstractDiagramEditPart));
        
            if (parentPart != null) {
                return parentPart.getTargetEditPart(request);
            }
        }
        return super.getTargetEditPart(request);
    }

    /**
     * An enumeration describing the 3 states we can be in: either behave totally like a lane container, behave totally like a free zone, or behave like an hybrid because state is not yet defined.
     */
    @objid ("613166ec-55b6-11e2-877f-002564c97630")
    public enum Behaviour {
        /**
         * The behaviour to use is an hybrid (or more usually a combination) of both free zone and lane container behaviours.
         */
        @objid ("613166ee-55b6-11e2-877f-002564c97630")
        HYBRID,
        /**
         * The behaviour to use is the one of a "regular" free zone.
         */
        @objid ("613166f0-55b6-11e2-877f-002564c97630")
        FREE_ZONE,
        /**
         * The behaviour to use is the one of a "regular" lane container.
         */
        @objid ("ebf82db0-ffc8-4866-8dd7-000c9ba00e94")
        LANE_CONTAINER;

    }

}
