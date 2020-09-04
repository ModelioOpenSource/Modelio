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

package org.modelio.diagram.elements.core.link.path;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;

/**
 * Utilities to convert draw2d connection bend point contraints to model constraint and reverse.
 * @author cma
 * @since 3.7
 */
@objid ("94243f78-e2cd-4b15-b7e8-34a4f9494076")
public class BendPointUtils {
    @objid ("4368d59c-afca-4472-b29e-013c552e3025")
    public static List<Point> draw2dPointsToModelConstraint(List<Point> draw2dPoints) {
        return draw2dPoints;
    }

    @objid ("df866947-e3d8-4fb5-bafe-7f83c18fcb1c")
    public static List<Point> draw2dConstraintToModelConstraint(List<Bendpoint> draw2dConstraint) {
        List<Point> points = new ArrayList<>(draw2dConstraint.size());
        
        for (Bendpoint bendpoint : draw2dConstraint) {
            Point bp = new Point(bendpoint.getLocation());
            points.add(bp);
        }
        return points;
    }

    @objid ("0e04bd20-cc57-4c2c-8d91-5b1b511522fa")
    public static List<Bendpoint> toDraw2dConstraint(List<Point> modelConstraint) {
        List<Bendpoint> ret = new ArrayList<>(modelConstraint.size());
        for (Point p : modelConstraint) {
            AbsoluteBendpoint bp = new AbsoluteBendpoint(p.x, p.y);
            ret.add(bp);
        }
        return ret;
    }

    /**
     * Get the connection points in a java standard list of points,.
     * 
     * @param connection a PolylineConnection figure.
     * @return a list of points in coordinates relative to the connection.
     */
    @objid ("9bef32f7-96aa-41e2-8459-be461d08f9e2")
    public static List<Point> getConnectionPoints(PolylineConnection connection) {
        PointList list = connection.getPoints();
        int size = list.size();
        List<Point> ret = new ArrayList<>(size);
        for (int i=0; i< size; i++) {
            ret.add(list.getPoint(i));
        }
        return ret;
    }

}
