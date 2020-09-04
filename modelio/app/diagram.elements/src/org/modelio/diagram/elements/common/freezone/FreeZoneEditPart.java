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

package org.modelio.diagram.elements.common.freezone;

import java.beans.PropertyChangeEvent;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editpolicies.SnapFeedbackPolicy;
import org.eclipse.gef.requests.DropRequest;
import org.modelio.diagram.elements.common.abstractdiagram.SnapEditPartAdapter;
import org.modelio.diagram.elements.core.figures.anchors.PointAnchor;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.styles.core.IStyle;

/**
 * {@link GmFreeZone} edit part.
 */
@objid ("7e3f2368-1dec-11e2-8cad-001ec947c8cc")
public class FreeZoneEditPart extends AbstractNodeEditPart {
    /**
     * A temporary point to avoid allocations of hundred of Points.
     * Used only by {@link #createAnchor(Point)}
     */
    @objid ("04c3cdff-56d6-4859-bd47-ba56f3c77959")
    private static final Point tmpAnchor = new Point();

    /**
     * c'tor.
     */
    @objid ("7e3f236a-1dec-11e2-8cad-001ec947c8cc")
    public FreeZoneEditPart() {
        super();
    }

    @objid ("7e3f236d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // If the visibility of the zone changes, it will be notified by a
        // PROPERTY_LAYOUTDATA event.
        if (evt.getPropertyName() == IGmObject.PROPERTY_LAYOUTDATA) {
            updateVisibility(getFigure());
        }
        
        super.propertyChange(evt);
    }

    @objid ("7e3f2371-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        // Update visibility
        IFigure freeZoneFig = getFigure();
        
        updateVisibility(freeZoneFig);
        
        // Call super
        super.addChildVisual(childEditPart, index);
    }

    @objid ("7e3f2378-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new DefaultFreeZoneLayoutEditPolicy());
        
        installEditPolicy(TranslateChildrenOnResizeEditPolicy.class, new TranslateChildrenOnResizeEditPolicy());
        
        // Snap to Geometry feedback
        installEditPolicy("Snap Feedback", new SnapFeedbackPolicy()); //$NON-NLS-1$
    }

    @objid ("7e3f237b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IFigure createFigure() {
        Figure groupFigure = new Figure();
        
        // Style independent properties
        groupFigure.setOpaque(false);
        groupFigure.setBackgroundColor(null);
        groupFigure.setBorder(new MarginBorder(3, 2, 3, 2));
        final FreeZoneLayout layout = new FreeZoneLayout();
        groupFigure.setLayoutManager(layout);
        
        /*
        // debug free zone position
        FreeZoneEditPart thisEp = this;
        groupFigure.addFigureListener(new FigureListener() {
            private Rectangle oldBounds;
            @Override
            public void figureMoved(IFigure source) {
                Rectangle newBounds = source.getBounds();
                if (this.oldBounds == null ) {
                    this.oldBounds = newBounds.getCopy();
                } else if (! this.oldBounds.equals(newBounds)) {
                    if (this.oldBounds.x() > newBounds.x()) {
                        DiagramElements.LOG.debug("   FreeZoneEditPart.FigureListener: %s LEFT moved from %s to %s", thisEp, this.oldBounds, newBounds);
                    } else {
                        DiagramElements.LOG.debug("   FreeZoneEditPart.FigureListener: %s moved from %s to %s", thisEp, this.oldBounds, newBounds);
                    }
                    this.oldBounds.setBounds(newBounds);
                }
            }
        });*/
        
        // Set style dependent properties
        refreshFromStyle(groupFigure, getModelStyle());
        return groupFigure;
    }

    /**
     * Get the GmFreeZone model.
     * 
     * @return the GmFreeZone.
     */
    @objid ("7e3f2382-1dec-11e2-8cad-001ec947c8cc")
    protected GmFreeZone getZoneModel() {
        return (GmFreeZone) getModel();
    }

    @objid ("7e3f2387-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshFromStyle(IFigure fig, IStyle style) {
        super.refreshFromStyle(fig, style);
        updateVisibility(fig);
    }

    @objid ("7e3f238e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void removeChildVisual(EditPart childEditPart) {
        // call super
        super.removeChildVisual(childEditPart);
        
        // update visibility
        updateVisibility(getFigure());
    }

    /**
     * Updates the visibility of the zone's figure.
     * 
     * @param aFigure the zone's figure.
     */
    @objid ("7e3f2394-1dec-11e2-8cad-001ec947c8cc")
    private void updateVisibility(IFigure aFigure) {
        final GmFreeZone gmZone = (GmFreeZone) getModel();
        final boolean oldVisible = (aFigure.isVisible());
        final boolean newVisible = gmZone.isVisible();
        
        if (oldVisible == newVisible) {
            return;
        }
        
        aFigure.setVisible(newVisible);
    }

    @objid ("7e3f239a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean isSelectable() {
        return false;
    }

    /**
     * In order to provide snap to grid facility the AbstractEditPart must be adaptable to a SnapToHelper. Here we adapt
     * to a SnapToGrid only if the style defines SNAPTOGRID = true
     */
    @objid ("7e4185aa-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Object getAdapter(final Class type) {
        if (type == SnapToHelper.class) {
            return new SnapEditPartAdapter(this).getSnapToHelper();
        }
        return super.getAdapter(type);
    }

    /**
     * Create a {@link PointAnchor} anchor relative to free zone coordinates.
     * 
     * @param absPoint an absolute point.
     * @return a XY anchor for the free zone figure
     */
    @objid ("9b203db9-f248-4183-88d4-55f2de1c8ca1")
    private ConnectionAnchor createAnchor(final Point absPoint) {
        tmpAnchor.setLocation(absPoint);
        getFigure().translateToRelative(tmpAnchor);
        PointAnchor pointAnchor = new PointAnchor(getFigure(), tmpAnchor);
        return pointAnchor;
    }

    /**
     * Creates a {@link PointAnchor} from the request location.
     */
    @objid ("cdc76ce0-b2e6-4a2d-8621-ba1dd3e1c215")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        if (request instanceof DropRequest) {
            final Point p = ((DropRequest) request).getLocation();
            return createAnchor(p);
        }
        throw new IllegalArgumentException(request + " not handled.");
    }

    /**
     * Creates a {@link PointAnchor} from the request location.
     */
    @objid ("d4bfefcd-f530-48e4-b1dd-04496cdfebfa")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        if (request instanceof DropRequest) {
            final Point p = ((DropRequest) request).getLocation();
            return createAnchor(p);
        }
        throw new IllegalArgumentException(request + " not handled.");
    }

}
