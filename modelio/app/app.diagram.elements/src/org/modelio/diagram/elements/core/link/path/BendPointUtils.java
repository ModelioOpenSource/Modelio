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
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.modelio.diagram.elements.core.link.IMPoint;
import org.modelio.diagram.elements.core.link.MPoint;

/**
 * Utilities to convert draw2d connection bend point contraints to model constraint and reverse.
 * 
 * @author cma
 * @since 3.7
 */
@objid ("94243f78-e2cd-4b15-b7e8-34a4f9494076")
public class BendPointUtils {
    /**
     * No instance
     */
    @objid ("915624b3-89ba-41a9-8ade-0a116f7d7e3c")
    private  BendPointUtils() {
        // no instance
    }

    /**
     * Make a deep copy of a complete routing constraint returns it.
     * @param fromConstraint the connection to rebuild a constraint for.
     * @return a copy of the constraint
     */
    @objid ("47dfbba0-a114-4de2-9aa9-489081e5e173")
    public static List<MPoint> copyConstraint(List<MPoint> fromConstraint) {
        List<MPoint> newConstraint = new ArrayList<>(fromConstraint.size());
        for (MPoint point : fromConstraint) {
            newConstraint.add(point.getCopy());
        }
        return newConstraint;
    }

    /**
     * Make best effort to guess whether the given object is a list of {@link MPoint MPoints}.
     * <p>
     * Returns false for null, true for an empty list.
     * For all other cases test the first element.
     * @param routingConstraint an object usually used as connection routing constraint.
     * @return true if it looks like a MPoint list else false.
     */
    @objid ("f760d9b1-0780-4353-b081-b35e0d0ccb33")
    public static boolean isMPointList(Object routingConstraint) {
        if (! (routingConstraint instanceof List))
            return false;
        
        List<?> l = (List<?>) routingConstraint;
        
        if (l.isEmpty()) {
            // le doute beneficie a l'accuse
            return true;
        }
        
        Object o = l.get(0);
        return o instanceof MPoint;
    }

    @objid ("4368d59c-afca-4472-b29e-013c552e3025")
    public static List<Point> draw2dPointsToModelConstraint(List<Point> draw2dPoints) {
        List<Point> points = new ArrayList<>(draw2dPoints.size());
        
        for (Point p : draw2dPoints) {
            MPoint bp = new MPoint(p.x, p.y, p instanceof MPoint ? ((MPoint) p).isFixed() : true);
            points.add(bp);
        }
        return points;
    }

    /**
     * @param draw2dConstraint a draw2d constraint
     * @return a list of Points containing {@link MPoint MPoints}
     */
    @objid ("df866947-e3d8-4fb5-bafe-7f83c18fcb1c")
    public static List<Point> draw2dConstraintToModelConstraint(List<Bendpoint> draw2dConstraint) {
        List<Point> points = new ArrayList<>(draw2dConstraint.size());
        
        for (Bendpoint bendpoint : draw2dConstraint) {
            Point p = bendpoint.getLocation();
            MPoint bp = new MPoint(p.x, p.y, p instanceof MPoint ? ((MPoint) p).isFixed() : true);
            points.add(bp);
        }
        return points;
    }

    /**
     * @param modelConstraint a graphic model constraint
     * @return a list of Bendpoint containing {@link MPoint MPoints}
     */
    @objid ("0e04bd20-cc57-4c2c-8d91-5b1b511522fa")
    public static List<Bendpoint> toDraw2dConstraint(List<Point> modelConstraint) {
        List<Bendpoint> ret = new ArrayList<>(modelConstraint.size());
        for (Point p : modelConstraint) {
            MPoint bp = new MPoint(p.x, p.y, p instanceof MPoint ? ((MPoint) p).isFixed() : true);
            ret.add(bp);
        }
        return ret;
    }

