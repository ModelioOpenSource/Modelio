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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.geometry.Point;

/**
 * Locator for a Segment on a Connection.
 * 
 * @author fpoyer
 */
@objid ("803fbd31-1dec-11e2-8cad-001ec947c8cc")
public class SegmentLocator extends ConnectionLocator {
    @objid ("803fbd35-1dec-11e2-8cad-001ec947c8cc")
    private int index;

    @objid ("dc97832f-fe72-467a-b7fa-d4558820313c")
    private static final Point ref1 = new Point();

    @objid ("d6527bdf-345c-4aee-a7ba-5a008d9e2b1e")
    private static final Point ref2 = new Point();

    /**
     * Creates a new SegmentLocator for the segment between the points index and index + 1 of the connection.
     * 
     * @param connection Connection associated with SegmentLocator
     * @param index index of Segment
     */
    @objid ("803fbd3e-1dec-11e2-8cad-001ec947c8cc")
    public SegmentLocator(final Connection connection, final int index) {
        super(connection);
        this.index = index;
    }

    /**
     * Returns the index of this segment locator. This index is the position of the segment in this SegmentLocator's
     * {@link Connection}.
     * 
     * @return The index
     */
    @objid ("80421f45-1dec-11e2-8cad-001ec947c8cc")
    public int getIndex() {
        return this.index;
    }

    @objid ("80421f4a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Point getReferencePoint() {
        getConnection().getPoints().getPoint(ref1, getIndex());
        getConnection().getPoints().getPoint(ref2, getIndex() + 1);
        Point p = Point.SINGLETON.setLocation((ref1.x + ref2.x) / 2, (ref1.y + ref2.y) / 2);
        getConnection().translateToAbsolute(p);
        return p;
    }

}
