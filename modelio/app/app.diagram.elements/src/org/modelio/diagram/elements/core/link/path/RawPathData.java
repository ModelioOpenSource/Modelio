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
package org.modelio.diagram.elements.core.link.path;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Raw path data used to create a connection.
 */
@objid ("8052cfb3-1dec-11e2-8cad-001ec947c8cc")
public class RawPathData {
    /**
     * Connection routing mode.
     */
    @objid ("905c0b2c-1e83-11e2-8cad-001ec947c8cc")
    private ConnectionRouterId routingMode;

    /**
     * Bend point list, in absolute coordinates.
     */
    @objid ("64f0f831-1e83-11e2-8cad-001ec947c8cc")
    private List<Point> path = new ArrayList<>();

    /**
     * Point on the source node.
     */
    @objid ("64f5bce5-1e83-11e2-8cad-001ec947c8cc")
    private Point srcPoint;

    /**
     * Current last point;
     */
    @objid ("64f5bce7-1e83-11e2-8cad-001ec947c8cc")
    private Point lastPoint;

    /**
     * @return the raw path
     */
    @objid ("8052cfc5-1dec-11e2-8cad-001ec947c8cc")
    public List<Point> getPath() {
        return this.path;
    }

    /**
     * @return the routing mode
     */
    @objid ("8052cfce-1dec-11e2-8cad-001ec947c8cc")
    public ConnectionRouterId getRoutingMode() {
        return this.routingMode;
    }

    /**
     * Set the routing mode.
     * @param routingMode the routing mode
     */
    @objid ("8052cfd3-1dec-11e2-8cad-001ec947c8cc")
    public void setRoutingMode(final ConnectionRouterId routingMode) {
        this.routingMode = routingMode;
    }

    /**
     * Set the point in the source figure.
     * @param srcPoint the source point.
     */
    @objid ("8052cfd8-1dec-11e2-8cad-001ec947c8cc")
    public void setSrcPoint(final Point srcPoint) {
        this.srcPoint = srcPoint;
    }

    /**
     * Get the point in the source figure.
     * @return the source point.
     */
    @objid ("8052cfdf-1dec-11e2-8cad-001ec947c8cc")
    public Point getSrcPoint() {
        return this.srcPoint;
    }

    /**
     * Set the last point
     * @param lastPoint the last point.
     */
    @objid ("8052cfe6-1dec-11e2-8cad-001ec947c8cc")
    public void setLastPoint(final Point lastPoint) {
        this.lastPoint = lastPoint;
    }

    /**
     * Returns the last point
     * @return the last point.
     */
    @objid ("8052cfed-1dec-11e2-8cad-001ec947c8cc")
    public Point getLastPoint() {
        return this.lastPoint;
    }

    @objid ("afe100bb-11d1-482e-9c30-21e5ae8154e4")
    @Override
    public String toString() {
        if (DiagramElements.LOG.isDebugEnabled()) {
            return String.format("(%s - src=%s last=%s  path=%s\n", 
                    this.routingMode, this.srcPoint, this.lastPoint, this.path);
        } else {
            return super.toString();
        }
        
    }

}
