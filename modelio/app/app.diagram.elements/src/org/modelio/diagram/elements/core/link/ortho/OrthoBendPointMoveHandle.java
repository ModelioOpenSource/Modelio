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
package org.modelio.diagram.elements.core.link.ortho;

import java.beans.PropertyChangeEvent;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BendpointLocator;
import org.eclipse.draw2d.Cursors;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.handles.ConnectionHandle;
import org.eclipse.gef.tools.ConnectionBendpointTracker;
import org.modelio.diagram.elements.core.figures.geometry.Orientation;

/**
 * Handle for move/translating a bend point of a polyline connection.
 * 
 * @author fpoyer
 */
@objid ("802f0c6e-1dec-11e2-8cad-001ec947c8cc")
class OrthoBendPointMoveHandle extends ConnectionHandle {
    @objid ("802f0c72-1dec-11e2-8cad-001ec947c8cc")
    private int index;

    @objid ("90d0de69-1e83-11e2-8cad-001ec947c8cc")
    private Orientation orientationOfPreviousSegment = Orientation.NONE;

    @objid ("802f0c74-1dec-11e2-8cad-001ec947c8cc")
    protected int getIndex() {
        return this.index;
    }

    @objid ("802f0c78-1dec-11e2-8cad-001ec947c8cc")
    private void setIndex(final int index) {
        this.index = index;
        setLocator(new BendpointLocator(getConnection(), index));
        ((ConnectionBendpointTracker) getDragTracker()).setIndex(index);
        
    }

    /**
     * C'tor.
     * @param owner the owning connection edit part.
     * @param index the index of the associated bendpoint.
     */
    @objid ("802f0c7c-1dec-11e2-8cad-001ec947c8cc")
    public  OrthoBendPointMoveHandle(final ConnectionEditPart owner, final int index) {
        super();
        setOwner(owner);
        setIndex(index);
        
    }

    @objid ("802f0c85-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected DragTracker createDragTracker() {
        OrientedBendpointTracker tracker = new OrientedBendpointTracker((ConnectionEditPart) getOwner(),
                getIndex(),
                getOrientationOfPreviousSegment());
        tracker.setType(RequestConstants.REQ_MOVE_BENDPOINT);
        tracker.setDefaultCursor(getCursor());
        tracker.setDisabledCursor(Cursors.NO);
        return tracker;
    }

    /**
     * Revalidates this handle when the connection's points change.
     * @param event the event that caused the points change
     */
    @objid ("80316ec8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        revalidate();
    }

    @objid ("80316ece-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void validate() {
        // skip validation if invalid index
        if (getIndex() < 0 || getIndex() >= getConnection().getPoints().size()) {
            return;
        }
        
        super.validate();
        
    }

    /**
     * C'tor.
     * @param owner the owning connection edit part.
     * @param index the index of the associated bendpoint.
     * @param orientationOfPreviousSegment the orientation of the segment preceding the associated bendpoint.
     */
    @objid ("80316ed1-1dec-11e2-8cad-001ec947c8cc")
    public  OrthoBendPointMoveHandle(final ConnectionEditPart owner, final int index, final Orientation orientationOfPreviousSegment) {
        super();
        setOwner(owner);
        setIndex(index);
        setOrientationOfPreviousSegment(orientationOfPreviousSegment);
        
    }

    /**
     * @param orientationOfPreviousSegment the orientation of the segment preceding the associated bendpoint.
     */
    @objid ("80316edc-1dec-11e2-8cad-001ec947c8cc")
    private void setOrientationOfPreviousSegment(final Orientation orientationOfPreviousSegment) {
        this.orientationOfPreviousSegment = orientationOfPreviousSegment;
        ((OrientedBendpointTracker) getDragTracker()).setOrientation(this.orientationOfPreviousSegment);
        
    }

    @objid ("80316ee1-1dec-11e2-8cad-001ec947c8cc")
    private Orientation getOrientationOfPreviousSegment() {
        return this.orientationOfPreviousSegment;
    }
{
            setCursor(Cursors.SIZEALL);
        }
    
}
