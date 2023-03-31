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
package org.modelio.diagram.elements.common.resizablegroup;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.OrderedLayoutEditPolicy;
import org.modelio.diagram.elements.core.figures.GradientFigure;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.LayoutConnectionsOrderedLayoutEditPolicyDecorator;

/**
 * Base class for edit part of {@link GmResizableGroup}.
 * 
 * @author fpoyer
 */
@objid ("7f0c2da4-1dec-11e2-8cad-001ec947c8cc")
public class ResizableGroupEditPart extends AbstractNodeEditPart {
    /**
     * This element is not meant to be an independent select-able node, but rather a child of a main node.
     */
    @objid ("7f0e8fed-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("7f0e8ff3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        // If child doesn't have a layout data, compute one for it.
        GmNodeModel childModel = (GmNodeModel) childEditPart.getModel();
        if (childModel.getLayoutData() == null) {
            // TODO: reduce the constraint of all other children.
            childModel.setLayoutData(Integer.valueOf(-1));
        }
        super.addChildVisual(childEditPart, index);
        
    }

    @objid ("7f0e8fe7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new ResizableGroupLayoutEditPolicy());
        
        // Remove the default DIRECT_EDIT policy: we don't want the container to
        // delegate direct edit requests.
        removeEditPolicy(EditPolicy.DIRECT_EDIT_ROLE);
        
    }

    @objid ("7f0c2da6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IFigure createFigure() {
        Figure fig = new GradientFigure();
        fig.setOpaque(false);
        // Define properties not specific to style.
        ResizableGroupLayout layoutManager = new ResizableGroupLayout();
        layoutManager.setStretchMinorAxis(true);
        fig.setLayoutManager(layoutManager);
        
        // Define properties specific to style
        refreshFromStyle(fig, getModelStyle());
        return fig;
    }

    @objid ("b8c62880-5685-47a9-94f7-ea1e5c3b0556")
    @Override
    protected EditPolicy createLayoutPolicyDecorator(EditPolicy layoutPolicy) {
        return new LayoutConnectionsOrderedLayoutEditPolicyDecorator((OrderedLayoutEditPolicy) layoutPolicy);
    }

    @objid ("7f0e8fea-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshVisuals() {
        // Update figure constraint in parent from model
        final IFigure fig = getFigure();
        final GmResizableGroup groupModel = (GmResizableGroup) getModel();
        if (groupModel.getLayoutData() != null) {
            fig.getParent().setConstraint(getFigure(), groupModel.getLayoutData());
        }
        
        // On the other hand, go read the "vertical" property to update the
        // layout.
        ((ToolbarLayout) fig.getLayoutManager()).setHorizontal(!groupModel.isVertical());
        
    }

}