    /**
     * Get the connection points in a java standard list of points,.
     * @param connection a PolylineConnection figure.
     * @return a list of points in coordinates relative to the connection.
     */
    @objid ("9bef32f7-96aa-41e2-8459-be461d08f9e2")
    public static List<Point> getConnectionPoints(Connection connection) {
        PointList list = connection.getPoints();
        int size = list.size();
        List<Point> ret = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            ret.add(list.getPoint(i));
        }
        return ret;
    }

    /**
     * Get the position of the point at the given index from the anchors and routing constraint.
     * @param connection The connection to query.
     * @param constraint The routing constraint for convenience, avoid casts. It is expected to have the number of connection points - 2.
     * @param index the index of the point to modify in the points list.<br>
     * <li><b>0</b> is the source anchor,
     * <li><b>1</b> is the first bend point,
     * <li><b>c.size()</b> is the last bend point,
     * <li><b>c.size()+1</b> is the target anchor.
     * @return the point location in relative coordinates. The point may be returned by reference and must not be copied.
     */
    @objid ("4545cc75-a8e3-4fc2-b957-8b5fd7145774")
    public static Point getConstrainedPoint(Connection connection, List<MPoint> constraint, int index) {
        if (index == 0) {
            // Get source anchor.
            Point ref;
            if (constraint.isEmpty()) {
                ref = connection.getTargetAnchor().getReferencePoint();
            } else {
                ref = constraint.get(0).getCopy();
                connection.translateToAbsolute(ref);
            }
        
            Point ret = connection.getSourceAnchor().getLocation(ref).getCopy();
            connection.translateToRelative(ret);
            return ret;
        } else if (index == constraint.size() + 1) {
            // Get target anchor.
            Point ref;
            if (constraint.isEmpty()) {
                ref = connection.getSourceAnchor().getReferencePoint();
            } else {
                ref = constraint.get(constraint.size() - 1).getCopy();
                connection.translateToAbsolute(ref);
            }
        
            Point ret = connection.getTargetAnchor().getLocation(ref).getCopy();
            connection.translateToRelative(ret);
            return ret;
        } else {
            return constraint.get(index - 1);
        }
        
    }

    /**
     * Get the position of the point at the given index from the anchors and routing constraint.
     * <p>
     * Fills output with the point location in relative coordinates. This method minimize the number of point allocations. {@link IMPoint#setFixed(boolean)} is called with true for manual bend points and anchors, false for automatic anchors.
     * @param <P>           the type of the passed point
     * @param output the point that will receive the point location in relative coordinates.
     * @param connection The connection to query.
     * @param constraint The routing constraint for convenience, avoid casts. It is expected to have the number of connection points - 2.
     * @param index the index of the point to modify in the points list.<br>
     * <li><b>0</b> is the source anchor,
     * <li><b>1</b> is the first bend point,
     * <li><b>c.size()</b> is the last bend point,
     * <li><b>c.size()+1</b> is the target anchor.
     * @param anchorIsfixed {@link IMPoint#setFixed(boolean)} is called with this value with anchors
     * @return output for convenience. the point location in relative coordinates.
     */
    @objid ("2731a21f-72a7-4118-bdf8-ced68bc804fb")
    public static <P extends IMPoint<P>> P getConstrainedPoint(P output, Connection connection, List<MPoint> constraint, int index, boolean anchorIsfixed) {
        if (index == 0) {
            // Get source anchor.
            // hack : use output as ref to minimize allocations.
            P ref = output;
            if (constraint.isEmpty()) {
                ref.setLocation(connection.getTargetAnchor().getReferencePoint());
            } else {
                ref.setLocation(constraint.get(0));
                connection.translateToAbsolute(ref);
            }
        
            output.setLocation(connection.getSourceAnchor().getLocation(ref.asPoint()));
            output.setFixed(anchorIsfixed);
            connection.translateToRelative(output);
        
            return output;
        } else if (index == constraint.size() + 1) {
            // Get target anchor.
            // hack : use output as ref to minimize allocations.
            P ref = output;
            if (constraint.isEmpty()) {
                ref.setLocation(connection.getSourceAnchor().getReferencePoint());
            } else {
                ref.setLocation(constraint.get(constraint.size() - 1));
                connection.translateToAbsolute(ref);
            }
        
            output.setLocation(connection.getTargetAnchor().getLocation(ref.asPoint()));
            output.setFixed(anchorIsfixed);
            connection.translateToRelative(output);
        
            return output;
        } else {
            return output.setValues(constraint.get(index - 1));
        }
        
    }

    /**
     * Build a MPoint list routing constraint from the Connection points.
     * <p>
     * The MPoints are all automatic.
     * @param connection a connection
     * @return a MPoint list routing constraint.
     */
    @objid ("81c6e371-7325-4c5d-8a33-509796f30cb4")
    public static List<MPoint> connectionPointsToMPointsConstraint(Connection connection) {
        PointList pts = connection.getPoints();
        ArrayList<MPoint> ret = new ArrayList<>(pts.size()-2);
        for (int i=1, n=pts.size()-1; i<n; i++ ) {
            MPoint p = new MPoint();
            pts.getPoint(p, i);
            ret.add(p);
        }
        return ret;
    }

}
