/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.sequence.elements.lifeline.body;

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
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editpolicies.SnapFeedbackPolicy;
import org.eclipse.gef.requests.DropRequest;
import org.modelio.diagram.editor.sequence.elements.GmMessageEndAnchor;
import org.modelio.diagram.editor.sequence.elements.MessageEndAnchor;
import org.modelio.diagram.editor.sequence.elements.lifeline.CreateLinkEditPolicy;
import org.modelio.diagram.editor.sequence.elements.sequencediagram.IPlacementConstraintProvider;
import org.modelio.diagram.elements.common.abstractdiagram.SnapEditPartAdapter;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;

/**
 * EditPart for the body zone of the lifeline (== dashed line). This class is in charge of the usual (EditPolicies, etc) but has the additional value of returning anchors that are centred on the lifeline.
 * 
 * @author fpoyer
 */
@objid ("d92e42b0-55b6-11e2-877f-002564c97630")
public class LifelineBodyEditPart extends AbstractNodeEditPart {
    @objid ("d92e42b4-55b6-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(final Request req) {
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

    @objid ("d92e42bb-55b6-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final Request req) {
        DropRequest r = (DropRequest) req;
        Point fakePoint = Point.SINGLETON;
        fakePoint.y = r.getLocation().y;
        this.getFigure().translateToRelative(fakePoint);
        return new MessageEndAnchor(this.getFigure(), fakePoint.y);
    }

    @objid ("d92e42c2-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("d92fc91b-55b6-11e2-877f-002564c97630")
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

    @objid ("d92fc922-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.NODE_ROLE, new CreateLinkEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new LifelineBodyLayoutEditPolicy());
        
        // Snap to Geometry feedback
        installEditPolicy("Snap Feedback", new SnapFeedbackPolicy()); //$NON-NLS-1$
    }

    @objid ("d92fc925-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        LifelineBodyFigure fig = new LifelineBodyFigure();
        // set style independent properties
        fig.setOpaque(true);
        fig.setLayoutManager(new LifelineBodyLayout());
        // set style dependent properties
        refreshFromStyle(fig, ((GmAbstractObject) getModel()).getDisplayedStyle());
        return fig;
    }

    @objid ("d92fc92a-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        GmAbstractObject model = getModel();
        getFigure().getParent().setConstraint(getFigure(), model.getLayoutData());
    }

    @objid ("d92fc92d-55b6-11e2-877f-002564c97630")
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

    @objid ("d92fc933-55b6-11e2-877f-002564c97630")
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

    @objid ("d92fc939-55b6-11e2-877f-002564c97630")
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

    /**
     * In order to provide snap to grid facility the AbstractEditPart must be adaptable to a SnapToHelper. Here we adapt to a SnapToGrid only if the style defines SNAPTOGRID = true
     */
    @objid ("d92fc93f-55b6-11e2-877f-002564c97630")
    @Override
    public Object getAdapter(final Class type) {
        if (type == SnapToHelper.class) {
            return new SnapEditPartAdapter(this).getSnapToHelper();
        }
        return super.getAdapter(type);
    }

}
