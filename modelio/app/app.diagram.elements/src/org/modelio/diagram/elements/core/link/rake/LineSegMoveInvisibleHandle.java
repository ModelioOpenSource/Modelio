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

package org.modelio.diagram.elements.core.link.rake;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.tools.ConnectionBendpointTracker;

/**
 * @author sshaw
 */
@objid ("805ebb91-1dec-11e2-8cad-001ec947c8cc")
public class LineSegMoveInvisibleHandle extends BendpointCreationInvisibleHandle {
    /**
     * Creates a new BendpointCreationHandle, sets its owner to <code>owner</code> and its index to <code>index</code>,
     * and sets its locator to a new {@link org.eclipse.draw2d.MidpointLocator}.
     * 
     * @param owner the moved connection
     * @param index the index of the moved bend point
     */
    @objid ("805ebb93-1dec-11e2-8cad-001ec947c8cc")
    public LineSegMoveInvisibleHandle(ConnectionEditPart owner, int index) {
        super(owner, index);
        
        PointList points = ((Connection) owner.getFigure()).getPoints();
        Point pt1 = points.getPoint(index);
        Point pt2 = points.getPoint(index + 1);
        if (Math.abs(pt1.x - pt2.x) < Math.abs(pt1.y - pt2.y)) {
            setCursor(Cursors.SIZEWE);
        } else {
            setCursor(Cursors.SIZENS);
        }
    }

    /**
     * Creates and returns a new {@link ConnectionBendpointTracker}.
     * 
     * @return the new ConnectionBendpointTracker
     */
    @objid ("805ebb9a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected DragTracker createDragTracker() {
        ConnectionBendpointTracker tracker;
        tracker = new ConnectionBendpointTracker((ConnectionEditPart) getOwner(), getIndex());
        tracker.setType(RequestConstants.REQ_CREATE_BENDPOINT);
        tracker.setDefaultCursor(getCursor());
        return tracker;
    }

}
