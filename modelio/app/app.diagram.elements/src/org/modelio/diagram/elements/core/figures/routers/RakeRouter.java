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
package org.modelio.diagram.elements.core.figures.routers;

import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AnchorListener;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.core.figures.geometry.Direction;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;
import org.modelio.diagram.elements.core.figures.geometry.Orientation;

/**
 * Router for links in rake mode.
 * <p>
 * A raked link has 2 bend points:
 * <li>the anchor where all raked links merge. This anchor is shared by all links on the same rake. The rake anchor must
 * be on the target side of the connection.
 * <li>and a second bend point that is orthogonal to the first one. The bend point must be on the source side of the
 * connection.
 * 
 * @author cmarin
 */
@objid ("7fbc9bad-1dec-11e2-8cad-001ec947c8cc")
public class RakeRouter implements ConnectionRouter {
    @objid ("7fbc9bb1-1dec-11e2-8cad-001ec947c8cc")
    private Map<Connection, RakeConstraint> constraints = new HashMap<>();

    @objid ("63606d20-1e83-11e2-8cad-001ec947c8cc")
    private static final Point P1 = new Point();

    @objid ("63606d22-1e83-11e2-8cad-001ec947c8cc")
    private static final Point P2 = new Point();

    @objid ("6362cf79-1e83-11e2-8cad-001ec947c8cc")
    private static final Point P3 = new Point();

    @objid ("6362cf7b-1e83-11e2-8cad-001ec947c8cc")
    private static final Point P4 = new Point();

    @objid ("636531d4-1e83-11e2-8cad-001ec947c8cc")
    private static final Point rakePos = new Point();

    @objid ("7fcae9b4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Object getConstraint(Connection connection) {
        return this.constraints.get(connection);
    }

    @objid ("7fcae9bc-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void invalidate(Connection connection) {
        // Nothing to do
    }

    @objid ("7fcae9c2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void remove(Connection connection) {
        final RakeConstraint old = this.constraints.remove(connection);
        if (old != null) {
            old.removeListener((AnchorListener) connection);
        }
        
    }

    /**
     * Route the connection with the rake on the target side on the connection.
     * @param conn the connection to route.
     */
    @objid ("7fcae9c8-1dec-11e2-8cad-001ec947c8cc")
    private void routeToTarget(Connection conn) {
        final ConnectionAnchor sourceAnchor = conn.getSourceAnchor();
        final ConnectionAnchor targetAnchor = conn.getTargetAnchor();
        
        final Rectangle targetBounds = getAnchorBounds(targetAnchor);
        
        PointList points = conn.getPoints();
        points.removeAllPoints();
        
        RakeConstraint c = this.constraints.get(conn);
        if (c == null) {
            throw new IllegalStateException("The connection has no layout constraint.");
        }
        
        // Reference points in absolute coordinates
        // The rake anchor locations are in relative coordinates
        Point srcRef;
        RakeRouter.rakePos.setLocation(c.getTargetRakeAnchor().getReferencePoint());
        conn.translateToAbsolute(RakeRouter.rakePos);
        
        // Get or guess rake orientation
        Orientation rakeOrientation = c.getOrientation();
        if (rakeOrientation==null) {
            Direction rakeDir = GeomUtils.getDirection(conn.getTargetAnchor().getLocation(RakeRouter.rakePos), targetBounds);
            rakeOrientation = GeomUtils.getOrientation(rakeDir);
            c.setOrientation(rakeOrientation);
        }
        
        // Align intermediate points on anchor reference point.
        if (rakeOrientation == Orientation.VERTICAL) {
            srcRef = new Point(sourceAnchor.getReferencePoint().x, RakeRouter.rakePos.y);
            RakeRouter.rakePos.x = targetAnchor.getReferencePoint().x;
        } else {
            srcRef = new Point(RakeRouter.rakePos.x, sourceAnchor.getReferencePoint().y);
            RakeRouter.rakePos.y = targetAnchor.getReferencePoint().y;
        }
        
        // Source anchor
        RakeRouter.P1.setLocation(sourceAnchor.getLocation(srcRef));
        
        // Bend point on the source side
        RakeRouter.P2.setLocation(srcRef);
        
        // Rake anchor on the target side
        RakeRouter.P3.setLocation(RakeRouter.rakePos);
        
        // Target anchor
        RakeRouter.P4.setLocation(targetAnchor.getLocation(RakeRouter.rakePos));
        
        // Align segments on source and target points.
        if (rakeOrientation == Orientation.VERTICAL) {
            RakeRouter.P3.x = RakeRouter.P4.x;
            RakeRouter.P2.x = RakeRouter.P1.x;
        } else {
            RakeRouter.P3.y = RakeRouter.P4.y;
            RakeRouter.P2.y = RakeRouter.P1.y;
        }
        
        conn.translateToRelative(RakeRouter.P1);
        conn.translateToRelative(RakeRouter.P2);
        conn.translateToRelative(RakeRouter.P3);
        conn.translateToRelative(RakeRouter.P4);
        
        points.addPoint(RakeRouter.P1);
        points.addPoint(RakeRouter.P2);
        points.addPoint(RakeRouter.P3);
        points.addPoint(RakeRouter.P4);
        conn.setPoints(points);
        
    }

