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

import java.util.List;
import java.util.function.IntPredicate;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.modelio.diagram.elements.core.link.MPoint;

@objid ("c2833d84-bd2b-4614-941e-5287e4b96252")
class AutoOrthoUtils {
    /**
     * Convert a route to a constraint.
     * <p>
     * @param route a route computed by {@link #computeMPointRoute(Connection, List)}, {@link #computePointList(Connection)} ...
     * @return the same list striped from extremities, suitable as constraint.
     */
    @objid ("71be3553-1472-4995-bbf2-fe834d3a16e1")
    public static List<MPoint> routeToConstraint(List<MPoint> route) {
        // Remove first and last points that are anchors
        if (!route.isEmpty()) {
            route.remove(0);
        }
        
        if (!route.isEmpty()) {
            route.remove(route.size() - 1);
        }
        return route;
    }

    @objid ("873657cf-9623-49ca-8f27-acdfeff58d05")
    public static PointList toPointList(final List<MPoint> allPoints) {
        final PointList newPointList = new PointList(allPoints.size());
        for (int i = 0; i < allPoints.size(); i++) {
            MPoint bp = allPoints.get(i);
            newPointList.addPoint(bp.getLocation());
        }
        return newPointList;
    }

    /**
     * Compare a list of points with a PointList.
     * @param l1 a list of points
     * @param l2 a PointList
     * @return true only if both lists containe the same points in order.
     */
    @objid ("ca018d26-b7b3-4557-994e-911c1098e8d6")
    public static boolean areSame(final List<? extends Point> l1, PointList l2) {
        if (l1.size() != l2.size())
            return false;
        
        Point p = Point.SINGLETON;
        for (int i = 0; i < l2.size(); i++) {
            Point bp = l2.getPoint(p, i);
            if (! l1.get(i).equals(bp))
                return false;
        }
        return true;
    }

    /**
     * Finish by removing unnecessary points.
     * @param allPoints point list to clean unnecessary bend points from.
     * @param cleanFixed if true, manual points may be removed
     */
    @objid ("ea80f991-4dc3-431e-ad3c-bb1d0bc1fa6b")
    public static void cleanup(final List<MPoint> allPoints, boolean cleanFixed) {
        boolean pointsRemoved = false;
        // Removing unnecessary points:
        
        // 1: overlapping points.
        pointsRemoved = removeIf(allPoints, i -> {
            MPoint current = allPoints.get(i);
            MPoint next = allPoints.get(i + 1);
            if (current.getDistance(next) < 1) {
        
                // Here points are overlapping
                if (!current.isFixed()) {
                    // System.err.format("Deleting overlapping point [*%s*; %s] \n", current, next);
                    return true;
                }
                if (!next.isFixed()) {
                    // switch this point with next
                    next.setFixed(true);
                    // remove this point
                    return true;
                }
                // here both are manual
                if (cleanFixed) {
                    // System.err.format("Deleting overlapping point [*%s*; %s] (both manual)\n", current, next);
                }
                return cleanFixed;
        
            }
        
            return false;
        
        }) || pointsRemoved;
        
        // 2: allPoints not bending :
        // 1 --- 2 --- 3 => remove 2
        // 1 --- 3 --- 2 (conn goes from 1 to 2 then back to 3) ==> remove 2
        pointsRemoved = removeIf(allPoints, i -> {
            MPoint current = allPoints.get(i);
            MPoint previous = allPoints.get(i - 1);
            MPoint next = allPoints.get(i + 1);
            boolean toRemove = previous.x == next.x || previous.y == next.y;
            if (toRemove) {
                if (current.isFixed()) {
                    // This will switch the current fixed bend point to the next point
                    next.setFixed(true);
                }
            }
            return toRemove;
        }) || pointsRemoved;
        
        if (pointsRemoved) {
            // Some points were removed, try cleaning the new point list again
            cleanup(allPoints, cleanFixed);
        }
        
    }

    @objid ("0d91f67a-729c-4c79-96ac-2f352f1f3ac9")
    private static boolean removeIf(final List<MPoint> points, IntPredicate test) {
        boolean pointRemoved = false;
        for (int i = 1; i < points.size() - 1; ++i) {
            if (test.test(i)) {
                points.remove(i);
                i--;
                pointRemoved = true;
            }
        }
        return pointRemoved;
    }

}
