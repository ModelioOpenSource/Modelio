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
package org.modelio.uml.sequencediagram.editor.elements.executionoccurencespecification;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.DropRequest;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.IBrushOptionsSupport;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.FillMode;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageSort;
import org.modelio.uml.sequencediagram.editor.elements.GmMessageEndAnchor;
import org.modelio.uml.sequencediagram.editor.elements.MessageEndAnchor;
import org.modelio.uml.sequencediagram.editor.elements.common.node.AbstractSequenceNodeEditPart;
import org.modelio.uml.sequencediagram.editor.elements.message.GmMessage;
import org.modelio.uml.sequencediagram.editor.elements.message.MessageEditPart;
import org.modelio.uml.sequencediagram.editor.elements.sequencediagram.GmSequenceDiagram;
import org.modelio.uml.sequencediagram.editor.elements.sequencediagram.IPlacementConstraintProvider;
import org.modelio.uml.sequencediagram.editor.elements.sequencediagram.PlacementConstraint;

/**
 * Edit part for the GmExecutionOccurenceSpecification class.
 * 
 * @author fpoyer
 */
@objid ("d8d74900-55b6-11e2-877f-002564c97630")
public class ExecutionOccurenceSpecificationEditPart extends AbstractSequenceNodeEditPart implements IPlacementConstraintProvider {
    @objid ("d8d74904-55b6-11e2-877f-002564c97630")
    private MasterExecutionOccurrenceSpecificationAnchor masterAnchor;

    @objid ("d8d74905-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        ExecutionOccurenceSpecificationFigure fig = new ExecutionOccurenceSpecificationFigure();
        // Style independent properties
        fig.setOpaque(true);
        fig.setPreferredSize(11, 11);
        // Style dependent properties.
        refreshFromStyle(fig, getModelStyle());
        this.masterAnchor = new MasterExecutionOccurrenceSpecificationAnchor(fig);
        return fig;
    }

