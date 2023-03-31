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
import org.eclipse.draw2d.Cursors;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.handles.ConnectionHandle;
import org.modelio.diagram.elements.core.figures.geometry.Orientation;

/**
 * Handle for move/translating a segment of a polyline connection.
 * 
 * @author fpoyer
 */
@objid ("80421f72-1dec-11e2-8cad-001ec947c8cc")
public class VerticalSegmentMoveHandle extends ConnectionHandle {
    @objid ("80421f76-1dec-11e2-8cad-001ec947c8cc")
    private int index;

    @objid ("80421f77-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected DragTracker createDragTracker() {
        ConnectionSegmentTracker tracker = new ConnectionSegmentTracker((ConnectionEditPart) getOwner(),
                getIndex(),
                Orientation.VERTICAL);
        tracker.setDefaultCursor(getCursor());
        tracker.setDisabledCursor(Cursors.NO);
        return tracker;
    }

    @objid ("80421f7e-1dec-11e2-8cad-001ec947c8cc")
    public int getIndex() {
        return this.index;
    }

    /**
     * Revalidates this handle when the connection's points change.
     * @param event the event that caused the points change
     */
    @objid ("80421f82-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        revalidate();
    }

    @objid ("80421f88-1dec-11e2-8cad-001ec947c8cc")
    public void setIndex(final int index) {
        this.index = index;
        setLocator(new SegmentLocator(getConnection(), index));
        ((ConnectionSegmentTracker) getDragTracker()).setIndex(index);
        
    }

    /**
     * @param owner the editpart of the connection
     * @param index the index.
     */
    @objid ("80421f8c-1dec-11e2-8cad-001ec947c8cc")
    public  VerticalSegmentMoveHandle(final ConnectionEditPart owner, final int index) {
        super();
        setOwner(owner);
        setIndex(index);
        
    }

    @objid ("80421f95-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void validate() {
        if (getIndex() < 0 || getIndex() >= getConnection().getPoints().size() - 1) {
            return;
        }
        super.validate();
        
    }

{
            setCursor(Cursors.SIZEWE);
        }
    
}