    /**
     * Route the connection with the rake on the source side on the connection.
     * @param conn the connection to route.
     */
    @objid ("7fcae9ce-1dec-11e2-8cad-001ec947c8cc")
    private void routeToSource(Connection conn) {
        final ConnectionAnchor sourceAnchor = conn.getSourceAnchor();
        final ConnectionAnchor targetAnchor = conn.getTargetAnchor();
        
        final Rectangle srcBounds = getAnchorBounds(sourceAnchor);
        
        PointList points = conn.getPoints();
        points.removeAllPoints();
        
        RakeConstraint c = this.constraints.get(conn);
        if (c == null) {
            throw new IllegalStateException("The connection has no layout constraint.");
        }
        
        // Reference points in absolute coordinates
        Point ref2;
        
        // The rake anchor locations are in relative coordinates
        RakeRouter.rakePos.setLocation(c.getSourceRakeAnchor().getReferencePoint());
        conn.translateToAbsolute(RakeRouter.rakePos);
        
        final Direction rakeDir = GeomUtils.getDirection(conn.getSourceAnchor().getLocation(RakeRouter.rakePos),
                srcBounds);
        final Orientation rakeOrientation = GeomUtils.getOrientation(rakeDir);
        
        if (rakeOrientation == Orientation.VERTICAL) {
            ref2 = new Point(targetAnchor.getReferencePoint().x, RakeRouter.rakePos.y);
            RakeRouter.rakePos.x = sourceAnchor.getReferencePoint().x;
        } else {
            ref2 = new Point(RakeRouter.rakePos.x, targetAnchor.getReferencePoint().y);
            RakeRouter.rakePos.y = targetAnchor.getReferencePoint().y;
        }
        
        // Source anchor
        RakeRouter.P1.setLocation(sourceAnchor.getLocation(RakeRouter.rakePos));
        
        // Rake anchor on the target side
        RakeRouter.P2.setLocation(RakeRouter.rakePos);
        
        // Bend point on the source side
        RakeRouter.P3.setLocation(ref2);
        
        // Target anchor
        RakeRouter.P4.setLocation(targetAnchor.getLocation(ref2));
        
        // Align segments on source and target points.
        if (rakeOrientation == Orientation.VERTICAL) {
            RakeRouter.P3.x = RakeRouter.P4.x;
            RakeRouter.P2.x = RakeRouter.P1.x;
        } else {
            RakeRouter.P3.y = RakeRouter.P4.y;
            RakeRouter.P2.y = RakeRouter.P1.y;
        }
        
        conn.translateToRelative(RakeRouter.P1);
        conn.translateToRelative(RakeRouter.P2);
        conn.translateToRelative(RakeRouter.P3);
        conn.translateToRelative(RakeRouter.P4);
        
        points.addPoint(RakeRouter.P1);
        points.addPoint(RakeRouter.P2);
        points.addPoint(RakeRouter.P3);
        points.addPoint(RakeRouter.P4);
        
        conn.setPoints(points);
        
    }

    /**
     * Get the anchor owner bounds in absolute coordinates. If the anchor is not attached to a figure, returns a 1x1
     * sized rectangle located at the anchor reference point.
     * @param anchor The anchor.
     * @return The anchor bounds in absolute coordinates.
     */
    @objid ("7fcae9d4-1dec-11e2-8cad-001ec947c8cc")
    private Rectangle getAnchorBounds(ConnectionAnchor anchor) {
        final IFigure f = anchor.getOwner();
        if (f == null) {
            Point p = anchor.getReferencePoint();
            return new Rectangle(p.x, p.y, 1, 1);
        } else {
            final Rectangle bounds = f.getBounds().getCopy();
            f.translateToAbsolute(bounds);
        
            return bounds;
        }
        
    }

    @objid ("7fcae9dd-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setConstraint(Connection connection, Object constraint) {
        final RakeConstraint old = this.constraints.get(connection);
        if (old == constraint) {
            return;
        }
        
        if (old != null) {
            old.removeListener((AnchorListener) connection);
        }
        
        final RakeConstraint rakeConstraint = (RakeConstraint) constraint;
        this.constraints.put(connection, rakeConstraint);
        
        rakeConstraint.addListener((AnchorListener) connection);
        
    }

    @objid ("7fcae9e4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void route(Connection connection) {
        RakeConstraint c = this.constraints.get(connection);
        if (c == null) {
            return; // throw new IllegalStateException("The connection has no layout constraint.");
        }
        
        if (c.getSourceRakeAnchor() != null) {
            routeToSource(connection);
        } else if (c.getTargetRakeAnchor() != null) {
            routeToTarget(connection);
        }
        
    }

}