    @objid ("d8d7490a-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        // No direct edition.
        removeEditPolicy(EditPolicy.DIRECT_EDIT_ROLE);
        installEditPolicy(EditPolicy.NODE_ROLE, new CreateLinkEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
        installEditPolicy("hover", new EosSelectionEditPolicy());
        
    }

    @objid ("d8d7490d-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return true;
    }

    /**
     * Creates and returns a PlacementConstraint for the given model.
     * @param model the graphic model for which a constraint is to be created.
     * @param x the desired X coordinate in coordinates relative to the parent figure.
     * @param y the desired Y coordinates relative to the parent figure.
     * @param width the desired width of the figure.
     * @param height the desired height of the figure.
     * @return a new PlacementConstraint.
     */
    @objid ("d8d74912-55b6-11e2-877f-002564c97630")
    @Override
    public PlacementConstraint createPlacementConstraint(final GmModel model, final int x, final int y, final int width, final int height) {
        return new ExecutionOccurenceSpecificationPlacementConstraint((ExecutionOccurenceSpecification) model.getRelatedElement(),
                                x,
                                y,
                                width,
                                height,
                                (GmSequenceDiagram) model.getDiagram());
        
    }

    @objid ("d8d8cf82-55b6-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(final ConnectionEditPart connection) {
        if (connection instanceof MessageEditPart) {
            return this.masterAnchor;
        }
        return new SlaveAnchor(this.masterAnchor, getFigure());
    }

    @objid ("d8d8cf89-55b6-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(final Request req) {
        Point fakePoint = Point.SINGLETON;
        if (req instanceof CreateBendedConnectionRequest) {
            CreateBendedConnectionRequest r = (CreateBendedConnectionRequest) req;
            fakePoint.y = r.getData().getSrcPoint().y;
            getFigure().translateToRelative(fakePoint);
        } else {
            DropRequest r = (DropRequest) req;
            fakePoint.y = r.getLocation().y;
            getFigure().translateToRelative(fakePoint);
        }
        return new MessageEndAnchor(getFigure(), fakePoint.y);
    }

    @objid ("d8d8cf90-55b6-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final ConnectionEditPart connection) {
        if (connection instanceof MessageEditPart) {
            // exception for the case of the creation message:
            MessageEditPart messageEditPart = (MessageEditPart) connection;
            if (((GmMessage) messageEditPart.getModel()).getRelatedElement().getSortOfMessage() == MessageSort.CREATEMESSAGE) {
                EditPart lifelineBody = getParent();
                EditPart lifeline = lifelineBody.getParent();
                for (Object childEditPart : lifeline.getChildren()) {
                    if (!(childEditPart.equals(lifelineBody))) {
                        return new ChopboxAnchor(((GraphicalEditPart) childEditPart).getFigure());
                    }
                }
            }
            return this.masterAnchor;
        }
        return new SlaveAnchor(this.masterAnchor, getFigure());
    }

    @objid ("d8d8cf97-55b6-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final Request req) {
        DropRequest r = (DropRequest) req;
        Point fakePoint = Point.SINGLETON;
        fakePoint.y = r.getLocation().y;
        getFigure().translateToRelative(fakePoint);
        return new MessageEndAnchor(getFigure(), fakePoint.y);
    }

    @objid ("d8d8cf9e-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        ExecutionOccurenceSpecificationFigure fig = (ExecutionOccurenceSpecificationFigure) getFigure();
        GmExecutionOccurenceSpecification model = (GmExecutionOccurenceSpecification) getModel();
        ExecutionOccurenceSpecification modelElement = (ExecutionOccurenceSpecification) model.getRelatedElement();
        if (modelElement != null &&
                modelElement.isValid() &&
                modelElement.getReceivedMessage() != null &&
                modelElement.getReceivedMessage().isValid() &&
                modelElement.getReceivedMessage().getSortOfMessage() == MessageSort.DESTROYMESSAGE) {
            // set the figure to draw the 'X'.
            fig.setDrawX(true);
        } else {
            fig.setDrawX(false);
        }
        fig.getParent().setConstraint(fig, model.getLayoutData());
        
    }

    /**
     * Overridden so that fill color is not null even when fill mode is transparent, because the fill color may be used when execution occurrence specification is a destruction event
     */
    @objid ("d8d8cfa1-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(final IFigure aFigure, final IStyle style) {
        final GmModel gmModel = getModel();
        
        // Set brush properties where applicable
        if (aFigure instanceof IBrushOptionsSupport) {
            final IBrushOptionsSupport brush = (IBrushOptionsSupport) aFigure;
        
            if (gmModel.getStyleKey(MetaKey.FILLCOLOR) != null) {
                brush.setFillColor(style.getColor(gmModel.getStyleKey(MetaKey.FILLCOLOR)));
            }
        
            if (gmModel.getStyleKey(MetaKey.FILLMODE) != null) {
                switch ((FillMode) style.getProperty(gmModel.getStyleKey(MetaKey.FILLMODE))) {
                case GRADIENT:
                    brush.setUseGradient(true);
                    aFigure.setOpaque(true);
                    break;
                case SOLID:
                    brush.setUseGradient(false);
                    aFigure.setOpaque(true);
                    break;
                case TRANSPARENT:
                    aFigure.setOpaque(false);
                    break;
                }
            }
        }
        
    }

    @objid ("d8d8cfab-55b6-11e2-877f-002564c97630")
    @Override
    public Object createAnchorModel(final ConnectionAnchor anchor) {
        if (anchor instanceof MessageEndAnchor) {
            Point referencePoint = ((MessageEndAnchor) anchor).getReferencePoint();
            getFigure().translateToRelative(referencePoint);
            return new GmMessageEndAnchor(referencePoint.y);
        } else {
            return super.createAnchorModel(anchor);
        }
        
    }

}
