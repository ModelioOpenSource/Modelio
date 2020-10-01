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

package org.modelio.uml.activitydiagram.editor.elements.partition;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.GroupRequest;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.commands.DeleteInDiagramCommand;
import org.modelio.diagram.elements.core.figures.borders.TLBRBorder;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.policies.AutoExpandLayoutEditPolicy;
import org.modelio.diagram.elements.core.policies.DefaultDeleteNodeEditPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.uml.activitydiagram.editor.elements.partitioncontainer.GmPartitionContainer;
import org.modelio.uml.activitydiagram.editor.elements.policies.CreateFlowEditPolicy;

/**
 * EditPart for an Partition Node.
 * 
 * @author fpoyer
 */
@objid ("2b18ad69-55b6-11e2-877f-002564c97630")
public class PartitionEditPart extends AbstractNodeEditPart {
    @objid ("2b18d47b-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        // create the figure
        PartitionFigure fig = new PartitionFigure();
        fig.setLayoutManager(new BorderLayout());
        
        // set style independent properties
        fig.setOpaque(true);
        fig.setSize(100, 50); // TODO: Find a nice initial size
        
        // set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        
        // return the figure
        return fig;
    }

    @objid ("2b18fb89-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.NODE_ROLE, new CreateFlowEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                new LinkedNodeStartCreationEditPolicy());
        
        installEditPolicy(AutoExpandLayoutEditPolicy.class, new AutoExpandLayoutEditPolicy());
        
        // Delegates "standard" requests like CREATE, ADD, CLONE and MOVE to the
        // correct composite child with the notable exception of request of
        // creation of a sibling partition being delegated to the containing
        // partition container instead.
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new PartitionDelegatingEditPolicy());
        
        // Override the default DROP policy
        installEditPolicy(ModelElementDropRequest.TYPE, new DropDelegatingEditPolicy());
        
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
    }

    /**
     * Refresh this EditPart's visuals.
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @objid ("2b18fb8c-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        GmPartition partitionModel = (GmPartition) getModel();
        getFigure().getParent().setConstraint(getFigure(), partitionModel.getLayoutData());
    }

    @objid ("2b19229a-55b6-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
        // Positional reading: see GmPartition constructor for details of
        // indices.
        if (index == 0) {
            // header
            if (((GmPartitionContainer) getParent().getModel()).isVertical()) {
                child.setBorder(new TLBRBorder(false, false, false, true));
                getContentPane().add(child, BorderLayout.LEFT);
            } else {
                child.setBorder(new TLBRBorder(false, false, true, false));
                getContentPane().add(child, BorderLayout.TOP);
            }
        
            // refresh style for the new border
            refreshFromStyle(getContentPane(), getModelStyle());
        } else if (index == 1) {
            // body: free zone: go in center and take all available space.
            getContentPane().add(child, BorderLayout.CENTER);
        } else {
            throw new IllegalArgumentException("Unexpected child");
        }
    }

    @objid ("2b1949a9-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (aFigure instanceof PartitionFigure) {
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

}
