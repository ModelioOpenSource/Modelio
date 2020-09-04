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

package org.modelio.diagram.editor.activity.elements.partitioncontainer;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editpolicies.SnapFeedbackPolicy;
import org.modelio.diagram.elements.common.abstractdiagram.SnapEditPartAdapter;
import org.modelio.diagram.elements.core.figures.GradientFigure;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.metamodel.uml.infrastructure.Element;

/**
 * Base class for edit part of {@link GmPartitionContainer}.
 * 
 * @author fpoyer
 */
@objid ("2b2d94f9-55b6-11e2-877f-002564c97630")
public class PartitionContainerEditPart extends AbstractNodeEditPart {
    @objid ("2b2d94fd-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        Figure fig = new GradientFigure();
        fig.setOpaque(false);
        // Define properties not specific to style.
        PartitionContainerLayout layoutManager = new PartitionContainerLayout();
        // // Prevent spacing between children.
        // layoutManager.setMinorSpacing(0);
        // // Define initial dimension
        // if (layoutManager.isHorizontal())
        // layoutManager.setCommonDimension(fig.getSize().height);
        // else
        // layoutManager.setCommonDimension(fig.getSize().width);
        
        // ToolbarLayout layoutManager = new ToolbarLayout();
        layoutManager.setSpacing(-1);
        layoutManager.setStretchMinorAxis(true);
        fig.setLayoutManager(layoutManager);
        
        // Define properties specific to style
        refreshFromStyle(fig, getModelStyle());
        return fig;
    }

    @objid ("2b2dbc0b-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new PartitionContainerLayoutEditPolicy());
        
        // Remove the default DIRECT_EDIT policy: we don't want the container to
        // delegate direct edit requests.
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, null);
        
        // Override the default DROP policy to add one that can only understand Partitions
        installEditPolicy(ModelElementDropRequest.TYPE, new PartitionDropEditPolicy());
        
        // Snap to Geometry feedback
        installEditPolicy("Snap Feedback", new SnapFeedbackPolicy()); //$NON-NLS-1$
    }

    @objid ("2b2dbc0e-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        // Update figure constraint in parent from model
        final IFigure fig = getFigure();
        final GmPartitionContainer partitionContainerModel = (GmPartitionContainer) this.getModel();
        fig.getParent().setConstraint(this.getFigure(), partitionContainerModel.getLayoutData());
        
        // On the other hand, go read the "vertical" property to update the
        // layout.
        ((ToolbarLayout) fig.getLayoutManager()).setHorizontal(!partitionContainerModel.isVertical());
    }

    @objid ("2b2de31b-55b6-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        // If child doesn't have a layout data, compute one for it.
        GmNodeModel childModel = (GmNodeModel) childEditPart.getModel();
        if (childModel.getLayoutData() == null) {
            if (GmPartitionContainer.SUB_PARTITION.equals(childModel.getRoleInComposition())) {
                // Transposer transposer = new Transposer();
                // transposer.setEnabled(!((GmPartitionContainer) getModel()).isVertical());
                // int availableSpace = transposer.t(getFigure().getBounds()).height;
                // Integer initialLayoutData = Integer.valueOf(availableSpace / (getChildren().size() + 1));
                // childModel.setLayoutData(initialLayoutData);
                childModel.setLayoutData(Integer.valueOf(-1));
                // TODO: reduce the constraint of all other children.
            }
        }
        super.addChildVisual(childEditPart, index);
    }

    /**
     * In order to provide snap to grid facility the AbstractEditPart must be adaptable to a SnapToHelper. Here we adapt to a SnapToGrid only if the style defines SNAPTOGRID = true
     */
    @objid ("2b2e0a2a-55b6-11e2-877f-002564c97630")
    @Override
    public Object getAdapter(final Class type) {
        if (type == SnapToHelper.class) {
            return new SnapEditPartAdapter(this).getSnapToHelper();
        }
        
        if (Element.class.isAssignableFrom(type)) {
            return null;
        }
        return super.getAdapter(type);
    }

}
