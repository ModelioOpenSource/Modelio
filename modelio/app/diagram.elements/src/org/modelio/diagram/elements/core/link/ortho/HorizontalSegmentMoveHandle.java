/* 
 * Copyright 2013-2018 Modeliosoft
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
@objid ("80363399-1dec-11e2-8cad-001ec947c8cc")
public class HorizontalSegmentMoveHandle extends ConnectionHandle {
    @objid ("8036339d-1dec-11e2-8cad-001ec947c8cc")
    private int index;

    /**
     * @param owner the editpart of the connection
     * @param index the index.
     */
    @objid ("8036339e-1dec-11e2-8cad-001ec947c8cc")
    public HorizontalSegmentMoveHandle(final ConnectionEditPart owner, final int index) {
        super();
        setOwner(owner);
        setIndex(index);
    }

    @objid ("803633a7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected DragTracker createDragTracker() {
        ConnectionSegmentTracker connectionSegmentTracker = new ConnectionSegmentTracker((ConnectionEditPart) getOwner(),
                                                                                         getIndex(),
                                                                                         Orientation.HORIZONTAL);
        connectionSegmentTracker.setDefaultCursor(getCursor());
        return connectionSegmentTracker;
    }

    @objid ("803633ae-1dec-11e2-8cad-001ec947c8cc")
    public int getIndex() {
        return this.index;
    }

    /**
     * Revalidates this handle when the connection's points change.
     * @param event the event that caused the points change
     */
    @objid ("803633b2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        revalidate();
    }

    @objid ("803633b8-1dec-11e2-8cad-001ec947c8cc")
    public void setIndex(final int index) {
        this.index = index;
        setLocator(new SegmentLocator(getConnection(), index));
        ((ConnectionSegmentTracker) getDragTracker()).setIndex(index);
    }

    @objid ("803633bc-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void validate() {
        if (getIndex() < 0 || getIndex() >= getConnection().getPoints().size() - 1)
            return;
        super.validate();
    }


{
        setCursor(Cursors.SIZENS);
    }
}
