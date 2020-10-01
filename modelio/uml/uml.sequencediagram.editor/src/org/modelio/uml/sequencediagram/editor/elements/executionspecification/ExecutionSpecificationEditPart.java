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

package org.modelio.uml.sequencediagram.editor.elements.executionspecification;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.DropRequest;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;
import org.modelio.uml.sequencediagram.editor.elements.GmMessageEndAnchor;
import org.modelio.uml.sequencediagram.editor.elements.MessageEndAnchor;
import org.modelio.uml.sequencediagram.editor.elements.sequencediagram.GmSequenceDiagram;
import org.modelio.uml.sequencediagram.editor.elements.sequencediagram.IPlacementConstraintProvider;
import org.modelio.uml.sequencediagram.editor.elements.sequencediagram.PlacementConstraint;

/**
 * EditPart (ie Controller in the GEF model) for ExecutionSpecification in Sequence Diagram.
 */
@objid ("d8e37e1b-55b6-11e2-877f-002564c97630")
public class ExecutionSpecificationEditPart extends AbstractNodeEditPart implements IPlacementConstraintProvider {
    /**
     * Default height to use for an execution specification in none is given by user.
     */
    @objid ("d8e37e1f-55b6-11e2-877f-002564c97630")
    public static final int DEFAULT_EXECUTION_HEIGHT = 50;

    /**
     * Visible width of an execution specification. Should always be used.
     */
    @objid ("d8e50479-55b6-11e2-877f-002564c97630")
    public static final int EXECUTION_WIDTH = 11;

    /**
     * Creates and returns a PlacementConstraint for the given model.
     * 
     * @param model the graphic model for which a constraint is to be created.
     * @param x the desired X coordinate in coordinates relative to the parent figure.
     * @param y the desired Y coordinates relative to the parent figure.
     * @param width the desired width of the figure.
     * @param height the desired height of the figure.
     * @return a new PlacementConstraint.
     */
    @objid ("d8e5047c-55b6-11e2-877f-002564c97630")
    @Override
    public PlacementConstraint createPlacementConstraint(final GmModel model, final int x, final int y, final int width, final int height) {
        return new ExecutionSpecificationPlacementConstraint((ExecutionSpecification) model.getRelatedElement(),
                                x,
                                y,
                                width,
                                height,
                                (GmSequenceDiagram) model.getDiagram());
    }

    @objid ("d8e5048e-55b6-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(Request req) {
        Point fakePoint = Point.SINGLETON;
        if (req instanceof CreateBendedConnectionRequest) {
            CreateBendedConnectionRequest r = (CreateBendedConnectionRequest) req;
            fakePoint.y = r.getData().getSrcPoint().y;
            this.getFigure().translateToRelative(fakePoint);
        } else {
            DropRequest r = (DropRequest) req;
            fakePoint.y = r.getLocation().y;
            this.getFigure().translateToRelative(fakePoint);
        }
        return new MessageEndAnchor(this.getFigure(), fakePoint.y);
    }

    @objid ("d8e50494-55b6-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(Request req) {
        DropRequest r = (DropRequest) req;
        Point fakePoint = Point.SINGLETON;
        fakePoint.y = r.getLocation().y;
        this.getFigure().translateToRelative(fakePoint);
        return new MessageEndAnchor(this.getFigure(), fakePoint.y);
    }

    @objid ("d8e5049a-55b6-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(final EditPart childEditPart, final int index) {
        GmModel model = (GmModel) childEditPart.getModel();
        Object layoutData = model.getLayoutData();
        if (layoutData == null) {
            // If no constraint defined, create a default one.
            layoutData = new Rectangle(0, 0, -1, -1);
            model.setLayoutData(layoutData);
        }
        if (layoutData instanceof Rectangle && childEditPart instanceof IPlacementConstraintProvider) {
            // On first time, fix the constraint.
            layoutData = ((IPlacementConstraintProvider) childEditPart).createPlacementConstraint(model,
                    ((Rectangle) layoutData).x,
                    ((Rectangle) layoutData).y,
                    ((Rectangle) layoutData).width,
                    ((Rectangle) layoutData).height);
            model.setLayoutData(layoutData);
        }
        IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
        getContentPane().add(child, layoutData, index);
    }

    @objid ("d8e504a1-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        // No direct edition
        removeEditPolicy(EditPolicy.DIRECT_EDIT_ROLE);
        installEditPolicy(EditPolicy.NODE_ROLE, new CreateLinkEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
    }

    @objid ("d8e504a4-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        // Create the figure
        ExecutionSpecificationFigure executionFigure = new ExecutionSpecificationFigure();
        
        // Set style independent properties
        executionFigure.setOpaque(false);
        executionFigure.setLayoutManager(new ExecutionSpecificationLayout());
        
        // Set style dependent properties
        IStyle style = ((GmAbstractObject) this.getModel()).getDisplayedStyle();
        refreshFromStyle(executionFigure, style);
        return executionFigure;
    }

    @objid ("d8e504a9-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        GmAbstractObject model = getModel();
        getFigure().getParent().setConstraint(getFigure(), model.getLayoutData());
    }

    @objid ("d8e504ac-55b6-11e2-877f-002564c97630")
    @Override
    public Object createAnchorModel(final ConnectionAnchor anchor) {
        if (anchor instanceof MessageEndAnchor) {
            Point referencePoint = ((MessageEndAnchor) anchor).getReferencePoint();
            return new GmMessageEndAnchor(referencePoint.y);
        } else {
            return super.createAnchorModel(anchor);
        }
    }

    @objid ("d8e504b2-55b6-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(final ConnectionEditPart connection) {
        final GmLink gmLink = (GmLink) connection.getModel();
        if (gmLink.getPath().getSourceAnchor() instanceof GmMessageEndAnchor) {
            return new MessageEndAnchor(getFigure(),
                    ((GmMessageEndAnchor) gmLink.getPath().getSourceAnchor()).getReference());
        } else {
            return super.getSourceConnectionAnchor(connection);
        }
    }

    @objid ("d8e504b8-55b6-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final ConnectionEditPart connection) {
        final GmLink gmLink = (GmLink) connection.getModel();
        if (gmLink.getPath().getTargetAnchor() instanceof GmMessageEndAnchor) {
            return new MessageEndAnchor(getFigure(),
                    ((GmMessageEndAnchor) gmLink.getPath().getTargetAnchor()).getReference());
        } else {
            return super.getSourceConnectionAnchor(connection);
        }
    }

}
